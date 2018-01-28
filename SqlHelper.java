/*
 * handle database class
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SqlHelper {
	PreparedStatement ps = null;
	Connection ct = null;
	ResultSet rs = null;
	String url = "jdbc:mysql://localhost;database=spdb1";
	String user = "//please input your user account id";
	String passwd = "//input your db password";
	String driver = "com.sqlserver.jdbc.Driver";
	
	public SqlHelper() {
		try {
			Class.forName(driver);
			ct = DriverManager.getConnection(url, user, passwd);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//close db
	public void close () {
		try {
			if (rs!=null) rs.close();
			if (ps!=null) ps.close();
			if (ct!=null) ct.close();
		} catch (Exception e2) {
			// TODO: handle exception
		}
	}
	
	public ResultSet queryExecute(String sql) {
		try {
			ps = ct.prepareStatement(sql);
			rs = ps.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		} finally {
			
		}
		return rs;
	}
	
	//select sql
	public ResultSet queryExecute(String sql, String[] paras) {
		try {
			ps = ct.prepareStatement(sql);
			for (int i = 0; i < paras.length; i++) {
				ps.setString(i + 1, paras[i]);
			}
			rs = ps.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		} finally {
			
		}
		return rs;
	}
	
	//add, delete, update together
	public boolean updExecute (String sql, String[] paras) {
		boolean b = true;
		try {
			ps = ct.prepareStatement(sql);
			for (int i = 0; i < paras.length; i++) {
				ps.setString(i + 1, paras[i]);
			}
			if (ps.executeUpdate() != 1) {
				b = false;
			}
			
		} catch (Exception e) {
			b = false;
			e.printStackTrace();
			// TODO: handle exception
		} finally {
			this.close();
		}
		
		return b;
	}
}
