/*
    Description:    DQM Training session 02
    Created by:     DQM team
    Created:        2025-10-15
    PL:             PL005710
    Parameter:      TotalSum
    DBase PrePROD: TBLUEAZ8
    DBase PROD: PBLUELV8
*/

// Generic variable to hold the calculated data
def CalcResult = 0;

// Variable to hold data related to the SDI
def currentDS = ${dataset};
def SDIDataItem = SGS.getSDIDataitem('pbluelv8', ${primary:s_sampleid}, ${paramlistid}, ${paramlistversionid}.toString(), ${variantid}, currentDS.toString(), '*', '*', '*');

// Execute code only if there's value on AnalystName parameter
if (SGS.checkResults('pbluelv8', SDIDataItem, ${paramlistid}, ${paramlistversionid}.toString(), ${variantid}, 'max', 'AnalystName', 'DataEntry', 'max', true))
{
    CalcResult = ${Blank1;DataEntry}+${Blank2;DataEntry}+${Blank3;DataEntry};
    return CalcResult
}


/*
    Description:    DQM Training session 02
    Created by:     DQM team
    Created:        2025-10-15
    PL:             PL005710
    Parameter:      TotalSum01
    DBase PrePROD: TBLUEAZ8
    DBase PROD: PBLUELV8
*/

// Generic variable to hold the calculated data
def CalcResult = 0;
def numBlank1 = 0;
def numBlank2 = 0;
def numBlank3 = 0;
def DataEntryBlank1;
def DataEntryBlank2;
def DataEntryBlank3;

// Variable to hold data related to the SDI
def currentDS = ${dataset};
// Field Condition PL
def FieldPL = 'PL005710';
// SDIDataItem database conection
def SDIDataItem = SGS.getSDIDataitem('pbluelv8', ${primary:s_sampleid}, ${paramlistid}, ${paramlistversionid}.toString(), ${variantid}, currentDS.toString(), '*', '*', '*');

// Execute code only if there's value on AnalystName parameter
if (SGS.checkResults('pbluelv8', SDIDataItem, ${paramlistid}, ${paramlistversionid}.toString(), ${variantid}, 'max', 'AnalystName', 'DataEntry', 'max', true))
{
    // Get an array with parameter value from the PL005710 PL
    DataEntryBlank1 = SGS.getNumericResults('pbluelv8', SDIDataItem, FieldPL, '*', '*', 'max', 'Blank1', 'DataEntry', '*', false);
	DataEntryBlank2 = SGS.getNumericResults('pbluelv8', SDIDataItem, FieldPL, '*', '*', 'max', 'Blank2', 'DataEntry', '*', false);
    DataEntryBlank3 = SGS.getNumericResults('pbluelv8', SDIDataItem, FieldPL, '*', '*', 'max', 'Blank3', 'DataEntry', '*', false);
	if (DataEntryBlank1.length > 0) { numBlank1 = DataEntryBlank1[0]; }
    if (DataEntryBlank2.length > 0) { numBlank2 = DataEntryBlank2[0]; }
    if (DataEntryBlank3.length > 0) { numBlank3 = DataEntryBlank3[0]; }
    
    CalcResult = (numBlank1 + numBlank2 + numBlank3);
    if (CalcResult > 0) return CalcResult
}



/*
    Description:    DQM Training session 02
    Created by:     DQM team
    Created:        2025-10-15
    PL:             PL005711
    Parameter:      TotalSum
    DBase PrePROD: TBLUEAZ8
    DBase PROD: PBLUELV8
*/

def result=0;

switch(${AnalystName;DataEntry})
{            
         case ['Carlos','Javier'] :
            result=(${Blank1;DataEntry} + ${Blank2;DataEntry});
            break; 
         case ['Grace'] :
            result=2;
            break; 
         case ['Marino'] :
            result=3;
            break; 
         default :
            result="";
            break; 
}
return result


/*
    Description:    DQM Training session 02
    Created by:     DQM team
    Created:        2025-10-15
    PL:             PL005711
    Parameter:      TotalSum01
    DBase PrePROD: TBLUEAZ8
    DBase PROD: PBLUELV8
*/

def result=0;

