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


===============
Pruebas anteriores
===============

/*
  TM=APHA9221_E_W
  PL=APHA9221_E_TF_Calculation_W
  MPNValue is Text type
  Parameter=ThermotolerantFecalColiform
  Type=Standard
*/
def result="";
def bDecimal=false;
/* Declare parameters to get values from replicates samples */
def DestinationPFC = 'ThermotolerantFecalColiform';
def DestinationP = 'MPNValue';
def DestinationPTypeF = 'Final Value';
def DestinationPTypeS = 'Standard';
def sdidataitem = SGS.getSDIDataitem('labvantage',${primary:s_sampleid},${paramlistid},""+${paramlistversionid},${variantid}, ""+${dataset}, DestinationPFC, DestinationPTypeS, 'max');
if (SGS.checkResults('labvantage',sdidataitem, '#','#','#','#', DestinationP, DestinationPTypeF, 'max', true))
{
  def sMPNValue=SGS.getTextResults('labvantage', sdidataitem,, '#','#','#','#', DestinationP, DestinationPTypeF, 'max', true)[0]
  if (sMPNValue.isNumber())
    {
    result=0
    float convertedNumber = Float.parseFloat(sMPNValue)
    if (sMPNValue.indexOf('.')>-1)
        result=((convertedNumber/${LowestDilution;Final Value})*10).round(2)
    else
        result=Math.round((convertedNumber/${LowestDilution;Final Value})*10)
	}
  else
    {
    if (sMPNValue.indexOf('<')>-1)
       result="<1.8"
    else
    if (sMPNValue.indexOf('>')>-1)
       result=">1600"
    else
       result="777"
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
def bDecimal=false;
/* Declare parameters to get values from replicates samples */
def DestinationPFC = 'ThermotolerantFecalColiform';
def DestinationP = 'MPNValue';
def DestinationPTypeF = 'Final Value';
def sdidataitem = SGS.getSDIDataitem('labvantage',${primary:s_sampleid},${paramlistid},""+${paramlistversionid},${variantid}, ""+${dataset}, DestinationPFC, DestinationPTypeF, 'max');
if (SGS.checkResults('labvantage',sdidataitem, '#','#','#','#', DestinationP, DestinationPTypeF, 'max', true))
{
 def sMPNValue=SGS.getTextResults('labvantage', sdidataitem,, '#','#','#','#', DestinationP, DestinationPTypeF, 'max', true)[0]
 if (sMPNValue.isNumber())
    {
    result=0
    float convertedNumber = Float.parseFloat(sMPNValue)
    if (sMPNValue.indexOf('.')>-1) 
      result=Math.ceil((convertedNumber/${LowestDilution;Final Value})*10) as int
    else
      result=Math.round((convertedNumber/${LowestDilution;Final Value})*10)
    }
 else
    {
    if (sMPNValue.indexOf('<')>-1)
      {
      result="<2"
      }
    else
    if (sMPNValue.indexOf('>')>-1)
      {
      result=">1600"
      }
    }
}
return result

==========================================
/*
  TM=APHA9223_B_FC_W
  PL=APHA9223_B_Calculation_FC_W
  MPNValue is Text type
  Parameter=FecalColiforms(Standard) 
*/
def result="";
def bDecimal=false;
/* Declare parameters to get values from replicates samples */
def DestinationPFC = 'FecalColiforms';
def DestinationP = 'MPNValue';
def DestinationPTypeF = 'Final Value';
def DestinationPTypeS = 'Standard';
def sdidataitem = SGS.getSDIDataitem('labvantage',${primary:s_sampleid},${paramlistid},""+${paramlistversionid},${variantid}, ""+${dataset}, DestinationPFC, DestinationPTypeS, 'max');
if (SGS.checkResults('labvantage',sdidataitem, '#','#','#','#', DestinationP, DestinationPTypeF, 'max', true))
{
  def sMPNValue=SGS.getTextResults('labvantage', sdidataitem,, '#','#','#','#', DestinationP, DestinationPTypeF, 'max', true)[0]
  if (sMPNValue.isNumber())
    {
    result=0
    float convertedNumber = Float.parseFloat(sMPNValue)
    if (sMPNValue.indexOf('.')>-1)
        result=(convertedNumber)
    else
        result=Math.round(convertedNumber)
	}
  else
    {
    if (sMPNValue.indexOf('<')>-1)
       {
       result="<1"
       }
    else
    if (sMPNValue.indexOf('>')>-1)
       {
       result=">2419.6"
       }
    }
}
return result

/*
  TM=APHA9223_B_FC_W
  PL=APHA9223_B_Calculation_FC_W
  MPNValue is Text type
  Parameter=FecalColiforms(Final Value) 
*/
def result="";
def prevresult=0;
def bDecimal=false;
/* Declare parameters to get values from replicates samples */
def DestinationPFC = 'FecalColiforms';
def DestinationP = 'MPNValue';
def DestinationPTypeF = 'Final Value';
def sdidataitem = SGS.getSDIDataitem('labvantage',${primary:s_sampleid},${paramlistid},""+${paramlistversionid},${variantid}, ""+${dataset}, DestinationPFC, DestinationPTypeF, 'max');
if (SGS.checkResults('labvantage',sdidataitem, '#','#','#','#', DestinationP, DestinationPTypeF, 'max', true))
{
  def sMPNValue=SGS.getTextResults('labvantage', sdidataitem,, '#','#','#','#', DestinationP, DestinationPTypeF, 'max', true)[0]
  if (sMPNValue.isNumber())
    {
    result=0
    float convertedNumber = Float.parseFloat(sMPNValue)
    if (sMPNValue.indexOf('.')>-1)
        result=(${DF;Final Value}*convertedNumber).round(2)
    else
        result=Math.round(${DF;Final Value}*convertedNumber)
	}
  else
    {
    if (sMPNValue.indexOf('<')>-1)
       {
       result="<1"
       }
    else
    if (sMPNValue.indexOf('>')>-1)
       {
       result=">2419.6"
       }
    }
}
return result
