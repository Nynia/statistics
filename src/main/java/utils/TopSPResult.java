package utils;

/**
 * Created by Ridiculous on 2017/2/22.
 */
public class TopSPResult {
    private String spid;
    private String spname;
    private int count;

    public TopSPResult() {

    }

    public TopSPResult(String spid, String spname, int count) {
        this.spid = spid;
        this.spname = spname;
        this.count = count;
    }

    public String getSpid() {
        return spid;
    }

    public void setSpid(String spid) {
        this.spid = spid;
    }

    public String getSpname() {
        return spname;
    }

    public void setSpname(String spname) {
        this.spname = spname;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
