/*
    Expression: SGS_GetHydrobiology v2
	Type: Groovy
	Description: SGS Get Description depending on Taxa
	Namespace: SGS
    Method: getHydrobiology('DQM_HB_FitoPeri','Acanthoceras sp.')
    Date: 2024.04.19
*/
def DescT1="";
def DescT2="";
def HBDesc="";
def conn= sapphire.custom.sgs.calculations.CustomCalculation.getConnection(datasourceid);
java.lang.StringBuilder sql= new java.lang.StringBuilder();
sql.append("select top 1 refvaluedesc, refdisplayvalue");
sql.append("\nfrom reftype rt");
sql.append("\ninner join refvalue rv");
sql.append("\non rt.reftypeid=rv.reftypeid");
sql.append("\nwhere");
sql.append("\nrt.reftypeid = '").append(mdereftypeid).append("'"); 
sql.append("\nand refvalueid = '").append(mderefvalueid).append("'");
sapphire.util.Logger.logDebug("getHydrobiology"," SQL:"+sql);
try {
	java.sql.PreparedStatement stmt = conn.prepareStatement(sql.toString());
	java.sql.ResultSet rs = stmt.executeQuery(); 
	if (rs.next())
	{		
        DescT1=rs.getString("refvaluedesc");
        DescT2=rs.getString("refdisplayvalue");
	}
	rs.close();
} catch (java.sql.SQLException e) {
// TODO Auto-generated catch block
sapphire.util.Logger.logError("SGS_GetHydrobiology","SQL "+e.getMessage(), e);
throw new sapphire.SapphireException("SQL "+e.getMessage());
}
sapphire.util.Logger.logDebug("SGS_GetHydrobiology"," HBDesc :"+HBDesc);
HBDesc=DescT1 + ',' + DescT2
return HBDesc;




def DescT1="";
def DescT2="";
def HBDesc="";
def conn= sapphire.custom.sgs.calculations.CustomCalculation.getConnection(datasourceid);
java.lang.StringBuilder sql= new java.lang.StringBuilder();
sql.append("select top 1 refvaluedesc, refdisplayvalue");
sql.append("\n from reftype rt");
sql.append("\n inner join refvalue rv");
sql.append("\n on rt.reftypeid=rv.reftypeid");
sql.append("\n where");
sql.append("\n rt.reftypeid = '").append(mdereftypeid).append("'"); 
sql.append("\n and refvalueid = '").append(mderefvalueid).append("'");
sapphire.util.Logger.logDebug("getHydrobiology"," SQL:"+sql);
try {
	java.sql.PreparedStatement stmt = conn.prepareStatement(sql.toString());
	java.sql.ResultSet rs = stmt.executeQuery(); 
	if (rs.next())
	{
		DescT1=rs.getInt("refvaluedesc");
        DescT2=rs.getInt("refdisplayvalue");
	}
	rs.close();
} catch (java.sql.SQLException e) {
// TODO Auto-generated catch block
sapphire.util.Logger.logError("SGS_GetHydrobiology","SQL "+e.getMessage(), e);
throw new sapphire.SapphireException("SQL "+e.getMessage());
}
sapphire.util.Logger.logDebug("SGS_GetHydrobiology"," HBDesc :"+HBDesc);
HBDesc=DescT1 + ',' + DescT2
return HBDesc;




/*
    Expression: SGS_GetHydrobiology v1
	Type: Groovy
	Description: SGS Get Description depending on Taxa
	Namespace: SGS
    Method: getHydrobiology('DQM_HB_FitoPeri','Acanthoceras sp.')
    Date: 2024.04.09
*/
def HBDesc="";
def conn= sapphire.custom.sgs.calculations.CustomCalculation.getConnection(datasourceid);
java.lang.StringBuilder sql= new java.lang.StringBuilder();
sql.append("select top 1 refvaluedesc");
sql.append("\nfrom reftype rt");
sql.append("\ninner join refvalue rv");
sql.append("\non rt.reftypeid=rv.reftypeid");
sql.append("\nwhere");
sql.append("\nrt.reftypeid = '").append(mdereftypeid).append("'"); 
sql.append("\nand refvalueid = '").append(mderefvalueid).append("'");
sapphire.util.Logger.logDebug("getHydrobiology"," SQL:"+sql);
try {
	java.sql.PreparedStatement stmt = conn.prepareStatement(sql.toString());
	java.sql.ResultSet rs = stmt.executeQuery(); 
	if (rs.next())
	{
		HBDesc=rs.getString("refvaluedesc");
	}
	rs.close();
} catch (java.sql.SQLException e) {
// TODO Auto-generated catch block
sapphire.util.Logger.logError("SGS_GetHydrobiology","SQL "+e.getMessage(), e);
throw new sapphire.SapphireException("SQL "+e.getMessage());
}
sapphire.util.Logger.logDebug("SGS_GetHydrobiology"," HBDesc :"+HBDesc);
return HBDesc;