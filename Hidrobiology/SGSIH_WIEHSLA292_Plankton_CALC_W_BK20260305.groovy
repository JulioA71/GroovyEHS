/*
  PL: SGSIH_WIEHSLA292_Plankton_CALC_W
  Parameter: TaxaNumber
  Type: Final Value (Numeric Calc)
  Author: Julio Alvarez (DQM team)
  Purpose: Calculation for Number of Taxa (Fito + Zoo)
  Date: 2026-Feb-22
*/
// Declare variable general-purpose
def CalcResult = 0;
def bstatus = false;

// Variable declaration - modify the parameters below with the source (where the data comes from) and destination (where the data goes to)
String ParamListID1 = 'SGSIH_WIEHSLA292_Phyto_W';   // define the parameter list
String ParamListID2 = 'SGSIH_WIEHSLA292_Zoo_W';     // define the parameter list
String ParamID = 'NumberIndividuals';       // define the parameter id
String ParamType = 'Final Value';           // define the parameter type
def SampleType = ${primary:qcsampletype};   // define the sample type

// Variable to hold data related to the SDI
def SDIDataItem1=SGS.getSDIDataitem('labvantage', ${primary:s_sampleid}, ParamListID1, '*', ${variantid}, ${dataset}.toString(), ParamID, ParamType, 'max');

// Verify if TAXA = Pass
def sTaxa1=SGS.getTextResults('labvantage', SDIDataItem1,, '#','#','#','#', 'TAXA', 'Standard', 'max', true)[0]
if (sTaxa1 == 'Pass') {
    // Get the replicates number
    def maxRep=SGS.getMaxReplicates('labvantage', ${primary:s_sampleid}, ParamListID1, SDIDataItem1.paramlistversionid, SDIDataItem1.variantid, ""+SDIDataItem1.dataset, ParamID , ParamType);
    for(int i in 1..maxRep) {
        def MDENumber=SGS.getNumericResults('labvantage',SDIDataItem1, SDIDataItem1.paramlistid, SDIDataItem1.paramlistversionid, SDIDataItem1.variantid, ""+SDIDataItem1.dataset, ParamID , ParamType, Integer.toString(i), true)
        if (MDENumber.length > 0) {
            if (MDENumber[0] > 0) {
                CalcResult=CalcResult+1;                
            }
        }
    }
}

def SDIDataItem2=SGS.getSDIDataitem('labvantage', ${primary:s_sampleid}, ParamListID2, '*', ${variantid}, ${dataset}.toString(), ParamID, ParamType, 'max');
def sTaxa2=SGS.getTextResults('labvantage', SDIDataItem2,, '#','#','#','#', 'TAXA', 'Standard', 'max', true)[0]
if (sTaxa2 == 'Pass') {
    // Get the replicates number
    def maxRep=SGS.getMaxReplicates('labvantage', ${primary:s_sampleid}, ParamListID2, SDIDataItem2.paramlistversionid, SDIDataItem2.variantid, ""+SDIDataItem2.dataset, ParamID , ParamType);
    for(int i in 1..maxRep) {
        def MDENumber=SGS.getNumericResults('labvantage',SDIDataItem2, SDIDataItem2.paramlistid, SDIDataItem2.paramlistversionid, SDIDataItem2.variantid, ""+SDIDataItem2.dataset, ParamID , ParamType, Integer.toString(i), true)
        if (MDENumber.length > 0) {
            if (MDENumber[0] > 0) {
                CalcResult=CalcResult+1;
            }
        }
    }
}
if (sTaxa1 == 'Pass' && sTaxa2 == 'Pass')  
    bstatus=true
else
    bstatus=false
return (!bstatus?'':CalcResult)


======================================
/*
  PL: SGSIH_WIEHSLA292_Plankton_CALC_W
  Parameter: Abundance
  Type: Final Value (Numeric Calc)
  Author: Julio Alvarez (DQM team)
  Purpose: Calculation for Abundance (Fito + Zoo)
  Date: 2026-Feb-22
*/
// Declare variable general-purpose
def CalcResult = 0;
def bstatus = false;

