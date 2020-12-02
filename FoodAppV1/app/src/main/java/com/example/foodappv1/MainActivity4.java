package com.example.foodappv1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity4 extends AppCompatActivity {
    private Button button;

    private TextView ingredients;

    String strSmall;
    String strMedium;
    String strBig;

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
        strSmall=getIntent().getExtras().getString( "small value");
        strMedium=getIntent().getExtras().getString( "medium value");
        strBig=getIntent().getExtras().getString( "big value");

        intSmall = strgToInt(strSmall);
        intMedium = strgToInt(strMedium);
        intBig = strgToInt(strBig);

        int[] portions = new int[]{intSmall, intMedium, intBig};

        //This is a test
        ingredients.setText("Portions: " + intSmall + intMedium + intBig + "?");

        button =(Button) findViewById(R.id.button_continue);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Recipe sandwich = new Recipe(MainActivity2.getMealType(), "snack", portions, MainActivity.mealDate);
                //I created a sandwich :)
                sandwich.info();
                openActivity5();
            }
        });
    }
    public void openActivity5() {
        Intent intent = new Intent (this, MainActivity5.class);
        startActivity(intent);
    }
}