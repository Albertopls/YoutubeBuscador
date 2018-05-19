package com.example.eduardopalacios.youtubebuscador;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.eduardopalacios.youtubebuscador.WebService.WebService;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    String claveyoutube="AIzaSyAdDix7i7a3an-gyXiquTV_14cIsr8-DZg";
    WebService webService;

    EditText buscar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buscar=findViewById(R.id.buscar);




    }

    public void onClickButton(View view)
    {
        if (view.getId()==R.id.boton){

            String busqueda=buscar.getText().toString();
            String Url="https://www.googleapis.com/youtube/v3/search?part=snippet&q="+busqueda+"&type=video&maxResults=20&key="+claveyoutube+"";

            webService=new WebService();
            webService.execute(Url);

            Bundle bundle=new Bundle();
            Intent intent=new Intent(getApplicationContext(),Main2Activity.class);
            try {
                bundle.putString("titulotoolbar",busqueda);
                bundle.putParcelableArrayList("valor", (ArrayList<? extends Parcelable>) webService.get());
                intent.putExtras(bundle);
                startActivity(intent);

            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }


        }

    }
}
