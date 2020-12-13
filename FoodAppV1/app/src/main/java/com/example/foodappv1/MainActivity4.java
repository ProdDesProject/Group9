package com.example.foodappv1;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class MainActivity4 extends AppCompatActivity {

    public static final String URL_CHOOSE_INGREDIENT = "http://stulinux159.ipt.oamk.fi/chooseingredient.php?igd_name=Potato";
    public static final String URL_ADD_RECIPE = "http://stulinux159.ipt.oamk.fi/chooseingredient.php?igd_name=Potato";

    private SearchView search_ingredient;

    private Button btn_continue;
    private Button btn_addIngredient;
    private Button addName;

    private TextView ingredients1;
    private TextView ingredients2;
    private TextView ingredients3;
    private TextView ingredients4;
    private TextView ingredients5;
    private TextView ingredients6;
    private TextView ingredients7;
    private TextView ingredients8;
    private TextView ingredients9;
    private TextView ingredients10;
    private TextView recipeNameField;

    int[] portions;
    String date;
    String mealType;
    String recipeName;
    String[] test;
    String[] mealCategory;

    String ingredient;



    int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        search_ingredient = findViewById(R.id.search_ingredient);
        ingredient = getIntent().getExtras().getString("ingredient");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        ingredients1 = findViewById(R.id.ingredient1);
        ingredients2 = findViewById(R.id.ingredient2);
        ingredients3 = findViewById(R.id.ingredient3);
        ingredients4 = findViewById(R.id.ingredient4);
        ingredients5 = findViewById(R.id.ingredient5);
        ingredients6 = findViewById(R.id.ingredient6);
        ingredients7 = findViewById(R.id.ingredient7);
        ingredients8 = findViewById(R.id.ingredient8);
        ingredients9 = findViewById(R.id.ingredient9);
        ingredients10 = findViewById(R.id.ingredient10);


        recipeNameField = findViewById(R.id.recipe_field);
        Intent i = getIntent();

        //we save the portions array, date and type of meal sent by the previous main, as always.

        portions = i.getExtras().getIntArray("portions");
        date = i.getExtras().getString("date");
        mealType = i.getExtras().getString("Meal name");


        try {

            //we try to read the recipeName sent by the next activity. Which receives it from this one as well. So obviously in the first attempt
            //it's going to enter the catch statement cause the user hasn't input any recipeName yet. But when the user does it for the first time
            //both activities will start sharing between them this recipeName (as well as the ingredient array and category array),
            // and won't enter the catch statement anymore.

            recipeName = i.getExtras().getString("recipeName");
            recipeNameField.setText(recipeName);
        }
        catch(Exception e){
        }


        try {

            //we try to save the ingredient array and the category array, in its first attempt, it's going to enter the catch statement cause nothing
            //is sent by the FakePopUpIngredients yet, it will start storing them in the moment that activity is open and the button add is clicked
            //then both arrays will be sent here and won't enter the catch statement anymore.

            test = getIntent().getExtras().getStringArray("ingredient");
            mealCategory = getIntent().getExtras().getStringArray("ingredientCategory");
            counter = getIntent().getExtras().getInt("counter");
            ingredients1.setText(test[0]);
            ingredients2.setText(test[1]);
            ingredients3.setText(test[2]);
            ingredients4.setText(test[3]);
            ingredients5.setText(test[4]);
            ingredients6.setText(test[5]);
            ingredients7.setText(test[6]);
            ingredients8.setText(test[7]);
            ingredients9.setText(test[8]);
            ingredients10.setText(test[9]);


        }
        catch(Exception e){
            ingredients1.setText("");
            ingredients2.setText("");
            ingredients3.setText("");
            ingredients4.setText("");
            ingredients5.setText("");
            ingredients6.setText("");
            ingredients7.setText("");
            ingredients8.setText("");
            ingredients9.setText("");
            ingredients10.setText("");

        }

        btn_continue = findViewById(R.id.button_continue);
        btn_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent( MainActivity4.this, MainActivity5.class);
                recipeName=recipeNameField.getText().toString();
                //when the button continue is clicked it means the user has finished adding ingredients.
                // All variables needed to create the recipe in main5 will be sent.
                //portions array, date of the meal, meal name, name of the recipe and of course both arrays with the ingredients and their categories
                i.putExtra("portions", portions);
                i.putExtra("date", date);
                i.putExtra("Meal name", mealType);
                i.putExtra("recipeName", recipeName);
                i.putExtra("ingredientsArray", test);
                i.putExtra("mealCategory", mealCategory);
                startActivity(i);
            }
        });

        btn_addIngredient = findViewById(R.id.button_add_ingredient);
        btn_addIngredient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent( MainActivity4.this, FakePopUpIngredients.class);
                if(recipeNameField.getText().toString().matches("")){

                }else{
                    recipeName=recipeNameField.getText().toString();
                }

                //Here it starts the dinamic sharing between this activity and the FakePopUpIngredients one.
                //both will be sending the same variables while updating them, so, when the user is ready to click the continue button
                //in order to see the quantities, all variables and especially both arrays of ingredients and cateogories will be
                //updated to the last ingredient the user input.

                i.putExtra("portions", portions);
                i.putExtra("date", date);
                i.putExtra("Meal name", mealType);
                i.putExtra("recipeName", recipeName);
                counter++;
                i.putExtra("counter", counter);
                i.putExtra("ingredientsArray", test);
                i.putExtra("mealCategory", mealCategory);
                startActivity(i);
            }
        });
    }

    //Show Ingredient
    public void show_ingredient(View view){
        final String igd_name = search_ingredient.getQuery().toString();

        class show_ingredient extends AsyncTask<Void, Void, String> {

            ProgressDialog pdLoading = new ProgressDialog(MainActivity4.this);

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
                params.put("igd_name", igd_name);

                //returing the response
                System.out.println("calling request");
                return requestHandler.sendPostRequest(URL_CHOOSE_INGREDIENT, params);
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
                        Toast.makeText(MainActivity4.this, obj.getString("message"), Toast.LENGTH_LONG).show();
                        //Make TextViews Visible
                        ingredients10.setVisibility(View.VISIBLE);

                        //Set retrieved text to TextViews
                        ingredients10.setText("Name: "+obj.getString("igd_name"));

                    }
                } catch (Exception e ){
                    Toast.makeText(MainActivity4.this, "Exception: "+e, Toast.LENGTH_SHORT).show();
                }
            }
        }

        show_ingredient show = new show_ingredient();
        show.execute();
    }

    //Add Recipe to db
    public void add_recipe(){

        class Recipedb extends AsyncTask<Void, Void, String> {

            ProgressDialog pdLoading = new ProgressDialog(MainActivity4.this);

            @Override
            protected void onPreExecute() {
                super.onPreExecute();

                //this method will be running on UI thread
                System.out.println("recipe added");
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
                params.put("meal_name", recipeName);
                params.put("igd_name", ingredient);
                //params.put("meal_date", date);

                //returning the response
                return requestHandler.sendPostRequest(URL_ADD_RECIPE, params);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                pdLoading.dismiss();
                System.out.println("s: " + s);
                try {
                    //converting response to json object
                    JSONObject obj = new JSONObject(s);
                    //if no error in response
                    if (!obj.getBoolean("error")) {
                        Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(MainActivity4.this, "Exception: "+e, Toast.LENGTH_LONG).show();
                }
            }
        }
        Recipedb prod_exec = new Recipedb();
        prod_exec.execute();
    }

    public void openActivity5() {
        Intent intent = new Intent (this, MainActivity5.class);
        startActivity(intent);
    }

}