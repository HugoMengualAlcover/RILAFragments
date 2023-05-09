package com.example.rilafragments.fragments.destinosRecomendados;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rilafragments.APIs.ciudades.Actividad;
import com.example.rilafragments.R;
import com.example.rilafragments.adapters.ActividadesAdapter;
import com.example.rilafragments.constantes.Constantes;
import com.example.rilafragments.databinding.FragmentDestinosRecomendadosBinding;

import java.io.Serializable;
import java.util.List;

public class DestinosRecomendadosFragment extends Fragment {

    private FragmentDestinosRecomendadosBinding binding;
    private String ciudadName;
    private List<Actividad> actividadesList;

    private ActividadesAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    public DestinosRecomendadosFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param ciudadName Parameter 1.
     * @param actividadesList Parameter 2.
     * @return A new instance of fragment DestinosRecomendadosFragment.
     */
    public static DestinosRecomendadosFragment newInstance(String ciudadName, Serializable actividadesList) {
        DestinosRecomendadosFragment fragment = new DestinosRecomendadosFragment();
        Bundle args = new Bundle();
        args.putString(Constantes.CIUDAD, ciudadName);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_destinos_recomendados, container, false);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}