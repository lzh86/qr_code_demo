package com.demo.vo;

public class EnterVo {
    private String name;
    private String idCard;
    private String equityProve;
    private String equityAdress;
    private String application;
    private String area;
    private String status;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getEquityProve() {
        return equityProve;
    }

    public void setEquityProve(String equityProve) {
        this.equityProve = equityProve;
    }

    public String getEquityAdress() {
        return equityAdress;
    }

    public void setEquityAdress(String equityAdress) {
        this.equityAdress = equityAdress;
    }

    public String getApplication() {
        return application;
    }

    public void setApplication(String application) {
        this.application = application;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "EnterVo{" +
                "name='" + name + '\'' +
                ", idCard='" + idCard + '\'' +
                ", equityProve='" + equityProve + '\'' +
                ", equityAdress='" + equityAdress + '\'' +
                ", application='" + application + '\'' +
                ", area='" + area + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
