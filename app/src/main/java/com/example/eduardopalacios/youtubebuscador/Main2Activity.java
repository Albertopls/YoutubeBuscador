package com.example.eduardopalacios.youtubebuscador;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.eduardopalacios.youtubebuscador.Adaptadores.AdapterDatos;
import com.example.eduardopalacios.youtubebuscador.Classes.Items;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {

    ArrayList<Items> datos;
    AdapterDatos adapterDatos;
    ListView listView;
    Toolbar toolbar;
    String tituloToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        toolbar=findViewById(R.id.toolbar);
        listView=findViewById(R.id.lista);


        final Bundle bundle=getIntent().getExtras();
        datos=new ArrayList<>();


        tituloToolbar=bundle.getString("titulotoolbar");
        datos=bundle.getParcelableArrayList("valor");


        toolbar.setTitle("Resultados de "+tituloToolbar);
        toolbar.setTitleTextColor(Color.parseColor("#ffffff"));
        setSupportActionBar(toolbar);

        adapterDatos=new AdapterDatos(this,R.layout.diseniolistview,datos);
        listView.setAdapter(adapterDatos);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Bundle bundle1=new Bundle();
                Intent intent=new Intent(Main2Activity.this,Main3Activity.class);

                bundle1.putString("claveVideo",datos.get(i).getIdVideo());
                bundle1.putString("titulo",datos.get(i).getTitulo());
                intent.putExtras(bundle1);
                startActivity(intent);
            }
        });

    }
}
