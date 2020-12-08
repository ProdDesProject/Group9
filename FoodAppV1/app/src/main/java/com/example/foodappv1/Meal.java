package com.example.foodappv1;

import org.json.JSONArray;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Meal {


    private String recipeName;
    private String mealType;
    private Date date;
    //private static AcessDistant accessDistant;
    //private static Meal meal;

    public Meal(String recipeName, String mealType, Date date){
        this.recipeName= recipeName;
        this.mealType=mealType;
        this.date=date;
    }

    public String getRecipeName() { return recipeName;}
    public String getMealType() { return mealType;}
    public Date getMealDate() { return date;}

    public JSONArray convertToJSONArrayM() {
        List theList = new ArrayList();
        theList.add(recipeName);
        theList.add(mealType);
        theList.add(date);
        return new JSONArray(theList);
    }

}
