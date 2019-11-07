package com.self.cloud.demo.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by liruichuan on 2018/7/19.
 */
@Data
@ApiModel(value = "user对象",description = "用户对象user")
public class User implements Serializable{

    @ApiModelProperty(value="用户名",name="userName",example="coder")
    private String userName;
    @ApiModelProperty(value="真实姓名",name="realName",example="网三")
    private String realName;
    @ApiModelProperty(value="地址",name="address",example="北京市")
    private String address;
    @ApiModelProperty(value="年龄",name="age",example="20")
    private Integer age;
}
