package com.example;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.myapplication.R;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        // Retirez ces lignes si vous ne manipulez pas directement le TextView ici
        //TextView textView = findViewById(R.id.textView);
        //textView.setText("Contenu que vous souhaitez afficher");

        // Placez le code pour le Switch et son écouteur ici
        Switch switchModeSombre = findViewById(R.id.switch1);

        switchModeSombre.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // Vérifiez si le mode sombre est activé (isChecked)
                if (isChecked) {
                    // Mode sombre activé : changez le fond en noir et le texte en blanc
                    findViewById(R.id.secondLayout).setBackgroundColor(Color.BLACK);
                    ((TextView) findViewById(R.id.textView)).setTextColor(Color.WHITE);
                    ((TextView) findViewById(R.id.textview2)).setTextColor(Color.WHITE);
                    ((TextView) findViewById(R.id.switch1)).setTextColor(Color.WHITE);
                    // Modifiez les couleurs pour d'autres éléments si nécessaire
                } else {
                    // Mode sombre désactivé : revenez aux couleurs par défaut
                    findViewById(R.id.secondLayout).setBackgroundColor(Color.WHITE);
                    ((TextView) findViewById(R.id.textView)).setTextColor(Color.BLACK);
                    ((TextView) findViewById(R.id.textview2)).setTextColor(Color.YELLOW);
                    ((TextView) findViewById(R.id.switch1)).setTextColor(Color.YELLOW);
                    // Revenez aux couleurs par défaut pour d'autres éléments si nécessaire
                }
            }
        });
    }

    // Vous pouvez également ajouter d'autres méthodes ou fonctionnalités nécessaires pour cette activité ici

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
