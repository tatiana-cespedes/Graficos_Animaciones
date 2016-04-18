package com.cespedes.tatiana.graficos_animaciones;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView imagen;
    int duracion = 2000;
    int tiempo = 200;
    int contador = 0;
    int repetir = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imagen = (ImageView) findViewById(R.id.imagen);

        final AlphaAnimation animacionIn = new AlphaAnimation(0.0f, 1.0f);//Comienza transparente y termina opaco.
        animacionIn.setDuration(duracion);
        animacionIn.setStartOffset(tiempo);


        final AlphaAnimation animacionOut = new AlphaAnimation(1.0f, 0.0f);//Comienza en opaco y termina en transparente.
        animacionOut.setDuration(duracion);
        animacionOut.setStartOffset(tiempo);


        animacionIn.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                repetir++;
                if (contador == 0) {
                    imagen.setImageResource(R.drawable.twd);
                    contador++;
                } else {
                    imagen.setImageResource(R.drawable.zombies);
                    contador = 0;
                }
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                animacionOut.setDuration(duracion);
                animacionOut.setStartOffset(tiempo);
                imagen.startAnimation(animacionOut);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });

        animacionOut.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (repetir < 10) {
                    duracion = duracion - 200;
                    tiempo = tiempo - 20;
                    animacionIn.setDuration(duracion);
                    animacionIn.setStartOffset(tiempo);
                    imagen.startAnimation(animacionIn);
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        imagen.startAnimation(animacionIn);





    }
}
