package com.tw.apistackbase.controller;


import com.fasterxml.jackson.annotation.JsonValue;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.JSONValue;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeTest {

    @Autowired
    private MockMvc mockMvc;


    @Test
    public void shouldReturnEmployeesListWhenGet() throws Exception {
        String expectStr = "[{\"name\":\"aaa\",\"id\":11,\"age\":18,\"gender\":\"a1\"}]";
        this.mockMvc.perform(get("/employees")).andExpect(status().isOk())
                .andExpect(content().string(containsString(expectStr)));
    }

    @Test
    public void shouldReturn201WhenPost() throws Exception {
        //String expectStr="{\"name\":\"aaa\",\"id\":12,\"age\":40,\"gender\":\"asdas\"}";
        // String requestJson="{\"name\":\"aaa\",\"id\":12,\"age\":40,\"gender\":\"asdas\"}";
        // Employee employee=new Employee("aaa",11,18,"a1");
        Map<String, String> employeeInfo = new HashMap<>();
        employeeInfo.put("name", "aaa");
        employeeInfo.put("id", "11");
        employeeInfo.put("age", "18");
        employeeInfo.put("gender", "zzzzz");
        String requestJson = JSONObject.toJSONString(employeeInfo);
        this.mockMvc.perform(post("/employees").contentType(MediaType.APPLICATION_JSON).content(requestJson)).andExpect(status().isCreated());

    }

    @Test
    public void shouldReturnEmployeesListWhenPut() throws Exception {
        Map<String, String> employeeInfo = new HashMap<>();
        employeeInfo.put("name", "bbb");
        employeeInfo.put("id", "11");
        employeeInfo.put("age", "18");
        employeeInfo.put("gender", "zzzzz");
        String requestJson = JSONObject.toJSONString(employeeInfo);
        String mvcResult = this.mockMvc.perform(put("/employees").contentType(MediaType.APPLICATION_JSON).content(requestJson))
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        JSONObject jsonObject=(JSONObject) JSONValue.parse(mvcResult);
        Assertions.assertEquals("bbb",jsonObject.get("name"));

    }
    @Test
    public void shouldDeleteEmployeeWhenDelete()throws Exception{
            this.mockMvc.perform(get("/employees")).andReturn();
            this.mockMvc.perform(delete("/employees/11")).andExpect(status().isOk());
    }

}
