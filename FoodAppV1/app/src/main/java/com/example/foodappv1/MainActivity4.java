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
        Intent i = getIntent();
        intSmall=strgToInt(i.getStringExtra( "small value"));
        intMedium=strgToInt(i.getStringExtra( "medium value"));
        intBig=strgToInt(i.getStringExtra( "big value"));

        int[] portions = new int[]{intSmall, intMedium, intBig};

        btn_continue = findViewById(R.id.button_continue);
        btn_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent( MainActivity4.this, MainActivity5.class);
                i.putExtra("portions", portions);
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
            }
        });
    }
    public void openActivity5() {
        Intent intent = new Intent (this, MainActivity5.class);
        startActivity(intent);
    }

}