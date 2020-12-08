package com.example.foodappv1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity5 extends AppCompatActivity {
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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
        other_ingredients = findViewById(R.id.other_ingredients_list);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity6();
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