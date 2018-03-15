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
 * ��װ������java��Դ���룩���õĲ���
 * @author Administrator
 *
 */
public class JavaFileUtils {
	/**
	 * �����ֶ���Ϣ����java������Ϣ  var username--->private String username�Լ���Ӧ��set get����
	 * @param column �ֶ���Ϣ
	 * @param convertor	����ת����
	 * @return	java���Ժ���Ӧ��getset����
	 */
	public static JavaFieldGetSet creatFieldGetSetSRC(ColumnInfo column,TypeConvertor convertor){
		JavaFieldGetSet jfgs=new JavaFieldGetSet();
		String javaFieldType =  convertor.databaseType2JavaType(column.getDataType());
		/**
		 * ƴ�����Ե��ַ���
		 */
		jfgs.setFiledInfo("\tprivate "+javaFieldType+" "+column.getName()+";\n");
		
		//public String getUsername(){return username;}
		/**
		 * ƴ��set��get�������ַ���
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
			f.mkdirs();//ָ��Ŀ¼�����ڣ�������û�����
		}
		BufferedWriter bw=null;
		try {
			bw=new BufferedWriter(new FileWriter(f.getAbsolutePath()+"/"+StringUtils.firstChar2UpperCase(tableInfo.getName())+".java"));
			bw.write(src);
			System.out.println("������"+tableInfo.getName()+"��Ӧ��java��" +StringUtils.firstChar2UpperCase(tableInfo.getName()));
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
	 * ���ݱ���Ϣ����java��Դ����
	 * @param tableInfo	����Ϣ
	 * @param convertor	��������ת����
	 * @return	java���Դ����
	 */
	public static String creatJavaSrc(TableInfo tableInfo,TypeConvertor convertor){
		 StringBuilder src=new StringBuilder();
		 Map<String, ColumnInfo> columns = tableInfo.getColumns();
		 List<JavaFieldGetSet> javaFields = new ArrayList<>();
		 for(ColumnInfo c:columns.values()){
			 javaFields.add(creatFieldGetSetSRC(c, convertor));
		 }
		 //����package���
		 src.append("package "+DBManager.getconf().getPoPackage()+";\n\n");
		 //����import���
		 src.append("import java.sql.*;\n");
		 src.append("import java.util.*;\n\n");
		 //�������������
		 src.append("public class "+StringUtils.firstChar2UpperCase(tableInfo.getName())+" {\n\n");
		 //���������б�
		 for(JavaFieldGetSet f:javaFields){
			 src.append(f.getFiledInfo()+"\n\n");
		 }
		 //����get�����б�
		 for(JavaFieldGetSet f:javaFields){
			 src.append(f.getGetInfo());
		 }
		 //����set�����б�
		 for(JavaFieldGetSet f:javaFields){
			 src.append(f.getSetInfo());
		 }
		 //�����������
		 src.append("}\n");
		 return src.toString();
	}
}
