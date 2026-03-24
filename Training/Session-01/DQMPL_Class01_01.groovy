/*
    PL=DQMPL_Class01_01
    Parameter=DQMSum1 (Final Value)
*/
def result=0;
result=${DQMParam1;Final Value}+${DQMParam2;Final Value};
return result


/*
    PL=DQMPL_Class01_02
    Parameter=DQMSum2 (Final Value)
*/
def result=0;
result = ((float)${DQMParam1;Final Value}+${DQMParam2;Final Value}).round(1);
return result


/*
    PL=DQMPL_Class01_03
    Parameter=DQMSum3 (Final Value)
*/
def result=0;
/* result=Math.round(${DQMParam1;Final Value}+${DQMParam2;Final Value}) as int; */
result=Math.round(${DQMParam1;Final Value}+${DQMParam2;Final Value});
return result
