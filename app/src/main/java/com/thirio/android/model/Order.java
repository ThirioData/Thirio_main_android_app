package com.thirio.android.model;

/**
 * Created by abhinav on 20/1/18.
 */

public class Order {
    String name;
    String mainCourse;
    String sides;
    String salads;
    String breads;
    int diet;

    public int getDiet() {
        return diet;
    }

    public void setDiet(int diet) {
        this.diet = diet;
    }

    int userid;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMainCourse() {
        return mainCourse;
    }

    public void setMainCourse(String mainCourse) {
        this.mainCourse = mainCourse;
    }

    public String getSides() {
        return sides;
    }

    public void setSides(String sides) {
        this.sides = sides;
    }

    public String getSalads() {
        return salads;
    }

    public void setSalads(String salads) {
        this.salads = salads;
    }

    public String getBreads() {
        return breads;
    }

    public void setBreads(String breads) {
        this.breads = breads;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }
}
