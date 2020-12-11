package com.example.foodappv1;

import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;

public final class Remote extends AppCompatActivity {

    private static Remote instance = null;
    private static Ingredient ingredient;
    private static Meal meal;
    private static AcessDistant accessDistant;
    private static Context context;


    private Remote() { super(); }


    public static final Remote getInstance(Context context) {
        if (context != null){
            Remote.context=context;
        }
        if (Remote.instance == null) {
            Remote.instance= new Remote();
            accessDistant = new AcessDistant();
            accessDistant.send(  "chooseingredient", new JSONArray());
            accessDistant.send(  "showmeal", new JSONArray());
        }
        return Remote.instance;
    }

    public void addIngredient(String ingredientName, String category){
        ingredient = new Ingredient(ingredientName, category);
        accessDistant.send(  "addingredient", ingredient.convertToJSONArrayI());
    }

    //public void addMeal(String recipeName, String mealType,/*Date date*/ View.OnClickListener context){
    //  meal = new Meal(recipeName, mealType/*, date*/);
    // accessDistant.send(  "addmeal", meal.convertToJSONArrayM());
    //}

    public void setIngredient(Ingredient ingredient){
        Remote.ingredient=ingredient;
        //((MainActivity4).context).getIngredient();
    }
    public void setMeal(Meal meal){
        Remote.meal=meal;
        //((MainActivity4).context).getMeal();
    }

}
