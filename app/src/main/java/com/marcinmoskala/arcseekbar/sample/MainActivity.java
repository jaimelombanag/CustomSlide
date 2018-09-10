package com.marcinmoskala.arcseekbar.sample;

import android.content.Context;
import android.os.Bundle;
import android.os.PowerManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.marcinmoskala.arcseekbar.ArcSeekBar;
import com.marcinmoskala.arcseekbar.ProgressListener;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {



    private ArcSeekBar arcSeekBar;
    int progreso = 0;
    TextView calificacion;
    private LottieAnimationView animation;
    private GifImageView gifImageView;
    private GifImageView gifImageView2;
    private GifImageView animation2;
    protected PowerManager.WakeLock wakelock;

    private LinearLayout layout_principal;
    private LinearLayout layout_calificar;
    private TextView txt_calFinal;
    private String valorFinal;


    Animation animFadeIn,animFadeOut,animBlink,animZoomIn,animZoomOut,animRotate
            ,animMove,animSlideUp,animSlideDown,animBounce,animSequential,animTogether,animCrossFadeIn,animCrossFadeOut;



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        layout_principal = findViewById(R.id.layout_principal);
        layout_calificar = findViewById(R.id.layout_calificar);
        txt_calFinal = findViewById(R.id.txt_calFinal);
        arcSeekBar = findViewById(R.id.seekArc);
        calificacion = findViewById(R.id.calificacion);
        animation = findViewById(R.id.animation);
        /*******************************Para que La pantalla no se apague*********************/
        final PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
        this.wakelock = pm.newWakeLock(PowerManager.SCREEN_DIM_WAKE_LOCK, "etiqueta");
        wakelock.acquire();


        DesabilitarConteo();

        arcSeekBar.setMaxProgress(255);
        //ProgressListener progressListener = progress -> Log.i("SeekBar", "Value is " + progress); calificacion.setText(""+progress);
        //ProgressListener progressListener = progress -> progreso = progress;
        ProgressListener progressListener = progress -> PintaCalificacion(progress);
        progressListener.invoke(0);
        arcSeekBar.setOnProgressChangedListener(progressListener);

        int[] intArray = getResources().getIntArray(R.array.progressGradientColors);
        arcSeekBar.setProgressGradient(intArray);



        gifImageView = (GifImageView) findViewById(R.id.GifImageView);
        gifImageView.setGifImageResource(R.drawable.init);

        gifImageView2 = (GifImageView) findViewById(R.id.GifImageView2);
        gifImageView2.setGifImageResource(R.drawable.init);


        animation2 = (GifImageView) findViewById(R.id.animation2);
        animation2.setGifImageResource(R.drawable.loading);


        animZoomOut = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.zoom_out);
        animZoomIn = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.zoom_in);



    }

    public void DesabilitarConteo(){
        arcSeekBar.setEnabled(false);
    }


    public void PintaCalificacion(int progreso){
        float prodresoDecimal = getConvertedValue(progreso);
        //Log.i("JAIME", "-----------------   "   + prodresoDecimal);

        DecimalFormat decimalFormat = new DecimalFormat("0.00%");

        float progressPercentageFloat = (float)progreso / (float)255 ;

        String progressPercentage = decimalFormat.format(progressPercentageFloat);


        Log.i("JAIME", "===================   "   + progressPercentage);
        float valoReal = ((progressPercentageFloat*100) / 2) / 10;
        Log.i("JAIME", "=======REAL========  "   + valoReal);



        valorFinal = String.format("%.2f", valoReal);
        calificacion.setText(String.format("%.2f", valoReal));

    }


    public float getConvertedValue(int intVal){
        float floatVal = (float) 0.0;
        floatVal = .50f * intVal;
        return floatVal;
    }

    public void InicioConteo(View v){
        //Toast.makeText(this, "Se Oprime el boton de inicio", Toast.LENGTH_SHORT).show();
        animation2.setVisibility(View.VISIBLE);
        arcSeekBar.setEnabled(true);


    }

    public void ConfirmaConteo(View v){
       // Toast.makeText(this, "Se Oprime el boton de Confirmacion", Toast.LENGTH_SHORT).show();
        animation2.setVisibility(View.GONE);
        arcSeekBar.setEnabled(false);
        MuestraCalificacion();

    }


    public void MuestraCalificacion(){
        txt_calFinal.setText(valorFinal);
        layout_calificar.setVisibility(View.VISIBLE);
        layout_principal.setVisibility(View.GONE);
        txt_calFinal.startAnimation(animZoomIn);
        arcSeekBar.setProgress(0);

    }


    public void Exit(View v){

        layout_calificar.setVisibility(View.GONE);
        layout_principal.setVisibility(View.VISIBLE);
    }


}
