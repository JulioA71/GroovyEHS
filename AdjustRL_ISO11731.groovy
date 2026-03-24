def result=0
if (${ISO11731_ME_Filtration_W;*;*;max|Procedure;Final Value;max}=='ISO11731_1-4')
{
return (1000/${ISO11731_ME_Filtration_W;*;*;max|PlateVolume;Final Value;max})*${Legionella;Final Value;max;ParamLimit_1:RL}
}
else if (${ISO11731_ME_Filtration_W;*;*;max|Procedure;Final Value;max}=='ISO11731_5-7')
{
return (1000/${ISO11731_ME_Filtration_W;*;*;max|SampleVolumeFiltered;Final Value;max})*${Legionella;Final Value;max;ParamLimit_1:RL}
}
else if ((${ISO11731_ME_Filtration_W;*;*;max|Procedure;Final Value;max}=='ISO11731_8-10')&&(${Overgrowth_ISO11731;Final Value}=='Y'))
{
return ((((1000*${ISO11731_ME_Filtration_W;*;*;max|Wash;Final Value;max})/(${ISO11731_ME_Filtration_W;*;*;max|SampleVolumeFiltered;Final Value;max}*${ISO11731_ME_Filtration_W;*;*;max|PlateVolume;Final Value;max}))*10)*${Legionella;Final Value;max;ParamLimit_1:RL})
}
else  if (${ISO11731_ME_Filtration_W;*;*;max|Procedure;Final Value;max}=='ISO11731_8-10'&&${ISO11731_Calculation_W;*;*;max|Overgrowth_ISO11731;Final Value;max}=='N')
{
return (((1000*${ISO11731_ME_Filtration_W;*;*;max|Wash;Final Value;max})/(${ISO11731_ME_Filtration_W;*;*;max|SampleVolumeFiltered;Final Value;max}*${ISO11731_ME_Filtration_W;*;*;max|PlateVolume;Final Value;max}))*${Legionella;Final Value;max;ParamLimit_1:RL})
}
else 
{
return result
}