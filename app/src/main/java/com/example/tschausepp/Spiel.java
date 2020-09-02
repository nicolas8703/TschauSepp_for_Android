package com.example.tschausepp;


import java.util.Vector;

/**
 * Nicolas Feige
 *
 * @author Nicolas Feige
 * @version 1.0
 * @date 15.06.2020
 */


public class Spiel {
    private int aktuellerSpieler;
    private Vector<Spieler> spielerID;
    private Kartenstapel kartenstapel;
    private Vector<Vector<Karte>> spielerKarten1;
    private Vector<Karte> ablagestapel;
    private Karte.Symbol validesSymbol;
    private Karte.Zahl valideZahl;
    private boolean spielRichtung;
    private Vector<Karte> spielerHand1;
    private int maximalePunkte;
    MultiplayerActivity multiplayerActivity;

    /**
     * Initialisiert ein neues Spiel.
     *
     * Erstellt ein neuer Kartenstappel und mischt ihn
     * Erstellt den Vector ablagestapel, spieleriD spielerHand1 und spielerKarten1
     * Setzt die spielRichtung auf normal und stellt den ersten Spiler ein
     *  @param spieler1 the spieler 1
     * @param spieler2 the spieler 2
     * @param spieler3 the spieler 3
     * @param spieler4 the spieler 4
     * @param multiplayerActivity die multiplayerActiviy um einen Toast zu senden
     */
    public Spiel(Spieler spieler1, Spieler spieler2, Spieler spieler3, Spieler spieler4, int maximalePunkte, MultiplayerActivity multiplayerActivity) {
        this.multiplayerActivity = multiplayerActivity;
        this.maximalePunkte = maximalePunkte;
        kartenstapel = new Kartenstapel();
        kartenstapel.mischen();
        ablagestapel = new Vector<>();
        spielerID = new Vector<>();
        spielerKarten1 = new Vector<>();
        spielerHand1 = new Vector<>();
        spielerID.add(spieler1);
        spielerID.add(spieler2);
        spielerID.add(spieler3);
        spielerID.add(spieler4);
        aktuellerSpieler = 0;
        spielRichtung = false;
    }


    /**
     * Startet die Runde.
     * Gibt jedem Spieler 7 Karten
     * Findet heraus welches Symbol und welche Zahl gelegt werden Kann
     * Legt die erste Karte in die mitte
     * Prüft ob eine acht oder zehn die Erste Karte ist und führt, falls ja ihre spezial funktion aus
     *
     * @param spiel the spiel
     */
    public void startRunde(Spiel spiel) {
        for (int i = 0; i < spielerID.size(); i++) {
            Vector<Karte> spielerHand1 = new Vector<>();
            spielerHand1.add(kartenstapel.gebeKarte());
            spielerHand1.add(kartenstapel.gebeKarte());
            spielerHand1.add(kartenstapel.gebeKarte());
            spielerHand1.add(kartenstapel.gebeKarte());
            spielerHand1.add(kartenstapel.gebeKarte());
            spielerHand1.add(kartenstapel.gebeKarte());
            spielerHand1.add(kartenstapel.gebeKarte());
            spielerKarten1.add(spielerHand1);
        }
        Karte karte = kartenstapel.gebeKarte();
        validesSymbol = karte.getSymbol();
        valideZahl = karte.getZahl();

        if (karte.getZahl() == Karte.Zahl.acht) {
            /**
            JLabel nachricht = new JLabel(getAktuellerSpieler().getName() + " wurde ausgelassen, da eine Acht am Anfang der Runde war.");
            nachricht.setFont(new Font("SansSerif", Font.BOLD, 25));
            JOptionPane.showMessageDialog(null, nachricht);
             */
            naechsterSpielerBestimmten();
        }
        if (karte.getZahl() == Karte.Zahl.zehn) {
            /**
            JLabel nachricht = new JLabel("Die Spielrichtung wurde geändert, da eine Zehn am Anfang der Runde war.");
            nachricht.setFont(new Font("SansSerif", Font.BOLD, 25));
            JOptionPane.showMessageDialog(null, nachricht);
             */
            spielRichtung ^= true;
            aktuellerSpieler = spielerID.size() - 1;
        }
        ablagestapel.add(karte);
    }

    /**
     * Die Runde wird resetet
     * Alle Karten, Stapel und Hände werden gelöscht.
     * Eine neue Runde wird gestartet
     */
    public void rundeReseten() {
        kartenstapel.getKarten().removeAllElements();
        ablagestapel.removeAllElements();
        kartenstapel.kartenErstellen();
        kartenstapel.mischen();
        spielerHand1.removeAllElements();
        spielerKarten1.removeAllElements();
        spielRichtung = false;
        aktuellerSpieler = 0;
        startRunde(this);
    }

