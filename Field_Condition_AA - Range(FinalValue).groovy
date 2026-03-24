/*
    Description:    get the range of parameter and format it as a string
    Created by:     Marino Orsi
    Created:        2024-05-14
    Modification log (add below)
    Date            Name                            Description
    yyyy-MM-dd      xxxxx                           xxxxx
*/
// Variable initialization
def CalcResult = '0';
// Get SDIDataItem object
def SDIDataItem = SGS.getSDIDataitem('labvantage', ${primary:s_sampleid}, ${paramlistid}, ${paramlistversionid}.toString(), ${variantid}, ${dataset}.toString(), ${#;#;#;paramid}, ${#;#;#;paramtype}, 'max');
// get the Display Format
def DisplayFormat = SGS.GetDisplayFormat('labvantage', ${primary:s_sampleid}, ${paramlistid}, ${paramlistversionid}.toString(), ${variantid}, ${dataset}.toString(), ${#;#;#;paramid}, ${#;#;#;paramtype});
// get the rounding value based on the Dispaly Format
def RoundingValue = DisplayFormat.indexOf('.') >= 0 ? DisplayFormat.substring(DisplayFormat.indexOf('.') + 1, DisplayFormat.length()).length() : 0;
// get the parameter numeric value
def NumericResult = SGS.getNumericResults('labvantage', SDIDataItem, ${paramlistid}, ${paramlistversionid}.toString(), ${variantid}, ${dataset}.toString(), 'AtmosphericPressure', 'Final Value', '*', false);
// create the string with the range if the array > 0
if (NumericResult.length > 0) {
    CalcResult = NumericResult.min().doubleValue().round(RoundingValue).toString().concat(' - ').concat(NumericResult.max().doubleValue().round(RoundingValue).toString())
} else {
    CalcResult = '0'
}

return CalcResult;
