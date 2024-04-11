package kh.mclass.semim.board.model.service;

import static kh.mclass.jdbc.common.JdbcTemplate.*;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import kh.mclass.semim.board.model.dao.BoardDao;
import kh.mclass.semim.board.model.dto.BoardDto;
import kh.mclass.semim.board.model.dto.BoardInsertDto;
import kh.mclass.semim.board.model.dto.BoardListDto;

public class BoardService {
	private BoardDao dao = new BoardDao(); 

	//selectPageList 페이지 보이는 연산       //페이지당 글 개수, 하단에 나타날 페이지 수, 현재 페이지
	public Map<String, Object> selectPageList(int pageSize, int pageBlockSize, int currentPageNum) {
		Map<String, Object> result = null;
		
		Connection conn = getSemimConnection(true);
		System.out.println("currentPageNum : " + currentPageNum);
		int start = pageSize*(currentPageNum-1)+1; //해당 페이지에서 보이는 가장 첫 번호
		int end = pageSize*currentPageNum; 
//		select t2.*
//		from (select t1.*, rownum rn from (SELECT BOARD_ID, SUBJECT,CONTENT,WRITE_TIME,LOG_IP,BOARD_WRITER,READ_COUNT    FROM BOARD order by board_id desc) t1 ) t2
//		--where rn between 한페이지당글수*(현재페이지-1)+1   and 한페이지당글수*(현재페이지)
//		;
		// 총 글 개수
		int totalCount = dao.selectTotalCount(conn); //dao에서 연결을 끊지 않고 select 해옴
		System.out.println("totalCount : " + totalCount);
		
		//전체 페이지 수 = 페이지 크기로 나누어떨어지면 그대로 페이지수 유지되고 나머지가 있다면 한페이지 더 만들어야함
		int totalPageCount = (totalCount%pageSize == 0)? totalCount/pageSize : totalCount/pageSize +1;
		
		
		// --boardlist에서 보일 것--
		//아래 페이지 번호 첫번쨰 = (현재페이지%하단에 나타날 페이지 수 == 0) ? ((현재페이지/하단에 나타날 페이지 수)-1)*페이지수 + 1  :((현재페이지/하단에 나타날 페이지 수))*페이지수 + 1
		//현재 페이지가 하단에 나타날 페이지수로 떨어지면 ->나타낼 페이지 수의 배수  (((int)(현재 위치/나타낼 페이지 수)-1)*나타낼 페이지 수+1  ex) 5면 1, 10이
		// 나누어 떨어지지 않으면 ((int)현재위치 / 나타낼 페이지 수)*나타낼 페이지 수 + 1 ex) 1이면 1, 7이면 6
		int startPageNum = (currentPageNum%pageBlockSize == 0)?((currentPageNum/pageBlockSize)-1)*pageBlockSize+1 : (currentPageNum/pageBlockSize)*pageBlockSize +1;
		//끝페이지  = (페이지 번호 첫번째 + 페이지 크기 > 전체페이지수) ? 전체페이지수 : startPageNum+페이지수 - 1;
		int endPageNum = 
	
		return result;
	}
	
	
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
	public int insert(BoardInsertDto dto) {
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
