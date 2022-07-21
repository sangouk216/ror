package kr.ror.common.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.dao.support.DaoSupport;
import org.springframework.util.Assert;


public class CommonDAO extends DaoSupport {
	
	private SqlSession sqlSession;

    private boolean externalSqlSession;

    public final void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        if (!this.externalSqlSession) {
            this.sqlSession = new SqlSessionTemplate(sqlSessionFactory);
        }
    }

    public final void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        this.sqlSession = sqlSessionTemplate;
        this.externalSqlSession = true;
    }

    public final SqlSession getSqlSession() {
        return this.sqlSession;
    }

    protected void checkDaoConfig() {
        Assert.notNull(this.sqlSession, "Property 'sqlSessionFactory' or 'sqlSessionTemplate' are required");
    }
    
    
    public Object selectObject(String mapperId) throws Exception {
		return this.sqlSession.selectOne(mapperId);
	}
	
	public Object selectObject(String mapperId, Object parameter) throws Exception {
		return this.sqlSession.selectOne(mapperId, parameter);
	}

	
	@SuppressWarnings("unchecked")
	public Map<String, Object> selectMap(String mapperId) throws Exception {
		return (Map<String, Object>)this.sqlSession.selectOne(mapperId);
	}
	
	@SuppressWarnings("unchecked")
	public Map<String, Object> selectMap(String mapperId, Object parameter) throws Exception {
		return (Map<String, Object>)this.sqlSession.selectOne(mapperId, parameter);
	}
	
	
	@SuppressWarnings("rawtypes")
	public List selectList(String mapperId) throws Exception {
		return this.sqlSession.selectList(mapperId);
	}
	
	@SuppressWarnings("rawtypes")
	public List selectList(String mapperId, Object parameter) throws Exception {
		return this.sqlSession.selectList(mapperId, parameter);
	}
	
	public Object selectInfo(String statement, Object parameter) throws Exception{
		return (this.sqlSession.selectList(statement, parameter)).get(0);
	}
	
	public Object selectInfo(String statement) throws Exception{
		return (this.sqlSession.selectList(statement)).get(0);
	}
	
	public Object selectOne(String statement, Object parameter) throws Exception{
		return this.sqlSession.selectOne(statement, parameter);
	}
	
	public Object selectOne(String statement) throws Exception{
		return this.sqlSession.selectOne(statement);
	}
	
	
	
	public int insert(String mapperId) throws Exception {
		return this.sqlSession.insert(mapperId);
	}
	
	public int insert(String mapperId, Object parameter) throws Exception {
		return this.sqlSession.insert(mapperId, parameter);
	}
	
	
	
	public int update(String mapperId) throws Exception {
		return this.sqlSession.update(mapperId);
	}
	
	public int update(String mapperId, Object parameter) throws Exception {
		return this.sqlSession.update(mapperId, parameter);
	}

	public int delete(String mapperId) throws Exception {
		return this.sqlSession.delete(mapperId);
	}
	
	public int delete(String mapperId, Object parameter) throws Exception {
		return this.sqlSession.delete(mapperId, parameter);
	}
	
}
