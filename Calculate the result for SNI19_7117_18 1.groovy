/*
    Description:    Calculation for SNI19_7117_18
    Created by:     Marino Orsi
    Created:        2023-10-06
    Modification log (add below)
    Date            Name                            Description
    yyyy-MM-dd      xxxxx                           xxxxx
*/

// Variable declaration - modify below as needed
def QCType = 'MB';
def QCParam = '10028-15-6';
def QCPType = 'Standard';
def DestinationP = '10028-15-6';
def DestinationPType = 'Final Value';

// Generic variable to hold the calculated data
def CalcResult = 0;
def QCResult = 0;

// Exit if it's not a Unknown sample
def SampleType = ${primary:qcsampletype};
if (SampleType != 'Unknown')
{
	return CalcResult;
}

// Variable to hold data related to the sample SDI
def SDIDataItem = SGS.getSDIDataitem('labvantage', ${primary:s_sampleid}, ${paramlistid}, ${paramlistversionid}.toString(), ${variantid}, ${dataset}.toString(), DestinationP, DestinationPType, 'max');

// QC Result SDI and Value
def QCSampleID = SGS.qcSampleId('labvantage', SDIDataItem, false, QCType, '');
if (QCSampleID.length > 0)
{
	def QCSDIDataItem = SGS.getSDIDataitem('labvantage', QCSampleID[0], ${paramlistid}, ${paramlistversionid}.toString(), ${variantid}, ${dataset}.toString(), QCParam, QCPType, 'max');
	
	def QCResultArray = SGS.sampleNumericResults('labvantage', QCSDIDataItem, QCSampleID[0], ${paramlistid}, '*', '*', 'max', QCParam, QCPType, 'max');
	if (QCResultArray.length > 0)
	{
		QCResult = SGS.average(QCResultArray);
	}
}

// Variable declaration
def Dilution = ${DF;Final Value};
def SampleVolume = ${SampleVolume;Final Value};
def Multiplier = ${Multiplier;Final Value};
def UnkStd = ${7446-09-5;Standard};
def MWRatio = 64 / 96;
def SampleVolRatio = 250 / SampleVolume;

// Final calculation
if (SampleType == 'Unknown')
{
    CalcResult = MWRatio * (UnkStd - QCResult) * Dilution * SampleVolRatio * Multiplier;
}

return CalcResult