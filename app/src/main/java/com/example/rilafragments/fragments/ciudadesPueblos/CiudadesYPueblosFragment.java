package com.example.rilafragments.fragments.ciudadesPueblos;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.rilafragments.APIs.ciudades.ApiResponseCiudades;
import com.example.rilafragments.APIs.ciudades.Ciudad;
import com.example.rilafragments.APIs.conexiones.APIConexiones;
import com.example.rilafragments.APIs.conexiones.RetrofitObject;
import com.example.rilafragments.APIs.continente.CiudadesItem;
import com.example.rilafragments.R;
import com.example.rilafragments.adapters.CiudadesAdapter;
import com.example.rilafragments.constantes.Constantes;
import com.example.rilafragments.databinding.FragmentCiudadesYPueblosBinding;

import java.io.Serializable;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
public class CiudadesYPueblosFragment extends Fragment {

    private FragmentCiudadesYPueblosBinding binding;
    private String countryName;
    private List<CiudadesItem> ciudadItemList;
    private List<Ciudad> ciudadesList;

    private RecyclerView.LayoutManager layoutManager;
    private CiudadesAdapter adapter;

    public CiudadesYPueblosFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param countryName Nombre del país selleccionado. Tambien se utiliza para saber si se ha abierto desde
     *                    el boton de un país o si se ha abierto novedades.
     * @param ciudadItemList Lista de las ids y nombres de las ciudades del pais.
     * @return A new instance of fragment CiudadesYPueblosFragment.
     */
    public static CiudadesYPueblosFragment newInstance(String countryName, Serializable ciudadItemList) {
        CiudadesYPueblosFragment fragment = new CiudadesYPueblosFragment();
        Bundle args = new Bundle();
        args.putString(Constantes.COUNTRY_NAME, countryName);
        args.putSerializable(Constantes.CITY_LIST, ciudadItemList);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            countryName = getArguments().getString(Constantes.COUNTRY_NAME);
            ciudadItemList = (List<CiudadesItem>) getArguments().getSerializable(Constantes.CITY_LIST);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ciudadesList = new ArrayList<>();
        doGetCiudades();

        binding = FragmentCiudadesYPueblosBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        adapter = new CiudadesAdapter(ciudadesList, R.layout.card_view_ciudades_y_pueblos, this.getContext());
        layoutManager = new GridLayoutManager(this.getContext(), 1);

        binding.contenedor.setLayoutManager(layoutManager);
        binding.contenedor.setAdapter(adapter);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


    private void doGetCiudades() {
        Retrofit retrofit = RetrofitObject.getConnection();
        APIConexiones api = retrofit.create(APIConexiones.class);

        if(!countryName.equalsIgnoreCase(Constantes.NOVEDADES) || ciudadItemList == null){
            Call<ApiResponseCiudades> getCiudades = api.getCiudades();

            getCiudades.enqueue(new Callback<ApiResponseCiudades>() {
                @Override
                public void onResponse(Call<ApiResponseCiudades> call, Response<ApiResponseCiudades> response) {
                    System.out.println("Code "+response.code());
                    if(response.code() == HttpURLConnection.HTTP_OK){
                        List<Ciudad> resp = response.body().getData();
                        ciudadesList.addAll(resp);
                        adapter.notifyItemInserted(ciudadesList.size());
                    }
                }

                @Override
                public void onFailure(Call<ApiResponseCiudades> call, Throwable t) {
                    System.out.println("Error");
                    Toast.makeText(getContext(), "ERROR DE CONEXIÓN", Toast.LENGTH_SHORT).show();
                    Log.e("FAILURE", t.getLocalizedMessage());
                }
            });
        }else{
            //Va cogiendo ciudad a ciudad las ciudades de un pais y las añade a ciudadesList
            for (int i = 0; i < ciudadItemList.size(); i++) {
                Call<Ciudad> getCiudades = api.getCiudad(ciudadItemList.get(i).getCiudadId());

                getCiudades.enqueue(new Callback<Ciudad>() {
                    @Override
                    public void onResponse(Call<Ciudad> call, Response<Ciudad> response) {
                        System.out.println("Code "+response.code());
                        if(response.code() == HttpURLConnection.HTTP_OK){
                            Ciudad resp = response.body();
                            ciudadesList.add(resp);
                            adapter.notifyItemInserted(ciudadesList.size());
                        }
                    }

                    @Override
                    public void onFailure(Call<Ciudad> call, Throwable t) {
                        System.out.println("Error");
                        Toast.makeText(getContext(), "ERROR DE CONEXIÓN", Toast.LENGTH_SHORT).show();
                        Log.e("FAILURE", t.getLocalizedMessage());
                    }
                });
           }
        }
    }
}