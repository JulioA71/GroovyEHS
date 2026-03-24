/*
    Author: Nerea / Julio
    PL=APHA9223_B_Calculation_DQM3_W
    Parameter=TotalColiform (Final Value)
    Date: 2024.02.29
    
    Updated: 2024.03.01
    Remarks: we can select which calculation wants performs (TypeRounding)  Text(Calc)
    Updated: 2024.03.12
    Remarks: Change to Numeric (Calc) for TotalColiform of type Standard/Final Value to allow uses LimitRules or Specifications
*/
def result=0;
def prevresult=0;
def tempRes="";
def bcomma=false;
def bResult=false;
// Declare parameters to get values from replicates samples
def DestinationPFC = 'TotalColiform';
def DestinationP = 'MPNValue';
def DestinationPTypeF = 'Final Value';
def sdidataitem = SGS.getSDIDataitem('labvantage',${primary:s_sampleid},${paramlistid},""+${paramlistversionid},${variantid}, ""+${dataset}, DestinationPFC, DestinationPTypeF, 'max');
if (SGS.checkResults('labvantage',sdidataitem, '#','#','#','#', DestinationP, DestinationPTypeF, 'max', true))
{
  def sMPNValueText=SGS.getTextResults('labvantage', sdidataitem,, '#','#','#','#', DestinationP, DestinationPTypeF, 'max', true)[0]
  def nMPNValueNum=${TotalColiform;Standard} 
  if ((sMPNValueText.indexOf('<')>-1) || (sMPNValueText.indexOf('>')>-1)) {
    bResult=true
    prevresult=nMPNValueNum }
  else if (sMPNValueText == 'NOT VALID') {
    bResult=true
    prevresult=nMPNValueNum }
  else {
    if (sMPNValueText.indexOf(',')>-1) {
      bcomma=true }
    bResult=true
    prevresult=((nMPNValueNum) * (10 / ${LowestDilution;Final Value})) * ${DF;Final Value}
    // prevresult=Math.round((nMPNValueNum)*(10/${LowestDilution;Final Value}))*${DF;Final Value} as int 
    }
}
if (bResult) {
  result=prevresult
  }
if (bcomma)  { 
  tempRes=prevresult.toString()
  result=tempRes.replaceAll("\\.", ",")
  }
return (!bResult?'':result)




/*
    Author: Nerea / Julio
    PL=APHA9223_B_Calculation_DQM3_W
    Parameter=TotalColiform (Final Value)
    Date: 2024.02.29
    
    Updated: 2024.03.01
    Remarks: we can select which calculation wants performs (TypeRounding)  Text(Calc)
    Updated: 2024.03.12
    Remarks: Change to Numeric (Calc) for TotalColiform of type Standard/Final Value to allow uses LimitRules or Specifications
*/
def result=0;
def prevresult=0;
def tempRes="";
def bcomma=false;
def bResult=false;
// Declare parameters to get values from replicates samples
def sType = ${TypeRounding;Final Value};
def DestinationPFC = 'TotalColiform';
def DestinationP = 'MPNValue';
def DestinationPTypeF = 'Final Value';
def sdidataitem = SGS.getSDIDataitem('labvantage',${primary:s_sampleid},${paramlistid},""+${paramlistversionid},${variantid}, ""+${dataset}, DestinationPFC, DestinationPTypeF, 'max');
if (SGS.checkResults('labvantage',sdidataitem, '#','#','#','#', DestinationP, DestinationPTypeF, 'max', true))
{
  def sMPNValueText=SGS.getTextResults('labvantage', sdidataitem,, '#','#','#','#', DestinationP, DestinationPTypeF, 'max', true)[0]
  def nMPNValueNum=${TotalColiform;Standard} 
  if ((sMPNValueText.indexOf('<')>-1) || (sMPNValueText.indexOf('>')>-1)) {
    bResult=true
    prevresult=nMPNValueNum }
  else if (sMPNValueText == 'NOT VALID') {
    bResult=true
    prevresult=nMPNValueNum }
  else {
    if (sMPNValueText.indexOf(',')>-1) {
      bcomma=true
      bResult=true }
    if (sType=='OddEvenRounding') {
      bResult=true
      if (sMPNValueText.indexOf('.')>-1) {
        decimalPart = nMPNValueNum - (nMPNValueNum as int)
        // 4 % 2 = 0 and 5 % 2 = 1 if quotient 4/2=2=0 (integer) and 5/2=2.5=1 (float)
        // Math.floor rounds a number DOWN to the nearest integer
        // Math.ceil rounds a number UP to the nearest integer
        prevresult=(nMPNValueNum as int) % 2 == 0 && decimalPart < 0.6 && decimalPart >=0.5 ? Math.floor((nMPNValueNum*(10/${LowestDilution;Final Value}))*${DF;Final Value}) as int:
               (nMPNValueNum as int) % 2 != 0 && decimalPart < 0.6 && decimalPart >=0.5 ? Math.ceil(((nMPNValueNum)*(10/${LowestDilution;Final Value}))*${DF;Final Value}) as int:
                Math.round((nMPNValueNum)*(10/${LowestDilution;Final Value}))*${DF;Final Value} as int }
      else
        prevresult=Math.round((nMPNValueNum)*(10/${LowestDilution;Final Value}))*${DF;Final Value} as int }

    else if (sType=='NormalRounding') {
      bResult=true
      prevresult=Math.round((nMPNValueNum)*(10/${LowestDilution;Final Value}))*${DF;Final Value} as int }
    }
}
if (bResult) {
  result=prevresult
  }
