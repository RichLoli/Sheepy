package com.macoloco.sheepy.common.entity;

import com.macoloco.sheepy.util.IdGen;
import lombok.Data;
import lombok.Getter;

/**
 * @author Aya
 * @date 2022/1/16
 */
@Getter
public class BaseEntity {

    private String id;

    private String createtime;

    private String updatetime;

    public BaseEntity() {
        this.id = IdGen.uuid();
    }

    public void setId(String id) {
        this.id = id;
    }
}
