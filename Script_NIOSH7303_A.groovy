/* TM=NIOSH7303_A */
/* PL=NIOSH7303_A */
/* Parameter=7429-90-5 */

def result=0;
def sdidataitem=SGS.getSDIDataitem('labvantage',${primary:s_sampleid},'NIOSH7303_A', '*', '*', 'max', '7429-90-5','Final Value', 'max');
def nRL=SGS.getLimitResults('labvantage',sdidataitem, 'RL', 'NIOSH7303_A','*', '*','max', '7429-90-5', 'Final Value', 'max', true)[0].value1num;
/* Get Values from Parameters */
def nDF=SGS.getNumericResults('labvantage', sdidataitem,, '#','#','#', '#', 'DF', 'Final Value', 'max', true)[0];
def nFinalVolume=SGS.getNumericResults('labvantage', sdidataitem,, '#','#','#', '#', 'FinalVolume', 'Final Value', 'max', true)[0];
def nAirVolume=SGS.getNumericResults('labvantage', sdidataitem,, '#','#','#', '#', 'AirVolume', 'Final Value', 'max', true)[0];
/* Get MB Values from AQC */
def nMBStandard=${AQC:MB;All|NIOSH7303_A;*;#;max|7429-90-5;Standard;*};
def nMBDF=${AQC:MB;All|NIOSH7303_A;*;#;max|DF;Final Value;*};
def nMBFinalVolume=${AQC:MB;All|NIOSH7303_A;*;#;max|FinalVolume;Final Value;*};
/* Calculations */
def ValueMB=(((${7429-90-5;Standard}*nDF*nFinalVolume) - (nMBStandard*nMBDF*nMBFinalVolume)) / nAirVolume);
def ValueUNK=((${7429-90-5;Standard}*nDF*nFinalVolume) / nAirVolume);
switch(${primary:qcsampletype})
{
case ['Unknown','Dup']:
  if (${7429-90-5;Standard}>=nRL)
  {
      result=ValueMB
  }
  else
  {
      result=ValueUNK
  }
  break;
case ['MB','FB','LCS']: 
  result=(${7429-90-5;Standard})
  break;
default:
  result=0
  break;
}
return result
