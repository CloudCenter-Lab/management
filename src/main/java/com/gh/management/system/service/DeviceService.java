package com.gh.management.system.service;

import com.gh.management.system.mapper.DeviceMapper;
import com.gh.management.system.domain.Device;
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
