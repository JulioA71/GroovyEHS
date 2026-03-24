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
def QCParam = '7446-09-5';
def QCPType = 'Standard';
def DestinationP = '7446-09-5';
def DestinationPType = 'Final Value';

// Generic variable to hold the calculated data
def CalcResult = 0;

// Variable to hold data related to the SDI
def SampleType = ${primary:qcsampletype};
def SDIDataItem = SGS.getSDIDataitem('labvantage', ${primary:s_sampleid}, ${paramlistid}, ${paramlistversionid}.toString(), ${variantid}, ${dataset}.toString(), DestinationP, DestinationPType, 'max');
def QCSampleID = SGS.qcSampleId('labvantage', SDIDataItem, false, QCType, '');
def QCSDIDataItem = SGS.getSDIDataitem('labvantage', QCSampleID, ${paramlistid}, ${paramlistversionid}.toString(), ${variantid}, ${dataset}.toString(), QCParam, QCPType, 'max');

// Variable declaration
def Dilution = ${DF;Final Value};
def SampleVolume = ${SampleVolume;Final Value};
def Multiplier = ${Multiplier;Final Value};
def UnkStd = ${7446-09-5;Standard};
def MWRatio = 64 / 96;
def SampleVolRatio = 250 / SampleVolume;

// Generic variable to hold the calculated data
def CalcResult = 0;

// QC Result value
def QCResultArray = SGS.sampleNumericResults('labvantage', QCSDIDataItem, QCSampleID, ${paramlistid}, '*', '*', 'max', QCParam, QCPType, 'max');
def QCResult = 0;
if (QCResultArray.length > 0)
{
    QCResult = SGS.average(QCResultArray);
}

// Final calculation
if (SampleType == 'Unknown')
{
    CalcResult = MWRatio * (UnkStd - QCResult) * Dilution * SampleVolRatio * Multiplier;
}

return CalcResult