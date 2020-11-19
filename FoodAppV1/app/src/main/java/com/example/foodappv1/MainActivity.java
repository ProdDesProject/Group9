package com.example.foodappv1;
//SQLiteOpenHelper(Context context, String meal, SQLiteDatabase.CursorFactory factory, int mysql)SQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version)
//test
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    //Name the user will enter
    public static final String textInput = "com.example.foodappv1.textInputEditText";

    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button =(Button) findViewById(R.id.button_next);
        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity2();
            }
        });
    }
    public void openActivity2() {
        Intent intent = new Intent (this, MainActivity2.class);
        startActivity(intent);
    }
        public static void main(String[] args) {

            //portions[0] is the number of small portions, 1 => medium and 2 => large
            int[] portions = new int[]{1, 2, 1};

            //Creation of the user
            User user1 = new User(textInput);
            //Print user infos
            user1.info();

            //Creating new ingredients
            Veggies salad = new Veggies();
            Carbs bread = new Carbs();
            Dairy yogurt = new Dairy();
            MeatFishEggs ham = new MeatFishEggs();
            Cheese emmental = new Cheese();

            //Adding the ingredients to a list
            //Needed only to print the infos about the recipe
            Food[] list = new Food[]{salad, bread, yogurt, ham, emmental};

            //Creating a new recipe with these ingredients and the portions entered by the user
            Recipe sandwich = new Recipe("lunch", "sandwich", portions);

            sandwich.info();

        }

    }