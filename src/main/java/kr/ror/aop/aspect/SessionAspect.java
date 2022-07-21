package kr.ror.aop.aspect;

import java.lang.reflect.Method;
import java.util.Map;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import kr.ror.comm.vo.MemberAuthVO;
import kr.ror.comm.vo.UserAuthVO;


@Aspect
public class SessionAspect {

	@Before("execution(* kr.ror.common.dao.CommonDAO.select*(String, Object)) || "
          + "execution(* kr.ror.common.dao.CommonDAO.update*(String, Object)) || "
          + "execution(* kr.ror.common.dao.CommonDAO.insert*(String, Object)) || "
          + "execution(* kr.ror.common.dao.CommonDAO.delete*(String, Object))")
	public JoinPoint before(JoinPoint joinPoint) {
		
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			  System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
              System.out.println(authentication);
              System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
			if(authentication != null) {
				Object[] params = joinPoint.getArgs();
				
				if(params.length > 1 && params[1] != null) {
				    
				    // MEMBER LOGIN
				    if (authentication.getPrincipal() instanceof MemberAuthVO) {
		                MemberAuthVO memberAuthVO = (MemberAuthVO) authentication.getPrincipal();
		                String loginMemNo = memberAuthVO.getMemNo();
		                System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		                System.out.println(memberAuthVO);
		                System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
	                    Object param = params[1];
		                if(param instanceof Map ) {
		                    ((Map)param).put("loginMemNo", loginMemNo);
	                    } else {
	                        Class vo = param.getClass();
                            Method method = vo.getMethod("setLoginMemNo", String.class);
                            method.invoke(param, loginMemNo);
	                    }
		            }
                    
				    // USER LOGIN
                    if (authentication.getPrincipal() instanceof UserAuthVO) {
                        UserAuthVO userAuthVO = (UserAuthVO) authentication.getPrincipal();
                        String loginUserCd = userAuthVO.getUserCd();

                        Object param = params[1];
                        if(param instanceof Map ) {
                            ((Map)param).put("loginUserCd", loginUserCd);
                        } else {
                            Class vo = param.getClass();
                            Method method = vo.getMethod("setLoginUserCd", String.class);
                            method.invoke(param, loginUserCd);
                        }
                    }
				}
			}
			
		//} catch (NoSuchMethodException e) {
		//} catch (InvocationTargetException e) {
		//} catch (IllegalAccessException e) {
		} catch (Exception e) {
			
		}
		return joinPoint;
	}
	
}
