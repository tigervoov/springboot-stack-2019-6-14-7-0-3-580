package com.tw.apistackbase.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.ListResourceBundle;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * Created by jxzhong on 18/08/2017.
 */
@RestController
@RequestMapping("/employees")
public class HelloResource {
    private List<Employee> result=new ArrayList<>();

    private final Logger log = Logger.getLogger(this.getClass().getName());

    @GetMapping(path = "/{userName}", produces = {"application/json"})
    public ResponseEntity<String> getAll(@PathVariable String userName) {

        return ResponseEntity.ok("Hello:" + userName);
    }
    @GetMapping()
    public ResponseEntity getAllEmployees(){
        Employee employee=new Employee("aaa",11,18,"a1");
        result.add(employee);
        return ResponseEntity.ok().body(result);
    }
    @PostMapping()
    public ResponseEntity create(@RequestBody Employee employee){
        result.add(employee);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @PutMapping("/{id}")
    public ResponseEntity put(@PathVariable int id,@RequestBody Employee employee){
        List<Employee> changeEmployee=result.stream().filter(item->item.getId()==id).collect(Collectors.toList());
        changeEmployee.get(0).setName(employee.getName());
        changeEmployee.get(0).setAge(employee.getAge());
        changeEmployee.get(0).setGender(employee.getGender());
        return ResponseEntity.ok().body(changeEmployee);

    }
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable int id){
        for(Employee employee:result){
            if(employee.getId()==id){
                result.remove(employee);
                return ResponseEntity.ok().build();
            }
        }
        return ResponseEntity.notFound().build();
    }



}
