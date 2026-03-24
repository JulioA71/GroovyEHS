/*
  PL=DQMPL_Class02_01
  Parameter=DQMTxt1 (Final Value)
  Type=Numeric Calc
  display the value from the array depending on DQMParam1
*/
def result=0;
def pos=0;
def arrList=[11, 12, 13, 14];
if (${DQMParam1;Final Value}>=0 && ${DQMParam1;Final Value}<=3) {
  pos=${DQMParam1;Final Value} as int
  result=arrList[pos] }
else
  result=999
return result


/*
  PL=DQMPL_Class02_01
  Parameter=DQMTxt2 (Final Value)
  Type=Numeric Calc
  display the sum of value from the array (pos 3 and 4)
*/
def result=0;
def value=0;
def arrList=[11, 12, 13, 14];
if (${DQMParam2;Final Value}>=0) {
  value=${DQMParam2;Final Value}
  arrList.add(value)
  result=arrList[3] + arrList[4] }
else
  result=999
return result


/*
  PL=DQMPL_Class02_01
  Parameter=DQMTxt3 (Final Value)
  Type=Text Calc
  replace "." by ","
*/
def result=0;
def value=0;
def arrList=[11, 12, 13, 14];
def strValue="";
String ResultReplaced=""
if (${DQMParam2;Final Value}>=0) {
  value=${DQMParam2;Final Value}
  arrList.add(value)
  result=arrList[3] + arrList[4] }
else {
  result=999 }
strValue=result.toString() 
if (strValue.indexOf('.')>-1)
  ResultReplaced=strValue.replaceAll("\\.", ",")
else
  ResultReplaced=strValue
return ResultReplaced
