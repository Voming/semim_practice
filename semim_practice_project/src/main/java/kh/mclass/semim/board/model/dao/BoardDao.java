package kh.mclass.semim.board.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static kh.mclass.jdbc.common.JdbcTemplate.close;

import kh.mclass.semim.board.model.dto.BoardDto;
import kh.mclass.semim.board.model.dto.BoardInsertDto;
import kh.mclass.semim.board.model.dto.BoardListDto;
import kh.mclass.semim.board.model.dto.BoardReadDto;
import kh.mclass.semim.board.model.dto.BoardReplyListDto;
import kh.mclass.semim.board.model.dto.BoardReplyWriteDto;
import kh.mclass.semim.board.model.dto.FileReadDto;
import kh.mclass.semim.board.model.dto.FileWriteDto;

public class BoardDao {
	
	public List<FileReadDto> selectFileList(Connection conn, Integer boardId) {
		List<FileReadDto> result = null;
//		(BOARD_ID, BOARD_FILE_ID, SAVED_FILE_PATH_NAME, ORIGINAL_FILENAME)
		String sql = "SELECT BOARD_ID, BOARD_FILE_ID, SAVED_FILE_PATH_NAME, ORIGINAL_FILENAME   FROM BOARD_FILE WHERE BOARD_ID=?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			/*
			 * // pstmt = conn.prepareStatement(sql);
			 */			// ? 처리
			pstmt.setInt(1, boardId);
			rs = pstmt.executeQuery();
			// ResetSet처리
			if(rs.next()) {
				result = new ArrayList<FileReadDto>();
				do {
					FileReadDto dto = new FileReadDto(	
							rs.getInt("BOARD_ID"),rs.getInt("BOARD_FILE_ID"),
							rs.getString("SAVED_FILE_PATH_NAME"),rs.getString("ORIGINAL_FILENAME")
							);
					result.add(dto);
				}while (rs.next());
			}	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close(rs);
		close(pstmt);
		return result;
	}
	
	
	// 댓글 리스트 전채 가져오기(id구분) select list - board reply : board_id 
	public List<BoardReplyListDto> selectBoardReplyList(Connection conn, Integer boardId) {
		List<BoardReplyListDto> result = null;
		//순서를 정해서 리스트를 넘겨줌
		String sql = "select BOARD_REPLY_ID,  BOARD_REPLY_WRITER,BOARD_REPLY_CONTENT, "
				+ " BOARD_REPLY_WRITE_TIME, "
				+ " BOARD_REPLY_LEVEL,BOARD_REPLY_REF, BOARD_REPLY_STEP "
				+ " from board_reply where board_id=? order by board_reply_ref desc,  board_reply_step";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			// ? 처리
			pstmt.setInt(1, boardId);
			rs = pstmt.executeQuery();
			// ResetSet처리
			if(rs.next()) {
				result = new ArrayList<BoardReplyListDto>();
				do {
					BoardReplyListDto dto = new BoardReplyListDto(	
							rs.getInt("BOARD_REPLY_ID"),rs.getString("BOARD_REPLY_WRITER"),
							rs.getString("BOARD_REPLY_CONTENT"),rs.getString("BOARD_REPLY_WRITE_TIME"),
							rs.getInt("BOARD_REPLY_LEVEL"),rs.getInt("BOARD_REPLY_REF"),rs.getInt("BOARD_REPLY_STEP")
							);
					result.add(dto);
				}while (rs.next());
			}	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close(rs);
		close(pstmt);
		return result;
	}
	
	
	// 게시글 전체 개수 select total Count
	public int selectTotalCount(Connection conn) {
		int result = 0;
		String sql = "select count(*) cnt from board";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			// ? 처리
			rs = pstmt.executeQuery();
			// ResetSet처리
			if(rs.next()) {
				result = rs.getInt("cnt");
			}	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close(rs);
		close(pstmt);
		return result;
	}
	// 게시글 리스트 필요한 만큼 조회 select list - all
	public List<BoardListDto> selectPageList(Connection conn, int start, int end) {
		List<BoardListDto> result = null;
		String sql = "select t2.*"
				+"   from (select t1.*, rownum rn" 
			    +"       from (SELECT BOARD_ID, SUBJECT,CONTENT,WRITE_TIME,LOG_IP,BOARD_WRITER,READ_COUNT    FROM BOARD order by board_id desc) t1 ) t2"
			    +"   where rn between ?   and ?"
			    ;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			// ? 처리
			pstmt.setInt(1, start);//한페이지당글수*(현재페이지-1)+1
			pstmt.setInt(2, end);//한페이지당글수*(현재페이지)
			rs = pstmt.executeQuery();
			// ResetSet처리
			if(rs.next()) {
				result = new ArrayList<BoardListDto>();
				do {
					BoardListDto dto = new BoardListDto(	
							rs.getInt("BOARD_ID"),rs.getString("SUBJECT"),
							rs.getString("WRITE_TIME"),rs.getString("BOARD_WRITER"),
							rs.getInt("READ_COUNT")
							);
					result.add(dto);
				}while (rs.next());
			}	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close(rs);
		close(pstmt);
		return result;
	}
	
