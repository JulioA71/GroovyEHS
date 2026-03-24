/*
    Description:    Sum DQMParam1 + DQMParam2 + DQMParam3 trhough SGS.getParamsByParamTypeInclude
    Created by:     JA
    Created:        2023-11-30
    Modification log (add below)
    Date            Name                            Description
    yyyy-MM-dd      xxxxx                           xxxxx

    TM: DQMTM_Includes
    PL: DQMPL_Includes
    Parameter: DQMSum1 (Final Value)    
*/
def sdidataitem=SGS.getSDIDataitem('labvantage',${primary:s_sampleid},'DQMPL_Includes','*',${variantid}, ""+${dataset}, 'DQMSum1','*','max');
def paramList1=SGS.getParamsByParamTypeInclude(sdidataitem,'Final Value','DQMParam1;DQMParam2;DQMParam3');
def paramList=paramList1
def result=0;
def bResult=false;
sapphire.util.Logger.logDebug("TEST_CALCULATION","Nb of params : "+paramList.size());
for(paramid in paramList){
	def Sum1=SGS.getNumericResults('labvantage',sdidataitem, sdidataitem.paramlistid,sdidataitem.paramlistversionid,sdidataitem.variantid, ""+sdidataitem.dataset, paramid, 'Final Value', 'max', false)
	if(Sum1.length>0)
	{
		bResult=true;
		result=result+Sum1[0];
	}
}
sapphire.util.Logger.logDebug("TEST_CALCULATION","Results : "+result);
return (!bResult?'':result)