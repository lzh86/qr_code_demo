package com.demo.dao;

import com.demo.vo.Identity;
import com.demo.vo.IdentityExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface IdentityMapper {
    long countByExample(IdentityExample example);

    int deleteByExample(IdentityExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Identity record);

    int insertSelective(Identity record);

    List<Identity> selectByExample(IdentityExample example);

    Identity selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Identity record, @Param("example") IdentityExample example);

    int updateByExample(@Param("record") Identity record, @Param("example") IdentityExample example);

    int updateByPrimaryKeySelective(Identity record);

    int updateByPrimaryKey(Identity record);



    List<Identity> selectByHouseID(Long houseId);

    int updateStatusById(Long identityId);

    List<Identity> selectAll();

    Identity selectByIDCard(String idCard);

    List<Identity> selectByName(String userName);

    /**
     * 根据被查人身份查询信息
     * @param id_card
     * @return
     */
    Identity selectByNameAndIDCard(String id_card);


}