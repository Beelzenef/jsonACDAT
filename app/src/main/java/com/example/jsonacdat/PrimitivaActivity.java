package com.example.jsonacdat;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.jsonacdat.network.MySingleton;
import com.example.jsonacdat.utils.AnalisisJSON;

import org.json.JSONException;
import org.json.JSONObject;

public class PrimitivaActivity extends AppCompatActivity {

    public static final String TAG = "MyTag";
    public static final String WEB = "http://192.168.3.57/acceso/primitiva.json";
    //public static final String WEB = "https://portadaalta.mobi/acceso/primitiva.json";
    Button btn_DescargarPrimitiva;
    TextView txtV_Primitiva;
    RequestQueue mRequestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_primitiva);

        btn_DescargarPrimitiva = (Button) findViewById(R.id.btn_DescargarPrimitiva);
        txtV_Primitiva = (TextView) findViewById(R.id.txtV_Primitiva);

        mRequestQueue = MySingleton.getInstance(this.getApplicationContext()).getRequestQueue();
    }

    public void onClick_Primitiva(View view) {
        switch (view.getId()) {
            case R.id.btn_DescargarPrimitiva:
                descargarPrimitiva();
                break;
        }
    }

    private void descargarPrimitiva() {

        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, WEB, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            txtV_Primitiva.setText(AnalisisJSON.analizarPrimitiva(response));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        txtV_Primitiva.setText("Se ha producido un error :(");

                    }
                });


        // Set the tag on the request.
        jsObjRequest.setTag(TAG);
        // Set retry policy
        jsObjRequest.setRetryPolicy(new DefaultRetryPolicy(3000, 1, 1));
        // Add the request to the RequestQueue.
        mRequestQueue.add(jsObjRequest);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(TAG);
        }
    }
}
