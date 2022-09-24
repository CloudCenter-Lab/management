package com.gh.management.system.controller;


import com.gh.management.system.mapper.DeviceMapper;
import com.gh.management.system.domain.Device;
import com.gh.management.system.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/device")
public class DeviceController {
    @Autowired
    private DeviceMapper deviceMapper;

    @Autowired
    private DeviceService deviceService;

    //添加或修改
    @PostMapping
    public Integer save(@RequestBody Device device){
        return deviceService.save(device);
    }

    //查询所有信息
    @GetMapping
    public List<Device> findAll(){
        return deviceMapper.findAll();
    }

    //根据id删除
    @DeleteMapping("/{id}")
    public Integer delete(@PathVariable String id){
        return deviceMapper.deleteById(id);
    }

    //分页查询
    //@RequestParam 用于将参数列表中传递参数映射给形参
    //limit第一个参数=(pageNum-1)*pageSize
    @GetMapping("/page")
    public Map<String,Object> findPage(@RequestParam Integer pageNum,
                                       @RequestParam Integer pageSize){
        pageNum=(pageNum-1)*pageSize;
        List<Device> data= deviceMapper.selectPage(pageNum,pageSize);
        Integer total = deviceMapper.selectTotal();
        Map<String,Object> res=new HashMap<String,Object>();
        res.put("data",data);
        res.put("total",total);
        return res;
    }
}
