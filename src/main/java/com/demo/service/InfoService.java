package com.demo.service;

import com.demo.vo.EnterVo;
import com.demo.vo.Identity;
import com.demo.vo.Info;

import java.util.List;

public interface InfoService {

    List<Info> query(String name, String equityProve, String equityAdress , String area , String application, String status);

    List<Info> queryByIDCard(Long queryByIDCard);


    /**
     * 根据id删除房子以及用户信息
     * @param identityId        用户标识
     * @return
     */
    String updateStatus(Long identityId);
    /**
     * 查询全部
     * @return
     */
    List<Info> queryAll();

    /**
     * 信息录入
     * @param enterVoList
     * @return
     */
    String enterInfo(List<EnterVo> enterVoList,String name,String code,String idCard);

    Identity queryByIdenyityId(Long identityId);

    String queryByUserID(String userId);

    String queryIDInfoAndCode(String userId);
}
