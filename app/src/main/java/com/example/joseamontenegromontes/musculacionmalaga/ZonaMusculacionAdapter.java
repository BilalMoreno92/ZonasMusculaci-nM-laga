package com.example.joseamontenegromontes.musculacionmalaga;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ZonaMusculacionAdapter extends BaseAdapter {

    Context context;
    int layout;
    ArrayList<ZonaMusculacion> zonas;

    public ZonaMusculacionAdapter(Context context, int layout, ArrayList<ZonaMusculacion> zonas) {
        this.context = context;
        this.layout = layout;
        this.zonas = zonas;
    }

    @Override
    public int getCount() {
        return zonas.size();
    }

    @Override
    public Object getItem(int position) {
        return zonas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return zonas.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view;
        ImageView imagen;
        TextView nombre, descripcion, maquinas;
        ZonaMusculacion zonaMusculacion;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null){
            view = inflater.inflate(layout, null);
        } else {
            view = (View) convertView;
        }

        imagen = view.findViewById(R.id.imageViewZonaMusculacion);
        nombre = view.findViewById(R.id.textViewZonaMusculacion);
        descripcion = view.findViewById(R.id.textViewDescripcion);
        maquinas = view.findViewById(R.id.textViewMaquinas);

        zonaMusculacion = zonas.get(position);

        imagen.setImageResource(zonaMusculacion.getResourceFoto());
        nombre.setText(zonaMusculacion.getName());
        descripcion.setText(zonaMusculacion.getDescripcionUbicacion());
        maquinas.setText("Num. máquinas: " + zonaMusculacion.getMaquinas().size());

        return view;
    }
}