/*
	Query Sample and TM/PL
*/
select sdi.dataset,sdi.* from sdidata sdi
where
sdi.variantid='Global'
and sdi.sdcid='Sample'
and sdi.keyid1='S-ALL_L1000394'
--and createdt >= '20231205'
and sdi.paramlistid='DQMPL_DataSet'
--and sdi.paramlistversionid=1
and sdi.dataset in (select max(dataset) from sdidata where variantid=sdi.variantid and sdcid=sdi.sdcid and keyid1=sdi.keyid1 and paramlistid=sdi.paramlistid and paramlistversionid=sdi.paramlistversionid)

sp_help sdidata
