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
import com.example.jsonacdat.pojo.ContactoGSON;
import com.example.jsonacdat.pojo.ListaContactosGSON;
import com.google.gson.Gson;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class ContactosGSONActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    public static final String WEB = "http://alumno.mobi/superior/guzman/contactos.json";

    ListView lista;

    ArrayAdapter<ContactoGSON> adapter;
    ListaContactosGSON lectorContactosGSON;

    Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactos);

        lectorContactosGSON = new ListaContactosGSON();

        gson = new Gson();

        lista = (ListView) findViewById(R.id.listView);
        lista.setOnItemClickListener(this);
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
                    lectorContactosGSON = (ListaContactosGSON) gson.fromJson(response.toString(), ListaContactosGSON.class);
                    mostrar();
                } catch (Exception e) {
                    Toast.makeText(ContactosGSONActivity.this, "Error al leer contactos D:",
                            Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                progreso.dismiss();
                Toast.makeText(ContactosGSONActivity.this, "¡Ha fallado la descarga! :(",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void mostrar() {
        if (lectorContactosGSON != null)
            if (adapter == null) {
                adapter = new ArrayAdapter<ContactoGSON>(this, android.R.layout.simple_list_item_1,
                        lectorContactosGSON.getContacts());
                lista.setAdapter(adapter);
            }
            else {
                adapter.clear();
                adapter.addAll(lectorContactosGSON.getContacts());
            }
        else
            Toast.makeText(getApplicationContext(), "Error al crear la lista", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(ContactosGSONActivity.this,
                "Movil: " + lectorContactosGSON.getContacts().get(position).getPhone().getMovil(),
                Toast.LENGTH_SHORT).show();
    }
}
