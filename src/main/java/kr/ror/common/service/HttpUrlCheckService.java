package kr.ror.common.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class HttpUrlCheckService extends HandlerInterceptorAdapter {
	
	   
protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Value("#{config['url.allowed.domain']}") 
	private String allowedDoamin;
	
	@Value("#{config['url.redirect.protocol']}") 
	private String redirectProtocol;
	
	@Value("#{config['url.redirect.domain']}") 
	private String redirectDomain;
	
	@Value("#{config['url.redirect.port']}") 
	private String redirectPort;
   
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		
		// Check Allowed Domain & Protocol
		if(allowedDoamin.indexOf(request.getServerName()) < 0 || request.getRequestURL().indexOf(redirectProtocol) < 0) {
		//if(allowedDoamin.indexOf(request.getServerName()) < 0) {
			
			String redirectUrl = "";
			String requestUri = request.getRequestURI();
			String queryString = request.getQueryString();
			
			redirectUrl = redirectProtocol + redirectDomain + redirectPort + requestUri + (queryString == null ? "" : "?" + queryString);
			
			System.out.println(redirectUrl);
			
			response.sendRedirect(redirectUrl);
			return false;
		}
		
		return super.preHandle(request, response, handler);
	}
}