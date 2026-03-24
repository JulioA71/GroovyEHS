/*
  replace "." by ","
*/
def result=0;
def value=0;
def arrList=[11, 12, 13, 14];
def strValue="";
if (${DQMParam2;Final Value}>=0) {
  value=${DQMParam2;Final Value}
  arrList.add(value)
  result=arrList[3] + arrList[4] }
else {
  result=999 }
strValue=result.toString() 
if (strValue.indexOf('.')>-1) {
  /* ResultReplaced=strValue */
  String ResultReplaced=strValue.replaceAll(".", ",") 
  /* ResultReplaced=strValue.Math.BigDecimal.replaceAll(".", ",") */
  }
else
  String ResultReplaced=strValue
return ResultReplaced
