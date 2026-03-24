/*
    Description:    Calculate the Multiplier:Final Value for SGSIH_WIEHSLA283_AA
    Created by:     Marino Orsi
    Created:        2024-02-16
    Modification log (add below)
    Date            Name                Description
    2024-03-26      Marino Orsi         Changed PL to Sampling_FlowCorrected_AA of the same workitem
    yyyy-MM-dd      xxxxx               xxxxx
    2025-05-19      Grace Badua         Added Dummy declaration triger and replaced  with its actual parameter
    2025-09-23      Julio Alvarez       Replace:
*/

// Variable declaration - modify the parameters below with the source (where the data comes from) and destination (where the data goes to)
def SourcePL = 'Sampling_FlowCorrected_AA';
def PrepPL = 'SGSIH_WIEHSLA283_Prep_AA';
def ParamID = 'Multiplier';                         // define the parameter for the calculation
def ParamType = 'Final Value';                      // define the parameter type 
def SampleType = ${primary:qcsampletype};		    // define the sample type

// Dummy declaration for trigger
def dummy = ${DF;Final Value};

// Generic variable to hold the calculated data
def CalcResult = 0;

// Variable to hold data related to the SDI
def SDIDataItem = SGS.getSDIDataitem('labvantage', ${primary:s_sampleid}, ${paramlistid}, ${paramlistversionid}.toString(), ${variantid}, ${dataset}.toString(), ParamID, ParamType, 'max');

// Execute code only if there's value on DF parameter
if (SGS.checkResults('labvantage', SDIDataItem, ${paramlistid}, ${paramlistversionid}.toString(), ${variantid}, 'max', 'DF', 'Final Value', 'max', true))
{
    if (SampleType == "Unknown")
    {
        def PrepMultiplier = SGS.getNumericResults('labvantage', SDIDataItem, PrepPL, '*', '*', 'max', ParamID, ParamType, 'max', true);
        def SourceValue = SGS.getNumericResults('labvantage', SDIDataItem, SourcePL, '*', '*', 'max', ParamID, ParamType, 'max', true);
        CalcResult = 1 / SourceValue[0] * PrepMultiplier[0];
    }
    else if (SampleType == "Dup")
    {
        def LinkedUnkID = SGS.getUnknownSampleLink(SDIDataItem)
        def SourceValue = SGS.sampleNumericResults('labvantage', SDIDataItem, LinkedUnkID[0], '#', '#', '#', 'max', 'Multiplier', 'Final Value', 'max')
        CalcResult = SourceValue[0];
    }
    else
    {
        CalcResult = 1;
    }
    return CalcResult
}


/*
    Description:    Calculate the Multiplier:Final Value for SGSIH_WIEHSLA283_AA
    Created by:     Marino Orsi
    Created:        2024-02-16
    Modification log (add below)
    Date            Name                            Description
    2024-03-26      Marino Orsi                     Changed PL to Sampling_FlowCorrected_AA of the same workitem
    yyyy-MM-dd      xxxxx                           xxxxx
*/

// Variable declaration - modify the parameters below with the source (where the data comes from) and destination (where the data goes to)
def SourcePL = 'Sampling_FlowCorrected_AA';
def PrepPL = 'SGSIH_WIEHSLA283_Prep_AA'
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
        def PrepMultiplier = SGS.getNumericResults('labvantage', SDIDataItem, PrepPL, '*', '*', 'max', ParamID, ParamType, 'max', true);
        def SourceValue = SGS.getNumericResults('labvantage', SDIDataItem, SourcePL, '*', '*', 'max', ParamID, ParamType, 'max', true);
        CalcResult = 1 / SourceValue[0] * PrepMultiplier[0];
    }
    else if (SampleType == "Dup")
    {
        def LinkedUnkID = SGS.getUnknownSampleLink(SDIDataItem)
        def SourceValue = SGS.sampleNumericResults('labvantage', SDIDataItem, LinkedUnkID[0], '#', '#', '#', 'max', 'Multiplier', 'Final Value', 'max')
        CalcResult = SourceValue[0];
    }
    else
    {
        CalcResult = 1;
    }
    return CalcResult;
}




/*
    Description:    Calculate the #:Final Value for SGSIH_WIEHSLA283_AA
    Created by:     Marino Orsi
    Created:        2024-02-16
    Modification log (add below)
    Date            Name                            Description
    2024-03-14      Marino Orsi                     Added filter area used as a fraction
    yyyy-MM-dd      xxxxx                           xxxxx
    2025-05-19      Grace Badua          Added Dummy declaration triger and replaced ${#;#;#;paramid} with its actual parameter
*/

// Variable declaration
def SampleType = ${primary:qcsampletype};

// Dummy declaration for trigger
def dummy=${DF;Final Value};

// Generic variable to hold the calculated data
def CalcResult = 0;
def StdResult = 0;

// Exit if it's not a Unknown sample
if (!(SampleType in ['Unknown','Dup']))
{
	return CalcResult;
}

def SDIDataItem = SGS.getSDIDataitem('labvantage', ${primary:s_sampleid}, ${paramlistid}, ${paramlistversionid}.toString(), ${variantid}, ${dataset}.toString(), 'Multiplier', 'Final Value', 'max');

def FilterLength = SGS.getNumericResults('labvantage', SDIDataItem, ${paramlistid}, ${paramlistversionid}.toString(), ${variantid}, ${dataset}.toString(), 'Length', 'Final Value', 'max', true)[0];
def FilterWidth = SGS.getNumericResults('labvantage', SDIDataItem, ${paramlistid}, ${paramlistversionid}.toString(), ${variantid}, ${dataset}.toString(), 'Height', 'Final Value', 'max', true)[0];
def SampleVolume = SGS.getNumericResults('labvantage', SDIDataItem, ${paramlistid}, ${paramlistversionid}.toString(), ${variantid}, ${dataset}.toString(), 'SampleVolume', 'Final Value', 'max', true)[0];
def FilterFraction = 1 / SGS.getNumericResults('labvantage', SDIDataItem, ${paramlistid}, ${paramlistversionid}.toString(), ${variantid}, ${dataset}.toString(), 'FilterSurfaceFraction', 'Final Value', 'max', true)[0];
def Multiplier = SGS.getNumericResults('labvantage', SDIDataItem, ${paramlistid}, ${paramlistversionid}.toString(), ${variantid}, ${dataset}.toString(), 'Multiplier', 'Final Value', 'max', true)[0];
def Dilution = SGS.getNumericResults('labvantage', SDIDataItem, ${paramlistid}, ${paramlistversionid}.toString(), ${variantid}, ${dataset}.toString(), 'DF', 'Final Value', 'max', true)[0];

def FilterAreaFactor = (FilterLength * FilterWidth) / ((FilterLength * FilterWidth) * FilterFraction)

def BlankCorrectedValue = SGS.getNumericResults('labvantage', SDIDataItem, ${paramlistid}, ${paramlistversionid}.toString(), ${variantid}, ${dataset}.toString(), '7439-92-1', 'BlankCorrected', 'max', true);
def UncorrectedValue = SGS.getNumericResults('labvantage', SDIDataItem, ${paramlistid}, ${paramlistversionid}.toString(), ${variantid}, ${dataset}.toString(), '7439-92-1', 'Standard', 'max', true);

if (BlankCorrectedValue.length == 0)
{
    StdResult = UncorrectedValue[0];
}
else
{
    StdResult = BlankCorrectedValue[0];
}

CalcResult = StdResult * FilterAreaFactor * (SampleVolume / 1000) * Multiplier * Dilution;

return CalcResult;