package com.example.foodappv1;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;

public class MainActivity2 extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    Button button;
    ImageButton recipes;
    ImageButton meals;
    RadioGroup radioGroup;
    RadioButton radioButton;
    TextView name_display;
    String meal;
    String name;
    ImageButton calendarButton;
    public static final String URL_SHOW_MEAL = "http://stulinux159.ipt.oamk.fi/showmeals.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        name_display= findViewById(R.id.meal_text);
        calendarButton= findViewById(R.id.calendar_button);
        recipes = findViewById(R.id.recipes_button);
        meals = findViewById(R.id.meals_button);
        button = findViewById(R.id.button_apply);
        name = getIntent().getExtras().getString("Name value");

        //here we use the username we sent in the main1

        name_display.setText("What meal do you want to plan, " + String.valueOf(name) + "?");


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent( MainActivity2.this, MainActivity3.class);
                int selectedId = radioGroup.getCheckedRadioButtonId();
                    radioButton = (RadioButton) findViewById(selectedId);
                    meal = (String) radioButton.getText();
                    //we first take the type of meal capturing the corresponding radio button string
                    switch (meal){
                        case "Lunch":
                            meal = "lunch";
                            break;
                        case "Breakfast":
                            meal = "breakfast";
                            break;
                        case "Dinner":
                            meal = "dinner";
                            break;
                    //we change it to lower case because the calculation method of Recipe reads the strings like that
                    }
                    i.putExtra("Meal name", meal);
                    startActivity(i);
                    finish();
                    //Declaring an intent to share the type of meal with the main3 and then opening the main3

            }
        });
        recipes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopUp popUp = new PopUp();
                popUp.showPopupWindow(v, "List of recipes planned:");

            }
        });


        meals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopUp popUp = new PopUp();
                popUp.showPopupWindow(v, "List of meals planned:");
                show_meal();

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
                R.style.DialogTheme,
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

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

    }

    //Show meal
    public void show_meal(){
        final String meal_id = "";

        class ShowMeal extends AsyncTask<Void, Void, String> {

            ProgressDialog pdLoading = new ProgressDialog(MainActivity2.this);

            @Override
            protected void onPreExecute() {
                super.onPreExecute();

                System.out.println("calling onpre");
                //this method will be running on UI thread
                pdLoading.setMessage("\tLoading...");
                pdLoading.setCancelable(false);
                pdLoading.show();
            }

            @Override
            protected String doInBackground(Void... voids) {
                //creating request handler object
                RequestHandler requestHandler = new RequestHandler();

                //creating request parameters
                HashMap<String, String> params = new HashMap<>();
                params.put("meal_id", meal_id);

                //returning the response
                System.out.println("calling request");
                return requestHandler.sendPostRequest(URL_SHOW_MEAL, params);
            }

            @Override
            protected void onPostExecute(String s){
                super.onPostExecute(s);
                pdLoading.dismiss();

                try{
                    //Converting response to JSON Object
                    JSONObject obj = new JSONObject(s);

                    //if no error in response
                    if (!obj.getBoolean("error")){
                        Toast.makeText(MainActivity2.this, obj.getString("message"), Toast.LENGTH_LONG).show();
                        //Make TextViews Visible
                        meals.setVisibility(View.VISIBLE);

                        //Set retrieved text to TextViews
                       // meals.setText("Meal name: "+obj.getString("meal_name")+"for "+obj.getString("meal_type"));
                    }
                } catch (Exception e ){
                    Toast.makeText(MainActivity2.this, "Exception: "+e, Toast.LENGTH_SHORT).show();
                }
            }
        }

        ShowMeal show = new ShowMeal();
        show.execute();
    }

}
