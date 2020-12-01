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

    private static String tag ;
    private static String regex ;
    private static String msg ;

    @Override
    public void processFinish(String output) {

        Log.d(tag="Server", "*********"+output);
        //%
        String[] message = output.split(regex="%");
        //message[0]=  "save", "last" , "error"
        //message[1]=  The rest of the message

        //2 cases
        if (message.length>1)
        {
            if (message[0].equals("save")){
                Log.d(tag="save",  msg= "*************"+message[1]);
            } else {
                if (message[0].equals("last")) {
                    Log.d(tag = "last", msg = "*************" + message[1]);
                } else {
                    if (message[0].equals("error")) {
                        Log.d(tag = "error  !", msg = "*************" + message[1]);
                    }
                }
            }
        }
    }

    private static String name ;
    public void send(String operation, JSONArray jsonData){
        AccessHTTP accessData = new AccessHTTP();
        //link of delegation
        accessData.delegate = this;
        //Add parameter

        accessData.addParam( name="operation", operation);
        accessData.addParam( name="The data", jsonData.toString());
        //Call to server
        accessData.execute(SERVERADDR);

    }
}