	// 전체 리스트 조회 select list - all
	public List<BoardListDto> selectAllList(Connection conn) {
		List<BoardListDto> result = null;
		String sql = "SELECT BOARD_ID, SUBJECT,CONTENT,WRITE_TIME,LOG_IP,BOARD_WRITER,READ_COUNT    FROM BOARD";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			// ? 처리
			rs = pstmt.executeQuery();
			// ResetSet처리
			if(rs.next()) {
				result = new ArrayList<BoardListDto>();
				do {
					BoardListDto dto = new BoardListDto(	
							rs.getInt("BOARD_ID"),rs.getString("SUBJECT"),
							rs.getString("WRITE_TIME"),rs.getString("BOARD_WRITER"),
							rs.getInt("READ_COUNT")
							);
					result.add(dto);
				}while (rs.next());
			}	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close(rs);
		close(pstmt);
		return result;
	}
	// 아이디로 조회해서 게시글 전체 정보 가져옴 select one
	public BoardReadDto selectOne(Connection conn, Integer boardId) {
		BoardReadDto result = null;
		String sql = "SELECT BOARD_ID,SUBJECT,CONTENT,WRITE_TIME,LOG_IP,BOARD_WRITER,READ_COUNT    FROM BOARD WHERE BOARD_ID=?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			// ? 처리
			pstmt.setInt(1, boardId);
			rs = pstmt.executeQuery();
			// ResetSet처리
			if(rs.next()) {
				result = new BoardReadDto(	
						rs.getInt("BOARD_ID"),rs.getString("SUBJECT"),rs.getString("CONTENT"),
						rs.getString("WRITE_TIME"),rs.getString("LOG_IP"),rs.getString("BOARD_WRITER"),
						rs.getInt("READ_COUNT")
						);
			}			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close(rs);
		close(pstmt);
		return result;
	}
	
	// 게시글 추가할때 아이디 자동 증가 select 
	public int getSequenceNum(Connection conn) {
		int result = 0;
		String sql = "SELECT SEQ_BOARD_ID.nextval FROM DUAL";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			// ? 처리
			rs = pstmt.executeQuery();
			// ResetSet처리
			if(rs.next()) {
				result = rs.getInt(1);
			}			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close(rs);
		close(pstmt);
		return result;
	}
	
	// insert - Reply 댓글 대댓글
	public int insertRReply(Connection conn, BoardReplyWriteDto dto) {
		int result = 0;  // 1 정상, 0비정상
		String sql = " INSERT INTO BOARD_REPLY VALUES ( (SELECT NVL(MAX(BOARD_REPLY_ID),0)+1 FROM BOARD_REPLY), ?,"
				+ "            ?, ? , default , null, "
				+ "            (SELECT BOARD_REPLY_LEVEL+1 FROM BOARD_REPLY WHERE BOARD_REPLY_ID = ? )  , "
				+ "            (SELECT BOARD_REPLY_REF     FROM BOARD_REPLY WHERE BOARD_REPLY_ID = ? )  , "
				+ "            (SELECT BOARD_REPLY_STEP+1  FROM BOARD_REPLY WHERE BOARD_REPLY_ID = ? )  )";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			// ? 처리
			pstmt.setInt(1, dto.getBoardId());
			pstmt.setString(2, dto.getBoardReplyWriter());
			pstmt.setString(3, dto.getBoardReplyContent());
			pstmt.setInt(4, dto.getBoardReplyId()); // BOARD_REPLY_LEVEL+1
			pstmt.setInt(5, dto.getBoardReplyId()); // BOARD_REPLY_REF
			pstmt.setInt(6, dto.getBoardReplyId()); // BOARD_REPLY_STEP+1
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close(pstmt);
		return result;
	}
	
