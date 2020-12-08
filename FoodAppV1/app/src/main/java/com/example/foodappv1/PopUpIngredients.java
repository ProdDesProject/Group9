package com.example.foodappv1;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import org.json.JSONArray;

public class PopUpIngredients {

    private static AcessDistant accessDistant;
    private static Ingredient ingredient;
    private static PopUpIngredients instance = null;
    private static Context contexte;


    PopUpIngredients() { super(); }


    public static final PopUpIngredients getInstance(Context contexte) {
        if (contexte != null){
            PopUpIngredients.contexte=contexte;
        }
        if (PopUpIngredients.instance == null) {
            PopUpIngredients.instance= new PopUpIngredients();
            accessDistant = new AcessDistant();
            accessDistant.send(  "chooseingredient", new JSONArray());
        }
        return PopUpIngredients.instance;
    }

    public void addIngredient(String ingredientName, String category){
        ingredient = new Ingredient(ingredientName, category);
        accessDistant.send(  "addingredient", ingredient.convertToJSONArray());
    }

    public void setIngredient(Ingredient ingredient){
        PopUpIngredients.ingredient=ingredient;
        //((MainActivity4).contexte).getIngredient();
    }

    public void showPopupWindow(final View view) {

        //Create a View object yourself through inflater
        LayoutInflater inflater = (LayoutInflater) view.getContext().getSystemService(view.getContext().LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.pop_up_ingredients, null);

        //Specify the length and width through constants
        int width = RelativeLayout.LayoutParams.MATCH_PARENT;
        int height = RelativeLayout.LayoutParams.MATCH_PARENT;

        //Make Inactive Items Outside Of PopupWindow
        boolean focusable = true;

        //Create a window with our parameters
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

        //Set the location of the window on the screen
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

        //Initialize the elements of our window, install the handler
        EditText txtIngredient = (EditText) popupView.findViewById(R.id.ingredient_name);
        String ingredient = txtIngredient.getText().toString();

        RadioGroup radioGrp = (RadioGroup) popupView.findViewById(R.id.radioGroup_ingredients);
        int radioId = radioGrp.getCheckedRadioButtonId();
        RadioButton radioButton = popupView.findViewById(radioId);

        Button add = popupView.findViewById(R.id.add_ingredient);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO
                //close Window
                popupWindow.dismiss();
            }
        });

    }


}