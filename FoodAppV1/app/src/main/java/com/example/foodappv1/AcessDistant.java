package com.example.foodappv1;

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

        //Log.d(tag:"Server", msg:"***********"+output);
        //%
        String[] message = output.split(regex: "%");
    }
}











