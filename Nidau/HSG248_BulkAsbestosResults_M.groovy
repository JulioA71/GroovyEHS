/*
	Description:	Calculate the Results^1:Final Value for HSG248_BulkAsbestosResults_M
  Parameter: Results^1
	Created by:		Marino Orsi
	Created:		2025-08-13
	Modification log (add below)
	Date			Name			Description
	2025-08-13		Marino Orsi		Refactor from original code
	2025-08-18		Marino Orsi		Execute code only if HSG248_MOLP_M:Analyst1 is filled
	2025-08-19		Marino Orsi		Execute code only if mandatory params are filled
	2025-08-19		Marino Orsi		Added local mandatory params
	yyyy-MM-dd		xxxxx			xxxxx  
*/

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