    /**
     * Der nächste Spieler wird mit dem Modulo verfahren bestummen
     */
    public void naechsterSpielerBestimmten() {
        if (!spielRichtung) {
            aktuellerSpieler = (aktuellerSpieler + 1) % this.spielerID.size();
        } else if (spielRichtung) {
            aktuellerSpieler = (aktuellerSpieler - 1) % this.spielerID.size();
            if (aktuellerSpieler == -1) {
                aktuellerSpieler = this.spielerID.size() - 1;
            }
        }
    }

    /**
     * Gets oberste karte.
     *
     * @return the oberste karte
     */
    public Karte getObersteKarte() {
        return new Karte(validesSymbol, valideZahl);
    }

    /**
     * Gets aktueller spieler.
     *
     * @return the aktueller spieler
     */
    public Spieler getAktuellerSpieler() {
        return spielerID.get(aktuellerSpieler);
    }

    /**
     * Gets spieler karten 1.
     *
     * @param spielerID the spieler id
     * @return the spieler karten 1
     */
    public Vector<Karte> getSpielerKarten1(Spieler spielerID) {
        int index = this.spielerID.indexOf(spielerID);
        return spielerKarten1.get(index);
    }

    /**
     * Gets anzahl spieler karten.
     *
     * @param spielerID the spieler id
     * @return the anzahl spieler karten
     */
    public int getAnzahlSpielerKarten(Spieler spielerID) {
        return getSpielerKarten1(spielerID).size();
    }

    /**
     * Gets spieler karte.
     *
     * @param spielerID the spieler id
     * @param i         the
     * @return the spieler karte
     */
    public Karte getSpielerKarte(Spieler spielerID, int i) {
        Vector<Karte> hand = getSpielerKarten1(spielerID);
        return hand.get(i);
    }

    /**
     * Hat keine karten boolean.
     *
     * @param spielerID the spieler id
     * @return the boolean
     */
    public boolean hatKeineKarten(Spieler spielerID) {
        return getSpielerKarten1(spielerID).isEmpty();
    }

    /**
     * Ist zug valide boolean.
     *
     * @param karte the karte
     * @return the boolean
     */
    public boolean istZugValide(Karte karte) {
        return karte.getZahl() == valideZahl || karte.getSymbol() == validesSymbol;
    }

    /**
     * Prüft ob ein Spieler Tschau oder Sepp hatte und setzt dieses zurück
     * Danach wird geprüft ob eine Karte dem Spieler gegeben werden kann ( ob kartenstapel noch karten hat / ob der Spieler noch genaug platz hat)
     * Falls ja darf der Kartenstapel dem Spieler eine karte geben
     *
     * @param spielerID the spieler id
     */
    public void pruefeKarteGeben(Spieler spielerID) {
        if (getAktuellerSpieler().getHatTschau()) {
            getAktuellerSpieler().setHatTschau(false);
        }
        if (getAktuellerSpieler().getHatSepp()) {
            getAktuellerSpieler().setHatSepp(false);
        }

        if (kartenstapel.istLeer()) {
            kartenstapel.neuerKartenstapel(ablagestapel);
            kartenstapel.mischen();
            kartenstapel.setObersteKarte(kartenstapel.getAnzahlKarten() - 1);
        }
        if(getAnzahlSpielerKarten(spielerID) < 9){
            getSpielerKarten1(spielerID).add(kartenstapel.gebeKarte());
        }
        naechsterSpielerBestimmten();
    }

    /**
     * Es wird geprüft ob eine Karte dem Spieler gegeben werden kann ( ob kartenstapel noch karten hat / ob der Spieler noch genaug platz hat)
     * Falls ja darf der Kartenstapel dem Spieler zwei karte geben
     * Es werden dem Spieler aber nur maximal 18 karten gegeben
     *
     * @param spielerID the spieler id
     */
    public void pruefeKarteGeben2(Spieler spielerID) {
        if (kartenstapel.getObersteKarte() < 1) {
            kartenstapel.neuerKartenstapel(ablagestapel);
            kartenstapel.mischen();
            kartenstapel.setObersteKarte(kartenstapel.getAnzahlKarten() - 1);
        }
        //kontrollier das der Spieler maxmimal 18 Karten bekommt
        if(getAnzahlSpielerKarten(spielerID) >= 8){
            if(getAnzahlSpielerKarten(spielerID) < 9){
                getSpielerKarten1(spielerID).add(kartenstapel.gebeKarte());
            }
        }else{
            getSpielerKarten1(spielerID).add(kartenstapel.gebeKarte());
            getSpielerKarten1(spielerID).add(kartenstapel.gebeKarte());
        }
    }

