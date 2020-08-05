package com.demo.dao;

import com.demo.vo.House;
import com.demo.vo.HouseExample;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface HouseMapper {
    long countByExample(HouseExample example);

    int deleteByExample(HouseExample example);

    int deleteByPrimaryKey(Long id);

    int insert(House record);

    int insertSelective(House record);

    List<House> selectByExample(HouseExample example);

    House selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") House record, @Param("example") HouseExample example);

    int updateByExample(@Param("record") House record, @Param("example") HouseExample example);

    int updateByPrimaryKeySelective(House record);

    int updateByPrimaryKey(House record);

    int updateByHouseID(Long id);

    List<House> selectAll();

    House queryByOnlyKey(@Param("equityProve")String equityProve,@Param("equityAdress")String equityAdress,
                         @Param("application")String application,@Param("area")String area,
                         @Param("code")String code);

    List<House> query(House record);


}