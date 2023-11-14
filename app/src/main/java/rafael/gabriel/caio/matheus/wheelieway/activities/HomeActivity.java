package rafael.gabriel.caio.matheus.wheelieway.activities;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import rafael.gabriel.caio.matheus.wheelieway.R;
import rafael.gabriel.caio.matheus.wheelieway.fragments.FavoritosFragment;
import rafael.gabriel.caio.matheus.wheelieway.fragments.HomeFragment;
import rafael.gabriel.caio.matheus.wheelieway.fragments.MapaFragment;
import rafael.gabriel.caio.matheus.wheelieway.fragments.PerfilFragment;
import rafael.gabriel.caio.matheus.wheelieway.models.HomeViewModel;

public class HomeActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        final HomeViewModel vm = new ViewModelProvider(this).get(HomeViewModel.class);

        bottomNavigationView = findViewById(R.id.bnvHome);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                vm.setNavigationOpSelected(item.getItemId());
                switch (item.getItemId()){
                    case R.id.optHome:
                        HomeFragment homeFragment = HomeFragment.newInstance();
                        setFragment(homeFragment);
                        break;
                    case R.id.optMap:
                        MapaFragment mapaFragment= MapaFragment.newInstance();
                        setFragment(mapaFragment);
                        break;
                    case R.id.optFav:
                        FavoritosFragment favoritosFragment = FavoritosFragment.newInstance();
                        setFragment(favoritosFragment);
                        break;
                    case R.id.optPerfil:
                        PerfilFragment perfilFragment = PerfilFragment.newInstance();
                        setFragment(perfilFragment);
                        break;
                }
                return true;
            }
        });
    }

    void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragContainer, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}