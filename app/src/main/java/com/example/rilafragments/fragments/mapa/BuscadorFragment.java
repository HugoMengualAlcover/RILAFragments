package com.example.rilafragments.fragments.mapa;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rilafragments.APIs.Pais;
import com.example.rilafragments.R;
import com.example.rilafragments.adapters.PaisesAdapter;
import com.example.rilafragments.databinding.FragmentBuscadorBinding;

import java.util.ArrayList;


public class BuscadorFragment extends Fragment{

    private FragmentBuscadorBinding binding;

    private RecyclerView.LayoutManager layoutManager;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {



        BuscadorViewModel homeViewModel = new ViewModelProvider(this).get(BuscadorViewModel.class);

        binding = FragmentBuscadorBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        //Flipada q me pegao pa no tener q ir uno a uno
        setRecyclerViews(binding.contenedorEuropa, "Europa");
        setRecyclerViews(binding.contenedorAfrica, "Africa");
        setRecyclerViews(binding.contenedorAsia, "Asia");
        setRecyclerViews(binding.contenedorAmerica, "America");
        setRecyclerViews(binding.contenedorOceania, "Oceania");

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

    public void setRecyclerViews(RecyclerView contenedor, String continente){
        ArrayList<Pais> paisesList = new ArrayList<Pais>();//Lo rellenamos con los paises de continente de la api
        paisesList.add(new Pais("Hola"));
        paisesList.add(new Pais("Hal"));
        paisesList.add(new Pais("Que"));

        PaisesAdapter adapter = new PaisesAdapter(paisesList, R.layout.pais_button_model, this.getContext());
        layoutManager = new GridLayoutManager(this.getContext(), 3);

        //adapter.set         Por acabar

        contenedor.setLayoutManager(layoutManager);
        contenedor.setAdapter(adapter);
    }
}