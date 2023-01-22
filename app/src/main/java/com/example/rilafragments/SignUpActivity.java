package com.example.rilafragments;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.rilafragments.databinding.ActivityLogInBinding;
import com.example.rilafragments.databinding.ActivitySignUpBinding;


public class SignUpActivity extends AppCompatActivity {

    private ActivitySignUpBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnRegsitrarseSignUpActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = binding.txtNombreSignUpActivity.getText().toString();
                String gmail = binding.txtEmailSignUpActivity.getText().toString();
                String pass = binding.txtPasswordSignUpActivity.getText().toString();
                String passConfirm = binding.txtConfirmPasswordSignUpActivity.getText().toString();

                //Acabar SigUp
            }
        });
    }
}