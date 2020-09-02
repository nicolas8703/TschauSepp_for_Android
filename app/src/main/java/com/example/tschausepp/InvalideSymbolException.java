package com.example.tschausepp;

/**
 * Nicolas Feige
 *
 * @author Nicolas Feige
 * @version 1.0
 * @date 15.06.2020
 */
public class InvalideSymbolException extends Exception {

    /**
     * Initialisiert eine neue InvalidSymbolException.
     *
     * @param message      die Nachricht, die in der Konsole ausgegeben wird
     */
    public InvalideSymbolException(String message) {
        super(message);
    }
}
