package com.gala.bug.utils;

import com.alibaba.fastjson.JSON;
import org.springframework.boot.configurationprocessor.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class GalaUtils {
    public static String doTranslate(HttpServletRequest req) throws IOException {
        HashMap<String,Object> reqMap = new HashMap<String, Object>();
        reqMap.put("getRequestURL",req.getRequestURL());
        reqMap.put("getServerPort",req.getServerPort());

//        Map preMap = req.getParameterMap();

        BufferedReader reader = new BufferedReader(new InputStreamReader(req.getInputStream()));
        String str = "";
        String wholeStr = "";
        while((str = reader.readLine()) != null){//一行一行的读取body体里面的内容；
            wholeStr += str;
        }
        System.out.println("inputStr="+wholeStr);
        Map<String,Object> params = JSON.parseObject(wholeStr, Map.class);
        for (String key : params.keySet()) {
//            preMap.put(key,params.get(key));
            req.setAttribute(key,params.get(key));
        }
        reqMap.put("getInputStream",params);
        return JSON.toJSONString(reqMap);
    }
}
