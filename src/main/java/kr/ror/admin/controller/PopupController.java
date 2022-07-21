package kr.ror.admin.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kr.ror.admin.domain.PopupVO;
import kr.ror.admin.service.PopupService;
import kr.ror.common.ResultMap;
import kr.ror.common.exception.InvalidException;
import kr.ror.service.MyPageService;

@Controller
@RequestMapping(value = "/admin")
public class PopupController {
    private static final Logger LOGGER = LoggerFactory.getLogger(PopupController.class);
    @Autowired
    private PopupService popupService;

    @Autowired
    private MyPageService myPageService;
// 화면 요청, 공지 보기 부분
    @RequestMapping(value = "/popup/popupMng", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView viewPopupMng() {
        ModelAndView mav = new ModelAndView("admin/popup/popupMng");

        try {
            System.out.println("popupMng");
            mav.addObject("NTC", popupService.getPopupSelect());
            mav.addObject("USERINFO", myPageService.getUserInfo());
//            mav.addObject("NTCList", popupService.getPopupSelectList());
//            mav.addObject("TEXT", popupService.getNTCList());
            /*
             * mav.addObject("AS_CALL_CNT", mainService.getMdexMainAsCtrSelect());
             */
        } catch (Exception e) {
            LOGGER.debug("ERROR", e);
            e.printStackTrace();
        }
        return mav;
    } //공지 보기 끝
	   
	// 화면 요청,
	    @RequestMapping(value = "/popup/popupMngCh", method = RequestMethod.POST)
	    public ModelAndView PopupMngCh() {
	        ModelAndView mav = new ModelAndView("admin/popup/popupMngCh");

	        try {
	            mav.addObject("NTC", popupService.getPopupSelect());
	            mav.addObject("USERINFO", myPageService.getUserInfo());
	        } catch (Exception e) {
	            LOGGER.debug("ERROR", e);
	            e.printStackTrace();
	        }
	        return mav;
	    }//공지 수정화면 끝
	    
	  //공지 작성 폼
	    @RequestMapping(value = "/popup/addPopupMngCh", method = RequestMethod.POST)
	    @ResponseBody
	    public ResultMap popupPost(@ModelAttribute PopupVO popupVO) {
	        ResultMap result = new ResultMap();

	        try {
	            popupService.PopupInsert(popupVO);
	            result.setSuccess();

	        } catch (InvalidException e) {
	            result.setInvalid(e.getMessage());
	        } catch (Exception e) {
	            LOGGER.debug("ERROR", e);
	            e.printStackTrace();
	            result.setFailure();
	        }

	        return result;
	    }
	    
	 // 화면 요청, 메인화면 공지 띄우기
        @RequestMapping(value = "/popup/popupShow")
        public ModelAndView viewPopupShow() {
            ModelAndView mav = new ModelAndView("admin/popup/popupShow");

            try {
                System.out.println("mngShow");
                mav.addObject("NTC", popupService.getPopupSelect());
                mav.addObject("USERINFO", myPageService.getUserInfo());
            } catch (Exception e) {
                LOGGER.debug("ERROR", e);
                e.printStackTrace();
            }
            return mav;
        }//메인화면 공지 끝
	   

        
      //js에서 받을때 요청
//           @RequestMapping(value = "/getPopupList")
//           @ResponseBody
//           public ResultMap getPopupList(
//                 @RequestParam(value = "custNm", required = false) String custNm
//              ) throws Exception {
//              
//              ResultMap result = new ResultMap();
//              
//              try {
//                 //검사항목
//                 result.putAll(popupService.getPopupList(custNm));
//                 result.setSuccess();
//              } catch (InvalidException e) {
//                 result.setInvalid(e.getMessage());
//               } catch (Exception e) {
//                 LOGGER.debug("ERROR", e);
//                 result.setFailure();
//              }
//              
//              System.out.println(result);
//              
//              return result;
//           }
}//end class
