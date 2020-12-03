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

public class MainActivity2 extends AppCompatActivity {
    Button button;
    RadioGroup radioGroup;
    RadioButton radioButton;
    TextView name_display;
    public static String meal;
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        radioGroup = findViewById(R.id.radioGroup);
        name_display= findViewById(R.id.meal_text);
        button = findViewById(R.id.button_apply);
        name=getIntent().getExtras().getString("Name value");
        name_display.setText("What meal do you want to plan, " + String.valueOf(name) + "?");

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

}