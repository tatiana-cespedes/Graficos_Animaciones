package com.cespedes.tatiana.myapplication;

import android.app.Activity;
import android.content.Context;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class MainActivity extends AppCompatActivity {

    Animation traslacion;
    Animation rotacion;
    Animation escala;
    Animation transparencia;
    Button btrasladar, brotar, btransparencia, bescalar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ImageView imagen = (ImageView)findViewById(R.id.imagen);
        btrasladar = (Button)findViewById(R.id.btrasladar);
        brotar = (Button)findViewById(R.id.brotar);
        btransparencia = (Button)findViewById(R.id.btransparencia);
        bescalar = (Button)findViewById(R.id.bescalar);

        traslacion = AnimationUtils.loadAnimation(this, R.anim.transladar);
        rotacion = AnimationUtils.loadAnimation(this, R.anim.rotar);
        escala = AnimationUtils.loadAnimation(this, R.anim.escalar);
        transparencia = AnimationUtils.loadAnimation(this, R.anim.transparencia);

        btrasladar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                imagen.startAnimation(traslacion);
            }
        });

        brotar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imagen.startAnimation(rotacion);
            }
        });

        btransparencia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 imagen.startAnimation(transparencia);
            }
        });

        bescalar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imagen.startAnimation(escala);
            }
        });

    }

}
