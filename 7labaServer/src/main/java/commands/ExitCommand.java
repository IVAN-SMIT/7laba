package commands;

import auxiliary.Command;

/**
 * Завершает работу программы
 */

public class ExitCommand implements Command {
    public void run(String argument) throws Exception {
        if (argument != null) {
            throw new IllegalArgumentException("Exit не имеет аргументов!");
        }
        System.out.println("===========by IVAN SMIT===========");
        System.exit(0);
    }
}
