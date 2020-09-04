package com.ssoclient1.demo.controller;

import com.ssoclient1.demo.util.SessionUtil;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/*
*@author:liuhongdi
*@date:2020/7/3 下午3:49
*@description:
 * @param null
*@return:
*/
@Controller
@RequestMapping("/home")
public class HomeController {


    //查看登录后的用户信息
    @RequestMapping("/session")
    @ResponseBody
    public String getsession(){
        //session
        String userone = SessionUtil.getCurrentUserName();
        System.out.println("user:"+userone);
        if (userone == null) {
            return "not login";
        } else {
            return userone;
        }
    }
}
