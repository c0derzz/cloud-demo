package com.self.cloud.demo.model;

import lombok.Data;
import org.apache.commons.pool2.BaseObject;

import java.util.Date;

@Data
public class ItemCategory extends BaseEntity {

    private  int parentId;
    private String name;
    /**
     * 状态。可选值:1(正常),2(删除)
     */
    private int status;
    private int sortOrder;
    private int isParent;

}
