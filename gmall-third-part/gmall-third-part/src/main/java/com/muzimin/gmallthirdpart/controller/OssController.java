package com.muzimin.gmallthirdpart.controller;

import com.amazonaws.services.s3.AmazonS3;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author: 李煌民
 * @date: 2024-07-15 10:26
 **/
@RestController
@RequestMapping
public class OssController {

    @Autowired
    private AmazonS3 minioClient;

    @Value("${minio.bucket-name}")
    private String bucketName;

    /**
     * MinIO的签名直传
     * @return
     */
    @RequestMapping("/minio/policy")
    public String policy() {
        String format = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        Date expiration = new Date(System.currentTimeMillis() + 30 * 1000);
        URL generatePresignedUrl = minioClient.generatePresignedUrl(bucketName, format, expiration);
        return generatePresignedUrl.toString();
    }

}
