// define variable with the qcsampletype string
def SampleType = ${primary:qcsampletype};
// define variable with SDIDataItem object
def SDIDataItem= SGS.getSDIDataitem('labvantage', ${primary:s_sampleid}, ${paramlistid}, ${paramlistversionid}.toString(), ${variantid}, ${dataset}.toString(), 'Multiplier', 'Final Value', 'max');
// define variable with parameter id string
def ParamID = '71-43-2';
// define generic variable to hold the calculated value
def CalcResult = 0;
// Try to find the parameter with BlankCorrected type
def BlankCorrectedValue = SGS.getNumericResults('labvantage', SDIDataItem, ${paramlistid}, ${paramlistversionid}.toString(), ${variantid}, ${dataset}.toString(), ParamID, 'BlankCorrected', 'max', true);
// Try to find the parameter with Standard type
def UncorrectedValue = SGS.getNumericResults('labvantage', SDIDataItem, ${paramlistid}, ${paramlistversionid}.toString(), ${variantid}, ${dataset}.toString(), ParamID, 'Standard', 'max', true);
// Get the first value of the multiplier
def Multiplier = SGS.getNumericResults('labvantage', SDIDataItem, ${paramlistid}, ${paramlistversionid}.toString(), ${variantid}, ${dataset}.toString(), 'Multiplier', 'Final Value', 'max', true)[0];

// If the SampleType is Unknown or Dup:
if (SampleType in ['Unknown','Dup'])
{
    // If there's no BlankCorrected parameter (length == 0):
    if (BlankCorrectedValue.length == 0)
    {
        // Define the variable CalcResult as the first value (index 0) of the UncorrectedValue array
        CalcResult = UncorrectedValue[0];
    }
    // If there's a least one BlankCorrected parameter (length > 0):
    else
    {
        // Define the variable CalcResult as the first value (index 0) of the BlankCorrectedValue array
        CalcResult = BlankCorrectedValue[0];
    }
}
// If the SampleType is Blank or MB:
else if (SampleType in ['Blank','MB'])
{
    // Define the variable CalcResult as the first value (index 0) of the UncorrectedValue array
    CalcResult = UncorrectedValue[0];
}
/*  If the SampleType is anything else:
    Note: you can add another "else if (new condition)" if you want to separate to another group, such as ['LCS','LCSD']
    and the "else" for all other SampleTypes, but I'd recomend limiting to one or two "else if" and anything more than
    that to use a "switch...case" statement*/
else
{
    /* Define the variable CalcResult as 0
    Note: this entire "else" sections isn't needed, as the CalcResult already has the value 0 defined on the variable
    declaration and the value will only change if it falls into one of the "if" statements*/
    CalcResult = 0;
}
// Return the value from CalcResult multiplied by the Multiplier
return CalcResult * Multiplier;
