package com.thirio.android.model;

/**
 * Created by abhinav on 25/12/17.
 */

public class FoodItem {
    String image;
    String name;
    String cusine;
    String description;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCusine() {
        return cusine;
    }

    public void setCusine(String cusine) {
        this.cusine = cusine;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
