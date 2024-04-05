package kh.mclass.semim.member.model.dto;

public class MemberDto {
//	MEMBER
//	MEM_ID    NOT NULL VARCHAR2(20)  
//	MEM_PWD   NOT NULL VARCHAR2(20)  
//	MEM_EMAIL NOT NULL VARCHAR2(100) 
	private String memId;
	private String memPwd;
	private String memEmail;

	public MemberDto(String memId, String memPwd, String memEmail) {
		super();
		this.memId = memId;
		this.memPwd = memPwd;
		this.memEmail = memEmail;
	}

	@Override
	public String toString() {
		return "MemberDto [memId=" + memId + ", memPwd=" + memPwd + ", memEmail=" + memEmail + "]";
	}

	public String getMemId() {
		return memId;
	}

	public String getMemPwd() {
		return memPwd;
	}

	public String getMemEmail() {
		return memEmail;
	}

}
