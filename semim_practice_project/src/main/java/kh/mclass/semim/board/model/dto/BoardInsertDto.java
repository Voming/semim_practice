package kh.mclass.semim.board.model.dto;

public class BoardInsertDto {
//	BOARD_ID     NOT NULL NUMBER         
//	SUBJECT      NOT NULL VARCHAR2(120)  
//	CONTENT      NOT NULL VARCHAR2(4000) 
//	WRITE_TIME   NOT NULL TIMESTAMP(6)   
//	LOG_IP                VARCHAR2(15)   
//	BOARD_WRITER NOT NULL VARCHAR2(20)   
//	READ_COUNT   NOT NULL NUMBER

	private Integer boardId;
	private String subject;
	private String boardWriter;
	public BoardInsertDto(Integer boardId, String subject, String boardWriter) {
		super();
		this.boardId = boardId;
		this.subject = subject;
		this.boardWriter = boardWriter;
	}
	@Override
	public String toString() {
		return "BoardInsertDto [boardId=" + boardId + ", subject=" + subject + ", boardWriter=" + boardWriter + "]";
	}
	public Integer getBoardId() {
		return boardId;
	}
	public String getSubject() {
		return subject;
	}
	public String getBoardWriter() {
		return boardWriter;
	}
}
