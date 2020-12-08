package com.example.foodappv1;

import android.content.Context;

import org.json.JSONArray;

import java.sql.Date;

public final class Remote {

    private static AcessDistant accessDistant;
    private static Ingredient ingredient;
    private static Meal meal;
    private static Remote instance = null;
    private static Context context;


    Remote() { super(); }


    public static final Remote getInstance(Context context) {
        if (context != null){
            Remote.context=context;
        }
        if (Remote.instance == null) {
            Remote.instance= new Remote();
            accessDistant = new AcessDistant();
            accessDistant.send(  "chooseingredient", new JSONArray());
        }
        return Remote.instance;
    }

    public void addIngredient(String ingredientName, String category){
        ingredient = new Ingredient(ingredientName, category);
        accessDistant.send(  "addingredient", ingredient.convertToJSONArray());
    }

    public void addMeal(String mealName, String mealType, Date date){
        meal = new Meal(mealName, mealType, date);
        accessDistant.send(  "addmeal", meal.convertToJSONArray());
    }

    public void setIngredient(Ingredient ingredient){
        Remote.ingredient=ingredient;
        //((MainActivity4).context).getIngredient();
    }

}
