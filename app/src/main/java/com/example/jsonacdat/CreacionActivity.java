package com.example.jsonacdat;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.jsonacdat.network.RestClient;
import com.example.jsonacdat.pojo.Noticia;
import com.example.jsonacdat.utils.AnalisisJSON;
import com.example.jsonacdat.utils.AnalisisXML;
import com.loopj.android.http.FileAsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class CreacionActivity extends AppCompatActivity {

    public static final String CANAL = "https://geekstorming.wordpress.com/feed/";

    public static final String RESULTADO_JSON = "resultado.json";
    public static final String RESULTADO_GSON = "resultado_gson.json";
    public static final String TEMPORAL = "feed.xml";

    ArrayList<Noticia> noticias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creacion);

    }

    public void onClick_descargarFeedToJSON(View v) {
        switch (v.getId()) {
            case R.id.btn_DescargarFeedToJSON:
                descarga(CANAL, TEMPORAL);
                break;
        }
    }

    private void descarga(String canal, String temporal) {
        final ProgressDialog progreso = new ProgressDialog(this);
        RestClient.get(canal, new FileAsyncHttpResponseHandler(this) {
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, File file) {
                progreso.dismiss();
                Toast.makeText(CreacionActivity.this, "Algo ha salido mal... :(",
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, File file) {
                try {
                    progreso.dismiss();
                    noticias = AnalisisXML.analizarNoticias(file);
                    // Guardar en fichero
                    AnalisisJSON.escribirJSON(noticias, RESULTADO_JSON);
                } catch (Exception e) {
                    Toast.makeText(CreacionActivity.this, "¡Error! :(",
                            Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onStart() {
                super.onStart();
                progreso.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progreso.setMessage("Descargando");
                progreso.setCancelable(false);
                progreso.show();
            }
        });
    }
}
