/*
	TM=xxx
	PL=EPA8015_PHCs_GCMS_W
	Parameter=HF_C37_C40 (Final Value)
*/

def sdidataitem=SGS.getSDIDataitem('labvantage',${primary:s_sampleid},'EPA8015_PHCs_GCMS_W','*',${variantid}, ""+${dataset}, 'HF_C37_C40','*','max');
def paramList1=SGS.getParamsByParamTypeInclude(sdidataitem,'Final Value','7194-84-5;7194-85-6;7194-86-7;4181-95-7');
def paramList=paramList1
def result=0;
def bResult=true;
sapphire.util.Logger.logDebug("TEST_CALCULATION","Nb of params : "+paramList.size());
for(paramid in paramList){
def Sum1=SGS.getNumericResults('labvantage',sdidataitem, sdidataitem.paramlistid,sdidataitem.paramlistversionid,sdidataitem.variantid, ""+sdidataitem.dataset, paramid, 'Final Value', 'max', false)
if(Sum1.length>0)
{
	result=result+Sum1[0];
}
}
sapphire.util.Logger.logDebug("TEST_CALCULATION","Results : "+result);				
return (!bResult?'':result)				

