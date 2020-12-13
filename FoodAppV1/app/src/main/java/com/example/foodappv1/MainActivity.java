package com.example.foodappv1;
//SQLiteOpenHelper(Context context, String meal, SQLiteDatabase.CursorFactory factory, int mysql)

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {


    //Date the user will enter
    public static final String mealDate = "com.example.foodappv1.meal_date_field";

    private Button button;
    EditText name_fld;
    String st;

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
                //openActivity2();
            }
        });
    }

    public void openActivity2() {
        Intent intent = new Intent (this, MainActivity2.class);
        startActivity(intent);
    }
        public static void main(String[] args) {

        }

    }