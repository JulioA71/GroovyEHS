/*
	Expresion ID: SGS_GetMaxReplicates
	Type: Groovy
	Description: Get the Maximum Replicates of the Parameter
	Namespace: SGS
	Method: getMaxReplicates(datasourceid,sampleid,paramlistid,paramlistversionid,variantid,dataset,paramid,paramtype)
	
*/
def MaxRep=0;
def conn= sapphire.custom.sgs.calculations.CustomCalculation.getConnection(datasourceid);
java.lang.StringBuilder sql= new java.lang.StringBuilder();
sql.append("select count(sdii.replicateid) as MaxRepId");
sql.append("\nfrom sdidata sdi");
sql.append("\ninner join sdidataitem sdii on");
sql.append("\nsdi.sdcid=sdii.sdcid");
sql.append("\nand sdi.variantid=sdii.variantid");
sql.append("\nand sdi.keyid1=sdii.keyid1");
sql.append("\nand sdi.paramlistid=sdii.paramlistid");
sql.append("\nand sdi.paramlistversionid=sdii.paramlistversionid");
sql.append("\nand sdii.paramid='").append(paramid).append("'");
sql.append("\nand sdii.paramtype='").append(paramtype).append("'");
sql.append("\nwhere");
sql.append("\nsdi.variantid='").append(variantid).append("'");
sql.append("\nand sdi.sdcid='Sample'");
sql.append("\nand sdi.keyid1='").append(sampleid).append("'");
sql.append("\nand sdi.paramlistid='").append(paramlistid).append("'");
sql.append("\nand sdi.paramlistversionid=").append(paramlistversionid).append("");
sql.append("\nand sdi.dataset=").append(dataset).append("");
sapphire.util.Logger.logDebug("getMaxReplicates"," SQL:"+sql);
try {
	java.sql.PreparedStatement stmt = conn.prepareStatement(sql.toString());
	java.sql.ResultSet rs = stmt.executeQuery(); 
	if (rs.next())
	{
		MaxRep=rs.getInt("MaxRepId");
	}
	rs.close();
} catch (java.sql.SQLException e) {
// TODO Auto-generated catch block
sapphire.util.Logger.logError("SGS_GetMaxReplicates","SQL "+e.getMessage(), e);
throw new sapphire.SapphireException("SQL "+e.getMessage());
}
sapphire.util.Logger.logDebug("SGS_GetMaxReplicates"," MaxRep :"+MaxRep);
return MaxRep;