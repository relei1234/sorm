package sorm.core;

import java.util.List;

/**
 * 负责查询（对外提供服务的核心类）
 * @author Administrator
 *
 */
@SuppressWarnings("all")
public interface Query {
	/**
	 * 直接执行一个DML语句
	 * @param sql语句
	 * @param params参数
	 * @return 执行sql语句后影响记录的行数
	 */
	public int executDML(String sql,Object[]params);
	/**
	 * 将一个对象存储到数据库中
	 * 把对象中不为null的属性网数据库中存储，如果数字为null，则放0
	 * @param obj要存储的对象
	 */
	public void insert(Object obj);
	/**
	 * 删除clazz所对印的表中的记录（指定Id的记录）
	 * @param clazz跟表对应的类的class对象
	 * @param id主键的值
	 */
	public void delect(Class clazz,Object id);//delect from User where id=2;
	/**
	 * 删除对象在数据库中对印的记录（对象所在的类对应到表，对象主键的值对应到记录）
	 * @param obj
	 */
	public void delect(Object obj);
	/**
	 * 更新对象对应的记录，并且只更新指定的字段的值
	 * @param obj索要更新的对象
	 * @param fieldNames更新的属性列表
	 * @return 执行sql语句后影响的行数
	 */
	public int update(Object obj,String [] fieldNames);//update user set uname=?,pwd=?
	/**
	 * 查询返回多行记录，并将每行记录封装到clazz指定的类的对象中
	 * @param sql 查询语句
	 * @param clazz	封装数据的javabean类的Classd对象
	 * @param params  sql的参数
	 * @return	返回查询的结果
	 */
	public List queryRows(String sql,Class clazz,Object[]params);
	/**
	 * 查询返回一行记录，并将该记录封装到clazz指定的类的对象
	 * @param sql 查询结果
	 * @param clazz 封装数据的javabean类的Class对象
	 * @param params sql的参数
	 * @return 查询到的结果
	 */
	public Object queryUniqueRow(String sql,Class clazz,Object[]params);
	/**
	 * 查询返回一个值（一行一列），并将值返回
	 * @param sql 查询语句
	 * @param params sql参数
	 * @return 查询到的结果	
	 */
	public Object queryValue(String sql,Object[]params);
	/**
	 * 查询返回一个数字（一行一列），并将该值返回
	 * @param sql	查询语句
	 * @param params	sql语句
	 * @return	查询到的数字
	 */
	public Number queryNumber(String sql,Object[]params);

}
