/*
    PL=PL005743
    Parameter=ConstantMass (SystemCalculation)
    Data Type=Numeric Calc
    Remarks=
*/
if (${ExecuteCalculation;DataEntry} == "Y")
{
	int maxDS = SGS.getMaxValidDS('pbluelv8', ${primary:s_sampleid}, 'PL005581', '*', '*', 'Net_Eva_Cap_SW_D2', 'SystemCalculation', 'max');
	int previousDS = maxDS - 1;

	if (maxDS <= 1) {

		def firstDrying = ${PL005742;*;*;max|Net_Eva_Cap_SW_D1;SystemCalculation;max};
		def nextDrying = ${PL005581;*;*;max|Net_Eva_Cap_SW_D2;SystemCalculation;max};
		return nextDrying - firstDrying;

	} else {
		
		def maxDSResult = ${PL005581;*;*;max|Net_Eva_Cap_SW_D2;SystemCalculation;max};
		def SDIDataItem = SGS.getSDIDataitem('pbluelv8', ${primary:s_sampleid}, 'PL005581', '*', '*', '*', '*', '*', '*');
		def previousDSResult = SGS.getNumericResults('pbluelv8', SDIDataItem, 'PL005581', '*', '*', previousDS.toString(), 'Net_Eva_Cap_SW_D2', 'SystemCalculation', 'max', true)[0];
		return maxDSResult - previousDSResult;

	}
}



/*
    PL=PL005743
    Parameter=Verification_CMass (SystemCalculation)
    Data Type=Text Calc
    Remarks=
*/
if (Math.abs(${ConstantMass;SystemCalculation}) <= 0.0005) {
	return "OK";
} else {
	return "NOK";
}






if (${Limit_CMass;Standard} == "Yes") 
    if (${Limit_CMass;DataEntry} != null) {
        int maxDS = SGS.getMaxValidDS('pbluelv8', ${primary:s_sampleid}, 'PL005581', '*', '*', 'Net_Eva_Cap_SW_D2', 'SystemCalculation', 'max');
        int previousDS = maxDS - 1;
        if (maxDS <= 1) {
            def firstDrying = ${PL005742;*;*;max|Net_Eva_Cap_SW_D1;SystemCalculation;max};
            def nextDrying = ${PL005581;*;*;max|Net_Eva_Cap_SW_D2;SystemCalculation;max};
            return nextDrying - firstDrying;
        } else {
            def maxDSResult = ${PL005581;*;*;max|Net_Eva_Cap_SW_D2;SystemCalculation;max};
            def SDIDataItem = SGS.getSDIDataitem('pbluelv8', ${primary:s_sampleid}, 'PL005581', '*', '*', '*', '*', '*', '*');
            def previousDSResult = SGS.getNumericResults('pbluelv8', SDIDataItem, 'PL005581', '*', '*', previousDS.toString(), 'Net_Eva_Cap_SW_D2', 'SystemCalculation', 'max', true)[0];
            return maxDSResult - previousDSResult;
        }
    }


if (${Limit_CMass;Standard} == "Yes") 
    {
    int nValue = 0;
    nValue = 1 + 2;
    return nValue;
    }
else
    {
    return null;
    }



if (${ExecuteCalculation;DataEntry} == "Y") {
 if ((${ConstantMass;SystemCalculation} >= -0.0005) && (${ConstantMass;SystemCalculation} <= 0.0005)) 
  return "OK";
 else 
  return "NOK";
  }
 


