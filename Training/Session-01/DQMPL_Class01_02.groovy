/*
    PL=DQMPL_Class01_02
    Parameter=DQMSum1 (Final Value)
*/
sum( [DQMParam1;Final Value],[DQMParam2;Final Value],[DQMParam3;Final Value],[DQMParam4;Final Value])


/*
    PL=DQMPL_Class01_02
    Parameter=DQMSum2 (Final Value)
*/
def result=0;
result=${DQMParam1;Final Value}+${DQMParam2;Final Value}+${DQMParam3;Final Value}+${DQMParam4;Final Value}
return result

def result=0;
def value1=0;
def sdidataitem = SGS.getSDIDataitem('labvantage',${primary:s_sampleid},${paramlistid},""+${paramlistversionid},${variantid}, ""+${dataset}, 'DQMSum2','Final Value', 'max') 
if (SGS.checkResults('labvantage',sdidataitem, '#','#','#', '#', 'DQMParam1', 'Final Value', 'max', true))
{
  value1=SGS.getNumericResults('labvantage', sdidataitem,, '#','#','#', '#', 'DQMParam1', 'Final Value', 'max', true)[0]
  
}
result=value1+${DQMParam2;Final Value}+${DQMParam3;Final Value}+${DQMParam4;Final Value}
return result



/*
    PL=DQMPL_Class01_02
    Parameter=DQMTxt1 (Final Value)
*/
def result="";
if (${DQMSum2;Final Value} >= 10)
  result="Green"
else if (${DQMSum2;Final Value} >= 5)
  result="Yellow"
else
  result="Red"
return result


/*
    PL=DQMPL_Class01_02
    Parameter=DQMTxt2 (Final Value)
*/
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
