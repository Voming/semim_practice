package kh.mclass.semim.board.model.dto;

public class BoardDto {
//	BOARD_ID     NOT NULL NUMBER         
//	SUBJECT      NOT NULL VARCHAR2(120)  
//	CONTENT      NOT NULL VARCHAR2(4000) 
//	WRITE_TIME   NOT NULL TIMESTAMP(6)   
//	LOG_IP                VARCHAR2(15)   
//	BOARD_WRITER NOT NULL VARCHAR2(20)   
//	READ_COUNT   NOT NULL NUMBER

	private Integer boardId;
	private String subject;
	private String content;
	private String writeTime; // Timestamp
	private String logIp;
	private String boardWriter;
	private Integer readCount;

	public BoardDto(Integer boardId, String subject, String content, String writeTime, String logIp, String boardWriter,
			Integer readCount) {
		super();
		this.boardId = boardId;
		this.subject = subject;
		this.content = content;
		this.writeTime = writeTime;
		this.logIp = logIp;
		this.boardWriter = boardWriter;
		this.readCount = readCount;
	}

	@Override
	public String toString() {
		return "BoardDto [boardId=" + boardId + ", subject=" + subject + ", content=" + content + ", writeTime="
				+ writeTime + ", logIp=" + logIp + ", boardWriter=" + boardWriter + ", readCount=" + readCount + "]";
	}

	public Integer getBoardId() {
		return boardId;
	}

	public String getSubject() {
		return subject;
	}

	public String getContent() {
		return content;
	}

	public String getWriteTime() {
		return writeTime;
	}

	public String getLogIp() {
		return logIp;
	}

	public String getBoardWriter() {
		return boardWriter;
	}

	public Integer getReadCount() {
		return readCount;
	}

}
