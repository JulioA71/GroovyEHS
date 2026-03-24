/*
  PL: APHA3125_B_S 
  Parameter: MoistureFactor
  Type: Final Value (Numeric Calc)
  Author: DQM team
  Purpose: MoistureFactor from Dry_Weight_Prep when DF > 0
  Date: 2025-01-30
  Date: 2026-03-24
*/
// Variable declaration - modify the parameters below with the source (where the data comes from) and destination (where the data goes to)
String ParamID = ${#;#;#;paramid}.toString();	    // define the parameter for the calculation
String ParamType = ${#;#;#;paramtype}.toString(); 	// define the parameter type
def SampleType = ${primary:qcsampletype};		    // define the sample type

// Generic variable to hold the calculated data
def CalcResult = 0;
def bstatus = false;

// Variable to hold data related to the SDI
def SDIDataItem = SGS.getSDIDataitem('labvantage', ${primary:s_sampleid}, ${paramlistid}, ${paramlistversionid}.toString(), ${variantid}, ${dataset}.toString(), ParamID, ParamType, 'max');

// Execute code only if there's value on DF parameter
if (SGS.checkResults('labvantage', SDIDataItem, ${paramlistid}, ${paramlistversionid}.toString(), ${variantid}, 'max', 'DF', 'Final Value', 'max', true))
{
    if (SampleType == "Unknown") {
        // Get Moisture Factor
        def MoistureFactor = SGS.getNumericResults('labvantage', SDIDataItem, 'Dry_Weight_Prep', '*', '*', 'max', 'MoistureFactor', 'Final Value', 'max', false);        
        if (MoistureFactor.length > 0) {
            bstatus = true;
            CalcResult = MoistureFactor[0];
        }
    }
    else if (SampleType == "Dup") {
        def LinkedUnkID = SGS.getUnknownSampleLink(SDIDataItem);
        def MoistureFactor = SGS.sampleNumericResults('labvantage', SDIDataItem, LinkedUnkID[0], 'Dry_Weight_Prep', '*', '*', 'max', 'MoistureFactor', 'Final Value', 'max');
        bstatus = true;
        CalcResult = MoistureFactor[0];
    }
    else {
        bstatus = true;
        CalcResult = 0;
    }
}
return (!bstatus?'':CalcResult)



/*
    Description:    Calculate the Multiplier:Final Value with MoistureFactor
    Created by:     Marino Orsi
    Created:        2024-05-17
    PL:             APHA3125_B_S 
    Parameter:      Multiplier
    Modification log (add below)
    Date            Name                Description
    2024-05-27      Marino Orsi         Get MoistureFactor from the same PL    
    2025-01-30      DQM team            If SampleType in ('UNK','DUP') Multiplier=Multiplier * MF otherwise Multiplier=1
*/
// Variable declaration - modify the parameters below with the source (where the data comes from) and destination (where the data goes to)
String ParamID = ${#;#;#;paramid}.toString();	    // define the parameter for the calculation
String ParamType = ${#;#;#;paramtype}.toString(); 	// define the parameter type
def SampleType = ${primary:qcsampletype};		    // define the sample type

// Generic variable to hold the calculated data
def CalcResult = 0;
def bstatus = false;

// Get SDI object
def SDIDataItem = SGS.getSDIDataitem('labvantage', ${primary:s_sampleid}, ${paramlistid}, ${paramlistversionid}.toString(), ${variantid}, ${dataset}.toString(), 'Multiplier', 'Final Value', 'max');

// Get Prep Multiplier
def Multiplier = SGS.getNumericResults('labvantage', SDIDataItem, 'APHA3125_B_Prep_S', '*', '*', 'max', 'Multiplier', 'Final Value', 'max', true);
// Get Moisture Factor
def MoistureFactor = SGS.getNumericResults('labvantage', SDIDataItem, '#', '#', '#', 'max', 'MoistureFactor', 'Final Value', 'max', true);

if (SampleType == "Unknown" || SampleType == "Dup") {
    // Calculate the final multiplier value
    bstatus = true;
    CalcResult = MoistureFactor.length == 0 ? Multiplier[0] : Multiplier[0] * MoistureFactor[0];    
}
else {
    bstatus = true;
    CalcResult = 1;
}
return (!bstatus?'':CalcResult)






// ========================================================================================= //
/*
  PL: APHA3125_B_S 
  Parameter: MoistureFactor
  Type: Final Value (Numeric Calc)
  Author: DQM team
  Purpose: MoistureFactor from Dry_Weight_Prep when DF > 0
  Date: 2025-01-30
  BACKUP
*/
// Declare variable general-purpose
def CalcResult = 0;
def bstatus = false;

if (${DF;Final Value} > 0)
    {
    bstatus=true;
    CalcResult=${Dry_Weight_Prep;*;*;max|MoistureFactor;Final Value;max}
    }
return (!bstatus?'':CalcResult)




/*
    Description:    Calculate the Multiplier:Final Value with MoistureFactor
    Created by:     Marino Orsi
    Created:        2024-05-17
    Modification log (add below)
    Date            Name            Description
    2024-05-27      Marino Orsi     Get MoistureFactor from the same PL
    2025-01-30      DQM team        If SampleType in ('UNK','DUP') Multiplier=Multiplier * MF otherwise Multiplier=1
*/

// Get SDI object
def SDIDataItem = SGS.getSDIDataitem('labvantage', ${primary:s_sampleid}, ${paramlistid}, ${paramlistversionid}.toString(), ${variantid}, ${dataset}.toString(), 'Multiplier', 'Final Value', 'max');

// Get Prep Multiplier
def Multiplier = SGS.getNumericResults('labvantage', SDIDataItem, 'APHA3125_B_Prep_S', '*', '*', 'max', 'Multiplier', 'Final Value', 'max', true);
// Get Moisture Factor
def MoistureFactor = SGS.getNumericResults('labvantage', SDIDataItem, '#', '#', '#', 'max', 'MoistureFactor', 'Final Value', 'max', true);

// Calculate the final multiplier value
def CalcResult = MoistureFactor.length == 0 ? Multiplier[0] : Multiplier[0] * MoistureFactor[0];
// Return calculated value
return CalcResult;




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



/*
    Description:    Calculate the Multiplier:Final Value with MoistureFactor
    Created by:     Marino Orsi
    Created:        2024-05-17
    Modification log (add below)
    Date            Name                            Description
    2024-05-27      Marino Orsi                     Get MoistureFactor from the same PL
    yyyy-MM-dd      xxxxx                           xxxxx
*/

// Get SDI object
def SDIDataItem = SGS.getSDIDataitem('labvantage', ${primary:s_sampleid}, ${paramlistid}, ${paramlistversionid}.toString(), ${variantid}, ${dataset}.toString(), 'Multiplier', 'Final Value', 'max');

// Get Prep Multiplier
def Multiplier = SGS.getNumericResults('labvantage', SDIDataItem, 'APHA3125_B_Prep_S', '*', '*', 'max', 'Multiplier', 'Final Value', 'max', true);
// Get Moisture Factor
def MoistureFactor = SGS.getNumericResults('labvantage', SDIDataItem, '#', '#', '#', 'max', 'MoistureFactor', 'Final Value', 'max', true);

// Calculate the final multiplier value
def CalcResult = MoistureFactor.length == 0 ? Multiplier[0] : Multiplier[0] * MoistureFactor[0];
// Return calculated value
return CalcResult;
