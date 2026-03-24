/* TM=ISO22262_1_M */
/* PL=ISO22262_1_BulkAsbestosResults_M */
/* Parameter=Results^1 */
def sdidataitem=SGS.getSDIDataitem('labvantage',${primary:s_sampleid},'ISO22262_1_BulkAsbestosResults_M','*',${variantid}, ""+${dataset}, 'Results^1','*','max');
def paramList1=SGS.getParamsByParamTypeInclude(sdidataitem,'Final Value','TypeChrysotile;TypeAmosite;TypeCrocidolite;TypeTremolite;TypeAnthophyllite;TypeActinolite;TypeNonConventional');
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





/* TM=ISO22262_1_M */
/* PL=ISO22262_1_BulkAsbestosResults_M */
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
if (SGS.checkResults('labvantage',sdidataitem, '#','#','#','#', 'TypeNonConventional', 'Final Value', 'max', true))
{
  def temp6=SGS.getTextResults('labvantage', sdidataitem,, '#','#','#','#', 'TypeNonConventional', 'Final Value', 'max', true)[0]
  if (temp6=='Yes') {  
    if (result=='')
        result=result+"Non-conventional asbestos"
    else
        result=result+", Non-conventional asbestos"
  }
}
return result
