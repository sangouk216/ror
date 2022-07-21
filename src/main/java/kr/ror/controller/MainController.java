package kr.ror.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import kr.ror.admin.service.PopupService;
import kr.ror.board.service.RecipeService;
import kr.ror.common.util.CookieUtil;
import kr.ror.service.MateChoiceService;
import kr.ror.service.MyPageService;

@Controller
@RequestMapping(value = "/")
public class MainController {
    
    @Autowired
    private PopupService popupService;

    @Autowired
    private RecipeService recipeService;
    
    @Autowired
    private MyPageService myPageService;
    
    @Autowired
    private MateChoiceService mateChoiceService;
    
    private static final Logger LOGGER = LoggerFactory.getLogger(MainController.class);
    
	@RequestMapping(value = "/main", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView main(HttpServletResponse response,HttpServletRequest request) {
	    ModelAndView mav = new ModelAndView("/main");
	    String rememberId = request.getParameter("rememberId");
//	    response.addCookie(new Cookie("rememberId", rememberId));
	    CookieUtil.setCookie(request, response, "rememberId", rememberId);
	    LOGGER.info("rememberId: "+rememberId);
		try {
            System.out.println("mainController");
            //공지사항
            mav.addObject("NTC", popupService.getPopupSelect());
            //
            mav.addObject("CARD", recipeService.getRecipeMainCd());
            //메인페이지 급상승 검색어
            mav.addObject("RECP", recipeService.getRecipeList());
            //메인페이지 추천 랭킹
            mav.addObject("LCNT", recipeService.getLikeCnt());
            //메인페이지 주간 레시피 랭킹
            mav.addObject("VCNT", recipeService.getViewCnt());
            //메인페이지 재료 랭킹
            mav.addObject("MATERANK", mateChoiceService.getMateSelectList());
            //메인페이지 Footer 대분류
            mav.addObject("MATE", mateChoiceService.getMateChoiceSelectList());
            //footer 재료 읽어오기
            mav.addObject("FOOTERLIST", mateChoiceService.getfooterMateList());
            //마이페이지 정보 읽어오기
            mav.addObject("USERINFO", myPageService.getUserInfo());
            
        } catch (Exception e) {
            LOGGER.debug("ERROR", e);
            e.printStackTrace();
        }
        return mav;
	}
	
	@RequestMapping(value = "/ror/login", method = RequestMethod.GET)
	public ModelAndView login() {
		return new ModelAndView("login");
	}

	
}