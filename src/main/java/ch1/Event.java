package ch1;

import java.util.Date;

/**
 * Created by OGC on 2016/5/18.
 */
public class Event {
    private String value;
    private Date date;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
