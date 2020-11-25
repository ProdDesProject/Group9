package com.example.foodappv1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;

public class MainActivity3 extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    private Button chooseRecipe;
    private Button addRecipe;
    Button meal_date;
    TextView show_meal_date;
    DatePickerDialog datePickerDialog;
    int year;
    int month;
    int dayOfMonth;
    Calendar calendar;
    String meal_name;
    TextView display_meal_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        addRecipe = (Button) findViewById(R.id.button_add_recipe);
        chooseRecipe = (Button) findViewById(R.id.button_choose_recipe);
        meal_date= findViewById(R.id.date_button);
        show_meal_date=findViewById(R.id.meal_date_field);
        display_meal_name=findViewById(R.id.plan_meal_text);
        //meal_name=getIntent().getExtras().getString("Meal name");
        //display_meal_name.setText("What meal do you want to plan, " + String.valueOf(meal_name));

        chooseRecipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopUp popUp = new PopUp();
                popUp.showPopupWindow(v, "List of recipes from database");
            }
        });

        addRecipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity4();
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

    private void showDatePickerDialog() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH)

        );
        datePickerDialog.show();
    }
    public void openActivity4() {
        Intent intent = new Intent (this, MainActivity4.class);
        startActivity(intent);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

    }
}