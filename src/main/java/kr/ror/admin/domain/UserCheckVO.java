package kr.ror.admin.domain;

import java.util.Date;

public class UserCheckVO {
    private int memNo; //공지사항번호
    private String custNm; //제목
    private String custTel; //내용
    private String custId; //공지 시작일자
    private String custPw; //공지 종료일자
    private String custSt; //고객 상태(활동) 여부
    private String useYn; //탈퇴 회원 여부
    private String mngYn; //관리자 여부
    private String mngNm; //등록자
    private String modNm; //수정자
    private String imgPath; //수정자
    private Date mngReg; //등록일시
    private Date modReg; //수정일시
    
    public int getMemNo() {
        return memNo;
    }
    public void setMemNo(int memNo) {
        this.memNo = memNo;
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
    public String getCustSt() {
        return custSt;
    }
    public void setCustSt(String custSt) {
        this.custSt = custSt;
    }
    public String getUseYn() {
        return useYn;
    }
    public void setUseYn(String useYn) {
        this.useYn = useYn;
    }
    public String getMngYn() {
        return mngYn;
    }
    public void setMngYn(String mngYn) {
        this.mngYn = mngYn;
    }
    public String getMngNm() {
        return mngNm;
    }
    public void setMngNm(String mngNm) {
        this.mngNm = mngNm;
    }
    public String getModNm() {
        return modNm;
    }
    public void setModNm(String modNm) {
        this.modNm = modNm;
    }
    public String getImgPath() {
        return imgPath;
    }
    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }
    public Date getMngReg() {
        return mngReg;
    }
    public void setMngReg(Date mngReg) {
        this.mngReg = mngReg;
    }
    public Date getModReg() {
        return modReg;
    }
    public void setModReg(Date modReg) {
        this.modReg = modReg;
    }
}
