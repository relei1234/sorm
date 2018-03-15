package sorm.utils;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * 封装了JDBC查询常用的操作
 * @author Administrator
 *
 */
public class JDBCUtils {
	/**
	 * 给sql传参
	 * @param ps	预编译sql语句
	 * @param params
	 */
	public static void handleParams(PreparedStatement ps,Object [] params){
		if(params != null){
			for (int i = 0; i < params.length; i++) {
				try {
					ps.setObject(1+i, params[i]);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
