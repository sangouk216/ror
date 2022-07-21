package kr.ror.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kr.ror.common.ResultMap;
import kr.ror.common.exception.InvalidException;
import kr.ror.domain.JoinVO;
import kr.ror.service.MyPageService;

@Controller
@RequestMapping(value = "/ror")
public class MyPageController {
	private static final Logger LOGGER = LoggerFactory.getLogger(MyPageController.class);

	@Autowired
	private MyPageService myPageService;
	
    @RequestMapping(value = "/myPage", method = RequestMethod.POST)
    public ModelAndView viewMypage() throws Exception {
        ModelAndView mav = new ModelAndView("myPage");
        try {
            mav.addObject("USERINFO", myPageService.getUserInfo());
            System.out.println("mypage왜 돼?"+mav);
        } catch (Exception e) {
            LOGGER.debug("ERROR", e);
            e.printStackTrace();
        }
        return mav;
    }

	// 정보 수정, modify
    @ResponseBody
	@RequestMapping(value = "/modifyUser", method = RequestMethod.POST)
	public ResultMap modifyUser(JoinVO joinVO) throws Exception {
	    ResultMap result = new ResultMap();
		try {
		    myPageService.UpdateUser(joinVO);
		    result.setSuccess();
		} catch (InvalidException e) {
			LOGGER.debug("ERROR", e);
			e.printStackTrace();
			result.setFailure();
		}
		return result;
	}
}
    
//js에서 받을때 요청
//	   @RequestMapping(value = "/getRecipeList")
//	   @ResponseBody
//	   public ResultMap recipeInfo(
//	         @RequestParam(value = "repNo", required = false) int repNo
//	      ) throws Exception {
//	      
//	      ResultMap result = new ResultMap();
//	      
//	      try {
//	         //검사항목
//	         result.putAll(recipeService.getRecipeCard());
//	         result.setSuccess();
//	      } catch (InvalidException e) {
//	         result.setInvalid(e.getMessage());
//	       } catch (Exception e) {
//	         LOGGER.debug("ERROR", e);
//	         result.setFailure();
//	      }
//	      
//	      System.out.println(result);
//	      
//	      return result;
//	   }
