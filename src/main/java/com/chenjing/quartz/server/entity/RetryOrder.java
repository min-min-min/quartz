package com.chenjing.quartz.server.entity;

import java.io.Serializable;
import java.util.Date;

public class RetryOrder implements Serializable {
    private Integer id;

    private String orderId;

    private Date expTime;

    private Date createTime;

    private String info;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    public Date getExpTime() {
        return expTime;
    }

    public void setExpTime(Date expTime) {
        this.expTime = expTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info == null ? null : info.trim();
    }

    @Override
    public String toString() {
        return "RetryOrder{" +
                "id=" + id +
                ", orderId='" + orderId + '\'' +
                ", expTime=" + expTime +
                ", createTime=" + createTime +
                ", info='" + info + '\'' +
                '}';
    }
}