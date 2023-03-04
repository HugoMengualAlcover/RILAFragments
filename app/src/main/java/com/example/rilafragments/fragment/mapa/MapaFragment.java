package com.example.rilafragments.fragment.mapa;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.rilafragments.R;
import com.example.rilafragments.databinding.FragmentMapaBinding;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapaFragment extends Fragment implements OnMapReadyCallback, GoogleMap.OnMapClickListener, GoogleMap.OnMapLongClickListener {

    private FragmentMapaBinding binding;
    GoogleMap mMap;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {



        MapaViewModel homeViewModel = new ViewModelProvider(this).get(MapaViewModel.class);

        binding = FragmentMapaBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);



            if( mapFragment != null){
                mapFragment.getMapAsync(this);
            }else{
                Toast.makeText(getParentFragment().getContext(), "ERROR", Toast.LENGTH_SHORT).show();
            }



        /*final TextView textView = binding.textHome;
        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);*/ //getText es una funcion del ViewModel, viene por defecto, cambiar cuando sepamos que hacer con él
        return root;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;
        this.mMap.setOnMapClickListener(this);
        this.mMap.setOnMapLongClickListener(this);

        LatLng e = new LatLng(39.4077853,-0.3615113);
        mMap.addMarker(new MarkerOptions().position(e).title("España"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(e));
    }

    @Override
    public void onMapClick(@NonNull LatLng latLng) {
        Log.d("COORDENADAS",latLng.toString());

     }

    @Override
    public void onMapLongClick(@NonNull LatLng latLng) {

    }
}