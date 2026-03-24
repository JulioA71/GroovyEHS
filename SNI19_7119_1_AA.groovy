/*
    Description:    Calculate the Multiplier:Final Value for SNI19_7119_1_AA (Ammonia)
    Created by:     Marino Orsi
    Created:        2024-02-16
    Modification log (add below)
    Date            Name                            Description
    yyyy-MM-dd      xxxxx                           xxxxx
    Parameter       Multiplier (Final Value)
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
    Description:    Calculate the #:Final Value for SNI19_7119_1_AA
    Created by:     Marino Orsi
    Created:        2024-02-16
    Modification log (add below)
    Date            Name                            Description
    yyyy-MM-dd      xxxxx                           xxxxx
    Parameter       7664-41-7 (Final Value)
*/

// Variable declaration
def SampleType = ${primary:qcsampletype};

// Generic variable to hold the calculated data
def CalcResult = 0;

// Exit if it's not a Unknown sample
if (!(SampleType in ['Unknown','Dup']))
{
	return CalcResult;
}

def SDIDataItem = SGS.getSDIDataitem('labvantage', ${primary:s_sampleid}, ${paramlistid}, ${paramlistversionid}.toString(), ${variantid}, ${dataset}.toString(), 'Multiplier', 'Final Value', 'max');

def BlankCorrectedValue = SGS.getNumericResults('labvantage', SDIDataItem, ${paramlistid}, ${paramlistversionid}.toString(), ${variantid}, ${dataset}.toString(), ${#;#;#;paramid}, 'BlankCorrected', 'max', true);
def UncorrectedValue = SGS.getNumericResults('labvantage', SDIDataItem, ${paramlistid}, ${paramlistversionid}.toString(), ${variantid}, ${dataset}.toString(), ${#;#;#;paramid}, 'Standard', 'max', true);
def Multiplier = SGS.getNumericResults('labvantage', SDIDataItem, ${paramlistid}, ${paramlistversionid}.toString(), ${variantid}, ${dataset}.toString(), 'Multiplier', 'Final Value', 'max', true)[0];
def Dilution = SGS.getNumericResults('labvantage', SDIDataItem, ${paramlistid}, ${paramlistversionid}.toString(), ${variantid}, ${dataset}.toString(), 'DF', 'Final Value', 'max', true)[0];

if (BlankCorrectedValue.length == 0)
{
    CalcResult = UncorrectedValue[0] * Multiplier * Dilution * (17 / 24.47);
}
else
{
    CalcResult = BlankCorrectedValue[0] * Multiplier * Dilution * (17 / 24.47);
}

return CalcResult;


/*
    Parameter       7664-41-7 (RF)
*/
if([7664-41-7;Standard]=0,0,[7664-41-7;Standard;max;Signal]/[7664-41-7;Standard])



/*
    Parameter       7664-41-7 (Adjusted RL)
*/
[DF;Final Value]*[7664-41-7;Final Value;max;ParamLimit_1:RL]*[Multiplier;Final Value]


