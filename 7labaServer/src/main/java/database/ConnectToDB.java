package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectToDB {


    // "jdbc:postgresql://tai.db.elephantsql.com:5432/umokrpbh";
    //"jdbc:postgresql://pg:5432/studs";
    //jdbc:postgresql://localhost:5432/postgres;
    //"jdbc:postgresql:localhost:5432";


    static String USER = "postgres";
    static String PASS = "111";
    static String DB_URL ="jdbc:postgresql://localhost:5432/cityCollection";
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


            /*
            System.out.println("создаем бд...");
            stmt = myDatabase.createStatement();
            String sql = "CREATE DATABASE cityCollection";
            stmt.executeUpdate(sql);
            System.out.println("Database успешно создана...");
            */


/*
        stmt = myDatabase.createStatement();


        String sql = "INSERT INTO cityCollection(id, name, coordinates,localDate, area, population, metersAboveSeaLevel,\n" +
                "                                                         carCode, climate, standardOfLiving, governor, username)\n" +
                "                                     VALUES(56,12,'6 0','2022-04-22T21:25:30.184',324,23,23,23,'OCEANIC','HIGH',1.0, 'op')";
        stmt.executeUpdate(sql);

*/
        return myDatabase;
    }

}