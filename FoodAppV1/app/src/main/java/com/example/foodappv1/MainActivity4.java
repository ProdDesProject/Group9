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

    private TextView ingredients1;
    private TextView ingredients2;
    private TextView ingredients3;
    private TextView ingredients4;
    private TextView ingredients5;
    private TextView ingredients6;
    private TextView ingredients7;
    private TextView ingredients8;
    private TextView ingredients9;
    private TextView ingredients10;
    private TextView recipeNameField;

    int[] portions;
    String date;
    String mealType;
    String recipeName;
    String[] ingredientName = new String[9];
    String[] test;


    int counter = 0;

    public static int strgToInt(String strg){
        int e = Integer.parseInt(strg);
        return e;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //this.remote = Remote.getInstance(); //db

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        ingredients1 = findViewById(R.id.ingredient1);
        ingredients2 = findViewById(R.id.ingredient2);
        ingredients3 = findViewById(R.id.ingredient3);
        ingredients4 = findViewById(R.id.ingredient4);
        ingredients5 = findViewById(R.id.ingredient5);
        ingredients6 = findViewById(R.id.ingredient6);
        ingredients7 = findViewById(R.id.ingredient7);
        ingredients8 = findViewById(R.id.ingredient8);
        ingredients9 = findViewById(R.id.ingredient9);
        ingredients10 = findViewById(R.id.ingredient10);

        TextView [] textViewArray = new TextView[]{ingredients1, ingredients2, ingredients3, ingredients4, ingredients5, ingredients6, ingredients7, ingredients8, ingredients9, ingredients10};


        recipeNameField = findViewById(R.id.recipe_field);
        Intent i = getIntent();

        portions = i.getExtras().getIntArray("portions");
        date = i.getExtras().getString("date");
        mealType = i.getExtras().getString("Meal name");


        try {
            recipeName = i.getExtras().getString("recipeName");
        }
        catch(Exception e){
            recipeName = "";
        }


        try {
            test = getIntent().getExtras().getStringArray("ingredient");
            counter = getIntent().getExtras().getInt("counter");
            ingredients1.setText(test[0]);
            ingredients2.setText(test[1]);
            ingredients3.setText(test[2]);
            ingredients4.setText(test[3]);
            ingredients5.setText(test[4]);
            ingredients6.setText(test[5]);
            ingredients7.setText(test[6]);
            ingredients8.setText(test[7]);
            ingredients9.setText(test[8]);
            ingredients10.setText(test[9]);


        }
        catch(Exception e){
                ingredients1.setText("");
                ingredients2.setText("");
                ingredients3.setText("");
                ingredients4.setText("");
                ingredients5.setText("");
                ingredients6.setText("");
                ingredients7.setText("");
                ingredients8.setText("");
                ingredients9.setText("");
                ingredients10.setText("");

        }


        //int[] portions = new int[]{intSmall, intMedium, intBig};

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
                //finish();
                //openActivity5();
            }
        });

        btn_addIngredient = findViewById(R.id.button_add_ingredient);
        btn_addIngredient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent( MainActivity4.this, FakePopUpIngredients.class);
                i.putExtra("portions", portions);
                i.putExtra("date", date);
                i.putExtra("Meal name", mealType);
                i.putExtra("recipeName", recipeName);
                counter++;
                i.putExtra("counter", counter);
                i.putExtra("ingredientsArray", test);
                startActivity(i);
                //PopUpIngredients popUp = new PopUpIngredients();
                //popUp.showPopupWindow(v);

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