/*
    PL=DQMPL_Class05_02
    Parameter=DQMParam1 (Final Value)
    Data Type=Numeric Calc
    Remarks=DQM Batch Subtract Blank
*/
// Variable declaration - modify below as needed
def SampleType = ${primary:qcsampletype};		// define the sample type

// Generic variable to hold the calculated data
def CalcResult = 0;

/* Get Blank Values from AQC */
def nBlank=${AQC:Blank;All|DQMPL_Class05_02;*;#;max|DQMParam1;Standard;*};

// Exit and return 0 if it's not an Unknown/Dup sample
if (SampleType in ['Unknown','Dup'])
  CalcResult = ((${DF;Final Value} * ${DQMParam1;Standard} * ${Multiplier;Final Value}) / ${SampleVolume;Final Value})-nBlank
else
  CalcResult = ${DQMParam1;Standard}
return CalcResult;