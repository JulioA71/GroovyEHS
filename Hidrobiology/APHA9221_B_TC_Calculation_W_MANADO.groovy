/*
    Author: Nerea / Julio
    PL=APHA9221_B_TC_Calculation_W
    Parameter=TotalColiform (Final Value)
    Lab: MANADO
    Date: 2024.02.29
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
        decimalPart = convertedNumberMPN - (convertedNumberMPN as int)
        // 4 % 2 = 0 and 5 % 2 = 1 if quotient 4/2=2=0 (integer) and 5/2=2.5=1 (float)
        // Math.floor rounds a number DOWN to the nearest integer
        // Math.ceil rounds a number UP to the nearest integer
        prevresult=(convertedNumberMPN as int) % 2 == 0 && decimalPart < 0.6 && decimalPart >=0.5 ? Math.floor(convertedNumberMPN*(10/${LowestDilution;Final Value})) as int:
                   (convertedNumberMPN as int) % 2 != 0 && decimalPart < 0.6 && decimalPart >=0.5 ? Math.ceil(convertedNumberMPN*(10/${LowestDilution;Final Value})) as int:
                    Math.round(convertedNumberMPN*(10/${LowestDilution;Final Value})) as int }
      else
        prevresult = Math.round ((convertedNumberMPN)*(10/${LowestDilution;Final Value})) as int }
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