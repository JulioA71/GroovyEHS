/*
	Description:	Calculate the TaxaNumber:Final Value for SGSIH_WIEHSLA292_Plankton_CALC_W
	Created by:		Marino Orsi
	Created:		2026-03-06
	Modification log (add below)
	Date		Name			Description
	2026-03-03	Julio			To count the ParamId by Phyto and/or Zoo
*/
def dummy = ${CalculationDate;Final Value};

// Variable declaration
String phytoParamList = 'SGSIH_WIEHSLA292_Phyto_W';	// PL for Phytoplankton data
String zooParamList = 'SGSIH_WIEHSLA292_Zoo_W';		// Pl for Zooplankton data
String ParamID = 'NumberIndividuals';				// Parameter ID
String ParamType = 'Final Value';					// Parameter Type
def calcResult = 0;									// Calculated result

// Get DataInfo object
def currentSDIDataItem = SGS.getSDIDataitem('labvantage', ${primary:s_sampleid}, ${paramlistid}, '*', '*', '*', '*', '*', '*');		// Current PL

// Check if results exists in the current PL
if (SGS.checkResults('labvantage', currentSDIDataItem, ${paramlistid}, ${paramlistversionid}.toString(), ${variantid}, ${dataset}.toString(), 'CalculationDate', 'Final Value', '*', false)) {
	// Get Taxa value for Phytoplankton
	def taxaPhyto = SGS.getTextResults('labvantage', currentSDIDataItem, phytoParamList, '*', '*', '*', 'Taxa', 'Standard', '*', false);
	def taxaZoo = SGS.getTextResults('labvantage', currentSDIDataItem, zooParamList, '*', '*', '*', 'Taxa', 'Standard', '*', false);

	if ((taxaPhyto.length > 0 && taxaPhyto[0] != 'Pass') || (taxaZoo.length > 0 && taxaZoo[0] != 'Pass')) { return ''; }

	// Check if there's result for phytoplankton and count all replicates
	if (taxaPhyto.length > 0 && taxaPhyto[0] == 'Pass') {
        def SDIDataItemPhyto=SGS.getSDIDataitem('labvantage', ${primary:s_sampleid}, phytoParamList, '*', ${variantid}, ${dataset}.toString(), ParamID, ParamType, 'max');
        // Get the replicates number
        def maxRep=SGS.getMaxReplicates('labvantage', ${primary:s_sampleid}, phytoParamList, SDIDataItemPhyto.paramlistversionid, SDIDataItemPhyto.variantid, ""+SDIDataItemPhyto.dataset, ParamID , ParamType);
        for(int i in 1..maxRep) {
            def MDENumber=SGS.getNumericResults('labvantage',SDIDataItemPhyto, SDIDataItemPhyto.paramlistid, SDIDataItemPhyto.paramlistversionid, SDIDataItemPhyto.variantid, ""+SDIDataItemPhyto.dataset, ParamID , ParamType, Integer.toString(i), true)
            if (MDENumber.length > 0) {
                if (MDENumber[0] > 0) {
                    calcResult=calcResult+1;                
                }
            }
        }
	}

	// Check if there's result for zooplankton and count all replicates
	if (taxaZoo.length > 0 && taxaZoo[0] == 'Pass') {
        def SDIDataItemZoo=SGS.getSDIDataitem('labvantage', ${primary:s_sampleid}, zooParamList, '*', ${variantid}, ${dataset}.toString(), ParamID, ParamType, 'max');
		// Get the replicates number
        def maxRep=SGS.getMaxReplicates('labvantage', ${primary:s_sampleid}, zooParamList, SDIDataItemZoo.paramlistversionid, SDIDataItemZoo.variantid, ""+SDIDataItemZoo.dataset, ParamID , ParamType);
        for(int i in 1..maxRep) {
            def MDENumber=SGS.getNumericResults('labvantage',SDIDataItemZoo, SDIDataItemZoo.paramlistid, SDIDataItemZoo.paramlistversionid, SDIDataItemZoo.variantid, ""+SDIDataItemZoo.dataset, ParamID , ParamType, Integer.toString(i), true)
            if (MDENumber.length > 0) {
                if (MDENumber[0] > 0) {
                    calcResult=calcResult+1;                
                }
            }
        }
	}

	return calcResult
}

