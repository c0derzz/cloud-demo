package com.self.cloud.demo.model;

import lombok.Data;
import org.apache.commons.pool2.BaseObject;

import java.util.Date;

@Data
public class ReItem extends BaseEntity {
    private String title;
    private String sellPoint;
    private int price;
    private int num;
    private String barcode;
    private String image;
    private int cid;
    private int status;
    private Date recovered;
    private String recoveredStr;
}