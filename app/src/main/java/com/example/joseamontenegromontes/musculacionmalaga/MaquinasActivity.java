package com.example.joseamontenegromontes.musculacionmalaga;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MaquinasActivity extends AppCompatActivity {

    ArrayList<Maquina> maquinas;
    ListView lista;
    MaquinasAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maquinas);


        //ToDO: MaquinasActivity

        final Intent intent = getIntent();
        maquinas = (ArrayList<Maquina>) intent.getExtras().get("listaMaquinas");

        lista = findViewById(R.id.listViewMaquinas);
        adapter = new MaquinasAdapter(getApplicationContext(), R.layout.maquina_layout, maquinas);
        lista.setAdapter(adapter);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Maquina maquina = maquinas.get(position);

                Intent detalles = new Intent(getApplicationContext(), DetalleMaquinaActivity.class);
                detalles.putExtra("imagen", maquina.getIcono());
                detalles.putExtra("nombre", maquina.getNombreMaquina());
                detalles.putExtra("funcion", maquina.getFuncion());
                detalles.putExtra("nivel", maquina.getNivel());
                detalles.putExtra("desarrollo", maquina.getDesarrollo());
                detalles.putExtra("precauciones", maquina.getPrecauciones());

                startActivity(detalles);
            }
        });
    }
}
