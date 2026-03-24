/*
  TM: DQM_WIEHSLA292_W
  PL: DQM_WIEHSLA292_CALC_WS 
  Parameter: TaxaNumber
  Type: Final Value (Numeric Calc)
  Author: DQM team
  Purpose: Calculation for Number of Taxa
  Date: 2024-05-15
*/
// Declare variable general-purpose
def CalcResult = 0;
def bstatus = false;

// Variable declaration - modify the parameters below with the source (where the data comes from) and destination (where the data goes to)
String ParamListID = 'DQM_WIEHSLA292_WS';   // define the parameter list
String ParamID = 'NumberIndividuals';       // define the parameter id
String ParamType = 'Final Value';           // define the parameter type
def SampleType = ${primary:qcsampletype};   // define the sample type

// Variable to hold data related to the SDI
def SDIDataItem=SGS.getSDIDataitem('labvantage', ${primary:s_sampleid}, ParamListID, '*', ${variantid}, ${dataset}.toString(), ParamID, ParamType, 'max');

// Get the replicates number
def maxRep=SGS.getMaxReplicates('labvantage', ${primary:s_sampleid}, ParamListID, SDIDataItem.paramlistversionid, SDIDataItem.variantid, ""+SDIDataItem.dataset, ParamID , ParamType);
for(int i in 1..maxRep) {
    def MDENumber=SGS.getNumericResults('labvantage',SDIDataItem, SDIDataItem.paramlistid, SDIDataItem.paramlistversionid, SDIDataItem.variantid, ""+SDIDataItem.dataset, ParamID , ParamType, Integer.toString(i), true)
    if (MDENumber.length > 0) {
        if (MDENumber[0] > 0) {
            bstatus=true;
            CalcResult=CalcResult+1;
	    }
    }
}
return (!bstatus?'':CalcResult)



/*
  TM: DQM_WIEHSLA292_W
  PL: DQM_WIEHSLA292_CALC_WS 
  Parameter: Abundance
  Type: Final Value (Numeric Calc)
  Author: DQM team
  Purpose: Calculation for Number of Taxa
  Date: 2024-05-15
*/
// Declare variable general-purpose
def CalcResult = 0;
def bstatus = false;

// Variable declaration - modify the parameters below with the source (where the data comes from) and destination (where the data goes to)
String ParamListID = 'DQM_WIEHSLA292_WS';   // define the parameter list
String ParamID = 'NumberIndividuals';       // define the parameter id
String ParamType = 'Final Value';           // define the parameter type
def SampleType = ${primary:qcsampletype};   // define the sample type

// Variable to hold data related to the SDI
def SDIDataItem=SGS.getSDIDataitem('labvantage', ${primary:s_sampleid}, ParamListID, '*', ${variantid}, ${dataset}.toString(), ParamID, ParamType, 'max');

// Get the replicates number
def maxRep=SGS.getMaxReplicates('labvantage', ${primary:s_sampleid}, ParamListID, SDIDataItem.paramlistversionid, SDIDataItem.variantid, ""+SDIDataItem.dataset, ParamID , ParamType);
for(int i in 1..maxRep) {
    def MDENumber=SGS.getNumericResults('labvantage',SDIDataItem, SDIDataItem.paramlistid, SDIDataItem.paramlistversionid, SDIDataItem.variantid, ""+SDIDataItem.dataset, ParamID , ParamType, Integer.toString(i), true)
    if (MDENumber.length > 0) {
        if (MDENumber[0] > 0) {            
            bstatus=true;
            CalcResult=CalcResult+MDENumber[0];
	    }        
    }
}
return (!bstatus?'':CalcResult)



/*
  TM: DQM_WIEHSLA292_W
  PL: DQM_WIEHSLA292_CALC_WS 
  Parameter: TaxaLN
  Type: Final Value (Numeric Calc)
  Author: DQM team
  Purpose: Calculation Logarithm for Number of Taxa
  Date: 2024-05-15
*/
// Declare variable general-purpose
BigDecimal CalcResult = 0;
def bstatus = false;
def MDETaxaNum = ${TaxaNumber;Final Value};

if (MDETaxaNum > 0) {
    CalcResult=Math.log(MDETaxaNum);
    bstatus=true;
}
return (!bstatus?'':CalcResult)



/*
  PL: DQM_WIEHSLA292_WS
  Parameter: IndexDiversity
  Type: Final Value (Numeric Calc)
  Author: DQM team
  Purpose: Calculation for DQM_WIEHSLA292_WS (Phytoplankton/ Perifiton)
  Date: 2024-05-25
*/
// Declare variable general-purpose
def CalcResult = 0;
def bstatus = false;

