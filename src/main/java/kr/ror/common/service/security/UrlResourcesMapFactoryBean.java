package kr.ror.common.service.security;

import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.util.matcher.RequestMatcher;

public class UrlResourcesMapFactoryBean implements FactoryBean<LinkedHashMap<RequestMatcher, List<ConfigAttribute>>> {
	
	private SecuredResourcePatternService securedResourcePatternService;
	
	private LinkedHashMap<RequestMatcher, List<ConfigAttribute>> requestMap;

	public void setSecuredResourcePatternService(SecuredResourcePatternService securedResourcePatternService) {
		this.securedResourcePatternService = securedResourcePatternService;
	}
	
	public void init() throws Exception {
		requestMap = securedResourcePatternService.getRolesAndUrl();
	}

	@Override
	public LinkedHashMap<RequestMatcher, List<ConfigAttribute>> getObject() throws Exception {
		if(requestMap == null) {
			requestMap = securedResourcePatternService.getRolesAndUrl();
		}
		
		return requestMap;
	}

	@Override
	public Class<?> getObjectType() {
		return LinkedHashMap.class;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}
}