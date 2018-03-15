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
 * 负责获取管理数据库所有表结构与类结果的关系，并可以根据表结构生成类结构
 * @author Administrator
 * 
 */
public class TableContext {
	/**
	 * 表名为key，表信息对象为value
	 */
	public static Map<String,TableInfo> tables =new HashMap<String, TableInfo>();
	/**
	 * 将po的class对象和表信息对象关联起来，便于重用
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
				ResultSet set=dbmd.getColumns(null, "%", tableName, "%");//查询所有字段
				while(set.next()){
					ColumnInfo ci=new ColumnInfo(set.getString("COLUMN_NAME"),set.getString("TYPE_NAME"),0);
					ti.getColumns().put(set.getString("COLUMN_NAME"), ci);
				}
				ResultSet set2 = dbmd.getColumns(null, "%", tableName, "%");//查询t-user内的主键
				while(set2.next()){
					ColumnInfo ci2=ti.getColumns().get(set2.getObject("COLUMN_NAME"));
					ci2.setKeyType(1);//设置主键类型
					ti.getPrikeys().add(ci2);
				}
				if(ti.getPrikeys().size()>0){//取唯一主键，方便使用，如果是联合主键则为空
					ti.setOnlyPrikey(ti.getPrikeys().get(0));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//更新类结构
		updataJavaPoFile();
		//加载po包下面的所有的类，便于复用，提高效率 
		loadTables();
	}
	public static Map<String , TableInfo> getTableInfos(){
		return tables;
	}
	/**
	 * 根据表结构，更新po包下面的配置
	 * 实现了从表结构转化为类结构
	 */
	public static void updataJavaPoFile(){
		Map<String ,TableInfo > map =TableContext.tables;
		for (TableInfo t : map.values()) {
			JavaFileUtils.createJavaPoFile(t, new MySqlConvertor());
		}
	}
	/**
	 * 加载po包下面的类
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
