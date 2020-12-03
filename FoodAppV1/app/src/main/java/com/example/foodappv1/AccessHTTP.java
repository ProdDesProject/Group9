package com.example.foodappv1;

import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class AccessHTTP extends AsyncTask<String, Integer, Long > {

    private ArrayList<NameValuePair> parameters;
    private String ret= null;
    public AsyncResponse delegate = null;



    /**
     * builder
     */
    public AccessHTTP(){
        parameters = new ArrayList<NameValuePair>();
    }

    /**
     * Add parameter post
     * @param name
     * @param value
     */
    public void addParam(String name, String value){
        parameters.add(new BasicNameValuePair(name, value));
    }

    /**
     * connection in the background in a separate thread
     * @param strings
     * @return
     */
    @Override
    protected Long doInBackground(String... strings) {

        HttpClient cnxHttp = new DefaultHttpClient();
        HttpPost paramCnx = new HttpPost(strings[0]);

        try {
            //encoding the parameters
            paramCnx.setEntity(new UrlEncodedFormEntity(parameters));
            //connection and send of parameters and wait for response
            HttpResponse response = cnxHttp.execute(paramCnx);
            // Transformation of the reponse
            ret = EntityUtils.toString(response.getEntity());

        } catch (UnsupportedEncodingException e){

            Log.d(  "Error encoding","**************"+e.toString());
        } catch (ClientProtocolException e) {

            Log.d(  "Error protocol","**************"+e.toString());
        } catch (IOException e) {

            Log.d(  "Error I/O","**************"+e.toString());        }
        return null;
    }

    @Override
    protected void onPostExecute(Long result){
        delegate.processFinish(ret.toString());
    }
}
























