/*
  BOD Reported
*/
def sdidataitem=SGS.getSDIDataitem('labvantage',${primary:s_sampleid},'DQMPL_Rep','*',${variantid}, ""+${dataset}, 'BOD','*','max');
def result=0;
def MaxRepID=0;
def MaxSamVol=0;
def DestinationPDO5 = 'DO5';
def DestinationPDep = 'DODepletion';
def DestinationPVol = 'SampleVolume';
def DestinationPType = 'Final Value';
def maxRep=SGS.getMaxReplicates('labvantage', ${primary:s_sampleid}, ${paramlistid}, ${paramlistversionid}.toString(), ${variantid}, ${dataset}.toString(), DestinationPDO5 , DestinationPType);
def bResult=false;
def arrList= new ArrayList(); 
for(int i in 1..maxRep) {
  def DO5=SGS.getNumericResults('labvantage',sdidataitem, sdidataitem.paramlistid,sdidataitem.paramlistversionid,sdidataitem.variantid, ""+sdidataitem.dataset, DestinationPDO5 , DestinationPType, Integer.toString(i), true)
  def Deple=SGS.getNumericResults('labvantage',sdidataitem, sdidataitem.paramlistid,sdidataitem.paramlistversionid,sdidataitem.variantid, ""+sdidataitem.dataset, DestinationPDep , DestinationPType, Integer.toString(i), true)
  def SamVol=SGS.getNumericResults('labvantage',sdidataitem, sdidataitem.paramlistid,sdidataitem.paramlistversionid,sdidataitem.variantid, ""+sdidataitem.dataset, DestinationPVol , DestinationPType, Integer.toString(i), true)
  def strArr=""
	if (DO5.length>0 && Deple.length>0)
    if (DO5[0]>1 && Deple[0]>2)
	  {
      strArr=Integer.toString(i)+"@"+SamVol[0];
      arrList.add(strArr);
	  }
    if (SamVol[0]>MaxSamVol)
    {
      MaxSamVol=SamVol[0]
      MaxRepID=i
    }
}
def aRepId=0;
def aSamVol=0;
def NewSamVol=0;
for(x in arrList){
  def nLen=x.length()-1
  if (x.indexOf('@')>-1)
  {    
    aSamVol=(x.substring(x.indexOf('@')+1));
    float convertedVolume=Float.parseFloat(aSamVol)
    if (convertedVolume>NewSamVol)
    {
      NewSamVol=convertedVolume
      aRepId=(x.substring(0,x.indexOf('@')));
      result=convertedVolume;
      bResult=true;
    }  
  } 
}
if (!bResult)
	{
	result=MaxSamVol;
	}
return result


=====================================
/*
  BOD Reported
*/
def sdidataitem=SGS.getSDIDataitem('labvantage',${primary:s_sampleid},'DQMPL_Rep','*',${variantid}, ""+${dataset}, 'BOD','*','max');
def result=0;
def MaxRepID=0;
def MaxSamVol=777;
def DestinationPDO5 = 'DO5';
def DestinationPDep = 'DODepletion';
def DestinationPVol = 'SampleVolume';
def DestinationPType = 'Final Value';
def maxRep=SGS.getMaxReplicates('labvantage', ${primary:s_sampleid}, ${paramlistid}, ${paramlistversionid}.toString(), ${variantid}, ${dataset}.toString(), DestinationPDO5 , DestinationPType);
def bResult=false;
def arrList= new ArrayList(); 
for(int i in 1..maxRep) {
  def DO5=SGS.getNumericResults('labvantage',sdidataitem, sdidataitem.paramlistid,sdidataitem.paramlistversionid,sdidataitem.variantid, ""+sdidataitem.dataset, DestinationPDO5 , DestinationPType, Integer.toString(i), true)
  def Deple=SGS.getNumericResults('labvantage',sdidataitem, sdidataitem.paramlistid,sdidataitem.paramlistversionid,sdidataitem.variantid, ""+sdidataitem.dataset, DestinationPDep , DestinationPType, Integer.toString(i), true)
  def SamVol=SGS.getNumericResults('labvantage',sdidataitem, sdidataitem.paramlistid,sdidataitem.paramlistversionid,sdidataitem.variantid, ""+sdidataitem.dataset, DestinationPVol , DestinationPType, Integer.toString(i), true)
  def strArr=""
	if (DO5.length>0 && Deple.length>0)
    if (DO5[0]>1 && Deple[0]>2)
	  {
      strArr=Integer.toString(i)+"@"+SamVol;
      arrList.add(strArr);
	  }
}
def aRepId=0;
def aSamVol=0;
def NewSamVol=0;
for(x in arrList){
  def nLen=x.length()-1
  if (x.indexOf('@')>-1)
  {  
    aSamVol=(x.substring(x.indexOf('@')+2,nLen));
    float convertedVolume=Float.parseFloat(aSamVol)
    if (convertedVolume>NewSamVol)
    {
      NewSamVol=convertedVolume
      aRepId=(x.substring(0,x.indexOf('@')));
      result=convertedVolume;
      bResult=true;
    }  
  } 
}
if (!bResult)
	{
	result=MaxSamVol;
	}
return result


