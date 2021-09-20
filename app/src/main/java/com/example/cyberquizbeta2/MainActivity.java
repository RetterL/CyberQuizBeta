package com.example.cyberquizbeta2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import android.os.Bundle;
import android.view.Menu;
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
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar2);

        ArrayList<CategoryModel> categories = new ArrayList<>();
        categories.add(new CategoryModel("","Matematicas","https://cdn1.iconfinder.com/data/icons/banking-and-finance-2-4/128/89-512.png"));
        categories.add(new CategoryModel("","Ciencia","https://cdn3.iconfinder.com/data/icons/education-and-knowledge-7/155/vector_338_16-512.png"));
        categories.add(new CategoryModel("","Historia","https://cdn2.iconfinder.com/data/icons/ballicons-2-vol-2/100/castle-512.png"));
        categories.add(new CategoryModel("","Deportes","https://cdn3.iconfinder.com/data/icons/education-science-vol-2-1/512/tennis_ball_sports_game-512.png"));
        categories.add(new CategoryModel("","Colombia","https://cdn4.iconfinder.com/data/icons/world-flags-circular/1000/Flag_of_Colombia_-_Circle-512.png"));

        CategoryAdapter adapter = new CategoryAdapter(this,categories);
        binding.categoryList.setLayoutManager(new GridLayoutManager(this,2));
        binding.categoryList.setAdapter(adapter);


        setContentView(R.layout.activity_main);

        bottom_navigation=findViewById(R.id.);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.home) {
            Toast.makeText(this, "Inicio es presionado", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
}