if (bcomma)  { 
  tempRes=prevresult.toString()
  result=tempRes.replaceAll("\\.", ",")
  }
return (!bResult?'':result)


/*
    Author: Nerea / Julio
    PL=APHA9223_B_Calculation_DQM3_W
    Parameter=TotalColiform (Final Value)
    Date: 2024.02.29
    
    Updated: 2024.03.01
    Remarks: we can select which calculation wants performs (TypeRounding)  Text(Calc)
    Updated: 2024.03.12
    Remarks: Change to Numeric (Calc) for TotalColiform of type Standard/Final Value to allow uses LimitRules or Specifications
*/
def result=0;
def prevresult=0;
def tempRes="";
def bcomma=false;
def bResult=false;
// Declare parameters to get values from replicates samples
def sType = ${TypeRounding;Final Value};
def DestinationPFC = 'TotalColiform';
def DestinationP = 'MPNValue';
def DestinationPTypeF = 'Final Value';
def sdidataitem = SGS.getSDIDataitem('labvantage',${primary:s_sampleid},${paramlistid},""+${paramlistversionid},${variantid}, ""+${dataset}, DestinationPFC, DestinationPTypeF, 'max');
if (SGS.checkResults('labvantage',sdidataitem, '#','#','#','#', DestinationP, DestinationPTypeF, 'max', true))
{
  def sMPNValueText=SGS.getTextResults('labvantage', sdidataitem,, '#','#','#','#', DestinationP, DestinationPTypeF, 'max', true)[0]
  def nMPNValueNum=${TotalColiform;Standard} 
  if ((sMPNValueText.indexOf('<')>-1) || (sMPNValueText.indexOf('>')>-1)) {
    bResult=true
    prevresult=nMPNValueNum }
  else if (sMPNValueText == 'NOT VALID') {
    bResult=true
    prevresult=nMPNValueNum }
  else {
    if (sMPNValueText.indexOf(',')>-1) {
      bcomma=true
      bResult=true }
    if (sType=='OddEvenRounding') {
      bResult=true
      if (sMPNValueText.indexOf('.')>-1) {
        decimalPart = nMPNValueNum - (nMPNValueNum as int)
        // 4 % 2 = 0 and 5 % 2 = 1 if quotient 4/2=2=0 (integer) and 5/2=2.5=1 (float)
        // Math.floor rounds a number DOWN to the nearest integer
        // Math.ceil rounds a number UP to the nearest integer
        prevresult=(nMPNValueNum as int) % 2 == 0 && decimalPart < 0.6 && decimalPart >=0.5 ? Math.floor((nMPNValueNum*(10/${LowestDilution;Final Value}))*${DF;Final Value}) as int:
               (nMPNValueNum as int) % 2 != 0 && decimalPart < 0.6 && decimalPart >=0.5 ? Math.ceil(((nMPNValueNum)*(10/${LowestDilution;Final Value}))*${DF;Final Value}) as int:
                Math.round((nMPNValueNum)*(10/${LowestDilution;Final Value}))*${DF;Final Value} as int }
      else
        prevresult=Math.round((nMPNValueNum)*(10/${LowestDilution;Final Value}))*${DF;Final Value} as int }

    else if (sType=='NormalRounding') {
      bResult=true
      prevresult=Math.round((nMPNValueNum)*(10/${LowestDilution;Final Value}))*${DF;Final Value} as int }
    }
}
if (bResult) {
  result=prevresult
  }
if (bcomma)  { 
  tempRes=prevresult.toString()
  result=tempRes.replaceAll("\\.", ",")
  }
return (!bResult?'':result)




