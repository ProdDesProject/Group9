package com.example.foodappv1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    //Username the user will enter
    public static final String textInput = "com.example.foodappv1.textInputEditText";
    //Meal name the user will enter
    public static final String mealName = "com.example.foodappv1.meal_text";
    //Type of meal the user will check
    public static final String breakfast = "com.example.foodappv1.radio_one";
    public static final String lunch = "com.example.foodappv1.radio_two";
    public static final String dinner = "com.example.foodappv1.radio_three";
    //Number of each portion the user will enter
    public static final String smallPortions = "com.example.foodappv1.editTextNumber";
    public static final String mediumPortions = "com.example.foodappv1.editTextNumber3";
    public static final String largePortions = "com.example.foodappv1.editTextNumber2";
    //Date the user will enter
    public static final String mealDate = "com.example.foodappv1.editTextDate";


    private Button button;
    EditText name_fld;
    String st;

    public static int strgToInt(String strg){
        int e = Integer.parseInt(strg);
        return e;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button =(Button) findViewById(R.id.button_next);
        name_fld= (EditText) findViewById(R.id.name_field);

        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent( MainActivity.this, MainActivity2.class);
                st=name_fld.getText().toString();
                i.putExtra( "Name value",st);
                startActivity(i);
                finish();
                openActivity2();
            }
        });
    }
    public void openActivity2() {
        Intent intent = new Intent (this, MainActivity2.class);
        startActivity(intent);
    }
        public static void main(String[] args) {

            //Convert portion strings to int
            int S = strgToInt(smallPortions);
            int M = strgToInt(mediumPortions);
            int L = strgToInt(largePortions);


            //portions[0] is the number of small portions, 1 => medium and 2 => large
            int[] portions = new int[]{S, M, L};

            //Creation of the user
            User user1 = new User(textInput);
            //Print user infos
            user1.info();

            //Creating new ingredients
            //ONLY FOR TEST
            Veggies salad = new Veggies();
            Carbs bread = new Carbs();
            Dairy yogurt = new Dairy();
            MeatFishEggs ham = new MeatFishEggs();
            Cheese emmental = new Cheese();

            //Adding the ingredients to a list
            //Needed only to print the infos about the recipe
            //ONLY FOR TEST
            Food[] list = new Food[]{salad, bread, yogurt, ham, emmental};

            //Creating a new recipe with these ingredients and the portions entered by the user
            Recipe recipe1 = new Recipe("lunch", mealName, portions);

            recipe1.info();

        }

    }