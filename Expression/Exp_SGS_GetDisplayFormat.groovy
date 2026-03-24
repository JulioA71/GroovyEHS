/*
	Expresion ID: SGS_GetDisplayFormat
	Type: Groovy
	Description: Get the Display Format of the Parameter ID
	Namespace: SGS
	Method: getDisplayFormat(datasourceid,sampleid,paramlistid,paramlistversionid,variantid,dataset,paramid)
	
*/
def PDisplayFormat='';
def conn= sapphire.custom.sgs.calculations.CustomCalculation.getConnection(datasourceid);
java.lang.StringBuilder sql= new java.lang.StringBuilder();

sql.append("select top 1 sdidi.displayformat as displayformat");
sql.append("\nfrom sdidataitem sdidi");
sql.append("\nwhere");
sql.append("\nsdidi.variantid='").append(variantid).append("'");
sql.append("\nand sdidi.sdcid='Sample'");
sql.append("\nand sdidi.keyid1='").append(sampleid).append("'");
sql.append("\nand sdidi.paramlistid='").append(paramlistid).append("'");
sql.append("\nand sdidi.paramlistversionid=").append(paramlistversionid).append("");
sql.append("\nand sdidi.paramid='").append(paramid).append("'");
sql.append("\nand sdidi.dataset=").append(dataset).append("");
sapphire.util.Logger.logDebug("getDisplayFormat"," SQL:"+sql);
try {
	java.sql.PreparedStatement stmt = conn.prepareStatement(sql.toString());
	java.sql.ResultSet rs = stmt.executeQuery(); 
	if (rs.next())
	{
		PDisplayFormat=rs.getString("displayformat");
	}
	rs.close();
} catch (java.sql.SQLException e) {
// TODO Auto-generated catch block
sapphire.util.Logger.logError("SGS_GetDisplayFormat","SQL "+e.getMessage(), e);
throw new sapphire.SapphireException("SQL "+e.getMessage());
}
sapphire.util.Logger.logDebug("SGS_GetDisplayFormat"," displayformat :"+PDisplayFormat);
return PDisplayFormat;