/*
  PL=DQMPL_Class02_02
  Parameter=DQMTxt1 (Final Value)
  Type=Numeric Calc
  sum everyone array
*/
def sumNum=0;
def valueNum=0;
def arrList=[11, 12, 13, 14];
if (${DQMParam1;Final Value}>=0)
  valueNum=${DQMParam1;Final Value}
else {
  valueNum=0 }
arrList.add(valueNum)
for(i=0; i < arrList.size(); i++) {
  if (arrList[i] >= 14) 
    sumNum=sumNum+arrList[i] 
}
return sumNum


def sumNum=0;
def valueNum=0;
def arrList=[11, 12, 13, 14];
if (${DQMParam1;Final Value}>=0)
  valueNum=${DQMParam1;Final Value}
else {
  valueNum=0 }
arrList.add(valueNum)
for(i=0; i < arrList.size(); i++) {
  sumNum=sumNum+arrList[i]
}
return sumNum


/*
  PL=DQMPL_Class02_02
  Parameter=DQMTxt2 (Final Value)
  Type=Numeric Calc
  sum() array
*/
def sumNum=0;
def valueNum=0;
def arrList=[11, 12, 13, 14];
if (${DQMParam1;Final Value}>=0)
  valueNum=${DQMParam1;Final Value}
else {
  valueNum=0 }
arrList.add(valueNum);
sumNum=arrList.sum();
return sumNum


/*
  PL=DQMPL_Class02_02
  Parameter=DQMTxt3 (Final Value)
  Type=Numeric Calc
  Min() array
*/
def minNum=0;
def valueNum=0;
def arrList=[11, 12, 13, 14];
if (${DQMParam1;Final Value}>=0)
  valueNum=${DQMParam1;Final Value}
else {
  valueNum=0 }
arrList.add(valueNum);
minNum=arrList.min();
return minNum


/*
  PL=DQMPL_Class02_02
  Parameter=DQMTxt4 (Final Value)
  Type=Numeric Calc
  Max() array
*/
def maxNum=0;
def valueNum=0;
def arrList=[11, 12, 13, 14];
if (${DQMParam1;Final Value}>=0)
  valueNum=${DQMParam1;Final Value}
else {
  valueNum=0 }
arrList.add(valueNum);
maxNum=arrList.max();
return maxNum
