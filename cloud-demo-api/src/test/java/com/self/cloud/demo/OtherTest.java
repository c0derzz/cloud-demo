package com.self.cloud.demo;

import com.alibaba.fastjson.JSONObject;
import com.self.cloud.demo.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: LiRuiChuan
 * @Date: 2019/11/17 19:24
 * @Description:
 */
public class OtherTest {

    public static void main(String[] args) {
        List<User> users = new ArrayList<>();
        User lee = new User();


        User wang = new User();

        users.add(lee);
        users.add(wang);
        System.out.println(JSONObject.toJSONString(users));
    }
}
