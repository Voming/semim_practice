package kh.mclass.semim.member.model.dto;

public class MemberUpdateDto {
	private String memId;
	private String memPwd;
	private String memEmail;
	private String newMemEail;

	public MemberUpdateDto(String memId, String memPwd, String memEmail, String newMemEail) {
		super();
		this.memId = memId;
		this.memPwd = memPwd;
		this.memEmail = memEmail;
		this.newMemEail = newMemEail;
	}

	@Override
	public String toString() {
		return "MemberUpdateDto [memId=" + memId + ", memPwd=" + memPwd + ", memEmail=" + memEmail + ", newMemEail="
				+ newMemEail + "]";
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

	public String getNewMemEail() {
		return newMemEail;
	}

}
