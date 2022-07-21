package kr.ror.admin.service;

import java.util.Map;

public interface MateService {

	public Map<String, Object> getMateSelect() throws Exception;
	
	public Map<String, Object> getMateList(String custNm) throws Exception;
	
	//재료관리자 삭제
	public Map<String, Object> deleteMateMng(String matNm) throws Exception;
	
	//재료관리자 추가
    public Map<String, Object> plusMateMng(String matNm, String mainCd) throws Exception;
}
