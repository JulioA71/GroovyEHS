/* TM: EHSSOP057_W */
/* PL: EHSSOP057_W */
/* Parameter: HeterotrophicCount.Final Value */
/* Type: Text(Calc) */
def result;
if (${ColonyCount;Final Value}<30) 
{
  result=${ColonyCount;Final Value}
}
else
if (${ColonyCount;Final Value}>300) 
{
  result="TNTC"
}
else

{
  result=${ColonyCount;Final Value}/${SampleVolume;Standard}
}
return result