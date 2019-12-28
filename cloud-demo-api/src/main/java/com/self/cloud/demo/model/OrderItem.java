package com.self.cloud.demo.model;

import lombok.Data;
import org.apache.commons.pool2.BaseObject;

@Data
public class OrderItem extends BaseObject {
    private String id;

    private String itemId;

    private String orderId;

    private Integer num;

    private String title;

    private Long price;

    private Long totalFee;

    private String picPath;
}