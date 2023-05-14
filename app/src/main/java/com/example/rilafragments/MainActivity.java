package com.example.rilafragments;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Menu;
import android.widget.TextView;

import com.example.rilafragments.constantes.Constantes;
import com.example.rilafragments.databinding.ActivityMainBinding;
import com.example.rilafragments.fragments.ayuda.AyudaFragment;
import com.example.rilafragments.fragments.ciudadesPueblos.CiudadesYPueblosFragment;
import com.example.rilafragments.fragments.descubrimientos.DescubrimientosFragment;
import com.example.rilafragments.fragments.favoritos.FavoritosFragment;
import com.example.rilafragments.fragments.buscador.BuscadorFragment;
import com.example.rilafragments.fragments.perfil.PerfilFragment;
import com.google.android.material.navigation.NavigationView;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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

        // NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupWithNavController(navigationView, navController);

       binding.appBarMain.contentAppBar.btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawer.openDrawer(Gravity.LEFT);
            }
        });

        //---------------------------------------

        TextView t = (TextView) binding.navView.getHeaderView(0).findViewById(R.id.textNavHeader);
        //ToDO -> Poner nombre e imagen de usuario

        binding.navView.getHeaderView(0).findViewById(R.id.layoutNavHeader).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fragmentTransaction = manager.beginTransaction();
                fragmentTransaction.detach(navHostFragment);                        //El tercero es el tag
                fragmentTransaction.replace(R.id.contentAppBar, new PerfilFragment(), Constantes.FRAGMENT_PERFIL);
                fragmentTransaction.commit();

                drawer.closeDrawer(Gravity.LEFT);
            }
        });

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


        binding.navFavoritos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fragmentTransaction = manager.beginTransaction();
                fragmentTransaction.detach(navHostFragment);
                fragmentTransaction.replace(R.id.contentAppBar, new FavoritosFragment(), Constantes.FRAGMENT_FAVORITOS);
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
}