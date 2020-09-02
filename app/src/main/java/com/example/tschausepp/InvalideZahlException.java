package com.example.tschausepp;
/**
 * Nicolas Feige
 *
 * @author Nicolas Feige
 * @version 1.0
 * @date 15.06.2020
 */
public class InvalideZahlException extends Exception {

    /**
     * Initialisiert eine neue InvalidZahlException
     *
     * @param nachricht  die Nachricht, die in der Konsole ausgegeben wird
     */
    public InvalideZahlException(String nachricht) {
        super(nachricht);
    }
}
