/*
  PL: SGSIH_WIEHSLA292_Phyto_W
  Parameter: Taxa
  Type: Standard (Text Calc)
  Author: DQM team
  Purpose: Calculation for SGSIH_WIEHSLA292_Phyto_W (Phytoplankton / Zooplankton)
  Date: 2025-01-22
*/
def dummy = ${DateAnalysis;Final Value};

// Declare variable general-purpose
def CalcResult='';
def DestinationParam = 'Taxa';
def DestinationPType = 'Final Value';p

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



/*
	Description:	Calculate the Taxa:Standard for SGSIH_WIEHSLA292_Phyto_W/SGSIH_WIEHSLA292_Zoo_W
	Created by:		Marino Orsi
	Created:		2026-03-10
	Modification log (add below)
	Date			Name		Description
	yyyy-MM-dd		xxxxx		xxxxx
*/

// Get the results for all replicates from Data Entry
String[] taxaResult = ${Taxa;Final Value;*};

// Get the replicate ID for each replicate
int[] taxaResultRep = ${Taxa;Final Value;*;replicateid};

// Map with replicate:result
def resultMap = [ : ];

// Create the map
for (int pos in 0..taxaResult.length - 1) {
	resultMap.put(taxaResultRep[pos], taxaResult[pos]);
}

// Group by value (taxa)
def grouped = resultMap.groupBy { it.value }

// Keep only duplicated values
def duplicates = grouped.findAll { it.value.size() > 1 }

// If no duplicates were found, return Pass
if (duplicates.isEmpty()) {
	return "Pass";
}

// Convert to map: value -> list of replicate keys
def duplicateKeys = duplicates.collectEntries { k, v ->
	[k, v.collect { it.key }]
}

// Return the duplicated values
return duplicateKeys.toString();
