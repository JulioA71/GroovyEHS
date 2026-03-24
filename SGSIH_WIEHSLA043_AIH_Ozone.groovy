/*
    Description:    Calculate the 7647-01-0:Final Value for SGSIH_WIEHSLA043_AIH
    Created by:     JA
    Created:        2023-12-29
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


======================
/*
    Description:    Calculate the 7647-01-0:Final Value for SGSIH_WIEHSLA043_AIH
    Created by:     JA
    Created:        2023-12-21
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
def AirVolume = SGS.getNumericResults('labvantage', SDIDataItem, ${paramlistid}, ${paramlistversionid}.toString(), ${variantid}, ${dataset}.toString(), 'AirVolume', 'Final Value', 'max', true)[0];
if (BlankCorrectedValue.length == 0)
{
    CalcResult = ((UncorrectedValue[0] * Multiplier * Dilution) / AirVolume) * 1000;
}
else
{
    CalcResult = ((BlankCorrectedValue[0] * Multiplier * Dilution) / AirVolume) * 1000;
}
return CalcResult;




([10028-15-6;Standard]-[AQC:MB;All|SGSIH_WIEHSLA043_AIH;*;#;max|10028-15-6;Standard;max])/[AirVolume;Final Value]*1000


======================
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

=================================
// define variable with the qcsampletype string
def SampleType = ${primary:qcsampletype};
// define variable with SDIDataItem object
def SDIDataItem= SGS.getSDIDataitem('labvantage', ${primary:s_sampleid}, ${paramlistid}, ${paramlistversionid}.toString(), ${variantid}, ${dataset}.toString(), 'Multiplier', 'Final Value', 'max');
// define variable with parameter id string
def ParamID = '10028-15-6';
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



===========================================
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

==================================================================================

([10028-15-6;Standard]-[AQC:MB;All|SGSIH_WIEHSLA043_AIH;*;#;max|10028-15-6;Standard;max])/[AirVolume;Final Value]*1000

def CalcResult = 0;
if (${primary:qcsampletype} in ['Unknown','Dup'])
{
    CalcResult = ${10028-15-6;Standard}*${Multiplier;Final Value}*${DF;Final Value};
}
return CalcResult



if (${primary:qcsampletype}=='Unknown')
{
return (((${DF;Final Value}*${7439-97-6;Standard}*${Multiplier;Final Value}) * ${SampleVolume;Final Value} / ${AliquotVolume;Final Value}) - SGS.average(SGS.toArray(${AQC:Blank;All|NIOSH6009_A;*;#;max|7439-97-6;Standard;*})))/${AirVolume;Final Value}
}
else
{
 return "0"
}

