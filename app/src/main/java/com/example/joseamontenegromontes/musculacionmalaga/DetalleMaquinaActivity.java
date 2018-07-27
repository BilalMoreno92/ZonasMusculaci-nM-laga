package com.example.joseamontenegromontes.musculacionmalaga;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class DetalleMaquinaActivity extends AppCompatActivity {

    ImageView imagen;
    TextView nombre, funcion, nivel, desarrollo, precauciones;
    Maquina maquina;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_maquina);


        //ToDo: DetalleMaquina

        imagen = findViewById(R.id.imageViewmaquinaDetalle);
        nombre = findViewById(R.id.textViewNombre);
        funcion = findViewById(R.id.textViewValorFuncion);
        nivel = findViewById(R.id.textViewValorNivel);
        desarrollo = findViewById(R.id.textViewValorDesarrollo);
        precauciones = findViewById(R.id.textViewPrecauciones);

        Intent intent = getIntent();

        int img = intent.getIntExtra("imagen", - 1);
        if (img != -1) {
            imagen.setImageResource(img);
        }
        nombre.setText(intent.getStringExtra("nombre"));
        funcion.setText(intent.getStringExtra("funcion"));
        nivel.setText(Integer.toString(intent.getIntExtra("nivel", 0)));
        desarrollo.setText(intent.getStringExtra("desarrollo"));
        precauciones.setText(intent.getStringExtra("precauciones"));
    }
}
