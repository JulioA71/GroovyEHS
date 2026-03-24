/*
    PL=EPA8015_CALC_S
    Parameter=DRO_C10_C36 (Final Value)
    Data Type=Numeric Calc
    Remarks=Sum GetParamsByParamTypeInclude
*/
// Variable declaration
def result=0;
def nParam=0;
def avgNum=0;
def bResult=false;

// Establish the link between sample and ParameterList
def sdidataitem1=SGS.getSDIDataitem('labvantage',${primary:s_sampleid},'EPA8015_GCFID_S','*',${variantid}, ""+${dataset}, 'DQMAvg2','*','max');

// Obtain the list of parameters included and Paramlist array is created
def paramList1=SGS.getParamsByParamTypeInclude(sdidataitem1'Final Value','124-18-5;1120-21-4;112-40-3;629-50-5;629-59-4;629-62-9;544-76-3;629-78-7;1921-70-6;593-45-3;638-36-8;629-92-5;112-95-8;629-94-7;629-97-0;638-67-5;646-31-1;629-99-2;630-01-3;593-49-7;630-02-4;630-03-5;638-68-6;630-04-6;544-85-4;630-05-7;14167-59-0;630-07-9;630-06-8');

sapphire.util.Logger.logDebug("TEST_CALCULATION","Nb of params : "+paramList.size());
// Read the array that contains the list of parameters included
for(paramid in paramList1){
	// To get the contains of the Sample/Parameter
	def sumNum1=SGS.getNumericResults('labvantage',sdidataitem1, sdidataitem1.paramlistid,sdidataitem1.paramlistversionid,sdidataitem1.variantid, ""+sdidataitem1.dataset, paramid, 'Final Value', 'max', false)

	// Asking if the contains is not empty (lenght)>0
	if(sumNum1.length>0){
		bResult=true;
		result=result+sumNum1[0];
		// count the numbers of parameters with contains >=0
		nParam=nParam+1
	}
}

// Establish the link between sample and ParameterList
def sdidataitem2=SGS.getSDIDataitem('labvantage',${primary:s_sampleid},'EPA8015_GCMS_S','*',${variantid}, ""+${dataset}, 'DQMAvg2','*','max');

// Obtain the list of parameters included and Paramlist array is created
def paramList2=SGS.getParamsByParamTypeInclude(sdidataitem2'Final Value','124-18-5;112-40-3;90-12-0;91-20-3');

// Read the array that contains the list of parameters included
for(paramid in paramList2){
	// To get the contains of the Sample/Parameter
	def sumNum2=SGS.getNumericResults('labvantage',sdidataitem2, sdidataitem2.paramlistid,sdidataitem2.paramlistversionid,sdidataitem2.variantid, ""+sdidataitem2.dataset, paramid, 'Final Value', 'max', false)

	// Asking if the contains is not empty (lenght)>0
	if(sumNum2.length>0){
		bResult=true;
		result=result+sumNum2[0];
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





/*
	TM=VDI3877_M
	PL=VDI3877_Calculation_MD
	Parameter=StructureObservee
*/
def sdidataitem1=SGS.getSDIDataitem('labvantage',${primary:s_sampleid},'VDI3877_AnalysisX400_MD','*',${variantid}, ""+${dataset}, 'Fields','*','max');
def paramList1=SGS.getParamsByParamType(sdidataitem1,'Final Value','Fields;Sum_Isolated;Sum_Clump;Sum_Bundle;Sum_Matrix;Sum_18;Weighted_Isolated;Weighted_Clump;Weighted_Bundle;Weighted_Matrix;Weighted_18');
def paramList=paramList1
def result=0;
def bResult=false;
sapphire.util.Logger.logDebug("TEST_CALCULATION","Nb of params : "+paramList.size());
for(paramid in paramList1){
	def st1=SGS.getNumericResults('labvantage',sdidataitem1, sdidataitem1.paramlistid,sdidataitem1.paramlistversionid,sdidataitem1.variantid, ""+sdidataitem1.dataset, paramid, 'Final Value', 'max', false)
	if(st1.length>0)
	{
		bResult=true;
		result=result+st1[0];
	}
}
def sdidataitem2=SGS.getSDIDataitem('labvantage',${primary:s_sampleid},'VDI3877_AnalysisX1300_MD','*',${variantid}, ""+${dataset}, 'Fields','*','max');
def paramList2=SGS.getParamsByParamType(sdidataitem2,'Final Value','Fields;Sum_Isolated;Sum_Clump;Sum_Bundle;Sum_Matrix;Weighted_Isolated;Weighted_Clump;Weighted_Bundle;Weighted_Matrix');
paramList=paramList2
sapphire.util.Logger.logDebug("TEST_CALCULATION","Nb of params : "+paramList.size());
for(paramid in paramList2){
	def st2=SGS.getNumericResults('labvantage',sdidataitem2, sdidataitem2.paramlistid,sdidataitem2.paramlistversionid,sdidataitem2.variantid, ""+sdidataitem2.dataset, paramid, 'Final Value', 'max', false)
	if(st2.length>0)
	{
		bResult=true;
		result=result+st2[0];
	}
}
if (${Analysable;Final Value}=="No") 
	result=0
else if ( !bResult )
	result=0
bResult=true;
sapphire.util.Logger.logDebug("TEST_CALCULATION","Results : "+result);
return (!bResult?'':result)