// Variable declaration - modify the parameters below with the source (where the data comes from) and destination (where the data goes to)
String ParamListID1 = 'SGSIH_WIEHSLA292_Phyto_W';   // define the parameter list
String ParamListID2 = 'SGSIH_WIEHSLA292_Zoo_W';     // define the parameter list
String ParamID = 'NumberIndividuals';       // define the parameter id
String ParamType = 'Final Value';           // define the parameter type
def SampleType = ${primary:qcsampletype};   // define the sample type

// Variable to hold data related to the SDI
def SDIDataItem1=SGS.getSDIDataitem('labvantage', ${primary:s_sampleid}, ParamListID1, '*', ${variantid}, ${dataset}.toString(), ParamID, ParamType, 'max');

// Verify if TAXA = Pass
def sTaxa1=SGS.getTextResults('labvantage', SDIDataItem1,, '#','#','#','#', 'TAXA', 'Standard', 'max', true)[0]
if (sTaxa1 == 'Pass') {    
    // Get the replicates number
    def maxRep=SGS.getMaxReplicates('labvantage', ${primary:s_sampleid}, ParamListID1, SDIDataItem1.paramlistversionid, SDIDataItem1.variantid, ""+SDIDataItem1.dataset, ParamID , ParamType);
    for(int i in 1..maxRep) {
        def MDENumber=SGS.getNumericResults('labvantage',SDIDataItem1, SDIDataItem1.paramlistid, SDIDataItem1.paramlistversionid, SDIDataItem1.variantid, ""+SDIDataItem1.dataset, ParamID , ParamType, Integer.toString(i), true)
        if (MDENumber.length > 0) {
            if (MDENumber[0] > 0) {            
                CalcResult=CalcResult+MDENumber[0];
            }        
        }
    }
}

def SDIDataItem2=SGS.getSDIDataitem('labvantage', ${primary:s_sampleid}, ParamListID2, '*', ${variantid}, ${dataset}.toString(), ParamID, ParamType, 'max');
def sTaxa2=SGS.getTextResults('labvantage', SDIDataItem2,, '#','#','#','#', 'TAXA', 'Standard', 'max', true)[0]
if (sTaxa2 == 'Pass') {    
    // Get the replicates number
    def maxRep=SGS.getMaxReplicates('labvantage', ${primary:s_sampleid}, ParamListID2, SDIDataItem2.paramlistversionid, SDIDataItem2.variantid, ""+SDIDataItem2.dataset, ParamID , ParamType);
    for(int i in 1..maxRep) {
        def MDENumber=SGS.getNumericResults('labvantage',SDIDataItem2, SDIDataItem2.paramlistid, SDIDataItem2.paramlistversionid, SDIDataItem2.variantid, ""+SDIDataItem2.dataset, ParamID , ParamType, Integer.toString(i), true)
        if (MDENumber.length > 0) {
            if (MDENumber[0] > 0) {            
                CalcResult=CalcResult+MDENumber[0];
            }        
        }
    }
}
if (sTaxa1 == 'Pass' && sTaxa2 == 'Pass')  
    bstatus=true
else
    bstatus=false
return (!bstatus?'':CalcResult)



======================================
/*
  PL: SGSIH_WIEHSLA292_Plankton_CALC_W
  Parameter: TaxaLN
  Type: Final Value (Numeric Calc)
  Author: Julio Alvarez (DQM team)
  Purpose: Calculation for Taxa Logarithm (Fito + Zoo)
  Date: 2026-Feb-22
*/
// Declare variable general-purpose
BigDecimal CalcResult = 0;
def bstatus = false;
def MDETaxaNum = ${TaxaNumber;Final Value};

if (MDETaxaNum > 0) {
    CalcResult = Math.log(MDETaxaNum);
    bstatus = true;
}
return (!bstatus?'':CalcResult)



======================================
/*
  PL: SGSIH_WIEHSLA292_Plankton_CALC_W
  Parameter: IndexDiversity
  Type: Final Value (Numeric Calc)
  Author: Julio Alvarez (DQM team)
  Purpose: Calculation for Diversity Index (Fito + Zoo)
  Date: 2026-Feb-24
*/
// Declare variable general-purpose
def CalcResult = 0;
def bstatus = false;

