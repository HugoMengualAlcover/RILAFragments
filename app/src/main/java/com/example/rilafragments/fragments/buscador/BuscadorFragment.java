package com.example.rilafragments.fragments.buscador;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.rilafragments.APIs.conexiones.APIConexiones;
import com.example.rilafragments.APIs.conexiones.RetrofitObject;
import com.example.rilafragments.APIs.continente.ApiResponseContinentes;
import com.example.rilafragments.APIs.continente.Continente;
import com.example.rilafragments.APIs.continente.Pais;
import com.example.rilafragments.R;
import com.example.rilafragments.adapters.PaisesAdapter;
import com.example.rilafragments.databinding.FragmentBuscadorBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class BuscadorFragment extends Fragment implements SearchView.OnQueryTextListener {

    private FragmentBuscadorBinding binding;

    public ArrayList<Continente> continentes = new ArrayList<Continente>();
    PaisesAdapter adapterEur;
    PaisesAdapter adapterAme;
    PaisesAdapter adapterAsi;
    PaisesAdapter adapterAfr;
    PaisesAdapter adapterOce;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentBuscadorBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        doGetContinentes();

        binding.buscarView.setOnQueryTextListener(this);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void doGetContinentes(){
        Retrofit retrofit = RetrofitObject.getConnection();
        APIConexiones api = retrofit.create(APIConexiones.class);

        Call<ApiResponseContinentes> getContinentes = api.getContinentes();
        getContinentes.enqueue(new Callback<ApiResponseContinentes>() {

            @Override
            public void onResponse(Call<ApiResponseContinentes> call, Response<ApiResponseContinentes> response) {
                List<Continente> resp = response.body().getData();
                continentes.addAll(resp);

                //Europa
                adapterEur = new PaisesAdapter(getPaises("Europa"), R.layout.pais_button_model, BuscadorFragment.this.getContext());

                binding.contenedorEuropa.setLayoutManager(new GridLayoutManager(BuscadorFragment.this.getContext(), 3));
                binding.contenedorEuropa.setAdapter(adapterEur);

                //America
                adapterAme = new PaisesAdapter(getPaises("America"), R.layout.pais_button_model, BuscadorFragment.this.getContext());

                binding.contenedorAmerica.setLayoutManager( new GridLayoutManager(BuscadorFragment.this.getContext(), 3));
                binding.contenedorAmerica.setAdapter(adapterAme);

                //Asia
                adapterAsi = new PaisesAdapter(getPaises("Asia"), R.layout.pais_button_model, BuscadorFragment.this.getContext());

                binding.contenedorAsia.setLayoutManager(new GridLayoutManager(BuscadorFragment.this.getContext(), 3));
                binding.contenedorAsia.setAdapter(adapterAsi);

                //Africa
                adapterAfr = new PaisesAdapter(getPaises("Africa"), R.layout.pais_button_model, BuscadorFragment.this.getContext());

                binding.contenedorAfrica.setLayoutManager(new GridLayoutManager(BuscadorFragment.this.getContext(), 3));
                binding.contenedorAfrica.setAdapter(adapterAfr);

                //Oceania
                adapterOce = new PaisesAdapter(getPaises("Oceania"), R.layout.pais_button_model, BuscadorFragment.this.getContext());

                binding.contenedorOceania.setLayoutManager(new GridLayoutManager(BuscadorFragment.this.getContext(), 3));
                binding.contenedorOceania.setAdapter(adapterOce);
            }

            @Override
            public void onFailure(Call<ApiResponseContinentes> call, Throwable t) {
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
        adapterEur.filtrar(s, binding.europaGroup);
        adapterAfr.filtrar(s, binding.africaGroup);
        adapterAme.filtrar(s, binding.americaGroup);
        adapterAsi.filtrar(s, binding.asiaGroup);
        adapterOce.filtrar(s, binding.oceaniaGroup);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        adapterEur.filtrar(s, binding.europaGroup);
        adapterAfr.filtrar(s, binding.africaGroup);
        adapterAme.filtrar(s, binding.americaGroup);
        adapterAsi.filtrar(s, binding.asiaGroup);
        adapterOce.filtrar(s, binding.oceaniaGroup);
        return false;
    }
}