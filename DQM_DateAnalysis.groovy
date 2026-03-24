/*
    ParamID:    Organic_Mineral_Fibers
*/
def CalcResult = '';
def status = false;
def acceptedFormat = "yyyy-MM-dd";
if (${DateAnalysis;Final Value} != null) {
    def currentDate = ${DateAnalysis;Final Value}.format(acceptedFormat)
    if (currentDate >= "2020-01-01") {
        bstatus = true
        if (${Organic_Mineral_Fibers;Standard;#} == 0)
            CalcResult = 'None'
        else
            CalcResult = 'Organic Fibres Detected'
    }
}
return CalcResult
