import java.text.SimpleDateFormat
import java.util.GregorianCalendar

def sdf = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss", Locale.ENGLISH)

Date currentDate = new Date()

def rawStart = ${StartDate;DataEntry}
Date startDate

if (rawStart instanceof Date) {
	startDate = rawStart
} else if (rawStart instanceof GregorianCalendar) {
	startDate = rawStart.getTime()
} else if (rawStart) {
	startDate = sdf.parse(rawStart.toString())
} else {
	return "Start date is missing"
}

def rawFinish = ${FinishDate;DataEntry}
Date finishDate

if (rawFinish instanceof Date) {
	finishDate = rawFinish
} else if (rawFinish instanceof GregorianCalendar) {
	finishDate = rawFinish.getTime()
} else if (rawFinish) {
	finishDate = sdf.parse(rawFinish.toString())
} else {
	return "Finish date is missing"
}

// Remove time part
currentDate.clearTime()
startDate.clearTime()
finishDate.clearTime()

// Validation
if ((finishDate.before(currentDate) || finishDate.equals(currentDate)) &&
	(startDate.before(currentDate) || startDate.equals(currentDate))) {

	if (finishDate.before(startDate)) {
		return "Finish date before start date"
	} else {
		return "OK"
	}
} else {
	return "Future date entered"
}