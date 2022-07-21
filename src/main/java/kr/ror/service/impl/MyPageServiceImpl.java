package kr.ror.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import kr.ror.common.Constants;
import kr.ror.common.dao.CommonDAO;
import kr.ror.domain.JoinVO;
import kr.ror.service.MyPageService;

@Service
public class MyPageServiceImpl implements MyPageService {

    @Autowired
    private CommonDAO dao;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    public Map<String, Object> getUserInfo() throws Exception {
        Map<String, Object> params = new HashMap<String, Object>();
        //로그인처리 되면 등록자 수정자 처리를 위함
        //params.put("loginUserCd", authService.getLoginUserCd());
        System.out.println("userInfo params: "+params);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put(Constants.KEY_LIST, dao.selectList("comm.user.userInfo", params));
        
        return result;
    }

    @Override
    public Map<String, Object> UpdateUser(JoinVO joinVO) throws Exception {
        joinVO.setCustPw(passwordEncoder.encode(joinVO.getCustPw()));
        Map<String, Object> result = new HashMap<String, Object>();
        result = dao.selectMap("comm.user.UpdateUser", joinVO);
        return result;
    }
    
}
