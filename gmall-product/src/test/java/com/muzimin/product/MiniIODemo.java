package com.muzimin.product;

import io.minio.MinioClient;
import io.minio.errors.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * @author: 李煌民
 * @date: 2024-07-14 21:15
 **/
@SpringBootTest
@RunWith(SpringRunner.class)
public class MiniIODemo {

    @Autowired
    MinioClient minioClient;

    @Test
    public void testUpload() throws InvalidArgumentException, InvalidBucketNameException, InsufficientDataException, XmlPullParserException, ErrorResponseException, NoSuchAlgorithmException, IOException, NoResponseException, InvalidKeyException, InternalException {
        minioClient.putObject("gmall","a.txt","D:\\download\\a.txt");
    }
}
