/*
  Description:  YeastMold:Final Value for SGSIH_WIEHSLA263_Calculation_MSW
  Created by:    Marino Orsi
  Created:    2025-01-10
  Modification log (add below)
  Date      Name              Description
  yyyy-MM-dd    xxxxx              xxxxx
*/
// Initialize variable
def CalcValue = 0;

// Get SDI object
def SDIDataItem = SGS.getSDIDataitem('labvantage', ${primary:s_sampleid}, ${paramlistid}, ${paramlistversionid}.toString(), ${variantid}, ${dataset}.toString(), '*', '*', '*');

// Get UpperLimit object
def UpperLimit = SGS.sampleLimitResults('labvantage', SDIDataItem, 'UpperLimit', ${primary:s_sampleid}, '#', '#', '#', '#', ${#;#;#;paramid}, 'Final Value', '*');

// Get the parameter value
def StdVal = SGS.getNumericResults('labvantage', SDIDataItem, '#', '#', '#', '#', ${#;#;#;paramid}, 'Standard', 'max', true);
def DFVal = SGS.getNumericResults('labvantage', SDIDataItem, '#', '#', '#', '#', 'DF', 'Final Value', 'max', true);
def SAreaVal = SGS.getNumericResults('labvantage', SDIDataItem, '#', '#', '#', '#', 'SurfaceArea', 'Final Value', 'max', true);

// If there's no value in the DF, Standard or SurfaceArea param, return empty string
if (DFVal.length <= 0 || SAreaVal.length <= 0 || StdVal.length <= 0) { return ""; }

// if the upper limit is present, check if the StdVal is above the upper limit
if (UpperLimit.length > 0 && (StdVal[0] * DFVal[0] > UpperLimit[0].value1num)) {
// Use the upper limit to calculate the result in CFU/100 mL
  CalcValue = ">" + ( UpperLimit[0].value1num * DFVal[0]);
} else {
  // Calculate the result in CFU/100cm2
  CalcValue = StdVal[0] / SAreaVal[0] * DFVal[0] * 100;
}

// Return the result
return CalcValue;
