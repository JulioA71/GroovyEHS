/* TM=NIOSH3500_A */
/* PL=NIOSH3500_AIH */
/* Parameter=50-00-0 (Breakthrough) */
switch(${primary:qcsampletype})
{
case ['Unknown','Dup']:
if (${50-00-0;WB}>${50-00-0;WF}/3)
{
return "YES"
}
return "NO"
  break;
case ['MB','FB','LCS']: 
return "-"
  break;
default:
return 0
break;
}


/*
    Description:    Calculate Standard
    Created by:     JA
    Created:        2023-11-08
    Modification log (add below)
    Date            Name                            Description
    yyyy-MM-dd      xxxxx                           xxxxx
    TM=NIOSH3500_A
    PL=NIOSH3500_A
    Parameter=50-00-0 (Standard)
    ([50-00-0;WF]+[50-00-0;WB])-([AQC:MB;All|NIOSH3500_A;*;*;max|50-00-0;WF;max]+[AQC:MB;All|NIOSH3500_A;*;*;max|50-00-0;WB;max])
*/
def result=0;
def sdidataitem=SGS.getSDIDataitem('labvantage',${primary:s_sampleid},'NIOSH3500_A', '*', '*', 'max', '50-00-0','Standard', 'max');
/* Get MB Values from AQC */
def nMBStdWF=${AQC:MB;All|NIOSH3500_A;*;#;max|50-00-0;WF;*};
def nMBStdWB=${AQC:MB;All|NIOSH3500_A;*;#;max|50-00-0;WB;*};
/* Calculations */
result=((${50-00-0;WF}+${50-00-0;WB})-(nMBStdWF+nMBStdWB))
return result

([50-00-0;WF]+[50-00-0;WB])-([AQC:MB;All|NIOSH3500_AIH;*;*;max|50-00-0;WF;max]+[AQC:MB;All|NIOSH3500_AIH;*;*;max|50-00-0;WB;max])


/* TM=NIOSH3500_A */
/* PL=NIOSH3500_A */
/* Parameter=50-00-0 (Breakthrough) */
/* Parameter=50-00-0 (Standard) */
/* Parameter=50-00-0 (Final Value) */
switch(${primary:qcsampletype})
{
case ['Unknown','Dup']:
return (${50-00-0;Standard}-${AQC:MB;All|NIOSH3500_AIH;*;*;max|50-00-0;Standard;max})*${Multiplier;Final Value}*${DF;Final Value}
  break;
case ['MB','FB','LCS']: 
return (${50-00-0;Standard}-${AQC:MB;All|NIOSH3500_AIH;*;*;max|50-00-0;Standard;max})
  break;
default:
return 0
break;
}
