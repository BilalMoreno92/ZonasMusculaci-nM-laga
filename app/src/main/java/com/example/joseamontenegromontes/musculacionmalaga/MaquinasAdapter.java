package com.example.joseamontenegromontes.musculacionmalaga;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MaquinasAdapter extends BaseAdapter {

    Context context;
    int layout;
    ArrayList<Maquina> maquinas;

    public MaquinasAdapter(Context context, int layout, ArrayList<Maquina> maquinas) {
        this.context = context;
        this.layout = layout;
        this.maquinas = maquinas;
    }

    @Override
    public int getCount() {
        return maquinas.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view;
        ImageView imagen;
        TextView nombre, nivel, funcion;
        Maquina maquina;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null){
            view = inflater.inflate(layout, null);
        } else {
            view = (View) convertView;
        }

        imagen = view.findViewById(R.id.imageViewMaquina);
        nombre = view.findViewById(R.id.textViewMaquina);
        nivel = view.findViewById(R.id.textViewNivel);
        funcion = view.findViewById(R.id.textViewFuncion);

        maquina = maquinas.get(position);

        imagen.setImageResource(maquina.getIcono());
        nombre.setText(maquina.getNombreMaquina());
        nivel.setText("Nivel: " + Integer.toString(maquina.getNivel()));
        funcion.setText(maquina.getFuncion());

        return view;
    }
}