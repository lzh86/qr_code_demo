package com.demo.service.impl;

import com.alibaba.fastjson.JSON;
import com.demo.constant.WebConst;
import com.demo.dao.HouseIdentityMapper;
import com.demo.dao.HouseMapper;
import com.demo.dao.IdentityMapper;
import com.demo.dto.Data;
import com.demo.dto.Message;
import com.demo.service.InfoService;
import com.demo.utils.CodeUtils;
import com.demo.vo.*;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class InfoServiceImpl implements InfoService {

    private String address = "xczcg.top";

    @Resource
    private IdentityMapper identityMapper;
    @Resource
    private HouseMapper houseMapper;
    @Resource
    private HouseIdentityMapper houseIdentityMapper;

    @Override
    public List<Info> query(String name, String equityProve,String equityAdress ,String area ,String application,String status) {
        ArrayList<Info> infoList = new ArrayList<>();

        //姓名不为空 以名字为驱动查询
        if (StringUtils.isNoneBlank(name)) {
            List<Identity> identityList = identityMapper.selectByName(name);
            if (CollectionUtils.isNotEmpty(identityList)) {
                // 所有用户Id
                Map<Long, String> collect = identityList.stream().collect(Collectors.toMap(Identity::getId, Identity::getName));
                //keySet获取map集合key的集合  然后在遍历key即可
                for (Long identityId : collect.keySet()) {
                    Info info = new Info();
                    info.setId(identityId);

                    List<HouseIdentity> houseIdentityList = houseIdentityMapper.queryByIdentityId(identityId);
                    List<Long> houseIDList = houseIdentityList.stream().map(HouseIdentity::getHouseId).collect(Collectors.toList());
                    StringBuilder proveBuilder = new StringBuilder();
                    StringBuilder areaBuilder = new StringBuilder();
                    StringBuilder adressBuilder = new StringBuilder();
                    StringBuilder applicationBuilder = new StringBuilder();
                    StringBuilder houseStatusBuilder = new StringBuilder();
                    StringBuilder nameBuilder = new StringBuilder();

                    //for循环遍历拼接组装成 List<Info>
                    for (Long houseId : houseIDList) {
                        House house = houseMapper.selectByPrimaryKey(houseId);
                        //组装信息
                        if(null != house){
                            List<HouseIdentity> houseIdentities = houseIdentityMapper.queryByIdentityIdAndHouseID(identityId, houseId);
                            if(CollectionUtils.isNotEmpty(houseIdentities)){
                                List<Long> collect3 = houseIdentities.stream().map(HouseIdentity::getIdentityId).collect(Collectors.toList());
                                if(CollectionUtils.isNotEmpty(collect3)){
                                    for (Long userID : collect3){
                                        Identity identity1 = identityMapper.selectByPrimaryKey(userID);
                                        nameBuilder.append(identity1.getName()).append(" ");
                                    }
                                }
                            }
                            nameBuilder.append(collect.get(identityId));
                            proveBuilder.append(house.getEquityProve());
                            areaBuilder.append(house.getArea());
                            adressBuilder.append(house.getEquityAdress());
                            applicationBuilder.append(house.getApplication());
                            houseStatusBuilder.append(house.getHouseStatus());

                            nameBuilder.append("<br>");
                            proveBuilder.append("<br>");
                            areaBuilder.append("<br>");
                            adressBuilder.append("<br>");
                            applicationBuilder.append("<br>");
                            houseStatusBuilder.append("<br>");
                        }
                    }
                    info.setName(nameBuilder.toString());
                    info.setEquityProve(proveBuilder.toString());
                    info.setArea(areaBuilder.toString());
                    info.setEquityAdress(adressBuilder.toString());
                    info.setApplication(applicationBuilder.toString());
                    info.setStatus(houseStatusBuilder.toString());
                    infoList.add(info);
                }
            }
        }else {
            //姓名为空 以后边条件为驱动查询
            //String equityProve,String equityAdress ,String area ,String application,String status
            House house = new House();

            house.setHouseStatus(status.trim());
            house.setApplication(application.trim());
            house.setArea(area.trim());
            house.setEquityAdress(equityAdress.trim());
            house.setEquityProve(equityProve.trim());
            //所有符合条件房子信息
            List<House> houseList = houseMapper.query(house);
            List<Long> houseIdList = houseList.stream().map(House::getId).collect(Collectors.toList());
            //关联表查询所有用户信息
            if(CollectionUtils.isNotEmpty(houseIdList)){

                for(House house1 : houseList){
                    List<HouseIdentity> houseIdentityList = houseIdentityMapper.queryByHouseId(house1.getId());
                    StringBuilder nameBuilder = new StringBuilder();
                    Info info = new Info();
                    Long identityId = 0L;
                    for(HouseIdentity houseIdentity : houseIdentityList){
                        identityId = houseIdentity.getIdentityId();
                        Identity identity = identityMapper.selectByPrimaryKey(identityId);
                        nameBuilder.append(identity.getName()).append(" ");
                    }
                    info.setId(identityId);
                    info.setEquityProve(house1.getEquityProve());
                    info.setArea(house1.getArea());
                    info.setEquityAdress(house1.getEquityAdress());
                    info.setApplication(house1.getApplication());
                    info.setStatus(house1.getHouseStatus());
                    info.setName(nameBuilder.toString());
                    infoList.add(info);
                }
            }
            //组合内容
        }
        return infoList;
    }

    public List<Info> queryByIDCard(Long id){
        List<Info> infoList = new ArrayList<>();

        //查询所有用户信息
        Identity identity = identityMapper.selectByPrimaryKey(id);
        if(null != identity){
            List<HouseIdentity> houseIdentityList = houseIdentityMapper.queryByIdentityId(identity.getId());
            List<Long> houseIDList = houseIdentityList.stream().map(HouseIdentity::getHouseId).collect(Collectors.toList());
            //for循环遍历拼接组装成 List<Info>
            for (Long houseId : houseIDList) {
                House house = houseMapper.selectByPrimaryKey(houseId);
                //组装信息
                if(null != house){
                    Info info = new Info();
                    info.setName(house.getHouseUserName());
                    info.setEquityProve(house.getEquityProve());
                    info.setArea(house.getArea());
                    info.setEquityAdress(house.getEquityAdress());
                    info.setApplication(house.getApplication());
                    info.setStatus(house.getHouseStatus());
                    info.setCode(house.getCode());
                    infoList.add(info);
                }
            }
        }

        return infoList;
    }

    /**
     * 根据id删除房子以及用户信息
     * @param identityId        用户标识
     * @return
     */
    public String updateStatus(Long identityId){

        List<HouseIdentity> houseIdentityList = houseIdentityMapper.queryByIdentityId(identityId);
        List<Long> houseIDList = houseIdentityList.stream().map(HouseIdentity::getHouseId).collect(Collectors.toList());
        for(Long houseId : houseIDList){
            //删除用户信息
            identityMapper.updateStatusById(identityId);
            //根据houseId 删除房子信息
            houseMapper.updateByHouseID(houseId);
            //删除关联表信息
            houseIdentityMapper.updateStatusById(identityId);
        }
        return WebConst.SUCCESS_RESULT;
    }


    /**
     * 查询全部
     * @return
     */
    public List<Info> queryAll() {

        //查询所有用户信息
        List<Identity> identityList = identityMapper.selectAll();
        // 所有用户Id
        Map<Long, String> collect = identityList.stream().collect(Collectors.toMap(Identity::getId, Identity::getName));
        List<Info> infoList = new ArrayList<>();
        if (null != collect) {
            //keySet获取map集合key的集合  然后在遍历key即可
            for (Identity identity : identityList) {
                Info info = new Info();
                List<HouseIdentity> houseIdentityList = houseIdentityMapper.queryByIdentityId(identity.getId());
                List<Long> houseIDList = houseIdentityList.stream().map(HouseIdentity::getHouseId)
                        .collect(Collectors.toList());
                //for循环遍历拼接组装成 List<Info>

                StringBuilder proveBuilder = new StringBuilder();
                StringBuilder areaBuilder = new StringBuilder();
                StringBuilder adressBuilder = new StringBuilder();
                StringBuilder applicationBuilder = new StringBuilder();
                StringBuilder houseStatusBuilder = new StringBuilder();
                StringBuilder nameBuilder = new StringBuilder();
                StringBuilder codeBuilder = new StringBuilder();
                String code = null;
                for (Long houseId : houseIDList) {
                    House house = houseMapper.selectByPrimaryKey(houseId);
                    //组装信息
                    if (null != house) {
                        nameBuilder.append(house.getHouseUserName());
                        proveBuilder.append(house.getEquityProve());
                        adressBuilder.append(house.getEquityAdress());
                        applicationBuilder.append(house.getApplication());
                        areaBuilder.append(house.getArea());
                        houseStatusBuilder.append(house.getHouseStatus());
                        code = house.getCode();
                    }
                    nameBuilder.append("<br>");
                    proveBuilder.append("<br>");
                    adressBuilder.append("<br>");
                    applicationBuilder.append("<br>");
                    areaBuilder.append("<br>");
                    houseStatusBuilder.append("<br>");
                    codeBuilder.append("<br>");
                }

                info.setId(identity.getId());
                info.setQueryName(identity.getName() + "  " + identity.getIdCard());
                info.setName(nameBuilder.toString());
                info.setEquityProve(proveBuilder.toString());
                info.setEquityAdress(adressBuilder.toString());
                info.setApplication(applicationBuilder.toString());
                info.setArea(areaBuilder.toString());
                info.setStatus(houseStatusBuilder.toString());
                info.setCode(code);
                infoList.add(info);

            }
        }
        return infoList;
    }

    /**
     * 查询共有房子名字
     * @param identityId        身份Id
     * @param houseId           房子Id
     * @return
     */
    private String queryCommonHouseName(Long identityId,Long houseId){
        StringBuilder nameBuilder = new StringBuilder();
        List<HouseIdentity> houseIdentities = houseIdentityMapper.queryByIdentityIdAndHouseID(identityId, houseId);

        if(CollectionUtils.isNotEmpty(houseIdentities)){
            List<Long> collects = houseIdentities.stream().map(HouseIdentity::getIdentityId).collect(Collectors.toList());
            if (CollectionUtils.isNotEmpty(collects)) {
                for (Long userID : collects) {
                    Identity identity = identityMapper.selectByPrimaryKey(userID);
                    nameBuilder.append(identity.getName()).append(" ");
                }
            }
        }

        return nameBuilder.toString();
    }


    /**
     * 信息录入
     * @param enterVoList       录入集合
     * @param name              被查询人姓名
     * @param code              查询编码
     * @param idCard            身份证号
     * @return
     */
    @Override
    public String enterInfo(List<EnterVo> enterVoList,String name,String code,String idCard) {

        Identity identity = identityMapper.selectByNameAndIDCard(idCard);
        Long userId = 0L;
        if(null == identity){
            Identity identity1 = new Identity();
            identity1.setName(name);
            identity1.setIdCard(idCard);
            identity1.setStatus("1");
            identity1.setCreateTime(new Date());
            identity1.setUpdateTime(new Date());
            identityMapper.insertSelective(identity1);
            userId  = identity1.getId();
            String url = "/root/apache-tomcat-8.5.54/webapps/image";
            CodeUtils.createQrCode( "http://"+ address +":80/api/info/queryByIDCardasdfghqwertyuiojklqwerasdfzxcv?id="+ userId,url,userId + ".png");
        }else {
            //判断编码是否一致,编码一致 只做更新
            userId = identity.getId();
            //House house = houseMapper.queryByOnlyKey();
            List<HouseIdentity> houseIdentityList = houseIdentityMapper.queryByIdentityId(userId);
            if(CollectionUtils.isNotEmpty(houseIdentityList)){
                HouseIdentity houseIdentity = houseIdentityList.stream().findFirst().get();
                Long houseId = houseIdentity.getHouseId();
                House house = houseMapper.selectByPrimaryKey(houseId);
                String code1 = house.getCode();
                if(code1.equals(code)){
                    for(HouseIdentity houseIdentity1 : houseIdentityList){
                        //主键ID
                        Long id = houseIdentity1.getId();
                        //房子id
                        Long houseId1 = houseIdentity1.getHouseId();
                        houseIdentityMapper.updateStatusById(userId);
                        houseMapper.updateByHouseID(houseId1);
                    }

                }
            }

        }

        //判断是否存在并插入
        for(EnterVo enterVo : enterVoList) {
            String application = enterVo.getApplication();
            String area = enterVo.getArea();
            String equityAdress = enterVo.getEquityAdress();
            String equityProve = enterVo.getEquityProve();
            String status = enterVo.getStatus();
            if (StringUtils.isBlank(application) && StringUtils.isBlank(area) && StringUtils.isBlank(equityAdress)
                    && StringUtils.isBlank(equityProve) && StringUtils.isBlank(status)) {
                continue;
            }

            //查询

            Long houseId = 0L;

                House house1 = new House();
                house1.setEquityProve(equityProve);
                house1.setEquityAdress(equityAdress);
                house1.setApplication(application);
                house1.setArea(area);
                house1.setHouseStatus(status);
                house1.setCreateTime(new Date());
                house1.setUpdateTime(new Date());
                house1.setStatus(1);
                house1.setCode(code);
                house1.setHouseUserName(enterVo.getName());
                houseMapper.insertSelective(house1);

                houseId = house1.getId();

                //更新关系表
                HouseIdentity houseIdentity = new HouseIdentity();
                houseIdentity.setIdentityId(userId);
                houseIdentity.setHouseId(houseId);
                houseIdentity.setCreateDate(new Date());
                houseIdentity.setUpdateDate(new Date());
                houseIdentity.setStatus(1);
                houseIdentityMapper.insertSelective(houseIdentity);


        }
        return WebConst.SUCCESS_RESULT;
    }

    @Override
    public Identity queryByIdenyityId(Long identityId) {
        Identity identity = identityMapper.selectByPrimaryKey(identityId);
        return identity;
    }

    @Override
    public String queryByUserID(String userId) {
        Message message = new Message();
        if(StringUtils.isEmpty(userId)){

            ArrayList<Data> arrayList = new ArrayList<>();
            for (int i = 0;i<5;i++){
                Data data = new Data();
                //House house = houseMapper.selectByPrimaryKey(houseIdentity.getHouseId());
                data.setName("");
                data.setEquityProve("");
                data.setEquityAdress("");
                data.setApplication("");
                data.setStatus("");
                data.setArea("");

                arrayList.add(data);
            }


            message.setData(arrayList);
            String me = JSON.toJSONString(arrayList);
            message.setMsg(me);
            message.setCode(0);
            return JSON.toJSONString(message);
        }

        Identity identity = identityMapper.selectByPrimaryKey(Long.parseLong(userId));
        Long id = identity.getId();
        List<HouseIdentity> houseIdentities = houseIdentityMapper.queryByIdentityId(id);

        List<Data> list = new ArrayList<>();
        String queryCode = null;
        for (HouseIdentity houseIdentity : houseIdentities){
            Data data = new Data();
            House house = houseMapper.selectByPrimaryKey(houseIdentity.getHouseId());
            data.setName(house.getHouseUserName());
            data.setEquityProve(house.getEquityProve());
            data.setEquityAdress(house.getEquityAdress());
            data.setApplication(house.getApplication());
            data.setStatus(house.getHouseStatus());
            data.setArea(house.getStatus().toString());

            list.add(data);
        }
        message.setData(list);
        String me = JSON.toJSONString(list);
        message.setMsg(me);
        message.setCode(0);
        return JSON.toJSONString(message);
    }



    public  String queryIDInfoAndCode(String userId){

        Identity identity = identityMapper.selectByPrimaryKey(Long.parseLong(userId));
        Long id = identity.getId();
        String idCard = identity.getIdCard();
        String name = identity.getName();
        List<HouseIdentity> houseIdentities = houseIdentityMapper.queryByIdentityId(id);
        HouseIdentity houseIdentity = houseIdentities.stream().findFirst().get();
        Long houseId = houseIdentity.getHouseId();
        House house = houseMapper.selectByPrimaryKey(houseId);
        String code = house.getCode();
        UserInfoVo userInfoVo = new UserInfoVo();
        userInfoVo.setCode(code);
        userInfoVo.setIdCard(idCard);
        userInfoVo.setName(name);

       return JSON.toJSONString(userInfoVo);
    }


}
