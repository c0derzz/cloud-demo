package com.self.cloud.demo.api;

import com.alibaba.fastjson.JSONObject;
import com.self.cloud.demo.annotation.APILimit;
import com.self.cloud.demo.annotation.APILimitType;
import com.self.cloud.demo.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

/**
 * @Auther: LiRuiChuan
 * @Date: 2019/11/17 14:31
 * @Description:
 */
@Controller
@RequestMapping("/user")
public class UserController  {

    @RequestMapping(value = "/add",method = RequestMethod.GET)
    @APILimit(name = "user/add",type = APILimitType.CUSTOMER,prefix = "TEST",key = "user_add",count = 5,period = 60)
    public String addUser(ModelMap modelMap){
        modelMap.put("title","添加用户");
        modelMap.put("today",new Date());
        return "userForm";
    }

    @RequestMapping(value = "/save",method = RequestMethod.POST)
    @ResponseBody
    public String addUsers(@RequestBody List<User> user){
        System.out.println(JSONObject.toJSONString(user));
        return "success";
    }
}
