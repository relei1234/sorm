package sorm.core;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import sorm.bean.ColumnInfo;
import sorm.bean.TableInfo;
import sorm.utils.JavaFileUtils;
import sorm.utils.StringUtils;

/**
 * �����ȡ�������ݿ����б�ṹ�������Ĺ�ϵ�������Ը��ݱ�ṹ������ṹ
 * @author Administrator
 * 
 */
public class TableContext {
	/**
	 * ����Ϊkey������Ϣ����Ϊvalue
	 */
	public static Map<String,TableInfo> tables =new HashMap<String, TableInfo>();
	/**
	 * ��po��class����ͱ���Ϣ���������������������
	 */
	public static Map<Class, TableInfo> poClassTableMap=new HashMap<>();
	
	private TableContext(){
	}
	static {
		try {
			Connection conn=DBManager.getConn();
			DatabaseMetaData dbmd=conn.getMetaData();
			ResultSet tableRet= dbmd.getTables(null, "%", "%", new String []{"TABLE"});
			while(tableRet.next()){
				String tableName=(String) tableRet.getObject("TABLE_NAME");
				TableInfo ti=new TableInfo(tableName, new ArrayList<ColumnInfo>(), new HashMap<String,ColumnInfo>());
				tables.put(tableName, ti);
				ResultSet set=dbmd.getColumns(null, "%", tableName, "%");//��ѯ�����ֶ�
				while(set.next()){
					ColumnInfo ci=new ColumnInfo(set.getString("COLUMN_NAME"),set.getString("TYPE_NAME"),0);
					ti.getColumns().put(set.getString("COLUMN_NAME"), ci);
				}
				ResultSet set2 = dbmd.getColumns(null, "%", tableName, "%");//��ѯt-user�ڵ�����
				while(set2.next()){
					ColumnInfo ci2=ti.getColumns().get(set2.getObject("COLUMN_NAME"));
					ci2.setKeyType(1);//������������
					ti.getPrikeys().add(ci2);
				}
				if(ti.getPrikeys().size()>0){//ȡΨһ����������ʹ�ã����������������Ϊ��
					ti.setOnlyPrikey(ti.getPrikeys().get(0));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//������ṹ
		updataJavaPoFile();
		//����po����������е��࣬���ڸ��ã����Ч�� 
		loadTables();
	}
	public static Map<String , TableInfo> getTableInfos(){
		return tables;
	}
	/**
	 * ���ݱ�ṹ������po�����������
	 * ʵ���˴ӱ�ṹת��Ϊ��ṹ
	 */
	public static void updataJavaPoFile(){
		Map<String ,TableInfo > map =TableContext.tables;
		for (TableInfo t : map.values()) {
			JavaFileUtils.createJavaPoFile(t, new MySqlConvertor());
		}
	}
	/**
	 * ����po���������
	 */
	public static void loadTables(){
		for(TableInfo 	tableInfo:tables.values()){
			try {
				Class c=Class.forName(DBManager.getconf().getPoPackage()
						+"."+StringUtils.firstChar2UpperCase(tableInfo.getName()));
				poClassTableMap.put(c, tableInfo);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	}
	
}
