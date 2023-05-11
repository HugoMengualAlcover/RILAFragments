package com.example.rilafragments.fragments.descubrimientos;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.rilafragments.APIs.ciudades.Ciudad;
import com.example.rilafragments.APIs.conexiones.APIConexiones;
import com.example.rilafragments.APIs.conexiones.RetrofitObject;
import com.example.rilafragments.APIs.continente.ApiResponse;
import com.example.rilafragments.APIs.continente.CiudadesItem;
import com.example.rilafragments.APIs.continente.Continente;
import com.example.rilafragments.R;
import com.example.rilafragments.adapters.CiudadesAdapter;
import com.example.rilafragments.constantes.Constantes;
import com.example.rilafragments.databinding.FragmentDescubrimientosBinding;

import java.io.Serializable;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class DescubrimientosFragment extends Fragment {

    private FragmentDescubrimientosBinding binding;
    private String ciudadName;
    private List<Ciudad> ciudadList;
    private List<CiudadesItem> ciudadesItemList;
    private List<Continente> continentes;

    private CiudadesAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    public DescubrimientosFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param ciudadName Parameter 1.
     * @param actividadesList Parameter 2.
     * @return A new instance of fragment DescubrimientosFragment.
     */
    public static DescubrimientosFragment newInstance(String ciudadName, Serializable actividadesList) {
        DescubrimientosFragment fragment = new DescubrimientosFragment();
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
        ciudadList = new ArrayList<>();
        continentes = new ArrayList<>();
        ciudadesItemList = new ArrayList<>();

        doGetContinentes();

        binding = FragmentDescubrimientosBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        adapter = new CiudadesAdapter(ciudadList, R.layout.card_view_ciudades_y_pueblos, this.getContext());
        layoutManager = new GridLayoutManager(this.getContext(), 1);

        binding.contenedorDescubrimientosFragment.setLayoutManager(layoutManager);
        binding.contenedorDescubrimientosFragment.setAdapter(adapter);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void randomizedCiudadesAndCleanContinentes(){
        ArrayList<String> gastados = new ArrayList<>();
        for (int i = 0; i < 10; i++) {

            int r1 = new Random().nextInt(continentes.size());
            int r2 = new Random().nextInt(continentes.get(r1).getPaises().size());
            int r3 = new Random().nextInt(continentes.get(r1).getPaises().get(r2).getCiudades().size());

            String comprobacion = r1+" "+r2+" "+r3;
            if(!gastados.contains(comprobacion)){
                gastados.add(comprobacion);
                ciudadesItemList.add( continentes.get(r1).getPaises().get(r2).getCiudades().get(r3) );
            }else
                i--;

        }
        continentes.clear();
        doGetCiudades();
    }


    private void doGetCiudades() {
        Retrofit retrofit = RetrofitObject.getConnection();
        APIConexiones api = retrofit.create(APIConexiones.class);

        //Va cogiendo ciudad a ciudad las ciudades de un pais y las añade a ciudadesList
        for (int i = 0; i < ciudadesItemList.size(); i++) {
            Call<Ciudad> getCiudades = api.getCiudad(ciudadesItemList.get(i).getCiudadId());

            getCiudades.enqueue(new Callback<Ciudad>() {
                @Override
                public void onResponse(Call<Ciudad> call, Response<Ciudad> response) {
                    System.out.println("Code "+response.code());
                    if(response.code() == HttpURLConnection.HTTP_OK){
                        Ciudad resp = response.body();
                        ciudadList.add(resp);
                        adapter.notifyItemInserted(ciudadList.size());
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

    public void doGetContinentes(){
        Retrofit retrofit = RetrofitObject.getConnection();
        APIConexiones api = retrofit.create(APIConexiones.class);

        Call<ApiResponse> getContinentes = api.getContinentes();
        getContinentes.enqueue(new Callback<ApiResponse>() {

            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                List<Continente> resp = response.body().getData();
                continentes.addAll(resp);
                System.out.println("LENGTH "+continentes.size());
                randomizedCiudadesAndCleanContinentes();
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                Toast.makeText(getContext(), "ERROR DE CONEXIÓN", Toast.LENGTH_SHORT).show();
                Log.e("FAILURE", t.getLocalizedMessage());
            }
        });
    }
}