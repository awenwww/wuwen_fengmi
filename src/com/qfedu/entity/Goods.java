package com.qfedu.entity;


public class Goods {
    private Integer gid;
    private String goodsname;
    private Double price;
    private Integer score;
    private String deployDate;
    private String imgPath;
    private String comment;
    private GoodsType goodsType;

    public Goods() {
    }

    public Goods(Integer gid, String goodsname, Double price, Integer score, String deployDate, String imgPath, String comment, GoodsType goodsType) {
        this.gid = gid;
        this.goodsname = goodsname;
        this.price = price;
        this.score = score;
        this.deployDate = deployDate;
        this.imgPath = imgPath;
        this.comment = comment;
        this.goodsType = goodsType;
    }

    public Integer getGid() {
        return gid;
    }

    public void setGid(Integer gid) {
        this.gid = gid;
    }

    public String getGoodsname() {
        return goodsname;
    }

    public void setGoodsname(String goodsname) {
        this.goodsname = goodsname;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getDeployDate() {
        return deployDate;
    }

    public void setDeployDate(String deployDate) {
        this.deployDate = deployDate;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public GoodsType getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(GoodsType goodsType) {
        this.goodsType = goodsType;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "gid=" + gid +
                ", goodsname='" + goodsname + '\'' +
                ", price=" + price +
                ", score=" + score +
                ", deployDate='" + deployDate + '\'' +
                ", imgPath='" + imgPath + '\'' +
                ", comment='" + comment + '\'' +
                ", goodsType=" + goodsType +
                '}';
    }
}
