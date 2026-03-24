/*
    Description:    Calculation for APHA9215_E_Calculation_W (MPN)
    Created by:     DQM team
    PL:             APHA9221_B_TC_Calculation_W
    Parameter:      MPNValue (Final Value)
    Date:           2024.02.29
    Modification log (add below)
    Date            Name      Description
    
*/
// Variable declaration - modify the parameters below with the source (where the data comes from) and destination (where the data goes to)
def SourceParamID = 'MPN';
def SourcePType = 'Final Value';
String ParamID = ${#;#;#;paramid}.toString();	    // define the parameter for the calculation
String ParamType = ${#;#;#;paramtype}.toString(); 	// define the parameter type
def SampleType = ${primary:qcsampletype};		    // define the sample type

// Generic variable to hold the calculated data
def CalcResult = '';
def bResult = false;

// Variable to hold data related to the SDI
def SDIDataItem= SGS.getSDIDataitem('labvantage', ${primary:s_sampleid}, ${paramlistid}, ${paramlistversionid}.toString(), ${variantid}, ${dataset}.toString(), ParamID, ParamType, 'max');

// Obtain the maximum number of replicates
def maxRep=SGS.getMaxReplicates('labvantage', ${primary:s_sampleid}, ${paramlistid}, ${paramlistversionid}.toString(), ${variantid}, ${dataset}.toString(), SourceParamID , SourcePType);

def MPNCombination = '';
// Reading the replicates per sample to get the differents values
for(int i in 1..maxRep) {
  def MPNTubes=SGS.getTextResults('labvantage', SDIDataItem, SDIDataItem.paramlistid,SDIDataItem.paramlistversionid,SDIDataItem.variantid, ""+SDIDataItem.dataset, SourceParamID , SourcePType, Integer.toString(i), true)
  if (MPNTubes.length > 0) {
    if (i == 1)
        MPNCombination = MPNTubes[0]
    else
        MPNCombination = MPNCombination + "-" + MPNTubes[0]
    }
}
CalcResult=SGS.getMPNOperFinalValue('labvantage',${paramlistid},${paramlistversionid},${variantid},"MPNValue","Final Value",MPNCombination);

return CalcResult


/*
    Description:    Calculation for APHA9215_E_Calculation_W (MPN)
    Created by:     DQM team
    PL:             APHA9221_B_TC_Calculation_W
    Parameter:      LowConfidenceLimit (Final Value)
    Date:           2024.02.29
    Modification log (add below)
    Date            Name      Description
    
*/
// Variable declaration - modify the parameters below with the source (where the data comes from) and destination (where the data goes to)
def SourceParamID = 'MPN';
def SourcePType = 'Final Value';
String ParamID = ${#;#;#;paramid}.toString();	    // define the parameter for the calculation
String ParamType = ${#;#;#;paramtype}.toString(); 	// define the parameter type
def SampleType = ${primary:qcsampletype};		    // define the sample type

// Generic variable to hold the calculated data
def CalcResult = '';
def bstatus = false;

// Variable to hold data related to the SDI
def SDIDataItem= SGS.getSDIDataitem('labvantage', ${primary:s_sampleid}, ${paramlistid}, ${paramlistversionid}.toString(), ${variantid}, ${dataset}.toString(), ParamID, ParamType, 'max');

// Obtain the maximum number of replicates
def maxRep=SGS.getMaxReplicates('labvantage', ${primary:s_sampleid}, ${paramlistid}, ${paramlistversionid}.toString(), ${variantid}, ${dataset}.toString(), SourceParamID , SourcePType);

def MPNCombination = '';
// Reading the replicates per sample to get the differents values
for(int i in 1..maxRep) {
  def MPNTubes=SGS.getTextResults('labvantage', SDIDataItem, SDIDataItem.paramlistid,SDIDataItem.paramlistversionid,SDIDataItem.variantid, ""+SDIDataItem.dataset, SourceParamID , SourcePType, Integer.toString(i), true)
  if (MPNTubes.length > 0) {
    if (i == 1)
        MPNCombination = MPNTubes[0]
    else
        MPNCombination = MPNCombination + "-" + MPNTubes[0]
    }
}
CalcResult=SGS.getMPNLowerUnc('labvantage',${paramlistid},${paramlistversionid},${variantid},"MPNValue","Final Value",MPNCombination);
if (CalcResult >= 0)
    bstatus = true
return (!bstatus?'':CalcResult)


/*
    Description:    Calculation for APHA9215_E_Calculation_W (MPN)
    Created by:     DQM team
    PL:             APHA9221_B_TC_Calculation_W
    Parameter:      HighConfidenceLimit (Final Value)
    Date:           2024.02.29
    Modification log (add below)
    Date            Name      Description
    
*/
// Variable declaration - modify the parameters below with the source (where the data comes from) and destination (where the data goes to)
def SourceParamID = 'MPN';
def SourcePType = 'Final Value';
String ParamID = ${#;#;#;paramid}.toString();	    // define the parameter for the calculation
String ParamType = ${#;#;#;paramtype}.toString(); 	// define the parameter type
def SampleType = ${primary:qcsampletype};		    // define the sample type

// Generic variable to hold the calculated data
def CalcResult = '';
def bstatus = false;

// Variable to hold data related to the SDI
def SDIDataItem= SGS.getSDIDataitem('labvantage', ${primary:s_sampleid}, ${paramlistid}, ${paramlistversionid}.toString(), ${variantid}, ${dataset}.toString(), ParamID, ParamType, 'max');

// Obtain the maximum number of replicates
def maxRep=SGS.getMaxReplicates('labvantage', ${primary:s_sampleid}, ${paramlistid}, ${paramlistversionid}.toString(), ${variantid}, ${dataset}.toString(), SourceParamID , SourcePType);

def MPNCombination = '';
// Reading the replicates per sample to get the differents values
for(int i in 1..maxRep) {
  def MPNTubes=SGS.getTextResults('labvantage', SDIDataItem, SDIDataItem.paramlistid,SDIDataItem.paramlistversionid,SDIDataItem.variantid, ""+SDIDataItem.dataset, SourceParamID , SourcePType, Integer.toString(i), true)
  if (MPNTubes.length > 0) {
    if (i == 1)
        MPNCombination = MPNTubes[0]
    else
        MPNCombination = MPNCombination + "-" + MPNTubes[0]
    }
}
CalcResult=SGS.getMPNUpperUnc('labvantage',${paramlistid},${paramlistversionid},${variantid},"MPNValue","Final Value",MPNCombination);
if (CalcResult >= 0)
    bstatus = true
return (!bstatus?'':CalcResult)