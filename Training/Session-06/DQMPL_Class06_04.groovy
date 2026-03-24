/*
    PL:        DQMPL_Class06_04
    Parameter: Results^1 (Final Value)
    Data Type: Numeric Calc
    Remarks:   
    2025.08.28 - Example SGS.checkResults with 2 ParamID
*/
def bResult = false;
// Get (sample) SDIDataItem object
def SDIDataItem = SGS.getSDIDataitem('labvantage', ${primary:s_sampleid}, ${paramlistid}, ${paramlistversionid}.toString(), ${variantid}, ${dataset}.toString(), 'Results^1', '*','max');

if (SGS.checkResults('labvantage', SDIDataItem, 'DQMPL_Class06_02', '*', '*', 'max', 'Analyst1', 'Final Value', 'max', true) &&
    SGS.checkResults('labvantage', SDIDataItem, 'DQMPL_Class06_02', '*', '*', 'max', 'NbrSubSample', 'Final Value', 'max', true) &&
    SGS.checkResults('labvantage', SDIDataItem, 'DQMPL_Class06_03', '*', '*', 'max', 'TreatmentAcidification', 'Final Value', 'max', true))
{
	def paramList = SGS.getParamsByParamType(SDIDataItem,'Final Value','Results^1;Results;DQMParam1;DQMParam2');

	for (paramid in paramList)
	{
		def typeAsbestos = SGS.getTextResults('labvantage', SDIDataItem, ${paramlistid}, ${paramlistversionid}.toString(), ${variantid}, ${dataset}.toString(), paramid, 'Final Value', 'max', false);
		if(typeAsbestos.length > 0) {
			if (typeAsbestos[0] == "Yes")
			{
				return "Detected"
			}
		}
	}
	return "Not detected";
}



/*
    PL:        DQMPL_Class06_04
    Parameter: Results (Final Value)
    Data Type: Numeric Calc
    Remarks:   
    2025.08.28 - Example SGS.checkResults with 2 ParamID
*/
def bResult = false;
// Get (sample) SDIDataItem object
def SDIDataItem = SGS.getSDIDataitem('labvantage', ${primary:s_sampleid}, ${paramlistid}, ${paramlistversionid}.toString(), ${variantid}, ${dataset}.toString(), 'Results', '*','max');

if (SGS.checkResults('labvantage', SDIDataItem, 'DQMPL_Class06_02', '*', '*', 'max', 'Analyst1', 'Final Value', 'max', true) &&
    SGS.checkResults('labvantage', SDIDataItem, 'DQMPL_Class06_02', '*', '*', 'max', 'NbrSubSample', 'Final Value', 'max', true) &&
    SGS.checkResults('labvantage', SDIDataItem, 'DQMPL_Class06_03', '*', '*', 'max', 'TreatmentAcidification', 'Final Value', 'max', true))
{
	def paramList = SGS.getParamsByParamTypeInclude(SDIDataItem,'Final Value','TypeChrysotile;TypeAmosite;TypeCrocidolite');

	for (paramid in paramList)
	{
		def typeAsbestos = SGS.getTextResults('labvantage', SDIDataItem, ${paramlistid}, ${paramlistversionid}.toString(), ${variantid}, ${dataset}.toString(), paramid, 'Final Value', 'max', false);
		if(typeAsbestos.length > 0) {
			if (typeAsbestos[0] == "Yes")
			{
				return "Detected"
			}
		}
	}
	return "Not detected";
}
