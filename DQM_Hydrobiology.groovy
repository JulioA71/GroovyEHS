/*
  TM: DQM_APHA10200_F_W
  PL: DQM_APHA10200_F_W 
  Parameter: Phylum
  Type: Final Value (Text Calc)
  Author: DQM team
  Purpose: Calculation for APHA10200_F_W (Fitoplancton / Perifiton)
  Date: 2024-04-09
*/
// Declare variable general-purpose
def CalcResult='';
def arrListHB=new ArrayList()
def bResult=false;

// Get the RefTypeId from paramlistitem.TAXA for the field entryreftypeid and TAXA
def MDERefTypeId = ${DQM_APHA10200_F_W;*;*;max|Taxa;Final Value;#;entryreftypeid};
def MDETaxa = ${DQM_APHA10200_F_W;*;*;max|Taxa;Final Value;#};

// Obtain the refvaluedesc from refvalue depending on TAXA
String HBDescription=SGS.getHydrobiology('labvantage',MDERefTypeId, MDETaxa);

// Get the value from split 0
if (HBDescription.indexOf(',')>-1)
  {
  arrListHB = HBDescription.split(',');
  CalcResult = arrListHB[0];
  bResult=true;
  }
return (!bResult?'':CalcResult)



/*
  TM: DQM_APHA10200_F_W
  PL: DQM_APHA10200_F_W 
  Parameter: Taxa
  Type: Standard (Text Calc)
  Author: DQM team
  Purpose: Calculation for APHA10200_F_W (Fitoplancton / Perifiton)
  Date: 2024-04-11
*/
// Declare variable general-purpose
def CalcResult='';
def DestinationParam = 'Taxa';
def DestinationPType = 'Final Value';
def bResult=false;

// Variable declaration - modify the parameters below with the source (where the data comes from) and destination (where the data goes to)
String ParamID = ${#;#;#;paramid}.toString();	    // define the parameter for the calculation
String ParamType = ${#;#;#;paramtype}.toString(); 	// define the parameter type
def SampleType = ${primary:qcsampletype};		    // define the sample type

// Variable to hold data related to the SDI
def SDIDataItem = SGS.getSDIDataitem('labvantage', ${primary:s_sampleid}, ${paramlistid}, ${paramlistversionid}.toString(), ${variantid}, ${dataset}.toString(), ParamID, ParamType, 'max');

// Get the replicates number
def maxRep=SGS.getMaxReplicates('labvantage', ${primary:s_sampleid}, ${paramlistid}, ${paramlistversionid}.toString(), ${variantid}, ${dataset}.toString(), DestinationParam , DestinationPType);
def arrList= new ArrayList(); 
for(int i in 1..maxRep) {
  def MDETaxa=SGS.getTextResults('labvantage',SDIDataItem, SDIDataItem.paramlistid, SDIDataItem.paramlistversionid, SDIDataItem.variantid, ""+SDIDataItem.dataset, DestinationParam , DestinationPType, Integer.toString(i), true)
  def strArrTaxa=''
	if (MDETaxa.length > 0) {
    strArrTaxa=MDETaxa[0]+'|'+Integer.toString(i);
    arrList.add(strArrTaxa);
  }
}
// Array will be sorted alphabetically (by Taxa)
def newArrList = arrList.sort(); 
def OldTaxa='<<<<<>>>>>';
def arrTaxa='';
def arrRep='';
def RepNum=1;
def RepPos='';
for(x in newArrList){
  if (x.indexOf('|')>-1)
  { 
    arrTaxa=(x.substring(0,x.indexOf('|')));   
    arrRep=(x.substring(x.indexOf('|')+1));
    if (arrTaxa == OldTaxa) {
      RepNum=RepNum+1;
      RepPos=RepPos+arrRep+' ';
	    }
    else {
      OldTaxa=arrTaxa;
      }    
  } 
}
bResult=true;
if (RepNum >= 2) 
  CalcResult='Fail '+RepPos
else
  CalcResult='Pass'
return (!bResult?'':CalcResult)