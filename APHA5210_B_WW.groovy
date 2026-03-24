/*
    Description:    Calculation for APHA5210_B_WW (BOD5)
    Created by:     JA
    Created:        2023-11-14
    Modification log (add below)
    Date            Name      Description
    2023-11-18      JA        Average for LCS  
*/
def sdidataitem=SGS.getSDIDataitem('labvantage',${primary:s_sampleid},'APHA5210_B_WW','*',${variantid}, ""+${dataset}, 'BOD5','*','max');
/* Declare variable general-purpose */
def result=0;
def bResult=false;
/* Declare variables to get maximum Volume/BOD that doesn't meet conditions */
def MaxSamVol=0;
def MaxBOD=0;
/* Declare variables to get average for LCS */
def avgNumLCS=0;
def avgSumLCS=0;
/* Declare parameters to get values from replicates samples */
def DestinationPDO5 = 'DO5';
def DestinationPDep = 'DODepletion';
def DestinationPBOD = 'BOD';
def DestinationPVol = 'SampleVolume';
def DestinationPType = 'Final Value';
/* Obtain the maximum number of replicates  */
def maxRep=SGS.getMaxReplicates('labvantage', ${primary:s_sampleid}, ${paramlistid}, ${paramlistversionid}.toString(), ${variantid}, ${dataset}.toString(), DestinationPDO5 , DestinationPType);
/* Declare array to store the values that meet the conditions */
def arrList= new ArrayList();
/* Reading the replicates per sample to get the differents values such as DO5, Depletion, BOD and SampleVolume */
for(int i in 1..maxRep) {
  def DO5=SGS.getNumericResults('labvantage',sdidataitem, sdidataitem.paramlistid,sdidataitem.paramlistversionid,sdidataitem.variantid, ""+sdidataitem.dataset, DestinationPDO5 , DestinationPType, Integer.toString(i), true)
  def Deple=SGS.getNumericResults('labvantage',sdidataitem, sdidataitem.paramlistid,sdidataitem.paramlistversionid,sdidataitem.variantid, ""+sdidataitem.dataset, DestinationPDep , DestinationPType, Integer.toString(i), true)
  def BOD=SGS.getNumericResults('labvantage',sdidataitem, sdidataitem.paramlistid,sdidataitem.paramlistversionid,sdidataitem.variantid, ""+sdidataitem.dataset, DestinationPBOD , DestinationPType, Integer.toString(i), true)
  def SamVol=SGS.getNumericResults('labvantage',sdidataitem, sdidataitem.paramlistid,sdidataitem.paramlistversionid,sdidataitem.variantid, ""+sdidataitem.dataset, DestinationPVol , DestinationPType, Integer.toString(i), true)
  def strArr=""
  if (DO5.length>0 && Deple.length>0)
    /* Creating the array with BOD and SamVol that meet the conditions */
    if (DO5[0]>1 && Deple[0]>2)
    {
      strArr=BOD[0]+"@"+SamVol[0]
      arrList.add(strArr)
    }
    /* Identifying the maximun SampleVolume to get the BOD of the all replicates */
    if (SamVol[0]>MaxSamVol)
    {
      MaxSamVol=SamVol[0]
      MaxBOD=BOD[0]
    }
    /* Sum BOD to obtain Average */
    if (SamVol[0]>0)
    {
      avgNumLCS=avgNumLCS+1
      avgSumLCS=avgSumLCS+BOD[0]
    }
}
def aBOD=0;
def aSamVol=0;
def NewSamVol=0;
/* Reading array with values meet conditions */
/* Identifying what SampleVolumen has the highest value */
for(x in arrList){
  def nLen=x.length()-1
  if (x.indexOf('@')>-1)
  {    
    aSamVol=(x.substring(x.indexOf('@')+1))
    float convertedVolume=Float.parseFloat(aSamVol)
    if (convertedVolume>NewSamVol)
    {
      NewSamVol=convertedVolume
      aBOD=(x.substring(0,x.indexOf('@')))
      float convertedBOD=Float.parseFloat(aBOD)
      result=convertedBOD
      bResult=true
    }  
  } 
}
/* if replicates don't meet the conditions will get the BOD with maximum value for SampleVolume */
switch(${primary:qcsampletype})
{
case ['Unknown','Dup']:
  if (!bResult)
    result=MaxBOD
  break;
case ['LCS','MB']:
  if (avgNumLCS>0)
    result=avgSumLCS/avgNumLCS
  else
    result=88
  break;
default:
  result=0
  break;
}
return result