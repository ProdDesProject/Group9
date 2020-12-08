package com.example.foodappv1;

import org.json.JSONArray;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Meal {

    private static AcessDistant accessDistant;
    private String mealName;
    private String mealType;
    private Date date;
    private static Meal meal;

    public Meal(String mealName, String mealType, Date mealDate){
        this.mealName= mealName;
        this.mealType=mealType;
        this.date=mealDate;
    }
    public JSONArray convertToJSONArray() {
        List theList = new ArrayList();
        theList.add(mealName);
        theList.add(mealType);
        theList.add(date);
        return new JSONArray(theList);
    }

}
