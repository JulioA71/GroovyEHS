/*
    ParamID=
*/
def CalcResult=0;
def bstatus=false;
if (${pH_OX;Final Value} >= 6.5) {
    CalcResult = 0
    }
else {
    CalcResult = (49 * ${NaOHTitre;Final Value} * ${MolarityNaOH;Final Value} / ${SampleWeightTPA;Final Value})
    }
return CalcResult;

/*
if (CalcResult >= 0)
    bstatus = true
return (!bstatus?'':CalcResult)
*/

[DF;Final Value]
[Multiplier;Final Value]
[MoistureFactor;Final Value]
[MolarityHCL;Final Value]
[MolarityNaOH;Final Value]
[SampleWeightANCE;Final Value]
[TAA_H2SO4;Final Value]
[TAA_H;Final Value]
[TAA_S;Final Value]
[SampleWeightTPA;Final Value]
[HCLTitre;Final Value]
[NaOHTitre;Final Value]
[pH_OX;Final Value]
[TPA;Final Value]
[TPA_H;Final Value]
[TPA_S;Final Value]
[TSA_H;Final Value]
[TSA_H2SO4;Final Value]
[TSA_S;Final Value]
[ANC_EXCESS;Final Value]
[ANCE_H;Final Value]
[ANCE_S;Final Value]
[S_POS;Final Value]
[S_KCL;Final Value]
[S_POS_M;Final Value]


[S_P;Standard]
[S_P;Final Value]
[CA_KCL;Final Value]
[CA_P;Standard]
[CA_P;Final Value]
[CA_REACTED;Final Value]
[CA_REACTED_M;Final Value]
[MG_KCL;Final Value]
[MG_P;Standard]
[MG_P;Final Value]
[MG_REACTED;Final Value]
[MG_REACTED_M;Final Value]
[S_HCL;Final Value]
[S_NAS;Final Value]
[S_NAS_M;Final Value]