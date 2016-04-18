package com.cespedes.tatiana.myapplication2;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;


public class MainActivity extends AppCompatActivity {

    private View mContentView;
    private View mLoadingView;
    private int mAnimationDuration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContentView = findViewById(R.id.content);
        mLoadingView = findViewById(R.id.loading_spinner);

        mContentView.setVisibility(View.GONE);//Ocultar inicialmente el contenido de la pagina

        // Toma un tiempo de espera definido por el sistema
        // de acuerdo a la memoria caché que esté utilizando
        mAnimationDuration= getResources().getInteger(
                android.R.integer.config_longAnimTime);

        crossfade();
    }

    private void crossfade() {

        mContentView.setAlpha(0f);//Configura la imagen con 0% de opacidad
        mContentView.setVisibility(View.VISIBLE);//Configura la imagen como visible(pero transparente)

        //Animación que hace que aparezca de nuevo la imagen, durante el tiempo ya establecido
        mContentView.animate()
                .alpha(1f)
                .setDuration(mAnimationDuration)
                .setListener(null);

        //Animación que hace desaparecer la barra de progreso, durante el tiempo ya establecido
        //Cuando la animacion termina, quita totalmente su visibilidad
        mLoadingView.animate()
                .alpha(0f)
                .setDuration(mAnimationDuration)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        mLoadingView.setVisibility(View.GONE);
                    }
                });
    }

}
