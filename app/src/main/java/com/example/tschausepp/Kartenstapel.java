package com.example.tschausepp;

import java.util.Collections;
import java.util.Vector;

/**
 * Nicolas Feige
 *
 * @author Nicolas Feige
 * @version 1.0
 * @date 15.06.2020
 */


public class Kartenstapel {
    private Vector<Karte> karten;
    private int anzahlKarten;
    private int obersteKarte;

    /**
     * Initialisiert einen neuen Kartenstapel.
     */
    public Kartenstapel() {
        karten = new Vector<Karte>();
        kartenErstellen();
    }
    /**
     * Erstellt alle Karten für das Karten Spiel ( 2 Kartensetzt) mithilfe der Enums
     */
    public void kartenErstellen() {
        Karte.Symbol[] symbole = Karte.Symbol.values();
        anzahlKarten = 0;
        for(int m = 0;m < 2; m++) {
            for (int i = 0; i < symbole.length; i++) {
                Karte.Symbol symbol = symbole[i];

                for (int j = 0; j < 9; j++) {
                    Karte karte1 = new Karte(symbol, Karte.Zahl.getZahl(j));
                    karten.add(karte1);
                    anzahlKarten++;
                }
            }
        }
        obersteKarte = karten.size() - 1;
    }

    /**
     * Mischt die Karten
     */
    public void mischen(){
        Collections.shuffle(karten);
    }

    /**
     * Gibt einem Spieler eine Karte
     *
     * @return die karte
     */
    public Karte gebeKarte(){
        Karte ausgewaehlteKarte = karten.get(obersteKarte);
        karten.remove(obersteKarte--);
        anzahlKarten--;
        return ausgewaehlteKarte;
    }

    /**
     * Erstellt einen neuen Kartenstappel indem er den Ablagestapel mitgegeben bekommt
     *
     * @param karten der Ablagestapel
     */
    public void neuerKartenstapel(Vector<Karte> karten){
        this.karten = karten;
        anzahlKarten = karten.size();
    }

    /**
     * Ist leer boolean.
     *
     * @return the boolean
     */
    public boolean istLeer(){
        return obersteKarte < 0;
    }

    /**
     * Gets karten.
     *
     * @return the karten
     */
    public Vector<Karte> getKarten() {
        return karten;
    }

    /**
     * Gets anzahl karten.
     *
     * @return the anzahl karten
     */
    public int getAnzahlKarten() {
        return anzahlKarten;
    }

    /**
     * Gets oberste karte.
     *
     * @return the oberste karte
     */
    public int getObersteKarte() {
        return obersteKarte;
    }

    /**
     * Sets oberste karte.
     *
     * @param obersteKarte the oberste karte
     */
    public void setObersteKarte(int obersteKarte) {
        this.obersteKarte = obersteKarte;
    }
}


