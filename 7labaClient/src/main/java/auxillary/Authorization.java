package auxillary;

import connection.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;


public class Authorization {

    static String username = null;
    static String password;
    static String passwordFin = null;
    static String result = "НЕВЕРНЫЙ ПАРОЛЬ";
    static String resultReg ="ПОЛЬЗОВАТЕЛЬ С ТАКИМ ИМЕНЕМ УЖЕ СУЩЕСТВУЕТ";

    public static void authorization(connectionManager client) {

        BufferedReader n = new BufferedReader(new InputStreamReader(System.in));

        try {
            System.out.println("1-регистрация\n2-вход");
            boolean flag = true;
            while (flag) {
                int up = new Corrector().getInt();
                switch (up) {
                    case 1: {
                        while (resultReg.equals("ПОЛЬЗОВАТЕЛЬ С ТАКИМ ИМЕНЕМ УЖЕ СУЩЕСТВУЕТ")) {
                            System.out.println("Придумайте имя пользователя и пароль");
                            System.out.println("Username: ");
                            username = n.readLine();
                            System.out.println("Password: ");
                            password = String.valueOf(PasswordChecker.readPassword());
                            passwordFin = new PasswordChecker().toSHA256(password);
                            assert client != null;
                            resultReg = client.sendMessage(new Request("registration", username, passwordFin)).gettextResponse();
                        }
                        flag = false;
                        break;
                    }
                    case 2: {
                        while (result.equals("НЕВЕРНЫЙ ПАРОЛЬ") | result.equals("ПОЛЬЗОВАТЕЛЯ С ТАКИМ ИМЕНЕМ НЕ СУЩЕСТВУЕТ")) {
                            System.out.println("Введите имя пользователя и пароль");
                            System.out.println("Username: ");
                            username = n.readLine();
                            System.out.println("Password: ");
                            password = String.valueOf(PasswordChecker.readPassword());
                            passwordFin = new PasswordChecker().toSHA256(password);
                            assert client != null;
                            result = client.sendMessage(new Request("enter", username, passwordFin)).gettextResponse();
                        }
                        flag = false;
                        break;
                    }
                    default:
                        System.out.println("Ведите либо 1, либо 2!");
                        break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
   public String getUsername(){
        if (username != null){
            return username;
        }
        return null;
    }
    public String getPassword(){
        if (passwordFin != null){
            return passwordFin;
        }
        return null;
    }
}
