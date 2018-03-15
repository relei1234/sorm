package sorm.core;

public class MySqlConvertor implements TypeConvertor{

	@Override
	public String databaseType2JavaType(String columnType) {
		//varchar--->string
		if("varchar".equalsIgnoreCase(columnType)||"char".equalsIgnoreCase(columnType)){
			return "String";
		}else if("int".equalsIgnoreCase(columnType)||
				"tinyint".equalsIgnoreCase(columnType)||
				"smallint".equalsIgnoreCase(columnType)||
				"interger".equalsIgnoreCase(columnType)
				){
			return "Integer";
		}else if("biging".equalsIgnoreCase(columnType)){
			return "Long";
		}else if("double".equalsIgnoreCase(columnType)
				||"float".equalsIgnoreCase(columnType)
				){
			return "Double";
		}else if("clob".equalsIgnoreCase(columnType)){
			return "java.sql.Clob";
		}else if("blob".equalsIgnoreCase(columnType)){
			return "java.sql.Blob";
		}else if("date".equalsIgnoreCase(columnType)){
			return "java.sql.Date";
		}else if("time".equalsIgnoreCase(columnType)){
			return "java.sql.Time";
		}else if("timestamp".equalsIgnoreCase(columnType)){
			return "java.sql.Timestamp";
		}
		return null;
	}

	@Override
	public String javaType2DatabaseType(String javaDataTupe) {
		// TODO Auto-generated method stub
		return null;
	}
/**
 * mysql数据库和java数据类型转换
 */
}
