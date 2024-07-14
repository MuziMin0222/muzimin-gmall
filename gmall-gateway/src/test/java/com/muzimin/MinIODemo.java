package com.muzimin;


import io.minio.MinioClient;
import io.minio.errors.*;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * @author: 李煌民
 * @date: 2024-07-14 20:17
 **/
public class MinIODemo {
    public static void main(String[] args) throws Exception {
        // 使用Minio服务的URL，端口，Access key和Secret key创建一个MinioClient对象
        MinioClient minioClient = new MinioClient(
                "http://192.168.56.11:9000",
                "fileadmin",
                "fileadmin");

        boolean isExist = minioClient.bucketExists("gmall");
        System.out.println(isExist);

        // 使用putObject上传一个文件到存储桶中。
        minioClient.putObject("gmall","a.txt", "D:\\download\\Centos-7.repo");
    }
}
