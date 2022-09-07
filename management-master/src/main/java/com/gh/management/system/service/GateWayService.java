package com.gh.management.system.service;

import com.gh.management.system.mapper.GateWayMapper;
import com.gh.management.system.pojo.GateWay;
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
