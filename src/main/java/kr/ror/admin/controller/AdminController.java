package kr.ror.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import kr.ror.admin.service.UserCheckService;
import kr.ror.service.MyPageService;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    @Autowired
    private UserCheckService userCheckService;
    @Autowired
    private MyPageService myPageService;

	@RequestMapping(value = "/user/userCheckMng", method = { RequestMethod.GET, RequestMethod.POST })
    public ModelAndView userCheckMng() {
        ModelAndView mav = new ModelAndView("admin/user/userCheckMng");

        try {
            System.out.println("userCheckMng");
            mav.addObject("USERLIST", userCheckService.getUserInfoList());
            mav.addObject("USERINFO", myPageService.getUserInfo());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mav;
    } //공지 보기 끝

}
