package model;

public class Artist {
    private long artistId;
    private String artistName;
    private String artistPhoneNumber;
    private String artistGender;

    public Artist() {
    }

    public Artist(long artistId, String artistName, String artistPhoneNumber, String artistGender) {
        this.artistId = artistId;
        this.artistName = artistName;
        this.artistPhoneNumber = artistPhoneNumber;
        this.artistGender = artistGender;
    }

    public long getArtistId() {
        return artistId;
    }

    public void setArtistId(long artistId) {
        this.artistId = artistId;
    }


    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }


    public String getArtistPhoneNumber() {
        return artistPhoneNumber;
    }

    public void setArtistPhoneNumber(String artistPhoneNumber) {
        this.artistPhoneNumber = artistPhoneNumber;
    }


    public String getArtistGender() {
        return artistGender;
    }

    public void setArtistGender(String artistGender) {
        this.artistGender = artistGender;
    }

}
