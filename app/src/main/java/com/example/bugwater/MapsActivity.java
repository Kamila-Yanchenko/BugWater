package com.example.bugwater;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.content.Intent;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;
import android.widget.Button;

import android.content.pm.PackageManager;
import androidx.core.app.ActivityCompat;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback{

    private GoogleMap mMap;
    private List<River> rivers;
    private LatLng selectedLocation; // Для зберігання координат вибраного місця

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        // Отримуємо координати з Intent
        Intent intent = getIntent();
        if (intent.hasExtra("latitude") && intent.hasExtra("longitude")) {
            double latitude = intent.getDoubleExtra("latitude", 0);
            double longitude = intent.getDoubleExtra("longitude", 0);
            selectedLocation = new LatLng(latitude, longitude);
        }

        // Запит дозволів
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        }

        // Ініціалізація карти
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }

        // Ініціалізація списку річок
        rivers = new ArrayList<>();
        loadRivers();

        // Налаштування нижньої навігації
        BottomNavigationView nav = findViewById(R.id.bottomNavigation);
        nav.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.nav_home) {
                finish();
                return true;
            } else if (id == R.id.nav_map) {
                Toast.makeText(this, "Ви вже на карті", Toast.LENGTH_SHORT).show();
                return true;
            } else if (id == R.id.nav_chart) {
                startActivity(new Intent(this, ChartsActivity.class));
                return true;
            }
            return false;
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        mMap.getUiSettings().setZoomControlsEnabled(false);

        // Увімкнення кнопки "Моє місце"
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);
        }

        for (River river : rivers) {
            Marker marker = mMap.addMarker(new MarkerOptions()
                    .position(river.getLatLng())
                    .title(river.getName()));

            mMap.addCircle(new CircleOptions()
                    .center(river.getLatLng())
                    .radius(Math.sqrt(river.getBasinArea()) * 100)
                    .strokeColor(0xFF00BFFF)
                    .fillColor(0x5500BFFF)
                    .strokeWidth(2));

            if (marker != null) {
                marker.showInfoWindow();
            }
        }

        // Якщо є вибране місце, наближаємо до нього
        if (selectedLocation != null) {
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(selectedLocation, 15));
        } else {
            // Якщо місця не вибрано, центруємо на середині України
            LatLng ukraineCenter = new LatLng(48.3794, 31.1656);
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ukraineCenter, 6));
        }
    }

    private void loadRivers() {
        rivers.add(new River("с. Александрівка", new LatLng(48.9639, 30.7519), 46200));
        rivers.add(new River("с. Лелітка", new LatLng(48.3167, 30.3167), 4000));
        rivers.add(new River("с. Селище", new LatLng(48.4167, 30.3167), 9100));
        rivers.add(new River("с. Тростянчик", new LatLng(48.5167, 30.4167), 17400));
        rivers.add(new River("с. Підгір'я", new LatLng(48.6167, 30.5167), 24600));
        rivers.add(new River("м. Первомайськ", new LatLng(48.05, 30.85), 44000));
        rivers.add(new River("м. Кіровоград", new LatLng(48.5079, 32.2623), 840));
        rivers.add(new River("с. Седнівка", new LatLng(48.3167, 32.4167), 4770));
        rivers.add(new River("с. Новогорожене", new LatLng(48.2167, 32.5167), 6670));
        rivers.add(new River("с. Синюхін Брід", new LatLng(48.1167, 30.6167), 16700));
        rivers.add(new River("Ново-Архангельска", new LatLng(48.6167, 30.8167), 9600));
        rivers.add(new River("с. Ямпіль", new LatLng(48.2414, 28.2819), 2820));
    }

    private static class River {
        private final String name;
        private final LatLng latLng;
        private final double basinArea;

        public River(String name, LatLng latLng, double basinArea) {
            this.name = name;
            this.latLng = latLng;
            this.basinArea = basinArea;
        }

        public String getName() {
            return name;
        }

        public LatLng getLatLng() {
            return latLng;
        }

        public double getBasinArea() {
            return basinArea;
        }
    }
}

//    @Override
//    public void onMapReady(GoogleMap googleMap) {
//        mMap = googleMap;
//        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
//
//        mMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
//            @Override
//            public View getInfoWindow(Marker marker) {
//                return null; // Використовуємо стандартний фрейм
//            }
//
//            @Override
//            public View getInfoContents(Marker marker) {
//                View view = getLayoutInflater().inflate(R.layout.custom_info_window, null);
//                TextView title = view.findViewById(R.id.info_title);
//                title.setText(marker.getTitle());
//                return view;
//            }
//        });
//
//        // Увімкнення кнопки "Моє місце"
//        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
//            mMap.setMyLocationEnabled(true);
//        }
//
//        for (River river : rivers) {
//            Marker marker = mMap.addMarker(new MarkerOptions()
//                    .position(river.getLatLng())
//                    .title(river.getName()));
//
//            mMap.addCircle(new CircleOptions()
//                    .center(river.getLatLng())
//                    .radius(Math.sqrt(river.getBasinArea()) * 100)
//                    .strokeColor(0xFF00BFFF)
//                    .fillColor(0x5500BFFF)
//                    .strokeWidth(2));
//
//            if (marker != null) {
//                marker.showInfoWindow();
//            }
//        }
//
//        LatLng ukraineCenter = new LatLng(48.3794, 31.1656);
//        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ukraineCenter, 6));
//    }


