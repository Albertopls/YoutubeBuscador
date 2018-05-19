package com.example.eduardopalacios.youtubebuscador;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class Main3Activity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener ,YouTubePlayer.PlaybackEventListener{


    String claveYoutube="AIzaSyBt_wFkCfhQ99e2aHAqAR_wO1GJlecqQbw";
    String claveVideo;
    String tituloVideo;
    YouTubePlayerView youTubePlayerView;

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        toolbar=findViewById(R.id.toolbar);

        Bundle bundle=getIntent().getExtras();
        claveVideo=bundle.getString("claveVideo");
        tituloVideo=bundle.getString("titulo");

        toolbar.setTitle(tituloVideo);
        toolbar.setTitleTextColor(Color.parseColor("#ffffff"));

        youTubePlayerView=findViewById(R.id.youtubeView);
        youTubePlayerView.initialize(claveYoutube,this);
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean restaurado) {

        if (!restaurado)
        {
            youTubePlayer.cueVideo(claveVideo);
        }


    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

        if(youTubeInitializationResult.isUserRecoverableError())
        {
            youTubeInitializationResult.getErrorDialog(this,1).show();
        }
        else {

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
       if (requestCode==1)
       {
           getYoutubePlayerProvider().initialize(claveYoutube,this);
       }
    }

    protected YouTubePlayer.Provider getYoutubePlayerProvider(){
        return youTubePlayerView;
    }

    @Override
    public void onPlaying() {

    }

    @Override
    public void onPaused() {

    }

    @Override
    public void onStopped() {

    }

    @Override
    public void onBuffering(boolean b) {

    }

    @Override
    public void onSeekTo(int i) {

    }
}
