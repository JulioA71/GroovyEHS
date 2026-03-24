/* TM=VDI3492_A */

/* PL=VDI3492_Calculation_A */
/* Parameter=StatLowerLimit */
/* (SGS.ChiInv((1+0.95)/2,2*${VDI3492_Analysis_A;*;*;max|FARcounted;Final Value;max}))*0.5*(1/${AirSampledCorrelated;Final Value}) */

def result=0;
if (${VDI3492_Analysis_A;*;*;max|Analysable;Final Value;max}=="Yes")
if (${VDI3492_Analysis_A;*;*;max|FARcounted;Final Value;max}==0 || ${VDI3492_Calculation_A;*;*;max|AirSampledCorrelated;Final Value;max}==0)
{
  result=0
}
else
{
  result=(SGS.ChiInv((1+0.95)/2,2*${VDI3492_Analysis_A;*;*;max|FARcounted;Final Value;max}))*0.5*(1/${AirSampledCorrelated;Final Value})
}
else
{
  result=""
}
return result


/* TM=VDI3492_A */
/* PL=VDI3492_Calculation_A */
/* Parameter=StatUpperLimit */

if (${VDI3492_Analysis_A;*;*;max|Analysable;Final Value;max}=="Yes")
if (${VDI3492_Calculation_A;*;*;max|AirSampledCorrelated;Final Value;max}==0)
{
  result=0
}
else
{
  result=(SGS.ChiInv((1-0.95)/2,2*(${VDI3492_Analysis_A;*;*;max|FARcounted;Final Value;max}+1)))*0.5*(1/${AirSampledCorrelated;Final Value})
}
else
{
  result=""
}
return result


/* TM=VDI3492_A */
/* PL=VDI3492_Calculation_A */
/* Parameter=StatProbaBeyondThreshold */
/* (1-SGS.PoissonDist(1000*${AirSampledCorrelated;Final Value},${VDI3492_Analysis_A;*;*;max|FARcounted;Final Value;max},true))*100 */

def result=0;
if (${VDI3492_Analysis_A;*;*;max|Analysable;Final Value;max}=="Yes")
if (${VDI3492_Analysis_A;*;*;max|FARcounted;Final Value;max}==0)
{
  result=0
}
else
{
  result=(1-SGS.PoissonDist(1000*${AirSampledCorrelated;Final Value},${VDI3492_Analysis_A;*;*;max|FARcounted;Final Value;max},true))*100
}
else
{
  result=""
}
return result


/*
	TM=VDI3492_A
	PL=VDI3492_Results_A
	Parameter=Results
*/

def result="";
if (${VDI3492_Analysis_A;*;*;max|Analysable;Final Value;max}=="Yes" && ${VDI3492_Calculation_A;*;*;max|ConcAsbestos;Final Value;max}<1000)
{  
  result="The result is in compliance with CFST Directive 6503 f - 12.08: less than 1000 FAR/m�.";
}
else
if (${VDI3492_Analysis_A;*;*;max|Analysable;Final Value;max}=="Yes" && ${VDI3492_Calculation_A;*;*;max|ConcAsbestos;Final Value;max}>=1000)
{
  result="The result is not in compliance with CFST Directive 6503 f - 12.08: superior than 1000 RAF/m�. Respirable Asbestos Fibers counted: " + Math.round(${VDI3492_Analysis_A;*;*;max|FARcounted;Final Value;max}) + " Other type of fibers counted: " + Math.round(${VDI3492_Analysis_A;*;*;max|FIOTcounted;Final Value;max})
}
else
{
  result="This sample is not analysable according to VDI3492 guideline. More than 1/8th of the sample's surface is covered by particules."
}
return result


The result is not in compliance with CFST Directive 6503 f - 12.08: superior than 1000 RAF/m�. Respirable Asbestos Fibers counted: 77 Other type of fibers counted: 0
The result is not in compliance with CFST Directive 6503 f - 12.08: superior than 1000 RAF/m�. Respirable Asbestos Fibers counted: 77 Other type of fibers counted: 3


Math.round(${VDI3492_Analysis_A;*;*;max|FARcounted;Final Value;max})
Math.round(${VDI3492_Analysis_A;*;*;max|FIOTcounted;Final Value;max})


SI(D8="Yes";SI(D17<1000;"The result is in compliance with CFST Directive 6503 f - 12.08: less than 1000 FAR/m�.";

"The result is not in compliance with CFST Directive 6503 f - 12.08: superior than 1000 RAF/m�")&CARACTER(10)&"Respirable Asbestos Fibers counted: " & D9 & CARACTER(10)&"Other type of fibers counted: " & D10;

"This sample is not analysable according to VDI3492 guideline. More than 1/8th of the sample's surface is covered by particules.")



The result is not in compliance with CFST Directive 6503 f - 12.08: superior than 1000 RAF/m�. Respirable Asbestos Fibers counted: 77.0000000000 Other type of fibers counted: 0E-10



/* Parameter=AirSampledCorrelated
([VDI3492_Analysis_A;*;*;max|NbrFieldAnalyzed;Final Value;max]*[VDI3492_Sampling_A;*;*;max|SamplingVolume;Final Value;max]*[FieldArea;Final Value])/[ActiveFilterArea;Final Value]

def result="";
if (${VDI3492_Analysis_A;*;*;max|Analysable;Standard;max}=="Yes")
{
  result=(${VDI3492_Analysis_A;*;*;max|NbrFieldAnalyzed;Final Value;max}*${VDI3492_Sampling_A;*;*;max|SamplingVolume;Final Value;max}*${FieldArea;Final Value})/${ActiveFilterArea;Final Value}
}
else
{
  result="NO"
}
return result