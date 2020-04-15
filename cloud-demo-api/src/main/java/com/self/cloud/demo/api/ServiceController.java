package com.self.cloud.demo.api;

import com.self.cloud.demo.bean.User;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

/**
 * Created by liruichuan on 2018/5/29.
 */
@Api(value = "用户相关接口")
@RestController
public class ServiceController {

    @Autowired
    private RedisTemplate redisTemplate;

    @ApiOperation(value = "hello info 接口",notes = "测试接口")
    @RequestMapping(value = "/Info",method = RequestMethod.GET)
    public String getInfo(){
        redisTemplate.opsForValue().set("info", "info");
        return "hello I am is service ";
    }
    @ApiOperation(value = "getHelloInfo接口",notes = "根据用户名返回值")

    //@ApiImplicitParam
    @RequestMapping(value = "/HelloInfo",method = RequestMethod.GET)
    public String getHelloInfo(@ApiParam(value = "userName",name = "用户名",required = true,example = "coder")String userName){
        return "hello,"+ userName +" I am is service ";
    }

    @ApiOperation(value = "getHelloInfo接口",notes = "根据用户名返回值",response = String.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name="userName",value = "用户名",required = true,dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="age",value = "年龄",required = true,dataType = "Long",paramType = "query")
    })
    @RequestMapping(value = "/HelloInfo2",method = RequestMethod.GET)
    public String getHelloInfo2(String userName,Long age){
        return "hello,"+ userName +" I am is service "+age;
    }


    @ApiOperation(value = "查询用户接口",notes = "根据用户名查询用户信息")
    /**
     *  header-->请求参数的获取：@RequestHeader
     *  query-->请求参数的获取：@RequestParam
     *  path（用于restful接口）-->请求参数的获取：@PathVariable
     */
    @ApiImplicitParam(name="userName",value = "用户名",required = true,dataType = "String",paramType = "query" )// paramType :path\query\body\header\form  body是以流的形式提交 仅支持POST
    @RequestMapping(value = "/getUser",method = RequestMethod.GET)
    public User getUser(@RequestParam(value = "userName") String userName){
        User user = new User();
        user.setUserName(userName);
        user.setRealName("大哥哥");
        user.setAddress("北京市");
        user.setAge(66);
        return user;
    }

    @ApiOperation("更改用户信息")
    @RequestMapping(value = "/updateUserInfo",method = RequestMethod.POST)
    public int updateUserInfo(@RequestBody @ApiParam(name="用户对象",value="传入json格式",required=true) User user){
        return user.getAge()+1;
    }
}
