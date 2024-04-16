package kh.mclass.semim.board.model.dto;

public class FileWriteDto {
	private String filePath;
	private String originalFileName;

	@Override
	public String toString() {
		return "FileWriteDto [filePath=" + filePath + ", originalFileName=" + originalFileName + "]";
	}

	public FileWriteDto(String filePath, String originalFileName) {
		super();
		this.filePath = filePath;
		this.originalFileName = originalFileName;
	}

	public String getFilePath() {
		return filePath;
	}

	public String getOriginalFileName() {
		return originalFileName;
	}

}
