/*
	DUBAI
*/
select * from SCHEME
where
labcode='AEenv01' and sch_code like '%niosh15%'
and sch_code like 'EAHS_NIOSH1550_100%'

select * from profjob_cuid_scheme_analyte
where
labcode='AEenv01' and sch_code='EA_NIOSH1550_MOD' and schversion=1

select * from profjob_cuid
where
labcode='AEenv01' and PRO_JOB='LB2359727'
and CUID='2300048710'


LB2359727
LB2360281
LB2360284


/*
	BRAZIL
*/
select * from SCHEME
where
labcode='brenv01' and sch_code like '%niosh15%'
and sch_code like 'EAHS_NIOSH1550_100%'

EAHS_NIOSH1550_100
EAHS_NIOSH1550_100_P

select * from scheme_analyte
where
labcode='brenv01' and sch_code='EAHS_NIOSH1550_100_P'

select * from profjob_cuid_scheme_analyte
where
labcode='brenv01' and sch_code='EAHS_NIOSH1550_100' and schversion=2

select * from profjob_cuid
where
labcode='brenv01' and pro_job='LB2301679' -- LB2301969' --LB2302197, LB2302599
--use brenvcore_sbc
select * from profjob_cuid_scheme_analyte
where
labcode='brenv01' and PRO_JOB='LB2301679'
and CUID='2300048710'