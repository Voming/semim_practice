package kh.mclass.semim.member.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static kh.mclass.jdbc.common.JdbcTemplate.close;
import kh.mclass.semim.member.model.dto.MemberDto;

public class MemberDao {
	public int selectCheckId(Connection conn, String memId) {
		int result = 0;
		String sql = "SELECT COUNT(*) FROM MEMBER WHERE MEM_ID=?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId);
			rs = pstmt.executeQuery();
			
			if(rs !=null) {
				result = rs.getInt(1);
				System.out.println(result);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		close(rs);
		close(pstmt);
		return result;
	}
	
	public List<MemberDto> selectAllList(Connection conn) {
		List<MemberDto> result = null;
		String sql = "SELECT * FROM MEMBER";
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			if (rs != null) {
				result = new ArrayList<MemberDto>();
				while (rs.next()) {
					MemberDto dto = new MemberDto(rs.getString("MEM_ID"),rs.getString("MEM_PWD"),rs.getString("MEM_EMAIL"));
					result.add(dto);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		close(rs);
		close(pstmt);
		return result;
	}

	public MemberDto seletOne(Connection conn, String memId) {
		MemberDto result = null;
		String sql = "SELECT * FROM MEMBER WHERE MEM_ID=?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId);
			rs = pstmt.executeQuery();
			
			if(rs != null) {
				result = new MemberDto(rs.getString("MEM_ID"), rs.getString("MEM_PWD"), rs.getString("MEM_EMAIL"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		close(rs);
		close(pstmt);
		return result;
	}
	
	public int insert(Connection conn, MemberDto dto) {
		int result = 0;
		String sql = "INSERT INTO MEMBER (MEM_ID,MEM_PWD,MEM_EMAIL) VALUES (?, ?, ?)";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			// ? 처리
			pstmt.setString(1, dto.getMemId());
			pstmt.setString(2, dto.getMemPwd());
			pstmt.setString(3, dto.getMemEmail());
			System.out.println(result);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close(pstmt);
		return result;
	}
	
	public int update(Connection conn, String newId, String memId) {
		int result = 0;
		String sql = "UPDATE MEMBER SET MEM_ID=? WHERE MEM_ID=?";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, newId);
			pstmt.setString(2, memId);
			
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		close(pstmt);
		return result;
	}
	
	public int delete(Connection conn, String memId) {
		int result = 0;
		String sql = "DELETE FROM MEMBER WHERE MEM_ID=?";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId);
		
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		close(pstmt);
		return result;
	}
	
}
