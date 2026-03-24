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
    result=0
    float convertedNumber = Float.parseFloat(sMPNValue)
    if (sMPNValue.indexOf('.')>-1)
      decimalPart = convertedNumber - (convertedNumber as int)
      /* 4 % 2 = 0 and 5 % 2 = 1 if quotient 4/2=2=0 (integer) and 5/2=2.5=1 (float) */
      /* Math.floor rounds a number DOWN to the nearest integer */
      /* Math.ceil rounds a number UP to the nearest integer */
      result=(convertedNumber as int) % 2 == 0 && decimalPart < 0.6 && decimalPart >=0.5 ? Math.floor(convertedNumber) as int:
             (convertedNumber as int) % 2 != 0 && decimalPart < 0.6 && decimalPart >=0.5 ? Math.ceil(convertedNumber) as int:
             Math.round(convertedNumber) as int
    }
  else
    {
    if (sMPNValueReplaced.indexOf('<')>-1)
       result="<2"
    else
    if (sMPNValueReplaced.indexOf('>')>-1)
       result=">1600"
    else
       result="Not Valid"
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
    float convertedNumber = Float.parseFloat(sMPNValue)
    if (sMPNValue.indexOf('.')>-1)
      decimalPart = convertedNumber - (convertedNumber as int)
      /* 4 % 2 = 0 and 5 % 2 = 1 if quotient 4/2=2=0 (integer) and 5/2=2.5=1 (float) */
      /* Math.floor rounds a number DOWN to the nearest integer */
      /* Math.ceil rounds a number UP to the nearest integer */      
      result=(convertedNumber as int) % 2 == 0 && decimalPart < 0.6 && decimalPart >=0.5 ? Math.floor(convertedNumber) as int:
             (convertedNumber as int) % 2 != 0 && decimalPart < 0.6 && decimalPart >=0.5 ? Math.ceil(convertedNumber) as int:
             Math.round(convertedNumber) as int
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


/*
  TM=APHA9223_B_DQM3_W
  PL=APHA9223_B_Calculation_DQM3_W
  MPNValue is Text type
  Parameter=TotalColiform(Final Value)
  Type=Final Value
*/
def result="";
/* Declare parameters to get values from replicates samples */
def DestinationPFC = 'TotalColiform';
def DestinationP = 'MPNValue';
def DestinationPTypeF = 'Final Value';
def sdidataitem = SGS.getSDIDataitem('labvantage',${primary:s_sampleid},${paramlistid},""+${paramlistversionid},${variantid}, ""+${dataset}, DestinationPFC, DestinationPTypeF, 'max');
if (SGS.checkResults('labvantage',sdidataitem, '#','#','#','#', DestinationP, DestinationPTypeF, 'max', true))
{
 def sMPNValue=SGS.getTextResults('labvantage', sdidataitem,, '#','#','#','#', DestinationP, DestinationPTypeF, 'max', true)[0]
 /* String sMPNValueReplaced = sMPNValue.replaceAll(",", ".") */
 if (sMPNValue.isNumber())
    {
    result=0
    float convertedNumberMPN = Float.parseFloat(sMPNValue)
    if (sMPNValue.indexOf('.')>-1)
      result = (${DF;Final Value}*convertedNumberMPN).round(1)
    else
    if (sMPNValue.indexOf(',')>-1)
      result = (${DF;Final Value}*convertedNumberMPN).round(1)
    else
      result = Math.round(${DF;Final Value}*convertedNumberMPN) as int
    }
 else
    result=${TotalColiform;Standard}    
}
return result


/*
  TM=APHA9223_B_DQM3_W
  PL=APHA9223_B_Calculation_DQM3_W
  Parameter=TotalColiform
  Type=Final Value
*/
/*
  TM=APHA9223_B_TC_W
  PL=APHA9223_B_Calculation_TC_W
  MPNValue is Text type
  Parameter=TotalColiform(Final Value)
  def result="NOT VALID"
*/
def result="";
def bDecimal=false;
/* Declare parameters to get values from replicates samples */
def DestinationP = 'TotalColiform';
def DestinationPTypeF = 'Final Value';
def DestinationPTypeS = 'Standard';
def sdidataitem = SGS.getSDIDataitem('labvantage',${primary:s_sampleid},${paramlistid},""+${paramlistversionid},${variantid}, ""+${dataset}, DestinationP , DestinationPTypeF, 'max');
if (SGS.checkResults('labvantage',sdidataitem, '#','#','#','#', DestinationP, DestinationPTypeF, 'max', true))
{
  def sMPNValue=SGS.getTextResults('labvantage', sdidataitem,, '#','#','#','#', DestinationP, DestinationPTypeS, 'max', true)[0]
  if (sMPNValue == 'NOT VALID')
       result=${TotalColiform;Standard}
  else
  if (sMPNValue.indexOf('<')>-1)
       result=${TotalColiform;Standard}
  else
  if (sMPNValue.indexOf('>')>-1)
       result=${TotalColiform;Standard}
  else
      result=${DF;Final Value}*${TotalColiform;Standard}
}
return result

