package utils;

/**
 * Created by Ridiculous on 2016/10/14.
 */
public class crResult {
    private String ringid;
    private String ringname;
    private String cpid;
    private String cpname;

    private int weeklyorderamount;
    private int weeklycancelamount;

    private int monthlyorderamount;
    private int monthlycancelamount;

    private int givenorderamount;
    private int givencancelamount;

    private int totalamount;

    public crResult(String ringid, String ringname, String cpid, String cpname) {
        this.ringid = ringid;
        this.ringname = ringname;
        this.cpid = cpid;
        this.cpname = cpname;
        this.weeklyorderamount = 0;
        this.weeklycancelamount = 0;
        this.monthlyorderamount = 0;
        this.monthlycancelamount = 0;
        this.givenorderamount = 0;
        this.givencancelamount = 0;
        this.totalamount = 0;
    }

    public String getRingid() {
        return ringid;
    }

    public void setRingid(String ringid) {
        this.ringid = ringid;
    }

    public String getRingname() {
        return ringname;
    }

    public void setRingname(String ringname) {
        this.ringname = ringname;
    }

    public String getCpid() {
        return cpid;
    }

    public void setCpid(String cpid) {
        this.cpid = cpid;
    }

    public String getCpname() {
        return cpname;
    }

    public void setCpname(String cpname) {
        this.cpname = cpname;
    }

    public int getWeeklyorderamount() {
        return weeklyorderamount;
    }

    public void setWeeklyorderamount(int weeklyorderamount) {
        this.weeklyorderamount = weeklyorderamount;
    }

    public int getWeeklycancelamount() {
        return weeklycancelamount;
    }

    public void setWeeklycancelamount(int weeklycancelamount) {
        this.weeklycancelamount = weeklycancelamount;
    }

    public int getMonthlyorderamount() {
        return monthlyorderamount;
    }

    public void setMonthlyorderamount(int monthlyorderamount) {
        this.monthlyorderamount = monthlyorderamount;
    }

    public int getMonthlycancelamount() {
        return monthlycancelamount;
    }

    public void setMonthlycancelamount(int monthlycancelamount) {
        this.monthlycancelamount = monthlycancelamount;
    }

    public int getGivenorderamount() {
        return givenorderamount;
    }

    public void setGivenorderamount(int givenorderamount) {
        this.givenorderamount = givenorderamount;
    }

    public int getGivencancelamount() {
        return givencancelamount;
    }

    public void setGivencancelamount(int givencancelamount) {
        this.givencancelamount = givencancelamount;
    }

    public int getTotalamount() {
        return totalamount;
    }

    public void setTotalamount(int totalamount) {
        this.totalamount = totalamount;
    }
}
