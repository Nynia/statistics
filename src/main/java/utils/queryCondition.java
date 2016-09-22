package utils;

import java.util.Date;

/**
 * Created by Ridiculous on 2016/6/3.
 */
public class queryCondition {
    private Date start_time;
    private Date end_time;
    private int cpid;
    public queryCondition(Date start_time, Date end_time, int cpid) {
        this.start_time = start_time;
        this.end_time = end_time;
        this.cpid = cpid;
    }

    public int getCpid() {
        return cpid;
    }

    public void setCpid(int cpid) {
        this.cpid = cpid;
    }

    public Date getEnd_time() {
        return end_time;
    }

    public void setEnd_time(Date end_time) {
        this.end_time = end_time;
    }

    public Date getStart_time() {
        return start_time;
    }

    public void setStart_time(Date start_time) {
        this.start_time = start_time;
    }
}
