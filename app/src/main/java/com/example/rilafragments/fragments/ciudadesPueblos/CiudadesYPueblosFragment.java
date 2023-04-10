package com.example.rilafragments.fragments.ciudadesPueblos;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.rilafragments.APIs.ciudades.Ciudad;
import com.example.rilafragments.APIs.conexiones.APIConexiones;
import com.example.rilafragments.APIs.conexiones.RetrofitObject;
import com.example.rilafragments.R;
import com.example.rilafragments.adapters.CiudadesAdapter;
import com.example.rilafragments.constantes.Constantes;
import com.example.rilafragments.databinding.FragmentBuscadorBinding;

import java.net.HttpURLConnection;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
public class CiudadesYPueblosFragment extends Fragment {

    private FragmentBuscadorBinding binding;
    private RecyclerView.LayoutManager layoutManager;
    private String countryName;
    private List<String> ciudadIdsList;
    private List<Ciudad> ciudadList;

    private CiudadesAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            countryName = getArguments().getString(Constantes.COUNTRY_NAME);
            ciudadIdsList = getArguments().getStringArrayList(Constantes.CITY_IDs_LIST);
        }

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        doGetUsuarios();

        adapter = new CiudadesAdapter(ciudadList, R.layout.card_view_ciudades_y_pueblos, this.getContext())

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ciudades_y_pueblos, container, false);
    }

    private void doGetUsuarios() {
        Retrofit retrofit = RetrofitObject.getConnection();
        APIConexiones api = retrofit.create(APIConexiones.class);

        //Va cogiendo ciudad a ciudad las ciudades de un pais y las añade a ciudadList
        for (int i = 0; i < ciudadIdsList.size(); i++) {
            Call<Ciudad> getCiudades = api.getCiudades(ciudadIdsList.get(i));

            getCiudades.enqueue(new Callback<Ciudad>() {
                @Override
                public void onResponse(Call<Ciudad> call, Response<Ciudad> response) {
                    if(response.code() == HttpURLConnection.HTTP_OK){
                        Ciudad resp = response.body();
                        ciudadList.add(resp);
                        adapter.notifyItemInserted(ciudadList.size());
                    }
                }

                @Override
                public void onFailure(Call<Ciudad> call, Throwable t) {
                    Toast.makeText(getContext(), "ERROR DE CONEXIÓN", Toast.LENGTH_SHORT).show();
                    Log.e("FAILURE", t.getLocalizedMessage());
                }
            });
        }

    }




}