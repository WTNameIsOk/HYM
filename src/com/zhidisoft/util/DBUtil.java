package com.zhidisoft.util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.zhidisoft.exception.DataAccessException;

/**
 * ���ݿ����ͨ�ù�����
 * @author ¬����
 *
 */
public class DBUtil {
	private static String driverClass = "";
	private static String user = "";
	private static String password = "";
	private static String url = "";
	static{
		Properties prop = new Properties();
		//��jdbc������Ϣ�����ڴ沢�����õ�properites������
		try {
			prop.load(DBUtil.class.getResourceAsStream("/jdbc.properties"));
			driverClass = prop.getProperty("jdbc.driverClass");
			user = prop.getProperty("jdbc.user");
			password = prop.getProperty("jdbc.password");
			url = prop.getProperty("jdbc.url");
			Class.forName(driverClass);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * ��ȡ���ݿ����ӵķ���
	 * @return
	 */
	public static Connection getConnection(){
		try {
			return DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataAccessException("�޷���ȡ���ݿ�����",e);
		}
		
	}
	
	/**
	 * �ر��������ݿ���Դ
	 * @param rs
	 * @param stmt
	 * @param conn
	 */
	public static void close(ResultSet rs,Statement stmt,Connection conn){
		try {
			if(rs!=null){
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			if(stmt!=null){
				stmt.close();
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

	/**
	 * ִ�в�ѯ����ͨ�÷���
	 * @param sql -���� select * from teachers
	 * @param args
	 */
	public static List<Map<String,String>> query(String sql,Object...args){
		List<Map<String,String>> list = new ArrayList<Map<String,String>>();
		Connection conn = getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(sql);
			if(args!=null && args.length>0){
				for(int i=0;i<args.length;i++){
					ps.setObject(i+1, args[i]);
				}
			}
			//ִ�н��
			rs = ps.executeQuery();
			//��ȡ�������Ԫ����,��װ���β�ѯ����ѯ�����У���Щ������ʲô
			ResultSetMetaData rsmd = rs.getMetaData();
			//��ȡ��ѯ��������
			int count = rsmd.getColumnCount();
			
			while(rs.next()){
				//����һ��Map���ϣ�����ǰ�е����е��з��õ�map������
				Map<String,String> rowsData = new HashMap<String, String>();
				for(int i=0;i<count;i++){
					//�����е�������ȡ����
					String columnLabel = rsmd.getColumnLabel(i+1);
					//���������ӽ�����л�ȡĳһ�е�ֵ
					String columnValue = rs.getString(columnLabel);
					
					rowsData.put(columnLabel, columnValue);
				}
				//����ǰ�����ݷ��õ�list������
				list.add(rowsData);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataAccessException("ִ�в�ѯ���- "+sql+" -�ǳ����쳣",e);
		} finally{
			close(rs, ps, conn);
		}
		
		return list;
		
	}
	
	/**
	 * ��ѯͨ�÷���
	 * @param sql -ִ��������select * from emp
	 * @return
	 */
	public static List<Map<String,String>> query(String sql){
		return query(sql, new Object[]{});
	}
	
	/**
	 * ��ɾ��ͨ�÷���
	 * @param sql
	 * @param args
	 * @return
	 */
	public static boolean update(String sql,Object...args){
		Connection conn = getConnection();
		PreparedStatement ps = null;
		
		try {
			ps = conn.prepareStatement(sql);
			if(args!=null && args.length>0){
				for(int i=0;i<args.length;i++){
					ps.setObject(i+1, args[i]);
				}
			}
			int rows = ps.executeUpdate();
			if(rows>=0){
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			new DataAccessException("ִ�и������- "+sql+" -���ִ���",e);
		}finally{
			close(null, ps, conn);
		}
		
		return false;
	}
	
	
	/**
	 * ��ɾ��ͨ�÷���
	 * @param sql
	 * @return
	 */
	public static boolean update(String sql){
		return update(sql,new Object[]{});
	}

	/**
	 * ִ�в������,����ɹ����ظü�¼��id
	 * @param sql
	 * @param args
	 * @return
	 */
	public static Integer insert(String sql, Object...args) {
		Connection conn = getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			//����Ԥ����������ͬʱָ�������ִ����Ϸ������ݿ����ɵ�����ֵ
			ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			if(args!=null && args.length>0){
				for(int i=0;i<args.length;i++){
					ps.setObject(i+1, args[i]);
				}
			}
			//ִ��sql��� ������Ӱ�������
			int rows = ps.executeUpdate();
			//�������ɹ�
			if(rows>0){
				//��ȡ���ɵ�����,���ɵ�������ŵ��������
				rs = ps.getGeneratedKeys();
				//�ӽ�����л�ȡ���ɵ�����
				if(rs.next()){
					return rs.getInt(1);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			close(rs,ps,conn);
		}
		
		return null;
	}
}
