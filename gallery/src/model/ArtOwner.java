package model;

public class ArtOwner {

    private long ownerId;
    private long artworkId;
    private String ownType;
    private String ownersId;


    public ArtOwner() {
    }

    public ArtOwner(long ownerId, long artworkId, String ownType, String ownersId) {
        this.ownerId = ownerId;
        this.artworkId = artworkId;
        this.ownType = ownType;
        this.ownersId = ownersId;
    }

    public long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(long ownerId) {
        this.ownerId = ownerId;
    }

    public String getOwnType() {
        return ownType;
    }

    public void setOwnType(String ownType) {
        this.ownType = ownType;
    }

    public String getOwnersId() {
        return ownersId;
    }

    public void setOwnersId(String ownersId) {
        this.ownersId = ownersId;
    }

    public long getArtworkId() {
        return artworkId;
    }

    public void setArtworkId(long artworkId) {
        this.artworkId = artworkId;
    }
}
