package com.demo.vo;

import java.io.Serializable;

public class ResultCD implements Serializable {
    private String qlr;
    private String qzh;
    private String zl;
    private String yt;
    private String mj;
    private String ly;
    private String zt;

    public ResultCD() {
    }

    public ResultCD(String qlr, String qzh, String zl, String yt, String mj, String ly, String zt) {
        this.qlr = qlr;
        this.qzh = qzh;
        this.zl = zl;
        this.yt = yt;
        this.mj = mj;
        this.ly = ly;
        this.zt = zt;
    }

    public String getQlr() {
        return qlr;
    }

    public void setQlr(String qlr) {
        this.qlr = qlr;
    }

    public String getQzh() {
        return qzh;
    }

    public void setQzh(String qzh) {
        this.qzh = qzh;
    }

    public String getZl() {
        return zl;
    }

    public void setZl(String zl) {
        this.zl = zl;
    }

    public String getYt() {
        return yt;
    }

    public void setYt(String yt) {
        this.yt = yt;
    }

    public String getMj() {
        return mj;
    }

    public void setMj(String mj) {
        this.mj = mj;
    }

    public String getLy() {
        return ly;
    }

    public void setLy(String ly) {
        this.ly = ly;
    }

    public String getZt() {
        return zt;
    }

    public void setZt(String zt) {
        this.zt = zt;
    }

    @Override
    public String toString() {
        return "ResultCD{" +
                "qlr='" + qlr + '\'' +
                ", qzh='" + qzh + '\'' +
                ", zl='" + zl + '\'' +
                ", yt='" + yt + '\'' +
                ", mj='" + mj + '\'' +
                ", ly='" + ly + '\'' +
                ", zt='" + zt + '\'' +
                '}';
    }

}
