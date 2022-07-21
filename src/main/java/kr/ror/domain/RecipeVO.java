package kr.ror.domain;

import java.sql.Date;

public class RecipeVO {
	private int repNo; //레시피 번호
	private int memNo; //회원 번호
	private String repName;//레시피 제목
	private String repMainCd; //대분류코드
	private String mateNm; //중분류코드
	private String writer; //작성자
	private Date regDate; //등록일
	private String repCtnt; //내용
	private Long viewCnt; //조회수
	private Long likeCnt; //좋아요 수
	private int searchYN; //좋아요 수
	
	
	public int getRepNo() {
		return repNo;
	}
	public void setRepNo(int repNo) {
		this.repNo = repNo;
	}
	public int getMemNo() {
		return memNo;
	}
	public void setMemNo(int memNo) {
		this.memNo = memNo;
	}
	public String getRepName() {
		return repName;
	}
	public void setRepName(String repName) {
		this.repName = repName;
	}
	public String getRepMainCd() {
		return repMainCd;
	}
	public void setRepMainCd(String repMainCd) {
		this.repMainCd = repMainCd;
	}
	public String getmateNm() {
		return mateNm;
	}
	public void setRepMidCd(String mateNm) {
		this.mateNm = mateNm;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	public String getRepCtnt() {
		return repCtnt;
	}
	public void setRepCtnt(String repCtnt) {
		this.repCtnt = repCtnt;
	}
    public Long getViewCnt() {
        return viewCnt;
    }
    public void setViewCnt(Long viewCnt) {
        this.viewCnt = viewCnt;
    }
    public Long getLikeCnt() {
        return likeCnt;
    }
    public void setLikeCnt(Long likeCnt) {
        this.likeCnt = likeCnt;
    }
    public int getSearchYN() {
        return searchYN;
    }
    public void setSearchYN(int searchYN) {
        this.searchYN = searchYN;
    }


	
}
