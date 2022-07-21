package kr.ror.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kr.ror.common.ResultMap;
import kr.ror.common.exception.InvalidException;
import kr.ror.service.MateChoiceService;

@Controller
@RequestMapping(value = "/ror/material")
public class MateChoiceController {
	private static final Logger LOGGER = LoggerFactory.getLogger(MateChoiceController.class);
	@Autowired
	private MateChoiceService mateChoiceService;
	 
	 	// 대분류 보기.
		@RequestMapping(value = "/mateChoice")
		public ModelAndView viewMateChoice() {
			ModelAndView mav = new ModelAndView("material/mateChoice");
			
			try {
				System.out.println("mateChoiceView");
				mav.addObject("MATEA",mateChoiceService.getMateChoiceSelect());
				mav.addObject("MATE",mateChoiceService.getMateChoiceSelectList());
			} catch (Exception e) {
				LOGGER.debug("ERROR",e);
				e.printStackTrace();
			}		
			return mav;
		}
		
		//js에서 받을때 요청(중분류 보기)
		   @RequestMapping(value = "/getMateMidList")
		   @ResponseBody
		   public ResultMap getMateChoiceList(
		         @RequestParam(value = "mateCCd", required = false) String mateCCd
		      ) throws Exception {
		      
		      ResultMap result = new ResultMap();
		      
		      try {
		         //검사항목
		         result.putAll(mateChoiceService.getMateMidList(mateCCd));
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
		   
		   // 소분류 보기
		   @RequestMapping(value = "/getMateNmList")
		   @ResponseBody
		   public ResultMap getMateMidList(
		         @RequestParam(value = "midCCd", required = false) String midCCd
		      ) throws Exception {
		      
		      ResultMap result = new ResultMap();
		      
		      try {
		         //검사항목
		         result.putAll(mateChoiceService.getMateNmList(midCCd));
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

		   
		   
//			@RequestMapping(value = "/mateChoice")
//			@ResponseBody
//			   public ResultMap getMateNmList(
//			         @RequestParam(value = "mateList", required = false) String mateList
//			      ) throws Exception {
//			      
//			      ResultMap result = new ResultMap();
//				
//				try {
//					System.out.println("mateChoiceView");
//					result.putAll(mateChoiceService.getSearchMateList(mateList));
//					result.setSuccess();
//				} catch (InvalidException e) {
//			         result.setInvalid(e.getMessage());
//				} catch (Exception e) {
//					LOGGER.debug("ERROR",e);
//					e.printStackTrace();
//				}		
//				return result;
//			}
		   
//		// 화면 요청, 메인화면 공지 띄우기
//	        @RequestMapping(value = "/material/mateChoice")
//	        public ModelAndView viewMateChoiceShow() {
//	            ModelAndView mav = new ModelAndView("material/mateChoice");
//
//	            try {
//	                System.out.println("mngShow");
//	                mav.addObject("MATE", mateChoiceService.getMateChoiceSelect());
//	            } catch (Exception e) {
//	                LOGGER.debug("ERROR", e);
//	                e.printStackTrace();
//	            }
//	            return mav;
//	        }//메인화면 공지 끝
		   
		
//		@RequestMapping(value = "/mateChoice", method = RequestMethod.GET)
//		public ModelAndView mateChoice() {
//			return new ModelAndView("/mateChoice");
//		}

}
