package com.marcinmoskala.arcseekbar.sample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.marcinmoskala.arcseekbar.ArcSeekBar;
import com.marcinmoskala.arcseekbar.ProgressListener;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {



    int progreso = 0;
    TextView calificacion;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArcSeekBar arcSeekBar = findViewById(R.id.seekArc);
        calificacion = findViewById(R.id.calificacion);

        arcSeekBar.setMaxProgress(255);
        //ProgressListener progressListener = progress -> Log.i("SeekBar", "Value is " + progress); calificacion.setText(""+progress);
        //ProgressListener progressListener = progress -> progreso = progress;
        ProgressListener progressListener = progress -> PintaCalificacion(progress);
        progressListener.invoke(0);
        arcSeekBar.setOnProgressChangedListener(progressListener);

        int[] intArray = getResources().getIntArray(R.array.progressGradientColors);
        arcSeekBar.setProgressGradient(intArray);







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



        calificacion.setText(String.format("%.2f", valoReal));
    }


    public float getConvertedValue(int intVal){
        float floatVal = (float) 0.0;
        floatVal = .50f * intVal;
        return floatVal;
    }
}
