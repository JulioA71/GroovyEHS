/*
    Expression: SGS_GetParamsByParamTypeInclude
*/
def paramList= new ArrayList(); 
def conn= sapphire.custom.sgs.calculations.CustomCalculation.getConnection('labvantage');
java.lang.StringBuilder sql= new java.lang.StringBuilder();
sql.append("select paramid");
sql.append("\nfrom paramlistitem");
sql.append("\nwhere paramlistid='").append(sdidataitem.paramlistid).append("'");
sql.append("\nand paramlistversionid=").append(sdidataitem.paramlistversionid).append("");
sql.append("\nand variantid='").append(sdidataitem.variantid).append("'");
sql.append("\nand paramtype in ('").append(paramtype.replaceAll(";", "','")).append("')");
sql.append("\nand paramid in ('").append(includes.replaceAll(";", "','")).append("')");
sapphire.util.Logger.logDebug("GETPARAMSBYPARAMTYPEINCLUDE"," SQL:"+sql);
try {
	java.sql.PreparedStatement stmt = conn.prepareStatement(sql.toString());
	java.sql.ResultSet rs = stmt.executeQuery(); 
	while (rs.next())
	{
		paramList.add(rs.getString("paramid"));
		//sapphire.util.Logger.logDebug("GETPARAMSBYPARAMTYPEINCLUDE","param : " +rs.getString("paramid"));
	}
	rs.close();
} catch (java.sql.SQLException e) {
// TODO Auto-generated catch block
sapphire.util.Logger.logError("GETPARAMSBYPARAMTYPEINCLUDE","SQL "+e.getMessage(), e);
throw new sapphire.SapphireException("SQL "+e.getMessage());
}
sapphire.util.Logger.logDebug("GETPARAMSBYPARAMTYPEINCLUDE"," Params :"+paramList);
return paramList;
