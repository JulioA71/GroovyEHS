/*
    PL=DQMPL_Class04_03
    Parameter=DQMSum1 (Final Value)
    Data Type=Numeric Calc
    Remarks=Sum GetParamsByParamTypeInclude
*/
// Establish the link between sample and ParameterList #1 = sdidataitem1
def sdidataitem1=SGS.getSDIDataitem('labvantage',${primary:s_sampleid},'DQMPL_Class04_01','*',${variantid}, ""+${dataset}, 'DF','*','max');

// Obtain the list of parameters included and Paramlist array is created
def paramList1=SGS.getParamsByParamTypeInclude(sdidataitem1,'Final Value','DQMParam1;DQMParam2;DQMParam3');

// Variable declaration
def paramList=paramList1;
def result=0;
def bResult=false;
sapphire.util.Logger.logDebug("TEST_CALCULATION","Nb of params : "+paramList.size());
// Read the array #1 that contains the list of parameters included
for(paramid in paramList1){
	// Get the value of the Sample/Parameter
	def sumNum1=SGS.getNumericResults('labvantage',sdidataitem1, sdidataitem1.paramlistid,sdidataitem1.paramlistversionid,sdidataitem1.variantid, ""+sdidataitem1.dataset, paramid, 'Final Value', 'max', false)

	// Asking if the contains of the parameter is not empty (lenght) > 0
	if(sumNum1.length>0)
	{
		// Make a sum of the parameters with values >= 0
		bResult=true;
		result=result+sumNum1[0];
	}
}

// Establish the link between sample and ParameterList #2 = sdidataitem2
def sdidataitem2=SGS.getSDIDataitem('labvantage',${primary:s_sampleid},'DQMPL_Class04_02','*',${variantid}, ""+${dataset}, 'DF','*','max');
def paramList2=SGS.getParamsByParamTypeInclude(sdidataitem2,'Final Value','DQMParam1;DQMParam2;DQMParam3');
paramList=paramList2;
sapphire.util.Logger.logDebug("TEST_CALCULATION","Nb of params : "+paramList.size());
// Read the array #2 that contains the list of parameters included
for(paramid in paramList2){
	// Get the value of the Sample/Parameter
	def sumNum2=SGS.getNumericResults('labvantage',sdidataitem2, sdidataitem2.paramlistid,sdidataitem2.paramlistversionid,sdidataitem2.variantid, ""+sdidataitem2.dataset, paramid, 'Final Value', 'max', false)
	
	// Asking if the contains of the parameter is not empty (lenght) > 0
	if(sumNum2.length>0)
	{
		// Make a sum of the parameters with values >= 0
		bResult=true;
		result=result+sumNum2[0];
	}
}
// Asking if bResult is not true (False) then result = 0
if ( !bResult )
	result=0
bResult=true;
sapphire.util.Logger.logDebug("TEST_CALCULATION","Results : "+result);
return (!bResult?'':result)


/*
    PL=DQMPL_Class04_03
    Parameter=DQMTxt1 (Final Value)
    Data Type=Text Calc
    Remarks=Sum GetParamsByParamTypeInclude
*/
def result="";
if (${DQMSum1;Final Value} >= 10)
  result="Pass"
else
  result="Fail"
return result
