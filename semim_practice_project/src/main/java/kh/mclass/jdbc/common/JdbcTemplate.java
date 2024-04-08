package kh.mclass.jdbc.common;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

public class JdbcTemplate {
	public JdbcTemplate() {
	}

	// static method 로 만들기
	public static Connection getSemimConnection(boolean isLocalhost) {
		Connection conn = null;
		Properties prop = new Properties();
		try {
			String currentPath = JdbcTemplate.class.getResource("./").getPath(); 
			prop.load(new FileReader(currentPath+"driver.properties"));
			Class.forName(prop.getProperty("jdbc.driver"));
			if(isLocalhost) {
				conn = DriverManager.getConnection(prop.getProperty("jdbc.semim.localhost.url")
						, prop.getProperty("jdbc.semim.username")
						, prop.getProperty("jdbc.semim.password"));
			} else {
				conn = DriverManager.getConnection(prop.getProperty("jdbc.semim.dbserver.url")	
						, prop.getProperty("jdbc.semim.username")
						, prop.getProperty("jdbc.semim.password"));
			}
			if(conn != null) System.out.println("연결성공"); else System.out.println("연결실패!!!!!!!!!");
			//conn.setAutoCommit(false);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

	// scott 계정 연결
	public static Connection getConnection() {
		Connection conn = null;
		Properties prop = new Properties();
		try {
			String currentPath = JdbcTemplate.class.getResource("./").getPath();

			prop.load(new FileReader(currentPath + "driver.properties"));
			Class.forName(prop.getProperty("jdbc.driver"));

			conn = DriverManager.getConnection(prop.getProperty("jdbc.semim.localhost.url"),
					prop.getProperty("jdbc.username"), prop.getProperty("jdbc.password"));

			if (conn != null) {
				System.out.println("연결 성공");
			} else {
				System.out.println("연결 실패");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

	// 커밋설정
	public static void autoCommit(Connection con, boolean autocommit) {
		try {
			if (con != null)
				con.setAutoCommit(autocommit);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void Commit(Connection con) {
		try {
			if (con != null)
				con.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void Rollback(Connection con) {
		try {
			if (con != null)
				con.rollback();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void close(Connection con) {
		try {
			if (con != null)
				con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void close(ResultSet rs) {
		try {
			if (rs != null)
				rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void close(PreparedStatement pstmt) {
		try {
			if (pstmt != null)
				pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
