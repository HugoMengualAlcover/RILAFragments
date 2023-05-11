package com.example.rilafragments;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.rilafragments.databinding.ActivityForgotPasswordBinding;
import com.example.rilafragments.databinding.ActivityLogInBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPasswordActivity extends AppCompatActivity {

    private ActivityForgotPasswordBinding binding;

    private FirebaseAuth authSystem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityForgotPasswordBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        authSystem = FirebaseAuth.getInstance();

        binding.btnCambiarForgotPasswordActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = binding.txtEmailForgotPasswordActivity.getText().toString().trim();
                if (email.isEmpty()){
                    binding.txtEmailForgotPasswordActivity.setError("Campo obligatorio");
                }else{
                    authSystem.sendPasswordResetEmail(email).addOnCompleteListener(task -> {
                        if(task.isSuccessful()){
                            Toast.makeText(ForgotPasswordActivity.this, "Correo enviado correctamente", Toast.LENGTH_SHORT).show();
                            finish();
                        }else{
                            Toast.makeText(ForgotPasswordActivity.this,  task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(ForgotPasswordActivity.this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }
}