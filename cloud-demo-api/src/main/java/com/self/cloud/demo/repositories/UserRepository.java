package com.self.cloud.demo.repositories;

import com.self.cloud.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Auther: LiRuiChuan
 * @Date: 2019/11/16 17:29
 * @Description:
 */
public interface UserRepository extends JpaRepository<User,Integer> {

    /**
     * 根据姓名获取用户
     * @param name
     * @return
     */
    User findByName(String name);

}
