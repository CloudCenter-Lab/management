package com.dove.demo.service;


import com.dove.demo.entity.GateWay;
import com.dove.demo.mapper.GateWayMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GateWayService {
    @Autowired
    private GateWayMapper gateWayMapper;
    //根据数据库中有无id来判断是新增还是更新
    public int save(GateWay gateWay){
        if(gateWay.getId()==null){
            return gateWayMapper.insert(gateWay);
        }else{
            return gateWayMapper.update(gateWay);
        }
    }
}