======================================
/*
	Description:	Calculate the Abundance:Final Value for SGSIH_WIEHSLA292_Plankton_CALC_W
	Created by:		Marino Orsi
	Created:		2026-03-06
	Modification log (add below)
	Date		Name			Description
	yyyy-MM-dd	xxxxx			xxxxx
*/
def dummy = ${CalculationDate;Final Value};

// Variable declaration
String phytoParamList = 'SGSIH_WIEHSLA292_Phyto_W';	// PL for Phytoplankton data
String zooParamList = 'SGSIH_WIEHSLA292_Zoo_W';		// Pl for Zooplankton data
String ParamID = 'NumberIndividuals';				// Parameter ID
String ParamType = 'Final Value';					// Parameter Type
def calcResult = 0;									// Calculated result

// Get DataInfo object
def currentSDIDataItem = SGS.getSDIDataitem('labvantage', ${primary:s_sampleid}, ${paramlistid}, '*', '*', '*', '*', '*', '*');		// Current PL

// Check if results exists in the current PL
if (SGS.checkResults('labvantage', currentSDIDataItem, ${paramlistid}, ${paramlistversionid}.toString(), ${variantid}, ${dataset}.toString(), 'CalculationDate', 'Final Value', '*', false)) {
	// Get Taxa value for Phytoplankton
	def taxaPhyto = SGS.getTextResults('labvantage', currentSDIDataItem, phytoParamList, '*', '*', '*', 'Taxa', 'Standard', '*', false);
	def taxaZoo = SGS.getTextResults('labvantage', currentSDIDataItem, zooParamList, '*', '*', '*', 'Taxa', 'Standard', '*', false);

	if ((taxaPhyto.length > 0 && taxaPhyto[0] != 'Pass') || (taxaZoo.length > 0 && taxaZoo[0] != 'Pass')) { return ''; }

	// Check if there's result for phytoplankton and sum all replicates
	if (taxaPhyto.length > 0 && taxaPhyto[0] == 'Pass') {
		calcResult += SGS.getNumericResults('labvantage', currentSDIDataItem, phytoParamList, '*', '*', '*', ParamID , ParamType, '*', false).sum();
	}

	// Check if there's result for zooplankton and sum all replicates
	if (taxaZoo.length > 0 && taxaZoo[0] == 'Pass') {
		calcResult += SGS.getNumericResults('labvantage', currentSDIDataItem, zooParamList, '*', '*', '*', ParamID , ParamType, '*', false).sum();
	}

	return calcResult
}

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
	Description:	Calculate the IndexDiversity:Final Value for SGSIH_WIEHSLA292_Plankton_CALC_W
	Created by:		Marino Orsi
	Created:		2026-03-06
	Modification log (add below)
	Date		Name			Description
	2026-03-09	Julio			Update groovy
*/
def dummy = ${CalculationDate;Final Value};

// Variable declaration
String phytoParamList = 'SGSIH_WIEHSLA292_Phyto_W';	// PL for Phytoplankton data
String zooParamList = 'SGSIH_WIEHSLA292_Zoo_W';		// Pl for Zooplankton data
String ParamID = 'NumberIndividuals';				// Parameter ID
String ParamType = 'Final Value';					// Parameter Type
def calcResult = 0;									// Calculated result
def MDEAbudance = ${Abundance;Final Value};

// Get DataInfo object
def currentSDIDataItem = SGS.getSDIDataitem('labvantage', ${primary:s_sampleid}, ${paramlistid}, '*', '*', '*', '*', '*', '*');		// Current PL

