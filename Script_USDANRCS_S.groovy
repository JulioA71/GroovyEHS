/* 
  TM=USDANRCS_S
  PL=USDANRCS_S
  Parameter=SampleWeightAfterOvenDrying
  [Dry_Weight_Prep;*;*;max|DrySampleWeight;Final Value;max]
 */

switch(${primary:qcsampletype})
{
case 'Unknown': 
   def sdidataitem = SGS.getSDIDataitem('labvantage',${primary:s_sampleid},${paramlistid},""+${paramlistversionid},${variantid}, ""+${dataset}, 'Multiplier','Final Value', 'max') 
/*Dry_Weight_Prep;*;*;#|DrySampleWeight;Final Value;max*/
   def weightdry=SGS.getNumericResults('labvantage',sdidataitem, 'Dry_Weight_Prep','*','*', 'max', 'DrySampleWeight', 'Final Value', 'max', false)
	if( weightdry.length>0)
   		return weightdry[0]
	else
		1
break;
case 'Dup': 
	def sdidataitem = SGS.getSDIDataitem('labvantage',${primary:s_sampleid},${paramlistid},""+${paramlistversionid},${variantid}, ""+${dataset}, 'Multiplier','Final Value', 'max') 
/*Dry_Weight_Prep;*;*;#|DrySampleWeight;Final Value;max*/
	def unknownsamples=SGS.getUnknownSampleLink(sdidataitem)
	def weightdry=SGS.sampleNumericResults('labvantage',sdidataitem,unknownsamples[0], 'Dry_Weight_Prep','*','*', 'max', 'DrySampleWeight', 'Final Value', 'max')
	if( weightdry.length>0)
		return weightdry[0]
	else
		1
break;
default:
return 1
break;
}