package com.example.rilafragments;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class RilaStartActivity extends AppCompatActivity {

    Button btnSignUp;
    Button btnLogIn;
    ImageButton btnGoogle;
    ImageButton btnApple;
    TextView lblEntrarSinCuenta;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rila_start);

        btnSignUp = findViewById(R.id.btnSignUpMain);
        inicializarVistas();

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RilaStartActivity.this, SignUpActivity.class));
            }
        });

        btnLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RilaStartActivity.this, LogInActivity.class));
            }
        });

        lblEntrarSinCuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RilaStartActivity.this, MainActivity.class));

            }
        });

        btnGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Iniciar sesion con Google
            }
        });

        btnApple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Iniciar sesion con Apple
            }
        });
    }

    private void inicializarVistas() {

        btnLogIn = findViewById(R.id.btnLoginInMain);
        btnGoogle = findViewById(R.id.btnGoogleMain);
        btnApple = findViewById(R.id.btnAppleMain);
        lblEntrarSinCuenta = findViewById(R.id.lblEntrarSinCuentaMain);
    }
}