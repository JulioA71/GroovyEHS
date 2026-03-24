/*
  TM=APHA9221_E_W
  PL=APHA9221_E_TF_Calculation_W
  MPNValue is Text type
  Parameter=ThermotolerantFecalColiform
  Type=Standard
*/
def result="";
/* Declare parameters to get values from replicates samples */
def DestinationPFC = 'ThermotolerantFecalColiform';
def DestinationP = 'MPNValue';
def DestinationPTypeF = 'Final Value';
def DestinationPTypeS = 'Standard';
def sdidataitem = SGS.getSDIDataitem('labvantage',${primary:s_sampleid},${paramlistid},""+${paramlistversionid},${variantid}, ""+${dataset}, DestinationPFC, DestinationPTypeS, 'max');
if (SGS.checkResults('labvantage',sdidataitem, '#','#','#','#', DestinationP, DestinationPTypeF, 'max', true))
{
  def sMPNValue=SGS.getTextResults('labvantage', sdidataitem,, '#','#','#','#', DestinationP, DestinationPTypeF, 'max', true)[0]
  String sMPNValueReplaced = sMPNValue.replaceAll(",", ".")
  if (sMPNValueReplaced.isNumber())
    {
    float convertedNumber = Float.parseFloat(sMPNValueReplaced)
    if (sMPNValueReplaced.indexOf('.')>-1)
        result=((convertedNumber/${LowestDilution;Final Value})*10).round(2)
    else
        result=Math.round((convertedNumber/${LowestDilution;Final Value})*10)
   }
  else
    {
    if (sMPNValueReplaced.indexOf('<')>-1)
       result="<1.8"
    else
    if (sMPNValueReplaced.indexOf('>')>-1)
       result=">1600"
    else
       result="Error"
    }
}
return result



/*
  TM=APHA9221_E_W
  PL=APHA9221_E_TF_Calculation_W
  MPNValue is Text type
  Parameter=TotalColiform(Final Value)
  Type=Final Value
*/
def result="";
/* Declare parameters to get values from replicates samples */
def DestinationPFC = 'ThermotolerantFecalColiform';
def DestinationP = 'MPNValue';
def DestinationPTypeF = 'Final Value';
def sdidataitem = SGS.getSDIDataitem('labvantage',${primary:s_sampleid},${paramlistid},""+${paramlistversionid},${variantid}, ""+${dataset}, DestinationPFC, DestinationPTypeF, 'max');
if (SGS.checkResults('labvantage',sdidataitem, '#','#','#','#', DestinationP, DestinationPTypeF, 'max', true))
{
 def sMPNValue=SGS.getTextResults('labvantage', sdidataitem,, '#','#','#','#', DestinationP, DestinationPTypeF, 'max', true)[0]
 String sMPNValueReplaced = sMPNValue.replaceAll(",", ".")
 if (sMPNValueReplaced.isNumber())
    {
    result=0
    float convertedNumber = Float.parseFloat(sMPNValueReplaced)
    if (sMPNValueReplaced.indexOf('.')>-1) 
      result=Math.ceil((convertedNumber/${LowestDilution;Final Value})*10) as int
    else
      result=Math.round((convertedNumber/${LowestDilution;Final Value})*10)
    }
 else
    {
    if (sMPNValueReplaced.indexOf('<')>-1)
      result="<2"
    else
    if (sMPNValueReplaced.indexOf('>')>-1)
      result=">1600"
    }
}
return result
