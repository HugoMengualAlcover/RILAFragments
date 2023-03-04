package com.example.rilafragments;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.rilafragments.databinding.ActivityLogInBinding;
import com.example.rilafragments.databinding.ActivityMainBinding;
import com.example.rilafragments.databinding.FragmentMapaBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LogInActivity extends AppCompatActivity {

    private ActivityLogInBinding binding;

    private FirebaseAuth authSystem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityLogInBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        authSystem = FirebaseAuth.getInstance();


        binding.btnEntrarLogInActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String gmail = binding.txtEmailLogInActivity.getText().toString();
                String pass = binding.txtPasswordLogInActivity.getText().toString();

                if(gmail.isEmpty()){
                    binding.txtEmailLogInActivityBox.setError("Campo Obligatorio");
                }else{
                    if(pass.isEmpty()){
                        binding.txtPasswordLogInActivityBox.setError("Campo Obligatorio");
                    }else{
                        doLogin(gmail,pass);
                    }
                }
            }
        });


    }

    private void doLogin(String gmail, String pass) {
        authSystem.signInWithEmailAndPassword(gmail, pass)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            upDateUI(authSystem.getCurrentUser());
                        }
                        else {
                            Toast.makeText(LogInActivity.this, task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(LogInActivity.this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void upDateUI(FirebaseUser currentUser) {

        if (currentUser != null) {
            startActivity(new Intent(LogInActivity.this, MainActivity.class));
            finish();
        }
    }

}