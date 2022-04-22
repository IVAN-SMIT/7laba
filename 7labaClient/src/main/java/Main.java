import auxillary.*;
import connection.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
;

class Main {

    public static int port = 9890;

    public static void main(String[] args) throws Exception {


        connectionManager client = null;
        String username = null;
        String password = null;



        try {
             client = new connectionManager("localhost", port);
        }catch (Exception e){
            System.out.println("Сервер c указанным портом "+port+" не найден");
        }
        if (connectionManager.client != null) {
            System.out.println("Соединение установлено");
            BufferedReader n = new BufferedReader(new InputStreamReader(System.in));
            password = n.readLine();
            System.out.println(new PasswordChecker().toSHA256(password));
            //new PasswordChecker().toSHA256(password);

            System.out.println("\n\nВведите help чтобы посмотреть доступные команды");

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

