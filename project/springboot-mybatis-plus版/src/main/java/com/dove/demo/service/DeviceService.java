package com.dove.demo.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dove.demo.entity.Device;
import com.dove.demo.mapper.DeviceMapper;
import org.springframework.stereotype.Service;

@Service
public class DeviceService extends ServiceImpl<DeviceMapper, Device> {
    public boolean saveDevice(Device device) {
            return saveOrUpdate(device);
    }
}