    /**
     * Es wird geprüft ob eine Karte dem Spieler gegeben werden kann ( ob kartenstapel noch karten hat / ob der Spieler noch genaug platz hat)
     * Falls ja darf der Kartenstapel dem Spieler vier karte geben
     * Es werden dem Spieler aber nur maximal 18 karten gegeben
     *
     * @param spielerID the spieler id
     */
    public void pruefeKarteGeben4(Spieler spielerID) {
        if (kartenstapel.getObersteKarte() < 3) {
            kartenstapel.neuerKartenstapel(ablagestapel);
            kartenstapel.mischen();
            kartenstapel.setObersteKarte(kartenstapel.getAnzahlKarten() - 1);
        }
        //kontrollier das der Spieler maxmimal 18 Karten bekommt
        if(getAnzahlSpielerKarten(spielerID) >=6){
            if(getAnzahlSpielerKarten(spielerID) < 7){
                getSpielerKarten1(spielerID).add(kartenstapel.gebeKarte());
                getSpielerKarten1(spielerID).add(kartenstapel.gebeKarte());
                getSpielerKarten1(spielerID).add(kartenstapel.gebeKarte());
            }else if(getAnzahlSpielerKarten(spielerID) < 8){
                getSpielerKarten1(spielerID).add(kartenstapel.gebeKarte());
                getSpielerKarten1(spielerID).add(kartenstapel.gebeKarte());
            }else if(getAnzahlSpielerKarten(spielerID) < 9){
                getSpielerKarten1(spielerID).add(kartenstapel.gebeKarte());
            }
        }else{
            getSpielerKarten1(spielerID).add(kartenstapel.gebeKarte());
            getSpielerKarten1(spielerID).add(kartenstapel.gebeKarte());
            getSpielerKarten1(spielerID).add(kartenstapel.gebeKarte());
            getSpielerKarten1(spielerID).add(kartenstapel.gebeKarte());
        }
    }


    /**
     * Hier wird geprüft ob der Spieler die Karte legen kann. Danach wird ihm die Karte weggenommen und auf den Ablagestapel gelegt.
     * Zusätzlich wird hier geprüft, ob der Spieler noch zwei/eine Karte hat und Tschau/Sepp hätte sagen müssen.
     * Danach wird geprüft, ob der spieler noch eine Karte hat, oder ob er das Spiel bereits gewonnen hat.
     * Als letztes wird geprüft ob es sich um eine Sonderkarte Handelt (7,8,10 oder Ass) und dann die dementsprechende Aktion ausgeführt
     *
     * Falls ein Spieler versucht eine falsche Karte zu Spielern werfe ich eine Exeption, damit die Methode abgebrochen wird.
     * Ohne die Exeption könnte man trotzdem die Karte legen.
     *
     * @param spielerID the spieler id
     * @param karte     the karte
     * @throws InvalideSymbolException the invalide symbol eingabe exception
     * @throws InvalideZahlException   the invalide zahl eingabe exception
     */
    public void pruefeKarteLegen(Spieler spielerID, Karte karte) throws InvalideSymbolException, InvalideZahlException {
        Vector<Karte> spielerHand = getSpielerKarten1(spielerID);
        if (getSpielerKarten1(getAktuellerSpieler()).size() == 2 && getAktuellerSpieler().getHatTschau() == false) {
            multiplayerActivity.erstelleToast(getAktuellerSpieler().getName() + " hat vergessen Tschau zu sagen und hat zwei Strafkarten erhalten.");
            pruefeKarteGeben2(getAktuellerSpieler());
        } else if (getSpielerKarten1(getAktuellerSpieler()).size() == 1 && getAktuellerSpieler().getHatSepp() == false) {
            multiplayerActivity.erstelleToast(getAktuellerSpieler().getName() + " hat vergessen Sepp zu sagen und hat vier Strafkarten erhalten.");
            pruefeKarteGeben4(getAktuellerSpieler());
        }
        if (!istZugValide(karte)) {
            if (karte.getSymbol() != validesSymbol) {
                multiplayerActivity.erstelleToast("Die Karte kann nicht gespielt werden!");
                throw new InvalideSymbolException("Karte kann nicht gespielt werden");
            } else if (karte.getZahl() != valideZahl) {
                multiplayerActivity.erstelleToast("Die Karte kann nicht gespielt werden!");
                throw new InvalideZahlException("Karte kann nicht gespielt werden");
            }
        }
        spielerHand.remove(karte);

        if (hatKeineKarten(this.spielerID.get(aktuellerSpieler))) {
            multiplayerActivity.erstelleToast(getAktuellerSpieler().getName() + " hat die Runde gewonnen!");
            punkteZaehlen(this.spielerID.get(aktuellerSpieler));
        }
        valideZahl = karte.getZahl();
        validesSymbol = karte.getSymbol();
        ablagestapel.add(karte);
        naechsterSpielerBestimmten();

        if (karte.getZahl() == Karte.Zahl.acht) {
            multiplayerActivity.erstelleToast("Der nächste Spieler wurde übersprungen!");
            naechsterSpielerBestimmten();
        }
        if (karte.getZahl() == Karte.Zahl.zehn) {
            multiplayerActivity.erstelleToast("Die Richtung wurde geändert!");
            spielRichtung ^= true;
            if (!spielRichtung) {
                aktuellerSpieler = (aktuellerSpieler + 2) % this.spielerID.size();
            } else if (spielRichtung) {
                aktuellerSpieler = (aktuellerSpieler - 2) % this.spielerID.size();
                if (aktuellerSpieler == -1) {
                    aktuellerSpieler = this.spielerID.size() - 1;
                }
                if (aktuellerSpieler == -2) {
                    aktuellerSpieler = this.spielerID.size() - 2;
                }
            }
        }
        if (karte.getZahl() == Karte.Zahl.sieben) {
            multiplayerActivity.erstelleToast(getAktuellerSpieler().getName() + " muss 2 Karten aufnehmen");
            pruefeKarteGeben2(getAktuellerSpieler());
        }
        if (karte.getZahl() == Karte.Zahl.ass) {
            if (spielRichtung == false) {
                aktuellerSpieler = (aktuellerSpieler - 1) % this.spielerID.size();
                if (aktuellerSpieler == -1) {
                    aktuellerSpieler = this.spielerID.size() - 1;
                }
            } else if (spielRichtung == true) {
                aktuellerSpieler = (aktuellerSpieler + 1) % this.spielerID.size();
            }
            multiplayerActivity.erstelleToast(getAktuellerSpieler().getName() + " hat ein Ass gelegt und muss nochmal eine Karte spielen");
        }
    }
    /**
     * Gets maximale Punkt.
     *
     * @return the maximale Punkt
     */
    public int getMaximalePunkte() {
        return maximalePunkte;
    }

