package com.example.foodappv1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity2 extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    Button button;
    Button buttonApply;
    RadioGroup radioGroup;
    RadioButton radioButton;
    TextView textView;
    TextView name_display;
    //String meal;
    public static String meal;
    String name;
    ImageButton calendarButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        radioGroup = findViewById(R.id.radioGroup);
        name_display= findViewById(R.id.meal_text);
        calendarButton= findViewById(R.id.calendar_button);
        buttonApply = findViewById(R.id.button_apply);
        button = findViewById(R.id.button_apply);
        name=getIntent().getExtras().getString("Name value");
        name_display.setText("What meal do you want to plan, " + String.valueOf(name) + "?");


        buttonApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int radioId = radioGroup.getCheckedRadioButtonId();
                radioButton = findViewById(radioId);
                textView.setText("Your choice: " + radioButton.getText());
            }
        });


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent( MainActivity2.this, MainActivity3.class);
                meal=radioButton.getText().toString();
                i.putExtra( "Meal name",meal);
                startActivity(i);
                finish();
                openActivity3();
            }
        });

        calendarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();

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
    public void checkButton(View v) {
        int radioId = radioGroup.getCheckedRadioButtonId();
        radioButton = findViewById(radioId);
        Toast.makeText(this, "Selected meal: " + radioButton.getText(),
                Toast.LENGTH_SHORT).show();
    }
    public void openActivity3() {
        Intent intent = new Intent (this, MainActivity3.class);
        startActivity(intent);
    }

    public static String getMealType(){;
        return meal;
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

    }
}