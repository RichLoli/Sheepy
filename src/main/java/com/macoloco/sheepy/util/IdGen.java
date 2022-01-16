package com.macoloco.sheepy.util;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;

/**
 * ID生成工具类
 * @author Aya
 * @date 2022/1/16
 */
public class IdGen {

    /**
     * 机器ID
     */
    private static final long WORKER_ID = 1;

    /**
     * 数据中心ID
     */
    private static final long DATACENTER_ID = 1;

    /**
     * 雪花算法生成器
     */
    private static final Snowflake SNOWFLAKE = IdUtil.getSnowflake(WORKER_ID, DATACENTER_ID);

    /**
     * 获取一个ID
     * @return
     */
    public static String uuid() {
        return SNOWFLAKE.nextIdStr();
    }

}
