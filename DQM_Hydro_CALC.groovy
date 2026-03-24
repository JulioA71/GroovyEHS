/*
  TM: DQM_WIEHSLA292_W
  PL: DQM_WIEHSLA292_CALC_WS 
  Parameter: TaxaNumber
  Type: Final Value (Numeric Calc)
  Author: DQM team
  Purpose: Calculation for Number of Taxa
  Date: 2024-05-15
*/
// Declare variable general-purpose
def CalcResult = 0;
def bstatus = false;

// Variable declaration - modify the parameters below with the source (where the data comes from) and destination (where the data goes to)
String ParamListID = 'DQM_WIEHSLA292_WS';   // define the parameter list
String ParamID = 'NumberIndividuals';       // define the parameter id
String ParamType = 'Final Value';           // define the parameter type
def SampleType = ${primary:qcsampletype};   // define the sample type

// Variable to hold data related to the SDI
def SDIDataItem=SGS.getSDIDataitem('labvantage', ${primary:s_sampleid}, ParamListID, '*', ${variantid}, ${dataset}.toString(), ParamID, ParamType, 'max');

// Get the replicates number
def maxRep=SGS.getMaxReplicates('labvantage', ${primary:s_sampleid}, ParamListID, SDIDataItem.paramlistversionid, SDIDataItem.variantid, ""+SDIDataItem.dataset, ParamID , ParamType);
for(int i in 1..maxRep) {
    def MDENumber=SGS.getNumericResults('labvantage',SDIDataItem, SDIDataItem.paramlistid, SDIDataItem.paramlistversionid, SDIDataItem.variantid, ""+SDIDataItem.dataset, ParamID , ParamType, Integer.toString(i), true)
    if (MDENumber.length > 0) {
        if (MDENumber[0] > 0) {
            bstatus=true;
            CalcResult=CalcResult+1;
	    }
    }
}
return (!bstatus?'':CalcResult)


/*
  TM: DQM_WIEHSLA292_W
  PL: DQM_WIEHSLA292_CALC_WS 
  Parameter: Abundance
  Type: Final Value (Numeric Calc)
  Author: DQM team
  Purpose: Calculation for Number of Taxa
  Date: 2024-05-15
*/
// Declare variable general-purpose
def CalcResult = 0;
def bstatus = false;

// Variable declaration - modify the parameters below with the source (where the data comes from) and destination (where the data goes to)
String ParamListID = 'DQM_WIEHSLA292_WS';   // define the parameter list
String ParamID = 'NumberIndividuals';       // define the parameter id
String ParamType = 'Final Value';           // define the parameter type
def SampleType = ${primary:qcsampletype};   // define the sample type

// Variable to hold data related to the SDI
def SDIDataItem=SGS.getSDIDataitem('labvantage', ${primary:s_sampleid}, ParamListID, '*', ${variantid}, ${dataset}.toString(), ParamID, ParamType, 'max');

// Get the replicates number
def maxRep=SGS.getMaxReplicates('labvantage', ${primary:s_sampleid}, ParamListID, SDIDataItem.paramlistversionid, SDIDataItem.variantid, ""+SDIDataItem.dataset, ParamID , ParamType);
for(int i in 1..maxRep) {
    def MDENumber=SGS.getNumericResults('labvantage',SDIDataItem, SDIDataItem.paramlistid, SDIDataItem.paramlistversionid, SDIDataItem.variantid, ""+SDIDataItem.dataset, ParamID , ParamType, Integer.toString(i), true)
    if (MDENumber.length > 0) {
        if (MDENumber[0] > 0) {            
            bstatus=true;
            CalcResult=CalcResult+MDENumber[0];
	    }        
    }
}
return (!bstatus?'':CalcResult)


/*
  TM: DQM_WIEHSLA292_W
  PL: DQM_WIEHSLA292_CALC_WS 
  Parameter: TaxaLN
  Type: Final Value (Numeric Calc)
  Author: DQM team
  Purpose: Calculation Logarithm for Number of Taxa
  Date: 2024-05-15
*/
// Declare variable general-purpose
def CalcResult = 0;
def bstatus = false;
double MDETaxaNum = ${TaxaNumber;Final Value};

if (MDETaxaNum > 0) {
    CalcResult=Math.log(MDETaxaNum);
    bstatus=true;
}
return (!bstatus?'':CalcResult)






/*
    Description:    Calculation for SGSIH_EPA8015_CALC_AE_S
    Created by:     DQM team
    PL:             SGSIH_EPA8015_CALC_AE_S
    Parameter:      DRO_C10_C36 (Final Value)
    Date:           2024.05.10
    Modification log (add below)
    Date            Name      Description
    
*/
// Variable declaration - modify the parameters below with the source (where the data comes from) and destination (where the data goes to)
String ParamListID1 = 'EPA8015_D_GCFID_S';	    // define the parameter list
String ParamListID2 = 'EPA8015_D_GCMS_S';	        // define the parameter list
String ParamID = 'Multiplier';	            // define the parameter id
String ParamType = 'Final Value'; 	        // define the parameter type
def SampleType = ${primary:qcsampletype};   // define the sample type

