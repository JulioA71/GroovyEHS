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
    Description:    Calculation for SGSIH_EPA8015_CALC_AE_S
    Created by:     DQM team
    PL:             SGSIH_EPA8015_CALC_AE_S
    Parameter:      EPH_C10_C40 (Final Value)
    Date:           2024.05.10
    Modification log (add below)
    Date            Name      Description
    
*/
// Variable declaration - modify the parameters below with the source (where the data comes from) and destination (where the data goes to)
String ParamListID1 = 'EPA8015_D_GCFID_S';	    // define the parameter list
String ParamListID2 = 'EPA8015_D_GCMS_S';	    // define the parameter list
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
def paramList=SGS.getParamsByParamTypeInclude(SDIDataItem1,'Final Value','124-18-5;1120-21-4;112-40-3;629-50-5;629-59-4;629-62-9;544-76-3;629-78-7;1921-70-6;593-45-3;638-36-8;629-92-5;112-95-8;629-94-7;629-97-0;638-67-5;646-31-1;629-99-2;630-01-3;593-49-7;630-02-4;630-03-5;638-68-6;630-04-6;544-85-4;630-05-7;14167-59-0;630-07-9;630-06-8;7194-84-5;7194-85-6;7194-86-7;4181-95-7');

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
    Description:    Calculation for SGSIH_EPA8015_CALC_AE_S
    Created by:     DQM team
    PL:             SGSIH_EPA8015_CALC_AE_S
    Parameter:      GRO_C6_C9 (Final Value)
    Date:           2024.05.10
    Modification log (add below)
    Date            Name      Description
    
*/
// Variable declaration - modify the parameters below with the source (where the data comes from) and destination (where the data goes to)
String ParamListID1 = 'EPA8015_D_GCFID_S';	    // define the parameter list
String ParamListID2 = 'EPA8015_D_GCMS_S';	    // define the parameter list
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
def paramList=SGS.getParamsByParamTypeInclude(SDIDataItem1,'Final Value','111-65-9;111-84-2');

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
paramList=SGS.getParamsByParamTypeInclude(SDIDataItem2,'Final Value','71-43-2;100-41-4;108-88-3;95-47-6;106-42-3;110-54-3;111-65-9;526-73-8');

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
    Description:    Calculation for SGSIH_EPA8015_CALC_AE_S
    Created by:     DQM team
    PL:             SGSIH_EPA8015_CALC_AE_S
    Parameter:      HF_C37_C40 (Final Value)
    Date:           2024.05.10
    Modification log (add below)
    Date            Name      Description
    
*/
// Variable declaration - modify the parameters below with the source (where the data comes from) and destination (where the data goes to)
String ParamListID = 'EPA8015_D_GCFID_S';	        // define the parameter list
String ParamID = 'Multiplier';	            // define the parameter id
String ParamType = 'Final Value'; 	        // define the parameter type
def SampleType = ${primary:qcsampletype};   // define the sample type

// Generic variable to hold the calculated data
def CalcResult = 0;
def bstatus = false;

// Variable to hold data related to the SDI
def SDIDataItem=SGS.getSDIDataitem('labvantage', ${primary:s_sampleid}, ParamListID, '*', ${variantid}, ${dataset}.toString(), ParamID, ParamType, 'max');

// Obtain the list of parameters included and Paramlist array is created
def paramList=SGS.getParamsByParamTypeInclude(SDIDataItem,'Final Value','7194-84-5;7194-85-6;7194-86-7;4181-95-7');

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
    Description:    Calculation for SGSIH_EPA8015_CALC_AE_S
    Created by:     DQM team
    PL:             SGSIH_EPA8015_CALC_AE_S
    Parameter:      VPH_C5_C10 (Final Value)
    Date:           2024.05.10
    Modification log (add below)
    Date            Name      Description
    
*/
// Variable declaration - modify the parameters below with the source (where the data comes from) and destination (where the data goes to)
String ParamListID1 = 'EPA8015_D_GCFID_S';	    // define the parameter list
String ParamListID2 = 'EPA8015_D_GCMS_S';	    // define the parameter list
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
paramList=SGS.getParamsByParamTypeInclude(SDIDataItem2,'Final Value','71-43-2;100-41-4;108-88-3;95-47-6;106-42-3;1634-04-4;109-66-0;110-54-3;111-65-9;124-18-5;91-20-3;526-73-8');

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



