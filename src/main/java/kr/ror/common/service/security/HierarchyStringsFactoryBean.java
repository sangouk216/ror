package kr.ror.common.service.security;

import org.springframework.beans.factory.FactoryBean;

public class HierarchyStringsFactoryBean implements FactoryBean<String> {
	
	private SecuredResourcePatternService securedResourcePatternService;
	
	private String hierarchyStrings;

	public void setSecuredResourcePatternService(SecuredResourcePatternService securedResourcePatternService) {
		this.securedResourcePatternService = securedResourcePatternService;
	}
	
	public void init() throws Exception {
		hierarchyStrings = securedResourcePatternService.getHierarchicalRoles();
	}

	@Override
	public String getObject() throws Exception {
		if(hierarchyStrings == null) {
			hierarchyStrings = securedResourcePatternService.getHierarchicalRoles();
		}
		
		return hierarchyStrings;
	}

	@Override
	public Class<?> getObjectType() {
		return String.class;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}
}