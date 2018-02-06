package com.example.jsonacdat;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jsonacdat.pojo.GSONObject;
import com.example.jsonacdat.pojo.adapter.ClickListener;
import com.example.jsonacdat.pojo.adapter.RecyclerTouchListener;
import com.example.jsonacdat.pojo.adapter.ReposAdapter;

import java.util.ArrayList;

public class RetrofitActivity extends AppCompatActivity implements View.OnClickListener {

    EditText edT_URLRepo;
    Button btn_DescargarRepo;
    RecyclerView rvRepos;
    private ReposAdapter adapter;
    private ArrayList<GSONObject.Repositorio> repos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);

        edT_URLRepo = (EditText) findViewById(R.id.edT_URLRepo);
        btn_DescargarRepo = (Button) findViewById(R.id.btn_DescargarRepo);
        btn_DescargarRepo.setOnClickListener(this);
        rvRepos = (RecyclerView) findViewById(R.id.recyclerView);

        adapter = new ReposAdapter();
        rvRepos.setAdapter(adapter);
        rvRepos.setLayoutManager(new LinearLayoutManager(this));

        rvRepos.addOnItemTouchListener(new RecyclerTouchListener(this,
                rvRepos, new ClickListener() {
            @Override
            public void onClick(View view, final int position) {
                Uri uri = Uri.parse((String) repos.get(position).getUrl());
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                if (intent.resolveActivity(getPackageManager()) != null)
                    startActivity(intent);
                else
                    Toast.makeText(getApplicationContext(), "No hay un navegador",
                            Toast.LENGTH_SHORT).show();
            }
        }));
    }

    @Override
    public void onClick(View view) {

    }
}