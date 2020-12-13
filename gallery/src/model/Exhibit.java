package model;

import java.util.Date;

public class Exhibit {

    private long exhibitId;
    private String exhibitName;
    private String exhibitTheme;
    private Date exhibitBeginDate;
    private long exhibitTime;

    public long getExhibitId() {
        return exhibitId;
    }

    public void setExhibitId(long exhibitId) {
        this.exhibitId = exhibitId;
    }


    public String getExhibitName() {
        return exhibitName;
    }

    public void setExhibitName(String exhibitName) {
        this.exhibitName = exhibitName;
    }


    public String getExhibitTheme() {
        return exhibitTheme;
    }

    public void setExhibitTheme(String exhibitTheme) {
        this.exhibitTheme = exhibitTheme;
    }


    public Date getExhibitBeginDate() {
        return exhibitBeginDate;
    }

    public void setExhibitBeginDate(Date exhibitBeginDate) {
        this.exhibitBeginDate = exhibitBeginDate;
    }


    public long getExhibitTime() {
        return exhibitTime;
    }

    public void setExhibitTime(long exhibitTime) {
        this.exhibitTime = exhibitTime;
    }

}
