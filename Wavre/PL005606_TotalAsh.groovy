def currentDS = ${dataset};
def SDIDataItem = SGS.getSDIDataitem('pbluelv8', ${primary:s_sampleid}, ${paramlistid}, ${paramlistversionid}.toString(), ${variantid}, currentDS.toString(), '*', '*', '*');


def difference = SGS.getNumericResults('pbluelv8', SDIDataItem, 'PL005605', '*', '*', 'max', 'NCSWeight_IgnitionN', 'DataEntry', 'max', true)

if (difference){
    def calc = Math.abs((difference[0] - ${PL005602;*;BEBRU|Container_Weight;DataEntry;max}) / ${PL005602;*;BEBRU|SampleWeight;SystemCalculation;max} *100)
    return calc 
}

return Math.abs((SGS.getNumericResults('pbluelv8', SDIDataItem, 'PL005602', '*', '*', 'max', 'NCSWeight_Ignition2', 'DataEntry', 'max', true)[0] - ${PL005602;*;BEBRU|Container_Weight;DataEntry;max}) / ${PL005602;*;BEBRU|SampleWeight;SystemCalculation;max} *100)



def result="";
if (${TolueneDifference;SystemCalculation} <=0.0001 && ${TMPDifference;SystemCalculation} <=0.0005) {
   bstatus=true}
  else {
  bstatus=false
}
if (bstatus) {result="Pass"}
else
result="Fail"
return result



if (Math.abs(${TolueneDifference;SystemCalculation}) <= 0.0001)
    {
        if (Math.abs(${TMPDifference;SystemCalculation} <=0.0005)
            {
	        return "OK";
            }
        else {return "NOK";}
} else {
	return "NOK";
}