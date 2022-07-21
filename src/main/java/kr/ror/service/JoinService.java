package kr.ror.service;

import java.util.Map;

import kr.ror.domain.JoinVO;

public interface JoinService {

	public Map<String, Object> userJoin(JoinVO joinVO) throws Exception;//회원가입

	public Map<String, Object> idCheckJoin(String custId) throws Exception;//아이디 중복확인

	public Map<String, Object> nmCheckJoin(String custNm) throws Exception;//아이디 중복확인
	
	public Map<String, Object> getCheckNum(String custTel, String custId) throws Exception;//비밀번호 찾기 페이지에서 인증번호

	public Map<String, Object> imsiPwCh(JoinVO joinVO) throws Exception;//비밀번호 찾기 페이지에서 비밀번호 변경
	
	public Map<String, Object> getCheckNumId(String custTel) throws Exception;//아이디 찾기 페이지에서 인증번호
	
	public Map<String, Object> getId(String custTel, String custId) throws Exception;//아이디 찾기 페이지 아이디 뿌려주기
	
	

}
