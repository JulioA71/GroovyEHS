/*
    ParamID:    Organic_Mineral_Fibers
*/
def CalcResult = '';
def status = false;
def acceptedFormat = "yyyy-MM-dd";
if (${DateAnalysis;Final Value} != null) {
    def currentDate = ${DateAnalysis;Final Value}.format(acceptedFormat)
    if (currentDate >= "2020-01-01") {
        bstatus = true
        if (${Organic_Mineral_Fibers;Standard;#} == 0)
            CalcResult = 'None'
        else
            CalcResult = 'Organic Fibres Detected'
    }
}
return CalcResult






/*
    Description:    Calculate the AsbestosDetected:Final Value for SGSIH_MEAUENVAS4964_AN602_S
    Created by:     Marino Orsi
    Created:        2024-05-21
    ParamID:        1332-21-4
    Modification log (add below)
    Date            Name                            Description
    yyyy-MM-dd      xxxxx                           xxxxx

    IF(OR(AMOS="",CHRYS="",CROC=""),"",IF(AND(AMOS=0,CHRYS=0,CROC=0,UNK_MIN_FIBRES=1),"None",IF(AND(AMOS=1,CHRYS=0,CROC=0),"Amosite","")&IF(AND(AMOS=1,CHRYS=1,CROC=0),"Amosite & Chrysotile","")&IF(AND(AMOS=1,CHRYS=0,CROC=1),"Amosite & Crocidolite","")&IF(AND(AMOS=1,CHRYS=1,CROC=1),"Amosite, Chrysotile & Crocidolite","")&IF(AND(AMOS=0,CHRYS=1,CROC=0),"Chrysotile","")&IF(AND(AMOS=0,CHRYS=1,CROC=1),"Chrysotile & Crocidolite","")&IF(AND(AMOS=0,CHRYS=0,CROC=1),"Crocidolite","")&IF(AND(AMOS=0,CHRYS=0,CROC=0),"No","")&" Asbestos Found at RL of 0.1g/kg"))

*/
// get SDIDataItem object
def SDIDataItem = SGS.getSDIDataitem('labvantage', ${primary:s_sampleid}, ${paramlistid}, ${paramlistversionid}.toString(), ${variantid}, ${dataset}.toString(), '1332-21-4', 'Final Value', 'max');
// get values from parameters
def Amosite = SGS.getNumericResults('labvantage', SDIDataItem, ${paramlistid}, ${paramlistversionid}.toString(), ${variantid}, ${dataset}.toString(), '12172-73-5', 'Final Value', '#', true)[0];
def Crocidolite = SGS.getNumericResults('labvantage', SDIDataItem, ${paramlistid}, ${paramlistversionid}.toString(), ${variantid}, ${dataset}.toString(), '12001-28-4', 'Final Value', '#', true)[0];
def Chrysotile = SGS.getNumericResults('labvantage', SDIDataItem, ${paramlistid}, ${paramlistversionid}.toString(), ${variantid}, ${dataset}.toString(), '12001-29-5', 'Final Value', '#', true)[0];
def UnkMinFibres = SGS.getNumericResults('labvantage', SDIDataItem, ${paramlistid}, ${paramlistversionid}.toString(), ${variantid}, ${dataset}.toString(), 'Unk_Mineral_Fibers', 'Standard', '#', true)[0];
// apply truth table
if (Amosite > 0 || Crocidolite > 0 || Chrysotile > 0) { return 'Yes'; }
if (UnkMinFibres > 0 && Amosite == 0 && Crocidolite == 0 && Chrysotile == 0) { return 'Unknown'; }
return 'No';



/*
    Description:    Calculate the Asbestos:Final Value for SGSIH_MEAUENVAS4964_AN602_S
    Created by:     Marino Orsi
    Created:        2024-05-21
    ParamID:        AsbestosDetected
    Modification log (add below)
    Date            Name                            Description
    yyyy-MM-dd      xxxxx                           xxxxx
    
    IF(AND(AMOS=0,CHRYS=0,CROC=0,UNK_MIN_FIBRES=1),"Unknown","")&IF(AND(AMOS=0,CHRYS=0,CROC=0,UNK_MIN_FIBRES=0),"No","")&IF(AND(AMOS=1,CHRYS=1,CROC=1),"Yes","")&IF(AND(AMOS=1,CHRYS=0,CROC=1),"Yes","")&IF(AND(AMOS=1,CHRYS=1,CROC=0),"Yes","")&IF(AND(AMOS=0,CHRYS=1,CROC=0),"Yes","")&IF(AND(AMOS=0,CHRYS=1,CROC=1),"Yes","")&IF(AND(AMOS=0,CHRYS=0,CROC=1),"Yes","")&IF(AND(AMOS=1,CHRYS=0,CROC=0),"Yes","")

*/
// get SDIDataItem object
def SDIDataItem = SGS.getSDIDataitem('labvantage', ${primary:s_sampleid}, ${paramlistid}, ${paramlistversionid}.toString(), ${variantid}, ${dataset}.toString(), 'AsbestosDetected', 'Final Value', 'max');

// get values from parameters
def Amosite = SGS.getNumericResults('labvantage', SDIDataItem, ${paramlistid}, ${paramlistversionid}.toString(), ${variantid}, ${dataset}.toString(), '12172-73-5', 'Final Value', '#', true)[0];
def AmositePresent = 0;
String AmositeStr = '';
def Crocidolite = SGS.getNumericResults('labvantage', SDIDataItem, ${paramlistid}, ${paramlistversionid}.toString(), ${variantid}, ${dataset}.toString(), '12001-28-4', 'Final Value', '#', true)[0];
def CrocidolitePresent = 0;
String CrocidoliteStr = ''
def Chrysotile = SGS.getNumericResults('labvantage', SDIDataItem, ${paramlistid}, ${paramlistversionid}.toString(), ${variantid}, ${dataset}.toString(), '12001-29-5', 'Final Value', '#', true)[0];
def ChrysotilePresent = 0;
String ChrysotileStr = ''
def UnkMinFibres = SGS.getNumericResults('labvantage', SDIDataItem, ${paramlistid}, ${paramlistversionid}.toString(), ${variantid}, ${dataset}.toString(), 'Unk_Mineral_Fibers', 'Standard', '#', true)[0];
String StringResult = '';

// apply truth table and build string
if (Amosite == '' || Crocidolite == '' || Chrysotile == '') { return ''; }
if (UnkMinFibres > 0 && Amosite == 0 && Crocidolite == 0 && Chrysotile == 0) { return 'None'; }

if (Amosite > 0)
{
    AmositePresent = 1;
    AmositeStr = 'Amosite';
}
if (Crocidolite > 0)
{
    CrocidolitePresent = 2;
    CrocidoliteStr = 'Crocidolite';
}
if (Chrysotile > 0)
{
    ChrysotilePresent = 4;
    ChrysotileStr = 'Chrysolite';
}

switch(AmositePresent + CrocidolitePresent + ChrysotilePresent) {
    case 0:
        return 'No Asbestos Found at RL of 0.1 g/kg';
        break;
    case 1:
    case 2:
    case 4:
        return "$AmositeStr$CrocidoliteStr$ChrysotileStr Asbestos Found at RL of 0.1 g/kg";
        break;
    case 3:
        return "$AmositeStr and $CrocidoliteStr Asbestos Found at RL of 0.1 g/kg";
        break;
    case 5:
        return "$AmositeStr and $ChrysotileStr Asbestos Found at RL of 0.1 g/kg";
        break;
    case 6:
        return "$CrocidoliteStr and $ChrysotileStr Asbestos Found at RL of 0.1 g/kg";
        break;
    case 7:
        return "$AmositeStr, $CrocidoliteStr and $ChrysotileStr Asbestos Found at RL of 0.1 g/kg";
        break;
}
