package com.muzimin.gmallthirdpart.controller;

import com.muzimin.common.utils.R;
import io.minio.GetPresignedObjectUrlArgs;
import io.minio.MinioClient;
import io.minio.errors.*;
import io.minio.http.Method;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author: 李煌民
 * @date: 2024-07-15 10:26
 **/
@RestController
@RequestMapping
public class OssController {

    @Autowired
    private MinioClient minioClient;

    @Value("${minio.bucket-name}")
    private String bucketName;

    @Value("${minio.endpoint}")
    private String endpoint;

    /**
     * MinIO的签名直传
     *
     * @return
     */
    @RequestMapping("/minio/policy/{objectName}")
    public R policy(@PathVariable("objectName") String objectName) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        /*String format = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        //设置签名的超时时间，现设定为5min
        Date expiration = new Date(System.currentTimeMillis() + 300 * 1000);
        URL generatePresignedUrl = minioClient.generatePresignedUrl(bucketName, format, expiration);
        return R.ok().put("data", generatePresignedUrl.toString());*/

        Map<String, String> reqParams = new HashMap<String, String>();
        reqParams.put("response-content-type", "application/json");
        String format = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        String minioPath = format + "/" + objectName;
        String PresignedUrl = minioClient.getPresignedObjectUrl(
                GetPresignedObjectUrlArgs.builder()
                        .method(Method.PUT) //这里必须是PUT，如果是GET的话就是文件访问地址了。如果是POST上传会报错.
                        .bucket(bucketName)
                        .object(minioPath)
                        .expiry(60 * 60 * 24)
                        .extraQueryParams(reqParams)
                        .build());

        Map<String, String> respMap= new LinkedHashMap<>();
        respMap.put("name", objectName);
        respMap.put("host", PresignedUrl);
        respMap.put("path", minioPath);
        respMap.put("url", endpoint + "/" + bucketName + "/" + minioPath);

        return R.ok().put("data", respMap);
    }

}
