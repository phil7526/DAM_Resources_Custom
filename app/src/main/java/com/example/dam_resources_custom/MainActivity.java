package com.example.dam_resources_custom;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * exemple d'utilisation d'une resoures XML  custom
 * en cliquant sur le bouton j'affiche le contenu du fichier res/XML/map.xml
 * qui décrit une map.
 */
public class MainActivity extends AppCompatActivity  {
    Map<String,String> mapFilms;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();
        // lance le parser

       mapFilms= Utilitaires.readMapXML(this,"map");
    }


    public void on_click(View view) {
        TextView tv_affiche = this.findViewById(R.id.textView);
        StringBuilder mapData = new StringBuilder();
        // Afficher le contenu du MAP
        Set listeDesCles=mapFilms.keySet();  // Obtient la liste des clés
        Iterator iterateur=listeDesCles.iterator();
        // Parcourir les clés et afficher les entrées de chaque clé;
        while(iterateur.hasNext())
        {
            Object key= iterateur.next();
           mapData.append(key+"=>"+mapFilms.get(key)+ "\n");
        }
        tv_affiche.setText(mapData.toString());
    }
}