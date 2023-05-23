package com.example.rilafragments.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.Group;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rilafragments.APIs.continente.Pais;
import com.example.rilafragments.R;
import com.example.rilafragments.constantes.Constantes;
import com.example.rilafragments.fragments.ciudadesPueblos.CiudadesYPueblosFragment;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PaisesAdapter extends RecyclerView.Adapter<PaisesAdapter.PaisVH> {

    private List<Pais> objects;
    private List<Pais> objectsSearch;
    private Context context;
    private int resource;
    Group label;


    private Target loadtarget;

    public PaisesAdapter(List<Pais> objects, int resource, Context context) {
        this.objects = objects;
        this.context = context;
        this.resource = resource;
        this.label = label;

        objectsSearch = new ArrayList<>();
        objectsSearch.addAll(objects);
    }

    @NonNull
    @Override
    public PaisesAdapter.PaisVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View paisView = LayoutInflater.from(context).inflate(resource, null);
        paisView.setLayoutParams(
                new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT)
        );
        return new PaisVH(paisView);
    }

    @Override
    public void onBindViewHolder(@NonNull PaisesAdapter.PaisVH holder, int position) {
        Pais pais = objectsSearch.get(position);
        holder.btnPais.setText(pais.getNombre());

        loadtarget = new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                Drawable bdrawable = new BitmapDrawable(context.getResources(), bitmap);
                holder.btnPais.setCompoundDrawablesWithIntrinsicBounds(bdrawable, null, null, null);
            }
            @Override
            public void onBitmapFailed(Exception e, Drawable errorDrawable) {
                System.out.println("Error Cargando Imagen");
                System.out.println(pais.getBandera());
                e.printStackTrace();
            }
            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {

            }
        };


        Picasso.get()
                .load(pais.getBandera())
                .placeholder(android.R.drawable.ic_popup_sync)
                .error(android.R.drawable.stat_notify_error)
                .resize(60, 40)
                .into(loadtarget);


        holder.btnPais.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager manager = ((FragmentActivity) view.getContext()).getSupportFragmentManager();
                NavHostFragment navHostFragment = (NavHostFragment) manager.findFragmentById(R.id.nav_host_fragment_content_main);

                FragmentTransaction fragmentTransaction = manager.beginTransaction();
                fragmentTransaction.detach(navHostFragment);

                CiudadesYPueblosFragment fragment = CiudadesYPueblosFragment.newInstance(
                        pais.getNombre(), pais.getCiudades()
                );
                fragmentTransaction.replace(R.id.contentAppBar, fragment, Constantes.FRAGMENT_CIUDADES_Y_PUEBLOS);
                fragmentTransaction.commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return objectsSearch.size();
    }

    public class PaisVH extends RecyclerView.ViewHolder {
        Button btnPais;
        public PaisVH(@NonNull View itemView) {
            super(itemView);
            btnPais = itemView.findViewById(R.id.btnPais);
        }
    }

    public void filtrar(String input, LinearLayout label){
        System.out.println("0");
        label.setVisibility(View.VISIBLE);
        if(input.length() == 0){
            objectsSearch.clear();
            objectsSearch.addAll(objects);
            System.out.println("1");
        }else{
            List<Pais> collection = objects.stream().
                    filter(i -> i.getNombre().toLowerCase().contains(input.toLowerCase())).collect(Collectors.toList());
            objectsSearch.clear();
            objectsSearch.addAll(collection);
        }
        if(objectsSearch.isEmpty()){
            label.setVisibility(View.GONE);
            System.out.println("2");
        }
        notifyDataSetChanged();
    }

}