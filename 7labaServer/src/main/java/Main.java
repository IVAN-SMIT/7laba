import City.City;
import auxiliary.Commander;
import auxiliary.IdChecker;
import connection.ServerManager;
import fileManager.FileManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Stack;

// вариант 665580


public class Main {

    public static void main(String[] args) throws Exception {

        if (args.length != 1) {
            System.out.println("Пожалуйста, запускайте только с именем файла коллекции!");
        }


         String driverClassName = "org.postgresql.Driver";
         String DB_URL = "jdbc:postgresql://tai.db.elephantsql.com:5432/umokrpbh";
         String USER = "umokrpbh";
         String PASS = "HXZqmwY3rbFjh-ZoOdo8yXCkOkDPWkUQ";

        Connection con = null;

        try{
            Class.forName(driverClassName);

        }catch (ClassNotFoundException cl){
            System.out.println(cl);
            System.out.println("класс драйвера не найден");
        }

        try {
           con = DriverManager.getConnection(DB_URL, USER, PASS);
        }catch (SQLException e){
            System.out.println("Не удалось подключиться к БД");
        }

        if (con != null) {
            System.out.println("Соединение с БД выполнено успешно");
        } else {
            System.out.println("Ошибка при подключении к БД");
        }



        Stack<City> cityCollection = new FileManager().loadCollection(args[0]);
        Commander.setCollection(cityCollection);
        System.out.println("Коллекция загружена");

        while (true) {

            new IdChecker();
            IdChecker.check(cityCollection);
            try {
                ServerManager server = new ServerManager(9890);
                server.starting(cityCollection);
            }catch (Exception e){
                System.out.println(e +"\n =======================");break;
            }

            }
        }
    }


