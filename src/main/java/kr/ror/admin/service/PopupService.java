package kr.ror.admin.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import kr.ror.admin.domain.PopupVO;

@Service
public interface PopupService {

//    public void registerNTC(PopupVO popupboard); // 등록
//    public PopupVO getNTC(Long ntc_no); // 읽기
//    public String getNTC();
//    public boolean modifyNTC(PopupVO popupboard); // 수정

//    public boolean removeNTC(Long ntc_no); // 삭제

    public Map<String, Object> getPopupSelect() throws Exception;
    
    public Map<String, Object> getPopupSelectList() throws Exception;
    
    public Map<String, Object> PopupInsert(PopupVO popupVo) throws Exception;
    
}