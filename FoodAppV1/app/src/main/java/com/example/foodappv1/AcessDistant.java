package com.example.foodappv1;

import android.util.Log;

import org.json.JSONArray;


public class AcessDistant implements AsyncResponse {
    //constant
    private static final String SERVERADDR="http://stulinux159.ipt.oamk.fi/info.php";
    public AcessDistant(){
        super();
    }


    /**
     *
     * return from remote server
     * @param output
     */



    @Override
    public void processFinish(String output) {

        Log.d("Server", "*********"+output);
        //%
        String[] message = output.split("%");
        //message[0]=  "save", "last" , "error"
        //message[1]=  The rest of the message

        //2 cases
        if (message.length>1)
        {
            if (message[0].equals("save")){
                Log.d("save",  "*************"+message[1]);
            } else {
                if (message[0].equals("last")) {
                    Log.d("last", "*************" + message[1]);
                } else {
                    if (message[0].equals("error")) {
                        Log.d("error  !", "*************" + message[1]);
                    }
                }
            }
        }
    }

    public void send(String operation, JSONArray jsonData){
        AccessHTTP accessData = new AccessHTTP();
        //link of delegation
        accessData.delegate = this;
        //Add parameter

        accessData.addParam( "operation", operation);
        accessData.addParam( "The data", jsonData.toString());
        //Call to server
        accessData.execute(SERVERADDR);

    }
}











