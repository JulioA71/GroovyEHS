/*
    Description:    Calculation for APHA9223_B_Calculation_EC_W
    Created by:     DQM team
    Created:        2024.03.12
    Parameter:      EscheriachiaColi (Standard)
    Modification log (add below)
    Date            Name      Description

*/
def result=0;
def bResult=false;
def sMPNValue=${MPNValue;Final Value};
if (sMPNValue.indexOf('<') >-1) {
    bResult=true
    result=0.1 }
else if (sMPNValue.indexOf('>') >-1) {
    bResult=true
    result=99999 }
else if (sMPNValue == "NOT VALID") {
    bResult=true
    result=999999 }
else {
    bResult=true
    result=sMPNValue }
return (!bResult?'':result)



/*
    Author: Nerea / Julio
    PL=APHA9223_B_Calculation_DQM3_W
    Parameter=Escherichia Coli (Final Value)
    Date: 2024.02.29
    
    Updated: 2024.03.01
    Remarks: we can select which calculation wants performs (TypeRounding)  Text(Calc)
    Updated: 2024.03.12
    Remarks: Change to Numeric (Calc) for Escheriachia Coli of type Standard/Final Value to allow uses LimitRules or Specifications
*/
def result=0;
def prevresult=0;
def tempRes="";
def bcomma=false;
def bResult=false;
// Declare parameters to get values from replicates samples
def DestinationPFC = 'EscheriachiaColi';
def DestinationP = 'MPNValue';
def DestinationPTypeF = 'Final Value';
def sdidataitem = SGS.getSDIDataitem('labvantage',${primary:s_sampleid},${paramlistid},""+${paramlistversionid},${variantid}, ""+${dataset}, DestinationPFC, DestinationPTypeF, 'max');
if (SGS.checkResults('labvantage',sdidataitem, '#','#','#','#', DestinationP, DestinationPTypeF, 'max', true))
{
  def sMPNValueText=SGS.getTextResults('labvantage', sdidataitem,, '#','#','#','#', DestinationP, DestinationPTypeF, 'max', true)[0]
  def nMPNValueNum=${EscheriachiaColi;Standard} 
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
    prevresult=((nMPNValueNum) * ${DF;Final Value})
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
    PL=APHA9223_B_Calculation_EC_W
    Parameter=Escherichia Coli (Final Value)
    Date: 2024.02.29
    
    Updated: 2024.03.01
    Remarks: we can select which calculation wants performs (TypeRounding)  Text(Calc)
    Updated: 2024.03.12
    Remarks: Change to Numeric (Calc) for Escheriachia Coli of type Standard/Final Value to allow uses LimitRules or Specifications
    Updated: 2024.08.22 by Javier / Julio
    Remarks: Use DF when the MPNValue >
*/
def result=0;
def prevresult=0;
def tempRes="";
def bcomma=false;
def bResult=false;
// Declare parameters to get values from replicates samples
def DestinationPFC = 'EscheriachiaColi';
def DestinationP = 'MPNValue';
def DestinationPTypeF = 'Final Value';
def sdidataitem = SGS.getSDIDataitem('labvantage',${primary:s_sampleid},${paramlistid},""+${paramlistversionid},${variantid}, ""+${dataset}, DestinationPFC, DestinationPTypeF, 'max');
if (SGS.checkResults('labvantage',sdidataitem, '#','#','#','#', DestinationP, DestinationPTypeF, 'max', true))
{
  def sMPNValueText=SGS.getTextResults('labvantage', sdidataitem,, '#','#','#','#', DestinationP, DestinationPTypeF, 'max', true)[0]
  def nMPNValueNum=${EscheriachiaColi;Standard} 
  if ((sMPNValueText.indexOf('<')>-1) || ((sMPNValueText.indexOf('>')>-1) && (${DF;Final Value} == 1))) {
    bResult=true
    prevresult=nMPNValueNum }
  else if ((sMPNValueText.indexOf('>')>-1) || ((sMPNValueText.indexOf('>')>-1) && (${DF;Final Value} > 1))) {
    bResult=true
    String sMPNValueReplaced = sMPNValueText.replaceAll(">", "")
    float convertedNumber = Float.parseFloat(sMPNValueReplaced)    
    prevresult='>'+((convertedNumber).round(0) * ${DF;Final Value}) }
  else if (sMPNValueText == 'NOT VALID') {
    bResult=true
    prevresult=nMPNValueNum }
  else {
    if (sMPNValueText.indexOf(',')>-1) {
      bcomma=true }
    bResult=true
    prevresult=((nMPNValueNum) * ${DF;Final Value})
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


def CalcResult = 0;
def bstatus = false;
double MDETaxaNum = ${TaxaNumber;Final Value};

if (MDETaxaNum > 0) {
    CalcResult=Math.log(MDETaxaNum);
    bstatus=true;
}

 String sMPNValueReplaced = sMPNValue.replaceAll(",", ".")
 if (sMPNValueReplaced.isNumber())
    {
    result=0
    float convertedNumber = Float.parseFloat(sMPNValueReplaced)


if (SGS.checkResults('labvantage', SDIDataItem, ${paramlistid}, ${paramlistversionid}.toString(), ${variantid}, 'max', 'DF', 'Final Value', 'max', true))
{  
  // reading array for CA
  for(i=0; i < arrListCa.size(); i++) {
  tempArrayCa=arrListCa[i]
  if (tempArrayCa.indexOf(';')>-1) {
    SourcePL=(tempArrayCa.substring(0,tempArrayCa.indexOf(';')));
    SourceParamID=(tempArrayCa.substring(tempArrayCa.indexOf(';')+1));

    ExternalValue = SGS.getNumericResults('labvantage', SDIDataItem, SourcePL, '*', '*', 'max', SourceParamID, 'Final Value', 'max', false);
    if (ExternalValue.length > 0)
      {
      CalcResultCa = ExternalValue[0]
      bResultCa=true
      break
      }
    }
  }



  $G{/*
    Author: Nerea / Julio
    PL=APHA9223_B_Calculation_EC_W
    Parameter=Escherichia Coli (Final Value)
    Date: 2024.02.29
    
    Updated: 2024.03.01
    Remarks: we can select which calculation wants performs (TypeRounding)  Text(Calc)
    Updated: 2024.03.12
    Remarks: Change to Numeric (Calc) for Escheriachia Coli of type Standard/Final Value to allow uses LimitRules or Specifications
    Updated: 2024.08.22 by Javier / Julio
    Remarks: Use DF when the MPNValue >
*/
def result=0;
def prevresult=0;
def tempRes="";
def bcomma=false;
def bResult=false;
// Declare parameters to get values from replicates samples
def DestinationPFC = 'EscheriachiaColi';
def DestinationP = 'MPNValue';
def DestinationPTypeF = 'Final Value';
def sdidataitem = SGS.getSDIDataitem('labvantage',${primary:s_sampleid},${paramlistid},""+${paramlistversionid},${variantid}, ""+${dataset}, DestinationPFC, DestinationPTypeF, 'max');
if (SGS.checkResults('labvantage',sdidataitem, '#','#','#','#', DestinationP, DestinationPTypeF, 'max', true))
{
  def sMPNValueText=SGS.getTextResults('labvantage', sdidataitem,, '#','#','#','#', DestinationP, DestinationPTypeF, 'max', true)[0]
  def nMPNValueNum=${EscheriachiaColi;Standard} 
  if ((sMPNValueText.indexOf('<')>-1) || ((sMPNValueText.indexOf('>')>-1) && (${DF;Final Value} == 1))) {
    bResult=true
    prevresult=nMPNValueNum }
  else if ((sMPNValueText.indexOf('>')>-1) || ((sMPNValueText.indexOf('>')>-1) && (${DF;Final Value} > 1))) {
    bResult=true
    String sMPNValueReplaced = sMPNValueText.replaceAll(">", "")
    float convertedNumber = Float.parseFloat(sMPNValueReplaced) 
    prevresult='>'+((convertedNumber).round(0) * ${DF;Final Value}) }
  else if (sMPNValueText == 'NOT VALID') {
    bResult=true
    prevresult=nMPNValueNum }
  else {
    if (sMPNValueText.indexOf(',')>-1) {
      bcomma=true }
    bResult=true
    prevresult=((nMPNValueNum) * ${DF;Final Value})
    }
}
if (bResult) {
  result=prevresult
  }
if (bcomma)  { 
  tempRes=prevresult.toString()
  result=tempRes.replaceAll("\\.", ",")
  }
return (!bResult?'':result)}