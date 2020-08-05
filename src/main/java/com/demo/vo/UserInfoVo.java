package com.demo.vo;

import java.io.Serializable;

public class UserInfoVo implements Serializable {
    private String name;
    private String idCard;
    private String code;

    @Override
    public String toString() {
        return "UserInfoVo{" +
                "name='" + name + '\'' +
                ", idCard='" + idCard + '\'' +
                ", code='" + code + '\'' +
                '}';
    }

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
