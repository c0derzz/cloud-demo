package com.self.cloud.demo;

import com.alibaba.fastjson.JSONObject;
import com.self.cloud.demo.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

/**
 * @Auther: LiRuiChuan
 * @Date: 2019/11/17 19:29
 * @Description:
 */
/*@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)*/

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)//MOCK为默认值，也可不设置
@AutoConfigureMockMvc
public class UserControllerTest {

    /*@LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private URL url;

    @Before
    public void setUp() throws Exception{
        url = new URL("http://localhost:"+port+"/user/adds");
    }*/

    @Autowired
    private MockMvc mvc;
    @Test
    public void testAddUser() throws Exception {
        List<User> users = new ArrayList<>();
        User lee = new User();
        lee.setName("lee");
        lee.setAge(20);

        User wang = new User();
        wang.setName("wang");
        wang.setAge(20);

        users.add(lee);
        users.add(wang);
        String paraJson = JSONObject.toJSONString(users);

        String mvcResult = mvc.perform(MockMvcRequestBuilders.post(new URI("/user/save"))
                .contentType(MediaType.APPLICATION_JSON)
                .content(paraJson))
                .andDo(print())
                .andReturn()
                .getResponse()
                .getContentAsString();
        System.out.println(mvcResult);

    }

}
