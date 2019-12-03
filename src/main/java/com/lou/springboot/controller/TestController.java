package com.lou.springboot.controller;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

@Controller
@RequestMapping("/paramurl")
public class TestController {

    @RequestMapping(value="/paramTest.do",method=RequestMethod.POST)
    @ResponseBody
    public String paramTest(@RequestBody String param){
        System.out.println(param);
        JSONObject jo=new JSONObject();

        //如果页面传的是json字符串，用下列方式解析
//		Map<String, Object> m=(Map<String, Object> )jo.parse(param); //string转map
//		System.out.println(m);//
//		JSONObject parseObject = jo.parseObject(param); //string转json
//		System.out.println(parseObject);

        //如果页面传的是json数组字符串，用下列方式解析
        List<Map> parseArray = JSON.parseArray(param, Map.class);
        System.out.println(parseArray); //string转list
        System.out.println(parseArray.get(0));

        JSONArray parseArray2 = JSON.parseArray(param);
        System.out.println(parseArray2);
        System.out.println(parseArray2.get(0));

        return "ok";
    }
}
