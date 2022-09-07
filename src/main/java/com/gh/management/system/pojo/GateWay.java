package com.gh.management.system.pojo;

import lombok.Data;

@Data
public class GateWay {
    private Integer id;
    private String gid;
    private String ip;
    private String category;
    private String pk;
    private String sk;
    private String skp;
    private Long validTime;
    private String auxiliaryData;
}
