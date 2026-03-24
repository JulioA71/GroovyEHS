/*
	TM=APHA9222_B_W
	PL=APHA9222_B_TC_Calculation_W
	Parameter=ColonyCount
*/

if([VerifiedColonies;Final Value]=0,0,([APHA9222_B_TC_Filtration_W;*;*;max|DarkPinkMetallicSheenColonies;Final Value;max]+[APHA9222_B_TC_Filtration_W;*;*;max|RedColonies;Final Value;max])*[TotalColoniesVerification;Final Value]/[VerifiedColonies;Final Value])

def result=0;
if (${APHA9222_B_TC_Filtration_W;*;*;max|TotalPresumptiveColonies;Final Value;max}==0)
{
  result=0
}  
else
if (${VerifiedColonies;Final Value}>0 && ${TotalColoniesVerification;Final Value}>0)
{
  result=(${VerifiedColonies;Final Value}/${TotalColoniesVerification;Final Value})*${APHA9222_B_TC_Filtration_W;*;*;max|TotalPresumptiveColonies;Final Value;max}
}
else
{
  result=0
}
return result



/*
	TM=APHA9222_B_W
	PL=APHA9222_B_TC_Calculation_W
	Parameter=TotalColiform
	
	(${APHA9222_B_TC_Confirm_W;*;*;max|ColonyConfirmedCount;Final Value;max}*100)/${APHA9222_B_TC_Filtration_W;*;*;max|SampleVolumeFiltered;Final Value;max}
	[ColonyConfirmedCount;Final Value]/[APHA9222_B_TC_Filtration_W;*;*;max|SampleVolumeFiltered;Final Value;max]*100
*/

def result=0;
if (${APHA9222_B_TC_Filtration_W;*;*;max|SampleVolumeFiltered;Final Value;max}>0)
{
  result=(${ColonyCount;Final Value}/${APHA9222_B_TC_Filtration_W;*;*;max|SampleVolumeFiltered;Final Value;max})*100
}
else
{
  result=0
}
return result