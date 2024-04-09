package kh.mclass.semim.board.model.service;

import static kh.mclass.jdbc.common.JdbcTemplate.*;

import java.sql.Connection;
import java.util.List;

import kh.mclass.semim.board.model.dao.BoardDao;
import kh.mclass.semim.board.model.dto.BoardDto;
import kh.mclass.semim.board.model.dto.BoardListDto;

public class BoardService {
	private BoardDao dao = new BoardDao(); 
	
	// select list - all
	public List<BoardListDto> selectAllList() {
		List<BoardListDto> result = null;
		Connection conn = getSemimConnection(true);
		result = dao.selectAllList(conn);
		close(conn);
		return result;
	}
	// select one
	public BoardDto selectOne(Integer boardId) {
		BoardDto result = null;
		Connection conn = getSemimConnection(true);
		result = dao.selectOne(conn, boardId);
		close(conn);
		return result;
	}
	// insert
	public int insert(BoardDto dto) {
		int result = 0;
		Connection conn = getSemimConnection(true);
		result = dao.insert(conn, dto);
		close(conn);
		return result;
	}
	// update
	public int update(BoardDto dto) {
		int result = 0;
		Connection conn = getSemimConnection(true);
		result = dao.update(conn, dto);
		close(conn);
		return result;
	}
	// delete
	public int delete(Integer boardId) {
		int result = 0;
		Connection conn = null;
		result = dao.delete(conn, boardId);
		close(conn);
		return result;
	}

}