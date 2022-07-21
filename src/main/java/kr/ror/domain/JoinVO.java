package kr.ror.domain;

import java.util.Date;

public class JoinVO {
     private int memNo;//회원번호
     private String custId;//아이디
     private String custPw;//비밀번호
     private String custNm;//닉네임
     private String custTel;//핸드폰번호
     private String mngNm;//핸드폰번호
     private Date mngReg;//등록일시
     
     public String getMngNm() {
        return mngNm;
    }
    public void setMngNm(String mngNm) {
        this.mngNm = mngNm;
    }
    private boolean mngYn;//관리자 여부
	
     
     public int getMemNo() {
		return memNo;
	}
	public void setMemNo(int memNo) {
		this.memNo = memNo;
	}
	public String getCustId() {
		return custId;
	}
	public void setCustId(String custId) {
		this.custId = custId;
	}
	public String getCustPw() {
		return custPw;
	}
	public void setCustPw(String custPw) {
		this.custPw = custPw;
	}
	public String getCustNm() {
		return custNm;
	}
	public void setCustNm(String custNm) {
		this.custNm = custNm;
	}
	public String getCustTel() {
		return custTel;
	}
	public void setCustTel(String custTel) {
		this.custTel = custTel;
	}
	public Date getMngReg() {
		return mngReg;
	}
	public void setMngReg(Date mngReg) {
		this.mngReg = mngReg;
	}
	public boolean isMngYn() {
		return mngYn;
	}
	public void setMngYn(boolean mngYn) {
		this.mngYn = mngYn;
	}
     
}
