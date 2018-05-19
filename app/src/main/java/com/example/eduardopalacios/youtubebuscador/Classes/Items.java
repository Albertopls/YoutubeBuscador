package com.example.eduardopalacios.youtubebuscador.Classes;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by eduardopalacios on 01/04/18.
 */

public class Items implements Parcelable{

    String idVideo,titulo,descripcion,urlImagen;

    public Items(String idVideo, String titulo, String descrpcion, String urlImagen)
    {
        this.idVideo=idVideo;
        this.titulo=titulo;
        this.descripcion=descrpcion;
        this.urlImagen=urlImagen;
    }

    protected Items(Parcel in) {
        idVideo = in.readString();
        titulo = in.readString();
        descripcion = in.readString();
        urlImagen = in.readString();
    }

    public static final Creator<Items> CREATOR = new Creator<Items>() {
        @Override
        public Items createFromParcel(Parcel in) {
            return new Items(in);
        }

        @Override
        public Items[] newArray(int size) {
            return new Items[size];
        }
    };

    public String getIdVideo()
    {
        return idVideo;
    }
    public String getTitulo()
    {
        return titulo;
    }

    public String getDescripcion()
    {
        return descripcion;
    }
    public String getUrlImagen()
    {
        return urlImagen;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(idVideo);
        parcel.writeString(titulo);
        parcel.writeString(descripcion);
        parcel.writeString(urlImagen);
    }
}
