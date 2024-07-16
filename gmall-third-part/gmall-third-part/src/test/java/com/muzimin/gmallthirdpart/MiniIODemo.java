package com.muzimin.gmallthirdpart;

import io.minio.GetPresignedObjectUrlArgs;
import io.minio.MinioClient;
import io.minio.errors.*;
import io.minio.http.Method;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: 李煌民
 * @date: 2024-07-14 21:15
 **/
@SpringBootTest
@RunWith(SpringRunner.class)
public class MiniIODemo {

    /*@Autowired
    AmazonS3 minioClient;*/

   /* @Test
    public void testUpload() {
        minioClient.putObject("gmall", "b.txt", "D:\\download\\a.txt");
    }*/

    /*@Test
    public void testpolicy() {
        String format = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        Date expiration = new Date(System.currentTimeMillis() + 30 * 1000);
        String bucketName = "gmall";
        URL generatePresignedUrl = minioClient.generatePresignedUrl(bucketName, format, expiration);
        System.out.println(generatePresignedUrl.toString());
    }*/

    @Test
    public void getpolicy() throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        MinioClient minioClient = MinioClient.builder().endpoint("http://192.168.56.11:9000")
                .credentials("fileadmin", "fileadmin").build();
        Map<String, String> reqParams = new HashMap<String, String>();
        reqParams.put("response-content-type", "application/json");
        String product = minioClient.getPresignedObjectUrl(
                GetPresignedObjectUrlArgs.builder()
                        .method(Method.PUT) //这里必须是PUT，如果是GET的话就是文件访问地址了。如果是POST上传会报错.
                        .object("aaa.txt")
                        .bucket("gmall")
                        .expiry(60 * 60 * 24)
                        .extraQueryParams(reqParams)
                        .build());
        System.out.println(product);
    }
}
