package kr.ror.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ror.common.Constants;
import kr.ror.common.dao.CommonDAO;
import kr.ror.service.MateChoiceService;

@Service
public class MateChoiceServiceImpl implements MateChoiceService {
	
	@Autowired
	private CommonDAO dao;
	
    @Override
    public Map<String, Object> getMateChoiceSelect() throws Exception {
        Map<String, Object> params = new HashMap<String, Object>();

        Map<String, Object> result = new HashMap<String, Object>();
        result = dao.selectMap("material.mateChoice.mcSelectInfo", params);

        return result;
    }
    
    @Override
    public Map<String, Object> getMateSelectList() throws Exception {
        Map<String, Object> params = new HashMap<String, Object>();

        Map<String, Object> result = new HashMap<String, Object>();
        result.put(Constants.KEY_LIST, dao.selectList("material.mateChoice.mateSelectList", params));
        return result;
    }
    
    @Override
    public Map<String, Object> getMateChoiceSelectList() throws Exception {
        Map<String, Object> params = new HashMap<String, Object>();

        Map<String, Object> result = new HashMap<String, Object>();
        result.put(Constants.KEY_LIST, dao.selectList("material.mateChoice.mateChoiceSelectList", params));
        return result;
    }
    
    
    @Override
    public Map<String, Object> getMateChoiceList(String mateNm) throws Exception {
        Map<String, Object> params = new HashMap<String, Object>();
        
        params.put("mateNm", mateNm);
        
        Map<String, Object> result = new HashMap<String, Object>();
        result = dao.selectMap("common.mateChoice.selectMateChoiceInfo", params);
        
        return result;
    }
    // 대분류 카테고리 보이기.
    
    
    @Override
    public Map<String, Object> getMateMidList(String mateCCd) throws Exception {
        Map<String, Object> params = new HashMap<String, Object>();
        
        params.put("mateCCd", mateCCd);
        
        Map<String, Object> result = new HashMap<String, Object>();
       
        result.put(Constants.KEY_LIST, dao.selectList("material.mateChoice.selectMateMidInfo", params));
        return result;
    }
    // 중분류 카테고리 보이기.


	@Override
	public Map<String, Object> getMateNmList(String midCCd) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
        
        params.put("midCCd", midCCd);
        
        Map<String, Object> result = new HashMap<String, Object>();
        
        result.put(Constants.KEY_LIST, dao.selectList("material.mateChoice.selectMateNmInfo", params));
        return result;
	}
	// 소분류 카테고리 보이기.


    @Override
    public Map<String, Object> getfooterMateList() throws Exception {
        Map<String, Object> params = new HashMap<String, Object>();
        Map<String, Object> result = new HashMap<String, Object>();
       
        result.put(Constants.KEY_LIST, dao.selectList("material.mateChoice.footerMateList", params));
        return result;
    }


//	@Override
//	public Map<String, Object> getSearchMateList(String mateList) throws Exception {
//		Map<String, Object> params = new HashMap<String, Object>();
//
//        Map<String, Object> result = new HashMap<String, Object>();
//        result.put(Constants.KEY_LIST, dao.selectList("material.mateChoice.searchMateList", params));
//        return result;
//	}
    
    

}