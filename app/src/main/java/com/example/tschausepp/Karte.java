package com.example.tschausepp;

/**
 * Nicolas Feige
 *
 * @author Nicolas Feige
 * @version 1.0
 * @date 15.06.2020
 */


public class Karte {

    /**
     * Das Enum zahl. Mit dem Enum werdne alle Karten erstellt.
     */
    enum Zahl {

        sechs, sieben, acht, neun, zehn, under, ober, koenig, ass;

        private static final Zahl[] zahlen = Zahl.values();

        /**
         * Die Zahl kriegen
         *
         * @param i welche Zahl
         * @return die zahl
         */
        public static Zahl getZahl(int i) {
            return Zahl.zahlen[i];
        }
    }

    /**
     * Das Enum Symbol. Mit dem Enum werdne alle Karten erstellt.
     */
    enum Symbol {

        schellen, rosen, schilten, eichel;

        private static final Symbol[] symbole = Symbol.values();

        /**
         * Das Symbol kriegen.
         *
         * @param i welches Symbol
         * @return das symbol
         */
        public static Symbol getSymbol(int i) {
            return Symbol.symbole[i];
        }
    }

    private final Zahl zahl;
    private final Symbol symbol;

    /**
     * Initialisiert eine neue Karte.
     *
     * @param symbol das symbol
     * @param zahl   die zahl
     */
    public Karte(Symbol symbol, Zahl zahl) {
        this.zahl = zahl;
        this.symbol = symbol;
    }

    /**
     * Hollt die zahl.
     *
     * @return the zahl
     */
    public Zahl getZahl() {
        return zahl;
    }

    /**
     * Hollt das symbol.
     *
     * @return the symbol
     */
    public Symbol getSymbol() {
        return symbol;
    }

    /**
     * Gibt die Karte als String aus
     *
     */

    @Override
    public String toString() {
        return symbol + "_" + zahl;
    }
}

