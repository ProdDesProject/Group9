package com.example.foodappv1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity4 extends AppCompatActivity {

    // Ouma's modification
    private Remote remote;
    private static AcessDistant accessDistant;
    private static MainActivity4 instance = null;


    /**
     * builder private
     */
    //private MainActivity4() { super(); }

    /**
     * instance creation
     *
     * @return instance
     */
/*
    public static final MainActivity4 getInstance(Context context) {
        if (MainActivity4.instance == null) {
            MainActivity4.instance= new MainActivity4();
            accessDistant = new AcessDistant();
            accessDistant.send(  "chooseingredient", new JSONArray());
        }
        return MainActivity4.instance;
    }

    public JSONArray convertToJSONArray() {
        List theList = new ArrayList();
        theList.add(ingredients);
        //theList.add();
        return new JSONArray(theList);
    }

    private String ingredientName;

    public Ingredient(ingredientName){
        this.ingredientName= ingredientName;
    }

    public void getIngredient(){

    }*/
    // end modification

    private Button btn_continue;
    private Button btn_addIngredient;
    private Button addName;

    private TextView ingredients;
    private TextView recipeNameField;

    int intSmall;
    int intMedium;
    int intBig;
    String date;
    String mealType;
    String recipeName;
    String[] ingredientsList = new String[10];

    public static int strgToInt(String strg){
        int e = Integer.parseInt(strg);
        return e;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //this.remote = Remote.getInstance(); //db

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        ingredients = findViewById(R.id.ingredients_list_text);
        recipeNameField = findViewById(R.id.name_field);
        Intent i = getIntent();
        intSmall=strgToInt(i.getStringExtra( "small value"));
        intMedium=strgToInt(i.getStringExtra( "medium value"));
        intBig=strgToInt(i.getStringExtra( "big value"));
        date=i.getStringExtra("date");
        mealType=i.getStringExtra("Meal name");


        int[] portions = new int[]{intSmall, intMedium, intBig};

        btn_continue = findViewById(R.id.button_continue);
        btn_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent( MainActivity4.this, MainActivity5.class);
                recipeName=recipeNameField.getText().toString();
                i.putExtra("portions", portions);
                i.putExtra("date", date);
                i.putExtra("Meal name", mealType);
                i.putExtra("recipeName", recipeName);
                startActivity(i);
                finish();
                openActivity5();
            }
        });

        btn_addIngredient = findViewById(R.id.button_add_ingredient);
        btn_addIngredient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopUpIngredients popUp = new PopUpIngredients();
                popUp.showPopupWindow(v);

                //ingredientsList[0] = PopUpIngredients.ingredient;
                //ingredients.setText(ingredientsList[0]);
            }
        });
    }
    public void openActivity5() {
        Intent intent = new Intent (this, MainActivity5.class);
        startActivity(intent);
    }

   /* private void showResults(String mealName,String typeMeal, Date date, String ingredientName, String category){
        this.remote.addMeal(mealName,typeMeal,date);
        this.remote.addIngredient(ingredientName,category);
    }
*/
}