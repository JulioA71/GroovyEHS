// Tracker 2059 - SUM METALS - EPA200_7_WW

//( [7429-90-5;Final Value] + [7440-43-9;Final Value] + [7440-47-3;Final Value] + [7440-50-8;Final Value] + [7439-89-6;Final Value] + [7439-97-6;Final Value] + [7440-02-0;Final Value] + [7439-96-5;Final Value] + [7439-92-1;Final Value] + [7440-66-6;Final Value] )
def sdidataitem =  SGS.getSDIDataitem('labvantage',${primary:s_sampleid},${paramlistid},""+${paramlistversionid},${variantid}, ""+${dataset}, 'TotalMetals','*','max');
def paramList=SGS.getParamsByParamType(sdidataitem,'Final Value','DF;TotalMetals;;FinalVolume;Multiplier');
def result=0;
def bResult=false;
sapphire.util.Logger.logDebug("TEST_CALCULATION","Nb of params : "+paramList.size());
for(paramid in paramList){
	def metal=SGS.getNumericResults('labvantage',sdidataitem, sdidataitem.paramlistid,sdidataitem.paramlistversionid,sdidataitem.variantid, ""+sdidataitem.dataset, paramid, 'Final Value', 'max', true)
	if(metal.length>0)
	{
		bResult=true;
		result=result+metal[0];
	}
}
sapphire.util.Logger.logDebug("TEST_CALCULATION","Results : "+result);
return (!bResult?'':result)


// Tracker 2157 - TotalPAHs - EPA8270_PAH_W

//( TotalPAHs )
def sdidataitem =  SGS.getSDIDataitem('labvantage',${primary:s_sampleid},${paramlistid},""+${paramlistversionid},${variantid}, ""+${dataset}, 'TotalPAHs','*','max');
def paramList1=SGS.getParamsByParamType(sdidataitem,'Final Value','DF;Multiplier;BTEX;Chlorobenzenes;Dichloropropanes;TotalXylene;TotalPAHs;120-12-7;1634-04-4;218-01-9;224-42-0;56-49-5;602-87-9;83-32-9;85-01-8;86-73-7;95-49-8;98-82-8');
def paramList2=SGS.getParamsByParamType(sdidataitem,'S_Surrogate','1868-53-7;2037-26-5;460-00-4;118-79-6;1718-51-0;4165-60-0;321-60-8');
def paramList3=SGS.getParamsByParamType(sdidataitem,'InternalStandard','3855-82-1;15067-26-2;1517-22-2;1719-03-5;1520-96-3');
def paramList=paramList1+paramList2+paramList3
def result=0;
def bResult=false;
sapphire.util.Logger.logDebug("TEST_CALCULATION","Nb of params : "+paramList.size());
for(paramid in paramList){
	def metal=SGS.getNumericResults('labvantage',sdidataitem, sdidataitem.paramlistid,sdidataitem.paramlistversionid,sdidataitem.variantid, ""+sdidataitem.dataset, paramid, 'Final Value', 'max', true)
	if(metal.length>0)
	{
		bResult=true;
		result=result+metal[0];
	}
}
sapphire.util.Logger.logDebug("TEST_CALCULATION","Results : "+result);
return (!bResult?'':result)



** Paramtype=
Final Value
S_Surrogate
InternalStandard



** TOTAL_PAH=
NAPHTHALENE
2_METHYLNAPHTHALENE
2_CHLORONAPHTHALENE
ACENAPHTHALENE
ACENAPHTHENE
FLUORANTHENE
PYRENE
BENZO_A_ANTHRACENE
BENZO_B_K_FLUORANTHE
BENZO_A_PYRENE
3_METHYLNAPHTHALENE
INDENOPYRENE
DIBENZO_A_H_ANTHRACEN
BENZO_GHI_PERYLENE




1868-53-7
2037-26-5
460-00-4
118-79-6
1718-51-0
4165-60-0
321-60-8
3855-82-1
15067-26-2
1517-22-2
1719-03-5
1520-96-3


PAHs - UAT

90-13-1 = Naphthalene, 1-chloro-
90-12-0 = Naphthalene, 1-methyl-
91-58-7 = Naphthalene, 2-chloro-
91-57-6 = Naphthalene, 2-methyl-
208-96-8 = Acenaphthylene
56-55-3 = Benz[a]anthracene
50-32-8 = Benzo[a]pyrene
57-97-6 = Benz[a]anthracene, 7,12-dimethyl-
205-99-2 = Benzo[b]fluoranthene
192-97-2 = Benzo[e]pyrene
191-24-2 = Benzo[ghi]perylene
207-08-9 = Benzo[k]fluoranthene
53-70-3 = Dibenz[a,h]anthracene
192-65-4 = 1,2:4,5-Dibenzopyrene
206-44-0 = Fluoranthene
193-39-5 = Indeno[1,2,3-cd]pyrene
91-20-3 = Naphthalene
198-55-0 = Perylene
129-00-0 = Pyrene



   select 
   pl.variantid, pl.paramlistid, pl.paramlistversionid, pl.paramlistdesc, pl.s_paramlisttype,
   pl.securitydepartment,
   case when pl.versionstatus='P' then 'Provisional' 
		when pl.versionstatus='C' then 'Current' 
		when PL.versionstatus='A' then 'Active' 
		when PL.versionstatus='E' then 'Expired'   
	end versionstatus,
	pli.paramid, p.paramdesc, pli.aliasid, pli.paramtype, pli.usersequence, pli.datatypes, pli.displayformat, pli.displayunits, pli.calcrule, pli.reportflag
     
   from paramlist pl
	inner join paramlistitem pli on
		pli.variantid=pl.variantid
		and pli.paramlistid=pl.paramlistid
		and pli.paramlistversionid=pl.paramlistversionid
		--AND pli.paramid in ('1520-96-3','191-24-2','191-24-2','191-24-2','1520-96-3','1520-96-3','1520-96-3','198-55-0')
		AND pli.paramid in ('129-00-0','191-24-2','192-65-4','192-97-2','193-39-5','198-55-0','205-99-2','206-44-0','207-08-9','208-96-8','50-32-8','53-70-3','56-55-3','57-97-6','90-12-0','90-13-1','91-20-3','91-57-6','91-58-7')
		--AND pli.paramid in ('DF;Multiplier','BTEX;Chlorobenzenes','Dichloropropanes','TotalXylene','TotalPAHs','120-12-7','1634-04-4','218-01-9','224-42-0','56-49-5','602-87-9','83-32-9','85-01-8','86-73-7','95-49-8','98-82-8','1868-53-7','2037-26-5','460-00-4','118-79-6','1718-51-0','4165-60-0','321-60-8','3855-82-1','15067-26-2','1517-22-2','1719-03-5','1520-96-3')
		AND pli.paramtype in ('Final Value')
		--AND pli.aliasid like '%HEXACHLOROBENZENE%'
	inner join param p on
		p.paramid=pli.paramid
		--and p.paramdesc like '%HEXACHLOROBENZENE%'
		--and p.paramid like '%HydroxideAlkalinity%'
		--AND pli.calcrule like '%getNumericResults%'
   where
	pl.variantid='GLOBAL'
	AND pl.paramlistid='EPA8270_PAH_W' 	
   --pl.modifiableflag <> 'Y' --or pl.s_cancellableflag <> 'N'
   --and pl.paramlistversionid = 1
   and pl.paramlistversionid = (select max(paramlistversionid) from paramlist
	   where variantid=pl.variantid and paramlistid=pl.paramlistid)
   order by
   pl.variantid, pl.paramlistid, pl.paramlistversionid, pli.usersequence
   