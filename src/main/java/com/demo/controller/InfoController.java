package com.demo.controller;

import com.alibaba.fastjson.JSON;
import com.demo.service.InfoService;
import com.demo.vo.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.*;

@Controller
@RequestMapping("/api/info")
public class InfoController {
    @Resource
    private InfoService infoService;

    /**
     * 条件筛选查询
     * @param name
     * @param equityProve
     * @param equityAdress
     * @param area
     * @param application
     * @param status
     * @return
     */
    @RequestMapping(value = "/query")
    @ResponseBody
    @Deprecated
    public List<Info> query(String name, String equityProve,String equityAdress ,String area ,String application,String status){
        List<Info> infoList = infoService.query(name.trim(), equityProve.trim(), equityAdress.trim(), area.trim(), application.trim(), status.trim());
        return infoList;
    }

    /**
     * 查询全部
     * @return
     */
    @RequestMapping(value = "/queryAll")
    @ResponseBody
    public List<Info> queryAll(){
        List<Info> infoList = infoService.queryAll();
        return infoList;
    }


    /**
     * 扫码识别跳转
     * @param id
     * @return
     */
    @RequestMapping(value = "/queryByIDCard")
    public ModelAndView queryByIDCard(String id){
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("pc2");
        modelAndView.addObject("identityCard",id);

        return modelAndView;
    }

    /**
     * 扫码识别跳转
     * @param id
     * @return
     */
    @RequestMapping(value = "/queryByIDCardasdfghqwertyuiojklqwerasdfzxcv")
    public ModelAndView queryByIDCard2(String id){
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("pc2");
        modelAndView.addObject("identityCard",id);

        return modelAndView;
    }


    /**
     * 查询具体信息
     * @param id
     * @return
     */
    @RequestMapping(value = "/queryByID")
    @ResponseBody
    public ResultVo queryByID(String id){
        List<Info> infoList = infoService.queryByIDCard(Long.parseLong(id.trim()));
        String code = infoList.stream().findFirst().get().getCode();
        ResultVo resultVo = new ResultVo();
        if(null != infoList && infoList.size() != 0){
            resultVo.setCdr("");
            resultVo.setCdzjh("");
            resultVo.setCdsj("");
            resultVo.setSuccess("true");
            resultVo.setCdh(code);
            Identity identity = infoService.queryByIdenyityId(Long.parseLong(id));
            if(null != identity){
                String identityCard = identity.getIdCard();
                String identityCardReplace = identityCard.replaceAll("(\\d{6})\\d{6}(\\d{6})", "$1******$2");
                resultVo.setCxrxx(identity.getName() + identityCardReplace);
                resultVo.setBcxr(identity.getName());
                resultVo.setHousenum("true");
            }

            ArrayList<ResultCD> list = new ArrayList<>();
            for(Info info : infoList){
                ResultCD resultCD = new ResultCD();
                resultCD.setQlr(info.getName());
                resultCD.setQzh(info.getEquityProve());
                resultCD.setZl(info.getEquityAdress());
                resultCD.setYt(info.getApplication());
                resultCD.setMj(info.getArea());
                resultCD.setLy("-----");
                resultCD.setZt(info.getStatus());
                list.add(resultCD);
            }
            resultVo.setResultCD(list);
        }
        return resultVo;
    }

    @RequestMapping(value = "/enterInfo")
    @ResponseBody
    public Result addInfo(String info,String name,String code,String idCard) {
        Result result = new Result();
        result.setCode(200);
        if(StringUtils.isBlank(info)){
            result.setMsg("信息为空");
        }else {
            System.out.println( "infoList" + info.toString()  );
            List<EnterVo> enterVoList= JSON.parseArray(info,EnterVo.class);
            infoService.enterInfo(enterVoList,name,code,idCard);
            result.setMsg("录入成功");
        }
        return result;
    }

    /**
     * 根据id删除房子以及用户信息
     * @param id        用户标识
     * @return
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Result deleteStatu(String id){
        Result result = new Result();
        result.setCode(200);
        infoService.updateStatus(Long.parseLong(id));
        result.setMsg("删除成功");
        return result;
    }


    /**
     * 根据id修改房子以及用户信息
     * @param id        用户标识
     * @return
     */
    @RequestMapping(value = "/update")
    public ModelAndView updateStatu(String id){
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("enter");
        modelAndView.addObject("id",id);

        return modelAndView;
    }

    @RequestMapping(value = "/queryByUserID")
    @ResponseBody
    public String queryByUserID(String userId){

        String s = infoService.queryByUserID(userId);
        System.out.println("返回值" + s);

        return s;
    }

    @RequestMapping(value = "/queryIDInfoAndCode")
    @ResponseBody
    public String queryIDInfoAndCode(String userId){
        String s = infoService.queryIDInfoAndCode(userId);
        return s;
    }




}
