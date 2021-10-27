package com.gala.bug.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

@Controller
public class IndexController {

//    @ApiOperation("jsp测试接口")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "username", value = "用户名", defaultValue = "jack"),
//            @ApiImplicitParam(name = "address", value = "用户地址", defaultValue = "长沙")
//    })
    @RequestMapping("/index")
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("index");
        mv.addObject("time", new Date());
        mv.addObject("message", "msghaha");
        return mv;
    }

    @RequestMapping("/freemarker")
    public String freemarker(Map<String, Object> map) {
        map.put("name", "zhuguang jack");
        map.put("gender", 0);

        List<Map<String, Object>> friends = new ArrayList<Map<String, Object>>();
        Map<String, Object> friend = new HashMap<String, Object>();
        friend.put("name", "roy");
        friend.put("age", 32);
        friends.add(friend);
        friend = new HashMap<String, Object>();
        friend.put("name", "walker");
        friend.put("age", 34);
        friends.add(friend);
        map.put("friends", friends);
        return "freemarker";
    }

}
