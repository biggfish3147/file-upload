package com.lou.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Controller
public class ImageHandleController {

    private final static String FILE_UPLOAD_PATH = "D:\\upload\\";

    @PostMapping(value = "/json_handle")
    @ResponseBody
    public Map<String, String> imageHandle(@RequestBody Map<String, String> requestData) {
        System.out.println(requestData);
//        return requestData;
        return requestData;
    }

    @PostMapping(value = "/img")
    @ResponseBody
    public Map<String, Object> getFile(@RequestBody MultipartFile file) throws IOException {
//        response.setCharacterEncoding("UTF-8");
        System.out.println("*****");
        System.out.println(file);
        Map<String, Object> map = new HashMap<String, Object>();
//        String filename = file.getOriginalFilename();
        if (file == null) {
            map.put("message", "文件上传失败");
            return map;
        }
        String filename = file.getOriginalFilename();
        System.out.println(filename);
        assert filename != null;

        if (!filename.endsWith(".jpg") && !filename.endsWith(".png") && !filename.endsWith(".jpeg")) {
            System.out.println("文件不是图片类型");
            map.put("message", "文件类型错误");
            return map;
        } else {
            map.put("message", "success");
            return map;
        }

    }

    @PostMapping(value = "/img_show")
    @ResponseBody
    public String ResultUrl(@RequestBody MultipartFile file) {
        String url = null;
        if (file.isEmpty()) {
            return "上传失败";
        }
        String fileName = file.getOriginalFilename();
        assert fileName != null;
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        //生成文件名称通用方法
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
        Random r = new Random();
        String newFileName = sdf.format(new Date()) + r.nextInt(100) + suffixName;
        try {
            // 保存文件
            byte[] bytes = file.getBytes();
            Path path = Paths.get(FILE_UPLOAD_PATH + newFileName);
//            String realurl=path.toString();
            url=newFileName;
            System.out.println(url);
            System.out.println("保存成功");
            Files.write(path, bytes);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return url;
    }

}


