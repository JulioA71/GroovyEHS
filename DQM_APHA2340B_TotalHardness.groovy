/*
  TM: DQM_APHA2340B
  PL: DQM_APHA2340B  
  Parameter: TotalHardness (Total Hardness)
  Type: Final Value (Numeric Calc)
  Author: DQM team
  Purpose: To calculate Total Hardness taking the Ca and Mg
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

// Varible declaration and Array for Magnesium - List of PL + Parameter
def SourceMgPL = '';
def SourceMgParamID = '';
def tempArrayMg = '';
def arrListMg= new ArrayList(); 
arrListMg.add("DQM_APHA3111B;7439-95-4");
arrListMg.add("DQM_APHA3120;7439-95-4");
arrListMg.add("DQM_APHA3125;7439-95-4");
arrListMg.add("DQM_APHA3500B_Mg;7439-95-4");

// Variable declaration - modify the parameters below with the source (where the data comes from) and destination (where the data goes to)
String ParamID = ${#;#;#;paramid}.toString();	    // define the parameter for the calculation
String ParamType = ${#;#;#;paramtype}.toString(); 	// define the parameter type
def SampleType = ${primary:qcsampletype};		    // define the sample type

// Generic variable to hold the calculated data
def bResultCa = false;
def bResultMg = false;
def bResult = false;
def DataEntryValue = 0;
def CalcResultCa = 0;
def CalcResultMg = 0;
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

  // reading array for Mg
  for(i=0; i < arrListMg.size(); i++) {
  tempArrayMg=arrListMg[i]
  if (tempArrayMg.indexOf(';')>-1) {
    SourcePL=(tempArrayMg.substring(0,tempArrayMg.indexOf(';')));
    SourceParamID=(tempArrayMg.substring(tempArrayMg.indexOf(';')+1));

    DataEntryValue = SGS.getNumericResults('labvantage', SDIDataItem, SourcePL, '*', '*', 'max', SourceParamID, 'Final Value', 'max', false);
    if (DataEntryValue.length > 0)
      {
      CalcResultMg = DataEntryValue[0]
      bResultMg=true
      break
      }
    }
  }
}
if (bResultCa && bResultMg) {
  CalcResult = (2.497 * CalcResultCa) + (4.118 * CalcResultMg)
  bResult=true
}
return (!bResult?'':CalcResult)