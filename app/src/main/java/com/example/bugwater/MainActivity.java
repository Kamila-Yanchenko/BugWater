package com.example.bugwater;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import android.content.Intent;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private LinearLayout riverContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        riverContainer = findViewById(R.id.riverContainer);
        loadRivers();

        BottomNavigationView nav = findViewById(R.id.bottomNavigation);
        nav.setOnItemSelectedListener(item -> {
            int id = item.getItemId();

            if (id == R.id.nav_map) {
                startActivity(new Intent(this, MapsActivity.class));
                return true;
            } else if (id == R.id.nav_chart) {
                startActivity(new Intent(this, ChartsActivity.class));
                return true;
            } else if (id == R.id.nav_home) {
                Toast.makeText(this, "Ви вже на головній", Toast.LENGTH_SHORT).show();
                return true;
            }
            return false;
        });
    }

    private void loadRivers() {
        addRiverCard("с. Александрівка", "Річка: Південний буг", "Площу басейну: 46 200 км2", R.drawable.aleksandrovska);
        addRiverCard("с. Лелітка", "Річка: Південний буг", "Площу басейну: 4 000 км2", R.drawable.lelitka);
        addRiverCard("с. Селище", "Річка: Південний буг", "Площу басейну: 9100 км2", R.drawable.seleshe);
        addRiverCard("с. Тростянчик", "Річка: Південний буг", "Площу басейну: 17 400 км2", R.drawable.trostjantunivka);
        addRiverCard("с. Підгір'я", "Річка: Південний буг", "Площу басейну: 24 600 км2", R.drawable.pidgirja);
        addRiverCard("м. Первомайськ", "Річка: Південний буг", "Площу басейну: 44000 км2", R.drawable.pervomausk);
        addRiverCard("м. Кіровоград", "Річка: Інгул", "Площу басейну: 840 км2", R.drawable.kirovograt);
        addRiverCard("с. Седнівка", "Річка: Інгул", "Площу басейну: 4770 км2", R.drawable.sednivka);
        addRiverCard("с.Новогорожене", "Річка: Інгул", "Площу басейну: 6670 км2", R.drawable.novogorozene);
        addRiverCard("с. Синюхін Брід", "Річка: Синюха", "Площу басейну:  16700 км2", R.drawable.shuhin_brid);
        addRiverCard("Ново-Архангельска", "Річка: Синюха", "Площу басейну: 9 600 км2", R.drawable.snuha);
        addRiverCard("с. Ямпіль", "Річка: Велика Вись", "Площу басейну: 2 820 км2", R.drawable.jampil);
    }

    private void addRiverCard(String title, String description, String s, int imageResId) {
        View card = getLayoutInflater().inflate(R.layout.item_river_card, riverContainer, false);

        ImageView img = card.findViewById(R.id.riverImage);
        TextView name = card.findViewById(R.id.riverName);
        TextView info = card.findViewById(R.id.riverInfo);
        TextView info2 = card.findViewById(R.id.riverInfo2);

        img.setImageResource(imageResId);
        name.setText(title);
        info.setText(description);
        info2.setText(s);

        card.setOnClickListener(v ->
                Toast.makeText(this, "Перехід до " + title, Toast.LENGTH_SHORT).show()
        );

        riverContainer.addView(card);
    }
}