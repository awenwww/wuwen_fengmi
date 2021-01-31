package com.qfedu.entity;

public class OrderStatus {
    private Integer sid;
    private String statusName;

    public OrderStatus() {
    }

    public OrderStatus(Integer sid, String statusName) {
        this.sid = sid;
        this.statusName = statusName;
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    @Override
    public String toString() {
        return "OrderStatus{" +
                "sid=" + sid +
                ", statusName='" + statusName + '\'' +
                '}';
    }
}
