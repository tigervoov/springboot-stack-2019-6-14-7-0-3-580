package com.tw.apistackbase.controller;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldReturnEmployeesList() throws Exception{
        String expectStr="[{\"name\":\"aaa\",\"id\":11,\"age\":18,\"gender\":\"a1\"}]";
        this.mockMvc.perform(get("/employees")).andExpect(status().isOk())
                .andExpect(content().string(containsString(expectStr)));
    }

}