/*
    Author: Nerea / Julio
    PL=APHA9223_B_Calculation_DQM3_W
    Parameter=TotalColiform (Final Value)
    Date: 2024.02.29
    
    Updated: 2024.03.01
    Remarks: we can select which calculation wants performs (TypeRounding)  Text(Calc)
*/
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
          prevresult=(convertedNumberMPN as int) % 2 == 0 && decimalPart < 0.6 && decimalPart >=0.5 ? Math.floor((convertedNumberMPN*(10/${LowestDilution;Final Value}))*${DF;Final Value}) as int:
                    (convertedNumberMPN as int) % 2 != 0 && decimalPart < 0.6 && decimalPart >=0.5 ? Math.ceil(((convertedNumberMPN)*(10/${LowestDilution;Final Value}))*${DF;Final Value}) as int:
                      Math.round(((convertedNumberMPN)*(10/${LowestDilution;Final Value}))*${DF;Final Value}) as int }
        else
          prevresult = Math.round((convertedNumberMPN)*(10/${LowestDilution;Final Value}))*${DF;Final Value}) as int

      else if (sType=='NormalRounding')
          if (sMPNValueReplaced.indexOf('.')>-1)       
            prevresult = Math.round((convertedNumberMPN)*(10/${LowestDilution;Final Value}))*${DF;Final Value}) as int
          else
            prevresult = Math.round((convertedNumberMPN)*(10/${LowestDilution;Final Value}))*${DF;Final Value}) as int
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
  TM=APHA9223_B_DQM3_W
  PL=APHA9223_B_Calculation_DQM3_W v2
  MPNValue is Text type
  Parameter=TotalColiform(Final Value)
  Type=Final Value
  Updated: 2024.02.23
*/
def result="";
def tempRes="";
def prevresult=0;
def sMPNValueReplaced="";
def bLowerUpper=false;
def bcomma=false;
// Declare parameters to get values from replicates samples
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
    if (sMPNValueReplaced.isNumber()) {
      float convertedNumberMPN = Float.parseFloat(sMPNValueReplaced)
      if (sMPNValueReplaced.indexOf('.')>-1) {
        // prevresult = (${DF;Final Value}*convertedNumberMPN).round(1)
        //
        decimalPart = convertedNumberMPN - (convertedNumberMPN as int)
        // 4 % 2 = 0 and 5 % 2 = 1 if quotient 4/2=2 then 0 (integer) and 5/2=2.5 then 1 (float)
        // Math.floor rounds a number DOWN to the nearest integer
        // Math.ceil rounds a number UP to the nearest integer
        prevresult=(convertedNumberMPN as int) % 2 == 0 && decimalPart < 0.6 && decimalPart >=0.5 ? Math.floor(convertedNumberMPN) as int:
                   (convertedNumberMPN as int) % 2 != 0 && decimalPart < 0.6 && decimalPart >=0.5 ? Math.ceil(convertedNumberMPN) as int:
                    Math.round(convertedNumberMPN) as int }
      else
        prevresult = Math.round(${DF;Final Value}*convertedNumberMPN) as int }
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
  TM=APHA9223_B_DQM3_W
  PL=APHA9223_B_Calculation_DQM3_W v1
  MPNValue is Text type
  Parameter=TotalColiform(Final Value)
  Type=Final Value
  Updated: 2024.01.31
*/
def result="";
def tempRes="";
def prevresult=0;
def sMPNValueReplaced="";
def bLowerUpper=false;
def bcomma=false;
/* Declare parameters to get values from replicates samples */
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
    if (sMPNValueReplaced.isNumber()) {
      float convertedNumberMPN = Float.parseFloat(sMPNValueReplaced)
      if (sMPNValueReplaced.indexOf('.')>-1)
        prevresult = (${DF;Final Value}*convertedNumberMPN).round(1)
      else
        prevresult = Math.round(${DF;Final Value}*convertedNumberMPN) as int }
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
  TM=APHA9223_B_DQM3_W
  PL=APHA9223_B_Calculation_DQM3_W
  MPNValue is Text type
  Parameter=TotalColiform(Final Value)
  Type=Final Value
*/
def result="";
def tempRes="";
def prevresult=0;
def sMPNValueReplaced="";
def bLowerUpper=false;
def bcomma=false;
/* Declare parameters to get values from replicates samples */
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
    if (sMPNValueReplaced.isNumber()) {
      float convertedNumberMPN = Float.parseFloat(sMPNValueReplaced)
      if (sMPNValueReplaced.indexOf('.')>-1)
        prevresult = (${DF;Final Value}*convertedNumberMPN).round(1)
      else
        prevresult = Math.round(${DF;Final Value}*convertedNumberMPN) as int }
    else
      prevresult=${TotalColiform;Standard} }
}
if (bLowerUpper)
  result=prevresult
else if (bcomma)
  { 
  def xPos=0
  def xResInt=""
  def xResDec=""
  tempRes=prevresult.toString() 
  xPos=tempRes.indexOf('.')
  xResInt=tempRes.substring(0,xPos)
  xResDec=tempRes.substring(xPos+1)
  result=xResInt + "," + xResDec
  }
