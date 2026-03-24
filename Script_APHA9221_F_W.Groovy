/* TM=APHA9221_F_W */
/* PL=APHA9221_F_EC_Calculation_W */
/* Parameter=EscheriachiaColi (Standard) */
def result="";
def sdidataitem = SGS.getSDIDataitem('labvantage',${primary:s_sampleid},${paramlistid},""+${paramlistversionid},${variantid}, ""+${dataset}, 'EscheriachiaColi','Standard', 'max');
if (SGS.checkResults('labvantage',sdidataitem, '#','#','#','#', 'MPNValue', 'Final Value', 'max', true))
{
  def sMPNValue=SGS.getTextResults('labvantage', sdidataitem,, '#','#','#','#', 'MPNValue', 'Final Value', 'max', true)[0]
  result=0;
  if (sMPNValue.isNumber())
    {  
	float convertedNumber = Float.parseFloat(sMPNValue)
	result=(convertedNumber/${LowestDilution;Final Value})*10
	}
  else
    {
    if (sMPNValue.indexOf('<')>-1)
       {
       result=0
       }
    else
    if (sMPNValue.indexOf('>')>-1)
       {
       result=9999
       }
    }
}
return result



/* TM=APHA9221_F_W */
/* PL=APHA9221_F_EC_Calculation_W */
/* Parameter=EscheriachiaColi (Standard) */
def result=0;
if (${MPNValue;Final Value}==0)
{
  result=0
}
else
if (${LowestDilution;Final Value}==0)
{
  result=0
}
else
{
 result=(${MPNValue;Final Value}/${LowestDilution;Final Value})*10
}
return result


/* TM=APHA9221_F_W */
/* PL=APHA9221_F_EC_Calculation_W */
/* Parameter=EscheriachiaColi (Final Value) */
[EscheriachiaColi;Standard]


/* TM=APHA9221_F_W */
/* PL=APHA9221_F_EC_Calculation_W */
/* Parameter=EscheriachiaColi (Adjusted RL) */
([EscheriachiaColi;Final Value;max;ParamLimit_1:RL]*10)/[LowestDilution;Final Value]