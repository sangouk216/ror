package kr.ror.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kr.ror.common.ResultMap;
import kr.ror.common.exception.InvalidException;
import kr.ror.domain.JoinVO;
import kr.ror.service.JoinService;

@Controller
@RequestMapping(value = "/join")
public class JoinController {

	@Autowired
	private JoinService joinService;

	private static final Logger LOGGER = LoggerFactory.getLogger(MainController.class);

	@RequestMapping(value = "/joinInfo", method = RequestMethod.GET)
	public ModelAndView join() {
		return new ModelAndView("/joinInfo");
	}

	// 회원가입 
	@RequestMapping(value = "/userJoin", method = RequestMethod.POST)
	@ResponseBody
	public ResultMap userJoin(@ModelAttribute JoinVO joinVO) {
		ResultMap result = new ResultMap();

		try {
			joinService.userJoin(joinVO);
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

	// 아이디 중복체크
	@RequestMapping(value = "/idCheckJoin", method = RequestMethod.POST)
	@ResponseBody
	public ResultMap idCheckJoin(String custId) throws Exception {

		LOGGER.info("idCheckJoin");
		ResultMap result = new ResultMap();

		try {
			joinService.idCheckJoin(custId);
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
	
	   // 닉네임 중복체크
    @RequestMapping(value = "/nmCheckJoin", method = RequestMethod.POST)
    @ResponseBody
    public ResultMap NmCheckJoin(String custNm) throws Exception {

        LOGGER.info("nmCheckJoin");
        ResultMap result = new ResultMap();

        try {
            joinService.nmCheckJoin(custNm);
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
	

	@RequestMapping(value = "/pwFind", method = RequestMethod.GET)
	public ModelAndView pwFind() {
		return new ModelAndView("/pwFind");
	}

	@RequestMapping(value = "/idFind", method = RequestMethod.GET)
	public ModelAndView idFine() {
		return new ModelAndView("/idFind");
	}

	// 비밀번호 찾기 페이지에서 인증번호
	@RequestMapping(value = "/pwFind", method = RequestMethod.POST)
	@ResponseBody
	public ResultMap getCheckNum(@RequestParam(value = "custTel", required = true) String custTel,
			@RequestParam(value = "custId", required = true) String custId) throws Exception {

		ResultMap result = new ResultMap();

		try {
			result.put("data", joinService.getCheckNum(custTel, custId));
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

	// 비밀번호 찾기 페이지에서 임시 비밀번호로 변경
	@RequestMapping(value = "/imsiPwCh", method = RequestMethod.POST)
	@ResponseBody
	public ResultMap imsiPwCh(JoinVO joinVO) {
		ResultMap result = new ResultMap();

		try {
			joinService.imsiPwCh(joinVO);
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

	// 아이디 찾기 페이지에서 인증번호
	@RequestMapping(value = "/idFind", method = RequestMethod.POST)
	@ResponseBody
	public ResultMap getCheckNumId(@RequestParam(value = "custTel", required = true) String custTel) throws Exception {

		ResultMap result = new ResultMap();

		try {
			result.put("data", joinService.getCheckNumId(custTel));
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

	// 아이디 찾기 페이지에서 모달창에 아이디 띄어주기
	@RequestMapping(value = "/modalId", method = RequestMethod.POST)
	@ResponseBody
	public ResultMap getId(@RequestParam(value = "custTel", required = true) String custTel,
			@RequestParam(value = "custId", required = true) String custId) throws Exception {

		ResultMap result = new ResultMap();

		try {
			result.put("data", joinService.getId(custTel, custId));
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

}
