def result=0;
def sdidataitem=SGS.getSDIDataitem('labvantage',${primary:s_sampleid},${paramlistid},""+${paramlistversionid},${variantid}, ""+${dataset}, '7429-90-5','Final Value', 'max');
def nRL=SGS.getLimitResults('labvantage',sdidataitem, 'RL', 'NIOSH7303_A','*', '*','max', '7429-90-5', 'Final Value', 'max', true)[0].value1num;
switch(${primary:qcsampletype})
{
case ['Unknown','Dup']:
  if (${7429-90-5;Standard}>=nRL)
  {
      result=(((${7429-90-5;Standard}*${DF;Final Value}*${FinalVolume;Final Value}) - (SGS.average(SGS.toArray(${AQC:MB;All|NIOSH7303_A;*;#;max|7429-90-5;Standard;*}*${DF;Final Value}*${FinalVolume;Final Value})))) / ${AirVolume;Final Value})
  }
  else
  {
      result=((${7429-90-5;Standard}*${DF;Final Value}*${FinalVolume;Final Value}) / ${AirVolume;Final Value})
  }
  break;
case ['MB','FB']: 
  result=77
  break;
case ['LCS']: 
  result=(${7429-90-5;Standard})
  break;
default:
  result=0
  break;
}
return result