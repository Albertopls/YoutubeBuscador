package com.example.eduardopalacios.youtubebuscador.WebService;

import android.os.AsyncTask;

import com.example.eduardopalacios.youtubebuscador.Classes.Items;
import com.example.eduardopalacios.youtubebuscador.classGson.Default;
import com.example.eduardopalacios.youtubebuscador.classGson.Id;
import com.example.eduardopalacios.youtubebuscador.classGson.Item;
import com.example.eduardopalacios.youtubebuscador.classGson.Snippet;
import com.example.eduardopalacios.youtubebuscador.classGson.Thumbnails;
import com.example.eduardopalacios.youtubebuscador.classGson.Values;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by eduardopalacios on 11/03/18.
 */

public class WebService extends AsyncTask<String,Integer,List<Items>> {

    List<Items> datos = null;
    @Override
    protected List<Items> doInBackground(String... params) {
        String ruta=params[0];


        URL url=null;
        try {

            url=new URL(ruta);
            HttpURLConnection conexion=(HttpURLConnection)url.openConnection();
            conexion.setRequestProperty("User-Agent", "Mozilla/5.0" +
                    " (Linux; Android 1.5; es-ES)");

            int respuesta=conexion.getResponseCode();

            StringBuilder result = new StringBuilder();

            if (respuesta==HttpURLConnection.HTTP_OK)
            {
                //permite leer el json del url(lectura de bytes)
               // InputStream lecturaJSon=new BufferedInputStream(conexion.getInputStream());
                // BufferedReader es el lector
                BufferedReader reader=new BufferedReader(new InputStreamReader(conexion.getInputStream()));


                String line;
                while ((line=reader.readLine())!=null)
                {

                    result.append(line);
                }

              //Serealizacion(result.toString());

                SerlializacionGson(result.toString());
            }


        }catch (IOException e)
        {
            e.printStackTrace();

        }

        return datos;
    }



    public void Serealizacion(String gson)
    {

        JSONObject respuestaJson;
        datos=new ArrayList();

        try {
            respuestaJson = new JSONObject(gson);
            JSONArray resultJson=respuestaJson.getJSONArray("items");

            for (int i=0;i<resultJson.length();i++)
            {
                JSONObject result = resultJson.getJSONObject(i);


                //Extraccion del id del video de youtube
                JSONObject resultId = result.getJSONObject("id");


                //Extraccion de descripcion y titulo del video
                JSONObject resultSnippet = result.getJSONObject("snippet");


                JSONObject resultthumbnails = resultSnippet.getJSONObject("thumbnails");

                //Extraccion de la url de la imagen del video
                JSONObject resultmedium = resultthumbnails.getJSONObject("medium");

                //datos.add(resultSnippet.getString("title"));

                datos.add(new Items(resultId.getString("videoId"),resultSnippet.getString("title"),resultSnippet.getString("channelTitle"),
                        resultmedium.getString("url")));




            }


        } catch (JSONException e) {
            e.printStackTrace();
        }



    }

    public void SerlializacionGson(String Json)
    {

        datos=new ArrayList<>();
        String idVideo,titulo,canal,urlImagen;
        Gson gson=new Gson();
        Values values=gson.fromJson(Json,Values.class);

        List<Item> item=values.getItems();

        for ( Item valor:item) {

            Id id=valor.getId();
            idVideo=id.getVideoId();

            Snippet snippet=valor.getSnippet();
            titulo=snippet.getTitle();
            canal=snippet.getChannelTitle();

            Thumbnails thumbnails=snippet.getThumbnails();
            Default deft=thumbnails.getDefault();
            urlImagen=deft.getUrl();


            datos.add(new Items(idVideo,titulo,canal,urlImagen));

        }


    }

}
