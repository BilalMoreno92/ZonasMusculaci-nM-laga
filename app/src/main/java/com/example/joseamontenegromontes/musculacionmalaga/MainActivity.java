package com.example.joseamontenegromontes.musculacionmalaga;

import android.content.Intent;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.opencsv.CSVReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


public class MainActivity extends AppCompatActivity {

    //ZONA CSV
    final int  GEO = 2;
    final int  ID_ZONA_MUSCULACION=14;
    final int  Nombre_ZONA_MUSCULACION =15;
    final int  UBICACION=16;
    final int  FOTO_ZONA=17;

    //MAQUINA CSV
    final int   Id_Maquina = 18;
    final int Nombre_Maquina = 19;
    final int Nivel = 20;
    final int Icono = 21;
    final int Funcion = 22;
    final int Desarrollo =23;
    final int Precauciones = 24;

    ArrayList<ZonaMusculacion> zonasMusculacion ;
    ArrayList<Maquina> maquinas;
    URL urlDatos;
    TextView update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         zonasMusculacion = new ArrayList<ZonaMusculacion>();
         maquinas = new ArrayList<Maquina>();
         update = findViewById(R.id.textViewActualizacion);
        try {
            urlDatos = new URL("http://datosabiertos.malaga.eu/recursos/deportes/zonasmusculacion/zonasMusculacion-4326.csv");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }


        InputStream is = getResources().openRawResource(R.raw.zonas_musculacion);
        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        CSVReader reader = new CSVReader(br);
        new readCSV().execute(reader);
    }

    /*
      OnClick
     */

    public void onClick(View v){

        Intent intent;

        switch (v.getId()){

            case R.id.imageButtonZonas:
                       //ToDo: ir Map Activity
                intent = new Intent(this, MapActivity.class);
                intent.putExtra("listaZonas", zonasMusculacion);
                startActivity(intent);
                break;
            case R.id.imageButtonMaquinas:
                        //ToDo: ir Maquinas Activity
                intent = new Intent(this, MaquinasActivity.class);
                intent.putExtra("listaMaquinas", maquinas);
                startActivity(intent);
                break;
            case R.id.imageButtonDownload:
                        downloadCSV();   break;
        }


    }

    public void downloadCSV(){

       //ToDo: Descarga
        new DownloadData().execute(urlDatos);
        Log.d("MainActivity","Download CSV");

    }


    // Funcion para pasar las url a resource ID.

    private int urlToResource (String url){

        int barra = url.lastIndexOf('/');
        int point = url.lastIndexOf('.');

        String file = url.substring(barra+1,point);

        Resources resource = getResources();
        String packageName = getPackageName();
        int resourceID = resource.getIdentifier(file,"drawable",packageName);

        return resourceID;

    }




    private class readCSV extends AsyncTask<CSVReader,Void,Void>{

        @Override
        protected Void doInBackground(CSVReader... csvReaders) {
            leerCSV(csvReaders[0]);
            return null;
        }

        public void leerCSV(CSVReader reader){

            zonasMusculacion = new ArrayList<ZonaMusculacion>();
            maquinas = new ArrayList<Maquina>();

            String [] nextLine=null;

            boolean cabecera = true;
            ZonaMusculacion zonaMusculacion = null;
            Maquina maquina = null;
            try {
                while ((nextLine = reader.readNext()) != null) {
                    if (cabecera) cabecera=false;

                    else{
                        //ToDO: Cargar zonas de musculación y máquinas

                        zonaMusculacion = new ZonaMusculacion(Integer.valueOf(nextLine[ID_ZONA_MUSCULACION]));
                        zonaMusculacion.setLatLng(nextLine[GEO]);
                        zonaMusculacion.setNombre(nextLine[Nombre_ZONA_MUSCULACION]);
                        zonaMusculacion.setDescripcionUbicacion(nextLine[UBICACION]);
                        zonaMusculacion.setResourceFoto(urlToResource(nextLine[FOTO_ZONA]));

                        maquina = new Maquina(Integer.valueOf(nextLine[Id_Maquina]));
                        maquina.setNombreMaquina(nextLine[Nombre_Maquina]);
                        maquina.setNivel(Integer.valueOf(nextLine[Nivel]));
                        maquina.setResourceFoto(urlToResource(nextLine[Icono]));
                        maquina.setFuncion(nextLine[Funcion]);
                        maquina.setDesarrollo(nextLine[Desarrollo]);
                        maquina.setPrecauciones(nextLine[Precauciones]);

                        if (!zonasMusculacion.contains(zonaMusculacion)){
                            zonasMusculacion.add(zonaMusculacion);
                        } else {
                            zonasMusculacion.get(zonasMusculacion.indexOf(zonaMusculacion)).addMaquina(maquina.getId());
                        }

                        if (!maquinas.contains(maquina)){
                            maquinas.add(maquina);
                        }

                       /// ZONA_MUSCULACION
                        Log.d("GEO",nextLine[GEO]) ;
                        Log.d("ID_ZONA_MUSCULACION",nextLine[ID_ZONA_MUSCULACION]) ;
                        Log.d("Nombre_ZONA_MUSCULACION",nextLine[Nombre_ZONA_MUSCULACION]) ;
                        Log.d("UBICACION",nextLine[UBICACION]) ;
                        Log.d("FOTO_ZONA",nextLine[FOTO_ZONA]) ;


                        /// MAQUINA
                        Log.d("Id_Maquina",nextLine[Id_Maquina]) ;
                        Log.d("Nombre_Maquina",nextLine[Nombre_Maquina]) ;
                        Log.d("Nivel",nextLine[Nivel]) ;
                        Log.d("Icono",nextLine[Icono]) ;
                        Log.d("Función",nextLine[Funcion]) ;
                        Log.d("Desarrollo",nextLine[Desarrollo]) ;
                        Log.d("Precauciones",nextLine[Precauciones]) ;


                    }

                }
            } catch (IOException e) {
                e.printStackTrace();
            }


            for (ZonaMusculacion zona:zonasMusculacion){
                Log.d("Zona",zona.toString());
            }

            for (Maquina maquinaActual:maquinas){
                Log.d("Maquina",maquinaActual.toString());
            }

        }

    }

    private class DownloadData extends AsyncTask<URL, Void, String> {

        @Override
        protected String doInBackground(URL... urls) {
            URL url = urls[0];
            CSVReader reader;
            BufferedReader input = null;
            try {
                input = new BufferedReader(new InputStreamReader(url.openStream()));
                reader = new CSVReader(input);
                new readCSV().execute(reader);
            } catch (IOException e) {
                e.printStackTrace();
            }
            DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
            Date today = Calendar.getInstance().getTime();

            return df.format(today);
        }

        @Override
        protected void onPostExecute(String s) {
            update.setText("Ultima actualización: " + s);
        }
    }
}
