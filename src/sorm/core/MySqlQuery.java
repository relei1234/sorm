package sorm.core;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Vo.EmpVo;
import po.Emp;
import sorm.bean.ColumnInfo;
import sorm.bean.TableInfo;
import sorm.utils.JDBCUtils;
import sorm.utils.ReflectUtil;
import sorm.utils.StringUtils;
/**
 * 针对mysql数据库的查询
 * @author Administrator
 *
 */
@SuppressWarnings("all")
public class MySqlQuery implements Query{
	
	public static void main(String[] args) {
		String sql2="select e.id,e.empname,salary 'xinshui' ,age,d.dname 'deptName' ,d.address 'deptAddr' from emp e join dept d on e.deptId=d.id";
		List<EmpVo> list=new MySqlQuery().queryRows(sql2, EmpVo.class, null);
		for(EmpVo e:list){
			System.out.println(e.getEmpname()+e.getDeptAddr()+e.getXinshui());
		}
	}

	@Override
	public int executDML(String sql, Object[] params) {
		Connection conn=DBManager.getConn();
		int count = 0;
		PreparedStatement ps=null;
		try {
			ps=conn.prepareStatement(sql);
			//给sql设置参数
			JDBCUtils.handleParams(ps, params);
			count = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DBManager.close(ps,conn);
		}
		return count;
	}

	@Override
	public void insert(Object obj) {
		//把对象obj-->表中 insert into 表名（id,uname,pwd）values (?,?,?)
		Class c=obj.getClass();
		List<Object> params = new ArrayList<>();//存储sql参数
		TableInfo info=TableContext.poClassTableMap.get(c);
		StringBuilder sql = new StringBuilder("insert into "+info.getName()+" (");
		int countNotNullField = 0;//计算不为空的属性值
		Field[] fs  = c.getDeclaredFields();
		for (Field f : fs) {
			String fieldName = f.getName();
			Object fieldValue = ReflectUtil.invokeGet(fieldName, obj); 
			if(fieldValue != null){
				countNotNullField++;
				sql.append(fieldName+",");
				params.add(fieldValue);
			}
		}
		System.out.println(countNotNullField);
		sql.setCharAt(sql.length()-1, ')');
		sql.append(" values (");
		for (int i = 0; i < countNotNullField; i++) {
			sql.append("?,");
		}
		sql.setCharAt(sql.length()-1, ')');
		System.out.println(sql);
		executDML(sql.toString(), params.toArray());
	}

	@Override
	public void delect(Class clazz, Object id) {
		//Emp.class ,2--->delete from emp where id=2
		
		//通过Class找对象，找TableInfo  
		TableInfo tableInfo=TableContext.poClassTableMap.get(clazz);
		
		ColumnInfo onlyPriKey = tableInfo.getOnlyPrikey();
		
		String sql = "delete from "+tableInfo.getName()+" where "+onlyPriKey.getName()+" = ? ";
		
		executDML(sql, new Object[]{id});
		
	}

	@Override
	public void delect(Object obj) {
		Class c=obj.getClass();
		TableInfo tableInfo=TableContext.poClassTableMap.get(c);
		ColumnInfo onlyPriKey = tableInfo.getOnlyPrikey();
		
		
		Object priKeyValue = ReflectUtil.invokeGet(onlyPriKey.getName(), obj);
		delect(c, priKeyValue);
	}

	@Override
	public int update(Object obj, String[] fieldNames) {
		//obj{"uname","pwd"}---->update 表名 set uname=?,pwd=? where id=?
		Class c=obj.getClass();
		List<Object> params = new ArrayList<>();//存储sql参数
		TableInfo info=TableContext.poClassTableMap.get(c);
		ColumnInfo priKey = info.getOnlyPrikey();//获取唯一的主键
		StringBuilder sql = new StringBuilder("update "+info.getName()+" set ");
		for (String  fname : fieldNames) {
			Object fvalue=ReflectUtil.invokeGet(fname, obj);
			params.add(fvalue);
			sql.append(fname+"=?,");
		}
		sql.setCharAt(sql.length()-1, ' ');
		sql.append(" where ");
		sql.append(priKey.getName()+"=? ");
		 System.out.println(sql);
		params.add(ReflectUtil.invokeGet(priKey.getName(), obj));
		return executDML(sql.toString(), params.toArray());
	}

	@Override
	public List queryRows(String sql, Class clazz, Object[] params) {
		Connection conn=DBManager.getConn();
		List list = null;
		PreparedStatement ps=null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(sql);
			//给sql传参
			JDBCUtils.handleParams(ps, params);
			System.out.println(sql);
			rs = ps.executeQuery();
			
			ResultSetMetaData metaData =  rs.getMetaData();
			//多行
			while(rs.next()){
				if(list==null){
					list = new ArrayList<>();
				}
				Object row = clazz.newInstance();//调用javabean的无参构造器
				//多列 select username,pwd,age from user where id>? and age>18
				for(int i = 0; i<metaData .getColumnCount();i++){
					String columnName = metaData.getColumnLabel(i+1);
					Object columnValue = rs.getObject(i+1);
					//调用rowObj对象的setUsername方法，将columnValue设置放进去
					ReflectUtil.invokeSet(row, columnName, columnValue);
				}
				list.add(row);
				return list;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Object queryUniqueRow(String sql, Class clazz, Object[] params) {
		List list = queryRows(sql, clazz, params);
		return (list == null&&list.size()>0)?null:list.get(0);
	}

	@Override
	public Object queryValue(String sql, Object[] params) {
		Connection conn=DBManager.getConn();
		Object value	= null;
		PreparedStatement ps=null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(sql);
			//给sql传参
			JDBCUtils.handleParams(ps, params);
			System.out.println(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				value = rs.getObject(1);
			}
		}catch(Exception e){
		}finally{
			DBManager.close(ps,conn);
		}
		return value;
	}

	@Override
	public Number queryNumber(String sql, Object[] params) {
		return (Number)queryValue(sql, params);
	}

}
