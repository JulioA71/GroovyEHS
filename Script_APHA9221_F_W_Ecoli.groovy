/* TM: APHA9221_F_W */
/* PL: APHA9221_F_Ec_MUG_W */
/* Parameter: EColi.Final Value */
/* [MPN;Standard]*10/[SampleVolume;Standard] */
def result=0;
if (${MPN;Standard}==0)
{
  result=0.5
}
else
if (${SampleVolume;Standard}==0)
{
  result=0.5
}
else
{
  result=${MPN;Standard}*10/${SampleVolume;Standard}
}
return result


/* TM: APHA9221_F_W */
/* PL: APHA9221_F_Ec_MUG_W */
/* Parameter: EColi.Final Value */
/* (([MPN;Standard]/100)/[SampleVolume;Standard])*10 */
def result=0;
if (${MPN;Standard}==0)
{
  result=0.5
}
else
if (${SampleVolume;Standard}==0)
{
  result=0.5
}
else
{
  result=((${MPN;Standard}/100)/${SampleVolume;Standard})*10
}
return result