// Generic variable to hold the calculated data
def CalcResult = 0;
def bstatus = false;

// Variable to hold data related to the SDI
def SDIDataItem1=SGS.getSDIDataitem('labvantage', ${primary:s_sampleid}, ParamListID1, '*', ${variantid}, ${dataset}.toString(), ParamID, ParamType, 'max');
def SDIDataItem2=SGS.getSDIDataitem('labvantage', ${primary:s_sampleid}, ParamListID2, '*', ${variantid}, ${dataset}.toString(), ParamID, ParamType, 'max');

// Obtain the list of parameters included and Paramlist array is created
def paramList=SGS.getParamsByParamTypeInclude(SDIDataItem1,'Final Value','124-18-5;1120-21-4;112-40-3;629-50-5;629-59-4;629-62-9;544-76-3;629-78-7;1921-70-6;593-45-3;638-36-8;629-92-5;112-95-8;629-94-7;629-97-0;638-67-5;646-31-1;629-99-2;630-01-3;593-49-7;630-02-4;630-03-5;638-68-6;630-04-6;544-85-4;630-05-7;14167-59-0;630-07-9;630-06-8');

// Read the array that contains the list of parameters included
for(paramid in paramList){
	// To get the contains of the Sample/Parameter
	def SumNum=SGS.getNumericResults('labvantage',SDIDataItem1, SDIDataItem1.paramlistid,SDIDataItem1.paramlistversionid,SDIDataItem1.variantid, ""+SDIDataItem1.dataset, paramid, 'Final Value', 'max', false)

	// Asking if the contains is not empty (lenght)>0
	if(SumNum.length>0)
	{
		bstatus=true;
		CalcResult=CalcResult+SumNum[0];
	}
}
// Obtain the list of parameters included and Paramlist array is created
paramList=SGS.getParamsByParamTypeInclude(SDIDataItem2,'Final Value','124-18-5;112-40-3;90-12-0;91-20-3');

// Read the array that contains the list of parameters included
for(paramid in paramList){
	// To get the contains of the Sample/Parameter
	def SumNum=SGS.getNumericResults('labvantage',SDIDataItem2, SDIDataItem2.paramlistid,SDIDataItem2.paramlistversionid,SDIDataItem2.variantid, ""+SDIDataItem2.dataset, paramid, 'Final Value', 'max', false)

	// Asking if the contains is not empty (lenght)>0
	if(SumNum.length>0)
	{
		bstatus=true;
		CalcResult=CalcResult+SumNum[0];
	}
}
if (CalcResult > 0)
    bstatus = true
return (!bstatus?'':CalcResult)



