// Variable declaration - modify the parameters below with the source (where the data comes from) and destination (where the data goes to)
def SourceParamID = 'WindSpeed';
def SourcePType = 'Final Value';
String ParamID = ${#;#;#;paramid}.toString();	    // define the parameter for the calculation
String ParamType = ${#;#;#;paramtype}.toString(); 	// define the parameter type
def SampleType = ${primary:qcsampletype};		    // define the sample type

// Generic variable to hold the calculated data
def CalcResult = 0;
def TextResult = '';
def bstatus = false;

// Variable to hold data related to the SDI
def SDIDataItem= SGS.getSDIDataitem('labvantage', ${primary:s_sampleid}, ${paramlistid}, ${paramlistversionid}.toString(), ${variantid}, ${dataset}.toString(), ParamID, ParamType, 'max');

// Obtain the maximum number of replicates
def ParamDisplayF=SGS.getDisplayFormat('labvantage', ${primary:s_sampleid}, ${paramlistid}, ${paramlistversionid}.toString(), ${variantid}, ${dataset}.toString(), SourceParamID);

double ParamValue = ${DQM_TextFormat;*;*;max|DQMParam1;Final Value;#};

switch(ParamDisplayF)
{
case ['0']: 
  CalcResult=Math.round(ParamValue) as int
  break
case ['0.0']: 
  CalcResult=(ParamValue).round(1)  
  break
case ['0.00']: 
  CalcResult=(ParamValue).round(2)  
  break
case ['0.000']: 
  CalcResult=(ParamValue).round(3)  
  break
default:
  CalcResult=(ParamValue).round(4)  
  break
}
TextResult=CalcResult.toString()
return TextResult



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



if (CalcResult > 0)
    bstatus = true
return (!bstatus?'':CalcResult)


${DQM_TextFormat;*;*;max|DQMParam1;Final Value;#;displayformat}




def result="";
def tempRes="";
def prevresult=0;
def sMPNValueReplaced="";
def bLowerUpper=false;
def bcomma=false;
// Declare parameters to get values from replicates samples
def sType = ${TypeRounding;Final Value};
def DestinationPFC = 'TotalColiform';
def DestinationP = 'MPNValue';
def DestinationPTypeF = 'Final Value';
def sdidataitem = SGS.getSDIDataitem('labvantage',${primary:s_sampleid},${paramlistid},""+${paramlistversionid},${variantid}, ""+${dataset}, DestinationPFC, DestinationPTypeF, 'max');
if (SGS.checkResults('labvantage',sdidataitem, '#','#','#','#', DestinationP, DestinationPTypeF, 'max', true))
{
  def sMPNValue=SGS.getTextResults('labvantage', sdidataitem,, '#','#','#','#', DestinationP, DestinationPTypeF, 'max', true)[0]
  if ((sMPNValue.indexOf('<')>-1) || (sMPNValue.indexOf('>')>-1)) {
    bLowerUpper=true
    prevresult=""
    prevresult=${TotalColiform;Standard} }
  else {
    if (sMPNValue.indexOf(',')>-1) {
      bcomma=true
      sMPNValueReplaced = sMPNValue.replaceAll(",", ".") }
    else {
      sMPNValueReplaced = sMPNValue }  
    if (sMPNValueReplaced.isNumber())
      {
      float convertedNumberMPN = Float.parseFloat(sMPNValueReplaced)

      if (sType=='OddEvenRounding')
        if (sMPNValueReplaced.indexOf('.')>-1) {
          decimalPart = convertedNumberMPN - (convertedNumberMPN as int)
          // 4 % 2 = 0 and 5 % 2 = 1 if quotient 4/2=2=0 (integer) and 5/2=2.5=1 (float)
          // Math.floor rounds a number DOWN to the nearest integer
          // Math.ceil rounds a number UP to the nearest integer
          prevresult=(convertedNumberMPN as int) % 2 == 0 && decimalPart < 0.6 && decimalPart >=0.5 ? Math.floor(convertedNumberMPN*(10/${LowestDilution;Final Value})) as int:
                    (convertedNumberMPN as int) % 2 != 0 && decimalPart < 0.6 && decimalPart >=0.5 ? Math.ceil(convertedNumberMPN*(10/${LowestDilution;Final Value})) as int:
                      Math.round(convertedNumberMPN*(10/${LowestDilution;Final Value})) as int }
        else
          prevresult = Math.round ((convertedNumberMPN)*(10/${LowestDilution;Final Value})) as int

      else if (sType=='NormalRounding')
          if (sMPNValueReplaced.indexOf('.')>-1)       
            prevresult = ((convertedNumberMPN)*(10/${LowestDilution;Final Value})).round(1)
          else
            prevresult = Math.round((convertedNumberMPN)*(10/${LowestDilution;Final Value})) as int
      }

    else
      prevresult=${TotalColiform;Standard} }
}
if (bLowerUpper)
  result=prevresult
else if (bcomma)
  { 
  tempRes=prevresult.toString()
  result=tempRes.replaceAll("\\.", ",")
  }
else
  result=prevresult
return result



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



switch(${DQMSum2;Final Value}) 
{
case 10:
    result = "Green"
    break
case 5: 
    result = "Yellow"
    break
default:
    result = "Red"
    break
}
return result 


$G{def result=""
switch(${DQMParam1;Final Value})
{
case ['Leah','Valor','Grace']: 
  result="Asia"
  break
case ['Nerea','Javier']: 
  result="Europe"
  break
case ['Marino']: 
  result="Latam"
  break
default:
  result="Peru"
  break
}
return result
}
