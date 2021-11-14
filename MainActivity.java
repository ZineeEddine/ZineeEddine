package com.example.chronomtre_app;

// Les différentes importations :

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;
import java.util.Locale;
/*
lien utile:
https://developer.android.com/reference/android/os/Handler

 */

public class MainActivity extends AppCompatActivity {
    /*

    VOUS POUVEZ IMPORTER DIRECTEMENT UN CHRONOMETRE
                   OU
     LE FAIRE DE CETTE MANIERE

     */
    /*

    Déclaration d'une variable seconde
    qui va être incrémenter de 1:
    ( seconde = secondes + 1) à chaques secondes

    et

    Déclaration d'un boolean running
    qui va servir à donner 2 état true et false
    pour le chronomètre


     */
    private int seconde = 0;
    private boolean running;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*

        Appel de la fonction Start_Chrono :
         va lancer le chronomètre

        */
        Start_Chrono();
    }

    /*

    Dans le fichier xml : activity_main.xml:
     - j'ai mis -> android:onClick="onClickStart"
     dans le bouton startBtn,
    du coups j'appel directement onClcikStart depuis
    le fichier MainActivity.java

    */
    public void onClickStart(View view) {

        running = true;
    }

    // idem
    public void onClickStop(View view) {
        running = false;
    }

    // idem
    public void onClickReset(View view) {
        running = false;
        seconde = 0;
    }

    private void Start_Chrono(){

        /*
        on récupère TextView_chrono avec son id car on va devoir
        le modifier toute les 1000 milisecondes ( 1 seconde )
         */

        final TextView timeView = (TextView) findViewById(R.id.TextView_temps);
        final Handler handler = new Handler();

        handler.post(new Runnable() {
            @Override
            public void run() {

                // opérations du chronomètre:

                int heures = seconde/3600;
                int minutes = (seconde%3600)/60;
                int secs = seconde%60;

                String time = String.format(Locale.getDefault(), "%02d:%02d:%02d", heures, minutes, secs);
                timeView.setText(time); //va changer l'état du text , va lui attribuer la valeur time
                if (running) { // si le chronomètre est lancer alors:
                    seconde++; // incrémentation
                } //toutes les 1000 millisecondes ( 1 sec )
                handler.postDelayed(this,1000);
            }
        });
    }
}
