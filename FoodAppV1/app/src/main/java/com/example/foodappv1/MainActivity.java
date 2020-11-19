package com.example.foodappv1;
//SQLiteOpenHelper(Context context, String meal, SQLiteDatabase.CursorFactory factory, int mysql)

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    //Username the user will enter
    public static final String textInput = "com.example.foodappv1.textInputEditText";
    //Meal name the user will enter
    //Not true for the moment, this is not the right text box
    public static final String mealName = "com.example.foodappv1.meal_text";
    //Meal Type the user will enter
    public static String meal = MainActivity2.getMealType();
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

            //Creating a new recipe with the type of meal, the ingredients,
            //the portions and the date entered by the user
            Recipe recipe1 = new Recipe(meal, mealName, portions, mealDate);
            //Print recipe infos
            recipe1.info();

        }

    }