// Variable declaration - modify the parameters below with the source (where the data comes from) and destination (where the data goes to)
String ParamListID1 = 'SGSIH_WIEHSLA292_Phyto_W' ;   // define the parameter list
String ParamListID2 = 'SGSIH_WIEHSLA292_Zoo_W' ;   // define the parameter list
String ParamID = 'NumberIndividuals';       // define the parameter id
String ParamType = 'Final Value';           // define the parameter type
def SampleType = ${primary:qcsampletype};   // define the sample type
def MDEAbudance = ${Abundance;Final Value};

// Variable to hold data related to the SDI
def SDIDataItem1=SGS.getSDIDataitem('labvantage', ${primary:s_sampleid}, ParamListID1, '*', ${variantid}, ${dataset}.toString(), ParamID, ParamType, 'max');

// Verify if TAXA = Pass
def sTaxa1=SGS.getTextResults('labvantage', SDIDataItem1,, '#','#','#','#', 'TAXA', 'Standard', 'max', true)[0]
if (sTaxa1 == 'Pass') {
    // Get the replicates number
    def maxRep=SGS.getMaxReplicates('labvantage', ${primary:s_sampleid}, ParamListID1, SDIDataItem1.paramlistversionid, SDIDataItem1.variantid, ""+SDIDataItem1.dataset, ParamID , ParamType);
    for(int i in 1..maxRep) {
        def MDENumber=SGS.getNumericResults('labvantage',SDIDataItem1, SDIDataItem1.paramlistid, SDIDataItem1.paramlistversionid, SDIDataItem1.variantid, ""+SDIDataItem1.dataset, ParamID , ParamType, Integer.toString(i), true)
        if (MDENumber.length > 0) {
            if (MDENumber[0] > 0) {
                double Pi = MDENumber[0] / MDEAbudance;
                double PiLn = Math.log(Pi);
                double Pi_PiLn = Pi * PiLn;
                CalcResult = CalcResult + Math.abs(Pi_PiLn);
            }
        }
    }
}

def SDIDataItem2=SGS.getSDIDataitem('labvantage', ${primary:s_sampleid}, ParamListID2, '*', ${variantid}, ${dataset}.toString(), ParamID, ParamType, 'max');

// Verify if TAXA = Pass
def sTaxa2=SGS.getTextResults('labvantage', SDIDataItem2,, '#','#','#','#', 'TAXA', 'Standard', 'max', true)[0]
if (sTaxa2 == 'Pass') {
    // Get the replicates number
    def maxRep=SGS.getMaxReplicates('labvantage', ${primary:s_sampleid}, ParamListID2, SDIDataItem2.paramlistversionid, SDIDataItem2.variantid, ""+SDIDataItem2.dataset, ParamID , ParamType);
    for(int i in 1..maxRep) {
        def MDENumber=SGS.getNumericResults('labvantage',SDIDataItem2, SDIDataItem2.paramlistid, SDIDataItem2.paramlistversionid, SDIDataItem2.variantid, ""+SDIDataItem2.dataset, ParamID , ParamType, Integer.toString(i), true)
        if (MDENumber.length > 0) {
            if (MDENumber[0] > 0) {
                double Pi = MDENumber[0] / MDEAbudance;
                double PiLn = Math.log(Pi);
                double Pi_PiLn = Pi * PiLn;
                CalcResult = CalcResult + Math.abs(Pi_PiLn);
            }
        }
    }
}
if (sTaxa1 == 'Pass' && sTaxa2 == 'Pass')  
    bstatus=true
else
    bstatus=false
return (!bstatus?'':CalcResult)


======================================
/*
  PL: SGSIH_WIEHSLA292_Plankton_CALC_W
  Parameter: IndexUniformity
  Type: Final Value (Numeric Calc)
  Author: Julio Alvarez (DQM team)
  Purpose: Calculation for Uniformity Index (Fito + Zoo)
  Date: 2026-Feb-22
*/
// Declare variable general-purpose
def CalcResult = 0;
def bstatus = false;

