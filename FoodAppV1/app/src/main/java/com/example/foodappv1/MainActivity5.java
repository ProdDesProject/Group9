package com.example.foodappv1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.Date;


public class MainActivity5 extends AppCompatActivity {
    private Remote remote;
    private static TextView list; //nm = new modification
    private Button button;
    private Button button2;
    TextView shopping_list;
    private TextView other_ingredients;
    int[] portions;
    String date;
    String mealType;
    String recipeName;

    int intSmall;
    int intMedium;
    int intLarge;


    public static int strgToInt(String strg){
        int e = Integer.parseInt(strg);
        return e;
    }

    public static TextView getList() {
        return list;
    }
     private void init(){
         this.remote = Remote.getInstance(this); //db
     }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        init();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        portions = getIntent().getExtras().getIntArray("portions");
        date = getIntent().getExtras().getString("date");
        mealType = getIntent().getExtras().getString("Meal name");
        recipeName = getIntent().getExtras().getString("recipeName");
        String[] list_ing = new String[]{"salad", "bread", "cream", "ham", "cheese"};
        Recipe sandwich = new Recipe(mealType, recipeName, portions, date, list_ing);

        button =findViewById(R.id.button_save);
        list = findViewById(R.id.shopping_list_text);
        shopping_list = findViewById(R.id.shopping_list_text);
        shopping_list.setText(sandwich.info());

        button.setOnClickListener(new View.OnClickListener() {
            private Remote remote;

            @Override
            public void onClick(View v) {
                openActivity6();
            }
            private void showResults(String recipeName, String typeMeal, Date date /*, String ingredientName, String category*/){
                this.remote.addMeal(recipeName, typeMeal, date, this);
                //this.remote.addIngredient(ingredientName,category);
            }
        });

        button2 = findViewById(R.id.button_modify);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                openActivity4();
            }
        });
    }

    public void openActivity6() {
        Intent intent = new Intent (this, MainActivity6.class);
        startActivity(intent);
    }

    public void openActivity4() {
        Intent intent = new Intent (this, MainActivity4.class);
        startActivity(intent);
    }

}