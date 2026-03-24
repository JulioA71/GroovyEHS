/*
    PL=DQMPL_Class03_03
    Parameter=DQMSum1 (Final Value)
    Data Type=Numeric Calc
    Remarks=Sum GetParamsByParamTypeInclude
*/
// Establish the link between sample and ParameterList 
def sdidataitem=SGS.getSDIDataitem('labvantage',${primary:s_sampleid},'DQMPL_Class03_03','*',${variantid}, ""+${dataset}, 'DQMAvg1','*','max');

// Obtain the list of parameters included and Paramlist array is created
def paramList1=SGS.getParamsByParamTypeInclude(sdidataitem,'Final Value','DQMParam1;DQMParam2;DQMParam3');

// Variable declaration
def paramList=paramList1
def result=0;
def nParam=0;
def avgNum=0;
def bResult=false;
sapphire.util.Logger.logDebug("TEST_CALCULATION","Nb of params : "+paramList.size());

// Read the array that contains the list of parameters included
for(paramid in paramList){
	// To get the contains of the Sample/Parameter
	def sumNum=SGS.getNumericResults('labvantage',sdidataitem, sdidataitem.paramlistid,sdidataitem.paramlistversionid,sdidataitem.variantid, ""+sdidataitem.dataset, paramid, 'Final Value', 'max', false)

	// Asking if the contains is not empty (lenght)>0
	if(sumNum.length>0)
	{
		bResult=true;
		result=result+sumNum[0];
		// count the numbers of parameters with contains >=0
		nParam=nParam+1
	}
}
if (bResult)
    // calculating the average
    avgNum=result/nParam
else
    avgNum=0
sapphire.util.Logger.logDebug("TEST_CALCULATION","Results : "+avgNum);
// If bResults is false then 'empty' otherwise print the avgNum
return (!bResult?'':avgNum)
