package model;

import java.util.Date;

public class Artwork {

    private long artworkId;
    private String artworkName;
    private String price;
    private String description;
    private String type;
    private Date dateReceived;
    private String status;

    public Artwork() {
    }

    public Artwork(long artworkId, String artworkName, String price, String description, String type, Date dateReceived, String status) {
        this.artworkId = artworkId;
        this.artworkName = artworkName;
        this.price = price;
        this.description = description;
        this.type = type;
        this.dateReceived = dateReceived;
        this.status = status;
    }

    public long getArtworkId() {
        return artworkId;
    }

    public void setArtworkId(long artworkId) {
        this.artworkId = artworkId;
    }


    public String getArtworkName() {
        return artworkName;
    }

    public void setArtworkName(String artworkName) {
        this.artworkName = artworkName;
    }


    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public Date getDateReceived() {
        return dateReceived;
    }

    public void setDateReceived(Date dateReceived) {
        this.dateReceived = dateReceived;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
