package com.example.rilafragments.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rilafragments.APIs.Pais;
import com.example.rilafragments.R;
import com.example.rilafragments.constantes.Constantes;
import com.example.rilafragments.fragments.ciudadesPueblos.CiudadesYPueblosFragment;
import com.example.rilafragments.fragments.mapa.BuscadorFragment;

import java.util.List;

public class PaisesAdapter extends RecyclerView.Adapter<PaisesAdapter.PaisVH> {

    private List<Pais> objects;
    private Context context;
    private int resource;

    public PaisesAdapter(List<Pais> objects, int resource, Context context) {
        this.objects = objects;
        this.context = context;
        this.resource = resource;
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
        Pais pais = objects.get(position);
        holder.btnPais.setText(pais.getNombre());


        holder.btnPais.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("btn", "Click");
                FragmentManager manager = ((FragmentActivity) view.getContext()).getSupportFragmentManager();
                NavHostFragment navHostFragment = (NavHostFragment) manager.findFragmentById(R.id.nav_host_fragment_content_main);

                FragmentTransaction fragmentTransaction = manager.beginTransaction();
                fragmentTransaction.detach(navHostFragment);
                fragmentTransaction.replace(R.id.contentAppBar, new CiudadesYPueblosFragment(), Constantes.fragmentCiudadesYPueblos);
                fragmentTransaction.commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return objects.size();
    }

    public class PaisVH extends RecyclerView.ViewHolder {
        Button btnPais;
        public PaisVH(@NonNull View itemView) {
            super(itemView);
            btnPais = itemView.findViewById(R.id.btnPais);
        }
    }
}
