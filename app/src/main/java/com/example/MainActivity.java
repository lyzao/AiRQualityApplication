package com.example;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private boolean termsAccepted = false;
    private TextToSpeech textToSpeech;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Afficher les termes d'utilisation au lancement de l'application
        afficherTermesUtilisation();

        // Récupération du Switch pour le mode sombre
        @SuppressLint("UseSwitchCompatOrMaterialCode") Switch switchModeSombre = findViewById(R.id.switch1);

        switchModeSombre.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // Vérification du mode sombre activé (isChecked)
                if (isChecked) {
                    // Mode sombre activé : changer le fond en noir et le texte en blanc
                    findViewById(R.id.mainLayout).setBackgroundColor(Color.BLACK);
                    ((TextView) findViewById(R.id.textview2)).setTextColor(Color.WHITE);
                    ((TextView) findViewById(R.id.temp)).setTextColor(Color.WHITE);
                    ((TextView) findViewById(R.id.hum)).setTextColor(Color.WHITE);
                    ((TextView) findViewById(R.id.pre)).setTextColor(Color.WHITE);
                    ((TextView) findViewById(R.id.gaz)).setTextColor(Color.WHITE);
                    ((TextView) findViewById(R.id.textView5)).setTextColor(Color.WHITE);
                    ((TextView) findViewById(R.id.textView6)).setTextColor(Color.WHITE);
                    ((TextView) findViewById(R.id.textView7)).setTextColor(Color.WHITE);
                    ((TextView) findViewById(R.id.textView8)).setTextColor(Color.WHITE);

                    ImageView imageView = findViewById(R.id.imageView8); // Remplacez mon_imageview par l'ID de votre ImageView
                    imageView.setImageResource(R.drawable.thermometerb);

                    ImageView imageView1 = findViewById(R.id.imageView3); // Remplacez mon_imageview par l'ID de votre ImageView
                    imageView1.setImageResource(R.drawable.humidityw);

                    ImageView imageView2 = findViewById(R.id.imageView4); // Remplacez mon_imageview par l'ID de votre ImageView
                    imageView2.setImageResource(R.drawable.barometerw);

                    ImageView imageView3 = findViewById(R.id.imageView5); // Remplacez mon_imageview par l'ID de votre ImageView
                    imageView3.setImageResource(R.drawable.gasw);


                    switchModeSombre.setTextColor(Color.WHITE);
                    // Modifier les couleurs pour d'autres éléments si nécessaire
                } else {
                    // Mode sombre désactivé : revenir aux couleurs par défaut
                    findViewById(R.id.mainLayout).setBackgroundColor(Color.WHITE);
                    ((TextView) findViewById(R.id.textview2)).setTextColor(Color.BLACK);
                    ((TextView) findViewById(R.id.temp)).setTextColor(Color.BLACK);
                    ((TextView) findViewById(R.id.hum)).setTextColor(Color.BLACK);
                    ((TextView) findViewById(R.id.pre)).setTextColor(Color.BLACK);
                    ((TextView) findViewById(R.id.gaz)).setTextColor(Color.BLACK);
                    ((TextView) findViewById(R.id.textView5)).setTextColor(Color.BLACK);
                    ((TextView) findViewById(R.id.textView6)).setTextColor(Color.BLACK);
                        ((TextView) findViewById(R.id.textView7)).setTextColor(Color.BLACK);
                    ((TextView) findViewById(R.id.textView8)).setTextColor(Color.BLACK);
                    ImageView imageView = findViewById(R.id.imageView8); // Remplacez mon_imageview par l'ID de votre ImageView
                    imageView.setImageResource(R.drawable.thermometerw);

                    ImageView imageView1 = findViewById(R.id.imageView3); // Remplacez mon_imageview par l'ID de votre ImageView
                    imageView1.setImageResource(R.drawable.humidityb);

                    ImageView imageView2 = findViewById(R.id.imageView4); // Remplacez mon_imageview par l'ID de votre ImageView
                    imageView2.setImageResource(R.drawable.barometerb);

                    ImageView imageView3 = findViewById(R.id.imageView5); // Remplacez mon_imageview par l'ID de votre ImageView
                    imageView3.setImageResource(R.drawable.gasb);
                    switchModeSombre.setTextColor(Color.BLACK);



                    // Revenir aux couleurs par défaut pour d'autres éléments si nécessaire
                }
            }
        });

        // Initialiser TextToSpeech
        textToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    // Définir la langue en français
                    int result = textToSpeech.setLanguage(Locale.FRANCE);

                    // Vérifier si la langue est supportée
                    if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        // Gérer le cas où la langue n'est pas disponible ou n'est pas prise en charge
                        Toast.makeText(MainActivity.this, "La langue française n'est pas disponible.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    // Gérer le cas d'échec de l'initialisation de TextToSpeech
                    Toast.makeText(MainActivity.this, "Échec de l'initialisation de la synthèse vocale.", Toast.LENGTH_SHORT).show();
                }
            }
        });
        // Ajouter un écouteur de clic pour le bouton de lecture
        Button buttonLire = findViewById(R.id.buttonLire);
        buttonLire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (termsAccepted) {
                    // Obtenez le texte que vous souhaitez lire
                    TextView tempTextView = findViewById(R.id.temp);
                    TextView humTextView = findViewById(R.id.hum);
                    TextView preTextView = findViewById(R.id.pre);
                    TextView gazTextView = findViewById(R.id.gaz);
                    // Obtenez le texte du TextView
                    String temperature = tempTextView.getText().toString();
                    String humidity = humTextView.getText().toString();
                    String pression = preTextView.getText().toString();
                    String gaz = gazTextView.getText().toString();

                    // Concaténez la chaîne "La température est " avec la température
                    String texteALire = "La température est   " + temperature + "l'humidity est à  " + humidity+ "La pression est à   " + pression + "le gaz est à   " + gaz ;

                    // Utilisez TextToSpeech pour lire le texte
                    textToSpeech.speak(texteALire, TextToSpeech.QUEUE_FLUSH, null, null);
                } else {
                    // Affichez un message demandant à l'utilisateur d'accepter les termes
                    Toast.makeText(MainActivity.this, "Veuillez accepter les termes d'utilisation", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void afficherTermesUtilisation() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Conditions d'utilisation de l'application AirQuality")
                .setMessage("Objectif :\n" +
                        "Fournir des informations sur la qualité de l'air de votre pièce.\n" +
                        "\n" +
                        "Collecte de données :\n" +
                        "Vos données sont anonymes, utilisées uniquement pour améliorer nos services.\n" +
                        "\n" +
                        "Mises à jour :\n" +
                        "Nous pouvons mettre à jour l'application pour améliorer ses fonctionnalités.\n" +
                        "\n" +
                        " Support :\n" +
                        "Pour toute question contactez-nous à AirQualityapp@email.com")
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
                                .setNegativeButton("Accepter les termes", new DialogInterface.OnClickListener() {
                                 @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                // Action lorsque l'utilisateur accepte les termes
                                        termsAccepted = true; // Indiquer que les termes ont été acceptés
                                // Continuer avec la fermeture de la boîte de dialogue de confirmation de sortie
                                        dialog.dismiss();
                                     }
                                 })
                                .show();
                    }
                })
                .setCancelable(false) // Empêcher de fermer la boîte de dialogue en cliquant à l'extérieur
                .show();
    }

    // ... (le reste de votre code)

    @Override
    protected void onDestroy() {
        // Libérez les ressources de TextToSpeech lors de la fermeture de l'activité
        if (textToSpeech != null) {
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
        super.onDestroy();
    }
}
