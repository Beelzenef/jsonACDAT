package com.example.jsonacdat;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.jsonacdat.network.RestClient;
import com.example.jsonacdat.pojo.Contacto;
import com.example.jsonacdat.utils.AnalisisJSON;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class ContactosActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    public static final String WEB = "http://alumno.mobi/~alumno/superior/guzman/contactos.json";

    ListView lista;
    ArrayList<Contacto> listaContactos;
    ArrayAdapter<Contacto> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactos);

        lista = (ListView) findViewById(R.id.listView);
    }

    public void onClick_obtenerContactos(View v) {
        switch (v.getId()) {
            case R.id.btn_ObtenerContactos:
                descarga(WEB);
                break;
        }
    }

    private void descarga(String web) {
        final ProgressDialog progreso = new ProgressDialog(this);
        RestClient.get(web, new JsonHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
                progreso.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progreso.setMessage("Conectando...");
                progreso.setCancelable(true);
                progreso.show();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    progreso.dismiss();
                    listaContactos = AnalisisJSON.analizarContactos(response);
                    mostrar();
                } catch (Exception e) {
                    Toast.makeText(ContactosActivity.this, "¡Error al mostrar contactos! :(",
                            Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                progreso.dismiss();
                Toast.makeText(ContactosActivity.this, "¡Ha fallado la descarga! :(",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void mostrar() {
        if (listaContactos != null)
            if (adapter == null) {
                adapter = new ArrayAdapter<Contacto>(this, android.R.layout.simple_list_item_1, listaContactos);
                lista.setAdapter(adapter);
            }
            else {
                adapter.clear();
                adapter.addAll(listaContactos);
            }
        else
            Toast.makeText(getApplicationContext(), "Error al crear la lista", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Contacto c = listaContactos.get(position);
        Toast.makeText(getApplicationContext(), "Movil: " + c.getTelefonos().getMovil(),
                Toast.LENGTH_SHORT).show();
    }


}