	// insert - Reply 댓글 원본글
	public int insertReply(Connection conn, BoardReplyWriteDto dto) {
		int result = 0;
		String sql = " INSERT INTO BOARD_REPLY VALUES"
				+ "        ( (SELECT NVL(MAX(BOARD_REPLY_ID),0)+1 FROM BOARD_REPLY) , ?, "
				+ "            ?, ? , default , null, "
				+ "            DEFAULT , (SELECT NVL(MAX(BOARD_REPLY_ID),0)+1 FROM BOARD_REPLY), DEFAULT )";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			// ? 처리
			pstmt.setInt(1, dto.getBoardId());
			pstmt.setString(2, dto.getBoardReplyWriter());
			pstmt.setString(3, dto.getBoardReplyContent());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close(pstmt);
		return result;
	}
	
	
	// insert
	public int insert(Connection conn, BoardInsertDto dto) {  //파일과 함께 게시글 추가
		System.out.println("boardDao insert() param : "+dto);
		int result = 0;
//		INSERT INTO BOARD VALUES (SEQ_BOARD_ID.nextval, '제목1', '내용1', default, '127.0.0.1', 'kh1', default);
//		String sql = "INSERT INTO BOARD (BOARD_ID,SUBJECT,CONTENT,WRITE_TIME,LOG_IP,BOARD_WRITER,READ_COUNT)"
//				+ " VALUES (SEQ_BOARD_ID.nextval, ?, ?, default, ?, ?, default)";
		String sql = "INSERT ALL ";   //만약 파일이 없는 채로 왔다면 여기만 실행
		sql+="	INTO BOARD (BOARD_ID,SUBJECT,CONTENT,WRITE_TIME,LOG_IP,BOARD_WRITER,READ_COUNT) ";
		sql+="		VALUES (SEQ_BOARD_ID.nextval, ?, ?, default, ?, ?, default) ";
		
		if(dto.getFileList()!= null && dto.getFileList().size()>0) {  //파일이 있다면 보드 파일 테이블에도 값 넣어줌
			for(FileWriteDto filedto :dto.getFileList()) {
		sql+="	INTO BOARD_FILE (BOARD_ID, BOARD_FILE_ID, SAVED_FILE_PATH_NAME, ORIGINAL_FILENAME) ";
		sql+="		VALUES (SEQ_BOARD_ID.nextval, ?, ?, ?) ";
			}
		}
		sql+="	SELECT * FROM DUAL ";  //조인결과
		System.out.println("sql: "+ sql);
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);  //전체sql 실행
			// ? 처리
			int i = 1;
			pstmt.setString(i++, dto.getSubject());   // 숫자 일일이 넣어주지 않는 방법 i++
			pstmt.setString(i++, dto.getContent());
			pstmt.setString(i++, null); //TODO	pstmt.setString(3, dto.getLogIp());
			pstmt.setString(i++, dto.getBoardWriter());
			if(dto.getFileList()!= null && dto.getFileList().size()>0) {  //파일 넣었는지 한번더 체크해서 ?값 넣기
				int fileId = 1;  
				for(FileWriteDto filedto :dto.getFileList()) { //그런데 넣은 파일 수만큼 반복문 돌면서
					pstmt.setInt(i++, fileId++);
					pstmt.setString(i++, filedto.getFilePath());
					pstmt.setString(i++, filedto.getOriginalFileName());
				}
			}
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close(pstmt);
		System.out.println("boardDao insert() return : "+result);
		return result;
	}
	
	// 대댓글 달때 step 하나씩 올려줌 update - reply Step
	public int updateReplyStep(Connection conn, Integer boardReplyId) {
		int result = -1;  // 0~n 정상이므로 비정상인경우-1
		String sql = "UPDATE BOARD_REPLY SET BOARD_REPLY_STEP = BOARD_REPLY_STEP+1  WHERE "  //지금 단 댓글이 아니라면 같은 보드 아이디에 단 댓글은 다 step을 하나씩 올림
				+ "            BOARD_REPLY_REF = ( SELECT BOARD_REPLY_REF FROM BOARD_REPLY WHERE BOARD_REPLY_ID = ?)"
				+ "            AND "
				+ "            BOARD_REPLY_STEP > ( SELECT BOARD_REPLY_STEP FROM BOARD_REPLY WHERE BOARD_REPLY_ID = ? )";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			// ? 처리
			pstmt.setInt(1, boardReplyId);
			pstmt.setInt(2, boardReplyId);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close(pstmt);
		return result;
	}
	// 조회수 증가 update - readCount
	public int updateReadCount(Connection conn, Integer boardId) {
		int result = 0;
		String sql = "update board set read_count=read_count+1 where board_id=?";  //TODO
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			// ? 처리
			pstmt.setInt(1, boardId);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close(pstmt);
		return result;
	}
	// update
	public int update(Connection conn, BoardDto dto) {
		int result = 0;
		String sql = "";  //TODO
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			// ? 처리
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close(pstmt);
		return result;
	}
	// delete
	public int delete(Connection conn, Integer boardId) {
		int result = 0;
		String sql = "DELETE FROM BOARD WHERE BOARD_ID=?";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			// ? 처리
			pstmt.setInt(1, boardId);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close(pstmt);
		return result;
	}
	
}
