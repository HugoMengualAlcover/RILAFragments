package com.example.rilafragments.fragments.ciudad;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rilafragments.APIs.ciudades.Actividad;
import com.example.rilafragments.APIs.ciudades.Ciudad;
import com.example.rilafragments.R;
import com.example.rilafragments.adapters.ActividadesAdapter;
import com.example.rilafragments.constantes.Constantes;
import com.example.rilafragments.databinding.FragmentCiudadBinding;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CiudadFragment extends Fragment {

    private FragmentCiudadBinding binding;
    private Ciudad ciudad;
    private List<Actividad> actividadesList;

    private RecyclerView.LayoutManager layoutManager;

    public CiudadFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param ciudad Parameter 1.
     * @return A new instance of fragment DestinoFragment.
     */
    public static CiudadFragment newInstance(Ciudad ciudad) {
        CiudadFragment fragment = new CiudadFragment();
        Bundle args = new Bundle();
        args.putSerializable(Constantes.CIUDAD, ciudad);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            ciudad = (Ciudad) getArguments().getSerializable(Constantes.CIUDAD);
            actividadesList = ciudad.getActivities();
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCiudadBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        binding.lblTituloDestinoActivity.setText(ciudad.getName());
        binding.lblInfoDestinoActivity.setText(ciudad.getDescripcion());

        Picasso.get()
                .load(ciudad.getUrlCiudad())
                .placeholder(android.R.drawable.ic_popup_sync)
                .error(android.R.drawable.stat_notify_error)
                .into(binding.imgDestinoActivity);

        ActividadesAdapter adapter = new ActividadesAdapter(actividadesList, this.getContext(), R.layout.card_view_actividades);
        layoutManager = new GridLayoutManager(this.getContext(), 1);

        binding.contenedorActividadesDestinoActivity.setLayoutManager(layoutManager);
        binding.contenedorActividadesDestinoActivity.setAdapter(adapter);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}