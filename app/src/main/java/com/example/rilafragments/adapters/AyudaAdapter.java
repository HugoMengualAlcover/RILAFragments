package com.example.rilafragments.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.rilafragments.R;
import com.example.rilafragments.fragments.ayuda.AyudaModel;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AyudaAdapter extends RecyclerView.Adapter<AyudaAdapter.AyudaVH> {

    private List<AyudaModel> objects;
    private List<AyudaModel> objectsSearch;
    private Context context;
    private int resource;

    public AyudaAdapter(List<AyudaModel> objects, Context context, int resource) {
        this.objects = objects;
        this.context = context;
        this.resource = resource;

        objectsSearch = new ArrayList<>();
        objectsSearch.addAll(objects);
    }


    @NonNull
    @Override
    public AyudaAdapter.AyudaVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View ayudaView = LayoutInflater.from(context).inflate(resource, null);
        ayudaView.setLayoutParams(
                new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT)
        );

        return new AyudaVH(ayudaView);
    }

    @Override
    public void onBindViewHolder(@NonNull AyudaAdapter.AyudaVH holder, int position) {
        AyudaModel ayuda = objectsSearch.get(position);
        holder.lblTitulo.setText(ayuda.getTitulo());
        holder.lblTexto.setText(ayuda.getTexto());
    }

    @Override
    public int getItemCount() {
        return objectsSearch.size();
    }

    public class AyudaVH extends RecyclerView.ViewHolder {
        TextView lblTitulo;
        TextView lblTexto;
        public AyudaVH(@NonNull View itemView) {
            super(itemView);

            lblTitulo = itemView.findViewById(R.id.lblTituloAyudaCard);
            lblTexto = itemView.findViewById(R.id.lblTextoAyudaCard);
        }
    }

    public void filtrar(String input){
        System.out.println("0");
        if(input.length() == 0){
            objectsSearch.clear();
            objectsSearch.addAll(objects);
            System.out.println("1");
        }else{
            List<AyudaModel> collection = objects.stream().
                    filter(i -> i.getTitulo().toLowerCase().contains(input.toLowerCase())).collect(Collectors.toList());
            objectsSearch.clear();
            objectsSearch.addAll(collection);
        }
        notifyDataSetChanged();
    }
}
