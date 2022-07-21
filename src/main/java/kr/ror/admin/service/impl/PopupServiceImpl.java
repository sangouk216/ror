package kr.ror.admin.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ror.admin.domain.PopupVO;
import kr.ror.admin.service.PopupService;
import kr.ror.common.Constants;
import kr.ror.common.dao.CommonDAO;

@Service
public class PopupServiceImpl implements PopupService {

    @Autowired
    private CommonDAO dao;
    
    @Override
    public Map<String, Object> getPopupSelect() throws Exception {
        Map<String, Object> params = new HashMap<String, Object>();
            //로그인처리 되면 등록자 수정자 처리를 위함
     //   params.put("loginUserCd", authService.getLoginUserCd());
//        params.put("ntc_ctnt", popupService.NTCget());

        Map<String, Object> result = new HashMap<String, Object>();
        result = dao.selectMap("admin.popup.selectInfo", params);

        return result;
    }
    
    
    @Override
    public Map<String, Object> getPopupSelectList() throws Exception {
        Map<String, Object> params = new HashMap<String, Object>();
            //로그인처리 되면 등록자 수정자 처리를 위함
     //   params.put("loginUserCd", authService.getLoginUserCd());
//        params.put("ntc_ctnt", popupService.NTCget());

        Map<String, Object> result = new HashMap<String, Object>();
      //  result = dao.selectMap("admin.popup.popupSelectInfo", params);
        result.put(Constants.KEY_LIST, dao.selectList("admin.popup.popupSelectList", params));
        return result;
    }
    
    
    @Override
    public Map<String, Object> PopupInsert(PopupVO popupVO) throws Exception {
        
        Map<String, Object> result = new HashMap<String, Object>();
        dao.insert("admin.popup.popupInsert", popupVO);
        
        return result;
    }

}
