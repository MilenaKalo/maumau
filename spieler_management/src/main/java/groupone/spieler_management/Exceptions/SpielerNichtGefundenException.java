package groupone.spieler_management.Exceptions;

public class SpielerNichtGefundenException extends RuntimeException {
    public SpielerNichtGefundenException(String nachricht) {
        super(nachricht);
    }
}
