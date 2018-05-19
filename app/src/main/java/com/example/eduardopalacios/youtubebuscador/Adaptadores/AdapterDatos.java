package com.example.eduardopalacios.youtubebuscador.Adaptadores;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.eduardopalacios.youtubebuscador.Classes.Items;
import com.example.eduardopalacios.youtubebuscador.Holders.holderItem;
import com.example.eduardopalacios.youtubebuscador.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by eduardopalacios on 01/04/18.
 */

public class AdapterDatos extends ArrayAdapter <Items>{

    Context context;
    int resource;
    List<Items> items;

    public AdapterDatos(Context context, int resource, List<Items> items) {
        super(context, resource, items);

        this.context=context;
        this.resource=resource;
        this.items=items;

    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view=convertView;
        holderItem holderItem=null;
        if (view==null)
        {
            LayoutInflater inflater=LayoutInflater.from(context);
            view=inflater.inflate(resource,parent,false);
            holderItem=new holderItem();

            holderItem.imagen=view.findViewById(R.id.imagenyoutube);
            holderItem.titulo=view.findViewById(R.id.titulovideo);
            holderItem.canal=view.findViewById(R.id.canal);
            view.setTag(holderItem);
        }
        else {
            holderItem=(holderItem) view.getTag();
        }

        Picasso.with(context).load(items.get(position).getUrlImagen()).into(holderItem.imagen);
        holderItem.titulo.setText(items.get(position).getTitulo());
        holderItem.canal.setText(items.get(position).getDescripcion());




        return view;
    }
}
