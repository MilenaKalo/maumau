package groupone.kartenstapel_management.Exceptions;

public class KartenstapelNichtGefundenException extends RuntimeException {
    public KartenstapelNichtGefundenException(String nachricht) {
        super(nachricht);
    }
}
