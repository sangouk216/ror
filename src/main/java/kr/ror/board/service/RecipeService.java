package kr.ror.board.service;

import java.util.Map;

import kr.ror.domain.Criteria;
import kr.ror.domain.RecipeVO;

public interface RecipeService {

	public Map<String, Object> getRecipeList() throws Exception; // 목록
	public Map<String, Object> getRecipeCard(RecipeVO recipeVO) throws Exception; // 레시피카드 부분콘텐츠
	public Map<String, Object> getRecipeMainCd() throws Exception; // 레시피카드 부분콘텐츠
	public Map<String, Object> getViewCnt() throws Exception;
    public Map<String, Object> getLikeCnt() throws Exception;
	public Map<String, Object> getReadRecipe(int repNo) throws Exception; // 조회
//
	public Map<String, Object> modifyRecipeViewCnt(int repNo) throws Exception; // 조회수카운트 증가
	public Map<String, Object> addRecipe(RecipeVO recipeVO) throws Exception; // 레시피 등록
	public Map<String, Object> updateRecipe(RecipeVO recipeVO) throws Exception;//  수정
	public void deleteRecipe(int repNo) throws Exception; // 삭제
	
	public Map<String, Object> getSearchList(Criteria cri) throws Exception; // 목록
	
//	public void updateReplyCnt(int repNo, int i);
//	public ReplyVO readReply(int cnmtNo);
}