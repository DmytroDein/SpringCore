package ua.training.spring;

import java.text.DateFormat;
import java.util.Date;

/**
 * Created by Dmytro_Deinichenko on 3/17/2017.
 */
public class Event {
    private static int genId = 0;
    private DateFormat df;
    private int id = ++genId;
    private String msg;
    private Date date;

    public Event(Date date, DateFormat df) {
        this.date = date;
        this.df = df;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", msg='" + msg + '\'' +
                ", date=" + df.format(date) +
                '}';
    }
}
