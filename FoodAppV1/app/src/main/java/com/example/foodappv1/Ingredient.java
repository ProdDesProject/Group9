package com.example.foodappv1;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class Ingredient {

    private String ingredientName;
    private String category;


    public Ingredient(String ingredientName, String category){
        this.ingredientName= ingredientName;
        this.category=category;
    }
    public String getIngredientName() { return ingredientName;}
    public String getCategory() { return category;}

    public JSONArray convertToJSONArrayI() {
        List theList = new ArrayList();
        theList.add(ingredientName);
        theList.add(category);
        return new JSONArray(theList);
    }

}
