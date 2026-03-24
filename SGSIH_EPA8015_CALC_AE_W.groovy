/*
    Description:    Calculation for SGSIH_EPA8015_CALC_AE_W (MPN)
    Created by:     DQM team
    PL:             SGSIH_EPA8015_CALC_AE_W
    Parameter:      GRO_C6_C10 (Final Value)
    Date:           2024.05.08
    Modification log (add below)
    Date            Name      Description
    
*/
// Variable declaration - modify the parameters below with the source (where the data comes from) and destination (where the data goes to)
String ParamListID1 = 'EPA8015_D_GCFID_W';	    // define the parameter list
String ParamListID2 = 'EPA8015_D_GCMS_W';	        // define the parameter list
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
def paramList=SGS.getParamsByParamTypeInclude(SDIDataItem1,'Final Value','111-65-9;111-84-2;124-18-5');

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
paramList=SGS.getParamsByParamTypeInclude(SDIDataItem2,'Final Value','71-43-2;100-41-4;108-88-3;95-47-6;108-38-3;106-42-3;1634-04-4;109-66-0;110-54-3;111-65-9;124-18-5;91-20-3;526-73-8');

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
    Description:    Calculation for SGSIH_EPA8015_CALC_AE_W (MPN)
    Created by:     DQM team
    PL:             SGSIH_EPA8015_CALC_AE_W
    Parameter:      VPH_C5_C12 (Final Value)
    Date:           2024.05.08
    Modification log (add below)
    Date            Name      Description
    
*/
// Variable declaration - modify the parameters below with the source (where the data comes from) and destination (where the data goes to)
String ParamListID1 = 'EPA8015_D_GCFID_W';	    // define the parameter list
String ParamListID2 = 'EPA8015_D_GCMS_W';	    // define the parameter list
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
def paramList=SGS.getParamsByParamTypeInclude(SDIDataItem1,'Final Value','111-65-9;111-84-2;124-18-5;1120-21-4;112-40-3');

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
paramList=SGS.getParamsByParamTypeInclude(SDIDataItem2,'Final Value','71-43-2;100-41-4;108-88-3;95-47-6;108-38-3;106-42-3;1634-04-4;109-66-0;110-54-3;111-65-9;124-18-5;112-40-3;90-12-0;91-20-3;526-73-8');

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
    Description:    Calculation for SGSIH_EPA8015_CALC_AE_W (MPN)
    Created by:     DQM team
    PL:             SGSIH_EPA8015_CALC_AE_W
    Parameter:      DRO_C10_C28 (Final Value)
    Date:           2024.05.08
    Modification log (add below)
    Date            Name      Description
    
*/
// Variable declaration - modify the parameters below with the source (where the data comes from) and destination (where the data goes to)
String ParamListID1 = 'EPA8015_D_GCFID_W';	    // define the parameter list
String ParamListID2 = 'EPA8015_D_GCMS_W';	    // define the parameter list
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
def paramList=SGS.getParamsByParamTypeInclude(SDIDataItem1,'Final Value','124-18-5;1120-21-4;112-40-3;629-50-5;629-59-4;629-62-9;544-76-3;629-78-7;1921-70-6;593-45-3;638-36-8;629-92-5;112-95-8;629-94-7;629-97-0;638-67-5;646-31-1;629-99-2;630-01-3;593-49-7;630-02-4');

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
paramList=SGS.getParamsByParamTypeInclude(SDIDataItem2,'Final Value','124-18-5;112-40-3;90-12-0;91-20-3;526-73-8');

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
    Description:    Calculation for SGSIH_EPA8015_CALC_AE_W (MPN)
    Created by:     DQM team
    PL:             SGSIH_EPA8015_CALC_AE_W
    Parameter:      EPH_C9_C36 (Final Value)
    Date:           2024.05.08
    Modification log (add below)
    Date            Name      Description
    
