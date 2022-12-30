import groupone.kartenstapel_management.implementation.KartenSpielImpl;
import groupone.spiel_management.implementation.KartenSpielerImpl;
import groupone.spiel_management.implementation.SpielImpl;
import groupone.spieler_management.implementation.SpielerImpl;
import groupone.spielregeln_management.implementation.SonderregelnImpl;
import groupone.spielregeln_management.implementation.SpielregelnImpl;
import groupone.ui_management.implementation.ControllerImpl;
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
        container.addComponent(SonderregelnImpl.class);
        container.addComponent(SpielregelnImpl.class);
        container.addComponent(ControllerImpl.class);
    }

    public static void main(String[] args) {
        registriereKomponenten();
        ControllerImpl ui = container.getComponent(ControllerImpl.class);
        // Aufruf von ui zum Start des Spiels
    }
}
