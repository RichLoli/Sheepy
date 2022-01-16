package com.macoloco.sheepy.minio;

/**
 * @author Aya
 * @date 2022/1/16
 */
public enum MinioBucket {

    /**
     * 存储桶 Demo 存放测试对象
     */
    DEMO("demo");

    @lombok.Getter
    private final String bucketName;

    MinioBucket(String bucketName) {
        this.bucketName = bucketName;
    }

}
