package com.example.foodappv1;

import android.util.Log;

import org.json.JSONArray;


public class AcessDistant implements AsyncResponse {
    //constant
    private static final String SERVERADDR = "http://stulinux159.ipt.oamk.fi/data.php";
    private Remote remote;

    public AcessDistant() {
        remote = Remote.getInstance(null);
    }


    /**
     * return from remote server
     *
     * @param output
     */

    @Override
    public void processFinish(String output) {

        Log.d("Server", "*********" + output);
        //%
        String[] message = output.split("%");
        //message[0]=  "chooseingredient", "addingredient" ,"showmeal","addmeal", "Error  !"
        //message[1]=  The rest of the message

        //2 cases
        if (message.length > 1) {
            if (message[0].equals("chooseingredient")) {
                Log.d("chooseingredient", "*************" + message[1]);
            } else {
                if (message[0].equals("addingredient")) {
                    Log.d("addingredient", "*************" + message[1]);
                } else {
                    if (message[0].equals("showmeal")) {
                        Log.d("showmeal", "*************" + message[1]);
                    } else {
                        if (message[0].equals("addmeal")) {
                            Log.d("addmeal", "*************" + message[1]);
                        } else {
                            if (message[0].equals("error  !")) {
                                Log.d("error  !", "*************" + message[1]);
                            }
                        }
                    }
                }
            }
        }
    }
    public void send (String operation, JSONArray jsonData){
        AccessHTTP accessData = new AccessHTTP();
        //link of delegation
        accessData.delegate = this;
        //Add parameter

        accessData.addParam("operation", operation);
        //accessData.addParam("theingredients", jsonData.toString());
        accessData.addParam("themeals", jsonData.toString());
        //Call to server
        accessData.execute(SERVERADDR);
    }
}