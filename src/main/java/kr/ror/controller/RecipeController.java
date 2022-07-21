package kr.ror.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kr.ror.board.service.RecipeService;
import kr.ror.common.ResultMap;
import kr.ror.common.exception.InvalidException;
import kr.ror.domain.Criteria;
import kr.ror.domain.RecipeVO;
import kr.ror.service.MyPageService;

@Controller
@RequestMapping(value = "/recipe")
public class RecipeController {
	private static final Logger LOGGER = LoggerFactory.getLogger(RecipeController.class);
	@Autowired
	private RecipeService recipeService;
	// 화면 요청 레시피 get? 전체조회 == list
	@Autowired
    private MyPageService myPageService;
	
	@RequestMapping(value = "/recipeSearch")
	public ModelAndView viewRecipeList(Criteria cri) throws Exception {
		ModelAndView mav = new ModelAndView("/recipe/recipeSearch");
		try {
			mav.addObject("RECP", recipeService.getRecipeList());
			mav.addObject("CARD", recipeService.getRecipeMainCd());
			mav.addObject("USERINFO", myPageService.getUserInfo());
		} catch (Exception e) {
			LOGGER.debug("ERROR", e);
			e.printStackTrace();
		}
		return mav;
	}
	
		@RequestMapping(value="/recipeSearch2", method = RequestMethod.POST)
		@ResponseBody
		public ResultMap recipeSearch2(Criteria cri) throws Exception {
			ResultMap result = new ResultMap();
			try {
				result.put("PICK", recipeService.getSearchList(cri));
				result.setSuccess();
			} catch (Exception e) {
				LOGGER.debug("ERROR", e);
				e.printStackTrace();
			}
			return result;
		}
		
	// 메인코드 해당 레시피카드만 조회되게하는 메소드 레시피분류를 눌렀을때 동작하게 요청
	@RequestMapping(value="/getRecipeMainCd", method = RequestMethod.POST)
	public ModelAndView viewRecipeCard(RecipeVO recipeVO) {
		ModelAndView mav = new ModelAndView("/recipe/recipeSearch");
		try {
			mav.addObject("CARD",recipeService.getRecipeCard(recipeVO));
			mav.addObject("USERINFO", myPageService.getUserInfo());
		} catch (Exception e) {
			LOGGER.debug("ERROR", e);
	        e.printStackTrace();
		}
		return mav;
	}
   // 상세보기 get
	   @RequestMapping(value="/recipeinfo")
	   public ModelAndView getRecipeInfo(@RequestParam(value = "repNo", required = false) int repNo) throws Exception {
	      ModelAndView mav = new ModelAndView("/recipe/recipeinfo");
	      try {
	         mav.addObject("INFO", recipeService.getReadRecipe(repNo));
	         recipeService.modifyRecipeViewCnt(repNo);
	         mav.addObject("USERINFO", myPageService.getUserInfo());
	      } catch (Exception e) {
	         LOGGER.debug("ERROR", e);
	         e.printStackTrace();
	      }
	      return mav;
	 }
	// 레시피 글작성페이지 불러오기 동작
	@RequestMapping(value="/recipeAdd")
	public ModelAndView addRecipe() throws Exception {
		ModelAndView mav = new ModelAndView("/recipe/recipeAdd");
		try {
			System.out.println("글작성페이지 불러오기");
			mav.addObject("USERINFO", myPageService.getUserInfo());
		} catch (Exception e) {
			LOGGER.debug("ERROR", e);
			e.printStackTrace();
		}
		return mav;
	}
	//	레시피 등록하기
	@RequestMapping(value = "/recipeBoard/insertRecipe", method =RequestMethod.POST)
	@ResponseBody
	public ResultMap addRecipeInfo(RecipeVO recipeVO) throws Exception {
		ResultMap result = new ResultMap();
		try {
			//검사항목
			recipeService.addRecipe(recipeVO);
			System.out.println("등록 컨트롤러"+recipeVO);
			result.setSuccess();
		} catch (InvalidException e) {
			result.setInvalid(e.getMessage());
		} catch (Exception e) {
			LOGGER.debug("ERROR", e);
			result.setFailure();
		}
		System.out.println(result);
		return result;
	}
	// 수정 modify update 작성페이지 불러오기
	@RequestMapping(value="/recipeUpdate")
	public ModelAndView updateRecipeInfo(Map<String, Object> map, int repNo) throws Exception {
		ModelAndView mav = new ModelAndView("/recipe/recipeUpdate");
		try {
			System.out.println("글수정페이지 불러오기");
			mav.addObject("LOAD", recipeService.getReadRecipe(repNo));
			mav.addObject("USERINFO", myPageService.getUserInfo());
		} catch (Exception e) {
			LOGGER.debug("ERROR", e);
			e.printStackTrace();
		}
		return mav;
	}
	// 정보 수정, modify
	@ResponseBody
	@RequestMapping(value = "/modifyRecipe2", method = RequestMethod.POST)
	public ResultMap modifyRecipe(RecipeVO recipeVO) throws Exception {
		ResultMap result = new ResultMap();
		try {
			recipeService.updateRecipe(recipeVO);
			result.setSuccess();
		} catch (InvalidException e) {
			LOGGER.debug("ERROR", e);
			e.printStackTrace();
			result.setFailure();
		}
		return result;
	}
	//삭제
	@RequestMapping(value="/deleteRecipe", method = RequestMethod.POST)
	@ResponseBody
	public ResultMap deleteRecipeInfo(int repNo) throws Exception {
		ResultMap result = new ResultMap();
		try {
			recipeService.deleteRecipe(repNo);
			System.out.println("삭제 컨트롤러 repNo : "+repNo);
			result.setSuccess();
		} catch (InvalidException e) {
			LOGGER.debug("ERROR", e);
			e.printStackTrace();
			result.setFailure();
		}
		return result;
	}
}
