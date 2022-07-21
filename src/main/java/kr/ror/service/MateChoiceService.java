package kr.ror.service;

import java.util.Map;

public interface MateChoiceService {
	//public mateChoiceVO get(Long MAT_NO);
	
	public Map<String, Object> getMateChoiceSelect() throws Exception;
    
	//
	public Map<String, Object> getMateSelectList() throws Exception;

	public Map<String, Object> getMateChoiceSelectList() throws Exception;
    // 대분류 카테고리.
    public Map<String, Object> getMateChoiceList(String mateNm) throws Exception;  
    // 중분류 카테고리.
    public Map<String, Object> getMateMidList(String mateCCd) throws Exception;
    // 소분류 카테고리.
    public Map<String, Object> getMateNmList(String midCCd) throws Exception;

    //
    public Map<String, Object> getfooterMateList() throws Exception;
    
//    public Map<String, Object> getSearchMateList(String mateList) throws Exception;
    
    // 어떤 수식을 쓸 거라고 정해놓는 것.
}
