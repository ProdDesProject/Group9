package com.example.foodappv1;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class PopUp {


    public static final String URL_SHOW_MEAL = "http://stulinux159.ipt.oamk.fi/chooseingredient.php?igd_name=Potato";

    public void showPopupWindow(final View view, String list) {


        //Create a View object yourself through inflater
        LayoutInflater inflater = (LayoutInflater) view.getContext().getSystemService(view.getContext().LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.pop_up, null);

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
        TextView text = popupView.findViewById(R.id.popUpText);
        text.setText(list);

        //Handler for clicking on the inactive zone of the window
        ImageButton close = popupView.findViewById(R.id.imageButton3);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //close Window
                popupWindow.dismiss();
            }
        });

    }
/*
    //Show meal
    public void show_meal(View view){
        final String meal_id = "2";

        class ShowMeal extends AsyncTask<Void, Void, String> {

            ProgressDialog pdLoading = new ProgressDialog(PopUp.this);

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

                //returing the response
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
                        Toast.makeText(MainActivity2, obj.getString("message"), Toast.LENGTH_LONG).show();
                        //Make TextViews Visible
                        text.setVisibility(View.VISIBLE);

                        //Set retrieved text to TextViews
                        text.setText("Name: "+obj.getString("meal_name"));

                    }
                } catch (Exception e ){
                    Toast.makeText(MainActivity2.this, "Exception: "+e, Toast.LENGTH_SHORT).show();
                }
            }
        }

        ShowMeal show = new ShowMeal();
        show.execute();
    }
*/

}