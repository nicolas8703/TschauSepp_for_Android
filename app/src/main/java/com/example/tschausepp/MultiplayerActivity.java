package com.example.tschausepp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;
import java.util.stream.Collectors;


public class MultiplayerActivity extends AppCompatActivity {

    ImageButton buttonKartenRuecken, buttonLetzteKarte;
    ImageButton imageButtonKarte1, imageButtonKarte2, imageButtonKarte3, imageButtonKarte4, imageButtonKarte5, imageButtonKarte6, imageButtonKarte7, imageButtonKarte8, imageButtonKarte9;
    TextView tvSpielerName, tvSpielerPunkte;
    Button buttonKarteZiehen, buttonTschau, buttonSepp;

    private Spiel spiel;
    private Vector<String> kartenIDs;
    private Vector<ImageButton> kartenButtons = new Vector<>();
    private ArrayList<String> temp1;

    String spielerName1;
    String spielerName2;
    String spielerName3;
    String spielerName4;
    int punktzahl;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiplayer);
        init();
        Spieler spieler1 = new Spieler(spielerName1);
        Spieler spieler2 = new Spieler(spielerName2);
        Spieler spieler3 = new Spieler(spielerName3);
        Spieler spieler4 = new Spieler(spielerName4);
        spiel = new Spiel(spieler1, spieler2, spieler3, spieler4, punktzahl, this);
        kartenIDs = new Vector<>();
        kartenBilderHinzufuegen();
        spiel.startRunde(spiel);
        spielerInfosAnzeigen();
        buttonKartenRuecken.setImageResource(getResources().getIdentifier("karte_rueckseite", "drawable", getPackageName()));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            kartenBilderAnzeigen();
        }
    }

    private void init() {

        Intent intent = getIntent();
        spielerName1 = intent.getStringExtra("spielerName1");
        spielerName2 = intent.getStringExtra("spielerName2");
        spielerName3 = intent.getStringExtra("spielerName3");
        spielerName4 = intent.getStringExtra("spielerName4");
        punktzahl = intent.getIntExtra("punkteZahl", 0);

        buttonKartenRuecken = findViewById(R.id.buttonKartenRuecken);
        buttonLetzteKarte = findViewById(R.id.buttonLetzteKarte);
        imageButtonKarte1 = findViewById(R.id.imageButtonKarte1);
        imageButtonKarte2 = findViewById(R.id.imageButtonKarte2);
        imageButtonKarte3 = findViewById(R.id.imageButtonKarte3);
        imageButtonKarte4 = findViewById(R.id.imageButtonKarte4);
        imageButtonKarte5 = findViewById(R.id.imageButtonKarte5);
        imageButtonKarte6= findViewById(R.id.imageButtonKarte6);
        imageButtonKarte7 = findViewById(R.id.imageButtonKarte7);
        imageButtonKarte8 = findViewById(R.id.imageButtonKarte8);
        imageButtonKarte9 = findViewById(R.id.imageButtonKarte9);
        tvSpielerName = findViewById(R.id.tvSpielerName);
        tvSpielerPunkte = findViewById(R.id.tvSpielerPunkte);
        buttonKarteZiehen = findViewById(R.id.buttonKarteZiehen);
        buttonTschau = findViewById(R.id.buttonTschau);
        buttonSepp = findViewById(R.id.buttonSepp);
        buttonKarteZiehen.setOnClickListener(new View.OnClickListener()  {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                /**
                 JLabel nachricht = new JLabel(spiel.getAktuellerSpieler().getName() + " hat eine Karte gezogen.");
                 nachricht.setFont(new Font("SansSerif", Font.BOLD, 25));
                 JOptionPane.showMessageDialog(null, nachricht);
                 */

                spiel.pruefeKarteGeben(spiel.getAktuellerSpieler());
                spielerInfosAnzeigen();
                kartenBilderAnzeigen();
            }
        });
        buttonTschau.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                if(spiel.getSpielerKarten1(spiel.getAktuellerSpieler()).size() == 2){
                    /**
                     JLabel nachricht = new JLabel(spiel.getAktuellerSpieler().getName() + " hat Tschau gesagt.");
                     nachricht.setFont(new Font("SansSerif", Font.BOLD, 25));
                     */
                    spiel.getAktuellerSpieler().setHatTschau(true);
                    spielerInfosAnzeigen();
                    kartenBilderAnzeigen();
                }else{
                    /**
                     JLabel nachricht = new JLabel(spiel.getAktuellerSpieler().getName() + " muss 2 Strafkarte aufnehmen, da er fälschlicherweisse Tschau gesagt hat.");
                     nachricht.setFont(new Font("SansSerif", Font.BOLD, 25));
                     JOptionPane.showMessageDialog(null, nachricht);
                     */
                    spiel.pruefeKarteGeben2(spiel.getAktuellerSpieler());
                    spiel.naechsterSpielerBestimmten();
                    spielerInfosAnzeigen();
                    kartenBilderAnzeigen();
                }
            }
        });
        buttonSepp.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                if(spiel.getSpielerKarten1(spiel.getAktuellerSpieler()).size() == 1){
                    /**
                     JLabel nachricht = new JLabel(spiel.getAktuellerSpieler().getName() + " hat Sepp gesagt.");
                     nachricht.setFont(new Font("SansSerif", Font.BOLD, 25));
                     JOptionPane.showMessageDialog(null, nachricht);
                     */
                    spiel.getAktuellerSpieler().setHatSepp(true);
                    spielerInfosAnzeigen();
                    kartenBilderAnzeigen();
                }else{
                    /**
                     JLabel nachricht = new JLabel(spiel.getAktuellerSpieler().getName() + " muss 4 Strafkarte aufnehmen, da er fälschlicherweisse Sepp gesagt hat.");
                     nachricht.setFont(new Font("SansSerif", Font.BOLD, 25));
                     JOptionPane.showMessageDialog(null, nachricht);
                     */
                    spiel.pruefeKarteGeben4(spiel.getAktuellerSpieler());
                    spiel.naechsterSpielerBestimmten();
                    spielerInfosAnzeigen();
                    kartenBilderAnzeigen();
                }
            }
        });

        imageButtonKarte1.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                if(kartenIDs.get(0) != null) {
                    String kartenid = kartenIDs.get(0);
                    try {
                        spiel.pruefeKarteLegen(spiel.getAktuellerSpieler(), spiel.getSpielerKarten1(spiel.getAktuellerSpieler()).get(0));
                    } catch (InvalideSymbolException | InvalideZahlException ex) {
                    }
                    //revalidate();
                    spielerInfosAnzeigen();
                    kartenBilderAnzeigen();
                }
            }
        });
        imageButtonKarte2.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                if(kartenIDs.get(1) != null) {
                    String kartenid = kartenIDs.get(1);
                    try {
                        spiel.pruefeKarteLegen(spiel.getAktuellerSpieler(), spiel.getSpielerKarten1(spiel.getAktuellerSpieler()).get(1));
                    } catch (InvalideSymbolException | InvalideZahlException ex) {
                    }
                    //revalidate();
                    spielerInfosAnzeigen();
                    kartenBilderAnzeigen();
                }
            }
        });
        imageButtonKarte3.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                if(kartenIDs.get(2) != null) {
                    String kartenid = kartenIDs.get(2);
                    try {
                        spiel.pruefeKarteLegen(spiel.getAktuellerSpieler(), spiel.getSpielerKarten1(spiel.getAktuellerSpieler()).get(2));
                    } catch (InvalideSymbolException | InvalideZahlException ex) {
                    }
                    //revalidate();
                    spielerInfosAnzeigen();
                    kartenBilderAnzeigen();
                }
            }
        });
        imageButtonKarte4.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                if(kartenIDs.get(3) != null) {
                    String kartenid = kartenIDs.get(3);
                    try {
                        spiel.pruefeKarteLegen(spiel.getAktuellerSpieler(), spiel.getSpielerKarten1(spiel.getAktuellerSpieler()).get(3));
                    } catch (InvalideSymbolException | InvalideZahlException ex) {
                    }
                    //revalidate();
                    spielerInfosAnzeigen();
                    kartenBilderAnzeigen();
                }
            }
        });
        imageButtonKarte5.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                if(kartenIDs.get(4) != null) {
                    String kartenid = kartenIDs.get(4);
                    try {
                        spiel.pruefeKarteLegen(spiel.getAktuellerSpieler(), spiel.getSpielerKarten1(spiel.getAktuellerSpieler()).get(4));
                    } catch (InvalideSymbolException | InvalideZahlException ex) {
                    }
                    //revalidate();
                    spielerInfosAnzeigen();
                    kartenBilderAnzeigen();
                }
            }
        });
        imageButtonKarte6.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                if(kartenIDs.get(5) != null) {
                    String kartenid = kartenIDs.get(5);
                    try {
                        spiel.pruefeKarteLegen(spiel.getAktuellerSpieler(), spiel.getSpielerKarten1(spiel.getAktuellerSpieler()).get(5));
                    } catch (InvalideSymbolException | InvalideZahlException ex) {
                    }
                    //revalidate();
                    spielerInfosAnzeigen();
                    kartenBilderAnzeigen();
                }
            }
        });
        imageButtonKarte7.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                if(kartenIDs.get(6) != null) {
                    String kartenid = kartenIDs.get(6);
                    try {
                        spiel.pruefeKarteLegen(spiel.getAktuellerSpieler(), spiel.getSpielerKarten1(spiel.getAktuellerSpieler()).get(6));
                    } catch (InvalideSymbolException | InvalideZahlException ex) {
                    }
                    //revalidate();
                    spielerInfosAnzeigen();
                    kartenBilderAnzeigen();
                }
            }
        });
        imageButtonKarte8.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                if(kartenIDs.get(7) != null) {
                    String kartenid = kartenIDs.get(7);
                    try {
                        spiel.pruefeKarteLegen(spiel.getAktuellerSpieler(), spiel.getSpielerKarten1(spiel.getAktuellerSpieler()).get(7));
                    } catch (InvalideSymbolException | InvalideZahlException ex) {
                    }
                    //revalidate();
                    spielerInfosAnzeigen();
                    kartenBilderAnzeigen();
                }
            }
        });
        imageButtonKarte9.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                if(kartenIDs.get(8) != null) {
                    String kartenid = kartenIDs.get(8);
                    try {
                        spiel.pruefeKarteLegen(spiel.getAktuellerSpieler(), spiel.getSpielerKarten1(spiel.getAktuellerSpieler()).get(8));
                    } catch (InvalideSymbolException | InvalideZahlException ex) {
                    }
                    //revalidate();
                    //recreate();
                    spielerInfosAnzeigen();
                    kartenBilderAnzeigen();
                }
            }
        });
    }

    public void kartenBilderHinzufuegen(){
        kartenButtons.add(imageButtonKarte1);
        kartenButtons.add(imageButtonKarte2);
        kartenButtons.add(imageButtonKarte3);
        kartenButtons.add(imageButtonKarte4);
        kartenButtons.add(imageButtonKarte5);
        kartenButtons.add(imageButtonKarte6);
        kartenButtons.add(imageButtonKarte7);
        kartenButtons.add(imageButtonKarte8);
        kartenButtons.add(imageButtonKarte9);
    }
    public void spielerInfosAnzeigen(){
        Spieler aktuellerSpieler = spiel.getAktuellerSpieler();
        String name = aktuellerSpieler.getName();
        tvSpielerName.setText(name);
        int punkte = aktuellerSpieler.getPunktzahl();
        tvSpielerPunkte.setText("Punkte: " + String.valueOf(punkte));
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void kartenBilderAnzeigen(){
        kartenIDs.clear();
        String listString = spiel.getSpielerKarten1(spiel.getAktuellerSpieler()).stream().map(Object::toString).collect(Collectors.joining(","));
        String[] temp = listString.split(",");
        temp1 = new ArrayList<>(Arrays.asList(temp));
        kartenIDs.addAll(temp1);
        temp1.clear();
        //buttonLetzteKarte.setIcon(new javax.swing.ImageIcon(getClass().getResource("images/" + spiel.getObersteKarte() + ".gif")));
        buttonLetzteKarte.setImageResource(getResources().getIdentifier(spiel.getObersteKarte().toString() , "drawable", getPackageName()));
        for(int i = 0; i < kartenIDs.size();i++){
            kartenButtons.get(i).setEnabled(true);
            //kartenButtons.get(i).setIcon(new javax.swing.ImageIcon(getClass().getResource("images/" + kartenIDs.get(i) + ".gif")));
            kartenButtons.get(i).setImageResource(getResources().getIdentifier(kartenIDs.get(i) , "drawable", getPackageName()));
        }
        //deaktiviert alle nicht genutzten Karten
        for(int i = kartenIDs.size(); i < kartenButtons.size();i++){
            kartenButtons.get(i).setImageDrawable(null);
            kartenButtons.get(i).setEnabled(false);
        }

    }
    public void erstelleToast(String inhalt){
        Toast.makeText(getApplicationContext(), inhalt, Toast.LENGTH_LONG).show();
    }

}