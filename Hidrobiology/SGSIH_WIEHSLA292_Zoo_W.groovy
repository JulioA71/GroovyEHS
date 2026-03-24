/*
  PL: SGSIH_WIEHSLA292_Zoo_W
  Parameter: Taxa
  Type: Standard (Text Calc)
  Author: DQM team
  Purpose: Calculation for SGSIH_WIEHSLA292_Zoo_W (Phytoplankton / Zooplankton)
  Date: 2026-02-24
*/
// Declare variable general-purpose
def CalcResult='';
def DestinationParam = 'Taxa';
def DestinationPType = 'Final Value';
def bstatus=false;

// Variable to hold data related to the SDI
def currentDS = ${dataset};
def SDIDataItem = SGS.getSDIDataitem('labvantage', ${primary:s_sampleid}, ${paramlistid}, ${paramlistversionid}.toString(), ${variantid}, currentDS.toString(), '*', '*', '*');

// Get the replicates number
def maxRep=SGS.getMaxReplicates('labvantage', ${primary:s_sampleid}, ${paramlistid}, ${paramlistversionid}.toString(), ${variantid}, currentDS.toString(), DestinationParam , DestinationPType);
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
    bstatus=true;
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
if (RepNum >= 2) 
  CalcResult='Fail '+RepPos
else
  CalcResult='Pass'
return (!bstatus?'':CalcResult)