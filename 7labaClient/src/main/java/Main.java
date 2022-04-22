import auxillary.CommandChecker;
import auxillary.Corrector;
import auxillary.Entries;
import connection.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Objects;

class Main {

    public static int port = 9890;

    public static void main(String[] args) throws Exception {

        connectionManager client = null;
        try {
             client = new connectionManager("localhost", port);
        }catch (Exception e){
            System.out.println("Сервер c указанным портом "+port+" не найден");
        }
        if (connectionManager.client != null) {
            System.out.println("Соединение установлено"+"\nВведите help чтобы посмотреть доступные команды");
            BufferedReader n = new BufferedReader(new InputStreamReader(System.in));
            CommandChecker checker = new CommandChecker();
            while (true) {
                String command = n.readLine();
                checker.check(command);
                if (command.equals("insert_at")){
                    client.sendMessage(new Request("insert_at_help"));
                    String index = "insert_at " + n.readLine();
                    CommandChecker.commandFin = new Entries().getData(index + ",");
                    if (!CommandChecker.commandFin.equals("err")) {
                        System.out.println("Ваша коллекция:\n" + CommandChecker.commandFin +
                                "\nВерно?\n \"1\"-да, все верно\n \"2\"-нет, ввести заново");
                        boolean flag = true;
                        Corrector.enter(flag);
                    }
                }
                if (CommandChecker.commandFin == null) {
                    client.sendMessage(new Request(command));
                } else {
                    client.sendMessage(new Request(CommandChecker.commandFin));
                    CommandChecker.commandFin = null;
                }

            }
        }
    }
}

