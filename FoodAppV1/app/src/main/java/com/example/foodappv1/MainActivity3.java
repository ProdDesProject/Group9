package com.example.foodappv1;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class MainActivity3 extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    private Button chooseRecipe;
    private Button addRecipe;
    Button meal_date;
    TextView show_meal_date;
    TextView whatMeal;
    DatePickerDialog datePickerDialog;
    int year;
    int month;
    int dayOfMonth;
    Calendar calendar;
    TextView display_meal_name;

    ImageButton small_meal;
    ImageButton medium_meal;
    ImageButton large_meal;

    EditText nameFieldSmall;
    EditText nameFieldMedium;
    EditText nameFieldBig;
    String small;
    String medium;
    String big;
    String dateString;
    String mealType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        Intent i = getIntent();
        mealType=i.getExtras().getString( "Meal name");

        //we save the type of meal sent by the main2 in order to display afterwards "Plan your mealName"

        small_meal=findViewById(R.id.info_small_meal);
        medium_meal=findViewById(R.id.info_medium_meal);
        large_meal=findViewById(R.id.info_large_meal);
        addRecipe = findViewById(R.id.button_create_recipe);
        chooseRecipe = findViewById(R.id.button_choose_recipe);
        meal_date= findViewById(R.id.date_button);
        show_meal_date=findViewById(R.id.meal_date_field);
        whatMeal=findViewById(R.id.plan_meal_text);
        whatMeal.setText("Plan your " + mealType);
        display_meal_name=findViewById(R.id.plan_meal_text);

        nameFieldSmall= findViewById(R.id.editTextNumber);
        nameFieldMedium= findViewById(R.id.editTextNumber3);
        nameFieldBig= findViewById(R.id.editTextNumber2);

        chooseRecipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopUp popUp = new PopUp();
                popUp.showPopupWindow(v, "List of the previous recipes:");
            }
        });


        small_meal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(MainActivity3.this, R.style.AlertDialog)
                        .setTitle("Small portion")
                        .setMessage("Portion size recommended for kids")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        }).show();
            }
        });

        medium_meal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(MainActivity3.this, R.style.AlertDialog)
                        .setTitle("Medium portion")
                        .setMessage("Portion size recommended for females")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        }).show();
            }
        });

        large_meal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(MainActivity3.this, R.style.AlertDialog)
                        .setTitle("Large portion")
                        .setMessage("Portion size recommended for males")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        }).show();
            }
        });

        addRecipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent( MainActivity3.this, MainActivity4.class);
                small=nameFieldSmall.getText().toString();
                medium=nameFieldMedium.getText().toString();
                big=nameFieldBig.getText().toString();

                //the following if's are used to set 0 by default in case the user doesn't enter any value

                if(small.matches("")){
                    small = "0";
                }
                if(medium.matches("")){
                    medium = "0";
                }
                if(big.matches("")){
                    big = "0";
                }
                dateString=show_meal_date.getText().toString();
                int[] portions = new int[]{Integer.parseInt(small), Integer.parseInt(medium), Integer.parseInt(big)};

                //we send to the main4 the array of int with the number of small, medium and large meals.
                //as well as the date of the meal that the user selected and again the type of meal, cause it will be
                //necessary in the calculation method inside Recipe

                i.putExtra( "portions", portions);
                i.putExtra( "date", dateString);
                i.putExtra( "Meal name", mealType);
                startActivity(i);
                finish();
            }
        });
        meal_date.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                calendar = Calendar.getInstance();
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
                datePickerDialog = new DatePickerDialog(MainActivity3.this,
                        R.style.DialogTheme,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                show_meal_date.setText(day + "/" + (month + 1) + "/" + year);
                            }
                        }, year, month, dayOfMonth);
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
                datePickerDialog.show();
            }
        });
    }


    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

    }

}

