package kr.ror.admin.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ror.admin.service.UserCheckService;
import kr.ror.common.Constants;
import kr.ror.common.dao.CommonDAO;

@Service
public class UserCheckServiceImpl implements UserCheckService {

    @Autowired
    private CommonDAO dao;
    
    @Override
    public Map<String, Object> getUserInfoList() throws Exception {
        Map<String, Object> params = new HashMap<String, Object>();
        Map<String, Object> result = new HashMap<String, Object>();
        result.put(Constants.KEY_LIST, dao.selectList("admin.userCheck.UserInfoList", params));
        
        return result;
    }

}
