package com.example.joseamontenegromontes.musculacionmalaga;

import java.io.Serializable;

public class Maquina implements Serializable{


    private int id;
    private String NombreMaquina;

    private int nivel;

    private int icono;


    private String funcion;
    private String desarrollo;
    private String precauciones;




    Maquina (int _id){
        id = _id;
    }

    public int getId (){
        return id;
    }

    public void setNombreMaquina(String _NombreMaquina){
        NombreMaquina = _NombreMaquina;
    }

    public void setNivel (int _nivel){
        nivel = _nivel;
    }

    public void setFuncion(String _funcion){
        funcion =_funcion;
    }

    public void setDesarrollo(String _desarrollo){
        desarrollo = _desarrollo;
    }

    public void setPrecauciones(String _precauciones){
        precauciones = _precauciones;
    }


    @Override
    public boolean equals(Object object) {

        if (object != null && object instanceof Maquina) {
            Maquina thing = (Maquina) object;

            return (thing.getId() == id);
        }

        return false;
    }

    public void setResourceFoto ( int _fotoID){

        icono = _fotoID;

    }

    public String toString(){
        String value ="Id: "+id;
        value += " name: "+NombreMaquina;
        value += " nivel: "+nivel;
        value += " resourceFoto: "+icono;
        value += " funcion: "+funcion;
        value += " desarrollo: "+desarrollo;
        value += " precauciones: "+precauciones;

        return value;
    }

    public String getNombreMaquina() {
        return NombreMaquina;
    }

    public int getIcono(){
        return icono;
    }

    public String getDesarrollo() {
        return desarrollo;
    }

    public String getFuncion() {
        return funcion;
    }

    public String getPrecauciones() {
        return precauciones;
    }

    public int getNivel() {
        return nivel;
    }
}
