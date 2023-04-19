package com.example.rilafragments.adapters;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rilafragments.APIs.ciudades.Actividades;
import com.example.rilafragments.APIs.ciudades.Ciudad;
import com.example.rilafragments.APIs.continente.Pais;
import com.example.rilafragments.R;
import com.example.rilafragments.constantes.Constantes;
import com.example.rilafragments.fragments.ciudadesPueblos.CiudadesYPueblosFragment;
import com.example.rilafragments.fragments.destino.DestinoFragment;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CiudadesAdapter extends RecyclerView.Adapter<CiudadesAdapter.CiudadVH> {

    private List<Ciudad> objects;
    private Context context;
    private int resource;

    public CiudadesAdapter(List<Ciudad> objects, int resource, Context context) {
        this.objects = objects;
        this.context = context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public CiudadesAdapter.CiudadVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View ciudadView = LayoutInflater.from(context).inflate(resource, null);
        ciudadView.setLayoutParams(
                new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT)
        );

        return new CiudadVH(ciudadView);
    }

    @Override
    public void onBindViewHolder(@NonNull CiudadesAdapter.CiudadVH holder, int position) {
        Ciudad ciudad = objects.get(position);
        holder.lblTitulo.setText(ciudad.getNombre());
        holder.lblInfo.setText(ciudad.getDescripcion());

        Picasso.get()
                .load(ciudad.getUrlCiudad())
                .placeholder(android.R.drawable.ic_popup_sync)
                .error(android.R.drawable.stat_notify_error)
                .into(holder.imageCiudad);

        holder.btnFavoritos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //ToDo Añadir a los favoritos del usuario
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager manager = ((FragmentActivity) view.getContext()).getSupportFragmentManager();
                NavHostFragment navHostFragment = (NavHostFragment) manager.findFragmentById(R.id.nav_host_fragment_content_main);

                FragmentTransaction fragmentTransaction = manager.beginTransaction();
                fragmentTransaction.detach(navHostFragment);

                DestinoFragment fragment = new DestinoFragment();
                fragment.setArguments(setBundle(ciudad)); //Le pasamos el bundle con los datos de la ciudad que ha abierto

                fragmentTransaction.replace(R.id.contentAppBar, fragment, Constantes.FRAGMENT_DESTINO);
                fragmentTransaction.commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return objects.size();
    }

    public class CiudadVH extends RecyclerView.ViewHolder {
        TextView lblTitulo;
        TextView lblInfo;
        ImageButton btnFavoritos;
        ImageView imageCiudad;
        public CiudadVH(@NonNull View itemView) {
            super(itemView);

            lblTitulo = itemView.findViewById(R.id.lblTituloCiudadesPueblosCard);
            lblTitulo = itemView.findViewById(R.id.lblInfoCiudadesPueblosCard);
            lblTitulo = itemView.findViewById(R.id.btnStarCiudadesPueblosCard);
            imageCiudad = itemView.findViewById(R.id.imgCiudadesPueblosCard);
        }
    }

    public Bundle setBundle(Ciudad ciudad){
        ArrayList<Actividades> actividades = (ArrayList<Actividades>) ciudad.getActivities();

        Bundle bundle = new Bundle();
        bundle.putString(Constantes.CIUDAD_NAME, ciudad.getNombre());
        bundle.putSerializable(Constantes.CIUDAD_ACTIVIDADES, actividades);
        return bundle;
    }
}
