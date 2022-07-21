package kr.ror.admin.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kr.ror.common.ResultMap;
import kr.ror.common.exception.InvalidException;
import kr.ror.domain.MateChoiceVO;
import kr.ror.service.MateChoiceService;
import kr.ror.service.MyPageService;
import kr.ror.admin.service.MateService;

@Controller
@RequestMapping(value = "/admin")
public class MateController {
    private static final Logger LOGGER = LoggerFactory.getLogger(MateController.class);
    @Autowired
    private MateService mateService;

    @Autowired
    private MateChoiceService mateChoiceService;

    @Autowired
    private MyPageService myPageService;

// 화면 요청
    @RequestMapping(value = "/mate/mateMng", method = { RequestMethod.GET, RequestMethod.POST })
    public ModelAndView viewMateMng() {
        ModelAndView mav = new ModelAndView("admin/mate/mateMng");

        try {
            System.out.println("mateController");
            // footer 재료 읽어오기
            mav.addObject("MATE", mateChoiceService.getMateChoiceSelectList());
            mav.addObject("FOOTERLIST", mateChoiceService.getfooterMateList());
            mav.addObject("USERINFO", myPageService.getUserInfo());
        } catch (Exception e) {
            LOGGER.debug("ERROR", e);
            e.printStackTrace();
        }
        return mav;
    }
//js에서 받을때 요청
    @RequestMapping(value = "/getMateList")
    @ResponseBody
    public ResultMap getMateList(@RequestParam(value = "custNm", required = false) String custNm) throws Exception {

        ResultMap result = new ResultMap();

        try {
            // 검사항목
            result.putAll(mateService.getMateList(custNm));
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
    // 삭제
    @RequestMapping(value = "/deleteMateMng", method = RequestMethod.POST)
    @ResponseBody
    public ResultMap deleteMateMng(String matNm) throws Exception {
        ResultMap result = new ResultMap();
        try {
            mateService.deleteMateMng(matNm);
            result.setSuccess();
        } catch (InvalidException e) {
            LOGGER.debug("ERROR", e);
            e.printStackTrace();
            result.setFailure();
        }
        return result;
    }
    
 // 수정
//    @RequestMapping(value = "/changeMateMng", method = RequestMethod.POST)
//    @ResponseBody
//    public ResultMap changeMateMng(MateChoiceVO mateChoiceVO) throws Exception {
//        ResultMap result = new ResultMap();
//        try {
//            mateService.changeMateMng(mateChoiceVO);
//            result.setSuccess();
//        } catch (InvalidException e) {
//            LOGGER.debug("ERROR", e);
//            e.printStackTrace();
//            result.setFailure();
//        }
//        return result;
//    }

    //추가
    @RequestMapping(value = "/plusMateMng", method = RequestMethod.POST)
    @ResponseBody
    public ResultMap plusMateMng(
            @RequestParam(value = "matNm", required = true) String matNm
            ,  @RequestParam(value = "mainCd", required = true) String mainCd
            ) throws Exception {
        ResultMap result = new ResultMap();
        try {
            mateService.plusMateMng(matNm, mainCd);
            result.setSuccess();
        } catch (InvalidException e) {
            result.setInvalid(e.getMessage());
        } catch (Exception e) {
            LOGGER.debug("ERROR", e);
            result.setFailure();
        }
        return result;
    }
    
}
