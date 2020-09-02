package com.example.tschausepp;

public class Spieler {
    private String name;
    private int punktzahl;
    private boolean hatTschau;
    private boolean hatSepp;

    /**
     * Initalisiert einen neuen Spieler und stellt die Punktzahl auf 0.
     *
     * @param name the name
     */
    public Spieler(String name) {
        this.name = name;
        punktzahl = 0;
        hatTschau = false;
        hatSepp = false;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets punktzahl.
     *
     * @return the punktzahl
     */
    public int getPunktzahl() {
        return punktzahl;
    }

    /**
     * Gets hat tschau.
     *
     * @return the hat tschau
     */
    public boolean getHatTschau() {
        return hatTschau;
    }

    /**
     * Gets hatSepp.
     *
     * @return the hatSepp
     */
    public boolean getHatSepp() {
        return hatSepp;
    }

    /**
     * Sets hatTschau.
     *
     * @param hatTschau the hatTschau
     */
    public void setHatTschau(boolean hatTschau) {
        this.hatTschau = hatTschau;
    }

    /**
     * Sets hat sepp.
     *
     * @param hatSepp the hatSepp
     */
    public void setHatSepp(boolean hatSepp) {
        this.hatSepp = hatSepp;
    }

    /**
     * Zählt alle gewonnenen Punkt zu den bestehenden hinzu.
     *
     * @param punkte the punkte
     */
    public void zaehlePunkteHinzu(int punkte){
        punktzahl = punktzahl + punkte;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}

