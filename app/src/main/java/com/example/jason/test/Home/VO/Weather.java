package com.example.jason.test.Home.VO;

import java.util.ArrayList;

/**
 * Created by Jason on 2018/1/14.
 */

public class Weather {
    private int id;
    private String name;
    private ArrayList<Towns> towns;

    public Weather(int id, String name, ArrayList<Towns> towns) {
        this.id = id;
        this.name = name;
        this.towns = towns;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Towns> getTowns() {
        return towns;
    }

    public void setTowns(ArrayList<Towns> towns) {
        this.towns = towns;
    }
}