// Check if results exists in the current PL
if (SGS.checkResults('labvantage', currentSDIDataItem, ${paramlistid}, ${paramlistversionid}.toString(), ${variantid}, ${dataset}.toString(), 'CalculationDate', 'Final Value', '*', false)) {
    // Get Taxa value for Phytoplankton
	def taxaPhyto = SGS.getTextResults('labvantage', currentSDIDataItem, phytoParamList, '*', '*', '*', 'Taxa', 'Standard', '*', false);
	def taxaZoo = SGS.getTextResults('labvantage', currentSDIDataItem, zooParamList, '*', '*', '*', 'Taxa', 'Standard', '*', false);

    if ((taxaPhyto.length > 0 && taxaPhyto[0] != 'Pass') || (taxaZoo.length > 0 && taxaZoo[0] != 'Pass')) { return ''; }

    // Check if there's result for phytoplankton and log + pi
	if (taxaPhyto.length > 0 && taxaPhyto[0] == 'Pass') {
        def SDIDataItemPhyto=SGS.getSDIDataitem('labvantage', ${primary:s_sampleid}, phytoParamList, '*', ${variantid}, ${dataset}.toString(), ParamID, ParamType, 'max');
        // Get the replicates number
        def maxRep=SGS.getMaxReplicates('labvantage', ${primary:s_sampleid}, SDIDataItemPhyto, SDIDataItemPhyto.paramlistversionid, SDIDataItemPhyto.variantid, ""+SDIDataItemPhyto.dataset, ParamID , ParamType);
        for(int i in 1..maxRep) {
            def MDENumber=SGS.getNumericResults('labvantage',SDIDataItemPhyto, SDIDataItemPhyto.paramlistid, SDIDataItemPhyto.paramlistversionid, SDIDataItemPhyto.variantid, ""+SDIDataItemPhyto.dataset, ParamID , ParamType, Integer.toString(i), true)
            if (MDENumber.length > 0) {
                if (MDENumber[0] > 0) {
                    double Pi = MDENumber[0] / MDEAbudance;
                    double PiLn = Math.log(Pi);
                    double Pi_PiLn = Pi * PiLn;
                    calcResult = calcResult + Math.abs(Pi_PiLn);
                }
            }
        }
    }

   	// Check if there's result for zooplankton and count all replicates
	if (taxaZoo.length > 0 && taxaZoo[0] == 'Pass') {
        def SDIDataItemZoo=SGS.getSDIDataitem('labvantage', ${primary:s_sampleid}, zooParamList, '*', ${variantid}, ${dataset}.toString(), ParamID, ParamType, 'max');
		// Get the replicates number
        def maxRep=SGS.getMaxReplicates('labvantage', ${primary:s_sampleid}, SDIDataItemZoo, SDIDataItemZoo.paramlistversionid, SDIDataItemZoo.variantid, ""+SDIDataItemZoo.dataset, ParamID , ParamType);
        for(int i in 1..maxRep) {
            def MDENumber=SGS.getNumericResults('labvantage',SDIDataItemZoo, SDIDataItemZoo.paramlistid, SDIDataItemZoo.paramlistversionid, SDIDataItemZoo.variantid, ""+SDIDataItemZoo.dataset, ParamID , ParamType, Integer.toString(i), true)
            if (MDENumber.length > 0) {
                if (MDENumber[0] > 0) {
                    double Pi = MDENumber[0] / MDEAbudance;
                    double PiLn = Math.log(Pi);
                    double Pi_PiLn = Pi * PiLn;
                    calcResult = calcResult + Math.abs(Pi_PiLn);
                }
            }
        }
    }
   	return calcResult
}


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
	Description:	Calculate the IndexDominance:Final Value for SGSIH_WIEHSLA292_Plankton_CALC_W
	Created by:		Marino Orsi
	Created:		2026-03-06
	Modification log (add below)
	Date		Name			Description
	2026-03-09	Julio			Update groovy
*/
// Declare variable general-purpose
def dummy = ${CalculationDate;Final Value};

