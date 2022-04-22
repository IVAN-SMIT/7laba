package commands;

import auxiliary.Command;
import auxiliary.Messager;

/**
 * Завершает работу программы
 */

public class ExitCommand implements Command {
    public void run(String argument) throws Exception {
        if (argument != null) {
            throw new IllegalArgumentException("Exit не имеет аргументов!");
        }
        new Messager().println("===========by IVAN SMIT===========",true);
        System.exit(0);
    }
}
