/*
    Description:    Calculate the multiplier based on the sampling time and air flow OR sampled air volume
    Created by:     Marino Orsi
    Created:        2023-10-04
    Modification log (add below)
    Date            Name                            Description
    yyyy-MM-dd      xxxxx                           xxxxx
*/

// Generic variable to hold the calculated data
def CalcResult = 0;

// Variable to hold data related to the SDI
def Param = 'Multiplier'
def ParamType = 'Final Value'
def SDIDataItem= SGS.getSDIDataitem('labvantage', ${primary:s_sampleid}, ${paramlistid}, ${paramlistversionid}.toString(), ${variantid}, ${dataset}.toString(), Param, ParamType, 'max');

// Variable declaration
def Volume = SGS.getNumericResults('labvantage',SDIDataItem, ${paramlistid},'*','*', 'max', 'SampledAirVolume', 'Final Value', 'max', true);
def Flow = ${FlowRate;Final Value};
def Time = ${SamplingDuration;Final Value};
def Temperature = ${Temperature;Final Value} + 273;
def Pressure = ${AtmosphericPressure;Final Value};
def CorVolume = 0;

if (Volume.length == 0)
{
    CorVolume = (Flow * Time);
}
else
{
    CorVolume = Volume[0];
}

return CorVolume / 1000;
