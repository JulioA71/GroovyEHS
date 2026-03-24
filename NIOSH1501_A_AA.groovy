/*
    Description:    Calculate the Multiplier:Final Value for NIOSH1501_A_AIH/NIOSH1501_B_AIH
    Created by:     Marino Orsi
    Created:        2023-11-28
    Modification log (add below)
    Date            Name                            Description
    2023-11-14      Marino Orsi                     Corrected wrong value when sampletype = Dup
    yyyy-MM-dd      xxxxx                           xxxxx
    Parameter       Multiplier (Final Value)
*/

// Variable declaration - modify the parameters below with the source (where the data comes from) and destination (where the data goes to)
def SourcePL = 'Sampling_AA';
def SourcePLUncorrected = 'Sampling_Uncorrected_AA';
String ParamID = ${#;#;#;paramid}.toString();	    // define the parameter for the calculation
String ParamType = ${#;#;#;paramtype}.toString(); 	// define the parameter type
def SampleType = ${primary:qcsampletype};		    // define the sample type

// Generic variable to hold the calculated data
def CalcResult = 0;

// Variable to hold data related to the SDI
def SDIDataItem = SGS.getSDIDataitem('labvantage', ${primary:s_sampleid}, ${paramlistid}, ${paramlistversionid}.toString(), ${variantid}, ${dataset}.toString(), ParamID, ParamType, 'max');

// Execute code only if there's value on DF parameter
if (SGS.checkResults('labvantage', SDIDataItem, ${paramlistid}, ${paramlistversionid}.toString(), ${variantid}, 'max', 'DF', 'Final Value', 'max', true))
{
    if (SampleType == "Unknown")
    {
        def CorrectedValue = SGS.getNumericResults('labvantage', SDIDataItem, SourcePL, '*', '*', 'max', ParamID, ParamType, 'max', false);
        def UncorrectedValue = SGS.getNumericResults('labvantage', SDIDataItem, SourcePLUncorrected, '*', '*', 'max', ParamID, ParamType, 'max', false);
        if (CorrectedValue.length > 0)
        {
            CalcResult = CorrectedValue[0];
        }
        else if (UncorrectedValue.length > 0)
        {
            CalcResult = UncorrectedValue[0];
        }
        else
        {
            return 0;
        }
    }
    else if (SampleType == "Dup")
    {
        def LinkedUnkID = SGS.getUnknownSampleLink(SDIDataItem);
        def SourceValue = SGS.sampleNumericResults('labvantage', SDIDataItem, LinkedUnkID[0], ${paramlistid}, '*', '*', 'max', ParamID, ParamType, 'max');
        CalcResult = 1 / SourceValue[0];
    }
    else
    {
        CalcResult = 1;
    }
    return 1 / CalcResult
}


/*
    Description:    Calculate the #:Breakthrough for NIOSH1501_A_AIH/NIOSH1501_B_AIH
    Created by:     Marino Orsi
    Created:        2023-11-28
    Modification log (add below)
    Date            Name                            Description
    yyyy-MM-dd      xxxxx                           xxxxx
    Parameter       71-43-2 (Breakthrough)
*/

// Variable declaration
String ParamID = ${#;#;#;paramid}.toString();	    // define the parameter for the calculation
String ParamType = 'Standard';                   	// define the parameter type
def SampleType = ${primary:qcsampletype};		    // define the sample type

// Generic variable to hold the calculated data
def CalcResult = 0;

// Variable to hold data related to the SDI
def SDIDataItem = SGS.getSDIDataitem('labvantage', ${primary:s_sampleid}, ${paramlistid}, ${paramlistversionid}.toString(), ${variantid}, ${dataset}.toString(), 'Multiplier', 'Final Value', 'max');

// Standard values
def ParamFS = SGS.getNumericResults('labvantage', SDIDataItem, ${paramlistid}, ${paramlistversionid}.toString(), ${variantid}, ${dataset}.toString(), ParamID + '_FS', ParamType, 'max', true)[0];
def ParamBS = SGS.getNumericResults('labvantage', SDIDataItem, ${paramlistid}, ${paramlistversionid}.toString(), ${variantid}, ${dataset}.toString(), ParamID + '_BS', ParamType, 'max', true)[0];

if (SampleType in ['Unknown','Dup'] && (ParamBS >= (ParamFS/10)))
{
    CalcResult = ParamBS / ParamFS * 100;
    return CalcResult;
}
else
{
    return '';
}


/*
    Description:    Calculate the #:Absolute Value for NIOSH1501_A_AIH/NIOSH1501_B_AIH
    Created by:     Marino Orsi
    Created:        2023-11-28
    Modification log (add below)
    Date            Name                            Description
    yyyy-MM-dd      xxxxx                           xxxxx
    Parameter       71-43-2 (Absolute Value)
*/

// Variable declaration - modify below as needed
def QCType = 'Blank';                               // define the type of QC used for blank subtract
def SampleType = ${primary:qcsampletype};           // define the sample type
def ParamID = ${#;#;#;paramid};						// define the parameter ID
def ParamType = 'Absolute Value';
def DEParamList = 'NIOSH1501_A_Prep_AA';

// Generic variable to hold the calculated data
def CalcResult = 0;
def MassValue = 0;
def QCMassValue = 0;

// Get the sample SDI
def SDIDataItem = SGS.getSDIDataitem('labvantage', ${primary:s_sampleid}, ${paramlistid}, ${paramlistversionid}.toString(), ${variantid}, ${dataset}.toString(), 'Multiplier', 'Final Value', 'max');

// Values for calculation
def StdValue_FS = SGS.getNumericResults('labvantage', SDIDataItem, ${paramlistid}, ${paramlistversionid}.toString(), ${variantid}, ${dataset}.toString(), ParamID + '_FS', 'Standard', 'max', true)[0];
def StdValue_BS = SGS.getNumericResults('labvantage', SDIDataItem, ${paramlistid}, ${paramlistversionid}.toString(), ${variantid}, ${dataset}.toString(), ParamID + '_BS', 'Standard', 'max', true)[0];
def DesorptionEfficiency = SGS.getNumericResults('labvantage', SDIDataItem, DEParamList, '*', '*', 'max', ParamID, 'Final Value', 'max', false)[0];
def DilFS = SGS.getNumericResults('labvantage', SDIDataItem, ${paramlistid}, ${paramlistversionid}.toString(), ${variantid}, ${dataset}.toString(), 'DF', 'Final Value', '1', true)[0];
def DilBS = SGS.getNumericResults('labvantage', SDIDataItem, ${paramlistid}, ${paramlistversionid}.toString(), ${variantid}, ${dataset}.toString(), 'DF', 'Final Value', '2', true)[0];

// UNK mass calculation
MassValue = (StdValue_FS * DilFS * DesorptionEfficiency) + (StdValue_BS * DilBS * DesorptionEfficiency);

// Blank mass subtraction
if (SampleType in ['Unknown','Dup'])
{
	// Get the QC sample ID
	def QCSampleID = SGS.qcSampleId('labvantage', SDIDataItem, false, QCType, '');
	if (QCSampleID.length > 0)
	{
		// If the QC exists, get the QC SDI
		def QCSDIDataItem = SGS.getSDIDataitem('labvantage', QCSampleID[0], ${paramlistid}, ${paramlistversionid}.toString(), ${variantid}, ${dataset}.toString(), ParamID, ParamType, 'max');
		
		// Sum the Mass parameter replicate values
		def QCValueArray = SGS.sampleNumericResults('labvantage', QCSDIDataItem, QCSampleID[0], ${paramlistid}, ${paramlistversionid}.toString(), ${variantid}, ${dataset}.toString(), ParamID, ParamType, 'max');
		if (QCValueArray.length > 0)
		{
			QCMassValue = QCValueArray[0];
		}
	}
}

CalcResult = MassValue - QCMassValue;

return CalcResult;



/*
    Description:    Calculate the #:Final Value for NIOSH1501_A_AIH/NIOSH1501_B_AIH
    Created by:     Marino Orsi
    Created:        2023-11-14
    Modification log (add below)
    Date            Name                            Description
    yyyy-MM-dd      xxxxx                           xxxxx
    Parameter       71-43-2 (Final Value)
*/

// Variable declaration - modify below as needed
def SampleType = ${primary:qcsampletype};		// define the sample type

// Generic variable to hold the calculated data
def CalcResult = 0;

// Exit and return 0 if it's not an Unknown/Dup sample
if (!(SampleType in ['Unknown','Dup']))
{
	return CalcResult;
}

CalcResult = ${#;Absolute Value} * ${Multiplier;Final Value} / 1000;

return CalcResult;