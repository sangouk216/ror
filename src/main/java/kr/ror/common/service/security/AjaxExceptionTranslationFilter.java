package kr.ror.common.service.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.ExceptionTranslationFilter;

import kr.ror.common.Constants;

public class AjaxExceptionTranslationFilter extends ExceptionTranslationFilter {
	
		
	public AjaxExceptionTranslationFilter(AuthenticationEntryPoint authenticationEntryPoint) {
		super(authenticationEntryPoint);
	}

	@Override
	protected void sendStartAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, AuthenticationException reason) throws ServletException, IOException {
		
		String xRequestedWith = request.getHeader("X-Requested-With");
		
		if(!StringUtils.isEmpty(xRequestedWith)) {
			response.setContentType("application/json;charset=UTF-8");
			
			JSONObject errorResult = new JSONObject();
			errorResult.put(Constants.KEY_RESULT, Constants.VALUE_RESULT_LOGIN_REQUIRED);
			errorResult.put(Constants.KEY_MSG, Constants.VALUE_MSG_WRONG_ACCESS);
			
			response.getWriter().write(errorResult.toString());
			
			return;
		} else {
			super.sendStartAuthentication(request, response, chain, reason);
		}
	}

	
}
