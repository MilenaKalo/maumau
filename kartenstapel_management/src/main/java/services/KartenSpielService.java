package services;

public interface KartenSpielService {

    /**
     * Oberste Karte des Ziehstapels wird geöffnet
     * (Methode der KLasse Spiel)
     * @param spiel Spiel wo die obersteKarte aufgedeckt wird
     */
    //void legeErsteKarteAufAblagetapel(Spiel spiel);

    /**
     * Karten werden gemischt
     * @param karteList Liste aus Karten die gemischt werden sollen
     * @return die fertig gemischte Kartenliste
     */
    //  List<Karte> mischeKarten(List<Karte> karteList );

    /**
     * das Spiel wird erzeugt
     * bereitet das spiel vor und mischt die karten und verteilt
     * @param ablageStapel
     * @param ziehStapel
     * @param spieler
     */
    //  void spielErzeugt(AblageStapel ablageStapel, ZiehStapel ziehStapel, Spieler spieler);

    /**
     * Eine einzelne Karte wird mit dieser Methode erzeugt
     * @param kartenFarben -> Die Farben der Karten
     * @param kartenWerte -> Der Werte der Karten
     * @return karte -> Die erzeugte Karte
     */
    //  Karte karteErzeugt(List<String> kartenFarben, List<String> kartenWerte);
}
