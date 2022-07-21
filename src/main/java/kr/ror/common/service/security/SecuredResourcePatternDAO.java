package kr.ror.common.service.security;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

public class SecuredResourcePatternDAO {

	private static final Logger LOGGER = LoggerFactory.getLogger(SecuredResourcePatternDAO.class);

	public static final String DEF_ROLES_AND_URL_QUERY =
		"SELECT A.RESOURCE_PATTERN AS URL, B.AUTHORITY AS AUTHORITY "
			+ "FROM TB_SECURED_RESOURCES A, TB_SECURED_RESOURCES_ROLE B "
			+ "WHERE A.RESOURCE_ID = B.RESOURCE_ID "
			+ "AND A.RESOURCE_TYPE = 'url' "
			+ "ORDER BY A.SORT_ORDER ";

	/*
	public static final String DEF_ROLES_AND_METHOD_QUERY =
		"SELECT A.RESOURCE_PATTERN AS METHOD, B.AUTHORITY AS AUTHORITY "
			+ "FROM SECURED_RESOURCES A, SECURED_RESOURCES_ROLE B "
			+ "WHERE A.RESOURCE_ID = B.RESOURCE_ID "
			+ "AND A.RESOURCE_TYPE = 'method' "
			+ "ORDER BY A.SORT_ORDER ";
	*/

	/*
	public static final String DEF_ROLES_AND_POINTCUT_QUERY =
		"SELECT A.RESOURCE_PATTERN AS POINTCUT, B.AUTHORITY AS AUTHORITY "
			+ "FROM SECURED_RESOURCES A, SECURED_RESOURCES_ROLE B "
			+ "WHERE A.RESOURCE_ID = B.RESOURCE_ID "
			+ "AND A.RESOURCE_TYPE = 'pointcut' "
			+ "ORDER BY A.SORT_ORDER ";
	*/
	

	public static final String DEF_REGEX_MATCHED_REQUEST_MAPPING_QUERY =
		"SELECT a.resource_pattern uri, b.authority authority "
			+ "FROM secured_resources a, secured_resources_role b "
			+ "WHERE a.resource_id = b.resource_id "
			+ "AND a.resource_id =  "
			+ " ( SELECT resource_id FROM "
			+ "    ( SELECT resource_id, ROW_NUMBER() OVER (ORDER BY sort_order) resource_order FROM secured_resources c "
			+ "      WHERE REGEXP_LIKE ( :url, c.resource_pattern ) "
			+ "      AND c.resource_type = 'url' "
			+ "      ORDER BY c.sort_order ) "
			+ "   WHERE resource_order = 1 ) ";


	public static final String DEF_HIERARCHICAL_ROLES_QUERY =
		"SELECT A.CHILD_ROLE CHILD, A.PARENT_ROLE PARENT "
			+ "FROM TB_ADMIN_ROLES_HIERARCHY A LEFT JOIN TB_ADMIN_ROLES_HIERARCHY B ON (A.CHILD_ROLE = B.PARENT_ROLE) ";
	
	private String sqlRolesAndUrl;

	//private String sqlRolesAndMethod;
	//private String sqlRolesAndPointcut;

	private String sqlRegexMatchedRequestMapping;

	private String sqlHierarchicalRoles;

	
	public SecuredResourcePatternDAO() {
		this.sqlRolesAndUrl = DEF_ROLES_AND_URL_QUERY;
		
		//this.sqlRolesAndMethod = DEF_ROLES_AND_METHOD_QUERY;
		//this.sqlRolesAndPointcut = DEF_ROLES_AND_POINTCUT_QUERY;

		this.sqlRegexMatchedRequestMapping = DEF_REGEX_MATCHED_REQUEST_MAPPING_QUERY;
		this.sqlHierarchicalRoles = DEF_HIERARCHICAL_ROLES_QUERY;

	}

	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public void setDataSource(DataSource dataSource) {
		this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}


	public String getSqlRolesAndUrl() {
		return sqlRolesAndUrl;
	}

	
	public void setSqlRolesAndUrl(String sqlRolesAndUrl) {
		this.sqlRolesAndUrl = sqlRolesAndUrl;
	}

	/*
	public String getSqlRolesAndMethod() {
		return sqlRolesAndMethod;
	}

	public void setSqlRolesAndMethod(String sqlRolesAndMethod) {
		this.sqlRolesAndMethod = sqlRolesAndMethod;
	}

	public String getSqlRolesAndPointcut() {
		return sqlRolesAndPointcut;
	}

	public void setSqlRolesAndPointcut(String sqlRolesAndPointcut) {
		this.sqlRolesAndPointcut = sqlRolesAndPointcut;
	}
	*/

