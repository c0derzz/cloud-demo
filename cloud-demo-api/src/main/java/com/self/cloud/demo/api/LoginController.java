package com.self.cloud.demo.api;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.MessageFormat;

/**
 * @Auther: LiRuiChuan
 * @Date: 2019/11/23 14:49
 * @Description: 登录
 */

@Controller
@RequestMapping("/admin")
public class LoginController extends BaseController {

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    /**
     * 登录
     *
     * @param
     * @param model
     * @param
     * @return
     */
    @PostMapping("/login")
    public String loginPost(String userName,String password, Model model) {
        if (StringUtils.isNotBlank(userName) && StringUtils.isNotBlank(password)) {
            System.out.println(MessageFormat.format("用户名：{0},密码：{1}",userName,password));
            return "redirect:/index/dashboard?userName"+userName;
        } else {
            model.addAttribute("error", "用户名或密码错误，请重新登录！");
            return "login";
        }
    }

}
