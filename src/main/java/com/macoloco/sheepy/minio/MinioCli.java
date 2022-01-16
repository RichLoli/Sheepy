package com.macoloco.sheepy.minio;

import io.minio.*;
import io.minio.errors.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * 基于SDK访问 Minio 对象存储服务
 * 并提供上传、下载和Minio管理的功能
 *
 * @author Aya
 * @date 2022/1/15
 */
@Component
@Slf4j
public class MinioCli {


    /**
     * Minio 客户端
     */
    private MinioClient minioClient;


    public MinioCli(@Autowired MinioClient minioClient) {
        this.minioClient = minioClient;
    }

    /**
     * 创建存储桶
     *
     * @param bucketName 存储桶名称
     * @return 是否成功
     */
    public boolean makeBucket(String bucketName) {
        try {
            BucketExistsArgs existsArgs = BucketExistsArgs.builder().bucket(bucketName).build();
            boolean bucketExists = minioClient.bucketExists(existsArgs);
            if (bucketExists) {
                log.warn("The bucket : {} is already exist", bucketName);
            } else {
                MakeBucketArgs bucketArgs = MakeBucketArgs.builder().bucket(bucketName).build();
                minioClient.makeBucket(bucketArgs);
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 存入数据到Minio
     * @param bucket 存储桶
     * @param objectName 对象名称
     * @param fileInputStream 对象字节流
     * @return 是否成功
     */
    public boolean putObject(MinioBucket bucket, String objectName, InputStream fileInputStream) {
        try {
            PutObjectArgs objectArgs = PutObjectArgs.builder().bucket(bucket.getBucketName())
                    .object(objectName)
                    .stream(fileInputStream, fileInputStream.available(), -1)
                    .build();
            ObjectWriteResponse response = minioClient.putObject(objectArgs);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 从Minio中获取对象流
     * @param bucket 存储桶
     * @param objectName 对象名称
     * @return 对象字节流
     */
    public InputStream getObject(MinioBucket bucket, String objectName){
        GetObjectArgs objectArgs = GetObjectArgs.builder().bucket(bucket.getBucketName()).object(objectName).build();
        try {
            return minioClient.getObject(objectArgs);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }




}
