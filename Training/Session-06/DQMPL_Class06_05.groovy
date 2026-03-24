/*
    PL:        DQMPL_Class06_06
    Parameter: Results (Final Value)
    Data Type: Numeric Calc
    Remarks:   
    2025.09.01 - Example Not SGS.checkResults (Sum)
*/
def result = 0;
result = (${DQMPL_Class06_05;*;*;max|DQMParam1;Final Value;#} + ${DQMPL_Class06_05;*;*;max|DQMParam2;Final Value;#});
return result



/*
    PL:        DQMPL_Class06_06
    Parameter: Results (Final Value)
    Data Type: Numeric Calc
    Remarks:   
    2025.09.01 - Example If SGS.checkResults (Sum)
*/
def result = "";
// Get (sample) SDIDataItem object
def SDIDataItem = SGS.getSDIDataitem('labvantage', ${primary:s_sampleid}, ${paramlistid}, ${paramlistversionid}.toString(), ${variantid}, ${dataset}.toString(), 'DQMParam3', '*','max');

if (SGS.checkResults('labvantage', SDIDataItem, 'DQMPL_Class06_06', '*', '*', 'max', 'DQMParam1', 'Final Value', 'max', true) &&
    SGS.checkResults('labvantage', SDIDataItem, 'DQMPL_Class06_06', '*', '*', 'max', 'DQMParam2', 'Final Value', 'max', true))
{
    result = (${DQMPL_Class06_06;*;*;max|DQMParam1;Final Value;#} + ${DQMPL_Class06_06;*;*;max|DQMParam2;Final Value;#});
}
return result


/*
    PL:        DQMPL_Class06_08
    Parameter: Results (Final Value)
    Data Type: Numeric Calc
    Remarks:   
    2025.09.01 - Example If SGS.checkResults (Sum)
*/
def result = 0;
def bstatus = false;

def SDIDataItem = SGS.getSDIDataitem('labvantage', ${primary:s_sampleid}, ${paramlistid}, ${paramlistversionid}.toString(), ${variantid}, ${dataset}.toString(), 'DQMParam3', '*','max');

if (SGS.checkResults('labvantage', SDIDataItem, 'DQMPL_Class06_08', '*', '*', 'max', 'DQMParam1', 'Final Value', 'max', true) && 
    SGS.checkResults('labvantage', SDIDataItem, 'DQMPL_Class06_08', '*', '*', 'max', 'DQMParam2', 'Final Value', 'max', true))
   {
   bstatus = true;
   result = (${DQMPL_Class06_08;*;*;max|DQMParam1;Final Value;max} + ${DQMPL_Class06_08;*;*;max|DQMParam2;Final Value;max}) ;
   }
return (!bstatus?'':result)