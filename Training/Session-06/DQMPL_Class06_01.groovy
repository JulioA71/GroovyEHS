/*
    PL:        DQMPL_Class06_01
    Parameter: DQMParam1 (Final Value)
    Data Type: Numeric Calc
    Remarks:   
    2025.08.28 - Example SGS.checkResults with 1 ParamID
*/
def result = "";
// Get (sample) SDIDataItem object
def SDIDataItem = SGS.getSDIDataitem('labvantage', ${primary:s_sampleid}, ${paramlistid}, ${paramlistversionid}.toString(), ${variantid}, ${dataset}.toString(), 'DQMParam1', '*','max');

if (SGS.checkResults('labvantage', SDIDataItem, 'DQMPL_Class06_01', '*', '*', 'max', 'Analyst1', 'Final Value', 'max', true) )
{
    def MDEUser = ${DQMPL_Class06_01;*;*;max|Analyst1;Final Value;#};

    if (MDEUser == "Grace" || MDEUser == "Javier" || MDEUser == "Carlos")
        result=1
    else if (MDEUser=='Marino' || MDEUser == "Julio")
        result=2
}
return result


/*
    PL:        DQMPL_Class06_01
    Parameter: DQMParam2 (Final Value)
    Data Type: Numeric Calc
    Remarks:   
    2025.08.28 - Example SGS.checkResults with 2 ParamID
*/
def bResult = false;
// Get (sample) SDIDataItem object
def SDIDataItem = SGS.getSDIDataitem('labvantage', ${primary:s_sampleid}, ${paramlistid}, ${paramlistversionid}.toString(), ${variantid}, ${dataset}.toString(), 'DQMParam2', '*','max');

if (SGS.checkResults('labvantage', SDIDataItem, 'DQMPL_Class06_01', '*', '*', 'max', 'Analyst1', 'Final Value', 'max', true) &&
    SGS.checkResults('labvantage', SDIDataItem, 'DQMPL_Class06_01', '*', '*', 'max', 'NbrSubSample', 'Final Value', 'max', true))
{
    def MDEUser = ${DQMPL_Class06_01;*;*;max|Analyst1;Final Value;#};
    def MDENbr = ${DQMPL_Class06_01;*;*;max|NbrSubSample;Final Value;#};

    bResult = true;
    if (MDEUser == "Grace" || MDEUser == "Javier" || MDEUser == "Carlos")
        result=10 + MDENbr
    else if (MDEUser=='Marino' || MDEUser == "Julio")
        result=20 + MDENbr
}
// Asking if bResult is true, then result otherwise it is empty
return (!bResult?'':result)


/*
    PL:        DQMPL_Class06_01
    Parameter: DQMParam3 (Final Value)
    Data Type: Numeric Calc
    Remarks:   
    2025.08.28 - Example SGS.checkResults with 2 ParamID (type Text)
*/
def bResult = false;
// Get (sample) SDIDataItem object
def SDIDataItem = SGS.getSDIDataitem('labvantage', ${primary:s_sampleid}, ${paramlistid}, ${paramlistversionid}.toString(), ${variantid}, ${dataset}.toString(), 'DQMParam3', '*','max');

if (SGS.checkResults('labvantage', SDIDataItem, 'DQMPL_Class06_01', '*', '*', 'max', 'Analyst1', 'Final Value', 'max', true) &&
    SGS.checkResults('labvantage', SDIDataItem, 'DQMPL_Class06_01', '*', '*', 'max', 'NbrSubSample', 'Final Value', 'max', true))
{
    def MDEUser = ${DQMPL_Class06_01;*;*;max|Analyst1;Final Value;#};
    bResult = true;

    switch(MDEUser) 
    {
    case ['Grace']: 
        result = "Philippines"
        break
    case ['Carlos','Javier']: 
        result = "Spain"
        break
    case ['Marino']: 
        result = "Brazil"
        break
    default:
        result = "Peru"
        break
    }
}
// Asking if bResult is true, then result otherwise it is empty
return (!bResult?'':result)



// Get (sample) SDIDataItem object
def SDIDataItem = SGS.getSDIDataitem('labvantage', ${primary:s_sampleid}, ${paramlistid}, ${paramlistversionid}.toString(), ${variantid}, ${dataset}.toString(), 'Results^1', '*','max');

if (SGS.checkResults('labvantage', SDIDataItem, 'HSG248_MOLP_M', '*', '*', 'max', 'Analyst1', 'Final Value', 'max', true) &&
SGS.checkResults('labvantage', SDIDataItem, 'HSG248_MOLP_M', '*', '*', 'max', 'NbrSubSample', 'Final Value', 'max', true) &&
SGS.checkResults('labvantage', SDIDataItem, 'HSG248_MOLP_M', '*', '*', 'max', 'DispersiveLiquid1', 'Final Value', 'max', true) &&
	SGS.checkResults('labvantage', SDIDataItem, 'HSG248_BulkAsbestos_Prep', '*', '*', 'max', 'TreatmentAcidification', 'Final Value', 'max', true) &&
	SGS.checkResults('labvantage', SDIDataItem, 'HSG248_BulkAsbestos_Prep', '*', '*', 'max', 'TreatmentCombustion', 'Final Value', 'max', true))
{
	def paramList = SGS.getParamsByParamType(SDIDataItem,'Final Value','CommentsSampPrivate;CommentsSampPublic^4;Results^1;TypeAsbestos^2');

	for (paramid in paramList)
	{
		def typeAsbestos = SGS.getTextResults('labvantage', SDIDataItem, ${paramlistid}, ${paramlistversionid}.toString(), ${variantid}, ${dataset}.toString(), paramid, 'Final Value', 'max', false);
		if(typeAsbestos.length > 0) {
			hasResult = true;
			if (typeAsbestos[0] == "Yes")
			{
				return "Asbestos detected"
			}
		}
	}
	return "Asbestos not detected";
}



/* TM=HSG248 */
/* PL=HSG248_BulkAsbestosResults_M */
/* Parameter=TypeAsbestos^2 */
def result="";
def sdidataitem = SGS.getSDIDataitem('labvantage',${primary:s_sampleid},${paramlistid},""+${paramlistversionid},${variantid}, ""+${dataset}, 'TypeAsbestos^2','Final Value', 'max');
if (SGS.checkResults('labvantage',sdidataitem, '#','#','#', '#', 'TypeChrysotile', 'Final Value', 'max', true))
{
  def temp1=SGS.getTextResults('labvantage', sdidataitem,, '#','#','#', '#', 'TypeChrysotile', 'Final Value', 'max', true)[0]
  if (temp1=='Yes') 
    result="Chrysotile"
}
if (SGS.checkResults('labvantage',sdidataitem, '#','#','#','#', 'TypeAmosite', 'Final Value', 'max', true))
{
  def temp2=SGS.getTextResults('labvantage', sdidataitem,, '#','#','#','#', 'TypeAmosite', 'Final Value', 'max', true)[0]
  if (temp2=='Yes') {
    if (result=='')
        result=result+"Amosite"
    else
        result=result+", Amosite"
  }
}
if (SGS.checkResults('labvantage',sdidataitem, '#','#','#','#', 'TypeCrocidolite', 'Final Value', 'max', true))
{
  def temp3=SGS.getTextResults('labvantage', sdidataitem,, '#','#','#','#', 'TypeCrocidolite', 'Final Value', 'max', true)[0]
  if (temp3=='Yes') {  
    if (result=='')
        result=result+"Crocidolite"
    else
        result=result+", Crocidolite"
  }
}
if (SGS.checkResults('labvantage',sdidataitem, '#','#','#','#', 'TypeTremolite', 'Final Value', 'max', true))
{
  def temp4=SGS.getTextResults('labvantage', sdidataitem,, '#','#','#','#', 'TypeTremolite', 'Final Value', 'max', true)[0]
  if (temp4=='Yes') {  
    if (result=='')
        result=result+"Tremolite"
    else
        result=result+", Tremolite"
  }
}
if (SGS.checkResults('labvantage',sdidataitem, '#','#','#','#', 'TypeAnthophyllite', 'Final Value', 'max', true))
{
  def temp5=SGS.getTextResults('labvantage', sdidataitem,, '#','#','#','#', 'TypeAnthophyllite', 'Final Value', 'max', true)[0]
  if (temp5=='Yes') {  
    if (result=='')
        result=result+"Anthophyllite"
    else
        result=result+", Anthophyllite"
  }
}
if (SGS.checkResults('labvantage',sdidataitem, '#','#','#','#', 'TypeActinolite', 'Final Value', 'max', true))
{
  def temp6=SGS.getTextResults('labvantage', sdidataitem,, '#','#','#','#', 'TypeActinolite', 'Final Value', 'max', true)[0]
  if (temp6=='Yes') {  
    if (result=='')
        result=result+"Actinolite"
    else
        result=result+", Actinolite"
  }
}
return result





/* TM=HSG248 */
/* PL=HSG248_BulkAsbestosResults_M */
/* Parameter=Results^1 */
/* resulttxt="Amiante décelé" */
/* resulttxt="Amiante non-décelé" */
def sdidataitem=SGS.getSDIDataitem('labvantage',${primary:s_sampleid},'HSG248_BulkAsbestosResults_M','*',${variantid}, ""+${dataset}, 'Results^1','*','max');
def paramList1=SGS.getParamsByParamType(sdidataitem,'Final Value','CommentsSampPrivate;CommentsSampPublic^3;Results^1;TypeAsbestos^2');
def paramList=paramList1
def result="";
def resulttxt="Asbestos not detected";
def bResult=false;
sapphire.util.Logger.logDebug("TEST_CALCULATION","Nb of params : "+paramList.size());
for(paramid in paramList){
	def typeAsb=SGS.getTextResults('labvantage', sdidataitem, sdidataitem.paramlistid,sdidataitem.paramlistversionid,sdidataitem.variantid, ""+sdidataitem.dataset, paramid, 'Final Value', 'max', false)
	if(typeAsb.length>0) {
	  if (typeAsb[0]=="Yes")
  		bResult=true
	}
}
if (bResult)
    resulttxt="Asbestos detected"
else
    resulttxt="Asbestos not detected"
bResult=true;
sapphire.util.Logger.logDebug("TEST_CALCULATION","Results : "+resulttxt);
return (!bResult?'':resulttxt)




/* TM=HSG248 */
/* PL=HSG248_BulkAsbestosResults_M */
/* Parameter=TypeAsbestos^2 */
def result="";
def sdidataitem = SGS.getSDIDataitem('labvantage',${primary:s_sampleid},${paramlistid},""+${paramlistversionid},${variantid}, ""+${dataset}, 'TypeAsbestos^2','Final Value', 'max');
if (SGS.checkResults('labvantage',sdidataitem, '#','#','#', '#', 'TypeChrysotile', 'Final Value', 'max', true))
{
  def temp1=SGS.getTextResults('labvantage', sdidataitem,, '#','#','#', '#', 'TypeChrysotile', 'Final Value', 'max', true)[0]
  if (temp1=='Yes') 
    result="Chrysotile"
}
if (SGS.checkResults('labvantage',sdidataitem, '#','#','#','#', 'TypeAmosite', 'Final Value', 'max', true))
{
  def temp2=SGS.getTextResults('labvantage', sdidataitem,, '#','#','#','#', 'TypeAmosite', 'Final Value', 'max', true)[0]
  if (temp2=='Yes') {
    if (result=='')
        result=result+"Amosite"
    else
        result=result+", Amosite"
  }
}
if (SGS.checkResults('labvantage',sdidataitem, '#','#','#','#', 'TypeCrocidolite', 'Final Value', 'max', true))
{
  def temp3=SGS.getTextResults('labvantage', sdidataitem,, '#','#','#','#', 'TypeCrocidolite', 'Final Value', 'max', true)[0]
  if (temp3=='Yes') {  
    if (result=='')
        result=result+"Crocidolite"
    else
        result=result+", Crocidolite"
  }
}
if (SGS.checkResults('labvantage',sdidataitem, '#','#','#','#', 'TypeTremolite', 'Final Value', 'max', true))
{
  def temp4=SGS.getTextResults('labvantage', sdidataitem,, '#','#','#','#', 'TypeTremolite', 'Final Value', 'max', true)[0]
  if (temp4=='Yes') {  
    if (result=='')
        result=result+"Tremolite"
    else
        result=result+", Tremolite"
  }
}
if (SGS.checkResults('labvantage',sdidataitem, '#','#','#','#', 'TypeAnthophyllite', 'Final Value', 'max', true))
{
  def temp5=SGS.getTextResults('labvantage', sdidataitem,, '#','#','#','#', 'TypeAnthophyllite', 'Final Value', 'max', true)[0]
  if (temp5=='Yes') {  
    if (result=='')
        result=result+"Anthophyllite"
    else
        result=result+", Anthophyllite"
  }
}
if (SGS.checkResults('labvantage',sdidataitem, '#','#','#','#', 'TypeActinolite', 'Final Value', 'max', true))
{
  def temp6=SGS.getTextResults('labvantage', sdidataitem,, '#','#','#','#', 'TypeActinolite', 'Final Value', 'max', true)[0]
  if (temp6=='Yes') {  
    if (result=='')
        result=result+"Actinolite"
    else
        result=result+", Actinolite"
  }
}
return result