else
  result=prevresult
return result



result="LD=" + Math.round(${DetectionLimit;Final Value}) + " ST=" + Math.round(${StructureObservee;Final Value}) + " *C=" + Math.round(${Concentration;Final Value}) + " FA/cm2";

 {
 result=0
    float convertedNumber = Float.parseFloat(sMPNValue)
    if (sMPNValue.indexOf('.')>-1)
      decimalPart = convertedNumber - (convertedNumber as int)
      /* 4 % 2 = 0 and 5 % 2 = 1 if quotient 4/2=2=0 (integer) and 5/2=2.5=1 (float) */
      /* Math.floor rounds a number DOWN to the nearest integer */
      /* Math.ceil rounds a number UP to the nearest integer */      
      result=(convertedNumber as int) % 2 == 0 && decimalPart < 0.6 && decimalPart >=0.5 ? Math.floor(convertedNumber) as int:
             (convertedNumber as int) % 2 != 0 && decimalPart < 0.6 && decimalPart >=0.5 ? Math.ceil(convertedNumber) as int:
             Math.round(convertedNumber) as int
    }


/*
  TM=APHA9223_B_DQM3_W
  PL=APHA9223_B_Calculation_DQM3_W
  MPNValue is Text type
  Parameter=TotalColiform(Final Value)
  Type=Final Value
*/
def result="";
def tempRes="";
def prevresult=0;
def sMPNValueReplaced="";
def bcomma=false;
/* Declare parameters to get values from replicates samples */
def DestinationPFC = 'TotalColiform';
def DestinationP = 'MPNValue';
def DestinationPTypeF = 'Final Value';
def sdidataitem = SGS.getSDIDataitem('labvantage',${primary:s_sampleid},${paramlistid},""+${paramlistversionid},${variantid}, ""+${dataset}, DestinationPFC, DestinationPTypeF, 'max');
if (SGS.checkResults('labvantage',sdidataitem, '#','#','#','#', DestinationP, DestinationPTypeF, 'max', true))
{
  def sMPNValue=SGS.getTextResults('labvantage', sdidataitem,, '#','#','#','#', DestinationP, DestinationPTypeF, 'max', true)[0]
  if (sMPNValue.indexOf(',')>-1) {
    bcomma=true
    sMPNValueReplaced = sMPNValue.replaceAll(",", ".") }
  else {
    sMPNValueReplaced = sMPNValue }
  if (sMPNValueReplaced.isNumber())
    {
    float convertedNumberMPN = Float.parseFloat(sMPNValueReplaced)
    if (sMPNValueReplaced.indexOf('.')>-1)
      prevresult = (${DF;Final Value}*convertedNumberMPN).round(1)
    else
      prevresult = Math.round(${DF;Final Value}*convertedNumberMPN) as int
    }
  else
    prevresult=${TotalColiform;Standard}    
}
if (bcomma)
  { tempRes=prevresult.toString()
  String tempRes2 = tempRes.replaceAll(".", ",")
  result=tempRes2 }
else
  { result=prevresult }
return result


if (!bResult)
	{
	result=MaxSamVol;
	}
return result


/* OLD VERSION */
/*
  TM=APHA9223_B_DQM3_W
  PL=APHA9223_B_Calculation_DQM3_W
  MPNValue is Text type
  Parameter=TotalColiform(Final Value)
  Type=Final Value
*/
def result="";
/* Declare parameters to get values from replicates samples */
def DestinationPFC = 'TotalColiform';
def DestinationP = 'MPNValue';
def DestinationPTypeF = 'Final Value';
def sdidataitem = SGS.getSDIDataitem('labvantage',${primary:s_sampleid},${paramlistid},""+${paramlistversionid},${variantid}, ""+${dataset}, DestinationPFC, DestinationPTypeF, 'max');
if (SGS.checkResults('labvantage',sdidataitem, '#','#','#','#', DestinationP, DestinationPTypeF, 'max', true))
{
 def sMPNValue=SGS.getTextResults('labvantage', sdidataitem,, '#','#','#','#', DestinationP, DestinationPTypeF, 'max', true)[0]
 /* String sMPNValueReplaced = sMPNValue.replaceAll(",", ".") */
 if (sMPNValue.isNumber())
    {
    result=0
    float convertedNumberMPN = Float.parseFloat(sMPNValue)
    if (sMPNValue.indexOf('.')>-1)
      result = (${DF;Final Value}*convertedNumberMPN).round(1)
    else
    if (sMPNValue.indexOf(',')>-1)
      result = (${DF;Final Value}*convertedNumberMPN).round(1)
    else
      result = Math.round(${DF;Final Value}*convertedNumberMPN) as int
    }
 else
    result=${TotalColiform;Standard}    
}
return result