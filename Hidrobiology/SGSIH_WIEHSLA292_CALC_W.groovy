/*
  TM: SGSIH_WIEHSLA292_W
  PL: SGSIH_WIEHSLA292_CALC_W
  Parameter: TaxaNumber
  Type: Final Value (Numeric Calc)
  Author: DQM team
  Purpose: Calculation for Number of Taxa
  Date: 2025-01-22
*/
// Declare variable general-purpose
def CalcResult = 0;
def bstatus = false;

// Variable declaration - modify the parameters below with the source (where the data comes from) and destination (where the data goes to)
String ParamListID = 'SGSIH_WIEHSLA292_W';   // define the parameter list
String ParamID = 'NumberIndividuals';       // define the parameter id
String ParamType = 'Final Value';           // define the parameter type
def SampleType = ${primary:qcsampletype};   // define the sample type

// Variable to hold data related to the SDI
def SDIDataItem=SGS.getSDIDataitem('labvantage', ${primary:s_sampleid}, ParamListID, '*', ${variantid}, ${dataset}.toString(), ParamID, ParamType, 'max');

// Verify if TAXA = Pass
def sTaxa=SGS.getTextResults('labvantage', SDIDataItem,, '#','#','#','#', 'TAXA', 'Standard', 'max', true)[0]
if (sTaxa == 'Pass') {
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
}
return (!bstatus?'':CalcResult)




/*
  TM: SGSIH_WIEHSLA292_W
  PL: SGSIH_WIEHSLA292_CALC_W 
  Parameter: Abundance
  Type: Final Value (Numeric Calc)
  Author: DQM team
  Purpose: Calculation for Number of Taxa
  Date: 2025-01-22
*/
// Declare variable general-purpose
def CalcResult = 0;
def bstatus = false;

// Variable declaration - modify the parameters below with the source (where the data comes from) and destination (where the data goes to)
String ParamListID = 'SGSIH_WIEHSLA292_W';   // define the parameter list
String ParamID = 'NumberIndividuals';       // define the parameter id
String ParamType = 'Final Value';           // define the parameter type
def SampleType = ${primary:qcsampletype};   // define the sample type

// Variable to hold data related to the SDI
def SDIDataItem=SGS.getSDIDataitem('labvantage', ${primary:s_sampleid}, ParamListID, '*', ${variantid}, ${dataset}.toString(), ParamID, ParamType, 'max');

// Verify if TAXA = Pass
def sTaxa=SGS.getTextResults('labvantage', SDIDataItem,, '#','#','#','#', 'TAXA', 'Standard', 'max', true)[0]
if (sTaxa == 'Pass') {    
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
}
return (!bstatus?'':CalcResult)



/*
  TM: SGSIH_WIEHSLA292_W
  PL: SGSIH_WIEHSLA292_CALC_W
  Parameter: TaxaLN
  Type: Final Value (Numeric Calc)
  Author: DQM team
  Purpose: Calculation Logarithm for Number of Taxa
  Date: 2025-01-22
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



/*
  PL: SGSIH_WIEHSLA292_CALC_W
  Parameter: IndexDiversity
  Type: Final Value (Numeric Calc)
  Author: DQM team
  Purpose: Calculation for Index Diversity
  Date: 2025-01-22
*/
// Declare variable general-purpose
def CalcResult = 0;
def bstatus = false;

// Variable declaration - modify the parameters below with the source (where the data comes from) and destination (where the data goes to)
String ParamListID = 'SGSIH_WIEHSLA292_W';   // define the parameter list
String ParamID = 'NumberIndividuals';       // define the parameter id
String ParamType = 'Final Value';           // define the parameter type
def SampleType = ${primary:qcsampletype};   // define the sample type
def MDEAbudance = ${Abundance;Final Value};

// Variable to hold data related to the SDI
def SDIDataItem=SGS.getSDIDataitem('labvantage', ${primary:s_sampleid}, ParamListID, '*', ${variantid}, ${dataset}.toString(), ParamID, ParamType, 'max');

// Verify if TAXA = Pass
def sTaxa=SGS.getTextResults('labvantage', SDIDataItem,, '#','#','#','#', 'TAXA', 'Standard', 'max', true)[0]
if (sTaxa == 'Pass') {
    // Get the replicates number
    def maxRep=SGS.getMaxReplicates('labvantage', ${primary:s_sampleid}, ParamListID, SDIDataItem.paramlistversionid, SDIDataItem.variantid, ""+SDIDataItem.dataset, ParamID , ParamType);
    for(int i in 1..maxRep) {
        def MDENumber=SGS.getNumericResults('labvantage',SDIDataItem, SDIDataItem.paramlistid, SDIDataItem.paramlistversionid, SDIDataItem.variantid, ""+SDIDataItem.dataset, ParamID , ParamType, Integer.toString(i), true)
        if (MDENumber.length > 0) {
            if (MDENumber[0] > 0) {
                bstatus = true;
                double Pi = MDENumber[0] / MDEAbudance;
                double PiLn = Math.log(Pi);
                double Pi_PiLn = Pi * PiLn;
                CalcResult = CalcResult + Math.abs(Pi_PiLn);
            }
        }
    }
}
return (!bstatus?'':CalcResult)




