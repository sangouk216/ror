package kr.ror.domain;

import java.util.Date;

public class MateChoiceVO {
	private Long matNo; // 재료번호.
	private String mainCd; // 대분류코드.
	private String midCd; // 중분류코드.
	private String matNm; // 재료이름.
	private String useYn; // 사용여부.
	private String mngNm; // 등록자.
	private Date mngReg; // 등록일시.
	private String modNm; // 수정자.
	private Date modReg; // 수정일시.
	
	
	public Long getMatNo() {
		return matNo;
	}
	public void setMatNo(Long matNo) {
		this.matNo = matNo;
	}
	public String getMainCd() {
		return mainCd;
	}
	public void setMainCd(String mainCd) {
		this.mainCd = mainCd;
	}
	public String getMidCd() {
		return midCd;
	}
	public void setMidCd(String midCd) {
		this.midCd = midCd;
	}
	public String getMatNm() {
		return matNm;
	}
	public void setMatNm(String matNm) {
		this.matNm = matNm;
	}
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
	public String getMngNm() {
		return mngNm;
	}
	public void setMngNm(String mngNm) {
		this.mngNm = mngNm;
	}
	public Date getMngReg() {
		return mngReg;
	}
	public void setMngReg(Date mngReg) {
		this.mngReg = mngReg;
	}
	public String getModNm() {
		return modNm;
	}
	public void setModNm(String modNm) {
		this.modNm = modNm;
	}
	public Date getModReg() {
		return modReg;
	}
	public void setModReg(Date modReg) {
		this.modReg = modReg;
	}
	
	
	
	
	
}
