package com.example;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

public class MainActivity extends AppCompatActivity {

    private boolean termsAccepted = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Afficher les termes d'utilisation au lancement de l'application
        afficherTermesUtilisation();

        // Récupération du Switch pour le mode sombre
        Switch switchModeSombre = findViewById(R.id.switch1);

        switchModeSombre.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // Vérification du mode sombre activé (isChecked)
                if (isChecked) {
                    // Mode sombre activé : changer le fond en noir et le texte en blanc
                    findViewById(R.id.mainLayout).setBackgroundColor(Color.BLACK);
                    ((TextView) findViewById(R.id.textview2)).setTextColor(Color.WHITE);
                    switchModeSombre.setTextColor(Color.WHITE);
                    // Modifier les couleurs pour d'autres éléments si nécessaire
                } else {
                    // Mode sombre désactivé : revenir aux couleurs par défaut
                    findViewById(R.id.mainLayout).setBackgroundColor(Color.WHITE);
                    ((TextView) findViewById(R.id.textview2)).setTextColor(Color.BLACK);
                    switchModeSombre.setTextColor(Color.BLACK);
                    // Revenir aux couleurs par défaut pour d'autres éléments si nécessaire
                }
            }
        });
    }

    private void afficherTermesUtilisation() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Conditions d'utilisation de l'application AirQuality")
                .setMessage("ici je vais mettre les termes d'utilisation (l'application utilisera les informations d'air de votre pièce)  ")
                .setPositiveButton("Accepter", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Action lorsque l'utilisateur accepte les termes
                        dialog.dismiss(); // Fermer la boîte de dialogue
                        termsAccepted = true; // Indiquer que les termes ont été acceptés
                    }
                })
                .setNegativeButton("Refuser", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Demander confirmation avant de quitter l'application
                        new AlertDialog.Builder(MainActivity.this)
                                .setTitle("Quitter l'application")
                                .setMessage("Êtes-vous sûr de vouloir quitter l'application ?")
                                .setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        // Si l'utilisateur confirme, fermer l'application
                                        finish();
                                    }
                                })
                                .setNegativeButton("Accepter les termes", null) // Annuler la fermeture de l'application
                                .show();

                    }
                })
                .setCancelable(false) // Empêcher de fermer la boîte de dialogue en cliquant à l'extérieur
                .show();
    }

    public void openSecondPage(View view) {
        if (termsAccepted) { // Vérifier si les termes ont été acceptés avant d'ouvrir la deuxième page
            Intent intent = new Intent(this, SecondActivity.class);
            startActivity(intent);
        } else {
            // Afficher un message pour indiquer à l'utilisateur d'accepter les termes
            // Vous pouvez afficher un Toast ou une boîte de dialogue pour informer l'utilisateur ici
        }
    }
}
