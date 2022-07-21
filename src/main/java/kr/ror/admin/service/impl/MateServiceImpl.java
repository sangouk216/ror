package kr.ror.admin.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ror.admin.service.MateService;
import kr.ror.common.Constants;
import kr.ror.common.dao.CommonDAO;

@Service
public class MateServiceImpl implements MateService {
	
	@Autowired
	private CommonDAO dao;
	
//	@Autowired
//	private AuthService authService;
	
	@Override
	public Map<String, Object> getMateSelect() throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
//		params.put("loginUserCd", authService.getLoginUserCd());
		Map<String, Object> result = new HashMap<String, Object>();
		result.put(Constants.KEY_LIST, dao.selectList("admin.mate.mateList", params));
		
		return result;
	}
	
	@Override
	public Map<String, Object> getMateList(String custNm) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		
		params.put("custNm", custNm);
		//params.put("password", passwordEncoder.encode(bdt)); 
		
		Map<String, Object> result = new HashMap<String, Object>();
		result = dao.selectMap("admin.mate.selectMateInfo", params);
		
		return result;
	}
	
	// 재료관리자 삭제
    @Override
    public Map<String, Object> deleteMateMng(String matNm) throws Exception {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("matNm", matNm);
        Map<String, Object> result = new HashMap<String, Object>();
        result = dao.selectMap("admin.mate.mateMngDelete", params);

        return result;
    }
    
 // 재료관리자 추가
    @Override
    public Map<String, Object>  plusMateMng(String matNm, String mainCd) throws Exception {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("matNm", matNm);
        params.put("mainCd", mainCd);

        Map<String, Object> result = new HashMap<String, Object>();
        result = dao.selectMap("admin.mate.mateMngPlus", params);

        return result;
    }
    // 재료관리자 수정
//    @Override
//        public Map<String, Object> changeMateMng(MateChoiceVO mateChoiceVO) throws Exception {
//            Map<String, Object> result = new HashMap<String, Object>();
//            result = dao.selectMap("admin.mate.mateMngChange", mateChoiceVO);
//            return result;
//        }
}
