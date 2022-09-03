package com.dove.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "gateway")
public class GateWay {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    private String gid;
    private String ip;
    private String category;
    private String pk;
    private String sk;
    private String skp;
    @TableField(value ="validTime" )
    private Long validTime;
    @TableField(value = "auxiliaryData")
    private String auxiliaryData;
}
