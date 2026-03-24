/*
	Expresion ID: SGS_GetMPNOperFinalValue
	Type: Groovy
	Description: SGS Get MPN Final Value
	Namespace: SGS
	Date: 2024.05.06
    Method: getMPNOperFinalValue(datasourceid,paramlistid,paramlistversionid,variantid,paramid,paramtype,combination)

	Method: getMaxReplicates(datasourceid,sampleid,paramlistid,paramlistversionid,variantid,dataset,paramid,paramtype)

	SGS.getMPNOperFinalValue('labvantage',${paramlistid},${paramlistversionid},${variantid},"MPNValue","Final Value",${MPN;Final Value;*})
*/
sapphire.util.Logger.logDebug("****SGS_GetMPNValues***");
sapphire.util.Logger.logDebug("***combination: "+combination);
String rep=combination;
if(rep.equals(""))
return "";
def result="NOT VALID"; 

def conn= sapphire.custom.sgs.calculations.CustomCalculation.getConnection(datasourceid);
java.lang.StringBuilder sql= new java.lang.StringBuilder();

sql.append("select top 1 (mpnvalues) mpnvalues from u_mpnvalues mv join paramlistitem pl ");
sql.append("\n on mv.mpnopermethodologyid=pl.u_mpnopermethodology ");
sql.append("\n where pl.paramlistid='").append(paramlistid).append("'");
sql.append("\n and pl.paramlistversionid='").append(paramlistversionid).append("'");
sql.append("\n and pl.variantid='").append(variantid).append("'");
sql.append("\n and paramid='").append(paramid).append("'");
sql.append("\n and paramtype='").append(paramtype).append("'");
sql.append("\n  and mv.combination='").append(rep).append("'");
sql.append("\n  order by mv.createdt desc");

sapphire.util.Logger.logDebug("getMPNOperFinalValue"," SQL:"+sql);
try {
	java.sql.PreparedStatement stmt = conn.prepareStatement(sql.toString());
	java.sql.ResultSet rs = stmt.executeQuery(); 
	if (rs.next())
	{
		result=rs.getString("mpnvalues");
               sapphire.util.Logger.logDebug("mpnvalues : "+result);
	}
	rs.close();
} catch (java.sql.SQLException e) {
// TODO Auto-generated catch block
sapphire.util.Logger.logError("getMPNValues","SQL "+e.getMessage(), e);
throw new sapphire.SapphireException("SQL "+e.getMessage());
}

sapphire.util.Logger.logDebug("***result: "+result);
return result;

/* ---------------- */
sapphire.util.Logger.logDebug("****SGS_GetMPNValues***");
sapphire.util.Logger.logDebug("***combination: "+combination);
String[] com=combination;
String rep="";
rep=com[0]+"-"+com[1];
if(rep.equals(""))
return "";
def result="NOT VALID"; 

result=rep;
return result;

 for(i=0; i < arrListCa.size(); i++) 


/* -------------- */
sapphire.util.Logger.logDebug("****SGS_GetMPNValues***");
sapphire.util.Logger.logDebug("***combination: "+combination);
String[] com=combination;
String rep="";
for(int i=0; i < com.length; i++){
if(com[i].equals("null") ||com[i]=="null"||com[i]==null)
continue;
rep=rep+"-"+com[i];
}
sapphire.util.Logger.logDebug("***com: "+com);
sapphire.util.Logger.logDebug("***rep: "+rep);
if(rep.equals(""))
return "";
def result="NOT VALID"; 

result=rep;
return result;


/*
	Expresion ID: SGS_GetMPNOperFinalValue
	Type: Groovy
	Description: SGS Get MPN Final Value
	Namespace: SGS
    Method: getMPNOperFinalValue(datasourceid,paramlistid,paramlistversionid,variantid,paramid,paramtype,combination)
*/
sapphire.util.Logger.logDebug("****SGS_GetMPNValues***");
sapphire.util.Logger.logDebug("***combination: "+combination);
String[] com=combination;
String rep="";
for(int i=0; i < com.length; i++){
if(com[i].equals("null") ||com[i]=="null"||com[i]==null)
continue;
rep=rep+"-"+com[i];
}
sapphire.util.Logger.logDebug("***com: "+com);
sapphire.util.Logger.logDebug("***rep: "+rep);
if(rep.equals(""))
return "";
def result="NOT VALID"; 

def conn= sapphire.custom.sgs.calculations.CustomCalculation.getConnection(datasourceid);
java.lang.StringBuilder sql= new java.lang.StringBuilder();

sql.append("select top 1 (mpnvalues) mpnvalues from u_mpnvalues mv join paramlistitem pl ");
sql.append("\n on mv.mpnopermethodologyid=pl.u_mpnopermethodology ");
sql.append("\n where pl.paramlistid='").append(paramlistid).append("'");
sql.append("\n and pl.paramlistversionid='").append(paramlistversionid).append("'");
sql.append("\n and pl.variantid='").append(variantid).append("'");
sql.append("\n and paramid='").append(paramid).append("'");
sql.append("\n and paramtype='").append(paramtype).append("'");
sql.append("\n  and mv.combination='").append(rep.substring(1)).append("'");
sql.append("\n  order by mv.createdt desc");

sapphire.util.Logger.logDebug("getMPNOperFinalValue"," SQL:"+sql);
try {
	java.sql.PreparedStatement stmt = conn.prepareStatement(sql.toString());
	java.sql.ResultSet rs = stmt.executeQuery(); 
	if (rs.next())
	{
		result=rs.getString("mpnvalues");
               sapphire.util.Logger.logDebug("mpnvalues : "+result);
	}
	rs.close();
} catch (java.sql.SQLException e) {
// TODO Auto-generated catch block
sapphire.util.Logger.logError("getMPNValues","SQL "+e.getMessage(), e);
throw new sapphire.SapphireException("SQL "+e.getMessage());
}

sapphire.util.Logger.logDebug("***result: "+result);
return result;