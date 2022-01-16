package com.macoloco.sheepy.config;

import io.minio.MinioClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Aya
 * @date 2022/1/16
 */
@Configuration
@Slf4j
public class MinioConfig {

    @Value("${minio.url}")
    private String minioUrl;

    @Value("${minio.accesskey}")
    private String accesskey;

    @Value("${minio.secretkey}")
    private String secretkey;

    @Bean
    public MinioClient getMinioClient() {
        log.info("Connect to Minio Service ...");
        MinioClient client = MinioClient.builder().endpoint(minioUrl).credentials(accesskey, secretkey).build();
        log.info("Connect to Minio Successful");
        return client;
    }

}
