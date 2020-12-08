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


    /*Ingredients and categories the user will enter
    * public static final String[] ingredient = new String[4];
    * public static final String[] category = new String[4];
    * public static final String ingredient[0] = "com.example.foodappv1.text_ingredient0";
    * public static final String category[0] = "com.example.foodappv1.text_category0";
    * public static final String ingredient[1] = "com.example.foodappv1.text_ingredient1";
    * public static final String category[1] = "com.example.foodappv1.text_category1";
    * public static final String ingredient[2] = "com.example.foodappv1.text_ingredient2";
    * public static final String category[2] = "com.example.foodappv1.text_category2";
    * public static final String ingredient[3] = "com.example.foodappv1.text_ingredient3";
    * public static final String category[3] = "com.example.foodappv1.text_category3";*/

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

            /*for(int i; i=0; i++){
                if(category[i]=="Carbs"){
                    Carbs ingredient[i] = new Carbs();
                }
                if(category[i]=="Cheese"){
                    Cheese ingredient[i] = new Cheese();
                }
                if(category[i]=="Dairy"){
                    Dairy ingredient[i] = new Dairy();
                }
                if(category[i]=="Veggies"){
                    Veggies ingredient[i] = new Veggies();
                }
                if(category[i]=="MeatFishEggs"){
                    MeatFishEggs ingredient[i] = new MeatFishEggs();
                }
            }*/

        }

    }