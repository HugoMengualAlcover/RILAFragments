package com.example.rilafragments.fragments.ayuda;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rilafragments.R;
import com.example.rilafragments.adapters.AyudaAdapter;
import com.example.rilafragments.databinding.FragmentAyudaBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class AyudaFragment extends Fragment implements SearchView.OnQueryTextListener{
    private FragmentAyudaBinding binding;
    private AyudaAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    private List<AyudaModel> ayudaModelList;

    private FirebaseAuth auth;
    private DatabaseReference firebaseDbReference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        auth = FirebaseAuth.getInstance();
        firebaseDbReference = FirebaseDatabase.getInstance("https://rila-3c493-default-rtdb.europe-west1.firebasedatabase.app").getReference("Ayuda");

        binding = FragmentAyudaBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        ayudaModelList = new ArrayList<>();
        binding.searchViewAyuda.setOnQueryTextListener(this);

        getAyudas();

        adapter = new AyudaAdapter(ayudaModelList, AyudaFragment.this.getContext(), R.layout.card_view_ayuda);
        layoutManager = new GridLayoutManager(this.getContext(), 1);

        binding.contenedorAyuda.setAdapter(adapter);
        binding.contenedorAyuda.setLayoutManager(layoutManager);

        return root;
    }

    private void getAyudas(){
        if(auth.getCurrentUser() != null){
            firebaseDbReference.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DataSnapshot> task) {
                    if(task.isSuccessful()){
                        if(task.getResult().exists()){
                            for (int i = 0; i < task.getResult().getChildrenCount(); i++) {
                                ayudaModelList.add(new AyudaModel(
                                        String.valueOf(task.getResult().child(String.valueOf(i+1)).child("Titulo").getValue()),
                                        String.valueOf(task.getResult().child(String.valueOf(i+1)).child("Texto").getValue())));
                                adapter.filtrar("");
                            }
                        }
                    }else {
                        System.out.println(task.getException());
                    }
                }
            });
        }
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        adapter.filtrar(query);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        adapter.filtrar(newText);
        return false;
    }
}