*/
// Variable declaration - modify the parameters below with the source (where the data comes from) and destination (where the data goes to)
String ParamListID1 = 'EPA8015_D_GCFID_W';	    // define the parameter list
String ParamListID2 = 'EPA8015_D_GCMS_W';	    // define the parameter list
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
def paramList=SGS.getParamsByParamTypeInclude(SDIDataItem1,'Final Value','111-84-2;124-18-5;1120-21-4;112-40-3;629-50-5;629-59-4;629-62-9;544-76-3;629-78-7;1921-70-6;593-45-3;638-36-8;629-92-5;112-95-8;629-94-7;629-97-0;638-67-5;646-31-1;629-99-2;630-01-3;593-49-7;630-02-4;630-03-5;638-68-6;630-04-6;544-85-4;630-05-7;14167-59-0;630-07-9;630-06-8');

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
paramList=SGS.getParamsByParamTypeInclude(SDIDataItem2,'Final Value','124-18-5;112-40-3;90-12-0;91-20-3;526-73-8');

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
    Description:    Calculation for SGSIH_EPA8015_CALC_AE_W (MPN)
    Created by:     DQM team
    PL:             SGSIH_EPA8015_CALC_AE_W
    Parameter:      HF_C27 (Final Value)
    Date:           2024.05.08
    Modification log (add below)
    Date            Name      Description
    
*/
// Variable declaration - modify the parameters below with the source (where the data comes from) and destination (where the data goes to)
String ParamListID = 'EPA8015_D_GCFID_W';	        // define the parameter list
String ParamID = 'Multiplier';	            // define the parameter id
String ParamType = 'Final Value'; 	        // define the parameter type
def SampleType = ${primary:qcsampletype};   // define the sample type

// Generic variable to hold the calculated data
def CalcResult = 0;
def bstatus = false;

// Variable to hold data related to the SDI
def SDIDataItem=SGS.getSDIDataitem('labvantage', ${primary:s_sampleid}, ParamListID, '*', ${variantid}, ${dataset}.toString(), ParamID, ParamType, 'max');

// Obtain the list of parameters included and Paramlist array is created
def paramList=SGS.getParamsByParamTypeInclude(SDIDataItem,'Final Value','14167-59-0;4181-95-7;544-85-4;630-02-4;630-03-5;630-04-6;630-05-7;630-06-8;630-07-9;638-68-6;7194-84-5;7194-85-6;7194-86-7');

