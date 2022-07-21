package kr.ror.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kr.ror.admin.controller.PopupController;
import kr.ror.board.service.BoardService;
import kr.ror.common.ResultMap;
import kr.ror.common.exception.InvalidException;
import kr.ror.domain.BoardVO;
import kr.ror.domain.Criteria;
import kr.ror.service.MyPageService;

@Controller
@RequestMapping(value = "/board")
public class BoardController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PopupController.class);
    @Autowired
    private BoardService boardService;
    @Autowired
    private MyPageService myPageService;
    
    // 리스트
	@RequestMapping(value = "/freeBoard")
	public ModelAndView freeBoard(Criteria cri) {
		ModelAndView mav = new ModelAndView("/board/freeBoard");
		try {
			mav.addObject("FRBD", boardService.getList());
			mav.addObject("USERINFO", myPageService.getUserInfo());
		} catch (Exception e) {
			LOGGER.debug("ERROR", e);
			e.printStackTrace();
		}
		return mav;
	}
	// 검색
	@RequestMapping(value="/boardSearch", method = RequestMethod.POST)
	@ResponseBody
	public ResultMap getBoardSearch(Criteria cri) throws Exception {
		ResultMap result = new ResultMap();
		try {
			result.put("BDSC", boardService.getSearchList(cri));
			System.out.println(result);
			result.setSuccess();
			System.out.println(result);
		} catch (Exception e) {
			LOGGER.debug("ERROR", e);
			e.printStackTrace();
		}
		return result;
	}
	
	// 글 상세보기
	@RequestMapping(value = "/freeBoardInfo")
	public ModelAndView freeBoardInfo(@RequestParam(value = "pstNo", required = false) int pstNo) throws Exception {
		ModelAndView mav = new ModelAndView("/board/freeBoardInfo");
		try {
	         mav.addObject("FRIN", boardService.read(pstNo));
	         boardService.viewCnt(pstNo);
	         mav.addObject("USERINFO", myPageService.getUserInfo());
	      } catch (Exception e) {
	    	 LOGGER.debug("ERROR", e);
	         e.printStackTrace();
	      }
		return mav;
	}
	// 글작성페이지 불러오기
	@RequestMapping(value = "/freeBoardUse")
	public ModelAndView freeBoardUse() throws Exception {
		ModelAndView mav = new ModelAndView("/board/freeBoardUse");
		try {
			System.out.println("글작성페이지 불러오기");
			mav.addObject("USERINFO", myPageService.getUserInfo());
		} catch (Exception e) {
			LOGGER.debug("ERROR", e);
			e.printStackTrace();
		}
		return mav;
	}
	//글작성동작
	@RequestMapping(value = "/insert", method =RequestMethod.POST)
	@ResponseBody
	public ResultMap addFreeBoardUse(BoardVO boardVO) throws Exception {
		ResultMap result = new ResultMap();
		try {
			//검사항목
			boardService.create(boardVO);
			System.out.println("등록 컨트롤러"+boardVO);
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
	@RequestMapping(value="/freeBoardModi")
	public ModelAndView updateFreeBoard(BoardVO boardVO, int pstNo) throws Exception {
	ModelAndView mav = new ModelAndView("/board/freeBoardModi");
		try {
			System.out.println("글수정페이지 불러오기");
			mav.addObject("FRIN", boardService.read(pstNo));
			mav.addObject("USERINFO", myPageService.getUserInfo());
		} catch (Exception e) {
			LOGGER.debug("ERROR", e);
			e.printStackTrace();
		}
		return mav;
	}
	// 정보 수정, modify
	@ResponseBody
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public ResultMap modifyFreeBoard(BoardVO boardVO) throws Exception {
	ResultMap result = new ResultMap();
		try {
			boardService.update(boardVO);
			result.setSuccess();
		} catch (InvalidException e) {
			LOGGER.debug("ERROR", e);
			e.printStackTrace();
			result.setFailure();
		}
		return result;
	}
	//삭제
	@RequestMapping(value="/delete", method = RequestMethod.POST)
	@ResponseBody
	public ResultMap deleteFreeBoard(int pstNo) throws Exception {
		ResultMap result = new ResultMap();
		try {
			boardService.delete(pstNo);
			System.out.println("삭제 컨트롤러 pstNo : "+pstNo);
			result.setSuccess();
		} catch (InvalidException e) {
			LOGGER.debug("ERROR", e);
			e.printStackTrace();
			result.setFailure();
		}
		return result;
	}
}