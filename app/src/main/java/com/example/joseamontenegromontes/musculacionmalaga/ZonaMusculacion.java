package com.example.joseamontenegromontes.musculacionmalaga;



import com.google.android.gms.maps.model.LatLng;

import java.io.Serializable;
import java.util.ArrayList;

public class ZonaMusculacion implements Serializable{

    private int id;
    private String name;
    private String descripcionUbicacion;

    private int resourceFoto;

    private ArrayList<Integer> maquinas;

    private double latitude;
    private double longitude;


    ZonaMusculacion (int _id){
        id=_id;

        maquinas = new ArrayList<Integer>();
    }

    public void addMaquina (int maquinaID){

        maquinas.add(maquinaID);
    }

    public void setNombre (String _name){
         name=_name;
    }

    public void setDescripcionUbicacion(String _descripcionUbicacion){
        descripcionUbicacion = _descripcionUbicacion;
    }

    public void setResourceFoto ( int _fotoID){

        resourceFoto = _fotoID;

    }

    public void setLatLng (String POINT){

        LatLng latLng = convert(POINT);

        latitude  = latLng.latitude;
        longitude = latLng.longitude;
    }

    public int getId(){
        return id;
    }

    @Override
    public boolean equals(Object object) {

        if (object != null && object instanceof ZonaMusculacion) {
            ZonaMusculacion thing = (ZonaMusculacion) object;

            return (thing.getId() == id);
        }

        return false;
    }

    private LatLng convert (String POINT){

        POINT=POINT.replace('(',' ');
        POINT=POINT.replace(')',' ');

        String geos [] = POINT.split(" ");

        double Long = Double.parseDouble(geos[2]);
        double Lat= Double.parseDouble(geos[3]);

        LatLng pto = new LatLng (Lat,Long);


        return pto;
    }




    public String toString(){
        String value ="Id: "+id;
        value += " name: "+name;
        value += " descripcionUbicacion: "+descripcionUbicacion;
        value += " resourceFoto: "+resourceFoto;
        value += " "+latitude+ " - "+ longitude;


        value += " maquinas: ";
        for (int valor:maquinas)
            value +=" "+valor;

        return value;
    }

    public int getResourceFoto() {
        return resourceFoto;
    }

    public String getName() {
        return name;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getDescripcionUbicacion() {
        return descripcionUbicacion;
    }

    public ArrayList<Integer> getMaquinas() {
        return maquinas;
    }
}

