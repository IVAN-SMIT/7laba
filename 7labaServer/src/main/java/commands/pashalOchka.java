package commands;

import auxiliary.Command;

import java.awt.Desktop;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * [ДАННЫЕ-УДАЛЕНЫ]
 */

public class pashalOchka implements Command {
    public static String run(String argument) throws URISyntaxException {

    URI uri = new URI("https://docs.google.com/spreadsheets/d/1cEvt5QcRtsLlS_taVT24gelxKZ3DYMgv1cnMwA3ta90/edit#gid=4531647&range=L28");
    Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
        if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
        try {
            desktop.browse(uri);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
        return argument;
    }
}
