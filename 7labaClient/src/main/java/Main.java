import auxillary.*;
import connection.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;


class Main {

    public static int port = 9890;

    public static void main(String[] args) throws Exception {

        BufferedReader n = new BufferedReader(new InputStreamReader(System.in));
        connectionManager client = null;
        String username = null;
        String password;
        String passwordFin = null;

        String result = "WRONG_PASS";
        try {
            while (result.equals("WRONG_PASS")) {
                System.out.println("Username: ");
                username = n.readLine();
                System.out.println("Password: ");
                password = n.readLine();
                passwordFin = new PasswordChecker().toSHA256(password);
                assert client != null;
                System.out.println(username + "\n"+passwordFin);
                result = client.sendMessage(new Request("registration", username, passwordFin)).gettextResponse();
                System.out.println(result);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        try {
            client = new connectionManager("localhost", port);
        }catch (Exception e){
            System.out.println("Сервер c указанным портом "+port+" не найден");
        }

        if (connectionManager.client != null) {
            System.out.println("Соединение установлено");
            System.out.println("\n\nВведите help чтобы посмотреть доступные команды");
            CommandChecker checker = new CommandChecker();
            while (true) {
                String command = n.readLine();
                checker.check(command);
                if (command.equals("insert_at")){
                    // client.sendMessage(new Request("insert_at_help"));
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
                    client.sendMessage(new Request(command, username, passwordFin));
                } else {
                    client.sendMessage(new Request(CommandChecker.commandFin, username, passwordFin));
                    CommandChecker.commandFin = null;
                }

            }
        }
    }
}

