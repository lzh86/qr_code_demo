package com.demo.dao;

import com.demo.vo.HouseIdentity;
import com.demo.vo.HouseIdentityExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface HouseIdentityMapper {
    long countByExample(HouseIdentityExample example);

    int deleteByExample(HouseIdentityExample example);

    int deleteByPrimaryKey(Long id);

    int insert(HouseIdentity record);

    int insertSelective(HouseIdentity record);

    List<HouseIdentity> selectByExample(HouseIdentityExample example);

    HouseIdentity selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") HouseIdentity record, @Param("example") HouseIdentityExample example);

    int updateByExample(@Param("record") HouseIdentity record, @Param("example") HouseIdentityExample example);

    int updateByPrimaryKeySelective(HouseIdentity record);

    int updateByPrimaryKey(HouseIdentity record);


    List<HouseIdentity> queryByIdentityId(Long identityId);

    int updateStatusById(Long identityId);

    List<HouseIdentity> queryByIdentityIdAndHouseID(@Param("identityId")Long identityId,@Param("houseId")Long houseId);


    List<HouseIdentity> queryByHouseId(Long houseID);


}