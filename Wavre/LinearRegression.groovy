/*

Parameters:

InstrumentReading	Data Entry			Numeric			5 Replicates
CertifiedValue		Data Entry			Numeric			5 Replicates
SumX				Data Entry			Numeric (Calc)	1 Replicate
SumY				Data Entry			Numeric (Calc)	1 Replicate
SumProduct			Data Entry			Numeric (Calc)	1 Replicate
SumXSquared			Data Entry			Numeric (Calc)	1 Replicate
Slope				SystemCalculation	Numeric (Calc)	1 Replicate
Intercept			SystemCalculation	Numeric (Calc)	1 Replicate
CurveEquation		SystemCalculation	Text (Calc)		1 Replicate

*/
//-----------------------------------------------------------------------------
// Parameter: SumX


// Get all replicates for the Instrument Reading parameter (X value)
def valuesX = ${InstrumentReading;DataEntry;*};

// Declare sumX as BigDecimal to mantain precision
BigDecimal sumX = 0;

// Sum the values for each replicate
for (int i = 0; i < valuesX.size(); i++)
{
	sumX += valuesX[i];
}

// Return the sum
return sumX;


//-----------------------------------------------------------------------------
// Parameter: SumY

// Get all replicates for the Certified Value parameter (Y value)
def valuesY = ${CertifiedValue;DataEntry;*};

// Declare sumY as BigDecimal to mantain precision
BigDecimal sumY = 0;

// Sum the values for each replicate
for (int i = 0; i < valuesY.size(); i++)
{
	sumY += valuesY[i];
}

// Return the sum
return sumY;


//-----------------------------------------------------------------------------
// Parameter: SumXSquared


// Get all replicates for the Instrument Reading parameter (X value)
def valuesX = ${InstrumentReading;DataEntry;*};

// Declare sumXSquared  as BigDecimal to mantain precision
BigDecimal sumXSquared  = 0;

// Elevate the values for each replicate to the power of 2 and sum the result
for (int i = 0; i < valuesX.size(); i++)
{
	sumXSquared += Math.pow(valuesX[i], 2);
}

// Return the sum
return sumXSquared;


//-----------------------------------------------------------------------------
// Parameter: SumProduct


// Get all replicates for the Instrument Reading parameter (X value)
def valuesX = ${InstrumentReading;DataEntry;*};
// Get all replicates for the Certified Value parameter (Y value)
def valuesY = ${CertifiedValue;DataEntry;*};

// Declare sumProduct  as BigDecimal to mantain precision
BigDecimal sumProduct  = 0;

// Elevate the values for each replicate to the power of 2 and sum the result
for (int i = 0; i < valuesX.size(); i++)
{
	sumProduct += valuesX[i] * valuesY[i];
}

// Return the sum
return sumProduct;


//-----------------------------------------------------------------------------
// Parameter: Slope


// Get the calculated values
int nValue = ${InstrumentReading;DataEntry;*}.size();
BigDecimal sumX = ${SumX;DataEntry};
BigDecimal sumXAbs = Math.abs(sumX);
BigDecimal sumY = ${SumY;DataEntry};
BigDecimal sumXSquared = ${SumXSquared;DataEntry};
BigDecimal sumProduct = ${SumProduct;DataEntry};

BigDecimal slope = (nValue * sumProduct - sumX * sumY) / (nValue * sumXSquared - Math.pow(sumXAbs, 2));

return slope;


//-----------------------------------------------------------------------------
// Parameter: Intercept


// Get the calculated values
int nValue = ${InstrumentReading;DataEntry;*}.size();
BigDecimal sumX = ${SumX;DataEntry};
BigDecimal sumXAbs = Math.abs(sumX);
BigDecimal sumY = ${SumY;DataEntry};
BigDecimal sumXSquared = ${SumXSquared;DataEntry};
BigDecimal sumProduct = ${SumProduct;DataEntry};

BigDecimal intercept = (sumY * sumXSquared - sumX * sumProduct) / (nValue * sumXSquared - Math.pow(sumXAbs, 2));

return intercept;


//-----------------------------------------------------------------------------
// Parameter: CurveEquation


// Get the slope and intercept values
BigDecimal slope = ${Slope;SystemCalculation}
BigDecimal intercept = ${Intercept;SystemCalculation}

// Assign the signal according with the intercept value
String signal = (intercept < 0) ? "-" : "+";

// Round the slope and intercept with 5 decimals
slope = slope.round(5);
intercept = Math.abs(intercept).round(5);

// Build and return the string with the curve equation
return "y = $slope" + "x $signal $intercept"