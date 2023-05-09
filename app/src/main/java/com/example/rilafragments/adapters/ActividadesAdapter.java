package com.example.rilafragments.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rilafragments.APIs.ciudades.Actividad;
import com.example.rilafragments.R;
import com.example.rilafragments.constantes.Constantes;
import com.example.rilafragments.fragments.actividad.ActividadFragment;
import com.squareup.picasso.Picasso;

import java.text.NumberFormat;
import java.util.List;

public class ActividadesAdapter extends RecyclerView.Adapter<ActividadesAdapter.ActividadVH> {
    private List<Actividad> objects;
    private Context context;
    private int resource;

    NumberFormat format = NumberFormat.getCurrencyInstance();

    public ActividadesAdapter(List<Actividad> objects, Context context, int resource) {
        this.objects = objects;
        this.context = context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public ActividadVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View actividadView = LayoutInflater.from(context).inflate(resource, null);
        actividadView.setLayoutParams(
                new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT)
        );
        return new ActividadVH(actividadView);
    }

    @Override
    public void onBindViewHolder(@NonNull ActividadVH holder, int position) {
        Actividad actividad = objects.get(position);
        holder.lblTitulo.setText(actividad.getNombreActividad());
        holder.lblDescripcion.setText(actividad.getDescripcion());
        holder.lblPrecio.setText(format.format(actividad.getPrecio()));

        Picasso.get()
                .load(actividad.getUrlActividad())
                .placeholder(android.R.drawable.ic_popup_sync)
                .error(android.R.drawable.stat_notify_error)
                .into(holder.img);

        if(actividad.getPrecio() == 0){
            holder.btnComprar.setVisibility(View.GONE);
            System.out.println("GONE");
        }else
            holder.btnComprar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    System.out.println("CLICK");
                }
            });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager manager = ((FragmentActivity) view.getContext()).getSupportFragmentManager();
                NavHostFragment navHostFragment = (NavHostFragment) manager.findFragmentById(R.id.nav_host_fragment_content_main);

                FragmentTransaction fragmentTransaction = manager.beginTransaction();
                fragmentTransaction.detach(navHostFragment);

                ActividadFragment fragment = ActividadFragment.newInstance(actividad);

                fragmentTransaction.replace(R.id.contentAppBar, fragment, Constantes.FRAGMENT_DESTINO);
                fragmentTransaction.commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return objects.size();
    }

    public class ActividadVH extends RecyclerView.ViewHolder {
        TextView lblTitulo;
        TextView lblDescripcion;
        TextView lblPrecio;
        ImageView img;
        Button btnComprar;


        public ActividadVH(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.imgActividadesCard);
            lblTitulo = itemView.findViewById(R.id.lblTituloActividadesCard);
            lblDescripcion = itemView.findViewById(R.id.lblInfoActividadesCard);
            lblPrecio = itemView.findViewById(R.id.lblPrecioActividadesCard);
            btnComprar = itemView.findViewById(R.id.btnComprarActividadesCard);
        }
    }
}