package com.example.rilafragments;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rilafragments.user.Users;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class RilaStartActivity extends AppCompatActivity {

    Button btnSignUp;
    Button btnLogIn;
    ImageButton btnGoogle;
    TextView lblEntrarSinCuenta;

    FirebaseAuth auth;
    FirebaseDatabase firebaseDatabase;
    GoogleSignInClient googleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rila_start);

        auth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();

        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail().build();

        googleSignInClient = GoogleSignIn.getClient(this, googleSignInOptions);

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
                //ToDo -> Iniciar sesion con Google
                signInGoogle();
            }
        });
    }

    @Override
    protected void onStart() {
        //Inicio de sesion automatico
        if(auth.getCurrentUser() != null){
            startActivity(new Intent(RilaStartActivity.this, MainActivity.class));
        }
        super.onStart();
    }

    private void inicializarVistas() {
        btnSignUp = findViewById(R.id.btnSignUpMain);
        btnLogIn = findViewById(R.id.btnLoginInMain);
        btnGoogle = findViewById(R.id.btnGoogleMain);
        lblEntrarSinCuenta = findViewById(R.id.lblEntrarSinCuentaMain);
    }

    int RC_SIGN_IN = 40;
    private void signInGoogle(){
        Intent intent = googleSignInClient.getSignInIntent();
        startActivityForResult(intent, RC_SIGN_IN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN){
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);

                firebaseAuth(account.getIdToken());
            } catch (ApiException e) {
                e.printStackTrace();
            }
        }
    }
    private void firebaseAuth(String idToken){
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        auth.signInWithCredential(credential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = auth.getCurrentUser();

                            Users users = new Users(user.getUid(), user.getDisplayName(), user.getPhotoUrl().toString());

                            firebaseDatabase.getReference().child("Users").child(user.getUid()).setValue(users);

                            Intent intent = new Intent(RilaStartActivity.this, MainActivity.class);
                            startActivity(intent);
                        }else{
                            Toast.makeText(RilaStartActivity.this, "Ocurrio un error: ", Toast.LENGTH_SHORT).show();
                            Objects.requireNonNull(task.getException()).printStackTrace();
                        }
                    }
                });
    }
}