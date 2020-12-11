package com.example.foodappv1;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class PopUp {

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


}