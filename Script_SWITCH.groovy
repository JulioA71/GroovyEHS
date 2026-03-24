switch(${PhenolphthaleinAlkalinity;Standard}) 
{
  case 0:
    result = 0
    break
  case �production�:
    result = "production"
    break
  case "uat":
    result = "uat"
    break
  default:
    result = "info"
    break
}




switch(${PhenolphthaleinAlkalinity;Standard}) 
{
  case "development":
    result = "dev"
    break
  case �production�:
    result = "production"
    break
  case "uat":
    result = "uat"
    break
  default:
    result = "info"
    break
}
return result 


def result=0;
if (${APHA9222_B_TC_Filtration_W;*;*;max|TotalPresumptiveColonies;Final Value;max}==0)
{
  result=0
}  
else
if (${VerifiedColonies;Final Value}>0 && ${TotalColoniesVerification;Final Value}>0)
{
  result=(${VerifiedColonies;Final Value}/${TotalColoniesVerification;Final Value})*${APHA9222_B_TC_Filtration_W;*;*;max|TotalPresumptiveColonies;Final Value;max}
}
else
{
  result=0
}
return result



GROOVY CONDITIONS
=================
&&: logical "and"

||: logical "or"

!: logical "not"





if( [b], [t], [f] )

    ([this]<25, "0", string ([this]))
    

/*
	Mensajes Hola Mundo
*/
for (ResultGridOptions.DefaultDataSet c : ResultGridOptions.DefaultDataSet.values())
    System.out.println(c);
Returns:

System.out.println("Hola Mundo Groovy")
return "hola"


def nombre='Julio'
def edad=50
println "Hola {nombre}, tienes {edad} a�os"

return 0
/* 
 System.out.println("Hola Mundo Groovy")
 ${DQMParam1;Final Value}
*/



$G{def MyName="Julio";
if (${FinalVolume;Standard}==0)
{
  MyName="abc"
}
else
{
  MyName=("Hello World $MyName")
}
return MyName}


def Status="Pass";
def MyName="Julio";
if (${DQMParam3;Final Value}==3)
{
  def Value1=${DQMParam3;Final Value}
  MyName=("La suma es $Value1")
}
else
{
  MyName=("Hello World $MyName")
}
return MyName


/*
    Description:    Verify if dataset # 2 is available
    Created by:     JA
    Created:        2023-11-23
    Modification log (add below)
    Date            Name                            Description
    yyyy-MM-dd      xxxxx                           xxxxx
    TM=DQMTM_DataSet
    PL=DQMPL_DataSet
    Parameter=DQMParam4 (Final Value)
    ${DQMPL_DataSet;*;*;#|DQMParam1;Final Value;max}
*/
def strStatus="non-Pass";
def numValue1=0;
def numValue2=0;
switch(${primary:qcsampletype})
{
case ['Unknown','Dup']: 
   def sdidataitem = SGS.getSDIDataitem('labvantage',${primary:s_sampleid},${paramlistid},""+${paramlistversionid},${variantid}, ""+${dataset}, 'DQMParam4','Final Value', 'max')
   /* DQMPL_DataSet|DQMParam1 - DataSet #1 */
   def numValue1=SGS.getNumericResults('labvantage',sdidataitem, 'DQMPL_DataSet','*','*', '1', 'DQMParam1', 'Final Value', 'max', false)
   /* DQMPL_DataSet|DQMParam1 - DataSet #2 */
   def numValue2=SGS.getNumericResults('labvantage',sdidataitem, 'DQMPL_DataSet','*','*', '2', 'DQMParam1', 'Final Value', 'max', false)
   if(numValue1.length>0 && numValue2.length>0)
     strStatus="Pass DS1/DS2"
   else if(numValue1.length>0)
     strStatus="Pass DS1"
   else if(numValue2.length>0)
     strStatus="Pass DS2"
   else
     strStatus="non-Pass"
break;
default:
  strStatus="Pass"
break;
}
return strStatus




private static Serializable extractIdentifier(ResultSet rs, String identifier, Type type, Class clazz)
    throws SQLException {
  if ( clazz == Long.class ) {
    return rs.getLong( identifier );
  }
  else if ( clazz == Integer.class ) {
    return rs.getInt( identifier );
  }
  else if ( clazz == Short.class ) {
    return rs.getShort( identifier );
  }
  else if ( clazz == String.class ) {
    return rs.getString( identifier );
  }
  else if ( clazz == BigInteger.class ) {
    return rs.getBigDecimal( identifier ).setScale( 0, BigDecimal.ROUND_UNNECESSARY ).toBigInteger();
  }
  else if ( clazz == BigDecimal.class ) {
    return rs.getBigDecimal( identifier ).setScale( 0, BigDecimal.ROUND_UNNECESSARY );
  }
  else {
    throw new IdentifierGenerationException(
        "unrecognized id type : " + type.getName() + " -> " + clazz.getName()
    );
  }
}


https://www.tabnine.com/code/java/methods/java.sql.ResultSet/getInt
https://www.tabnine.com/code/java/methods/java.sql.ResultSet/getString

https://www.tutorialspoint.com/groovy/groovy_forin_statement.htm
