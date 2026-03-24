/*
	SGS_GetParamsByParamTypeInclude
	getParamsByParamTypeInclude(sdidataitem,paramtype,includes)
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

/*
	SGS_GetUnknownSampleLink
	getUnknownSampleLink(sdidataitem)
*/
def sampleList= new ArrayList(); 
def conn= sapphire.custom.sgs.calculations.CustomCalculation.getConnection('labvantage');
java.lang.StringBuilder sql= new java.lang.StringBuilder();
sql.append("with previouslink (s_qcbatchid,s_qcbatchitemid) as"); 
sql.append("\n(");
sql.append("\nselect '");
sql.append(sdidataitem.s_qcbatchid);
sql.append("' s_qcbatchid,'");
sql.append(sdidataitem.s_qcbatchitemid);
sql.append("'s_qcbatchitemid");
sql.append("\nunion"); 
sql.append("\n	select qcbi.s_qcbatchid,qcbi.linktoqcbatchitemid s_qcbatchitemid from s_qcbatchitem qcbi where qcbi.s_qcbatchid='");
sql.append(sdidataitem.s_qcbatchid);
sql.append("' and qcbi.s_qcbatchitemid='");
sql.append(sdidataitem.s_qcbatchitemid);
sql.append("' and  qcbi.linktoqcbatchitemid<>''");
sql.append("\nunion all");
sql.append("\n	select qcbi.s_qcbatchid,qcbi.linktoqcbatchitemid s_qcbatchitemid from previouslink link,s_qcbatchitem qcbi");
sql.append("\n		where qcbi.s_qcbatchid=link.s_qcbatchid and qcbi.s_qcbatchitemid=link.s_qcbatchitemid and  qcbi.linktoqcbatchitemid<>''");
sql.append("\n)");
sql.append("\n,nextlink (s_qcbatchid,s_qcbatchitemid)");
sql.append("\nas");
sql.append("\n(");
sql.append("\n	select qcbi.s_qcbatchid,qcbi.s_qcbatchitemid from previouslink, s_qcbatchitem qcbi");
sql.append("\n		where qcbi.s_qcbatchid=previouslink.s_qcbatchid and qcbi.linktoqcbatchitemid=previouslink.s_qcbatchitemid ");  
sql.append("\n			and qcbi.s_qcbatchitemid not in (select s_qcbatchitemid from previouslink)");
sql.append("\nunion all");
sql.append("\n	select qcbi.s_qcbatchid,qcbi.s_qcbatchitemid from nextlink link,s_qcbatchitem qcbi"); 
sql.append("\n		where qcbi.s_qcbatchid=link.s_qcbatchid and qcbi.linktoqcbatchitemid=link.s_qcbatchitemid");
sql.append("\n)");
sql.append("\n,link as (");
sql.append("\n	select s_qcbatchitemid from nextlink");
sql.append("\nunion ");
sql.append("\n	select s_qcbatchitemid from previouslink"); 
sql.append("\n)\n");
sql.append("select qcsampleid");
sql.append("\nfrom (");
sql.append("\nselect ");
sql.append("\nsdi.keyid1 qcsampleid");
sql.append("\n,ROW_NUMBER() over(partition by qcbi.s_qcbatchid,sdi.keyid1 order by qcbi.s_qcbatchitemid,sdi.paramlistid) ROW_ID");
sql.append("\nfrom s_qcbatch qcb");
sql.append("\n	left outer join s_qcbatchitem qcbi");
sql.append("\n		on qcb.s_qcbatchid=qcbi.s_qcbatchid");
sql.append("\n	left outer join sdidata sdi");
sql.append("\n		on  qcbi.s_qcbatchid=sdi.s_qcbatchid");
sql.append("\n			and qcbi.s_qcbatchitemid=sdi.s_qcbatchitemid");
sql.append("\n	left outer join s_sample s");
sql.append("\n		on sdi.keyid1=s.s_sampleid");
sql.append("\n			and sdi.sdcid='Sample'");
sql.append("\nwhere qcb.s_qcbatchid='");
sql.append(sdidataitem.s_qcbatchid);
sql.append("'");
sql.append("\nand  qcbi.s_qcbatchitemid<>'");
sql.append(sdidataitem.s_qcbatchitemid);
sql.append("'");
sql.append("\nand (sdi.s_datasetstatus is null  or sdi.s_datasetstatus<>'Cancelled')");
sql.append("\nand (s.samplestatus is null  or s.samplestatus<>'Cancelled')");
sql.append("\nand qcbi.batchitemtype ='Unknown'");
sql.append("\nand qcbi.s_qcbatchitemid in (select s_qcbatchitemid from  link)");
sql.append("\n) data where ROW_ID=1");
sql.append("\norder by 1");
sql.append("\nOPTION (MaxRecursion 100)");
sapphire.util.Logger.logDebug("SGS_GETUNKNOWNSAMPLELINK"," SQL:"+sql);
try {
	java.sql.PreparedStatement stmt = conn.prepareStatement(sql.toString());
	java.sql.ResultSet rs = stmt.executeQuery(); 
	while (rs.next())
	{
		sampleList.add(rs.getString("qcsampleid"));
		//sapphire.util.Logger.logDebug("SGS_GETUNKNOWNSAMPLELINK","param : " +rs.getString("paramid"));
	}
	rs.close();
} catch (java.sql.SQLException e) {
// TODO Auto-generated catch block
sapphire.util.Logger.logError("SGS_GETUNKNOWNSAMPLELINK","SQL "+e.getMessage(), e);
throw new sapphire.SapphireException("SQL "+e.getMessage());
}
sapphire.util.Logger.logDebug("SGS_GETUNKNOWNSAMPLELINK"," Samples :"+sampleList);
return sampleList;


