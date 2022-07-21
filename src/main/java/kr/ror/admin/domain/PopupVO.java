package kr.ror.admin.domain;

import java.util.Date;

public class PopupVO {
    private Long ntc_no; //공지사항번호
    private String ntc_title; //제목
    private String ntc_ctnt; //내용
    private Date ntc_st_reg; //공지 시작일자
    private Date ntc_ed_reg; //공지 종료일자
    private String ues_yn; //사용여부
    private String rmk; //비고
    private String mng_nm; //등록자
    private String mod_nm; //수정자
    private Date mng_reg; //등록일시
    private Date mod_reg; //수정일시
    
    public Long getNtc_no() {
        return ntc_no;
    }
    public void setNtc_no(Long ntc_no) {
        this.ntc_no = ntc_no;
    }
    public String getNtc_title() {
        return ntc_title;
    }
    public void setNtc_title(String ntc_title) {
        this.ntc_title = ntc_title;
    }
    public String getNtc_ctnt() {
        return ntc_ctnt;
    }
    public void setNtc_ctnt(String ntc_ctnt) {
        this.ntc_ctnt = ntc_ctnt;
    }
    public Date getNtc_st_reg() {
        return ntc_st_reg;
    }
    public void setNtc_st_reg(Date ntc_st_reg) {
        this.ntc_st_reg = ntc_st_reg;
    }
    public Date getNtc_ed_reg() {
        return ntc_ed_reg;
    }
    public void setNtc_ed_reg(Date ntc_ed_reg) {
        this.ntc_ed_reg = ntc_ed_reg;
    }
    public String getUes_yn() {
        return ues_yn;
    }
    public void setUes_yn(String ues_yn) {
        this.ues_yn = ues_yn;
    }
    public String getRmk() {
        return rmk;
    }
    public void setRmk(String rmk) {
        this.rmk = rmk;
    }
    public String getMng_nm() {
        return mng_nm;
    }
    public void setMng_nm(String mng_nm) {
        this.mng_nm = mng_nm;
    }
    public String getMod_nm() {
        return mod_nm;
    }
    public void setMod_nm(String mod_nm) {
        this.mod_nm = mod_nm;
    }
    public Date getMng_reg() {
        return mng_reg;
    }
    public void setMng_reg(Date mng_reg) {
        this.mng_reg = mng_reg;
    }
    public Date getMod_reg() {
        return mod_reg;
    }
    public void setMod_reg(Date mod_reg) {
        this.mod_reg = mod_reg;
    }
    
}