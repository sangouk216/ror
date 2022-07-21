package kr.ror.common.service.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

public class CustomLogoutSuccessHandler implements LogoutSuccessHandler {

	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		
    	    Cookie[] cookies = request.getCookies(); // 모든 쿠키의 정보를 cookies에 저장
    	    if (cookies != null) { // 쿠키가 한개라도 있으면 실행
    	        for (int i = 0; i < cookies.length; i++) {
    	            cookies[i].setMaxAge(0); // 유효시간을 0으로 설정
    	            response.addCookie(cookies[i]); // 응답에 추가하여 만료시키기.
    	        }
    	    }
		   try {
//               request.getSession().invalidate();
          } catch (Exception e) {
              e.printStackTrace();
          }
		  response.sendRedirect("/ror/login"); 
	}

}
