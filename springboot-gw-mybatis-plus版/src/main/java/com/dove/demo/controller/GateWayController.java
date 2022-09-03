package com.dove.demo.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dove.demo.entity.GateWay;
import com.dove.demo.service.GateWayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/gateway")
public class GateWayController {

    @Autowired
    private GateWayService gateWayService;

    //添加或修改
    @PostMapping
    public boolean save(@RequestBody GateWay gateWay){
        return gateWayService.saveGateWay(gateWay);
    }

    //查询所有
    @GetMapping
    public List<GateWay> findAll(){
        return gateWayService.list();
    }

    //根据id删除
    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable Integer id){
        return gateWayService.removeById(id);
    }

    //mybatis-plus的方式实现分页查询
    @GetMapping("/page")
    public IPage<GateWay> findPage(@RequestParam Integer pageNum,
                                   @RequestParam Integer pageSize){
        IPage<GateWay> page=new Page<>(pageNum,pageSize);
        return gateWayService.page(page);
    }
}
