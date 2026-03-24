switch(${primary:qcsampletype})
{

try {
case ['Unknown','Dup']:
return ${7664-39-3;BlankCorrected}
  break;
} catch(Exception e) {
  break;
}
case ['MB','FB','LCS','Blank']:
return 555
  break;
default:
return 0
break;
}



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