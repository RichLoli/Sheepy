package com.macoloco.sheepy.util;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.util.Strings;
import org.rocksdb.Options;
import org.rocksdb.RocksDB;
import org.rocksdb.RocksDBException;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * @author Aya
 * @date 2022/1/16
 */
@Slf4j
public class RocksClient {

    private static RocksDB rocks;

    static {
        RocksDB.loadLibrary();
        try {
            Options options = new Options().setCreateIfMissing(true);
            rocks = RocksDB.open(options, "rocks");
            log.info("Loading RocksDB Success");
        } catch (RocksDBException e) {
            log.error("Loading RocksDB Failure Error Message : {}", e.getMessage());
        }
    }

    private RocksClient() {

    }


    private static void put(String key, byte[] values) {
        if (StringUtils.isNotEmpty(key) && values.length > 0) {
            try {
                rocks.put(key.getBytes(StandardCharsets.UTF_8), values);
            } catch (RocksDBException e) {
                log.error("RocksDB Put Value Error Message ：{}", e.getMessage());
            }
        }
        log.warn("RocksDB Put Values is Empty");
    }

    /**
     * 添加数据到缓存中
     *
     * @param key   键
     * @param value 值
     */
    public static void put(String key, String value) {
        if (StringUtils.isNotEmpty(key) && StringUtils.isNotEmpty(value)) {
            put(key, value.getBytes());
        }
    }

    /**
     * 添加数据到缓存中
     *
     * @param key   键
     * @param value 值
     */
    public static void put(String key, Map<String, Object> value) {
        if (StringUtils.isNotEmpty(key)) {
            String pack = JSON.toJSONString(value);
            put(key, pack.getBytes());
        }
    }

    /**
     * 添加数据到缓存中
     *
     * @param key   键
     * @param value 值
     */
    public static void put(String key, int value) {
        if (StringUtils.isNotEmpty(key)) {
            put(key, ByteBuffer.allocate(4).putInt(value).array());
        }
    }

    /**
     * 添加数据到缓存中
     *
     * @param key   键
     * @param value 值
     */
    public static void put(String key, double value) {
        if (StringUtils.isNotEmpty(key)) {
            put(key, ByteBuffer.allocate(4).putDouble(value).array());
        }
    }

    /**
     * 获取缓存中的值
     *
     * @param key 键
     * @return 返回键对应存放的值
     */
    public static String get(String key) {
        if (StringUtils.isNotEmpty(key)) {
            try {
                byte[] bytes = rocks.get(key.getBytes(StandardCharsets.UTF_8));
                if (null != bytes) {
                    return new String(bytes, "UTF-8");
                }
            } catch (RocksDBException e) {
                log.error("缓存获取失败 异常信息：{}", e.getMessage());
            } catch (UnsupportedEncodingException e) {
                log.error("缓存获取失败 不支持的编码集");
            }
        }
        return Strings.EMPTY;
    }

    /**
     * 获取缓存中的值
     *
     * @param key 键
     * @return 返回键对应存放的值
     */
    public static byte[] getByte(String key) {
        if (StringUtils.isNotEmpty(key)) {
            try {
                return rocks.get(key.getBytes(StandardCharsets.UTF_8));
            } catch (RocksDBException e) {
                log.error("缓存获取失败 异常信息：{}", e.getMessage());
            }
        }
        return null;
    }

    /**
     * 获取缓存中的值
     *
     * @param key 键
     * @return 如果 key 为空返回null，并且在缓存命中失败时返回null
     * 否则返回缓存中key对应的Value值
     */
    public static Map<String, Object> getMap(String key) {
        if (StringUtils.isNotEmpty(key)) {
            try {
                byte[] bytes = rocks.get(key.getBytes(StandardCharsets.UTF_8));
                if (null != bytes) {
                    String json = new String(bytes);
                    return JSON.parseObject(json);
                }
            } catch (RocksDBException e) {
                log.error("缓存获取失败 异常信息：{}", e.getMessage());
            }
        }
        return null;
    }

    /**
     * 获取缓存中的值
     *
     * @param key 键
     * @return 返回键对应存放的值
     */
    public static int getInt(String key) {
        if (StringUtils.isNotEmpty(key)) {
            try {
                byte[] bytes = rocks.get(key.getBytes(StandardCharsets.UTF_8));
                if (null != bytes) {
                    return ByteBuffer.wrap(bytes).getInt();
                }
            } catch (RocksDBException e) {
                log.error("缓存获取失败 异常信息：{}", e.getMessage());
            }
        }
        return -1;
    }

    /**
     * 获取缓存中的值
     *
     * @param key 键
     * @return 返回键对应存放的值
     */
    public static double getDouble(String key) {
        if (StringUtils.isNotEmpty(key)) {
            try {
                byte[] bytes = rocks.get(key.getBytes(StandardCharsets.UTF_8));
                if (null != bytes) {
                    return ByteBuffer.wrap(bytes).getDouble();
                }
            } catch (RocksDBException e) {
                log.error("缓存获取失败 异常信息：{}", e.getMessage());
            }
        }
        return -1;
    }

    /**
     * 删除缓存中存放的值
     *
     * @param key
     */
    public static void remove(String key) {
        if (StringUtils.isNotEmpty(key)) {
            try {
                rocks.delete(key.getBytes(StandardCharsets.UTF_8));
            } catch (RocksDBException e) {
                log.error("缓存删除失败 异常信息：{}", e.getMessage());
            }
        }
    }



}



