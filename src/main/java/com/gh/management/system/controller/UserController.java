package com.gh.management.system.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gh.management.system.domain.User;
import com.gh.management.system.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.List;

/**
 * @author YJL
 * @create 2022-09-18 14:45
 */
@RestController
@RequestMapping("/user")
@Api(tags = "用户管理接口")
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping
    @ApiOperation("新增或修改用户")
    public boolean saveOrUpdate(@RequestBody User user) { return userService.saveOrUpdate(user); }


    @DeleteMapping("/{id}")
    @ApiOperation("删除id")
    public Boolean delete(@PathVariable Integer id) { return userService.removeById(id); }

    @PostMapping("/del/batch")
    @ApiOperation("批量删除")
    public boolean deleteBatch(@RequestBody List<Integer> ids) {
        return userService.removeByIds(ids);
    }

    @GetMapping
    @ApiOperation("查询所有用户")
    public List<User> findAll() {
        return userService.list();
    }

    @GetMapping("/{id}")
    @ApiOperation("使用id查询单个用户")
    public User findOne(@PathVariable Integer id) {
        return userService.getById(id);
    }

    @GetMapping("/{pageNum}/{pageSize}")
    @ApiOperation("分页")
    public Page<User> findPage(@PathVariable Integer pageNum,
                               @PathVariable Integer pageSize){
//        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
//        queryWrapper.orderByAsc("id")
//                .like(StringUtils.isNotBlank(username), "username", username)
//                .like(StringUtils.isNotBlank(email), "email", email)
//                .like(StringUtils.isNotBlank(address), "address", address);
        return userService.page(new Page<>(pageNum, pageSize), null);

    }


//    /**
//     * 导出接口
//     */
//    @GetMapping("/export")
//    public void export(HttpServletResponse response) throws Exception {
//        // 从数据库查询出所有的数据
//        List<User> list = userService.list();
//        // 通过工具类创建writer 写出到磁盘路径
////        ExcelWriter writer = ExcelUtil.getWriter(filesUploadPath + "/用户信息.xlsx");
//        // 在内存操作，写出到浏览器
//        ExcelWriter writer = ExcelUtil.getWriter(true);
//        //自定义标题别名
//        writer.addHeaderAlias("username", "用户名");
//        writer.addHeaderAlias("password", "密码");
//        writer.addHeaderAlias("sex", "性别");
//        writer.addHeaderAlias("email", "邮箱");
//        writer.addHeaderAlias("phone", "电话");
//        writer.addHeaderAlias("address", "地址");
//        writer.addHeaderAlias("available", "是否可用");
//        writer.addHeaderAlias("avatar", "头像");
//        writer.addHeaderAlias("createdTime", "创建时间");
//        writer.addHeaderAlias("createdUser", "创建人");
//        // 一次性写出list内的对象到excel，使用默认样式，强制输出标题
//        writer.write(list, true);
//        // 设置浏览器响应的格式
//        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
//        String fileName = URLEncoder.encode("用户信息", "UTF-8");
//        response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ".xlsx");
//        ServletOutputStream out = response.getOutputStream();
//        writer.flush(out, true);
//        out.close();
//        writer.close();
//    }
//
//
//    /**
//     * excel 导入
//     * @param file
//     * @throws Exception
//     */
//    @PostMapping("/import")
//    public Boolean imp(MultipartFile file) throws Exception {
//        InputStream inputStream = file.getInputStream();
//        ExcelReader reader = ExcelUtil.getReader(inputStream);
//        List<List<Object>> list = reader.read(1);
//        List<User> users = CollUtil.newArrayList();
//        for (List<Object> row : list) {
//            User user = new User();
//            user.setUsername(row.get(0).toString());
//            user.setPassword(row.get(1).toString());
//            user.setSex(row.get(2).toString());
//            user.setEmail(row.get(3).toString());
//            user.setPhone(row.get(4).toString());
//            user.setAddress(row.get(5).toString());
//            user.setAvatar(row.get(7).toString());
//            users.add(user);
//        }
//        userService.saveBatch(users);
//        return true;
//    }


}
