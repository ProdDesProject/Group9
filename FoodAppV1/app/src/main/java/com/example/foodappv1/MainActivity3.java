package com.example.foodappv1;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
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
    String meal_name;
    TextView display_meal_name;

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
                Intent i= new Intent( MainActivity3.this, MainActivity4.class);
                small=nameFieldSmall.getText().toString();
                medium=nameFieldMedium.getText().toString();
                big=nameFieldBig.getText().toString();
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
                i.putExtra( "portions", portions);
                i.putExtra( "date", dateString);
                i.putExtra( "Meal name", mealType);
                startActivity(i);
                finish();
                //openActivity4();
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

    /*private void showDatePickerDialog() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH)

        );
        datePickerDialog.show();
    }*/
    public void openActivity4() {
        Intent intent = new Intent (this, MainActivity4.class);
        startActivity(intent);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

    }
}