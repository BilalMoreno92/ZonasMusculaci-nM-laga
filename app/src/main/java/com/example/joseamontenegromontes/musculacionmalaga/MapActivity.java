package com.example.joseamontenegromontes.musculacionmalaga;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {

    ListView lista;
    ZonaMusculacionAdapter adapter;

    ArrayList<ZonaMusculacion> zonasMusculacion;
    GoogleMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        //ToDo: MapActivity

        Intent intent = getIntent();
        zonasMusculacion = (ArrayList<ZonaMusculacion>) intent.getExtras().get("listaZonas");

        lista = findViewById(R.id.listViewZonas);
        adapter = new ZonaMusculacionAdapter(getApplicationContext(), R.layout.zona_layout, zonasMusculacion);
        lista.setAdapter(adapter);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                setMarker(zonasMusculacion.get(position));
            }
        });

        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
    }

    private void setMarker(ZonaMusculacion zonaMusculacion){
        map.clear();
        LatLng marker = new LatLng(zonaMusculacion.getLatitude(), zonaMusculacion.getLongitude());
        int zoom = 16;
        map.addMarker(new MarkerOptions().position(marker).title(zonaMusculacion.getName()).snippet(zonaMusculacion.getMaquinas().size() + " máquinas."));
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(marker, zoom));
    }
}