if (${TaxaLN;Final Value}>0 && ${IndexDiversity;Final Value}>0) {
    double MDELnTaxa = ${TaxaLN;Final Value};
    double MDEIndDiv = ${IndexDiversity;Final Value};

    bstatus = true;
    CalcResult = MDEIndDiv / MDELnTaxa;
}
return (!bstatus?'':CalcResult)


======================================
/*
  PL: SGSIH_WIEHSLA292_Plankton_CALC_W
  Parameter: IndexDominance
  Type: Final Value (Numeric Calc)
  Author: Julio Alvarez (DQM team)
  Purpose: Calculation for Dominance Index (Fito + Zoo)
  Date: 2026-Feb-22
*/
// Declare variable general-purpose
def CalcResult = 0;
def bstatus = false;

// Variable declaration - modify the parameters below with the source (where the data comes from) and destination (where the data goes to)
String ParamListID1 = 'SGSIH_WIEHSLA292_Phyto_W';   // define the parameter list
String ParamListID2 = 'SGSIH_WIEHSLA292_Zoo_W';   // define the parameter list
String ParamID = 'NumberIndividuals';       // define the parameter id
String ParamType = 'Final Value';           // define the parameter type
def SampleType = ${primary:qcsampletype};   // define the sample type
def MDEAbudance = ${Abundance;Final Value};

// Variable to hold data related to the SDI
def SDIDataItem1=SGS.getSDIDataitem('labvantage', ${primary:s_sampleid}, ParamListID1, '*', ${variantid}, ${dataset}.toString(), ParamID, ParamType, 'max');

// Verify if TAXA = Pass
def sTaxa1=SGS.getTextResults('labvantage', SDIDataItem1,, '#','#','#','#', 'TAXA', 'Standard', 'max', true)[0]
if (sTaxa1 == 'Pass') {
    // Get the replicates number
    def maxRep=SGS.getMaxReplicates('labvantage', ${primary:s_sampleid}, ParamListID1, SDIDataItem1.paramlistversionid, SDIDataItem1.variantid, ""+SDIDataItem1.dataset, ParamID , ParamType);
    for(int i in 1..maxRep) {
        def MDENumber=SGS.getNumericResults('labvantage',SDIDataItem1, SDIDataItem1.paramlistid, SDIDataItem1.paramlistversionid, SDIDataItem1.variantid, ""+SDIDataItem1.dataset, ParamID , ParamType, Integer.toString(i), true)
        if (MDENumber.length > 0) {
            if (MDENumber[0] > 0) {
                double Pi = MDENumber[0] / MDEAbudance;
                double PiPower = Math.pow(Pi,2);
                CalcResult = CalcResult + Math.abs(PiPower);
            }
        }
    }
}

def SDIDataItem2=SGS.getSDIDataitem('labvantage', ${primary:s_sampleid}, ParamListID2, '*', ${variantid}, ${dataset}.toString(), ParamID, ParamType, 'max');
// Verify if TAXA = Pass
def sTaxa2=SGS.getTextResults('labvantage', SDIDataItem2,, '#','#','#','#', 'TAXA', 'Standard', 'max', true)[0]
if (sTaxa2 == 'Pass') {
    // Get the replicates number
    def maxRep=SGS.getMaxReplicates('labvantage', ${primary:s_sampleid}, ParamListID2, SDIDataItem2.paramlistversionid, SDIDataItem2.variantid, ""+SDIDataItem2.dataset, ParamID , ParamType);
    for(int i in 1..maxRep) {
        def MDENumber=SGS.getNumericResults('labvantage',SDIDataItem2, SDIDataItem2.paramlistid, SDIDataItem2.paramlistversionid, SDIDataItem2.variantid, ""+SDIDataItem2.dataset, ParamID , ParamType, Integer.toString(i), true)
        if (MDENumber.length > 0) {
            if (MDENumber[0] > 0) {
                double Pi = MDENumber[0] / MDEAbudance;
                double PiPower = Math.pow(Pi,2);
                CalcResult = CalcResult + Math.abs(PiPower);
            }
        }
    }
}
if (sTaxa1 == 'Pass' && sTaxa2 == 'Pass')  
    bstatus=true
else
    bstatus=false
return (!bstatus?'':CalcResult)