    /**
     * Hier werden nach der Runde die Punkte zusammen gezählt und dem Gewinner gegeben. Danach wird geprüft, ob die Maximale Punktezahl
     * schon überschritten ist, oder ob noch eine Runde gespielt werden muss
     *
     * @param spieler the spieler
     */
    public void punkteZaehlen(Spieler spieler) {
        int punkte = 0;
        for (int m = 0; m < 3; m++) {
            int l = getSpielerKarten1(spielerID.get(m)).size();
            if (l != 0) {
                for (int i = 0; i < l; i++) {
                    if (getSpielerKarte(spielerID.get(m), i).getZahl().equals(Karte.Zahl.sechs)) {
                        punkte = punkte + 6;
                    } else if (getSpielerKarte(spielerID.get(m), i).getZahl().equals(Karte.Zahl.sieben)) {
                        punkte = punkte + 7;
                    } else if (getSpielerKarte(spielerID.get(m), i).getZahl().equals(Karte.Zahl.acht)) {
                        punkte = punkte + 8;
                    } else if (getSpielerKarte(spielerID.get(m), i).getZahl().equals(Karte.Zahl.neun)) {
                        punkte = punkte + 9;
                    } else if (getSpielerKarte(spielerID.get(m), i).getZahl().equals(Karte.Zahl.zehn)) {
                        punkte = punkte + 10;
                    } else if (getSpielerKarte(spielerID.get(m), i).getZahl().equals(Karte.Zahl.under)) {
                        punkte = punkte + 20;
                    } else if (getSpielerKarte(spielerID.get(m), i).getZahl().equals(Karte.Zahl.ober)) {
                        punkte = punkte + 3;
                    } else if (getSpielerKarte(spielerID.get(m), i).getZahl().equals(Karte.Zahl.koenig)) {
                        punkte = punkte + 4;
                    } else if (getSpielerKarte(spielerID.get(m), i).getZahl().equals(Karte.Zahl.ass)) {
                        punkte = punkte + 11;
                    }
                }
            }
        }
        spieler.zaehlePunkteHinzu(punkte);
        if (spieler.getPunktzahl() < maximalePunkte) {
            multiplayerActivity.erstelleToast("Eine neue Runde wird gestartet");
            rundeReseten();
        } else {
            multiplayerActivity.erstelleToast(spieler.getName() + " hat das Spiel gewonnen!");
            System.exit(0);
        }
    }
}

