package com.example.tschausepp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Nicolas Feige
 *
 * @author Nicolas Feige
 * @version 1.0
 * @date 15.06.2020
 */

public class MultiplayerMenueActivity extends AppCompatActivity {

    private Button buttonStart;
    private TextView multiplayerMenueTitel1, multiplayerMenueTitel2, multiplayerMenueTitel3, multiplayerMenueTitel4, multiplayerMenueTitel5;
    private EditText multiplayerMenueEingabe1, multiplayerMenueEingabe2, multiplayerMenueEingabe3, multiplayerMenueEingabe4, multiplayerMenueEingabe5;
    private String spielerName1;
    private String spielerName2;
    private String spielerName3;
    private String spielerName4;
    private int punktzahl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiplayer_menue);

        buttonStart = findViewById(R.id.buttonStart);
        multiplayerMenueEingabe1 = findViewById(R.id.multiplayerMenueEingabe1);
        multiplayerMenueEingabe2 = findViewById(R.id.multiplayerMenueEingabe2);
        multiplayerMenueEingabe3 = findViewById(R.id.multiplayerMenueEingabe3);
        multiplayerMenueEingabe4 = findViewById(R.id.multiplayerMenueEingabe4);
        multiplayerMenueEingabe5 = findViewById(R.id.multiplayerMenueEingabe5);
        multiplayerMenueTitel1 = findViewById(R.id.multiplayerMenueTitel1);
        multiplayerMenueTitel2 = findViewById(R.id.multiplayerMenueTitel2);
        multiplayerMenueTitel3 = findViewById(R.id.multiplayerMenueTitel3);
        multiplayerMenueTitel4 = findViewById(R.id.multiplayerMenueTitel4);
        multiplayerMenueTitel5 = findViewById(R.id.multiplayerMenueTitel5);

        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickStart();
            }
        });
    }

    public void onClickStart() {
        punktzahl = Integer.parseInt(multiplayerMenueEingabe5.getText().toString());
        if (multiplayerMenueEingabe1.getText().toString().isEmpty() || multiplayerMenueEingabe2.getText().toString().isEmpty() || multiplayerMenueEingabe3.getText().toString().isEmpty() || multiplayerMenueEingabe4.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), "Es müssen mindestens vier Spieler mitspielen.", Toast.LENGTH_LONG).show();
        } else if (multiplayerMenueEingabe1.getText().length() < 2 || multiplayerMenueEingabe2.getText().length() < 2 || multiplayerMenueEingabe3.getText().length() < 2 || multiplayerMenueEingabe4.getText().length() < 2) {
            Toast.makeText(getApplicationContext(), "Die Spielernamen müssen mindestens zwei Zeichen lang sein.", Toast.LENGTH_LONG).show();
        } else if (multiplayerMenueEingabe1.getText().length() > 20 || multiplayerMenueEingabe2.getText().length() > 20 || multiplayerMenueEingabe3.getText().length() > 20 || multiplayerMenueEingabe4.getText().length() > 20) {
            Toast.makeText(getApplicationContext(), "Die Spielernamen dürfen maximal zwanzig Zeichen lang sein.", Toast.LENGTH_LONG).show();
        } else if (punktzahl < 1) {
            Toast.makeText(getApplicationContext(), "Die Punktezahl muss grösser als 0 sein.", Toast.LENGTH_LONG).show();
        } else {
            spielerName1 = multiplayerMenueEingabe1.getText().toString();
            spielerName2 = multiplayerMenueEingabe2.getText().toString();
            spielerName3 = multiplayerMenueEingabe3.getText().toString();
            spielerName4 = multiplayerMenueEingabe4.getText().toString();

            Intent intent = new Intent(MultiplayerMenueActivity.this, MultiplayerActivity.class);
            intent.putExtra("spielerName1", spielerName1);
            intent.putExtra("spielerName2", spielerName2);
            intent.putExtra("spielerName3", spielerName3);
            intent.putExtra("spielerName4", spielerName4);
            intent.putExtra("punkteZahl", punktzahl);
            startActivity(intent);
        }
    }

}