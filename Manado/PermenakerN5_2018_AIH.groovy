def CalcResult = 0;
def bstatus = true;

def NumRep = [2, 5, 1, 3, 4]

for (int i = 0; i < NumRep .size(); i++) {
    CalcResult = CalcResult  + NumRep [i]
}

return (!bstatus?'':CalcResult)

// Get SDI from the parameter Multiplier:Final Value
def SDIDataItem = SGS.getSDIDataitem('labvantage', ${primary:s_sampleid}, ${paramlistid}, ${paramlistversionid}.toString(), ${variantid}, ${dataset}.toString(), '*', '*', 'max');

// Array of specific replicate numbers to sum
int specificRepNumbers = {1, 7, 13, 19, 25};

// Variable to hold the Sum of values
int ScoreSum = 0;

for (int RepNum : specificRepNumbers) {
    // Check if the current replicate is not empty
    if (SGS.checkResults('labvantage', SDIDataItem, ${paramlistid}, ${paramlistversionid}.toString(), ${variantid}, ${dataset}.toString(), 'Score', 'Final Value', Integer.toString(RepNum), true)) {
        // Sum the current replicate to the ScoreSum variable
        ScoreSum += SGS.getNumericResults('labvantage', SDIDataItem, ${paramlistid}, ${paramlistversionid}.toString(), ${variantid}, ${dataset}.toString(), 'Score', 'Final Value', Integer.toString(RepNum), true)[0];
    }
}

return ScoreSum;




/*
  PL: PermenakerNo5_2018_AIH 
  Parameter: RoleImperatives_Score
  Type: Final Value (Numeric Calc)
  Author: DQM team
  Purpose: Sum of Parameter Score
  Date: 2025-01-27
*/
// Declare variable general-purpose
def CalcResult = 0;
def bstatus = false;

// Variable declaration - modify the parameters below with the source (where the data comes from) and destination (where the data goes to)
String ParamListID = 'PermenakerNo5_2018_AIH';   // define the parameter list
String ParamID = 'Score';       // define the parameter id
String ParamType = 'Final Value';           // define the parameter type
def SampleType = ${primary:qcsampletype};   // define the sample type

// Variable to hold data related to the SDI
def SDIDataItem=SGS.getSDIDataitem('labvantage', ${primary:s_sampleid}, ParamListID, '*', ${variantid}, ${dataset}.toString(), ParamID, ParamType, 'max');

// Array of specific replicate numbers to sum
def NumRep = [1, 7, 13, 19, 25]

for (int i = 0; i < NumRep .size(); i++) {
    def MDENumber=SGS.getNumericResults('labvantage',SDIDataItem, SDIDataItem.paramlistid, SDIDataItem.paramlistversionid, SDIDataItem.variantid, ""+SDIDataItem.dataset, ParamID , ParamType, Integer.toString(NumRep[i]), true)
    if (MDENumber.length > 0) {
        if (MDENumber[0] > 0) {            
            bstatus=true;
            CalcResult=CalcResult+MDENumber[0];
        }
    }
}
return (!bstatus?'':CalcResult)




def CalcResult = 0;
def bstatus = true;

if (${Activity;Final Value} == "" || ${Activity;Final Value} == null)
  bstatus = false
else
  CalcResult=${DQMParam1;Final Value}+${DQMParam2;Final Value}
return (!bstatus?'':CalcResult)
