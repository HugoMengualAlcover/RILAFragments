package com.example.rilafragments.fragments.buscador;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rilafragments.APIs.conexiones.APIConexiones;
import com.example.rilafragments.APIs.conexiones.RetrofitObject;
import com.example.rilafragments.APIs.continente.ApiResponse;
import com.example.rilafragments.APIs.continente.Continente;
import com.example.rilafragments.APIs.continente.Pais;
import com.example.rilafragments.R;
import com.example.rilafragments.adapters.PaisesAdapter;
import com.example.rilafragments.databinding.FragmentBuscadorBinding;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class BuscadorFragment extends Fragment implements SearchView.OnQueryTextListener {

    private FragmentBuscadorBinding binding;
    private RecyclerView.LayoutManager layoutManager;

    public ArrayList<Continente> continentes = new ArrayList<Continente>() {
    };
    PaisesAdapter adapterEur;
    PaisesAdapter adapterAme;
    PaisesAdapter adapterAsi;
    PaisesAdapter adapterAfr;
    PaisesAdapter adapterOce;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        //BuscadorViewModel homeViewModel = new ViewModelProvider(this).get(BuscadorViewModel.class);

        binding = FragmentBuscadorBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        doGetContinentes();

        binding.buscarView.setOnQueryTextListener(this);

        /*final TextView textView = binding.textHome;
        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);*/
        //getText es una funcion del ViewModel, viene por defecto, cambiar cuando sepamos que hacer con él
        
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void setRecyclerViews(RecyclerView contenedor, String continente, PaisesAdapter adapter, TextView label){

        List<Pais> paisesList;
        paisesList = getPaises(continente);

        adapter = new PaisesAdapter(paisesList, R.layout.pais_button_model, this.getContext(), label);
        layoutManager = new GridLayoutManager(this.getContext(), 3);

        contenedor.setLayoutManager(layoutManager);
        contenedor.setAdapter(adapter);


    }

    public void doGetContinentes(){
        Retrofit retrofit = RetrofitObject.getConnection();
        APIConexiones api = retrofit.create(APIConexiones.class);

        Call<ApiResponse> getContinentes = api.getContinentes();
        getContinentes.enqueue(new Callback<ApiResponse>() {

            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                List<Continente> resp = response.body().getData();
                continentes.addAll(resp);

                setRecyclerViews(binding.contenedorEuropa, "Europa", adapterEur, binding.labelEuropaBuscadorFragment);
                setRecyclerViews(binding.contenedorAfrica, "Africa", adapterAfr, binding.labelAfricaBuscadorFragment);
                setRecyclerViews(binding.contenedorAsia, "Asia", adapterAsi, binding.labelAsiaBuscadorFragment);
                setRecyclerViews(binding.contenedorAmerica, "America", adapterAfr, binding.labelAmericaBuscadorFragment);
                setRecyclerViews(binding.contenedorOceania, "Oceania", adapterOce, binding.labelOceaniaBuscadorFragment);
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                Toast.makeText(getContext(), "ERROR DE CONEXIÓN", Toast.LENGTH_SHORT).show();
                Log.e("FAILURE", t.getLocalizedMessage());
            }
        });
    }

    public List<Pais> getPaises(String continente){
        for (Continente c : continentes){
            if (c.getNombreContinente().equalsIgnoreCase(continente))
                return c.getPaises();
        }
        return new ArrayList<>();
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        adapterEur.filtrar(s);
        adapterAfr.filtrar(s);
        adapterAme.filtrar(s);
        adapterAsi.filtrar(s);
        adapterOce.filtrar(s);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        return false;
    }
}