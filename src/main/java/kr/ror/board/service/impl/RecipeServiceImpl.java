package kr.ror.board.service.impl;


import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import kr.ror.board.service.RecipeService;
import kr.ror.common.Constants;
import kr.ror.common.dao.CommonDAO;
import kr.ror.domain.Criteria;
import kr.ror.domain.RecipeVO;


@Service
public class RecipeServiceImpl implements RecipeService {
	@Autowired
	private CommonDAO dao;
	@Override
	public Map<String, Object> getRecipeList() throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
        result.put(Constants.KEY_LIST, dao.selectList("recipe.recipeBoard.recipeList", params));
        System.out.println(result);
		return result;
	}
	// 모여라시킬 대분류 보여주려면
	@Override
	public Map<String, Object> getRecipeCard(RecipeVO recipeVO) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("RecipeVO", recipeVO);
		System.out.println("레시피카드 params: "+params);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put(Constants.KEY_LIST, dao.selectObject("recipe.recipeBoard.recipeCard", params));
		return result;
	}
	// 대분류 누르면 레시피카드 모여라 수행할메소드
	@Override
	public Map<String, Object> getRecipeMainCd() throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
//		params.put("RecipeVO", recipeVO);
		System.out.println("메인코드 params: "+params);
		Map<String, Object> result = new HashMap<String, Object>();
		 result.put(Constants.KEY_LIST, dao.selectList("recipe.recipeBoard.recipeMainCd", params));
		return result;
	}

	   //주간 랭킹 스케쥴러 교체할 것 - (cron = "0 0 0 * * *") 매일0시0분0초
    //주간 랭킹, 조회수 카운트
    @Scheduled(cron = "0/10 * 10 * * *") //테스트용 10초마다 실행
    @Override
    public Map<String, Object> getViewCnt() throws Exception {
        Map<String, Object> params = new HashMap<String, Object>();
        //params.put("RecipeVO", recipeVO);
        //System.out.println("getViewCnt params: "+params);
        Map<String, Object> result = new HashMap<String, Object>();
         result.put(Constants.KEY_LIST, dao.selectList("recipe.recipeBoard.viewCnt", params));
        return result;
    }
    //추천 랭킹, 좋아요 수 카운트
    @Scheduled(cron = "0/10 * 10 * * *")
    @Override
    public Map<String, Object> getLikeCnt() throws Exception {
        Map<String, Object> params = new HashMap<String, Object>();
        //params.put("RecipeVO", recipeVO);
        
        System.out.println("getViewCnt params: "+params);
        Map<String, Object> result = new HashMap<String, Object>();
         result.put(Constants.KEY_LIST, dao.selectList("recipe.recipeBoard.likeCnt", params));
        return result;
    }
	
	//상세조회
	@Override
	public Map<String, Object> getReadRecipe(int repNo) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("repNo", repNo);
		System.out.println("조회 params: "+params);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put(Constants.KEY_LIST, dao.selectList("recipe.recipeBoard.readRecipe", params));
		return result;
	}
	// 조회수 업데이트
    @Override
    public Map<String, Object> modifyRecipeViewCnt(int repNo) throws Exception {
       Map<String, Object> params = new HashMap<String, Object>();
       params.put("repNo", repNo);
       dao.update("recipe.recipeBoard.updateRecipeViewCnt", params);
       return params;
    }
    
	/* 등록 */
	@Override
	public Map<String, Object> addRecipe(RecipeVO recipeVO) throws Exception {
	    Map<String, Object> result = new HashMap<String, Object>();
        result.put(Constants.KEY_LIST, dao.selectList("recipe.recipeBoard.insertRecipe", recipeVO));
        System.out.println("등록 params : " + recipeVO);
        
        return result;
	}
	/* 수정 */
    @Override
    public Map<String, Object> updateRecipe(RecipeVO recipeVO) throws Exception {
        Map<String, Object> result = new HashMap<String, Object>();
        result = dao.selectMap("recipe.recipeBoard.updateRecipe", recipeVO);
        return result;
    }
    
    /* 삭제 */
    @Override
    public void deleteRecipe(int repNo) throws Exception {
    	Map<String, Object> params = new HashMap<String, Object>();
		params.put("repNo", repNo);
		dao.selectMap("recipe.recipeBoard.deleteRecipe", params	);
        System.out.println("삭제 params : int repNo ------>>>" + params);
    }
    
    // 검색
	@Override
	public Map<String, Object> getSearchList(Criteria cri) throws Exception {
		Map<String, Object> result=new HashMap<String, Object>();
		result.put(Constants.KEY_LIST, dao.selectList("recipe.recipeBoard.getSearch", cri));
		return result;
	}
}