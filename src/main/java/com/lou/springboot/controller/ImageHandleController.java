package com.lou.springboot.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    /**
     * 读取文件上传后的存储根路径
     */
    @Value("${web-file-upload-path}")
    private String fileUploadPath;

    /**
    *前端以ajax方式传输json数据，测试接受json数据
     * json：JavaScript Object Notation，独立于语言，轻量级的文本数据交换格式，自描述性
     */
    @PostMapping(value = "/json")
    @ResponseBody
    public Map<String, String> jsonHandle(@RequestBody Map<String, String> requestData) {
        System.out.println("json测试:"+requestData);
        return requestData;
    }

    /**
     *值得注意，以@RequestBody注解来获取文件，参数名必须与前端form中类型为file的input标签name属性值相同
     * 此处对应前端index.html中相应的属性名为file（更改参数名测试可验证）
     * @param file:获取前端传来的文件，参数类型为MultipartFile
     * @return 返回map数据，说明文件上传状态
     */
    @PostMapping(value = "/file-upload")
    @ResponseBody
    public Map<String, Object> getFile(@RequestBody MultipartFile file) {

        Map<String, Object> map = new HashMap<>();

        if (file == null) {
            map.put("message", "文件上传失败");
            return map;
        }

        //file先进行非空判断，才能调用getOriginalFilename函数，避免空指针错误
        String filename = file.getOriginalFilename();

        //assert断言：用于调试，一定程度替换System.out/if等来输出调试信息
        assert filename != null : "filename为空值，抛出AssertionError";

        if (!filename.endsWith(".jpg") && !filename.endsWith(".png") && !filename.endsWith(".jpeg")) {
            map.put("message", "文件不是支持的图片类型");
            return map;
        } else {
            map.put("message", "上传成功，后端已接收");
            return map;
        }

    }

    /**
     *功能说明：图片的回显。不同于前端js实现的实时预览，这里通过访问图片在服务器的url来访问图片
     * @param file 获取前端传递的文件
     * @return 返回文件的实际url，因为配置中将存储文件路径设置为静态资源文件夹，故只需要返回文件名即可
     */
    @PostMapping(value = "/file-showBack")
    @ResponseBody
    public String resultUrl(@RequestBody MultipartFile file) {
        String url = null;
        if (file.isEmpty()) {
            return "上传失败";
        }
        String fileName = file.getOriginalFilename();
        assert fileName != null;
        //获取文件后缀
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        //生成文件名称，避免多次提交相同文件导致服务器中文件名冲突
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
        Random r = new Random();
        String newFileName = simpleDateFormat.format(new Date()) + r.nextInt(100) + suffixName;

        try {
            // 保存文件
            byte[] bytes = file.getBytes();
            Path path = Paths.get(fileUploadPath + newFileName);
            url = newFileName;
            System.out.println("图片上传回显测试："+url);
            Files.write(path, bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return url;
    }

}


