package com.example.jsonacdat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick_lanzarExs(View v)
    {
        Intent unIntent = null;

        switch (v.getId())
        {
            case R.id.btn_Primitiva:
                unIntent = new Intent(MainActivity.this, PrimitivaActivity.class);
                break;
            case R.id.btn_Contactos:
                unIntent = new Intent(MainActivity.this, ContactosActivity.class);
                break;
            case R.id.btn_ContactosGSON:
                unIntent = new Intent(MainActivity.this, ContactosGSONActivity.class);
                break;
            case R.id.btn_Creacion:
                unIntent = new Intent(MainActivity.this, CreacionActivity.class);
                break;
            case R.id.btn_Retrofit:
                unIntent = new Intent(MainActivity.this, RetrofitActivity.class);
                break;
        }

        startActivity(unIntent);
    }
}
