package database;


import City.*;

import java.sql.*;
import java.util.*;
import java.time.LocalDateTime;

/**
 *Класс, выполняющий обязанности работы с sql таблицами
 */

public class DatabaseManager {
    public static String date;

    /**
     * Метод загружает данные с базы данных и добавляет это все в коллекцию
     * @param db - база данных моя
     * @return возвращает коллекцию
     */
    public static Stack<City> loadCollection(Connection db) {
        date = java.time.LocalDateTime.now().toString();
        Stack<City> cityCollection = new Stack<>();

        try {
            Statement statement = db.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM cityCollection");

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
                    System.out.println(e);
                    System.out.println("Поврежденный файл! Некоторые элементы типа City имеют неправильные значения, поэтому они были пропущены");
                }
            }
            resultSet.close();
            statement.close();
        } catch (Exception e) {
            System.out.println(e.getClass().getName() + "\n" + e);
        }
        return cityCollection;
    }
    /**
     *  Загружает всё с коллекции в sql таблицу
     * @param cityCollection - коллекция
     * @param database - база данных
     * @param username - имя пользователя который сейчас работает
     */
    public static void saveCollection(Stack<City> cityCollection, Connection database, String username) {


        try {
            Iterator<City> iterator = cityCollection.iterator();
            Statement st = database.createStatement();
            st.executeUpdate("TRUNCATE cityCollection");
            while (iterator.hasNext()) {
                City element = iterator.next();
                Long id = element.getId();
                String name = element.getName();
                Coordinates coordinates = element.getCoordinates();
                String localDate = element.getLocalDate();
                long area = element.getArea();
                long population = element.getPopulation();
                long metersAboveSeaLevel = element.getMetersAboveSeaLevel();
                long carCode = element.getCarCode();
                Climate climate = element.getClimate();
                StandardOfLiving standardOfLiving = element.getStandardOfLiving();
                Human governor = element.getGovernor();

                //String[] fields = iterator.next().toString().split(" ");
                try {
                    PreparedStatement prst = database.prepareStatement("INSERT INTO cityCollection(id, name, coordinates,localDate," +
                            "                    area, population, metersAboveSeaLevel," +
                                    "                      carCode, climate, standardOfLiving, governor, username)" +
                                    " VALUES(?,?,?,?,?,?,?,?,?,?,?,?)");
                    prst.setInt(1, Math.toIntExact(id));
                    prst.setString(2,name);
                    prst.setString(3, String.valueOf(coordinates));
                    prst.setString(4,localDate);
                    prst.setString(5, String.valueOf(area));
                    prst.setString(6, String.valueOf(population));
                    prst.setString(7, String.valueOf(metersAboveSeaLevel));
                    prst.setString(8, String.valueOf(carCode));
                    prst.setString(9, String.valueOf(climate));
                    prst.setString(10, String.valueOf(standardOfLiving));
                    prst.setString(11, String.valueOf(governor));
                    prst.setString(12,username);
                    prst.executeUpdate();
                    prst.close();
                } catch (SQLException e) {
                    System.out.println(e.getClass().getName() + "\n" + e);
                }
            }
            st.close();
        } catch (Exception e) {
            System.out.println(e.getClass().getName() + "\n" + e);
        }
    }
}






/*
потеряю ещё вдруг

CREATE TABLE cityCollection (
   id serial PRIMARY KEY,
   name VARCHAR (50) NOT NULL,
   coordinates VARCHAR (50) NOT NULL,
   localDate VARCHAR (50) NOT NULL,
   area VARCHAR (50) NOT NULL,
   population VARCHAR (50) NOT NULL,
   metersAboveSeaLevel VARCHAR (50) NOT NULL,
   carCode VARCHAR (50) NOT NULL,
   climate VARCHAR (50) NOT NULL,
   standardOfLiving VARCHAR (50) NOT NULL,
   governor VARCHAR (50) NOT NULL,
   username VARCHAR (100) NOT NULL
 );
 */


