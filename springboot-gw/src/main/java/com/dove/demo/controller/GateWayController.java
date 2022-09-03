package com.dove.demo.controller;

import com.dove.demo.entity.GateWay;
import com.dove.demo.mapper.GateWayMapper;
import com.dove.demo.service.GateWayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/gateway")
public class GateWayController {
    @Autowired
    private GateWayMapper gateWayMapper;

    @Autowired
    private GateWayService gateWayService;

    //添加或修改
    @PostMapping
    public Integer save(@RequestBody GateWay gateWay){
        return gateWayService.save(gateWay);
    }

    //查询所有信息
    @GetMapping
    public List<GateWay> findAll(){
        return gateWayMapper.findAll();
    }

    //根据id删除
    @DeleteMapping("/{id}")
    public Integer delete(@PathVariable String id){
        return gateWayMapper.deleteById(id);
    }

    //分页查询
    //@RequestParam 用于将参数列表中传递参数映射给形参
    //limit第一个参数=(pageNum-1)*pageSize
    @GetMapping("/page")
    public Map<String,Object> findPage(@RequestParam Integer pageNum,
                                       @RequestParam Integer pageSize){
        pageNum=(pageNum-1)*pageSize;
        List<GateWay> data= gateWayMapper.selectPage(pageNum,pageSize);
        Integer total = gateWayMapper.selectTotal();
        Map<String,Object> res=new HashMap<String,Object>();
        res.put("data",data);
        res.put("total",total);
        return res;
    }
    
}
