package com.example.foodappv1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity4 extends AppCompatActivity {
    private Button btn_continue;
    private Button btn_addIngredient;

    private TextView ingredients;

    int intSmall;
    int intMedium;
    int intBig;

    public static int strgToInt(String strg){
        int e = Integer.parseInt(strg);
        return e;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        ingredients = findViewById(R.id.ingredients_text);
        intSmall=strgToInt(getIntent().getExtras().getString( "small value"));
        intMedium=strgToInt(getIntent().getExtras().getString( "medium value"));
        intBig=strgToInt(getIntent().getExtras().getString( "big value"));

        int[] portions = new int[]{intSmall, intMedium, intBig};

        //This is a test
        ingredients.setText("Portions: " + intSmall + intMedium + intBig + "?");

        btn_continue = findViewById(R.id.button_continue);
        btn_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Recipe sandwich = new Recipe(MainActivity2.getMealType(), "snack", portions, MainActivity.mealDate);
                //I created a sandwich :)
                sandwich.info();
                openActivity5();
            }
        });

        btn_addIngredient = findViewById(R.id.button_add_ingredient);
        btn_addIngredient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopUpIngredients popUp = new PopUpIngredients();
                popUp.showPopupWindow(v);
            }
        });
    }
    public void openActivity5() {
        Intent intent = new Intent (this, MainActivity5.class);
        startActivity(intent);
    }
}