/*
	SGS_GetMaxDataSet
	getMaxDataSet(datasourceid,sampleid,paramlistid,paramlistversionid,variantid)
*/
def DSMax=0;
def conn= sapphire.custom.sgs.calculations.CustomCalculation.getConnection(datasourceid);
java.lang.StringBuilder sql= new java.lang.StringBuilder();
sql.append("select sdi.dataset");
sql.append("\nfrom sdidata sdi");
sql.append("\nwhere sdi.variantid='").append(variantid).append("'");
sql.append("\nand sdi.sdcid='Sample'");
sql.append("\nand sdi.keyid1='").append(sampleid).append("'");
sql.append("\nand sdi.paramlistid='").append(paramlistid).append("'");
sql.append("\nand sdi.paramlistversionid=").append(paramlistversionid).append("");
sql.append("\nand sdi.dataset in (select max(dataset) from sdidata where variantid=sdi.variantid and sdcid=sdi.sdcid and keyid1=sdi.keyid1 and paramlistid=sdi.paramlistid and paramlistversionid=sdi.paramlistversionid)");
sapphire.util.Logger.logDebug("getMaxDataSet"," SQL:"+sql);
try {
	java.sql.PreparedStatement stmt = conn.prepareStatement(sql.toString());
	java.sql.ResultSet rs = stmt.executeQuery(); 
	if (rs.next())
	{
		DSMax=rs.getInt("dataset");
	}
	rs.close();
} catch (java.sql.SQLException e) {
// TODO Auto-generated catch block
sapphire.util.Logger.logError("SGS_GetMaxDataSet","SQL "+e.getMessage(), e);
throw new sapphire.SapphireException("SQL "+e.getMessage());
}
sapphire.util.Logger.logDebug("SGS_GetMaxDataSet"," DSMax :"+DSMax);
return DSMax;


==================



def DSMax=0; 
def conn= sapphire.custom.sgs.calculations.CustomCalculation.getConnection('labvantage');
java.lang.StringBuilder sql= new java.lang.StringBuilder();
sql.append("select sdi.dataset");
sql.append("\nfrom sdidata sdi");
sql.append("\nwhere sdi.paramlistid='").append(sdidataitem.paramlistid).append("'");
sql.append("\nand sdi.paramlistversionid=").append(sdidataitem.paramlistversionid).append("");
sql.append("\nand sdi.variantid='").append(sdidataitem.variantid).append("'");
sql.append("\nand sdi.sdcid='Sample'");
sql.append("\nand sdi.keyid1='").append(sampleid).append("'");
sql.append("\nand sdi.dataset in (select max(dataset) from sdidata where sdcid=sdi.sdcid and keyid1=sdi.keyid1 and sdi.paramlistid=paramlistid)");
sapphire.util.Logger.logDebug("getMaxDataSet"," SQL:"+sql);
try {
	java.sql.PreparedStatement stmt = conn.prepareStatement(sql.toString());
	java.sql.ResultSet rs = stmt.executeQuery(); 
	if (rs.next())
	{
		DSMax=rs.getInt("dataset");
	}
	rs.close();
} catch (java.sql.SQLException e) {
// TODO Auto-generated catch block
sapphire.util.Logger.logError("SGS_GetMaxDataSet","SQL "+e.getMessage(), e);
throw new sapphire.SapphireException("SQL "+e.getMessage());
}
sapphire.util.Logger.logDebug("SGS_GetMaxDataSet"," DSMax :"+DSMax);
return DSMax;
