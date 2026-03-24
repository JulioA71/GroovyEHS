/*
    Expression: SGS_GetMPNLowerUnc
	Type: Groovy
	Description: SGS Get MPN Lower Uncertainty
	Namespace: SGS
	Date: 2024.05.06
    Method: getMPNLowerUnc('labvantage',$ {paramlistid},$ {paramlistversionid},$ {variantid},"LowConfidenceLimit","Final Value",$ {MPN;Final Value;*})
*/
sapphire.util.Logger.logDebug("****SGS_GetMPNLowerUnc***");
sapphire.util.Logger.logDebug("***combination: "+combination);
String rep=combination;
if(rep.equals(""))
return "";
float result=0;

def conn= sapphire.custom.sgs.calculations.CustomCalculation.getConnection(datasourceid);
java.lang.StringBuilder sql= new java.lang.StringBuilder();

sql.append("select top 1 mv.mpnvalues, isnull(mv.loweruncertainty,-1) lowervalue, isnull(mv.upperuncertainty,-1) uppervalue ");
sql.append("\n from u_mpnvalues mv join paramlistitem pl ");
sql.append("\n on mv.mpnopermethodologyid=pl.u_mpnopermethodology ");
sql.append("\n where pl.paramlistid='").append(paramlistid).append("'");
sql.append("\n and pl.paramlistversionid='").append(paramlistversionid).append("'");
sql.append("\n and pl.variantid='").append(variantid).append("'");
sql.append("\n and paramid='").append(paramid).append("'");
sql.append("\n and paramtype='").append(paramtype).append("'");
sql.append("\n  and mv.combination='").append(rep).append("'");
sql.append("\n  order by mv.createdt desc");

sapphire.util.Logger.logDebug("getMPNLowerUnc"," SQL:"+sql);
try {
	java.sql.PreparedStatement stmt = conn.prepareStatement(sql.toString());
	java.sql.ResultSet rs = stmt.executeQuery();
	if (rs.next())
	{
        result=rs.getBigDecimal("lowervalue");
        sapphire.util.Logger.logDebug("lowervalue : "+result);
	}
	rs.close();
} catch (java.sql.SQLException e) {
// TODO Auto-generated catch block
sapphire.util.Logger.logError("getMPNLower","SQL "+e.getMessage(), e);
throw new sapphire.SapphireException("SQL "+e.getMessage());
}

sapphire.util.Logger.logDebug("***result: "+result);
return result;



/*
    Expression: SGS_GetMPNLowerUnc
	Type: Groovy
	Description: SGS Get MPN Lower Uncertainty
	Namespace: SGS
    Method: getMPNLowerUnc('labvantage',$ {paramlistid},$ {paramlistversionid},$ {variantid},"LowConfidenceLimit","Final Value",$ {MPN;Final Value;*})
*/
sapphire.util.Logger.logDebug("****SGS_GetMPNLowerUnc***");
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
float result=0;

def conn= sapphire.custom.sgs.calculations.CustomCalculation.getConnection(datasourceid);
java.lang.StringBuilder sql= new java.lang.StringBuilder();

sql.append("select top 1 (loweruncertainty) loweruncertainty from u_mpnvalues mv join paramlistitem pl ");
sql.append("\n on mv.mpnopermethodologyid=pl.u_mpnopermethodology ");
sql.append("\n where pl.paramlistid='").append(paramlistid).append("'");
sql.append("\n and pl.paramlistversionid='").append(paramlistversionid).append("'");
sql.append("\n and pl.variantid='").append(variantid).append("'");
sql.append("\n and paramid='").append(paramid).append("'");
sql.append("\n and paramtype='").append(paramtype).append("'");
sql.append("\n  and mv.combination='").append(rep.substring(1)).append("'");
sql.append("\n  order by mv.createdt desc");

sapphire.util.Logger.logDebug("getMPNLowerUnc"," SQL:"+sql);
try {
	java.sql.PreparedStatement stmt = conn.prepareStatement(sql.toString());
	java.sql.ResultSet rs = stmt.executeQuery(); 
	if (rs.next())
	{
        result=rs.getBigDecimal("loweruncertainty");
               sapphire.util.Logger.logDebug("loweruncertainty : "+result);
	}
	rs.close();
} catch (java.sql.SQLException e) {
// TODO Auto-generated catch block
sapphire.util.Logger.logError("getMPNLower","SQL "+e.getMessage(), e);
throw new sapphire.SapphireException("SQL "+e.getMessage());
}

sapphire.util.Logger.logDebug("***result: "+result);
return result;



/*
	Backup #1
	
sql.append("select top 1 (isnull(loweruncertainty, 0)) loweruncertainty from u_mpnvalues mv join paramlistitem pl ");
	
*/

select top 1 (isnull(loweruncertainty, 0)) loweruncertainty from u_mpnvalues mv join paramlistitem pl 
 on mv.mpnopermethodologyid=pl.u_mpnopermethodology 
 where pl.paramlistid='APHA9223_B_Calculation_DQM3_W'
 and pl.paramlistversionid=3
 and pl.variantid='Global'
 and paramid='MPNValue'
 and paramtype='Final Value'
  and mv.combination='0-0-0'
  order by mv.createdt desc
  
  
select top 1 (isnull(loweruncertainty, 0)) loweruncertainty from u_mpnvalues mv join paramlistitem pl 
select top 1 (COALESCE(loweruncertainty, 0)) loweruncertainty from u_mpnvalues mv join paramlistitem pl 
select top 1 (IFNULL(loweruncertainty, 0)) loweruncertainty from u_mpnvalues mv join paramlistitem pl 
--select top 1 (loweruncertainty) loweruncertainty from u_mpnvalues mv join paramlistitem pl 
 on mv.mpnopermethodologyid=pl.u_mpnopermethodology 
 where pl.paramlistid='APHA9223_B_Calculation_DQM3_W'
 and pl.paramlistversionid=3
 and pl.variantid='Global'
 and paramid='MPNValue'
 and paramtype='Final Value'
  and mv.combination='0-0-0'
  order by mv.createdt desc
