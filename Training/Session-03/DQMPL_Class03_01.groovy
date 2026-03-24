/*
    PL=DQMPL_Class03_01
    Parameter=DQMSum1 (Final Value)
    Data Type=Numeric Calc
    Remarks=Sum GetParamsByParamType Exclude
*/
// Establish the link between sample and ParamaterList
def sdidataitem=SGS.getSDIDataitem('labvantage',${primary:s_sampleid},'DQMPL_Class03_01','*',${variantid}, ""+${dataset}, 'DQMSum1','*','max');

// Obtain the list of parameters excluded and Paramlist array is created
def paramList1=SGS.getParamsByParamType(sdidataitem,'Final Value','DF;Multiplier;DQMSum1');

// Variable declaration
def paramList=paramList1
def result=0;
def bResult=false;
//sapphire.util.Logger.logDebug("TEST_CALCULATION","Nb of params : "+paramList.size());

// Read the array that contains the list of parameters excluded
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


//sapphire.util.Logger.logDebug("TEST_CALCULATION","Results : "+result);

//** TestCase Properties **//
logDirectoryName = C:\LabVantage\LogDebug
testCaseLogFileName = TestLog

//** LOG FILE DEFINITION - Place somewhere at the top of the script **//
def logDirectoryName = context.expand( '${#TestCase#logDirectoryName}' );
def testCaseLogFileName = context.expand( '${#TestCase#testCaseLogFileName}' );
def filext = ".log";
def fn = "$logDirectoryName$testCaseLogFileName$filext";
def FileName = new PrintWriter( new FileWriter(fn));

// If bResults is false then 'empty' otherwise print the result
return (!bResult?'':result)


https://backstage.forgerock.com/knowledge/kb/article/a40713338

https://community.smartbear.com/discussions/soapui_os/custom-logging-in-groovy-script/25417



/*
    PL=DQMPL_Class03_01
    Parameter=DQMSum1 (Final Value)
    Data Type=Numeric Calc
    Remarks=Sum GetParamsByParamType Exclude
*/
// Establish the link between sample and ParamaterList
def sdidataitem=SGS.getSDIDataitem('labvantage',${primary:s_sampleid},'DQMPL_Class03_01','*',${variantid}, ""+${dataset}, 'DQMSum1','*','max');

// Obtain the list of parameters excluded and Paramlist array is created
def paramList1=SGS.getParamsByParamType(sdidataitem,'Final Value','DF;Multiplier;DQMSum1');

// Variable declaration
def paramList=paramList1
def result=0;
def bResult=false;
//sapphire.util.Logger.logDebug("TEST_CALCULATION","Nb of params : "+paramList.size());

// Read the array that contains the list of parameters excluded
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
//sapphire.util.Logger.logDebug("TEST_CALCULATION","Results : "+result);

import java.io.*;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.util.logging.FileHandler;
import java.util.logging.SimpleFormatter;

Logger globalLogger = Logger.getLogger("TestLog");
globalLogger.debug ("I am a test info log");

boolean append = true;
FileHandler fh = new FileHandler("C:\LabVantage\LogDebug\TestLog.log", append);
//fh.setFormatter(new XMLFormatter());
fh.setFormatter(new SimpleFormatter());
LOGGER.addHandler(fh);

//java.util.logging.Logger.getLogger("").severe(msg)

// If bResults is false then 'empty' otherwise print the result
return (!bResult?'':result)



/*
    PL=DQMPL_Class03_01
    Parameter=DQMSum1 (Final Value)
    Data Type=Numeric Calc
    Remarks=Sum GetParamsByParamType Exclude
*/
// Establish the link between sample and ParamaterList
def sdidataitem=SGS.getSDIDataitem('labvantage',${primary:s_sampleid},'DQMPL_Class03_01','*',${variantid}, ""+${dataset}, 'DQMSum1','*','max');

// Obtain the list of parameters excluded and Paramlist array is created
def paramList1=SGS.getParamsByParamType(sdidataitem,'Final Value','DF;Multiplier;DQMSum1');

// Variable declaration
def paramList=paramList1
def result=0;
def bResult=false;
sapphire.util.Logger.logDebug("TEST_CALCULATION","Nb of params : "+paramList.size());

// Read the array that contains the list of parameters excluded
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
sapphire.util.Logger.logDebug("TEST_CALCULATION","Results : "+result);
// If bResults is false then 'empty' otherwise print the result
return (!bResult?'':result)