package sorm.utils;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import sorm.bean.ColumnInfo;
import sorm.bean.JavaFieldGetSet;
import sorm.bean.TableInfo;
import sorm.core.DBManager;
import sorm.core.TypeConvertor;
/**
 * 封装了生成java（源代码）常用的操作
 * @author Administrator
 *
 */
public class JavaFileUtils {
	/**
	 * 根据字段信息生成java属性信息  var username--->private String username以及相应的set get方法
	 * @param column 字段信息
	 * @param convertor	类型转换器
	 * @return	java属性和相应的getset方法
	 */
	public static JavaFieldGetSet creatFieldGetSetSRC(ColumnInfo column,TypeConvertor convertor){
		JavaFieldGetSet jfgs=new JavaFieldGetSet();
		String javaFieldType =  convertor.databaseType2JavaType(column.getDataType());
		/**
		 * 拼接属性的字符串
		 */
		jfgs.setFiledInfo("\tprivate "+javaFieldType+" "+column.getName()+";\n");
		
		//public String getUsername(){return username;}
		/**
		 * 拼接set、get方法的字符串
		 */
		StringBuilder getSrc=new StringBuilder();
		getSrc.append("\tpublic "+javaFieldType+" get"+StringUtils.firstChar2UpperCase(column.getName())+"(){\n");
		getSrc.append("\t\treturn "+column.getName()+";\n");
		getSrc.append("\t}\n");
		jfgs.setGetInfo(getSrc.toString());
		//public String setUsername(String username){this.username=username;}
		StringBuilder setSrc=new StringBuilder();
		setSrc.append("\tpublic void set"+StringUtils.firstChar2UpperCase(column.getName())+"(");
		setSrc.append(javaFieldType+" "+column.getName()+"){\n");
		setSrc.append("\t\tthis."+column.getName()+"="+column.getName()+";\n");
		setSrc.append("\t}\n");
		jfgs.setSetInfo(setSrc.toString());
		return jfgs;
	}
	/**
	 * @param tableInfo
	 * @param convertor
	 */
	public static void createJavaPoFile(TableInfo tableInfo,TypeConvertor convertor){
		String src=creatJavaSrc(tableInfo,convertor);
		String srcPath = DBManager.getconf().getSrcPath()+"\\";
		String packagePath = DBManager.getconf().getPoPackage().replace("\\.", "\\");
		File f=new File(srcPath+packagePath);
		if(!f.exists()){
			f.mkdirs();//指定目录不存在，则帮助用户建立
		}
		BufferedWriter bw=null;
		try {
			bw=new BufferedWriter(new FileWriter(f.getAbsolutePath()+"/"+StringUtils.firstChar2UpperCase(tableInfo.getName())+".java"));
			bw.write(src);
			System.out.println("建立表"+tableInfo.getName()+"对应的java类" +StringUtils.firstChar2UpperCase(tableInfo.getName()));
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(bw!=null){
				try {
					bw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	/**
	 * 根据表信息生成java的源代码
	 * @param tableInfo	表信息
	 * @param convertor	数据类型转换器
	 * @return	java类的源代码
	 */
	public static String creatJavaSrc(TableInfo tableInfo,TypeConvertor convertor){
		 StringBuilder src=new StringBuilder();
		 Map<String, ColumnInfo> columns = tableInfo.getColumns();
		 List<JavaFieldGetSet> javaFields = new ArrayList<>();
		 for(ColumnInfo c:columns.values()){
			 javaFields.add(creatFieldGetSetSRC(c, convertor));
		 }
		 //生成package语句
		 src.append("package "+DBManager.getconf().getPoPackage()+";\n\n");
		 //生成import语句
		 src.append("import java.sql.*;\n");
		 src.append("import java.util.*;\n\n");
		 //生成类声明语句
		 src.append("public class "+StringUtils.firstChar2UpperCase(tableInfo.getName())+" {\n\n");
		 //生成属性列表
		 for(JavaFieldGetSet f:javaFields){
			 src.append(f.getFiledInfo()+"\n\n");
		 }
		 //生成get方法列表
		 for(JavaFieldGetSet f:javaFields){
			 src.append(f.getGetInfo());
		 }
		 //生成set方法列表
		 for(JavaFieldGetSet f:javaFields){
			 src.append(f.getSetInfo());
		 }
		 //生成类结束符
		 src.append("}\n");
		 return src.toString();
	}
}
