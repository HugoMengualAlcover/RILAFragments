package com.example.rilafragments.fragments.actividad;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.rilafragments.APIs.ciudades.Actividad;
import com.example.rilafragments.MetodosDePagoActivity;
import com.example.rilafragments.R;
import com.example.rilafragments.constantes.Constantes;
import com.example.rilafragments.databinding.FragmentActividadBinding;
import com.squareup.picasso.Picasso;

import java.util.Base64;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ActividadFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ActividadFragment extends Fragment {

    private FragmentActividadBinding binding;
    private Actividad actividad;

    public ActividadFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param actividad Parameter 1.
     * @return A new instance of fragment ActividadFragment.
     */
    public static ActividadFragment newInstance(Actividad actividad) {
        ActividadFragment fragment = new ActividadFragment();
        Bundle args = new Bundle();
        args.putSerializable(Constantes.ACTIVIDAD, actividad);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            actividad = (Actividad) getArguments().getSerializable(Constantes.ACTIVIDAD);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentActividadBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        binding.lblTituloActividadFragment.setText(actividad.getNombreActividad());
        binding.lblInfoActividadFragment.setText(actividad.getDescripcion());

        Picasso.get()
                .load(actividad.getUrlActividad())
                .placeholder(android.R.drawable.ic_popup_sync)
                .error(android.R.drawable.stat_notify_error)
                .into(binding.imgActividadFragment);

        byte[] decodedString = Base64.getDecoder().decode(actividad.getImgLocalizacion());
        Bitmap decodedByte = BitmapFactory. decodeByteArray(decodedString, 0, decodedString.length);
        binding.imgMapaActividadFragment.setImageBitmap(decodedByte);

        if(actividad.getPrecio() == 0){
            binding.btnComprarActividadFragment.setVisibility(View.GONE);
            System.out.println("GONE");
        }else
            binding.btnComprarActividadFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putFloat(Constantes.PRECIO, actividad.getPrecio());
                startActivity(new Intent(getActivity(), MetodosDePagoActivity.class).putExtras(bundle));
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public String base64 = "";
}