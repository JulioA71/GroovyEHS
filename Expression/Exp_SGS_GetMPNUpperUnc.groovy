/*
    Expression: SGS_GetMPNUpperUnc
	Type: Groovy
	Description: SGS Get MPN Upper Uncertainty
	Namespace: SGS
	Date: 2024.05.06
    Method: getMPNLowerUnc('labvantage',$ {paramlistid},$ {paramlistversionid},$ {variantid},"LowConfidenceLimit","Final Value",$ {MPN;Final Value;*})
*/
sapphire.util.Logger.logDebug("****SGS_GetMPNUpperUnc***");
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

sapphire.util.Logger.logDebug("getMPNUpperUnc"," SQL:"+sql);
try {
	java.sql.PreparedStatement stmt = conn.prepareStatement(sql.toString());
	java.sql.ResultSet rs = stmt.executeQuery(); 
	if (rs.next())
	{
        result=rs.getBigDecimal("uppervalue");
        sapphire.util.Logger.logDebug("uppervalue : "+result);
	}
	rs.close();
} catch (java.sql.SQLException e) {
// TODO Auto-generated catch block
sapphire.util.Logger.logError("getMPNUpper","SQL "+e.getMessage(), e);
throw new sapphire.SapphireException("SQL "+e.getMessage());
}

sapphire.util.Logger.logDebug("***result: "+result);
return result;