/*
	TM=VDI3877_M
	PL=VDI3877_Calculation_MD
	Parameter=Results^1
*/
def result1=0;
def result2=0;
def sdidataitem1=SGS.getSDIDataitem('labvantage',${primary:s_sampleid},'VDI3877_AnalysisX400_MD','*',${variantid}, ""+${dataset}, 'Fields','*','max');
def sdidataitem2=SGS.getSDIDataitem('labvantage',${primary:s_sampleid},'VDI3877_AnalysisX1300_MD','*',${variantid}, ""+${dataset}, 'Fields','*','max');
def paramList1=SGS.getParamsByParamType(sdidataitem1,'Final Value','Fields;Miscellaneous_Isolated;Miscellaneous_Clump;Miscellaneous_Bundle;Miscellaneous_Matrix;Miscellaneous_18;Amphibole_Isolated;Amphibole_Clump;Amphibole_Bundle;Amphibole_Matrix;Amphibole_18;Sum_Isolated;Sum_Clump;Sum_Bundle;Sum_Matrix;Sum_18;Weighted_Isolated;Weighted_Clump;Weighted_Bundle;Weighted_Matrix;Weighted_18');
def paramList=paramList1
def bResult=true;
sapphire.util.Logger.logDebug("TEST_CALCULATION","Nb of params : "+paramList.size());
for(paramid in paramList){
    def SumChrysotile=SGS.getNumericResults('labvantage',sdidataitem1, sdidataitem1.paramlistid,sdidataitem1.paramlistversionid,sdidataitem1.variantid, ""+sdidataitem1.dataset, paramid, 'Final Value', 'max', false)
    if(SumChrysotile.length>0)
        result1=result1+SumChrysotile[0];
}
def paramList2=SGS.getParamsByParamType(sdidataitem2,'Final Value','Fields;Miscellaneous_Isolated;Miscellaneous_Clump;Miscellaneous_Bundle;Miscellaneous_Matrix;Amphibole_Isolated;Amphibole_Clump;Amphibole_Bundle;Amphibole_Matrix;Sum_Isolated;Sum_Clump;Sum_Bundle;Sum_Matrix;Weighted_Isolated;Weighted_Clump;Weighted_Bundle;Weighted_Matrix');
paramList=paramList2
sapphire.util.Logger.logDebug("TEST_CALCULATION","Nb of params : "+paramList.size());
for(paramid in paramList){
    def SumChrysotile=SGS.getNumericResults('labvantage',sdidataitem2, sdidataitem2.paramlistid,sdidataitem2.paramlistversionid,sdidataitem2.variantid, ""+sdidataitem2.dataset, paramid, 'Final Value', 'max', false)
    if(SumChrysotile.length>0)
        result1=result1+SumChrysotile[0];
}
def paramList3=SGS.getParamsByParamType(sdidataitem1,'Final Value','Fields;Miscellaneous_Isolated;Miscellaneous_Clump;Miscellaneous_Bundle;Miscellaneous_Matrix;Miscellaneous_18;Chrysotile_Isolated;Chrysotile_Clump;Chrysotile_Bundle;Chrysotile_Matrix;Chrysotile_18;Sum_Isolated;Sum_Clump;Sum_Bundle;Sum_Matrix;Sum_18;Weighted_Isolated;Weighted_Clump;Weighted_Bundle;Weighted_Matrix;Weighted_18');
paramList=paramList3
sapphire.util.Logger.logDebug("TEST_CALCULATION","Nb of params : "+paramList.size());
for(paramid in paramList){
    def SumAmphibole=SGS.getNumericResults('labvantage',sdidataitem1, sdidataitem1.paramlistid,sdidataitem1.paramlistversionid,sdidataitem1.variantid, ""+sdidataitem1.dataset, paramid, 'Final Value', 'max', false)
    if(SumAmphibole.length>0)
        result2=result2+SumAmphibole[0];
}
def paramList4=SGS.getParamsByParamType(sdidataitem2,'Final Value','Fields;Miscellaneous_Isolated;Miscellaneous_Clump;Miscellaneous_Bundle;Miscellaneous_Matrix;Chrysotile_Isolated;Chrysotile_Clump;Chrysotile_Bundle;Chrysotile_Matrix;Sum_Isolated;Sum_Clump;Sum_Bundle;Sum_Matrix;Weighted_Isolated;Weighted_Clump;Weighted_Bundle;Weighted_Matrix');
paramList=paramList4
sapphire.util.Logger.logDebug("TEST_CALCULATION","Nb of params : "+paramList.size());
for(paramid in paramList){
    def SumAmphibole=SGS.getNumericResults('labvantage',sdidataitem2, sdidataitem2.paramlistid,sdidataitem2.paramlistversionid,sdidataitem2.variantid, ""+sdidataitem2.dataset, paramid, 'Final Value', 'max', false)
    if(SumAmphibole.length>0)
        result2=result2+SumAmphibole[0];
}
if (${Analysable;Final Value}=="No") 
	return "Not analysable"
else if (result1>0 || result2>0)
    return "Asbestos detected"
else 
      return "Asbestos not detected"
sapphire.util.Logger.logDebug("TEST_CALCULATION","Results : "+result1);
return (!bResult?'':resulttext)





/*
  TM: DQM_APHA10200_F_W
  PL: DQM_APHA10200_F_W 
  Parameter: Taxa
  Type: Standard (Text Calc)
  Author: DQM team
  Purpose: Calculation for APHA10200_F_W (Fitoplancton / Perifiton)
  Date: 2024-04-11
*/
// Declare variable general-purpose
def CalcResult='';
def DestinationParam = 'Taxa';
def DestinationPType = 'Final Value';
def bResult=false;

// Variable declaration - modify the parameters below with the source (where the data comes from) and destination (where the data goes to)
String ParamID = ${#;#;#;paramid}.toString();	    // define the parameter for the calculation
String ParamType = ${#;#;#;paramtype}.toString(); 	// define the parameter type
def SampleType = ${primary:qcsampletype};		    // define the sample type

// Variable to hold data related to the SDI
def SDIDataItem = SGS.getSDIDataitem('labvantage', ${primary:s_sampleid}, ${paramlistid}, ${paramlistversionid}.toString(), ${variantid}, ${dataset}.toString(), ParamID, ParamType, 'max');

// Get the replicates number
def maxRep=SGS.getMaxReplicates('labvantage', ${primary:s_sampleid}, ${paramlistid}, ${paramlistversionid}.toString(), ${variantid}, ${dataset}.toString(), DestinationParam , DestinationPType);
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
bResult=true;
if (RepNum >= 2) 
  CalcResult='Fail '+RepPos
else
  CalcResult='Pass'
return (!bResult?'':CalcResult)