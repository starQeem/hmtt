package com.heima.minio.test;

import com.heima.file.service.FileStorageService;
import com.heima.minio.MinIoApplication;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * @Date: 2023/7/12 17:49
 * @author: Qeem
 */
@SpringBootTest(classes = MinIoApplication.class)
@RunWith(SpringRunner.class)
public class MinIoTest {

    @Resource
    private FileStorageService fileStorageService;

    //把list.html文件上传到minio中,并且可以在浏览器中访问
    @Test
    public void test() throws FileNotFoundException {
        FileInputStream fileInputStream = new FileInputStream("d:/tem/heima-leadnews/list.html");
        String path = fileStorageService.uploadHtmlFile("", "list.html", fileInputStream);
        System.out.println(path);
    }

    /**
     *  把list.html文件上传到minio中,并且可以在浏览器中访问
     *
     * @param args
     */
    public static void main(String[] args) {
        try {
            FileInputStream fileInputStream = new FileInputStream("d:/tem/js/index.js");
            //1.获取minio的链接信息,创建一个minio的客户端
            MinioClient minioClient = MinioClient.builder().credentials("minio", "minio456").endpoint("http://180.110.202.50:9000").build();
            //2.上传
            PutObjectArgs putObjectArgs = PutObjectArgs.builder()
                    .object("plugins/js/index.js")  //文件名称
                    .contentType("text/javascript") //文件类型
                    .bucket("leadnews") //桶名称  与minio管理界面的桶一致即可
                    .stream(fileInputStream, fileInputStream.available(), -1)
                    .build(); //-1是指所有的相关文件都上传
            minioClient.putObject(putObjectArgs);
            //访问路径
//            System.out.println("http://180.110.202.50:9000/leadnews/list.html");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
