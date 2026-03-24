/*
    Description:    Verify if dataset # 2 is available
    Created by:     JA
    Created:        2023-11-23
    Modification log (add below)
    Date            Name                            Description
    yyyy-MM-dd      xxxxx                           xxxxx
    TM=DQMTM_DataSet
    PL=DQMPL_DataSet
    Parameter=DQMParam4 (Final Value)
    ${DQMPL_DataSet;*;*;#|DQMParam1;Final Value;max}
*/
def strStatus="non-Pass";
def numDS=0;
switch(${primary:qcsampletype})
{
case ['Unknown','Dup']: 
   def sdidataitem=SGS.getSDIDataitem('labvantage',${primary:s_sampleid},${paramlistid},""+${paramlistversionid},${variantid}, ""+${dataset}, 'Status','Final Value', 'max')
   /* Verifying the max(DataSet) from SDIData */
   numDS=SGS.getMaxDataSet('labvantage', ${primary:s_sampleid}, ${paramlistid}, ${paramlistversionid}.toString(), ${variantid})
   if (numDS==2)
     strStatus="Pass DS2"
   else if (numDS==1)
     strStatus="Pass DS1"
   else
     strStatus="non-Pass"
break;
default:
  strStatus="Pass"
break;
}
return strStatus


/*
    Description:    Verify if dataset # 2 is available
    Created by:     JA
    Created:        2023-11-23
    Modification log (add below)
    Date            Name                            Description
    yyyy-MM-dd      xxxxx                           xxxxx
    TM=DQMTM_DataSet
    PL=DQMPL_DataSet
    Parameter=DQMParam4 (Final Value)
    ${DQMPL_DataSet;*;*;#|DQMParam1;Final Value;max}
*/
def strStatus="non-Pass";
def numValue1=0;
def numValue2=0;
def numDS=0;
switch(${primary:qcsampletype})
{
case ['Unknown','Dup']: 
   def sdidataitem1 = SGS.getSDIDataitem('labvantage',${primary:s_sampleid},${paramlistid},""+${paramlistversionid},${variantid}, '1', 'Status','Final Value', 'max')
   /* DQMPL_DataSet|DQMParam1 - DataSet #1 */
   if (SGS.checkResults('labvantage',sdidataitem1, '#','#','#','1', 'DQMParam1', 'Final Value', 'max', true))
   {
     numValue1=SGS.getNumericResults('labvantage',sdidataitem1, 'DQMPL_DataSet','*','*', '1', 'DQMParam1', 'Final Value', 'max', false)
     if(numValue1.length>0)
    {
     numDS=1
    }}

   /* DQMPL_DataSet|DQMParam1 - DataSet #2 */
   try {
   def sdidataitem2 = SGS.getSDIDataitem('labvantage',${primary:s_sampleid},${paramlistid},""+${paramlistversionid},${variantid}, '2', 'Status','Final Value', 'max')
   if (SGS.checkResults('labvantage',sdidataitem2, '#','#','#','2', 'DQMParam1', 'Final Value', 'max', true))
   {
     numValue2=SGS.getNumericResults('labvantage',sdidataitem1, 'DQMPL_DataSet','*','*', '2', 'DQMParam1', 'Final Value', 'max', false)
     if(numValue2.length>0)
    {
     numDS=numDS+1
    }}
    } catch(Exception e) {
     numDS=1
    }
    
   if(numDS>1)
     strStatus="Pass DS1/DS2"
   else if(numDS>0)
     strStatus="Pass DS1"   
   else
     strStatus="non-Pass"
break;
default:
  strStatus="Pass"
break;
}
return strStatus
