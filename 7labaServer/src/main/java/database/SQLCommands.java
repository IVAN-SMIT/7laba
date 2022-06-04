package database;

import City.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.Stack;


/**
 * Содержит в себе методы для самых необходимых sql запросов
 * @value stmt - для выполнения простых SQL-запросов без параметров
 */

public class SQLCommands {

    Statement stmt = null;

    /**
     * Метод, очищающий базу данных от всех значений
     * @param myDatabase - база мать его данных
     * @return возвращает уже очищенную базу данных
     * @throws SQLException - если бд не существует
     */

    public Connection clearDataBase(Connection myDatabase) throws SQLException {
        stmt = myDatabase.createStatement();
        String sql = "TRUNCATE cityCollection";
        stmt.executeUpdate(sql);
        return myDatabase;
    }

    /**
     * Метод создает новую таблицу, если со старой возникают проблемы
     * @param myDatabase - база данных
     * @throws SQLException - это если таблица уже существует, то есть сначала нужно удалить старую
     */

    public Connection createNewTable(Connection myDatabase) throws SQLException{
        stmt = myDatabase.createStatement();
        String sql = "CREATE TABLE cityCollection (\n" +
                "   id serial PRIMARY KEY,\n" +
                "   name VARCHAR (50) NOT NULL,\n" +
                "   coordinates VARCHAR (50) NOT NULL,\n" +
                "   localDate VARCHAR (50) NOT NULL,\n" +
                "   area VARCHAR (50) NOT NULL,\n" +
                "   population VARCHAR (50) NOT NULL,\n" +
                "   metersAboveSeaLevel VARCHAR (50) NOT NULL,\n" +
                "   carCode VARCHAR (50) NOT NULL,\n" +
                "   climate VARCHAR (50) NOT NULL,\n" +
                "   standardOfLiving VARCHAR (50) NOT NULL,\n" +
                "   governor VARCHAR (50) NOT NULL,\n" +
                "   username VARCHAR (100) NOT NULL\n" +
                " );";
        stmt.executeUpdate(sql);
        return myDatabase;
    }

    /**
     *  Создает новую таблицу с пользователями и паролями, если со старой возникают проблемы
     *  50,250 - количество символов. Пароль невъебенно длинный, поэтому 250 чтобы наверняка
     * @param myDatabase - база данных
     * @throws SQLException - это если таблица уже существует, то есть сначала нужно удалить старую
     */

    public Connection createUsers(Connection myDatabase) throws SQLException{
        stmt = myDatabase.createStatement();
        String sql = "CREATE TABLE users(\n" +
                "\t id VARCHAR (50)NOT NULL,\n" +
                "\tusername VARCHAR (50)NOT NULL,\n" +
                "\tpassword VARCHAR (250)NOT NULL\n" +
                "\t);";
        stmt.executeUpdate(sql);
        return myDatabase;
    }

    /**
     * Удаляет элемент из базы данных по значению его id
     * @param myDatabase - база данных
     * @param id -id элемента
     * @return - возвращает базу данных
     * @throws SQLException - если все по пизде
     */

    public Connection deleteByID(Connection myDatabase, long id) throws  SQLException{
        stmt = myDatabase.createStatement();
        String sql = "DELETE FROM cityCollection WHERE id =" + id;
        stmt.executeUpdate(sql);
      return myDatabase;
    }

    /**
     * Перемешивает все элементы в базе данных
     * @param myDatabase - база данных
     * @return - возвращает уже перемешанную
     * @throws SQLException - если не существует
     */

    public Stack<City> shuffleDB(Connection myDatabase, Stack<City> cityCollection)throws SQLException{
        try{
        stmt = myDatabase.createStatement();
        ResultSet resultSet = stmt.executeQuery("SELECT * FROM cityCollection order by random()");
        while (resultSet.next()) {
            try {
                long id = Long.parseLong(resultSet.getString(1));
                String name = resultSet.getString(2);
                String[] xy = resultSet.getString(3).split(" ");
                Coordinates coordinates = new Coordinates(Integer.valueOf(xy[0]), Integer.valueOf(xy[1]));
                java.time.LocalDateTime localDate = LocalDateTime.parse(resultSet.getString(4));
                int area = Integer.parseInt(resultSet.getString(5));
                Long population = Long.valueOf(resultSet.getString(6));
                Long metersAboveSeaLevel = Long.valueOf(resultSet.getString(7));
                long carCode = Long.parseLong(resultSet.getString(8));
                Climate climate = Climate.getEnumByName(resultSet.getString(9));
                StandardOfLiving standardOfLiving = StandardOfLiving.getEnumByName(resultSet.getString(10));
                float height = Float.parseFloat(resultSet.getString(11));
                Human governor = new Human(height);
                governor.setHeight(height);
                String username = resultSet.getString(12);

                cityCollection.add(new City(id, name.trim(), coordinates,
                        localDate.toString(),
                        area, population, metersAboveSeaLevel,
                        carCode, climate, standardOfLiving, governor, username));
            } catch (IllegalArgumentException e) {
                System.out.println("Поврежденный файл! Некоторые элементы типа City имеют неправильные значения, поэтому они были пропущены");
            }
        }
        resultSet.close();
        stmt.close();
    } catch (Exception e) {
        e.printStackTrace();
    }

        return cityCollection;
    }
}


   // SELECT * FROM users INNER JOIN cityCollection on users . username =  cityCollection . username // объединить  cityCollection и username