package com.self.cloud.demo;

/**
 * @Auther: LiRuiChuan
 * @Date: 2019/11/16 21:28
 * @Description:
 */

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

// 指定启动类
@SpringBootTest(classes={DemoApiApplication.class})
@RunWith(SpringRunner.class)
public class BaseJunitTest {

    @Before
    public void before(){

    }

}

