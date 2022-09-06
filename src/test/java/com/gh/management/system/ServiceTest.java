package com.gh.management.system;

import com.gh.management.system.pojo.Device;
import com.gh.management.system.pojo.User;
import com.gh.management.system.service.DeviceService;
import com.gh.management.system.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author YJL
 * @create 2022-08-26 15:31
 */
@SpringBootTest
public class ServiceTest {

    @Autowired
    private UserService userService;
    @Autowired
    private DeviceService deviceService;
    @Test
    public void save() {
//        User user1=new User();
//        user1.setUsername("www");
//        user1.setPassword("qqqwww");
//        user1.setAddress("qqq");
//        user1.setPhone("qqq");
//        user1.setEmail("qqq");
//        user1.setSex(SexEnum.FEMALE);
//        boolean b = userService.save(user1);
//        System.out.println(b);
//        boolean b = userService.removeById(14);
//        System.out.println(b);
        Device device=new Device();
        device.setCategory("1");
        device.setAuxiliaryData("hd");
        device.setId(1);
        device.setEid("eid1");
        device.setIp("192.168.1.2");
        device.setPk("pk");
        device.setValidTime(System.currentTimeMillis());
        deviceService.save(device);

//        List<User> list = userService.list();
//        list.forEach(System.out::println);

    }



}
