/*
    Description:    Calculate the Multiplier:Final Value for Sampling_Isokinetic_ASE
    Created by:     Marino Orsi
    Created:        2023-10-16
    Modification log (add below)
    Date            Name                            Description
    2024-04-12      Marino Orsi                     Allow data to be entered directly on Sampling PL
    yyyy-MM-dd      xxxxx                           xxxxx
*/

// Generic variable to hold the calculated data
def CalcResult = 0;

// Exit and return 1 if it's not a Unknown sample
if (!(${primary:qcsampletype} in ['Unknown','Dup'])) {return 1;}

// Get SDIDataItem
def SDIDataItem = SGS.getSDIDataitem('labvantage', ${primary:s_sampleid}, ${paramlistid}, ${paramlistversionid}.toString(), ${variantid}, ${dataset}.toString(), 'Multiplier', 'Final Value', 'max');

// Field Condition PL
def FieldPL = 'Field_Condition_ASE';

// Get an array with parameter value
def FlowRate = SGS.getNumericResults('labvantage', SDIDataItem, ${paramlistid}, '#', '#', '#', 'FlowRate', 'Final Value', '*', true);
def SamplingDuration = SGS.getNumericResults('labvantage', SDIDataItem, ${paramlistid}, '#', '#', '#', 'SamplingDuration', 'Final Value', '#', true);
def SampledAirVolume = SGS.getNumericResults('labvantage', SDIDataItem, ${paramlistid}, '#', '#', '#', 'SampledAirVolume', 'Final Value', '#', true);
def MeterTemperature = SGS.getNumericResults('labvantage', SDIDataItem, ${paramlistid}, '#', '#', '#', 'MeterTemperature', 'Final Value', '#', true);
def AtmosphericPressure = SGS.getNumericResults('labvantage', SDIDataItem, ${paramlistid}, '#', '#', '#', 'AtmosphericPressure', 'Final Value', '#', true);
def DGM_Coeff = SGS.getNumericResults('labvantage', SDIDataItem, ${paramlistid}, '#', '#', '#', 'DGM_Coeff', 'Final Value', '#', true);
def ActualOrifice = SGS.getNumericResults('labvantage', SDIDataItem, ${paramlistid}, '#', '#', '#', 'ActualOrifice', 'Final Value', '#', true);

// Choose between value in the PL (if exists) or from Field_Condition PL --- the priority is the value from the same PL
FlowRate = FlowRate.length == 0 ? SGS.getNumericResults('labvantage', SDIDataItem, FieldPL, '*', '*', 'max', 'FlowRate', 'Final Value', '*', false) : FlowRate;
SamplingDuration = SamplingDuration.length == 0 ? SGS.getNumericResults('labvantage', SDIDataItem, FieldPL, '*', '*', 'max', 'SamplingDuration', 'Final Value', '*', false) : SamplingDuration;
SampledAirVolume = SampledAirVolume.length == 0 ? SGS.getNumericResults('labvantage', SDIDataItem, FieldPL, '*', '*', 'max', 'SampledAirVolume', 'Final Value', '*', false) : SampledAirVolume;
MeterTemperature = MeterTemperature.length == 0 ? SGS.getNumericResults('labvantage', SDIDataItem, FieldPL, '*', '*', 'max', 'MeterTemperature', 'Final Value', '*', false) : MeterTemperature;
AtmosphericPressure = AtmosphericPressure.length == 0 ? SGS.getNumericResults('labvantage', SDIDataItem, FieldPL, '*', '*', 'max', 'AtmosphericPressure', 'Final Value', '*', false) : AtmosphericPressure;
DGM_Coeff = DGM_Coeff.length == 0 ? SGS.getNumericResults('labvantage', SDIDataItem, FieldPL, '*', '*', 'max', 'DGM_Coeff', 'Final Value', '*', false) : DGM_Coeff;
ActualOrifice = ActualOrifice.length == 0 ? SGS.getNumericResults('labvantage', SDIDataItem, FieldPL, '*', '*', 'max', 'ActualOrifice', 'Final Value', '*', false) : ActualOrifice;

// If the sampled volume is filled, use it on the calculation; else, calculate it from the flow rate and duration
SampledAirVolume = SampledAirVolume.length == 0 ? SGS.average(FlowRate) * SamplingDuration[0] : SampledAirVolume[0];

// Generic calculation
def DeltaH = ActualOrifice[0] / 13.6;
def PresTempConst = (298.15 / 760);
def PresDeltaH = AtmosphericPressure[0] + DeltaH;
MeterTemperature = MeterTemperature[0] + 273.15;

// Calculate the corrected volume
CalcResult = SampledAirVolume * DGM_Coeff[0] * PresTempConst * (PresDeltaH / MeterTemperature);

return CalcResult / 1000;
