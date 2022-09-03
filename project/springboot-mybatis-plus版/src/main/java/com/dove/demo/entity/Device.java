package com.dove.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@TableName(value = "device")
@ToString
public class Device {

    @TableId(type = IdType.AUTO)
    private Integer id;
    private String eid;
    private String ip;
    private String category;
    private String pk;
    @TableField(value ="validTime" )
    private Long validTime;
    @TableField(value = "auxiliaryData")
    private String auxiliaryData;

}
