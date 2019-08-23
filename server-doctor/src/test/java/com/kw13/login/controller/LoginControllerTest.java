package com.kw13.login.controller;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.*;

/**
 * Created by cheng on 2019/8/22.
 */
@RunWith(SpringRunner.class)
@Transactional
@SpringBootTest
public class LoginControllerTest {
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac;

    @Before
    public void setUp() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void info1() throws Exception {
        String mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/login/health/info"))
                .andReturn().getResponse().getContentAsString();
        System.out.println("Result === " + mvcResult);
    }

    @Test
    public void home1() throws Exception {
    }

}