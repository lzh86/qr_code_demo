package com.demo.vo;

import java.util.Date;

public class House {
    private Long id;

    private String equityProve;

    private String equityAdress;

    private String application;

    private String area;

    private Date createTime;

    private Date updateTime;

    private String code;

    private String houseUserName;

    private String houseStatus;

    private Integer status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEquityProve() {
        return equityProve;
    }

    public void setEquityProve(String equityProve) {
        this.equityProve = equityProve == null ? null : equityProve.trim();
    }

    public String getEquityAdress() {
        return equityAdress;
    }

    public void setEquityAdress(String equityAdress) {
        this.equityAdress = equityAdress == null ? null : equityAdress.trim();
    }

    public String getApplication() {
        return application;
    }

    public void setApplication(String application) {
        this.application = application == null ? null : application.trim();
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area == null ? null : area.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getHouseUserName() {
        return houseUserName;
    }

    public void setHouseUserName(String houseUserName) {
        this.houseUserName = houseUserName == null ? null : houseUserName.trim();
    }

    public String getHouseStatus() {
        return houseStatus;
    }

    public void setHouseStatus(String houseStatus) {
        this.houseStatus = houseStatus == null ? null : houseStatus.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}