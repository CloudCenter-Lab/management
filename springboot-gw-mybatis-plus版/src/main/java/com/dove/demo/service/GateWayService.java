package com.dove.demo.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dove.demo.entity.GateWay;
import com.dove.demo.mapper.GateWayMapper;
import org.springframework.stereotype.Service;

@Service
public class GateWayService extends ServiceImpl<GateWayMapper, GateWay> {
    public boolean saveGateWay(GateWay gateWay) {
            return saveOrUpdate(gateWay);
    }
}