// Read the array that contains the list of parameters included
for(paramid in paramList){
	// To get the contains of the Sample/Parameter
	def SumNum=SGS.getNumericResults('labvantage',SDIDataItem, SDIDataItem.paramlistid,SDIDataItem.paramlistversionid,SDIDataItem.variantid, ""+SDIDataItem.dataset, paramid, 'Final Value', 'max', false)

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
    Description:    Calculation for SGSIH_EPA8015_CALC_AE_W (MPN)
    Created by:     DQM team
    PL:             SGSIH_EPA8015_CALC_AE_W
    Parameter:      TPH_C8_C40 (Final Value)
    Date:           2024.05.08
    Modification log (add below)
    Date            Name      Description
    
*/
// Variable declaration - modify the parameters below with the source (where the data comes from) and destination (where the data goes to)
String ParamListID = 'EPA8015_D_GCFID_W';	        // define the parameter list
String ParamID = 'Multiplier';	            // define the parameter id
String ParamType = 'Final Value'; 	        // define the parameter type
def SampleType = ${primary:qcsampletype};   // define the sample type

// Generic variable to hold the calculated data
def CalcResult = 0;
def bstatus = false;

// Variable to hold data related to the SDI
def SDIDataItem=SGS.getSDIDataitem('labvantage', ${primary:s_sampleid}, ParamListID, '*', ${variantid}, ${dataset}.toString(), ParamID, ParamType, 'max');

// Obtain the list of parameters included and Paramlist array is created
def paramList=SGS.getParamsByParamTypeInclude(SDIDataItem,'Final Value','111-65-9;111-84-2;1120-21-4;112-40-3;112-95-8;124-18-5;14167-59-0;4181-95-7;544-76-3;544-85-4;593-45-3;593-49-7;629-50-5;629-59-4;629-62-9;629-78-7;629-92-5;629-94-7;629-97-0;629-99-2;630-01-3;630-02-4;630-03-5;630-04-6;630-05-7;630-06-8;630-07-9;638-67-5;638-68-6;646-31-1;7194-84-5;7194-85-6;7194-86-7');

// Read the array that contains the list of parameters included
for(paramid in paramList){
	// To get the contains of the Sample/Parameter
	def SumNum=SGS.getNumericResults('labvantage',SDIDataItem, SDIDataItem.paramlistid,SDIDataItem.paramlistversionid,SDIDataItem.variantid, ""+SDIDataItem.dataset, paramid, 'Final Value', 'max', false)

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




/* ==================================== */
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

avg([EPA8015_GCFID_W;*;*;max|111-65-9;Final Value;max],[EPA8015_GCFID_W;*;*;max|111-84-2;Final Value;max],[EPA8015_GCFID_W;*;*;max|124-18-5;Final Value;max],[EPA8015_GCMS_W;*;*;max|71-43-2;Final Value;max],[EPA8015_GCMS_W;*;*;max|100-41-4;Final Value;max],[EPA8015_GCMS_W;*;*;max|108-88-3;Final Value;max],[EPA8015_GCMS_W;*;*;max|95-47-6;Final Value;max],[EPA8015_GCMS_W;*;*;max|108-38-3;Final Value;max],[EPA8015_GCMS_W;*;*;max|106-42-3;Final Value;max],[EPA8015_GCMS_W;*;*;max|1634-04-4;Final Value;max],[EPA8015_GCMS_W;*;*;max|109-66-0;Final Value;max],[EPA8015_GCMS_W;*;*;max|110-54-3;Final Value;max],[EPA8015_GCMS_W;*;*;max|111-65-9;Final Value;max],[EPA8015_GCMS_W;*;*;max|124-18-5;Final Value;max],[EPA8015_GCMS_W;*;*;max|91-20-3;Final Value;max],[EPA8015_GCMS_W;*;*;max|526-73-8;Final Value;max])


/*
    Description:    Calculation for APHA9215_E_Calculation_W (MPN)
    Created by:     DQM team
    PL:             APHA9221_B_TC_Calculation_W
    Parameter:      MPNValue (Final Value)
    Date:           2024.02.29
    Modification log (add below)
    Date            Name      Description
    
*/
// Variable declaration - modify the parameters below with the source (where the data comes from) and destination (where the data goes to)
def SourceParamID = 'MPN';
def SourcePType = 'Final Value';
String ParamID = ${#;#;#;paramid}.toString();	    // define the parameter for the calculation
String ParamType = ${#;#;#;paramtype}.toString(); 	// define the parameter type
def SampleType = ${primary:qcsampletype};		    // define the sample type

// Generic variable to hold the calculated data
def CalcResult = '';
def bstatus = false;

// Variable to hold data related to the SDI
def SDIDataItem= SGS.getSDIDataitem('labvantage', ${primary:s_sampleid}, ${paramlistid}, ${paramlistversionid}.toString(), ${variantid}, ${dataset}.toString(), ParamID, ParamType, 'max');

// Obtain the maximum number of replicates
def maxRep=SGS.getMaxReplicates('labvantage', ${primary:s_sampleid}, ${paramlistid}, ${paramlistversionid}.toString(), ${variantid}, ${dataset}.toString(), SourceParamID , SourcePType);

def MPNCombination = '';
// Reading the replicates per sample to get the differents values
for(int i in 1..maxRep) {
  def MPNTubes=SGS.getTextResults('labvantage', SDIDataItem, SDIDataItem.paramlistid,SDIDataItem.paramlistversionid,SDIDataItem.variantid, ""+SDIDataItem.dataset, SourceParamID , SourcePType, Integer.toString(i), true)
  if (MPNTubes.length > 0) {
    if (i == 1)
        MPNCombination = MPNTubes[0]
    else
        MPNCombination = MPNCombination + "-" + MPNTubes[0]
    }
}
CalcResult=SGS.getMPNOperFinalValue('labvantage',${paramlistid},${paramlistversionid},${variantid},"MPNValue","Final Value",MPNCombination);

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
    PL=DQMPL_Class03_02
    Parameter=DQMSum1 (Final Value)
    Data Type=Numeric Calc
    Remarks=Sum GetParamsByParamTypeInclude
*/
// Establish the link between sample and ParameterList
def sdidataitem=SGS.getSDIDataitem('labvantage',${primary:s_sampleid},'DQMPL_Class03_02','*',${variantid}, ""+${dataset}, 'DF','*','max');

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