package com.dove.demo.service;

import com.dove.demo.entity.Device;
import com.dove.demo.mapper.DeviceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeviceService {
    @Autowired
    private DeviceMapper deviceMapper;

    public int save(Device device){
        if(device.getId()==null){
            return deviceMapper.insert(device);
        }else{
            return deviceMapper.update(device);
        }
    }
}
