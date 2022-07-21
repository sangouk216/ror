package kr.ror.board.service.impl;


import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ror.board.service.BoardService;
import kr.ror.common.Constants;
import kr.ror.common.dao.CommonDAO;
import kr.ror.domain.BoardVO;
import kr.ror.domain.Criteria;
import lombok.extern.log4j.Log4j;


@Service
@Log4j
public class BoardServiceImpl implements BoardService {
	@Autowired
	private CommonDAO dao;
	@Override
	public Map<String, Object> getList() throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
        result.put(Constants.KEY_LIST, dao.selectList("board.getList", params));
        System.out.println(result);
		return result;
	}
	//상세조회
	@Override
	public Map<String, Object> read(int pstNo) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("pstNo", pstNo);
		System.out.println("조회 params: "+params);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put(Constants.KEY_LIST, dao.selectList("board.read", params));
		return result;
	}
	// 조회수 업데이트
    @Override
    public Map<String, Object> viewCnt(int pstNo) throws Exception {
       Map<String, Object> params = new HashMap<String, Object>();
       params.put("pstNo", pstNo);
       dao.update("board.updateViewCnt", params);
       return params;
    }
    
	/* 등록 */
	@Override
	public Map<String, Object> create(BoardVO boardVO) throws Exception {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put(Constants.KEY_LIST, dao.selectList("board.addFree", boardVO));
		System.out.println("등록 params : " + boardVO);
		return result;
	}
	
	/* 수정 */
    @Override
    public Map<String, Object> update(BoardVO boardVO) throws Exception {
        Map<String, Object> result = new HashMap<String, Object>();
        result = dao.selectMap("board.update", boardVO);
        return result;
    }
    
    /* 삭제 */
    @Override
    public void delete(int pstNo) throws Exception {
    	Map<String, Object> params = new HashMap<String, Object>();
		params.put("pstNo", pstNo);
		dao.selectMap("board.delete", params);
        System.out.println("삭제 params : int pstNo ------>>>" + params);
    }
    // 검색
    @Override
    public Map<String, Object> getSearchList(Criteria cri) throws Exception {
        Map<String, Object> result=new HashMap<String, Object>();
        result.put(Constants.KEY_LIST, dao.selectList("board.getSearch", cri));
        return result;
    }
}
