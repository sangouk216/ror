package kr.ror.common.service.security;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

public class SecuredResourcePatternServiceImpl implements SecuredResourcePatternService {

	private SecuredResourcePatternDAO securedResourcePatternDAO;
	
	public SecuredResourcePatternDAO getSecuredResourcePatternDAO() {
		return securedResourcePatternDAO;
	}

	public void setSecuredResourcePatternDAO(
			SecuredResourcePatternDAO securedResourcePatternDao) {
		this.securedResourcePatternDAO = securedResourcePatternDao;
	}

	@Override
	public LinkedHashMap<RequestMatcher, List<ConfigAttribute>> getRolesAndUrl() throws Exception {
		LinkedHashMap<RequestMatcher, List<ConfigAttribute>> ret = new LinkedHashMap<RequestMatcher, List<ConfigAttribute>>();
		LinkedHashMap<Object, List<ConfigAttribute>> data = securedResourcePatternDAO.getRolesAndUrl();
		Set<Object> keys = data.keySet();
		
		for(Object key : keys) {
			ret.put((AntPathRequestMatcher)key, data.get(key));
		}
		
		return ret;
	}
	

//	@Override
//	public LinkedHashMap<String, List<ConfigAttribute>> getRolesAndMethod() throws Exception {
//		LinkedHashMap<String, List<ConfigAttribute>> ret = new LinkedHashMap<String, List<ConfigAttribute>>();
//		LinkedHashMap<Object, List<ConfigAttribute>> data = securedResourcePatternDao.getRolesAndMethod();
//		Set<Object> keys = data.keySet();
//		
//		for(Object key : keys) {
//			ret.put((String)key, data.get(key));
//		}
//		
//		return ret;
//	}	
//	
//	
//	@Override
//	public LinkedHashMap<String, List<ConfigAttribute>> getRolesAndPointcut() throws Exception {
//		LinkedHashMap<String, List<ConfigAttribute>> ret = new LinkedHashMap<String, List<ConfigAttribute>>();
//		LinkedHashMap<Object, List<ConfigAttribute>> data = securedResourcePatternDao.getRolesAndPointcut();
//		Set<Object> keys = data.keySet();
//		
//		for(Object key : keys) {
//			ret.put((String)key, data.get(key));
//		}
//		
//		return ret;
//	}	
	
	

	@Override
	public List<ConfigAttribute> getMatchedRequestMapping(String url) throws Exception {
		return securedResourcePatternDAO.getRegexMatchedRequestMapping(url);
	}

	@Override
	public String getHierarchicalRoles() throws Exception {
		return securedResourcePatternDAO.getHierarchicalRoles();
	}

}
