package com.dove.demo.mapper;

import com.dove.demo.entity.GateWay;
import org.apache.ibatis.annotations.*;

import java.util.List;
@Mapper
public interface GateWayMapper {
    @Select("select * from gateway")
    List<GateWay> findAll();

    @Insert("insert into gateway(id,gid,ip,category,pk,sk,skp,validTime,auxiliaryData) values (#{id},#{gid},#{ip},#{category},#{pk},#{sk},#{skp},#{validTime},#{auxiliaryData})")
    int insert(GateWay gateWay);

    int update(GateWay gateWay);

    @Delete("delete from gateway where id=#{id}")
    Integer deleteById(@Param("id") String id);

    @Select("select * from gateway limit #{pageNum},#{pageSize}")
    List<GateWay> selectPage(Integer pageNum, Integer pageSize);

    @Select("select count(*) from gateway ")
    Integer selectTotal();
}
