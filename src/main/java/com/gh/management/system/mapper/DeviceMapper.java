package com.gh.management.system.mapper;

import com.gh.management.system.domain.Device;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DeviceMapper {
    @Select("select * from device")
    List<Device> findAll();

    @Insert("insert into device(id,eid,ip,category,pk,validTime,auxiliaryData) values (#{id},#{eid},#{ip},#{category},#{pk},#{validTime},#{auxiliaryData})")
    int insert(Device device);

    int update(Device device);

    @Delete("delete from device where id=#{id}")
    Integer deleteById(@Param("id") String id);

    @Select("select * from device limit #{pageNum},#{pageSize}")
    List<Device> selectPage(Integer pageNum, Integer pageSize);

    @Select("select count(*) from device ")
    Integer selectTotal();
}
