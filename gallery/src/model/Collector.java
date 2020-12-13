package model;

public class Collector {

    private long collectorId;
    private String collectorName;
    private String collectorPhoneNumber;
    private String collectorGender;

    public Collector() {
    }

    public Collector(long collectorId, String collectorName, String collectorPhoneNumber, String collectorGender) {
        this.collectorId = collectorId;
        this.collectorName = collectorName;
        this.collectorPhoneNumber = collectorPhoneNumber;
        this.collectorGender = collectorGender;
    }

    public long getCollectorId() {
        return collectorId;
    }

    public void setCollectorId(long collectorId) {
        this.collectorId = collectorId;
    }

    public String getCollectorName() {
        return collectorName;
    }

    public void setCollectorName(String collectorName) {
        this.collectorName = collectorName;
    }

    public String getCollectorPhoneNumber() {
        return collectorPhoneNumber;
    }

    public void setCollectorPhoneNumber(String collectorPhoneNumber) {
        this.collectorPhoneNumber = collectorPhoneNumber;
    }

    public String getCollectorGender() {
        return collectorGender;
    }

    public void setCollectorGender(String collectorGender) {
        this.collectorGender = collectorGender;
    }
}