// Variable declaration - modify the parameters below with the source (where the data comes from) and destination (where the data goes to)
String ParamListID = 'DQM_WIEHSLA292_WS';   // define the parameter list
String ParamID = 'NumberIndividuals';       // define the parameter id
String ParamType = 'Final Value';           // define the parameter type
def SampleType = ${primary:qcsampletype};   // define the sample type
def MDEAbudance = ${Abundance;Final Value};

// Variable to hold data related to the SDI
def SDIDataItem=SGS.getSDIDataitem('labvantage', ${primary:s_sampleid}, ParamListID, '*', ${variantid}, ${dataset}.toString(), ParamID, ParamType, 'max');

// Get the replicates number
def maxRep=SGS.getMaxReplicates('labvantage', ${primary:s_sampleid}, ParamListID, SDIDataItem.paramlistversionid, SDIDataItem.variantid, ""+SDIDataItem.dataset, ParamID , ParamType);
for(int i in 1..maxRep) {
    def MDENumber=SGS.getNumericResults('labvantage',SDIDataItem, SDIDataItem.paramlistid, SDIDataItem.paramlistversionid, SDIDataItem.variantid, ""+SDIDataItem.dataset, ParamID , ParamType, Integer.toString(i), true)
    if (MDENumber.length > 0) {
        if (MDENumber[0] > 0) {
            bstatus=true;
            double Pi=MDENumber[0] / MDEAbudance;
            double PiLn=Math.log(Pi);
            double Pi_PiLn=Pi * PiLn;
            CalcResult=CalcResult + Math.abs(Pi_PiLn);
	    }
    }
}
return (!bstatus?'':CalcResult)



/*
  PL: DQM_WIEHSLA292_WS
  Parameter: IndexUniformity
  Type: Final Value (Numeric Calc)
  Author: DQM team
  Purpose: Calculation for DQM_WIEHSLA292_WS (Phytoplankton/ Perifiton)
  Date: 2024-05-25
*/
// Declare variable general-purpose
def CalcResult = 0;
def bstatus = false;
double MDELnTaxa = ${TaxaLN;Final Value};
double MDEIndDiv = ${IndexDiversity;Final Value};

if (MDELnTaxa > 0) {
    bstatus = true;
    CalcResult=MDEIndDiv / MDELnTaxa;
}
return (!bstatus?'':CalcResult)



/*
  PL: DQM_WIEHSLA292_WS
  Parameter: IndexDominance
  Type: Final Value (Numeric Calc)
  Author: DQM team
  Purpose: Calculation for DQM_WIEHSLA292_WS (Phytoplankton/ Perifiton)
  Date: 2024-05-25
*/
// Declare variable general-purpose
def CalcResult = 0;
def bstatus = false;

// Variable declaration - modify the parameters below with the source (where the data comes from) and destination (where the data goes to)
String ParamListID = 'DQM_WIEHSLA292_WS';   // define the parameter list
String ParamID = 'NumberIndividuals';       // define the parameter id
String ParamType = 'Final Value';           // define the parameter type
def SampleType = ${primary:qcsampletype};   // define the sample type
def MDEAbudance = ${Abundance;Final Value};

// Variable to hold data related to the SDI
def SDIDataItem=SGS.getSDIDataitem('labvantage', ${primary:s_sampleid}, ParamListID, '*', ${variantid}, ${dataset}.toString(), ParamID, ParamType, 'max');

// Get the replicates number
def maxRep=SGS.getMaxReplicates('labvantage', ${primary:s_sampleid}, ParamListID, SDIDataItem.paramlistversionid, SDIDataItem.variantid, ""+SDIDataItem.dataset, ParamID , ParamType);
for(int i in 1..maxRep) {
    def MDENumber=SGS.getNumericResults('labvantage',SDIDataItem, SDIDataItem.paramlistid, SDIDataItem.paramlistversionid, SDIDataItem.variantid, ""+SDIDataItem.dataset, ParamID , ParamType, Integer.toString(i), true)
    if (MDENumber.length > 0) {
        if (MDENumber[0] > 0) {
            bstatus=true;
            double Pi=MDENumber[0] / MDEAbudance;
            double PiPower=Math.pow(Pi,2);
            CalcResult=CalcResult + Math.abs(PiPower);
	    }
    }
}
return (!bstatus?'':CalcResult)
