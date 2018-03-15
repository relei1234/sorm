package sorm.core;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import sorm.bean.Configuration;

/**
 * ����������Ϣ��ά�����Ӷ���Ĺ����������ӳأ�
 * @author Administrator
 *
 */
public class DBManager {
		private static Configuration conf;
		
		static{//��̬�����
			Properties pros=new Properties();
			try {
				pros.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("db.properties")) ;
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			conf=new Configuration();
			conf.setDriver(pros.getProperty("driver"));
			conf.setPoPackage(pros.getProperty("poPackage"));
			conf.setPwd(pros.getProperty("pwd"));
			conf.setSrcPath(pros.getProperty("srcPath"));
			conf.setUrl(pros.getProperty("url"));
			conf.setUser(pros.getProperty("user"));
			conf.setUsingDB(pros.getProperty("usingDB"));
		}
		public static Connection getConn(){
			try {
				Class.forName(conf.getDriver());
				//Ŀǰֱ�ӽ������ӣ������������ӳ�
				return DriverManager.getConnection(conf.getUrl(),conf.getUser(),conf.getPwd());
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
		
		public static void close(ResultSet rs,Statement ps,Connection conn){
			try {
				if(rs!=null){
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if(ps!=null){
					ps.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if(conn!=null){
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		public static void close(Statement ps,Connection conn){
			try {
				if(ps!=null){
					ps.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if(conn!=null){
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		public static void close(Connection conn){
		
			try {
				if(conn!=null){
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		/**
		 *��ˮconfiguration����
		 * @return
		 */
		public static Configuration getconf(){
			return conf;
		}
		
		
}
