package kr.ror.domain;

import java.sql.Date;

import lombok.Data;

@Data
public class BoardVO {
   private int memNo;
   private int pstNo;
   private String pstNm;
   private String pstCtnt;
   private String writer;
   private Date regDate;
   private String modifyer;
   private Date modDate;
   private int viewCnt;
   
}