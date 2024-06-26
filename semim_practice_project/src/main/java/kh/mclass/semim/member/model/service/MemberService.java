package kh.mclass.semim.member.model.service;

import static kh.mclass.jdbc.common.JdbcTemplate.*;

import java.sql.Connection;
import java.util.List;

import kh.mclass.semim.member.model.dao.MemberDao;
import kh.mclass.semim.member.model.dto.MemberDto;
import kh.mclass.semim.member.model.dto.MemberInfoDto;
import kh.mclass.semim.member.model.dto.MemberLoginDto;

public class MemberService {
	private MemberDao dao = new MemberDao();
	
	public MemberInfoDto loginGetInfo(MemberLoginDto dto) {
		MemberInfoDto result = null;
		Connection conn = getSemimConnection(true);
		result = dao.loginGetInfo(conn, dto);
		System.out.println("서비스" + result);
		close(conn);
		return result;
	}
	
	public int login(MemberLoginDto dto) {
		int result = 0;
		Connection conn = getSemimConnection(true);
		result = dao.login(conn, dto);
		close(conn);
		return result;
	}

	public int selectCheckId(String memId) {
		int result = 0;
		Connection conn = getSemimConnection(true);
		result = dao.selectCheckId(conn, memId);
		close(conn);
		return result;
	}

	public List<MemberDto> selectAllList() {
		List<MemberDto> result = null;
		Connection conn = getSemimConnection(true);
		result = dao.selectAllList(conn);
		close(conn);
		return result;
	}

	public MemberDto seletOne(String memId) {
		MemberDto result = null;
		Connection conn = getSemimConnection(true);
		result = dao.seletOne(conn, memId);
		close(conn);
		return result;
	}

	public int insert(MemberDto dto) {
		int result = 0;
		Connection conn = getSemimConnection(true);
		result = dao.insert(conn, dto);
		close(conn);
		return result;
	}

	public int update(String newId, String memId) {
		int result = 0;
		Connection conn = getSemimConnection(true);
		result = dao.update(conn, newId, memId);
		close(conn);
		return result;
	}

	public int delete(String memId) {
		int result = 0;
		Connection conn = getSemimConnection(true);
		result = dao.delete(conn, memId);
		close(conn);
		return result;
	}

}
