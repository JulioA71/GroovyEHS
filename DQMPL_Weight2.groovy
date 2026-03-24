/*
    Author: DQM Team
    PL=DQMPL_Weight2 (Total Ash - LVP)
    Parameter=NWeight_difference (Final Value)
    Date: 2025.02.28
*/
def CalcResult = 0;
def bstatus = false;
def numDS = Math.round(${dataset});
int xDS = 0;

// Get SDIDataItem object
def SDIDataItem = SGS.getSDIDataitem('labvantage', ${primary:s_sampleid}, 'DQMPL_Weight2', '*', ${variantid}, ${dataset}.toString(), 'NWeight_difference', '*', 'max');

if (numDS==1) {
   bstatus = true;
   CalcResult = (${DQMPL_Weight2;*;*;#|NCSWeight_IgnitionN;Final Value;max} - ${DQMPL_Weight1;*;*;max|NCSWeight_Ignition2;Final Value;max}) }
else if (numDS>1) {
   bstatus = true;
   xDS = numDS - 1;
   def NumericResult = SGS.getNumericResults('labvantage', SDIDataItem, SDIDataItem.paramlistid, SDIDataItem.paramlistversionid, SDIDataItem.variantid, xDS.toString(), 'NCSWeight_IgnitionN', 'Final Value', '*', false);
   CalcResult = (${DQMPL_Weight2;*;*;#|NCSWeight_IgnitionN;Final Value;max} - NumericResult[0]) }
else
  CalcResult = 99
return (!bstatus?'':CalcResult)


/*
    Example #1
*/
def CalcResult = 0;
def bstatus = false;
def numDS = Math.round(${dataset});

if (numDS==1) {
   bstatus = true;
   CalcResult = (${DQMPL_Weight2;*;*;#|NCSWeight_IgnitionN;Final Value;max} - ${DQMPL_Weight1;*;*;max|NCSWeight_Ignition2;Final Value;max}) }
else if (numDS==2) {
   bstatus = true;
   CalcResult = (${DQMPL_Weight2;*;*;#|NCSWeight_IgnitionN;Final Value;max} - ${DQMPL_Weight2;*;*;1|NCSWeight_IgnitionN;Final Value;max}) }
else if (numDS==3) {
   bstatus = true;
   CalcResult = (${DQMPL_Weight2;*;*;#|NCSWeight_IgnitionN;Final Value;max} - ${DQMPL_Weight2;*;*;2|NCSWeight_IgnitionN;Final Value;max}) }
else if (numDS==4) {
   bstatus = true;
   CalcResult = (${DQMPL_Weight2;*;*;#|NCSWeight_IgnitionN;Final Value;max} - ${DQMPL_Weight2;*;*;3|NCSWeight_IgnitionN;Final Value;max}) }
else
  CalcResult = 99
return (!bstatus?'':CalcResult)


/*
    Example #2
*/
def CalcResult = 0;
def bstatus = false;
def numDS = Math.round(${dataset});
int x = 1;

if (numDS==1) {
   bstatus = true;
   CalcResult = (${DQMPL_Weight2;*;*;#|NCSWeight_IgnitionN;Final Value;max} - ${DQMPL_Weight1;*;*;max|NCSWeight_Ignition2;Final Value;max}) }
else if (numDS>1) {
   bstatus = true;
   CalcResult = (${DQMPL_Weight2;*;*;#|NCSWeight_IgnitionN;Final Value;max} - ${DQMPL_Weight2;*;*;1|NCSWeight_IgnitionN;Final Value;max}) }
else
  CalcResult = 99
return (!bstatus?'':CalcResult)




