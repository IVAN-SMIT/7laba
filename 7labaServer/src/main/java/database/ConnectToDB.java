package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Класс для подключения к бд
 */

public class ConnectToDB {


    // "jdbc:postgresql://tai.db.elephantsql.com:5432/umokrpbh";
    //"jdbc:postgresql://pg:5432/studs";
    //jdbc:postgresql://localhost:5432/postgres;
    //"jdbc:postgresql:localhost:5432";


    static String USER = "postgres";
    static String PASS = "111";
    static String DB_URL ="jdbc:postgresql://localhost:5432/studs";
    static String driverClassName = "org.postgresql.Driver";

    public Connection connect(Connection myDatabase) throws Exception {

        Statement stmt = null;
        try{
            Class.forName(driverClassName);
        }catch (ClassNotFoundException cl){
            System.out.println(cl);
            System.out.println("класс драйвера не найден");
        }
        try {
            myDatabase = DriverManager.getConnection(DB_URL, USER, PASS);
        }catch (SQLException e){
            System.out.println("Не удалось подключиться к БД");
        }

        if (myDatabase != null) {
            System.out.println("Соединение с БД выполнено успешно");
        } else {
            System.out.println("Ошибка при подключении к БД");

        }

        try{
            assert myDatabase != null;
            stmt = myDatabase.createStatement();
            String sql = "CREATE DATABASE cityCollection";
            stmt.executeUpdate(sql);
            System.out.println("Database успешно создана");
        } catch (Exception e){
            if (e.getMessage().contains("уже существует"))
                System.out.println("Найдена база данных ");
            else {
                System.out.println(e.getClass().getName() + ": " + e.getMessage());
            }
        }

        try {
            new SQLCommands().createNewTable(myDatabase);
            System.out.println("Таблица с коллекцией не найдена, создаем новую");
        }catch (Exception e){
            if (e.getMessage().contains("уже существует"))
                System.out.println("Найдена таблица с коллекцией");
            else {
                System.out.println(e.getClass().getName() + ": " + e.getMessage());
            }
        }
        try {
            new SQLCommands().createUsers(myDatabase);
            System.out.println("Таблица users не найдена, создаем новую");
        }catch (Exception e) {
            if (e.getMessage().contains("уже существует"))
                System.out.println("Найдена таблица users ");
            else {
                System.out.println(e.getClass().getName() + ": " + e.getMessage());
            }
        }

        return myDatabase;
    }

}