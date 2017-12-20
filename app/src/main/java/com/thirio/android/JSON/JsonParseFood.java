package com.thirio.android.JSON;

import com.thirio.android.model.FoodItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by abhinav on 25/12/17.
 */

public class JsonParseFood {
    public static String[] name;
    public static String[] image;
    public static String[] cusine;
    public static String[] description;
    List<FoodItem> data;
    private JSONArray fooditem = null;
    private String json;

    public void parseJSONteam(String json) {
//        JSONObject jsonObject=null;
        this.json = json;
        System.out.println("JSON PARSING");
        try {

            fooditem = new JSONArray(json);


            name = new String[fooditem.length()];
            image = new String[fooditem.length()];
            cusine = new String[fooditem.length()];
            description = new String[fooditem.length()];
            data = new ArrayList<FoodItem>();


            for (int i = 0; i < fooditem.length(); i++) {
                FoodItem data_object = new FoodItem();

                JSONObject jsonObject = fooditem.getJSONObject(i);

                name[i] = jsonObject.getString("food-name");
                image[i] = jsonObject.getString("food-image");
                cusine[i] = jsonObject.getString("food-cuisine");
                description[i] = jsonObject.getString("food-description");
                data_object.setCusine(cusine[i]);
                data_object.setDescription(description[i]);
                data_object.setImage(image[i]);
                data_object.setName(name[i]);
                data.add(data_object);


            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public List<FoodItem> getData() {
        //function to return the final populated list
        return data;
    }

}
