package com.example.rilafragments;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.rilafragments.databinding.ActivityLogInBinding;
import com.example.rilafragments.databinding.ActivityMainBinding;
import com.example.rilafragments.databinding.FragmentMapaBinding;

public class LogInActivity extends AppCompatActivity {

    private ActivityLogInBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityLogInBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnEntrarLogInActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String gmail = binding.txtEmailLogInActivity.getText().toString();
                String pass = binding.txtPasswordLogInActivity.getText().toString();

                //Acabar LogIn
            }
        });
    }
}