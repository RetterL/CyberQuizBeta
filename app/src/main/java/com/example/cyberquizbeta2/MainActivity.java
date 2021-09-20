package com.example.cyberquizbeta2;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.cyberquizbeta2.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    FirebaseFirestore database;

    BottomNavigationView bottom_navigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar2);

        database = FirebaseFirestore.getInstance();

        ArrayList<CategoryModel> categories = new ArrayList<>();


        CategoryAdapter adapter = new CategoryAdapter(this,categories);

        database.collection("categories")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        categories.clear();
                        for (DocumentSnapshot snapshot : value.getDocuments()) {
                            CategoryModel model = snapshot.toObject(CategoryModel.class);
                            model.setCategoryId(snapshot.getId());
                            categories.add(model);
                        }
                        adapter.notifyDataSetChanged();
                    }
                });

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

