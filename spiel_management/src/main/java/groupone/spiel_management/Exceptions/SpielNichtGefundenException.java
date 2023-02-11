package groupone.spiel_management.Exceptions;

public class SpielNichtGefundenException extends RuntimeException {
    public SpielNichtGefundenException(String nachricht) {
        super(nachricht);
    }
}
