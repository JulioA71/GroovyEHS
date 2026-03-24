/*
  TM: DQM_APHA3500B_Mg
  PL: DQM_APHA3500B_Mg  
  Parameter: 7439-95-4 (Magnesium)
  Type: Final Value (Numeric Calc)
  Author: DQM team
  Purpose: To calculate Magnesium taking the Ca and Total Hardness
  Date: 2024-03-26
*/
// Variable declaration and Array for Ca - List of PL + Parameter
def SourceCaPL = '';
def SourceCaParamID = '';
def tempArrayCa = '';
def arrListCa= new ArrayList(); 
arrListCa.add("DQM_APHA3111B;7440-70-2");
arrListCa.add("DQM_APHA3111E;7440-70-2");
arrListCa.add("DQM_APHA3111D;7440-70-2");
arrListCa.add("DQM_APHA3120;7440-70-2");
arrListCa.add("DQM_APHA3500Ca;7440-70-2");

// Varible declaration and Array for Total Hardness - List of PL + Parameter
def SourceTHPL = '';
def SourceTHParamID = '';
def tempArrayTH = '';
def arrListTH= new ArrayList(); 
arrListTH.add("DQM_APHA2340B;TotalHardness");
arrListTH.add("DQM_APHA2340C;TotalHardness");

// Variable declaration - modify the parameters below with the source (where the data comes from) and destination (where the data goes to)
String ParamID = ${#;#;#;paramid}.toString();	    // define the parameter for the calculation
String ParamType = ${#;#;#;paramtype}.toString(); 	// define the parameter type
def SampleType = ${primary:qcsampletype};		    // define the sample type

// Generic variable to hold the calculated data
def bResultCa = false;
def bResultTH = false;
def bResult = false;
def DataEntryValue = 0;
def CalcResultCa = 0;
def CalcResultTH = 0;
def CalcResult = 0;

// Variable to hold data related to the SDI
def SDIDataItem = SGS.getSDIDataitem('labvantage', ${primary:s_sampleid}, ${paramlistid}, ${paramlistversionid}.toString(), ${variantid}, ${dataset}.toString(), ParamID, ParamType, 'max');

// Execute code only if there's value on DF parameter
if (SGS.checkResults('labvantage', SDIDataItem, ${paramlistid}, ${paramlistversionid}.toString(), ${variantid}, 'max', 'DF', 'Final Value', 'max', true))
{  
  // reading array for CA
  for(i=0; i < arrListCa.size(); i++) {
  tempArrayCa=arrListCa[i]
  if (tempArrayCa.indexOf(';')>-1) {
    SourcePL=(tempArrayCa.substring(0,tempArrayCa.indexOf(';')));
    SourceParamID=(tempArrayCa.substring(tempArrayCa.indexOf(';')+1));

    DataEntryValue = SGS.getNumericResults('labvantage', SDIDataItem, SourcePL, '*', '*', 'max', SourceParamID, 'Final Value', 'max', false);
    if (DataEntryValue.length > 0)
      {
      CalcResultCa = DataEntryValue[0]
      bResultCa=true
      break
      }
    }
  }

  // reading array for TH
  for(i=0; i < arrListTH.size(); i++) {
  tempArrayTH=arrListTH[i]
  if (tempArrayTH.indexOf(';')>-1) {
    SourcePL=(tempArrayTH.substring(0,tempArrayTH.indexOf(';')));
    SourceParamID=(tempArrayTH.substring(tempArrayTH.indexOf(';')+1));

    DataEntryValue = SGS.getNumericResults('labvantage', SDIDataItem, SourcePL, '*', '*', 'max', SourceParamID, 'Final Value', 'max', false);
    if (DataEntryValue.length > 0)
      {
      CalcResultTH = DataEntryValue[0]
      bResultTH=true
      break
      }
    }
  }
}
if (bResultCa && bResultTH) {
  CalcResult = (CalcResultTH - CalcResultCa) * 0.243
  bResult=true
}
return (!bResult?'':CalcResult)