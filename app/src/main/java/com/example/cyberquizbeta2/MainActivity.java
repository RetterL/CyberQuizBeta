package com.example.cyberquizbeta2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.cyberquizbeta2.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    BottomNavigationView bottom_navigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());

        ArrayList<CategoryModel> categories = new ArrayList<>();
        categories.add(new CategoryModel("","Matematicas",""));
        categories.add(new CategoryModel("","Ciencia",""));
        categories.add(new CategoryModel("","Historia",""));
        categories.add(new CategoryModel("","Deportes",""));
        categories.add(new CategoryModel("","Colombia",""));

        CategoryAdapter adapter = new CategoryAdapter(this,categories);


        setContentView(R.layout.activity_main);

        bottom_navigation=findViewById(R.id.barraNavegacion);
        bottom_navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId())
                {
                    case R.id.home:
                        Toast.makeText(MainActivity.this, "Inicio", Toast.LENGTH_SHORT).show();

                    case R.id.score:
                        Toast.makeText(MainActivity.this, "Puntuacion", Toast.LENGTH_SHORT).show();

                    case R.id.config:
                        Toast.makeText(MainActivity.this, "Configuracion", Toast.LENGTH_SHORT).show();

                    case R.id.profile:
                        Toast.makeText(MainActivity.this, "Perfil", Toast.LENGTH_SHORT).show();
                }
                return true;
            }

        });


    }
}