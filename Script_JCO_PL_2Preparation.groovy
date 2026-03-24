$G{def sdidataitem=SGS.getSDIDataitem('labvantage',${primary:s_sampleid},${paramlistid},""+${paramlistversionid},${variantid}, ""+${dataset}, 'Multiplier','Final Value', 'max')
/*Max DF in the child sample*/
/*Get Child sample Ids*/
def childs=SGS.childSampleId('labvantage',${primary:s_sampleid},'');
/*Get  DF values of Child sample Ids*/
def result=SGS.sampleNumericResults('labvantage',sdidataitem,SGS.concat(childs,';'), 'NIOSH1003_A_Child','*','#', 'max', 'DF', '*', 'max')
/*Retum  max*/
return SGS.max(result)}




$G{
def a = Custom.getDataSql('java:jboss/datasources/labvantage',${primary:s_sampleid},'JCO_Prep1','1','Global',true);
if(a){  
	return Custom.getDataSql('java:jboss/datasources/labvantage',${primary:s_sampleid},'JCO_Prep1','1','Global','Multiplier','Final Value','1');
}  
else { 
	return Custom.getDataSql('java:jboss/datasources/labvantage',${primary:s_sampleid},'JCO_Prep2','1','Global','Multiplier','Final Value','1');
	}
}
