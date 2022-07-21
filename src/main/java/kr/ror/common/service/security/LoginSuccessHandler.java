package kr.ror.common.service.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import kr.ror.common.util.CookieUtil;

public class LoginSuccessHandler implements AuthenticationSuccessHandler {


	private String defaultSuccessUrl;
	
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
	 
        /* String corpUrl = request.getParameter(corpDishUrl); */
		String rememberId = request.getParameter("rememberId");
		String id = request.getParameter("id").split(":")[0];
		
		 if(rememberId != null && rememberId.equals("1")) {
	            // Set Cookie
	            CookieUtil.setCookie(request, response, "id", id);
	            CookieUtil.setCookie(request, response, "rememberId", rememberId);
	        } else {
	            // Remove Cookies
	            CookieUtil.removeCookie(request, response, "id");
	            CookieUtil.removeCookie(request, response, "rememberId");
	        }
		 
		 HttpSession session = request.getSession();    
         /* session.setAttribute("corpDishUrl", corpUrl); */
		 
		// CookieUtil.setCookie(request, response, "sessionId", session.getId());
		 
		
		 
		response.sendRedirect(defaultSuccessUrl);
	}






	public String getDefaultSuccessUrl() {
		return defaultSuccessUrl;
	}


	public void setDefaultSuccessUrl(String defaultSuccessUrl) {
		this.defaultSuccessUrl = defaultSuccessUrl;
	}

	
	
	
	
}
