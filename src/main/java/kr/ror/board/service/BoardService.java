package kr.ror.board.service;

import java.util.Map;

import kr.ror.domain.BoardVO;
import kr.ror.domain.Criteria;


public interface BoardService {

	public Map<String, Object> getList() throws Exception; // 목록
	public Map<String, Object> read(int pstNo) throws Exception; // 조회
	public Map<String, Object> viewCnt(int pstNo) throws Exception; // 조회수카운트 증가
	public Map<String, Object> create(BoardVO boardVO) throws Exception; //등록
	public Map<String, Object> update(BoardVO boardVO) throws Exception;//수정
	public void delete(int pstNo) throws Exception; // 삭제
//	public void updateReplyCnt(int repNo, int i);
//	public ReplyVO readReply(int cnmtNo);
	public Map<String, Object> getSearchList(Criteria cri) throws Exception; // 검색
}