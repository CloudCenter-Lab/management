package com.dove.demo.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dove.demo.entity.Device;
import com.dove.demo.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.List;

@RestController
@RequestMapping("/device")
public class DeviceController {

    @Autowired
    private DeviceService deviceService;

    //添加或修改
    @PostMapping
    public boolean save(@RequestBody Device device){
        return deviceService.saveDevice(device);
    }

    //查询所有
    @GetMapping
    public List<Device> findAll(){
        return deviceService.list();
    }

    //根据id删除
    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable Integer id){
        return deviceService.removeById(id);
    }

    //mtbatis-plus的方式实现分页查询
    @GetMapping("/page")
    public IPage<Device> findPage(@RequestParam Integer pageNum,
                                  @RequestParam Integer pageSize){
        IPage<Device> page=new Page<>(pageNum,pageSize);
        return deviceService.page(page);
    }

    /**
     * 导出接口
     */
    @GetMapping("/export")
    public void export(HttpServletResponse response) throws Exception {
        // 从数据库查询出所有的数据
        List<Device> list = deviceService.list();
        // 通过工具类创建writer 写出到磁盘路径
//        ExcelWriter writer = ExcelUtil.getWriter(filesUploadPath + "/用户信息.xlsx");
        // 在内存操作，写出到浏览器
        ExcelWriter writer = ExcelUtil.getWriter(true);
        //自定义标题别名
        writer.addHeaderAlias("eid", "设备序号");
        writer.addHeaderAlias("ip", "ip地址");
        writer.addHeaderAlias("category", "设备类型");
        writer.addHeaderAlias("pk", "公钥");
        writer.addHeaderAlias("createTime", "创建时间");
        writer.addHeaderAlias("validTime", "有效时间");
        writer.addHeaderAlias("auxiliaryData", "辅助数据");

        // 一次性写出list内的对象到excel，使用默认样式，强制输出标题
        writer.write(list, true);

        // 设置浏览器响应的格式
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        String fileName = URLEncoder.encode("设备信息", "UTF-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ".xlsx");

        ServletOutputStream out = response.getOutputStream();
        writer.flush(out, true);
        out.close();
        writer.close();

    }

    /**
     * excel 导入
     * @param file
     * @throws Exception
     */
    @PostMapping("/import")
    public boolean imp(MultipartFile file) throws IOException {
        InputStream inputStream = file.getInputStream();
        ExcelReader reader = ExcelUtil.getReader(inputStream);
        // 方式1：(推荐) 通过 javabean的方式读取Excel内的对象，但是要求表头必须是英文，跟javabean的属性要对应起来
        List<Device> list = reader.readAll(Device.class);

        // 方式2：忽略表头的中文，直接读取表的内容
//        List<List<Object>> list = reader.read(1);
//        List<Device> devices = CollUtil.newArrayList();
//        for (List<Object> row : list) {
//            Device device = new Device();
//            device.setEid(row.get(0).toString());
//            device.setIp(row.get(1).toString());
//            device.setCategory(row.get(2).toString());
//            device.setPk(row.get(3).toString());
//            device.setValidTime(row.get(4).hashCode());
//            device.setAuxiliaryData(row.get(5).toString());
//            devices.add(device);
//        }
        deviceService.saveBatch(list);
        return true;
    }
}
