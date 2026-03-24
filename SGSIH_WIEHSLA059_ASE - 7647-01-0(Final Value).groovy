/*
    Description:    Calculate the 7647-01-0:Final Value for SGSIH_WIEHSLA059_ASE
    Created by:     Marino Orsi
    Created:        2023-12-20
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
    CalcResult = UncorrectedValue[0] * Multiplier * Dilution;
}
else
{
    CalcResult = BlankCorrectedValue[0] * Multiplier * Dilution;
}

return CalcResult / 1000;