switch(${AnalystName;DataEntry})
{            
         case ['Carlos','Javier'] :
            result=Math.round((${Blank1;DataEntry} + ${Blank2;DataEntry}));
            break; 
         case ['Grace'] :
            result=2;
            break; 
         case ['Marino'] :
            result=3;
            break; 
         default :
            result="";
            break; 
}
return result


/*
    Description:    DQM Training session 02
    Created by:     DQM team
    Created:        2025-10-15
    PL:             PL005711
    Parameter:      TotalSumAllOtherImps
    DBase PrePROD: TBLUEAZ8
    DBase PROD: PBLUELV8
*/

def result=0;

switch(${AnalystName;DataEntry})
{            
         case ['Carlos','Javier'] :
            result=(${Blank1;DataEntry} + ${Blank2;DataEntry}).round(1);
            break; 
         case ['Grace'] :
            result=2;
            break; 
         case ['Marino'] :
            result=3;
            break; 
         default :
            result="";
            break; 
}
return result


/*
	Description:	Calculate the Multiplier:Final Value for Sampling_FlowCorrected_AA PL
	Created by:		Marino Orsi
	Created:		2024-03-15
	Modification log (add below)
	Date			Name			Description
	2025-02-28		Marino Orsi		Add param to choose wheter to use or not the Field Condition
	2025-05-15		Marino Orsi		Added dummy parameter to trigger Redo Calculation
	yyyy-MM-dd		xxxxx			xxxxx

	NOTES
		1. Flow rate correction by the temperature and pressure according to SNI method - ASTM method doesn't use the sqrt on the formula.
*/

// Dummy parameter to trigger Redo Calculation
def dummy = ${FieldConditionPL;Final Value}

// Variable initialization
def CalcResult = 0;
def AirVolume = 0;
def FlowRate;
def SamplingDuration;
def SampledAirVolume;
def Temperature;
def AtmosphericPressure;

// Exit and return 1 if it's not a Unknown sample
if (!(${primary:qcsampletype} in ['Unknown','Dup'])) {return 1;}

// Field Condition PL
def FieldPL = 'Field_Condition_AA';

// Get SDIDataItem
def SDIDataItem = SGS.getSDIDataitem('labvantage', ${primary:s_sampleid}, ${paramlistid}, ${paramlistversionid}.toString(), ${variantid}, ${dataset}.toString(), 'Multiplier', 'Final Value', 'max');

// Get FieldConditionPL value and check if Field PL will be used
def UseFieldConditionPL = SGS.getTextResults('labvantage', SDIDataItem, ${paramlistid}, ${paramlistversionid}.toString(), ${variantid}, ${dataset}.toString(), 'FieldConditionPL', 'Final Value', 'max', true);

