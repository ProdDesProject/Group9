package com.example.foodappv1;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class FakePopUpIngredients extends AppCompatActivity {

    Button add;
    TextView ingredientName;
    RadioGroup radioGroup;
    RadioButton radioButton;
    TextView ingredients;
    String [] ingredient = new String[10];
    String[] test;
    int[] portions;
    String date;
    String mealType;
    String recipeName;
    int counter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pop_up_ingredients);
        add = findViewById(R.id.add_ingredient);
        ingredientName = findViewById(R.id.ingredient_name);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup_ingredients);
        ingredients = findViewById(R.id.ingredient1);
        portions = getIntent().getExtras().getIntArray("portions");
        date = getIntent().getExtras().getString("date");
        mealType = getIntent().getExtras().getString("Meal name");
        recipeName = getIntent().getExtras().getString("recipeName");
        counter = getIntent().getExtras().getInt("counter");
        test = getIntent().getExtras().getStringArray("ingredientsArray");

        try {
                ingredient[0] = test[0];
                ingredient[1] = test[1];
                ingredient[2] = test[2];
                ingredient[3] = test[3];
                ingredient[4] = test[4];
                ingredient[5] = test[5];
                ingredient[6] = test[6];
                ingredient[7] = test[7];
                ingredient[8] = test[8];
                ingredient[9] = test[9];
        }catch(Exception e){

        }

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent( FakePopUpIngredients.this, MainActivity4.class);
                ingredient[counter-1] = ingredientName.getText().toString();
                i.putExtra( "ingredient", ingredient);
                i.putExtra( "portions", portions);
                i.putExtra( "date", date);
                i.putExtra( "Meal name", mealType);
                i.putExtra( "recipeName", recipeName);
                i.putExtra( "counter", counter);
                startActivity(i);
                //finish();
                //openActivity4();
            }
        });
    }

    public void openActivity4() {
        Intent intent = new Intent (this, MainActivity4.class);
        startActivity(intent);
    }

    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

    }
}