	public String getSqlRegexMatchedRequestMapping() {
		return sqlRegexMatchedRequestMapping;
	}

	public void setSqlRegexMatchedRequestMapping(String sqlRegexMatchedRequestMapping) {
		this.sqlRegexMatchedRequestMapping = sqlRegexMatchedRequestMapping;
	}

	public String getSqlHierarchicalRoles() {
		return sqlHierarchicalRoles;
	}

	public void setSqlHierarchicalRoles(String sqlHierarchicalRoles) {
		this.sqlHierarchicalRoles = sqlHierarchicalRoles;
	}

	public LinkedHashMap<Object, List<ConfigAttribute>> getRolesAndResources(String resourceType) throws Exception {
		LinkedHashMap<Object, List<ConfigAttribute>> resourcesMap = new LinkedHashMap<Object, List<ConfigAttribute>>();

		String sqlRolesAndResources;

		boolean isResourcesUrl = true;

		/*if("method".equals(resourceType)) {
			sqlRolesAndResources = getSqlRolesAndMethod();
			isResourcesUrl = false;
		} else if ("pointcut".equals(resourceType)) {
			sqlRolesAndResources = getSqlRolesAndPointcut();
			isResourcesUrl = false;
		} else {*/
			sqlRolesAndResources = getSqlRolesAndUrl();
		//}


		List<Map<String, Object>> resultList = this.namedParameterJdbcTemplate.queryForList(sqlRolesAndResources, new HashMap<String, String>());
		
		Iterator<Map<String, Object>> itr = resultList.iterator();

		Map<String, Object> tempMap;
		String preResource = null;
		String presentResourceStr;
		Object presentResource;

		
		while(itr.hasNext()) {
			tempMap = itr.next();

			presentResourceStr = (String) tempMap.get(resourceType);
			presentResource = isResourcesUrl ? new AntPathRequestMatcher(presentResourceStr) : presentResourceStr;

			List<ConfigAttribute> configList = new LinkedList<ConfigAttribute>();

			if(preResource != null && presentResourceStr.equals(preResource)) { 
				List<ConfigAttribute> preAuthList = resourcesMap.get(presentResource);

				Iterator<ConfigAttribute> preAuthItr = preAuthList.iterator();

				while (preAuthItr.hasNext()) {
					SecurityConfig tempConfig = (SecurityConfig) preAuthItr.next();
					configList.add(tempConfig);
				}
			}

			configList.add(new SecurityConfig((String) tempMap.get("authority")));

			resourcesMap.put(presentResource, configList);

			preResource = presentResourceStr;

		}

		return resourcesMap;

	}

	public LinkedHashMap<Object, List<ConfigAttribute>> getRolesAndUrl() throws Exception {
		return getRolesAndResources("url");
	}

	public LinkedHashMap<Object, List<ConfigAttribute>> getRolesAndMethod() throws Exception {
		return getRolesAndResources("method");
	}
	
	public LinkedHashMap<Object, List<ConfigAttribute>> getRolesAndPointcut() throws Exception {
		return getRolesAndResources("pointcut");
	}

	public List<ConfigAttribute> getRegexMatchedRequestMapping(String url) throws Exception {
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("url", url);

		List<Map<String, Object>> resultList = this.namedParameterJdbcTemplate.queryForList(getSqlRegexMatchedRequestMapping(), paramMap);

		Iterator<Map<String, Object>> itr = resultList.iterator();

		Map<String, Object> tempMap;

		List<ConfigAttribute> configList = new LinkedList<ConfigAttribute>();

		while(itr.hasNext()) {
			tempMap = itr.next();
			configList.add(new SecurityConfig((String) tempMap.get("authority")));
		}

		if (configList.size() > 0) {
			LOGGER.debug("Request Uri : " + url + ", matched Uri : " + resultList.get(0).get("uri") + ", mapping Roles : " + configList);
		}

		return configList;

	}

	public String getHierarchicalRoles() throws Exception {
		List<Map<String, Object>> resultList = this.namedParameterJdbcTemplate.queryForList(getSqlHierarchicalRoles(), new HashMap<String, String>());

		Iterator<Map<String, Object>> itr = resultList.iterator();

		StringBuffer concatedRoles = new StringBuffer();

		Map<String, Object> tempMap;

		while (itr.hasNext()) {
			tempMap = itr.next();
			concatedRoles.append(tempMap.get("child"));
			concatedRoles.append(" > ");
			concatedRoles.append(tempMap.get("parent"));
			concatedRoles.append("\n");
		}
		
		return concatedRoles.toString();
	}
}