package com.self.cloud.demo.model;

import lombok.Data;


@Data
public class OrderShipping extends BaseEntity {

    private String orderId;


    private String receiverName;


    private String receiverPhone;


    private String receiverMobile;


    private String receiverState;


    private String receiverCity;


    private String receiverDistrict;


    private String receiverAddress;


    private String receiverZip;

}