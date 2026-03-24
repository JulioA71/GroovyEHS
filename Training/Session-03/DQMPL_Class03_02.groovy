/*
    PL=DQMPL_Class03_02
    Parameter=DQMSum1 (Final Value)
    Data Type=Numeric Calc
    Remarks=Sum GetParamsByParamTypeInclude
*/
// Establish the link between sample and ParameterList 
def sdidataitem=SGS.getSDIDataitem('labvantage',${primary:s_sampleid},'DQMPL_Class03_02','*',${variantid}, ""+${dataset}, 'DQMSum1','*','max');

// Obtain the list of parameters included and Paramlist array is created
def paramList1=SGS.getParamsByParamTypeInclude(sdidataitem,'Final Value','DQMParam1;DQMParam2;DQMParam3');

// Variable declaration
def paramList=paramList1
def result=0;
def bResult=false;
sapphire.util.Logger.logDebug("TEST_CALCULATION","Nb of params : "+paramList.size());

// Read the array that contains the list of parameters included
for(paramid in paramList){
	// To get the contains of the Sample/Parameter
	def SumNum=SGS.getNumericResults('labvantage',sdidataitem, sdidataitem.paramlistid,sdidataitem.paramlistversionid,sdidataitem.variantid, ""+sdidataitem.dataset, paramid, 'Final Value', 'max', false)

	// Asking if the contains is not empty (lenght)>0
	if(SumNum.length>0)
	{
		bResult=true;
		result=result+SumNum[0];
	}
}
// result=0 by default. If exist one parameter (at minimum) then result is > 0
return result
