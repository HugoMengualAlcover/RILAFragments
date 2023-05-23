package com.example.rilafragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.widget.TextView;

import com.example.rilafragments.constantes.Constantes;
import com.example.rilafragments.databinding.ActivityMainBinding;
import com.example.rilafragments.fragments.ayuda.AyudaFragment;
import com.example.rilafragments.fragments.ciudadesPueblos.CiudadesYPueblosFragment;
import com.example.rilafragments.fragments.descubrimientos.DescubrimientosFragment;
import com.example.rilafragments.fragments.buscador.BuscadorFragment;
import com.example.rilafragments.fragments.perfil.PerfilFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;
    private NavHostFragment navHostFragment;

    private FirebaseAuth auth;
    private DatabaseReference firebaseDbReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        auth = FirebaseAuth.getInstance();
        firebaseDbReference = FirebaseDatabase.getInstance("https://rila-3c493-default-rtdb.europe-west1.firebasedatabase.app").getReference("Usuarios");

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        FragmentManager manager = getSupportFragmentManager();

        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.buscadorFragment, R.id.nav_gallery, R.id.nav_slideshow)
                .setOpenableLayout(drawer)
                .build();

        navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment_content_main);
        NavController navController = navHostFragment.getNavController();

        NavigationUI.setupWithNavController(navigationView, navController);

       binding.appBarMain.contentAppBar.btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawer.openDrawer(Gravity.LEFT);
            }
        });

        //---------------------------------------

        if(auth.getCurrentUser() == null){
            ((TextView) navigationView.getHeaderView(0).findViewById(R.id.textNavHeader)).setText("Iniciar Sesión");
            navigationView.getHeaderView(0).findViewById(R.id.layoutNavHeader).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish();
                    getFragmentManager().popBackStack();
                    startActivity(new Intent(MainActivity.this, RilaStartActivity.class));
                }
            });
        }else {
            System.out.println("hola");
            setTxtUserName();
            navigationView.getHeaderView(0).findViewById(R.id.layoutNavHeader).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    FragmentTransaction fragmentTransaction = manager.beginTransaction();
                    fragmentTransaction.detach(navHostFragment);                        //El tercero es el tag
                    fragmentTransaction.replace(R.id.contentAppBar, new PerfilFragment(), Constantes.FRAGMENT_PERFIL);
                    fragmentTransaction.commit();

                    drawer.closeDrawer(Gravity.LEFT);
                }
            });
        }
        binding.navNovedades.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fragmentTransaction = manager.beginTransaction();
                fragmentTransaction.detach(navHostFragment);                        //El tercero es el tag
                fragmentTransaction.replace(R.id.contentAppBar, CiudadesYPueblosFragment.newInstance(Constantes.NOVEDADES, null), Constantes.FRAGMENT_AYUDA);
                fragmentTransaction.commit();

                drawer.closeDrawer(Gravity.LEFT);
            }
        });

        binding.navAyuda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fragmentTransaction = manager.beginTransaction();
                fragmentTransaction.detach(navHostFragment);                        //El tercero es el tag
                fragmentTransaction.replace(R.id.contentAppBar, new AyudaFragment(), Constantes.FRAGMENT_AYUDA);
                fragmentTransaction.commit();

                drawer.closeDrawer(Gravity.LEFT);
            }
        });

        binding.navBuscador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fragmentTransaction = manager.beginTransaction();
                fragmentTransaction.detach(navHostFragment);
                fragmentTransaction.replace(R.id.contentAppBar, new BuscadorFragment(), Constantes.FRAGMENT_BUSCADOR);
                fragmentTransaction.commit();

                drawer.closeDrawer(Gravity.LEFT);
            }
        });

        binding.navDescubrimientos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fragmentTransaction = manager.beginTransaction();
                fragmentTransaction.detach(navHostFragment);
                fragmentTransaction.replace(R.id.contentAppBar, new DescubrimientosFragment(), Constantes.FRAGMENT_DESCUBRIMIENTOS);
                fragmentTransaction.commit();

                drawer.closeDrawer(Gravity.LEFT);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    public void setTxtUserName(){
        TextView txtUserName = (TextView) binding.navView.getHeaderView(0).findViewById(R.id.textNavHeader);
        if(auth.getCurrentUser() != null){
            firebaseDbReference.child(auth.getCurrentUser().getEmail().split("\\.")[0]).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DataSnapshot> task) {
                    if(task.isSuccessful()){
                        if(task.getResult().exists()){
                            txtUserName.setText(String.valueOf(task.getResult().child("nombre").getValue()));
                        }
                    }else {
                        System.out.println(task.getException());
                    }
                }
            });
        }
    }
}