if (UseFieldConditionPL.length > 0)
{
	if (UseFieldConditionPL[0] == "Y")
	{
		// Get an array with parameter value from the Field_Condition PL
		FlowRate = SGS.getNumericResults('labvantage', SDIDataItem, FieldPL, '*', '*', 'max', 'FlowRate', 'Final Value', '*', false);
		SamplingDuration = SGS.getNumericResults('labvantage', SDIDataItem, FieldPL, '*', '*', 'max', 'SamplingDuration', 'Final Value', '*', false);
		SampledAirVolume = SGS.getNumericResults('labvantage', SDIDataItem, FieldPL, '*', '*', 'max', 'SampledAirVolume', 'Final Value', '*', false);
		if (SampledAirVolume.length > 0) { AirVolume = SampledAirVolume[0] / 1000; }
		Temperature = SGS.getNumericResults('labvantage', SDIDataItem, FieldPL, '*', '*', 'max', 'Temperature', 'Final Value', '*', false);
		AtmosphericPressure = SGS.getNumericResults('labvantage', SDIDataItem, FieldPL, '*', '*', 'max', 'AtmosphericPressure', 'Final Value', '*', false);
	}
	else
	{
		// Get an array with parameter value from the same PL
		FlowRate = SGS.getNumericResults('labvantage', SDIDataItem, ${paramlistid}, '#', '#', '#', 'FlowRate', 'Final Value', '*', true);
		SamplingDuration = SGS.getNumericResults('labvantage', SDIDataItem, ${paramlistid}, '#', '#', '#', 'SamplingDuration', 'Final Value', '#', true);
		SampledAirVolume = SGS.getNumericResults('labvantage', SDIDataItem, ${paramlistid}, '#', '#', '#', 'SampledAirVolume', 'Final Value', '#', true);
		if (SampledAirVolume.length > 0) { AirVolume = SampledAirVolume[0]; }
		Temperature = SGS.getNumericResults('labvantage', SDIDataItem, ${paramlistid}, '#', '#', '#', 'Temperature', 'Final Value', '#', true);
		AtmosphericPressure = SGS.getNumericResults('labvantage', SDIDataItem, ${paramlistid}, '#', '#', '#', 'AtmosphericPressure', 'Final Value', '#', true);
	}

	// If the sampled volume is filled, use it on the calculation; else, calculate it from the flow rate and duration
	if (SampledAirVolume.length == 0)
	{
		FlowRate = SGS.average(FlowRate);
		FlowRate = FlowRate * Math.sqrt((298 * AtmosphericPressure[0]) / ((Temperature[0] + 273) * 760));
		CalcResult = FlowRate * SamplingDuration[0];
	}
	else
	{
		CalcResult = AirVolume * (298 * AtmosphericPressure[0]) / ((Temperature[0] + 273) * 760);
	}

	return CalcResult;
///// End    

def currentDS = ${dataset};
def SDIDataItem = SGS.getSDIDataitem('pbluelv8', ${primary:s_sampleid}, ${paramlistid}, ${paramlistversionid}.toString(), ${variantid}, currentDS.toString(), '*', '*', '*');


def difference = SGS.getNumericResults('pbluelv8', SDIDataItem, 'PL005605', '*', '*', 'max', 'NCSWeight_IgnitionN', 'DataEntry', 'max', true)

if (difference){
    def calc = Math.abs((difference[0] - ${PL005602;*;BEBRU|Container_Weight;DataEntry;max}) / ${PL005602;*;BEBRU|SampleWeight;SystemCalculation;max} *100)
    return calc 
}

return Math.abs((SGS.getNumericResults('pbluelv8', SDIDataItem, 'PL005602', '*', '*', 'max', 'NCSWeight_Ignition2', 'DataEntry', 'max', true)[0] - ${PL005602;*;BEBRU|Container_Weight;DataEntry;max}) / ${PL005602;*;BEBRU|SampleWeight;SystemCalculation;max} *100)



/*
    Description:    Calculate the Multiplier:Final Value for SGSIH_WIEHSLA283_AA
    Created by:     Marino Orsi
    Created:        2024-02-16
    Modification log (add below)
    Date            Name                            Description
    2024-03-26      Marino Orsi                     Changed PL to Sampling_FlowCorrected_AA of the same workitem
    yyyy-MM-dd      xxxxx                           xxxxx
*/

// Variable declaration - modify the parameters below with the source (where the data comes from) and destination (where the data goes to)
def SourcePL = 'Sampling_FlowCorrected_AA';
def PrepPL = 'SGSIH_WIEHSLA283_Prep_AA'
String ParamID = ${#;#;#;paramid}.toString();	    // define the parameter for the calculation
String ParamType = ${#;#;#;paramtype}.toString(); 	// define the parameter type
def SampleType = ${primary:qcsampletype};		    // define the sample type

// Generic variable to hold the calculated data
def CalcResult = 0;

// Variable to hold data related to the SDI
def SDIDataItem= SGS.getSDIDataitem('labvantage', ${primary:s_sampleid}, ${paramlistid}, ${paramlistversionid}.toString(), ${variantid}, ${dataset}.toString(), ParamID, ParamType, 'max');

// Execute code only if there's value on DF parameter
if (SGS.checkResults('labvantage', SDIDataItem, ${paramlistid}, ${paramlistversionid}.toString(), ${variantid}, 'max', 'DF', 'Final Value', 'max', true))
{
    if (SampleType == "Unknown")
    {
        def PrepMultiplier = SGS.getNumericResults('labvantage', SDIDataItem, PrepPL, '*', '*', 'max', ParamID, ParamType, 'max', true);
        def SourceValue = SGS.getNumericResults('labvantage', SDIDataItem, SourcePL, '*', '*', 'max', ParamID, ParamType, 'max', true);
        CalcResult = 1 / SourceValue[0] * PrepMultiplier[0];
    }
    else if (SampleType == "Dup")
    {
        def LinkedUnkID = SGS.getUnknownSampleLink(SDIDataItem)
        def SourceValue = SGS.sampleNumericResults('labvantage', SDIDataItem, LinkedUnkID[0], '#', '#', '#', 'max', 'Multiplier', 'Final Value', 'max')
        CalcResult = SourceValue[0];
    }
    else
    {
        CalcResult = 1;
    }
    return CalcResult;
}




/*
    Description:    Calculate the #:Final Value for SGSIH_WIEHSLA283_AA
    Created by:     Marino Orsi
    Created:        2024-02-16
    Modification log (add below)
    Date            Name                            Description
    2024-03-14      Marino Orsi                     Added filter area used as a fraction
    yyyy-MM-dd      xxxxx                           xxxxx
    2025-05-19      Grace Badua          Added Dummy declaration triger and replaced ${#;#;#;paramid} with its actual parameter
*/

// Variable declaration
def SampleType = ${primary:qcsampletype};

// Dummy declaration for trigger
def dummy=${DF;Final Value};

// Generic variable to hold the calculated data
def CalcResult = 0;
def StdResult = 0;

// Exit if it's not a Unknown sample
if (!(SampleType in ['Unknown','Dup']))
{
	return CalcResult;
}

def SDIDataItem = SGS.getSDIDataitem('labvantage', ${primary:s_sampleid}, ${paramlistid}, ${paramlistversionid}.toString(), ${variantid}, ${dataset}.toString(), 'Multiplier', 'Final Value', 'max');

def FilterLength = SGS.getNumericResults('labvantage', SDIDataItem, ${paramlistid}, ${paramlistversionid}.toString(), ${variantid}, ${dataset}.toString(), 'Length', 'Final Value', 'max', true)[0];
def FilterWidth = SGS.getNumericResults('labvantage', SDIDataItem, ${paramlistid}, ${paramlistversionid}.toString(), ${variantid}, ${dataset}.toString(), 'Height', 'Final Value', 'max', true)[0];
def SampleVolume = SGS.getNumericResults('labvantage', SDIDataItem, ${paramlistid}, ${paramlistversionid}.toString(), ${variantid}, ${dataset}.toString(), 'SampleVolume', 'Final Value', 'max', true)[0];
def FilterFraction = 1 / SGS.getNumericResults('labvantage', SDIDataItem, ${paramlistid}, ${paramlistversionid}.toString(), ${variantid}, ${dataset}.toString(), 'FilterSurfaceFraction', 'Final Value', 'max', true)[0];
def Multiplier = SGS.getNumericResults('labvantage', SDIDataItem, ${paramlistid}, ${paramlistversionid}.toString(), ${variantid}, ${dataset}.toString(), 'Multiplier', 'Final Value', 'max', true)[0];
def Dilution = SGS.getNumericResults('labvantage', SDIDataItem, ${paramlistid}, ${paramlistversionid}.toString(), ${variantid}, ${dataset}.toString(), 'DF', 'Final Value', 'max', true)[0];

def FilterAreaFactor = (FilterLength * FilterWidth) / ((FilterLength * FilterWidth) * FilterFraction)

def BlankCorrectedValue = SGS.getNumericResults('labvantage', SDIDataItem, ${paramlistid}, ${paramlistversionid}.toString(), ${variantid}, ${dataset}.toString(), '7439-92-1', 'BlankCorrected', 'max', true);
def UncorrectedValue = SGS.getNumericResults('labvantage', SDIDataItem, ${paramlistid}, ${paramlistversionid}.toString(), ${variantid}, ${dataset}.toString(), '7439-92-1', 'Standard', 'max', true);

if (BlankCorrectedValue.length == 0)
{
    StdResult = UncorrectedValue[0];
}
else
{
    StdResult = BlankCorrectedValue[0];
}

CalcResult = StdResult * FilterAreaFactor * (SampleVolume / 1000) * Multiplier * Dilution;

return CalcResult;