package kr.ror.service;

import java.util.Map;

import kr.ror.domain.JoinVO;


public interface MyPageService {

    public Map<String, Object> getUserInfo() throws Exception; //유저 정보 읽기
    
    public Map<String, Object> UpdateUser(JoinVO joinVO) throws Exception;// 유저 정보 수정
    
}
