package sorm.utils;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * ��װ��JDBC��ѯ���õĲ���
 * @author Administrator
 *
 */
public class JDBCUtils {
	/**
	 * ��sql����
	 * @param ps	Ԥ����sql���
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
