package com.gala.bug.controller;

import com.alibaba.fastjson.JSON;
import com.gala.bug.service.GalaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.ws.rs.POST;
import java.util.*;

//@Controller
@RequestMapping("/gala")
@RestController
public class GalaController {
    @Autowired
    private GalaService service;

    @RequestMapping(value="/queryStaff",method= RequestMethod.POST)
//    public Map<String,Object> queryStaff(@RequestAttribute String name) {
    public Map<String,Object> queryStaff(@RequestBody Map<String,Object> map) {
//        @RequestBody Map<String,String> map
        System.out.println(JSON.toJSONString(map));
        return service.queryStaff(null);
    }

    @RequestMapping(value="/queryDepartments",method= RequestMethod.POST)
    public Map<String,Object> queryDepartments() {
//        @RequestBody Map<String,Object> map
        return service.queryDepartments(null);
    }

}
