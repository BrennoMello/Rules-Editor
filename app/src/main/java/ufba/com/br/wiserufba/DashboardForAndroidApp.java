package ufba.com.br.wiserufba;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by brenno on 01/08/17.
 */

import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.content.Intent;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;


public class DashboardForAndroidApp extends AppCompatActivity implements View.OnClickListener
{

        ProgressBar myprogressBar;
        TextView progressingTextView;
        Handler progressHandler = new Handler();
        int i = 0;
    RequestQueue queue;
    ImageButton btnRule;
    EditText txtValor;


    public void doPost() {

        String url = "http://192.168.0.108:8181/cxf/lamp/devices/actuator/lamp/";
        StringRequest request2 =  new StringRequest(
                Request.Method.POST,
                url,

                new Response.Listener<String>(){
                    @Override
                    public void onResponse(String response) {
                        System.out.println(response.substring(1));
                    }
                },
                new  Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println(error.getMessage());
                    }
                }){
                  @Override
                  public Map<String, String> getParams(){
                   Map<String, String> params = new HashMap<>();
                   params.put("status", "true");

                   return params;

        }};
        /*
        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.POST,
                url,
                jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                      System.out.println(response.optString("status"));
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println(error.getMessage());
                    }
                });
                */
        queue.add(request2);
    }


        @Override
        protected void onCreate(Bundle savedInstanceState) {
             super.onCreate(savedInstanceState);
             setContentView(R.layout.dashboard_xml_ui_design);
             queue  = Volley.newRequestQueue(this)  ;
             btnRule = (ImageButton) findViewById(R.id.button_id4);
             this.txtValor = (EditText) findViewById(R.id.message);
             btnRule.setOnClickListener(this);





            /*
            myprogressBar = (ProgressBar) findViewById(R.id.progressBar);
            progressingTextView = (TextView) findViewById(R.id.progress_circle_text);

            new Thread(new Runnable() {
                public void run() {
                    while (i < 100) {
                        i += 2;
                        progressHandler.post(new Runnable() {
                            public void run() {
                                myprogressBar.setProgress(i);
                                progressingTextView.setText("" + i + " %");
                            }
                        });
                        try {
                            Thread.sleep(300);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
            */
        }





        @Override
        public void onClick(View v) {

            Intent intent = new Intent(DashboardForAndroidApp.this, DashboardForAndroidApp.class);
            startActivity(intent);

            final AlertDialog.Builder alertD = new AlertDialog.Builder(this);
            if(TextUtils.isEmpty(this.txtValor.getText().toString())){
                alertD.setMessage("Enter a value");
                alertD.show();

            }else {

                alertD.setMessage("Rule Configured");
                alertD.show();
                doPost();
            }

        }








}

