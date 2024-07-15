package com.muzimin.gmallthirdpart;

import com.amazonaws.services.s3.AmazonS3;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author: 李煌民
 * @date: 2024-07-14 21:15
 **/
@SpringBootTest
@RunWith(SpringRunner.class)
public class MiniIODemo {

    @Autowired
    AmazonS3 minioClient;

    @Test
    public void testUpload() {
        minioClient.putObject("gmall", "b.txt", "D:\\download\\a.txt");
    }

    @Test
    public void testpolicy() {
        String format = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        Date expiration = new Date(System.currentTimeMillis() + 30 * 1000);
        String bucketName = "gmall";
        URL generatePresignedUrl = minioClient.generatePresignedUrl(bucketName, format, expiration);
        System.out.println(generatePresignedUrl.toString());
    }
}
