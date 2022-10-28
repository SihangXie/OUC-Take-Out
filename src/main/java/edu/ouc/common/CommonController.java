package edu.ouc.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.UUID;

/**
 * @Author: Sihang Xie
 * @Description: 公共的Controller组件
 * @Date: 2022/10/3 14:18
 * @Version: 0.0.1
 * @Modified By:
 */
@RestController
@RequestMapping("/common")
@Slf4j
public class CommonController {

    // 获取配置文件中的存储路径
    @Value("${reggie.path}")
    private String basePath;

    // 文件上传
    @PostMapping("/upload")
    public R<String> upload(MultipartFile file) { // 入参名必须是file
        // file是一个临时文件，需要转存到指定位置，否则本次请求完成后临时文件会删除

        // 获取原始文件名
        String originalFilename = file.getOriginalFilename();   // 原始名称.jpg

        // 截取原始名称的后缀
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));  //.jpg

        // 使用UUID重新生成文件名，防止文件名称重复造成文件覆盖
        String fileName = UUID.randomUUID().toString() + suffix;    // sdfaiourei.jpg

        // 创建一个目录对象
        File dir = new File(basePath);
        // 判断当前目录是否存在
        if (!dir.exists()) {
            // 目录不存在需要创建
            dir.mkdir();
        }

        try {
            // 转存
            file.transferTo(new File(basePath + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 返回文件名称即可
        return R.success(fileName);
    }

    // 文件下载
    @GetMapping("/download")
    public void download(String name, HttpServletResponse response) {   // 入参名字必须是name
        FileInputStream fis = null;
        ServletOutputStream os = null;

        try {
            // 输入流，通过输入流读取文件内容
            fis = new FileInputStream(new File(basePath + name));

            // 输出流，通过输出流将文件写回浏览器，在浏览器展示图片
            os = response.getOutputStream();

            // 下面都是JavaSE中IO流的内容
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = fis.read(buffer)) != -1) {
                os.write(buffer, 0, len);
                os.flush();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            // 关闭流资源
            try {
                if (fis != null)
                    fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (os != null)
                    os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
