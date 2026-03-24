"LV Internal cal=

LV Internal cal= ${x;Standard}==0?0:${x;Standard;max;Signal}/${x;Standard}"

Multiplier=
[APHA3125_B_Prep_W;*;*;workitem=#|Multiplier;Final Value;max]


RF(Groovy)=
${7429-90-5;Standard}==0?0:${7429-90-5;Standard;max;Signal}/${7429-90-5;Standard}
${SAR;Standard}==0?0:${SAR;Standard;max;Signal}/${SAR;Standard}


FINAL VALUE=
[DF;Final Value]*[7429-90-5;Standard]*[Multiplier;Final Value]

RF=
${SAR;Standard}==0?0:${SAR;Standard;max;Signal}/${SAR;Standard}
${7429-90-5;Standard}==0?0:${7429-90-5;Standard;max;Signal}/${7429-90-5;Standard}


Adjusted SDL=
[DF;Final Value]*[SAR;Final Value;max;ParamLimit_1:SDL]*[Multiplier;Final Value]
[DF;Final Value]*[SAR;Final Value;max;ParamLimit_1:SDL]*[Multiplier;Final Value]/[#;#;#;#|SOP_Multiplier]

Adjusted MDL=
[DF;Final Value]*[SAR;Final Value;max;ParamLimit_1:MDL]*[Multiplier;Final Value]
[DF;Final Value]*[SAR;Final Value;max;ParamLimit_1:MDL]*[Multiplier;Final Value]/[#;#;#;#|SOP_Multiplier]

Adjusted RL
[DF;Final Value]*[SAR;Final Value;max;ParamLimit_1:RL]*[Multiplier;Final Value]
[DF;Final Value]*[SAR;Final Value;max;ParamLimit_1:RL]*[Multiplier;Final Value]/[#;#;#;#|SOP_Multiplier]

Surrogate
PL=EPA8260_W
Parameter=1868-53-7
Description=Methane, dibromofluoro-
S_Surrogate
ExpectedRecovery
%Recovery_SS = 100*([1868-53-7;S_Surrogate]/[1868-53-7;ExpectedRecovery])
SpkC


[NaMilliEquiG;Final Value]/(sqrt( 0.5*([MgMilliEquiG;Final Value]+[CaMilliEquiG;Final Value]) ))

$G{${NaMilliEquiG;Final Value}==0?0:${NaMilliEquiG;Final Value}/(sqrt(0.5*(${MgMilliEquiG;Final Value}+${CaMilliEquiG;Final Value})))}

$G{${NaMilliEquiG;Final Value}==0?0:${NaMilliEquiG;Final Value}/(0.5*(${MgMilliEquiG;Final Value}+${CaMilliEquiG;Final Value}))}


/*
	_WSTD_W
	DataType=Concentration
*/
SGS.getRecipeReagentLot('labvantage',${primary:reagentlotid},'Conductivity','Concentration','displayvalue')*
SGS.getRecipeReagentLot('labvantage',${primary:reagentlotid},'Conductivity','Concentration','amount')/${primary:amountinitial}


OLD RE:
=======
[SAR;Standard;max;Signal]/[SAR;Standard]


NEW RF:
=======
$G{${SAR;Standard}==0?0:${SAR;Standard;max;Signal}/${SAR;Standard}}

$G{${NaMilliEquiG;Final Value}==0?0:(
${CaMilliEquiG;Final Value}+${MgMilliEquiG;Final Value}==0?0:(
(${NaMilliEquiG;Final Value}/Math.sqrt(0.5*(${CaMilliEquiG;Final Value}+${MgMilliEquiG;Final Value})))
))}


PL=APHA4500_Norg_B_W
Parameter=TotalN_FinalValue

$G{def result=0;
def sdidataitem = SGS.getSDIDataitem('labvantage',${primary:s_sampleid},${paramlistid},""+${paramlistversionid},${variantid}, ""+${dataset}, 'TotalN','Standard', 'max');
if (SGS.checkResults('labvantage',sdidataitem, '#','#','#', '#', 'TKN', 'Final Value', 'max', true))
{
  result=result+SGS.getNumericResults('labvantage', sdidataitem,, '#','#','#', '#', 'TKN', 'Final Value', 'max', true)[0]
}
if (SGS.checkResults('labvantage',sdidataitem, '#','#','#', '#', 'NO3-N', 'Final Value', 'max', true))
{
  result=result+SGS.getNumericResults('labvantage', sdidataitem,, '#','#','#', '#', 'NO3-N', 'Final Value', 'max', true)[0]
}
if (SGS.checkResults('labvantage',sdidataitem, '#','#','#', '#', 'NO2-N', 'Final Value', 'max', true))
{
  result=result+SGS.getNumericResults('labvantage', sdidataitem,, '#','#','#', '#', 'NO2-N', 'Final Value', 'max', true)[0]
}
return result
}




EPA8082_A_S
11097-6-1	(Standard) Rep1 to 5

Final
[DF;Final Value]*[11097-69-1;Standard;#]*[Multiplier;Final Value]
[DF;Final Value]*[11097-69-1;Standard;*]*[Multiplier;Final Value]


TM=TNRCC1006_S
PL=TNRCC1006_S
Multiplier:
[TNRCC1006_Prep_S;*;*;workitem=#|Multiplier;Final Value;#]

==============================
PL=APHA2320_B_W
Parameter=HydroxideAlkalinity
Type=Standard
Groovy
==============================
def result=0;
if (${PhenolphthaleinAlkalinity;Standard}==0)
{
  result=6
}
else
if (${PhenolphthaleinAlkalinity;Standard}==${TotalAlkalinity;Standard})
{
  result=${TotalAlkalinity;Standard}
}
else
if (${PhenolphthaleinAlkalinity;Standard}<(0.5*${TotalAlkalinity;Standard}))
{
  result=7
}
else
if (${PhenolphthaleinAlkalinity;Standard}==(0.5*${TotalAlkalinity;Standard}))
{
  result=8
}
else
if (${PhenolphthaleinAlkalinity;Standard}>(0.5*${TotalAlkalinity;Standard}))
{
  result=(2*${PhenolphthaleinAlkalinity;Standard})-${TotalAlkalinity;Standard}
}
return result


==============================
PL=APHA2320_B_W
Parameter=CarbonateAlkalinity
Type=Standard
Groovy
==============================
def result=0;
if (${PhenolphthaleinAlkalinity;Standard}==0)
{
  result=0
}
else
if (${PhenolphthaleinAlkalinity;Standard}==${TotalAlkalinity;Standard})
{
  result=0
}
else
if (${PhenolphthaleinAlkalinity;Standard}<(0.5*${TotalAlkalinity;Standard}))
{
  result=(2*${PhenolphthaleinAlkalinity;Standard})
}
else
if (${PhenolphthaleinAlkalinity;Standard}==(0.5*${TotalAlkalinity;Standard}))
{
  result=(2*${PhenolphthaleinAlkalinity;Standard})
}
else
if (${PhenolphthaleinAlkalinity;Standard}>(0.5*${TotalAlkalinity;Standard}))
{
  result=(2*${TotalAlkalinity;Standard})-${PhenolphthaleinAlkalinity;Standard}
}
return result


==============================
PL=APHA2320_B_W
Parameter=BicarbonateAlkalinity
Type=Standard
Groovy
==============================
def result=0;
if (${PhenolphthaleinAlkalinity;Standard}==0)
{
  result=1
}
else
if (${PhenolphthaleinAlkalinity;Standard}==${TotalAlkalinity;Standard})
{
  result=0
}
else
if (${PhenolphthaleinAlkalinity;Standard}<(0.5*${TotalAlkalinity;Standard}))
{
  result=${TotalAlkalinity;Standard}-(2*${PhenolphthaleinAlkalinity;Standard})
}
else
if (${PhenolphthaleinAlkalinity;Standard}==(0.5*${TotalAlkalinity;Standard}))
{
  result=6
}
else
if (${PhenolphthaleinAlkalinity;Standard}>(0.5*${TotalAlkalinity;Standard}))
{
  result=7
}
return result


NEW FORMULA
===========
$G{def result=0;
def sdidataitem = SGS.getSDIDataitem('labvantage',${primary:s_sampleid},${paramlistid},""+${paramlistversionid},${variantid}, ""+${dataset}, 'PhenolphthaleinAlkalinity','Standard', 'max');
if (SGS.checkResults('labvantage',sdidataitem, '#','#','#', '#', 'TKN', 'Final Value', 'max', true))
{
  result=result+SGS.getNumericResults('labvantage', sdidataitem,, '#','#','#', '#', 'TKN', 'Final Value', 'max', true)[0]
}
if (SGS.checkResults('labvantage',sdidataitem, '#','#','#', '#', 'NO3-N', 'Final Value', 'max', true))
{
  result=result+SGS.getNumericResults('labvantage', sdidataitem,, '#','#','#', '#', 'NO3-N', 'Final Value', 'max', true)[0]
}
if (SGS.checkResults('labvantage',sdidataitem, '#','#','#', '#', 'NO2-N', 'Final Value', 'max', true))
{
  result=result+SGS.getNumericResults('labvantage', sdidataitem,, '#','#','#', '#', 'NO2-N', 'Final Value', 'max', true)[0]
}
return result
}

$G{/*([FinalWeight;BlankCorrected]-[InitialWeight;BlankCorrected])/[SampleVolume;Final Value]*1000*/
def sdidataitem = SGS.getSDIDataitem('labvantage',${primary:s_sampleid},${paramlistid},""+${paramlistversionid},${variantid}, ""+${dataset}, 'RespirableParticulateConc','Standard', 'max');
def finalWeight=(SGS.checkResults('labvantage',sdidataitem, '#','#','#', '#', 'FinalWeight', 'BlankCorrected', 'max', true)?SGS.getNumericResults('labvantage',sdidataitem, '#','#','#', '#', 'FinalWeight', 'BlankCorrected', 'max', true)[0]:SGS.getNumericResults('labvantage',sdidataitem, '#','#','#', '#', 'FinalWeight', 'Standard', 'max', true)[0])
def initialWeight=(SGS.checkResults('labvantage',sdidataitem, '#','#','#', '#', 'InitialWeight', 'BlankCorrected', 'max', true)?SGS.getNumericResults('labvantage',sdidataitem, '#','#','#', '#', 'InitialWeight', 'BlankCorrected', 'max', true)[0]:SGS.getNumericResults('labvantage',sdidataitem, '#','#','#', '#', 'InitialWeight', 'Standard', 'max', true)[0])
return (finalWeight-initialWeight)/${SampleVolume;Final Value}*1000}


def result=0;
if (${PhenolphthaleinAlkalinity;Standard}==0)
{
  result=6
}
else
if (${PhenolphthaleinAlkalinity;Standard}==${TotalAlkalinity;Standard})
{
  result=${TotalAlkalinity;Standard}
}
else
if (${PhenolphthaleinAlkalinity;Standard}<(0.5*${TotalAlkalinity;Standard}))
{
  result=7
}
else
if (${PhenolphthaleinAlkalinity;Standard}==(0.5*${TotalAlkalinity;Standard}))
{
  result=8
}
else
if (${PhenolphthaleinAlkalinity;Standard}>(0.5*${TotalAlkalinity;Standard}))
{
  result=(2*${PhenolphthaleinAlkalinity;Standard})-${TotalAlkalinity;Standard}
}
return result

$G{/*([FinalWeight;BlankCorrected]-[InitialWeight;BlankCorrected])/[SampleVolume;Final Value]*1000*/


SGS.checkResults(datasourceid,sdidataitem, paramlistid,paramlistversionid,variantid, dataset, paramid, paramtype, replicateid, sameWorkitem)
SGS.getNumericResults(datasourceid,sdidataitem, paramlistid,paramlistversionid,variantid, dataset, paramid, paramtype, replicateid, sameWorkitem)

def sdidataitem = SGS.getSDIDataitem('labvantage',${primary:s_sampleid},${paramlistid},""+${paramlistversionid},${variantid}, ""+${dataset}, 'PhenolphthaleinAlkalinity','Standard', 'max');
def PhenoAlka=(SGS.checkResults('labvantage',sdidataitem, '#','#','#', '#', 'PhenolphthaleinAlkalinity', 'Standard', 'max', true)?SGS.getNumericResults('labvantage',sdidataitem, '#','#','#', '#', 'PhenolphthaleinAlkalinity', 'Standard', 'max', true)[0]:SGS.getNumericResults('labvantage',sdidataitem, '#','#','#', '#', 'PhenolphthaleinAlkalinity', 'Standard', 'max', true)[0])
def TotalAlka=(SGS.checkResults('labvantage',sdidataitem, '#','#','#', '#', 'TotalAlkalinity', 'Standard', 'max', true)?SGS.getNumericResults('labvantage',sdidataitem, '#','#','#', '#', 'TotalAlkalinity', 'Standard', 'max', true)[0]:SGS.getNumericResults('labvantage',sdidataitem, '#','#','#', '#', 'TotalAlkalinity', 'Standard', 'max', true)[0])
def result=0;
if (PhenoAlka==0)
{
  result=6
}
else
if (PhenoAlka=TotalAlka)
{
  result=7
}

return 





$G{/*[DF;Final Value]*[7631-86-9;BlankCorrected]*[Multiplier;Final Value]/([FLOW;Standard]*[Sampling duration;Standard])*/
def sdidataitem = SGS.getSDIDataitem('labvantage',${primary:s_sampleid},${paramlistid},""+${paramlistversionid},${variantid}, ""+${dataset}, '7631-86-9','Final Value', 'max');
if (SGS.checkResults('labvantage',sdidataitem, '#','#','#', '#', '#', 'BlankCorrected', 'max', true))
{
	def resultBlkCorr=SGS.getNumericResults('labvantage',sdidataitem, '#','#','#', '#', '#', 'BlankCorrected', 'max', true)[0]
	${DF;Final Value}*resultBlkCorr*${Multiplier;Final Value}/(${FLOW;Standard}*${Sampling duration;Standard})
}
else
{
	${DF;Final Value}*${Multiplier;Final Value}/(${FLOW;Standard}*${Sampling duration;Standard})
}}






$G{if (${primary:qcsampletype}=='Unknown')  
{  
((${DF;Final Value}*${7440-74-6;Standard}*${Multiplier;Final Value}*${FinalVolume;Final Value})- (SGS.average(SGS.toArray(${AQC:Blank;All|NIOSH7303_A;*;#;max|7440-74-6;Standard;*}))*  SGS.average(SGS.toArray(${AQC:Blank;All|NIOSH7303_A;*;#;max|FinalVolume;Final Value;*}))))/${AirVolume;Final Value}  }  
else  
{  
return "0"  
}
}


$G{/*([DF;Final Value]*[7429-90-5;Standard]*[Multiplier;Final Value]*[FinalVolume;Final Value]-avg([AQC:Blank;Previous|NIOSH7303_A;#;#;max|7429-90-5;Standard;*])*[FinalVolume;Final Value])/[AirVolume;Final Value]*/
if (${primary:qcsampletype}=='Unknown')
{
((${DF;Final Value}*${7429-90-5;Standard}*${Multiplier;Final Value}*${FinalVolume;Final Value})-
(SGS.average(SGS.toArray(${AQC:Blank;All|NIOSH7303_A;*;#;max|7429-90-5;Standard;*}))*
SGS.average(SGS.toArray(${AQC:Blank;All|NIOSH7303_A;*;#;max|FinalVolume;Final Value;*}))))/${AirVolume;Final Value}
}
else
{
return "0"
}}



$G{def sdidataitem=SGS.getSDIDataitem('labvantage',${primary:s_sampleid},${paramlistid},""+${paramlistversionid},${variantid}, ""+${dataset}, 'Multiplier','Final Value', 'max')
/*Max DF in the child sample*/
/*Get Child sample Ids*/
def childs=SGS.childSampleId('labvantage',${primary:s_sampleid},'');
/*Get  DF values of Child sample Ids*/
def result=SGS.sampleNumericResults('labvantage',sdidataitem,SGS.concat(childs,';'), 'NIOSH6013_A_child','*','#', 'max', 'DF', '*', 'max')
/*Retum  max*/
return SGS.max(result)}


$G{if (${primary:qcsampletype}=='Unknown')
{
return (((${DF;Final Value}*${7439-97-6;Standard}*${Multiplier;Final Value}) * ${SampleVolume;Final Value} / ${AliquotVolume;Final Value}) - SGS.average(SGS.toArray(${AQC:Blank;All|NIOSH6009_A;*;#;max|7439-97-6;Standard;*})))/${AirVolume;Final Value}
}
else
{
 return "0"
}
}




$G{def sdidataitem=SGS.getSDIDataitem('labvantage',${primary:s_sampleid},${paramlistid},""+${paramlistversionid},${variantid}, ""+${dataset}, 'VPH_C5_C9','Final Value', 'max')
/*see method formula*/
/* front child sample*/
def frontChild=SGS.childSampleId('labvantage',${primary:s_sampleid},'s.samplesubtypeid =\'Air_Front\'');
/*back child sample*/
def backChild=SGS.childSampleId('labvantage',${primary:s_sampleid},'s.samplesubtypeid =\'Air_Back\'');
/*Get front result */
def frontresult=SGS.sampleNumericResults('labvantage',sdidataitem,frontChild[0], 'NIOSH1550_A_child','*','#', 'max', '#', 'Absolute Value', 'max');
/*Get back result */
def backresult=SGS.sampleNumericResults('labvantage',sdidataitem,backChild[0], 'NIOSH1550_A_child','*','#', 'max', '#', 'Absolute Value', 'max');
/*Get front QC*/
def frontqc=SGS.qcSampleIdByWorkItem('labvantage',frontChild[0],'NIOSH1550_A%',''+${#;#;#;#|sourceworkiteminstance},false,'Front_Blank','');
/*Get back QC*/
def backqc=SGS.qcSampleIdByWorkItem('labvantage',backChild[0],'NIOSH1550_A%',''+${#;#;#;#|sourceworkiteminstance},false,'Back_Blank','');
/*Get results front QC*/
def frontqcresult=SGS.sampleNumericResults('labvantage',sdidataitem,SGS.concat(frontqc,';'), 'NIOSH1550_A_child','*','#', 'max', '#', 'Absolute Value', 'max');
/*Get results backQC*/
def backqcresult=SGS.sampleNumericResults('labvantage',sdidataitem,SGS.concat(backqc,';'), 'NIOSH1550_A_child','*','#', 'max', '#', 'Absolute Value', 'max');
def result=-99999999;
if(frontresult.length>0 && backresult.length>0 && frontqcresult.length>0 && backqcresult.length>0)
{     
	result=(frontresult[0]+backresult[0]-SGS.average(frontqcresult)-SGS.average(backqcresult))*1000 /(${FLOW;Standard}*${Sampling duration;Standard})
}
/* return resultvalue */
return result;}



DATE
TM=APHA9610_D_W
PL=APHA9610_D_W
Parameter=IncubationTime
LV.hoursBetween(${StartIncubation;Final Value;#},${EndIncubation;Final Value;#})


QC
Water
Bottle_Drinking_Water
Brackish_Water
Drinking_Water
Distilled_Water
Ground_Water
Pool_Water
Rain_Water
Sea_Water
Surface_Water
Clean_Water
River_Water
Eluate

QC
WasteWater
Ballast_Water
Bath_Water




QC;Ground_Water;Drinking_Water;Clean_Water;WasteWater;Sea_Water



/*
	QCBatch %Recovery y RPD
*/
Phosphorous_APHA4500_P_1ppm_LCS_AEDXB

DQMTMJA1_Consumable
RL-0000674


NIOSH1550_A_EPA8015_25ppm_AEDXB

NIOSH1550_A_EPA8015_25ppm_AEDXB


DQMTMJA1_WSTD_TRAINING