// Variable declaration
String phytoParamList = 'SGSIH_WIEHSLA292_Phyto_W';	// PL for Phytoplankton data
String zooParamList = 'SGSIH_WIEHSLA292_Zoo_W';		// Pl for Zooplankton data
String ParamID = 'NumberIndividuals';				// Parameter ID
String ParamType = 'Final Value';					// Parameter Type
def calcResult = 0;									// Calculated result
def MDEAbudance = ${Abundance;Final Value};

// Get DataInfo object
def currentSDIDataItem = SGS.getSDIDataitem('labvantage', ${primary:s_sampleid}, ${paramlistid}, '*', '*', '*', '*', '*', '*');		// Current PL

// Check if results exists in the current PL
if (SGS.checkResults('labvantage', currentSDIDataItem, ${paramlistid}, ${paramlistversionid}.toString(), ${variantid}, ${dataset}.toString(), 'CalculationDate', 'Final Value', '*', false)) {
	// Get Taxa value for Phytoplankton
	def taxaPhyto = SGS.getTextResults('labvantage', currentSDIDataItem, phytoParamList, '*', '*', '*', 'Taxa', 'Standard', '*', false);
	def taxaZoo = SGS.getTextResults('labvantage', currentSDIDataItem, zooParamList, '*', '*', '*', 'Taxa', 'Standard', '*', false);

    if ((taxaPhyto.length > 0 && taxaPhyto[0] != 'Pass') || (taxaZoo.length > 0 && taxaZoo[0] != 'Pass')) { return ''; }

    // Check if there's result for phytoplankton and log + pi
	if (taxaPhyto.length > 0 && taxaPhyto[0] == 'Pass') {
        def SDIDataItemPhyto=SGS.getSDIDataitem('labvantage', ${primary:s_sampleid}, phytoParamList, '*', ${variantid}, ${dataset}.toString(), ParamID, ParamType, 'max');
        // Get the replicates number
        def maxRep=SGS.getMaxReplicates('labvantage', ${primary:s_sampleid}, SDIDataItemPhyto, SDIDataItemPhyto.paramlistversionid, SDIDataItemPhyto.variantid, ""+SDIDataItemPhyto.dataset, ParamID , ParamType);
        for(int i in 1..maxRep) {
            def MDENumber=SGS.getNumericResults('labvantage',SDIDataItemPhyto, SDIDataItemPhyto.paramlistid, SDIDataItemPhyto.paramlistversionid, SDIDataItemPhyto.variantid, ""+SDIDataItemPhyto.dataset, ParamID , ParamType, Integer.toString(i), true)
            if (MDENumber.length > 0) {
                if (MDENumber[0] > 0) {
                    double Pi = MDENumber[0] / MDEAbudance;
                    double PiPower = Math.pow(Pi,2);
                    calcResult = calcResult + Math.abs(PiPower);
                }
            }
        }
    }

    // Check if there's result for zooplankton and count all replicates
	if (taxaZoo.length > 0 && taxaZoo[0] == 'Pass') {
        def SDIDataItemZoo=SGS.getSDIDataitem('labvantage', ${primary:s_sampleid}, zooParamList, '*', ${variantid}, ${dataset}.toString(), ParamID, ParamType, 'max');
        // Get the replicates number
        def maxRep=SGS.getMaxReplicates('labvantage', ${primary:s_sampleid}, SDIDataItemZoo, SDIDataItemZoo.paramlistversionid, SDIDataItemZoo.variantid, ""+SDIDataItemZoo.dataset, ParamID , ParamType);
        for(int i in 1..maxRep) {
            def MDENumber=SGS.getNumericResults('labvantage',SDIDataItemZoo, SDIDataItemZoo.paramlistid, SDIDataItemZoo.paramlistversionid, SDIDataItemZoo.variantid, ""+SDIDataItemZoo.dataset, ParamID , ParamType, Integer.toString(i), true)
            if (MDENumber.length > 0) {
                if (MDENumber[0] > 0) {
                    double Pi = MDENumber[0] / MDEAbudance;
                    double PiPower = Math.pow(Pi,2);
                    calcResult = calcResult + Math.abs(PiPower);
                }
            }
        }
    }
   	return calcResult
}


======================================



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

