/*
    Description:    Calculate the Multiplier:Final Value for SGSIH_WIEHSLA043_AA
    Created by:     Marino Orsi
    Created:        2023-11-15
    PL:             SGSIH_WIEHSLA043_AA
    Parameter:      Multiplier (Final Value)
    Modification log (add below)
    Date            Name                            Description
    yyyy-MM-dd      xxxxx                           xxxxx
*/

// Variable declaration - modify the parameters below with the source (where the data comes from) and destination (where the data goes to)
def SourcePL = 'Sampling_AA';
String ParamID = ${#;#;#;paramid}.toString();	    // define the parameter for the calculation
String ParamType = ${#;#;#;paramtype}.toString(); 	// define the parameter type
def SampleType = ${primary:qcsampletype};		    // define the sample type

// Generic variable to hold the calculated data
def CalcResult = 0;

// Variable to hold data related to the SDI
def SDIDataItem= SGS.getSDIDataitem('labvantage', ${primary:s_sampleid}, ${paramlistid}, ${paramlistversionid}.toString(), ${variantid}, ${dataset}.toString(), ParamID, ParamType, 'max');

// Execute code only if there's value on DF parameter
if (SGS.checkResults('labvantage', SDIDataItem, ${paramlistid}, ${paramlistversionid}.toString(), ${variantid}, 'max', 'DF', 'Final Value', 'max', true))
{
    if (SampleType == "Unknown")
    {
        def SourceValue = SGS.getNumericResults('labvantage',SDIDataItem, SourcePL,'*','*', 'max', ParamID, ParamType, 'max', false);
        CalcResult = SourceValue[0];
    }
    else if (SampleType == "Dup")
    {
        def LinkedUnkID = SGS.getUnknownSampleLink(SDIDataItem)
        def SourceValue = SGS.sampleNumericResults('labvantage', SDIDataItem, LinkedUnkID[0], SourcePL, '*', '*', 'max', ParamID, ParamType, 'max')
        CalcResult = SourceValue[0];
    }
    else
    {
        CalcResult = 1;
    }
    return 1 / CalcResult
}

/*
    Description:    Calculate the 7647-01-0:Final Value for SGSIH_WIEHSLA043_AA
    Created by:     JA
    Created:        2024-01-09
    PL:             SGSIH_WIEHSLA043_AA
    Parameter:      10028-15-6 (Final Value)
    Modification log (add below)
    Date            Name                            Description
    yyyy-MM-dd      xxxxx                           xxxxx
*/

// Variable declaration
def SampleType = ${primary:qcsampletype};

// Generic variable to hold the calculated data
def CalcResult = 0;
def QCResult = 0;

// Exit if it's not a Unknown sample
if (!(SampleType in ['Unknown','Dup']))
{
	return CalcResult;
}
def SDIDataItem= SGS.getSDIDataitem('labvantage', ${primary:s_sampleid}, ${paramlistid}, ${paramlistversionid}.toString(), ${variantid}, ${dataset}.toString(), 'Multiplier', 'Final Value', 'max');
def BlankCorrectedValue = SGS.getNumericResults('labvantage', SDIDataItem, ${paramlistid}, ${paramlistversionid}.toString(), ${variantid}, ${dataset}.toString(), ${#;#;#;paramid}, 'BlankCorrected', 'max', true);
def UncorrectedValue = SGS.getNumericResults('labvantage', SDIDataItem, ${paramlistid}, ${paramlistversionid}.toString(), ${variantid}, ${dataset}.toString(), ${#;#;#;paramid}, 'Standard', 'max', true);
def Multiplier = SGS.getNumericResults('labvantage', SDIDataItem, ${paramlistid}, ${paramlistversionid}.toString(), ${variantid}, ${dataset}.toString(), 'Multiplier', 'Final Value', 'max', true)[0];
def Dilution = SGS.getNumericResults('labvantage', SDIDataItem, ${paramlistid}, ${paramlistversionid}.toString(), ${variantid}, ${dataset}.toString(), 'DF', 'Final Value', 'max', true)[0];
if (BlankCorrectedValue.length == 0)
{
    CalcResult = ((UncorrectedValue[0] * Multiplier * Dilution));
}
else
{
    CalcResult = ((BlankCorrectedValue[0] * Multiplier * Dilution));
}
return CalcResult;

$G{def CalcResult = 0;
if (${primary:qcsampletype} in ['Unknown','Dup'])
{
    CalcResult = ${10028-15-6;Standard}*${Multiplier;Final Value}*${DF;Final Value};
}
return CalcResult;}