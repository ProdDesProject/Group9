package com.example.foodappv1;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class Ingredient {

    private String ingredientName;
    private String category;
    private static AcessDistant accessDistant;
    private static Ingredient ingredient;

    public Ingredient(String ingredientName, String category){
        this.ingredientName= ingredientName;
        this.category=category;
    }
    public JSONArray convertToJSONArray() {
        List theList = new ArrayList();
        theList.add(ingredientName);
        theList.add(category);
        return new JSONArray(theList);
    }

/*   public void addIngredient(String ingredientName, String category){
        ingredient = new Ingredient(ingredientName, category);
        accessDistant.send(  "addingredient", ingredient.convertToJSONArray());
    }
    */
}
