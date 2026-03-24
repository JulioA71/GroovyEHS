/*
	Description:	Calculate the Multiplier:Final Value for Sampling_FlowCorrected_AA PL
	Created by:		Marino Orsi
	Created:		2024-03-15
	Modification log (add below)
	Date			Name			Description
	2025-02-28		Marino Orsi		Add param to choose wheter to use or not the Field Condition
	2025-05-15		Marino Orsi		Added dummy parameter to trigger Redo Calculation
	yyyy-MM-dd		xxxxx			xxxxx

	NOTES
		1. Flow rate correction by the temperature and pressure according to SNI method - ASTM method doesn't use the sqrt on the formula.
*/

// Dummy parameter to trigger Redo Calculation
def dummy = ${FieldConditionPL;Final Value}

// Variable initialization
def CalcResult = 0;
def AirVolume = 0;
def FlowRate;
def SamplingDuration;
def SampledAirVolume;
def Temperature;
def AtmosphericPressure;

// Exit and return 1 if it's not a Unknown sample
if (!(${primary:qcsampletype} in ['Unknown','Dup'])) {return 1;}

// Field Condition PL
def FieldPL = 'Field_Condition_AA';

// Get SDIDataItem
def SDIDataItem = SGS.getSDIDataitem('labvantage', ${primary:s_sampleid}, ${paramlistid}, ${paramlistversionid}.toString(), ${variantid}, ${dataset}.toString(), 'Multiplier', 'Final Value', 'max');

// Get FieldConditionPL value and check if Field PL will be used
def UseFieldConditionPL = SGS.getTextResults('labvantage', SDIDataItem, ${paramlistid}, ${paramlistversionid}.toString(), ${variantid}, ${dataset}.toString(), 'FieldConditionPL', 'Final Value', 'max', true);

if (UseFieldConditionPL.length > 0)
{
	if (UseFieldConditionPL[0] == "Y")
	{
		// Get an array with parameter value from the Field_Condition PL
		FlowRate = SGS.getNumericResults('labvantage', SDIDataItem, FieldPL, '*', '*', 'max', 'FlowRate', 'Final Value', '*', false);
		SamplingDuration = SGS.getNumericResults('labvantage', SDIDataItem, FieldPL, '*', '*', 'max', 'SamplingDuration', 'Final Value', '*', false);
		SampledAirVolume = SGS.getNumericResults('labvantage', SDIDataItem, FieldPL, '*', '*', 'max', 'SampledAirVolume', 'Final Value', '*', false);
		if (SampledAirVolume.length > 0) { AirVolume = SampledAirVolume[0] / 1000; }
		Temperature = SGS.getNumericResults('labvantage', SDIDataItem, FieldPL, '*', '*', 'max', 'Temperature', 'Final Value', '*', false);
		AtmosphericPressure = SGS.getNumericResults('labvantage', SDIDataItem, FieldPL, '*', '*', 'max', 'AtmosphericPressure', 'Final Value', '*', false);
	}
	else
	{
		// Get an array with parameter value from the same PL
		FlowRate = SGS.getNumericResults('labvantage', SDIDataItem, ${paramlistid}, '#', '#', '#', 'FlowRate', 'Final Value', '*', true);
		SamplingDuration = SGS.getNumericResults('labvantage', SDIDataItem, ${paramlistid}, '#', '#', '#', 'SamplingDuration', 'Final Value', '#', true);
		SampledAirVolume = SGS.getNumericResults('labvantage', SDIDataItem, ${paramlistid}, '#', '#', '#', 'SampledAirVolume', 'Final Value', '#', true);
		if (SampledAirVolume.length > 0) { AirVolume = SampledAirVolume[0]; }
		Temperature = SGS.getNumericResults('labvantage', SDIDataItem, ${paramlistid}, '#', '#', '#', 'Temperature', 'Final Value', '#', true);
		AtmosphericPressure = SGS.getNumericResults('labvantage', SDIDataItem, ${paramlistid}, '#', '#', '#', 'AtmosphericPressure', 'Final Value', '#', true);
	}

	// If the sampled volume is filled, use it on the calculation; else, calculate it from the flow rate and duration
	if (SampledAirVolume.length == 0)
	{
		FlowRate = SGS.average(FlowRate);
		FlowRate = FlowRate * Math.sqrt((298 * AtmosphericPressure[0]) / ((Temperature[0] + 273) * 760));
		CalcResult = FlowRate * SamplingDuration[0];
	}
	else
	{
		CalcResult = AirVolume * (298 * AtmosphericPressure[0]) / ((Temperature[0] + 273) * 760);
	}

	return CalcResult;
}
