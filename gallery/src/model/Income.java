package model;

import java.util.Date;

public class Income {
    private long incomeId;
    private long customerId;
    private long artistId;
    private long artworkId;
    private long employeeId;
    private Double receiveMoney;
    private Date createTime;
    private String incomeType;
    private String employeeName;
    private String artworkName;
    private String artistName;

    public Income() {
    }

    public Income(long incomeId, long customerId, long artistId, long artworkId, long employeeId, Double receiveMoney, Date createTime, String incomeType) {
        this.incomeId = incomeId;
        this.customerId = customerId;
        this.artistId = artistId;
        this.artworkId = artworkId;
        this.employeeId = employeeId;
        this.receiveMoney = receiveMoney;
        this.createTime = createTime;
        this.incomeType = incomeType;
    }

    public long getIncomeId() {
        return incomeId;
    }

    public void setIncomeId(long incomeId) {
        this.incomeId = incomeId;
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

    public Double getReceiveMoney() {
        return receiveMoney;
    }

    public void setReceiveMoney(Double receiveMoney) {
        this.receiveMoney = receiveMoney;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getIncomeType() {
        return incomeType;
    }

    public void setIncomeType(String incomeType) {
        this.incomeType = incomeType;
    }

    public long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(long employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getArtworkName() {
        return artworkName;
    }

    public void setArtworkName(String artworkName) {
        this.artworkName = artworkName;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }
}
