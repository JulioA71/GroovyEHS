/*
  TM: APHA_TotalNitrogen_Calc_W
  PL: APHA_TotalNitrogen_Calc_W  
  Parameter: TotalN (Total Nitrogen)
  Type: Final Value (Numeric Calc)
  Author: DQM team
  Purpose: To calculate Total Nitrogen
  Date: 2024-05-24
*/
// Variable declaration and Array for Total Nitrogen - List of PL + Parameter
def SourceTNPL = '';
def SourceTNParamID = '';
def tempArrayTN = '';
def arrListTN= new ArrayList(); 
arrListTN.add("APHA4110_B_W;NO2-N");
arrListTN.add("APHA4110_B_W;NO3-N");
arrListTN.add("APHA4500_Norg_B_TKN_W;TKN");

// Variable declaration - modify the parameters below with the source (where the data comes from) and destination (where the data goes to)
String ParamID = ${#;#;#;paramid}.toString();	    // define the parameter for the calculation
String ParamType = ${#;#;#;paramtype}.toString(); 	// define the parameter type
def SampleType = ${primary:qcsampletype};		    // define the sample type

// Generic variable to hold the calculated data
def bResult = false;
def DataEntryValue = 0;
def CalcResult = 0;

// Variable to hold data related to the SDI
def SDIDataItem = SGS.getSDIDataitem('labvantage', ${primary:s_sampleid}, ${paramlistid}, ${paramlistversionid}.toString(), ${variantid}, ${dataset}.toString(), ParamID, ParamType, 'max');

// reading array for TN=Total Nitrogen
for(i=0; i < arrListTN.size(); i++) {
    tempArrayTN=arrListTN[i]
    if (tempArrayTN.indexOf(';')>-1) {
        SourcePL=(tempArrayTN.substring(0,tempArrayTN.indexOf(';')));
        SourceParamID=(tempArrayTN.substring(tempArrayTN.indexOf(';')+1));

        DataEntryValue = SGS.getNumericResults('labvantage', SDIDataItem, SourcePL, '*', '*', 'max', SourceParamID, 'Final Value', 'max', false);
        if (DataEntryValue.length > 0) {
            CalcResult = CalcResult + DataEntryValue[0]
            bResult=true
        }
    }
}
return (!bResult?'':CalcResult)