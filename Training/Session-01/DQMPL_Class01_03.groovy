/*
    PL=DQMPL_Class01_03
    Paramater=DQMTxt1 (Final Value)
*/
result=""
if (${DQMParam1;Final Value} == "Leah" || ${DQMParam1;Final Value} == "Valor" || ${DQMParam1;Final Value} == "Grace")
  result="Asia"
else if (${DQMParam1;Final Value} == "Nerea" || ${DQMParam1;Final Value} == "Javier")
  result="Europe"
else
  result="Latam"
return result


/*
    PL=DQMPL_Class01_03
    Paramater=DQMTxt2 (Final Value)
*/
switch(${DQMParam1;Final Value}) 
{
case ['Leah','Valor','Grace']: 
    result = "Asia"
    break
case ['Nerea','Javier']: 
    result = "Europe"
    break
default:
    result = "Latam"
    break
}
return result


switch(${DQMParam1;Final Value}) 
{
case ['Leah','Valor','Grace']: 
    result = "Asia"
    break
case ['Nerea','Javier']: 
    result = "Europe"
    break
case ['Julio']: 
    result = Math.round(${DQMParam2;Final Value}*${DF;Final Value})
    break
default:
    result = "Latam"
    break
}
return result 



/*
    PL=DQMPL_Class01_03
    Paramater=DQMTxt3 (Final Value)
*/
def result="";
def names=${DQMParam1;Final Value};
if (names in ['Leah','Valor','Grace'])
  result="Asia"
else if (names in ['Nerea','Javier'])
  result="Europe"
else
  result="Latam"
return result


/*
    PL=DQMPL_Class01_03
    Paramater=DQMTxt4 (Final Value)
*/
def result="";
if (${DQMTxt2;Final Value}=="Asia" && ${DQMParam2;Final Value} == 1)
  result="Group " + Math.round(${DQMParam2;Final Value})
else if (${DQMTxt2;Final Value}=="Europe" && ${DQMParam2;Final Value} == 2)
  result="Group " + Math.round(${DQMParam2;Final Value})
else if (${DQMTxt2;Final Value}=="Latam" && ${DQMParam2;Final Value} == 3)
  result="Group " + Math.round(${DQMParam2;Final Value})
else
  result=""
return result


/*
    PL=DQMPL_Class01_03
    Paramater=DQMTxt5 (Final Value)
*/
def result="";
def bstatus=false;
def number=0;
if (${DQMParam2;Final Value} >= 1 && ${DQMParam2;Final Value} <= 3) {
  bstatus=true
  number=1 }
else {
  bstatus=false
  number=0 }
if (bstatus)
  result="Pass " + number
else
  result="Fail " + number
return result 