/*
  PL: SGSIH_WIEHSLA292_CALC_W
  Parameter: IndexUniformity
  Type: Final Value (Numeric Calc)
  Author: DQM team
  Purpose: Calculation for Index Uniformity
  Date: 2025-01-22
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





/*
  PL: SGSIH_WIEHSLA292_CALC_W
  Parameter: IndexDominance
  Type: Final Value (Numeric Calc)
  Author: DQM team
  Purpose: Calculation for Index Dominance
  Date: 2025-01-22
*/
// Declare variable general-purpose
def CalcResult = 0;
def bstatus = false;

// Variable declaration - modify the parameters below with the source (where the data comes from) and destination (where the data goes to)
String ParamListID = 'SGSIH_WIEHSLA292_W';   // define the parameter list
String ParamID = 'NumberIndividuals';       // define the parameter id
String ParamType = 'Final Value';           // define the parameter type
def SampleType = ${primary:qcsampletype};   // define the sample type
def MDEAbudance = ${Abundance;Final Value};

// Variable to hold data related to the SDI
def SDIDataItem=SGS.getSDIDataitem('labvantage', ${primary:s_sampleid}, ParamListID, '*', ${variantid}, ${dataset}.toString(), ParamID, ParamType, 'max');

// Verify if TAXA = Pass
def sTaxa=SGS.getTextResults('labvantage', SDIDataItem,, '#','#','#','#', 'TAXA', 'Standard', 'max', true)[0]
if (sTaxa == 'Pass') {
    // Get the replicates number
    def maxRep=SGS.getMaxReplicates('labvantage', ${primary:s_sampleid}, ParamListID, SDIDataItem.paramlistversionid, SDIDataItem.variantid, ""+SDIDataItem.dataset, ParamID , ParamType);
    for(int i in 1..maxRep) {
        def MDENumber=SGS.getNumericResults('labvantage',SDIDataItem, SDIDataItem.paramlistid, SDIDataItem.paramlistversionid, SDIDataItem.variantid, ""+SDIDataItem.dataset, ParamID , ParamType, Integer.toString(i), true)
        if (MDENumber.length > 0) {
            if (MDENumber[0] > 0) {
                bstatus = true;
                double Pi = MDENumber[0] / MDEAbudance;
                double PiPower = Math.pow(Pi,2);
                CalcResult = CalcResult + Math.abs(PiPower);
            }
        }
    }
}
return (!bstatus?'':CalcResult)





/*
    Description:    Verify if dataset # 2 is available
    Created by:     JA
    Created:        2023-11-23
    Modification log (add below)
    Date            Name                            Description
    yyyy-MM-dd      xxxxx                           xxxxx
    TM=DQMTM_DataSet
    PL=DQMPL_DataSet
    Parameter=DQMParam4 (Final Value)
    ${DQMPL_DataSet;*;*;#|DQMParam1;Final Value;max}
*/
def strStatus="non-Pass";
def numValue1=0;
def numValue2=0;
switch(${primary:qcsampletype})
{
case ['Unknown','Dup']: 
   def sdidataitem = SGS.getSDIDataitem('labvantage',${primary:s_sampleid},${paramlistid},""+${paramlistversionid},${variantid}, ""+${dataset}, 'DQMParam4','Final Value', 'max')
   /* DQMPL_DataSet|DQMParam1 - DataSet #1 */
   def numValue1=SGS.getNumericResults('labvantage',sdidataitem, 'DQMPL_DataSet','*','*', '1', 'DQMParam1', 'Final Value', 'max', false)
   /* DQMPL_DataSet|DQMParam1 - DataSet #2 */
   def numValue2=SGS.getNumericResults('labvantage',sdidataitem, 'DQMPL_DataSet','*','*', '2', 'DQMParam1', 'Final Value', 'max', false)
   if(numValue1.length>0 && numValue2.length>0)
     strStatus="Pass DS1/DS2"
   else if(numValue1.length>0)
     strStatus="Pass DS1"
   else if(numValue2.length>0)
     strStatus="Pass DS2"
   else
     strStatus="non-Pass"
break;
default:
  strStatus="Pass"
break;
}
return strStatus