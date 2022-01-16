package com.macoloco.sheepy.minio;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MinioCliTest {

    @Autowired
    MinioCli client;

    @Test
    public void testPutObject(){
        try {
            client.putObject(MinioBucket.DEMO, "test-20220116-0001", new FileInputStream("F:\\TestFile\\Minio\\Test.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}