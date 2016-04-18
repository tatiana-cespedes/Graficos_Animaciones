package com.cespedes.tatiana.aplicacion3;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.Point;
import android.graphics.Rect;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageButton;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    ImageButton imagen;

    private Animator mCurrentAnimator;
    private int mShortAnimationDuration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final View boton = findViewById(R.id.button_1);

        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                zoomImage(boton, R.drawable.android);
            }
        });
            mShortAnimationDuration = getResources().getInteger(
                    android.R.integer.config_shortAnimTime);
        }

        private void zoomImage(final View boton, int imagen) {
            //Si hay una animación en progreso, cancelarla y comenzar con la nueva animación
            if (mCurrentAnimator != null) {
                mCurrentAnimator.cancel();
            }

            //Cargar la imagen expandida
            final ImageView ImagenExpandida = (ImageView) findViewById(R.id.imagenExpandida);
            ImagenExpandida.setImageResource(imagen);

            boton.setAlpha(0f); // Volver el boton transparente
            ImagenExpandida.setVisibility(View.VISIBLE); //Mostrar la imagen Expandida

            //Se establece el pivote en la esquina superior izquierda
            ImagenExpandida.setPivotX(0f);
            ImagenExpandida.setPivotY(0f);

            //Crear una nueva animacion
            AnimatorSet set = new AnimatorSet();

            set.setDuration(mShortAnimationDuration);

            set.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mCurrentAnimator = null;
                }

                @Override
                public void onAnimationCancel(Animator animation) {
                    mCurrentAnimator = null;
                }
            });
            set.start();
            mCurrentAnimator = set;

           //Cuando se haga click sobre la imagen expandida, debe volver a la imagen del boton
            ImagenExpandida.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mCurrentAnimator != null) {
                        mCurrentAnimator.cancel();
                    }

                    AnimatorSet set = new AnimatorSet();

                    set.setDuration(mShortAnimationDuration);
                    set.addListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            boton.setAlpha(1f);//Volver a mostrar la imagen en el boton
                            ImagenExpandida.setVisibility(View.GONE); //Quitar la imagen expandida
                            mCurrentAnimator = null;
                        }
                        @Override
                        public void onAnimationCancel(Animator animation) {
                            boton.setAlpha(1f);
                            ImagenExpandida.setVisibility(View.GONE);
                            mCurrentAnimator = null;
                        }
                    });
                    set.start();
                    mCurrentAnimator = set;
                }
            });
        }

}
