package com.example.foodappv1;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;


public class MainActivity5 extends AppCompatActivity {
    private static TextView list;
    private Button button;
    private Button button2;
    TextView shopping_list;
    int[] portions;
    String date;
    String mealType;
    String recipeName;
    String[] ingredients;
    String[] categories;
    Food[] ingredientList = new Food[10];

    public static final String URL_ADD_MEAL = "http://stulinux159.ipt.oamk.fi/addmeal.php";

    public static TextView getList() {
        return list;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        portions = getIntent().getExtras().getIntArray("portions");
        date = getIntent().getExtras().getString("date");
        mealType = getIntent().getExtras().getString("Meal name");
        recipeName = getIntent().getExtras().getString("recipeName");
        ingredients = getIntent().getExtras().getStringArray("ingredientsArray");
        categories = getIntent().getExtras().getStringArray("mealCategory");

        //we save all variables sent by main4 that are needed to call the calculation method
        //which is going to be called automatically after we save all variables and elements in the screen

        button = findViewById(R.id.button_save);
        list = findViewById(R.id.shopping_list_text);
        shopping_list = findViewById(R.id.shopping_list_text);


        //For each ingredient, a new object is created and added to the ingredient list
        //When there is no more ingredients, the for loop is broken
        for(int i=0; i<10; i++){
            if(categories[i]!=null){
            if((categories[i].compareToIgnoreCase("Pasta/Rice/etc."))==0){
                Carbs newCarb = new Carbs(ingredients[i]);
                ingredientList[i] = newCarb;
            }
            else if((categories[i].compareToIgnoreCase("Cheese"))==0){
                Cheese newCheese = new Cheese(ingredients[i]);
                ingredientList[i] = newCheese;
            }
            else if((categories[i].compareToIgnoreCase("Dairy (except cheese)"))==0){
                Dairy newDairy = new Dairy(ingredients[i]);
                ingredientList[i] = newDairy;
            }
            else if((categories[i].compareToIgnoreCase("Fruits/Veggies"))==0){
                Veggies newFV = new Veggies(ingredients[i]);
                ingredientList[i] = newFV;
            }
            else if((categories[i].compareToIgnoreCase("Meat/Fish/Eggs"))==0){
                MeatFishEggs newMFE = new MeatFishEggs(ingredients[i]);
                ingredientList[i] = newMFE;
            }}
            else {
                break;
            }
        }

        //Creation of a new object recipe with all the parameters needed
        Recipe newRecipe = new Recipe(mealType, recipeName, portions, date, ingredientList);
        //Showing the shopping list
        shopping_list.setText(newRecipe.info());

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity6();
                add_meal();
            }

        });

        button2 = findViewById(R.id.button_modify);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                openActivity4();
            }
        });
    }

    //Add Meal
    public void add_meal(){

        class Meal extends AsyncTask<Void, Void, String> {

            ProgressDialog pdLoading = new ProgressDialog(MainActivity5.this);

            @Override
            protected void onPreExecute() {
                super.onPreExecute();

                //this method will be running on UI thread
                System.out.println("meal added");
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
                params.put("meal_type", mealType);
                //params.put("meal_date", date);

                //returning the response
                return requestHandler.sendPostRequest(URL_ADD_MEAL, params);
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
                    Toast.makeText(MainActivity5.this, "Exception: "+e, Toast.LENGTH_LONG).show();
                }
            }
        }
        Meal prod_exec = new Meal();
        prod_exec.execute();
}

    public void openActivity6() {
        Intent intent = new Intent (this, MainActivity6.class);
        startActivity(intent);
    }

    public void openActivity4() {
        Intent intent = new Intent (this, MainActivity4.class);
        startActivity(intent);
    }

}