package groupone.configuration_managment;

import groupone.exception_management.TechnischeException;
import groupone.kartenstapel_management.implementation.KartenSpielImpl;
import groupone.spiel_management.implementation.KartenSpielerImpl;
import groupone.spiel_management.implementation.SpielImpl;
import groupone.spieler_management.classes.VirtuellerSpieler;
import groupone.spieler_management.implementation.SpielerImpl;
import groupone.spieler_management.implementation.VirtuellerSpielerImpl;
import groupone.spielregeln_management.implementation.SonderregelnImpl;
import groupone.spielregeln_management.implementation.SpielregelnImpl;
import groupone.ui_management.implementation.ControllerImpl;
import groupone.ui_management.implementation.View;
import org.picocontainer.DefaultPicoContainer;
import org.picocontainer.MutablePicoContainer;
import org.picocontainer.injectors.ConstructorInjection;

public class Config {

    private static MutablePicoContainer container = new DefaultPicoContainer(new ConstructorInjection());

    private static void registriereKomponenten() {
        container.addComponent(KartenSpielImpl.class);
        container.addComponent(KartenSpielerImpl.class);
        container.addComponent(SpielImpl.class);
        container.addComponent(SpielerImpl.class);
        container.addComponent(VirtuellerSpielerImpl.class);
        container.addComponent(SonderregelnImpl.class);
        container.addComponent(SpielregelnImpl.class);
        container.addComponent(ControllerImpl.class);
        container.addComponent(View.class);
    }

    public static void main(String[] args) throws TechnischeException {
        try {
            registriereKomponenten();
        } catch (Exception e) {
            throw new TechnischeException("Registrierung der Komponenten fehlgeschlagen");
        }
        try {
            container.getComponent(ControllerImpl.class).starteSpiel();
        } catch (java.lang.NullPointerException e) {
           throw new TechnischeException("Fehler beim Starten des Spiels");
        } catch (Exception e){
            throw new TechnischeException();
        }
    }
}
