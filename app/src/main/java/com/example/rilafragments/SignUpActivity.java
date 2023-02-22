package com.example.rilafragments;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.rilafragments.databinding.ActivityLogInBinding;
import com.example.rilafragments.databinding.ActivitySignUpBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class SignUpActivity extends AppCompatActivity {

    private ActivitySignUpBinding binding;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        auth = FirebaseAuth.getInstance();

        binding.btnRegsitrarseSignUpActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = binding.txtNombreSignUpActivity.getText().toString();
                String gmail = binding.txtEmailSignUpActivity.getText().toString();
                String pass = binding.txtPasswordSignUpActivity.getText().toString();
                String passConfirm = binding.txtConfirmPasswordSignUpActivity.getText().toString();


                if(name.isEmpty()){
                    binding.txtNombreSignUpActivityBox.setError("Campo Obligatorio");
                }else{
                    binding.txtNombreSignUpActivityBox.setError(null);
                    if(gmail.isEmpty()){
                        binding.txtEmailSignUpActivityBox.setError("Campo Obligatorio");
                    }else{
                        binding.txtEmailSignUpActivityBox.setError(null);
                        if(pass.isEmpty()){
                            binding.txtPasswordSignUpActivityBox.setError("Campo Obligatorio");
                        }else{
                            binding.txtPasswordSignUpActivityBox.setError(null);
                            if(!passConfirm.equals(pass)){
                                binding.txtConfirmPasswordSignUpActivityBox.setError("La contraseña no coincide");
                            }else{
                                binding.txtConfirmPasswordSignUpActivityBox.setError(null);
                                doRegister(gmail,passConfirm);
                            }
                        }
                    }
                }
            }
        });
    }

    private void doRegister(String gmail, String passConfirm) {
        auth.createUserWithEmailAndPassword(gmail, passConfirm)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            upDateUI(auth.getCurrentUser());
                        }
                        else {
                            Toast.makeText(SignUpActivity.this, task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(SignUpActivity.this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

    }

    private void upDateUI(FirebaseUser currentUser) {
        if (currentUser != null) {
            startActivity(new Intent(SignUpActivity.this, MainActivity.class));
            finish();
        }
    }
}