package com.self.cloud.demo.api;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Auther: LiRuiChuan
 * @Date: 2019/11/23 14:54
 * @Description:
 */
@Controller
@RequestMapping("/index")
public class HomeController extends BaseController {

    @RequestMapping("/dashboard")
    public String home(String userName,Model model){
        model.addAttribute("userName",userName);
        System.out.println("跳转到首页。。。。");
        return "dashboard";
    }
}
