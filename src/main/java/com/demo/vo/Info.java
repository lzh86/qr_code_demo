package com.demo.vo;

public class Info {
    private Long id;

    private String name;

    private String identityCard;

    private String equityProve;

    private String equityAdress;

    private String application;

    private String area;

    private String status;

    private Integer statu;

    private String code;

    private String queryName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getIdentityCard() {
        return identityCard;
    }

    public void setIdentityCard(String identityCard) {
        this.identityCard = identityCard == null ? null : identityCard.trim();
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Integer getStatu() {
        return statu;
    }

    public void setStatu(Integer statu) {
        this.statu = statu;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getQueryName() {
        return queryName;
    }

    public void setQueryName(String queryName) {
        this.queryName = queryName;
    }

    @Override
    public String toString() {
        return "Info{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", identityCard='" + identityCard + '\'' +
                ", equityProve='" + equityProve + '\'' +
                ", equityAdress='" + equityAdress + '\'' +
                ", application='" + application + '\'' +
                ", area='" + area + '\'' +
                ", status='" + status + '\'' +
                ", statu=" + statu +
                ", code='" + code + '\'' +
                ", queryName='" + queryName + '\'' +
                '}';
    }
}