1. Dependency prüfen das keine Zirkel vorhanden sind 
2. Spielregeln Klasse wurde erstellt, welche Attribute braucht sie 
3. KartenSpielService:
   void legeErsteKarteAufAblagetapel(Spiel spiel);
        vielleicht hier die 2 Stapel als Parameter statt Spiel nehmen ?
   void spielErzeugt(AblageStapel ablageStapel, ZiehStapel ziehStapel, Spieler spieler);
        notwenig ? da wir einen Konstruktor in der Spiel Klasse haben ..
4. KartenSpielerService
   void legeKarteAb(Spiel spiel, Spieler spieler, Karte karte);
        hier haben wir einen Zirkel, in beiden poms werden die dependencys verwendet
   void zieheKarte(Spiel spiel);
    selbes Problem auch hier einen Zirkel in den Dependencys wenn die dependency importiert wird 
5. ÄNDERUNGEN FÜR DAS KOMPONENTENDIAGRAMM DANN MIR MITTEILEN :) ÄNDER ES DANN AB 
6. vielleicht gibGewinneraus Methode in Spieler zu spielklasse ? 
