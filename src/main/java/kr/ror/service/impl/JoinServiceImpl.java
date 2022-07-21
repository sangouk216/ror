package kr.ror.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import kr.ror.common.dao.CommonDAO;
import kr.ror.common.exception.InvalidException;
import kr.ror.domain.JoinVO;
import kr.ror.service.JoinService;

@Service
public class JoinServiceImpl implements JoinService {

	@Autowired
	private CommonDAO dao;

	@Autowired
	BCryptPasswordEncoder passwordEncoder;

	// 회원가입
	@Override
	public Map<String, Object> userJoin(JoinVO joinVO) throws Exception {
		// Map<String, Object> params = new HashMap<String, Object>();
		// params.put("custid", JoinService.getJoinSelect());

		// params.put("password", passwordEncoder.encode(joinVO.getCust_pw()));
		joinVO.setCustPw(passwordEncoder.encode(joinVO.getCustPw()));
		Map<String, Object> result = new HashMap<String, Object>();
		result = dao.selectMap("comm.join.userJoin", joinVO);

		return result;
	}

//	 아이디 중복체크
	@Override
	public Map<String, Object> idCheckJoin(String custId) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("custId", custId);

		Map<String, Object> result = new HashMap<String, Object>(); // 유효성검사에 사용할 맵

		result = dao.selectMap("comm.join.selectidCheckJoin", params); // 유효성검사에 사용할 쿼리
		if (!"0".equals(String.valueOf(result.get("ID_CNT")))) {
			throw new InvalidException("이미 가입된 아이디입니다.");
		} // 유효성검사 에 걸리면 아래로직 실행안하고 리턴

		return result;
	}

//  닉네임 중복체크
    @Override
    public Map<String, Object> nmCheckJoin(String custNm) throws Exception {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("custNm", custNm);

        Map<String, Object> result = new HashMap<String, Object>(); // 유효성검사에 사용할 맵

        result = dao.selectMap("comm.join.selectNmCheckJoin", params); // 유효성검사에 사용할 쿼리
        if (!"0".equals(String.valueOf(result.get("NM_CNT")))) {
            throw new InvalidException("이미 가입된 닉네임입니다.");
        } // 유효성검사 에 걸리면 아래로직 실행안하고 리턴

        return result;
    }
	
	// 비밀번호 찾기 페이지에서 인증번호
	@Override
	public Map<String, Object> getCheckNum(String custTel, String custId) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("custTel", custTel);
		params.put("custId", custId);

		Map<String, Object> result = new HashMap<String, Object>(); // 유효성검사에 사용할 맵
		result = dao.selectMap("comm.join.pwfiCheck", params); // 유효성검사에 사용할 쿼리
		if ("0".equals(String.valueOf(result.get("IDPHNUM_CNT")))) {
			throw new InvalidException("아이디가 없거나, 아이디와 핸드폰번호가 일치하지 않습니다.");
		} // 유효성검사 에 걸리면 아래로직 실행안하고 리턴

		int randomNum = (int) (Math.random() * 10000);
		result.put("checkNum", randomNum);
		System.out.println(result);

		return result;
	}

	// 비밀번호 찾기 페이지에서 비밀번호 변경
	@Override
	public Map<String, Object> imsiPwCh(JoinVO joinVO) throws Exception {
		joinVO.setCustPw(passwordEncoder.encode(joinVO.getCustPw()));
		Map<String, Object> result = new HashMap<String, Object>();
		result = dao.selectMap("comm.join.imsiPwCh", joinVO);

		return result;
	}

	// 아이디 찾기 페이지에서 인증번호
	@Override
	public Map<String, Object> getCheckNumId(String custTel) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("custTel", custTel);

		Map<String, Object> result = new HashMap<String, Object>(); // 유효성검사에 사용할 맵
		result = dao.selectMap("comm.join.idfiCheck", params); // 유효성검사에 사용할 쿼리
		if ("0".equals(String.valueOf(result.get("PHNUM_CNT")))) {
			throw new InvalidException("해당번호는 저희 회원에 가입되어있지 않습니다.");
		} // 유효성검사 에 걸리면 아래로직 실행안하고 리턴

		int randomNum = (int) (Math.random() * 10000);
		result.put("checkNum", randomNum);
		System.out.println(result);

		return result;
	}
	
//	아이디 찾기 페이지에서 아이디 모달창에 띄어주기
	@Override
	public Map<String, Object> getId(String custTel, String custId) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("custTel", custTel);
		params.put("custId", custId);

		Map<String, Object> result = new HashMap<String, Object>(); // 유효성검사에 사용할 맵

		result = dao.selectMap("comm.join.selectId", params); // 유효성검사에 사용할 쿼리
		return result;
	}

}