package model;

import java.util.Date;

public class Bill {

    private long billId;
    private long customerId;
    private long artistId;
    private long artworkId;
    private Double payMoney;
    private Date createTime;

    public Bill() {
    }

    public Bill(long billId, long customerId, long artistId, long artworkId, Double payMoney, Date createTime) {
        this.billId = billId;
        this.customerId = customerId;
        this.artistId = artistId;
        this.artworkId = artworkId;
        this.payMoney = payMoney;
        this.createTime = createTime;
    }

    public long getBillId() {
        return billId;
    }

    public void setBillId(long billId) {
        this.billId = billId;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public long getArtistId() {
        return artistId;
    }

    public void setArtistId(long artistId) {
        this.artistId = artistId;
    }

    public long getArtworkId() {
        return artworkId;
    }

    public void setArtworkId(long artworkId) {
        this.artworkId = artworkId;
    }

    public Double getPayMoney() {
        return payMoney;
    }

    public void setPayMoney(Double payMoney) {
        this.payMoney = payMoney;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
