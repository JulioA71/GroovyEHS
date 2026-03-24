/*
    Description:    Calculate the Multiplier:Final Value for SGSIH_WIEHSLA043_AIH
    Created by:     JA
    Created:        2023-12-19
    Modification log (add below)
    Date            Name                            Description
    yyyy-MM-dd      xxxxx                           xxxxx
*/
// Variable declaration - modify the parameters below with the source (where the data comes from) and destination (where the data goes to)
def SourcePL = 'Sampling_AIH';
def SourcePLUncorrected = 'Sampling_Uncorrected_AIH';
String ParamID = ${#;#;#;paramid}.toString();	    // define the parameter for the calculation
String ParamType = ${#;#;#;paramtype}.toString(); 	// define the parameter type
def SampleType = ${primary:qcsampletype};		    // define the sample type

// Generic variable to hold the calculated data
def CalcResult = 0;

// Variable to hold data related to the SDI
def SDIDataItem = SGS.getSDIDataitem('labvantage', ${primary:s_sampleid}, ${paramlistid}, ${paramlistversionid}.toString(), ${variantid}, ${dataset}.toString(), ParamID, ParamType, 'max');

// Execute code only if there's value on DF parameter
if (SGS.checkResults('labvantage', SDIDataItem, ${paramlistid}, ${paramlistversionid}.toString(), ${variantid}, 'max', 'DF', 'Final Value', 'max', true))
{
    if (SampleType == "Unknown")
    {
        def CorrectedValue = SGS.getNumericResults('labvantage', SDIDataItem, SourcePL, '*', '*', 'max', ParamID, ParamType, 'max', false);
        def UncorrectedValue = SGS.getNumericResults('labvantage', SDIDataItem, SourcePLUncorrected, '*', '*', 'max', ParamID, ParamType, 'max', false);
        if (CorrectedValue.length > 0)
        {
            CalcResult = CorrectedValue[0];
        }
        else if (UncorrectedValue.length > 0)
        {
            CalcResult = UncorrectedValue[0];
        }
        else
        {
            return 0;
        }
    }
    else if (SampleType == "Dup")
    {
        def LinkedUnkID = SGS.getUnknownSampleLink(SDIDataItem);
        def SourceValue = SGS.sampleNumericResults('labvantage', SDIDataItem, LinkedUnkID[0], ${paramlistid}, '*', '*', 'max', ParamID, ParamType, 'max');
        CalcResult = 1 / SourceValue[0];
    }
    else
    {
        CalcResult = 1;
    }
    return 1 / CalcResult
}