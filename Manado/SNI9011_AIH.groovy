// Get SDI from the parameter Multiplier:Final Value
def SDIDataItem = SGS.getSDIDataitem('labvantage', ${primary:s_sampleid}, ${paramlistid}, ${paramlistversionid}.toString(), ${variantid}, ${dataset}.toString(), '*', '*', 'max');

// Variable to hold the replicate range number
int InitialRepNumber = 1;
int FinalRepNumber = 16;

// Variable to hold the Sum of values
int ScoreSum = 0;

for (int RepNum = InitialRepNumber; RepNum <= FinalRepNumber; RepNum++)
{
	// Check if the current replicate is not empty
	if (SGS.checkResults('labvantage', SDIDataItem, ${paramlistid}, ${paramlistversionid}.toString(), ${variantid}, ${dataset}.toString(), 'Score', 'Final Value', RepNum.toString(), true))
	{
		// Sum the current replicate to the ScoreSum variable
		ScoreSum += SGS.getNumericResults('labvantage', SDIDataItem, ${paramlistid}, ${paramlistversionid}.toString(), ${variantid}, ${dataset}.toString(), 'Score', 'Final Value', RepNum.toString(), true)[0];
	}
}

return ScoreSum;


if (${TBAScore;Final Value} <= 2) {
    return "Safe Workplace Conditions"
} else if (${TBAScore;Final Value} >= 3 && ${TBAScore;Final Value} <= 6) {
    return "Need Further Observation"
} else if (${TBAScore;Final Value} >= 7) {
    return "Dangerous"
}




/*
  PL: SNI9011_AIH 
  Parameter: TBAScore
  Type: Final Value (Numeric Calc)
  Author: DQM team
  Purpose: 
  Date: 2025-01-27
*/

// Get SDI from the parameter Multiplier:Final Value
def SDIDataItem = SGS.getSDIDataitem('labvantage', ${primary:s_sampleid}, ${paramlistid}, ${paramlistversionid}.toString(), ${variantid}, ${dataset}.toString(), '*', '*', 'max');

// Variable to hold the replicate range number
int InitialRepNumber = 1;
int FinalRepNumber = 16;

// Variable to hold the Sum of values
int ScoreSum = 0;
def bstatus = true;

if (${Activity;Final Value} == "" || ${Activity;Final Value} == null)
  bstatus = false
else
    {for (int RepNum = InitialRepNumber; RepNum <= FinalRepNumber; RepNum++)
        {
	    // Check if the current replicate is not empty
	    if (SGS.checkResults('labvantage', SDIDataItem, ${paramlistid}, ${paramlistversionid}.toString(), ${variantid}, ${dataset}.toString(), 'Score', 'Final Value', RepNum.toString(), true))
	        {
		    // Sum the current replicate to the ScoreSum variable
		    ScoreSum += SGS.getNumericResults('labvantage', SDIDataItem, ${paramlistid}, ${paramlistversionid}.toString(), ${variantid}, ${dataset}.toString(), 'Score', 'Final Value', RepNum.toString(), true)[0];
	        }
        }
    }
return (!bstatus?'':ScoreSum)




/*
  PL: SNI9011_AIH 
  Parameter: TBAScoreConclusion
  Type: Final Value (Text Calc)
  Author: DQM team
  Purpose: 
  Date: 2025-01-27
*/
// Variable to hold the Sum of values
def TextScore = "";
def bstatus = true;

if (${Activity;Final Value} == "" || ${Activity;Final Value} == null)
  bstatus = false
else {
    if (${TBAScore;Final Value} <= 2) 
        TextScore = "Safe Workplace Conditions"
    else if (${TBAScore;Final Value} >= 3 && ${TBAScore;Final Value} <= 6) 
        TextScore = "Need Further Observation"
    else if (${TBAScore;Final Value} >= 7) 
        TextScore = "Dangerous"
    }
return (!bstatus?'':TextScore)