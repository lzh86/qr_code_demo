package com.demo.vo;

import java.io.Serializable;
import java.util.List;

public class ResultVo implements Serializable {
    private String cdr;
    private String cdzjh;
    private String cdsj;
    private String success;
    private String cdh;
    private String cxrxx;
    private String bcxr;
    private List<ResultCD> resultCD;
    private String housenum;
    private String reason;

    public ResultVo() {
    }

    public ResultVo(String cdr, String cdzjh, String cdsj, String success, String cdh, String cxrxx, String bcxr, List<ResultCD> resultCD, String housenum, String reason) {
        this.cdr = cdr;
        this.cdzjh = cdzjh;
        this.cdsj = cdsj;
        this.success = success;
        this.cdh = cdh;
        this.cxrxx = cxrxx;
        this.bcxr = bcxr;
        this.resultCD = resultCD;
        this.housenum = housenum;
        this.reason = reason;
    }

    public String getCdr() {
        return cdr;
    }

    public void setCdr(String cdr) {
        this.cdr = cdr;
    }

    public String getCdzjh() {
        return cdzjh;
    }

    public void setCdzjh(String cdzjh) {
        this.cdzjh = cdzjh;
    }

    public String getCdsj() {
        return cdsj;
    }

    public void setCdsj(String cdsj) {
        this.cdsj = cdsj;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getCdh() {
        return cdh;
    }

    public void setCdh(String cdh) {
        this.cdh = cdh;
    }

    public String getCxrxx() {
        return cxrxx;
    }

    public void setCxrxx(String cxrxx) {
        this.cxrxx = cxrxx;
    }

    public String getBcxr() {
        return bcxr;
    }

    public void setBcxr(String bcxr) {
        this.bcxr = bcxr;
    }

    public List<ResultCD> getResultCD() {
        return resultCD;
    }

    public void setResultCD(List<ResultCD> resultCD) {
        this.resultCD = resultCD;
    }

    public String getHousenum() {
        return housenum;
    }

    public void setHousenum(String housenum) {
        this.housenum = housenum;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @Override
    public String toString() {
        return "ResultVo{" +
                "cdr='" + cdr + '\'' +
                ", cdzjh='" + cdzjh + '\'' +
                ", cdsj='" + cdsj + '\'' +
                ", success='" + success + '\'' +
                ", cdh='" + cdh + '\'' +
                ", cxrxx='" + cxrxx + '\'' +
                ", bcxr='" + bcxr + '\'' +
                ", resultCD=" + resultCD +
                ", housenum='" + housenum + '\'' +
                ", reason='" + reason + '\'' +
                '}';
    }
}


