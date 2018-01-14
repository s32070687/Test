package com.example.jason.test.Home.VO;

/**
 * Created by Jason on 2018/1/14.
 */

public class Towns {
    private int townsid;
    private String townsname;

    public Towns(int townsid, String name, String townsname) {
        this.townsid = townsid;
        this.townsname = townsname;
    }

    public int getTownsid() {
        return townsid;
    }

    public void setTownsid(int townsid) {
        this.townsid = townsid;
    }

    public String getTownsname() {
        return townsname;
    }

    public void setTownsname(String townsname) {
        this.townsname = townsname;
    }
}
