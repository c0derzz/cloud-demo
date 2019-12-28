package com.self.cloud.demo.model;

import lombok.Data;
import org.apache.commons.pool2.BaseObject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * @Auther: LiRuiChuan
 * @Date: 17:43 2019/12/28
 * @Description: 用户
 */
@Data
@Entity
@Table(name="tb_user")
public class User extends BaseEntity {

    @Column(name="user_name",nullable = false,length = 45)
	private String userName;

	@Column(name="password",nullable = false,length = 45)
	private String password;

	@Column(name="real_name",length = 45)
	private String realName;

	@Column(name="business",length = 45)
	private String  business;

	@Column(name="email",length = 45)
	private String email;

	@Column(name="hea_picture",length = 45)
	private String headPicture;

	@Column(name="state",length = 11)
	private int state;
}
