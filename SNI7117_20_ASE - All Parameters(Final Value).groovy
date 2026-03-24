/*
    Description:    Calculate the #:Final Value for SNI7117_20_ASE
    Created by:     Marino Orsi
    Created:        2023-10-30
    Modification log (add below)
    Date            Name                            Description
    yyyy-MM-dd      xxxxx                           xxxxx
*/

// Variable declaration - modify below as needed
def QCType = 'FB';								// define the type of QC used for blank subtract
String ParamID = ${#;#;#;paramid}.toString();	// define the parameter for the calculation
def ParamType = 'Final Value';					// define the parameter type
def SampleType = ${primary:qcsampletype};		// define the sample type

// Generic variable to hold the calculated data
def MassValue = 0;
def QCValue = 0;
def DFValue = 0;
def MultiplierValue = 0;
def CalcResult = 0;

// Exit and return 0 if it's not an Unknown sample
if (SampleType != 'Unknown')
{
	return CalcResult;
}

// Get the sample SDI
def SDIDataItem = SGS.getSDIDataitem('labvantage', ${primary:s_sampleid}, ${paramlistid}, ${paramlistversionid}.toString(), ${variantid}, ${dataset}.toString(), ParamID, ParamType, 'max');

// Execute code only if there's value on DF parameter
if (SGS.checkResults('labvantage', SDIDataItem, ${paramlistid}, ${paramlistversionid}.toString(), ${variantid}, 'max', 'DF', 'Final Value', 'max', true))
{

	// Sum the Mass parameter replicate values
	def MassValueArray = SGS.sampleNumericResults('labvantage', SDIDataItem, ${primary:s_sampleid}, ${paramlistid}, '*', '*', 'max', ParamID, 'Mass', '*');
	if (MassValueArray.length > 0)
	{
		MassValue = SGS.sum(MassValueArray);
	}

	// Get the Dilution Factor and Multiplier
	def DilutionFactorArray = SGS.sampleNumericResults('labvantage', SDIDataItem, ${primary:s_sampleid}, ${paramlistid}, '*', '*', 'max', 'DF', 'Final Value', '*');
	if (DilutionFactorArray.length > 0)
	{
		DFValue = DilutionFactorArray[0];
	}
	def MultiplierArray = SGS.sampleNumericResults('labvantage', SDIDataItem, ${primary:s_sampleid}, ${paramlistid}, '*', '*', 'max', 'Multiplier', 'Final Value', '*');
	if (MultiplierArray.length > 0)
	{
		MultiplierValue = MultiplierArray[0];
	}

	// Get the QC sample ID
	def QCSampleID = SGS.qcSampleId('labvantage', SDIDataItem, false, QCType, '');
	if (QCSampleID.length > 0)
	{
		// If the QC exists, get the QC SDI
		def QCSDIDataItem = SGS.getSDIDataitem('labvantage', QCSampleID[0], ${paramlistid}, ${paramlistversionid}.toString(), ${variantid}, ${dataset}.toString(), ParamID, ParamType, 'max');
		
		// Sum the Mass parameter replicate values
		def QCValueArray = SGS.sampleNumericResults('labvantage', QCSDIDataItem, QCSampleID[0], ${paramlistid}, '*', '*', 'max', ParamID, 'Mass', '*');
		if (QCValueArray.length > 0)
		{
			QCValue = SGS.sum(QCValueArray);
		}
	}

	// Calculation - (Mass Value - QCValue) * DF * Multiplier / 1000
	CalcResult = (MassValue - QCValue) * DFValue * MultiplierValue / 1000;

	return CalcResult;
}