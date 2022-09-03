package com.dove.demo.entity;

import lombok.Data;

@Data
public class Device {
    private Integer id;
    private String eid;
    private String ip;
    private String category;
    private String pk;
    private Long validTime;
    private String auxiliaryData;
}
