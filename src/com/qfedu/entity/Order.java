package com.qfedu.entity;

public class Order {
    private Integer id;
    private String orderCode;
    private Double totalPrice;
    private String createDate;
    private Integer uid;
    private Integer status;
    private User user;
    private OrderStatus orderStatus;

    public Order() {
    }

    public Order(Integer id, String orderCode, Double totalPrice, String createDate, Integer uid, Integer status, User user, OrderStatus orderStatus) {
        this.id = id;
        this.orderCode = orderCode;
        this.totalPrice = totalPrice;
        this.createDate = createDate;
        this.uid = uid;
        this.status = status;
        this.user = user;
        this.orderStatus = orderStatus;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", orderCode='" + orderCode + '\'' +
                ", totalPrice=" + totalPrice +
                ", createDate='" + createDate + '\'' +
                ", uid=" + uid +
                ", status=" + status +
                ", user=" + user +
                ", orderStatus=" + orderStatus +
                '}';
    }
}
