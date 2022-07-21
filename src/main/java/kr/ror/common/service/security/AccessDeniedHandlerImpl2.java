package kr.ror.common.service.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import kr.ror.common.Constants;

public class AccessDeniedHandlerImpl2 implements AccessDeniedHandler {
	
	private String accessDeniedPage;
	
	public void setAccessDeniedPage(String accessDeniedPage) {
		this.accessDeniedPage = accessDeniedPage;
	}


	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException ex) throws IOException, ServletException {
		String xRequestedWith = request.getHeader("X-Requested-With");
		
		if(!StringUtils.isEmpty(xRequestedWith)) {
			
			response.setContentType("application/json;charset=UTF-8");
			
			JSONObject errorResult = new JSONObject();
			errorResult.put(Constants.KEY_RESULT, Constants.VALUE_RESULT_ACCESS_DENIED);
			errorResult.put(Constants.KEY_MSG, Constants.VALUE_MSG_WRONG_ACCESS);
			
			response.getWriter().write(errorResult.toString());
			return;
		} else {
			request.getSession().setAttribute("ACCESS_DENIED", true);
			String contextPath = request.getContextPath();
			response.sendRedirect(contextPath + this.accessDeniedPage);
		}
	}

}
