package com.example.jsonacdat.pojo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jsonacdat.R;
import com.example.jsonacdat.pojo.GSONObject;

import java.util.ArrayList;

/**
 * Adapter para repositorios
 */

public class ReposAdapter extends RecyclerView.Adapter<ReposAdapter.ViewHolder>  {

    private ArrayList<GSONObject.Repositorio> listaRepositorios;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View repoView = inflater.inflate(R.layout.item_view, parent, false);

        ViewHolder viewHolder = new ViewHolder(repoView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        GSONObject.Repositorio repositorio = listaRepositorios.get(position);

        holder.txtV_NombreRepo.setText(repositorio.getName());
        holder.txtV_Descripcion.setText(repositorio.getDescription());
        holder.txtV_URL.setText(repositorio.getUrl());
    }

    @Override
    public int getItemCount() {
        return listaRepositorios.size();
    }

    public ReposAdapter() {
        this.listaRepositorios = new ArrayList<>();
    }

    public void clear() {
        this.listaRepositorios.clear();
    }

    public void addAll(ArrayList<GSONObject.Repositorio> lista) {
        this.listaRepositorios.addAll(lista);
        notifyDataSetChanged();
    }

    public ReposAdapter(ArrayList<GSONObject.Repositorio> listaRepositorios) {
        this.listaRepositorios = listaRepositorios;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtV_NombreRepo;
        TextView txtV_URL;
        TextView txtV_Descripcion;

        public ViewHolder(final View itemView) {
            super(itemView);

            txtV_NombreRepo = (TextView) itemView.findViewById(R.id.txtV_NombreRepo);
            txtV_Descripcion = (TextView) itemView.findViewById(R.id.txtV_Descripcion);
            txtV_URL = (TextView) itemView.findViewById(R.id.txtV_URLRepo);
        }
    }
}
