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
    //private Remote remote;
    private static TextView list; //nm = new modification
    private Button button;
    private Button button2;
    TextView shopping_list;
    int[] portions;
    String date;
    String mealType;
    String recipeName;
    String[] ingredients;
    String[] categories;
    Food[] ingredientList;
    int nb_ingredients = 0;

    public static final String URL_ADD_MEAL = "http://stulinux159.ipt.oamk.fi/data.php?operation=addmeal";
    public static final String URL_SHOW_MEAL = "http://stulinux159.ipt.oamk.fi/data.php?operation=showmeal";
    public static final String URL_CHOOSE_INGREDIENT = "http://stulinux159.ipt.oamk.fi/data.php?operation=chooseingredient";
    public static final String URL_ADD_INGREDIENT = "http://stulinux159.ipt.oamk.fi/data.php?operation=addingredient";

    public static TextView getList() {
        return list;
    }
    /*
    private void init(){
         this.remote = Remote.getInstance(this); //db
     }
*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //init();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        portions = getIntent().getExtras().getIntArray("portions");
        date = getIntent().getExtras().getString("date");
        mealType = getIntent().getExtras().getString("Meal name");
        recipeName = getIntent().getExtras().getString("recipeName");
        ingredients = getIntent().getExtras().getStringArray("ingredientsArray");
        categories = getIntent().getExtras().getStringArray("mealCategory");

        /*for(int i=0; i<10; i++){
            if(categories[i]=="Pasta/Rice/etc."){
                Carbs newcarb = new Carbs(ingredients[i]);
                ingredientList[i] = newcarb;
            }
            else if(categories[i]=="Cheese"){
                Cheese newcheese = new Cheese(ingredients[i]);
                ingredientList[i] = newcheese;
            }
            else if(categories[i]=="Dairy (except cheese)"){
                Dairy newdairy = new Dairy(ingredients[i]);
                ingredientList[i] = newdairy;
            }
            else if(categories[i]=="Fruits/Veggies"){
                Veggies newfruit = new Veggies(ingredients[i]);
                ingredientList[i] = newfruit;
            }
            else if(categories[i]=="Meat/Fish/Eggs"){
                MeatFishEggs newmeat = new MeatFishEggs(ingredients[i]);
                ingredientList[i] = newmeat;
            }
            else {
                break;
            }
        }*/

        Veggies salad = new Veggies("salad");
        Veggies tomato = new Veggies("tomato");
        Carbs bread = new Carbs("bread");
        Dairy cream = new Dairy("cream");
        Cheese gouda = new Cheese("gouda");
        MeatFishEggs ham = new MeatFishEggs("ham");
        Food[] list_ing = new Food[]{salad, bread, cream, ham, tomato, gouda};
        Recipe sandwich = new Recipe(mealType, recipeName, portions, date, list_ing);

        button = findViewById(R.id.button_save);
        list = findViewById(R.id.shopping_list_text);
        shopping_list = findViewById(R.id.shopping_list_text);
        shopping_list.setText(sandwich.info());
        //AcessDistant a = new AcessDistant();
        //a.getIngredient();


        button.setOnClickListener(new View.OnClickListener() {
            //private Remote remote;

            @Override
            public void onClick(View v) {
                openActivity6();
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
    public void add_meal(View view){
        //final String name = etName.getText().toString();
        //final String price = etPrice.getText().toString();
        //final String desc = etDesc.getText().toString();

        class Product extends AsyncTask<Void, Void, String> {

            ProgressDialog pdLoading = new ProgressDialog(MainActivity5.this);

            @Override
            protected void onPreExecute() {
                super.onPreExecute();

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
                params.put("meal_name", recipeName);
                params.put("meal_type", mealType);
                params.put("meal_date", date);

                //returning the response
                return requestHandler.sendPostRequest(URL_ADD_MEAL, params);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                pdLoading.dismiss();

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

        Product prod_exec = new Product();
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