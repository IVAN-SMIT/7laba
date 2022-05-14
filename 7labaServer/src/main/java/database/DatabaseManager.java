package database;


import City.*;
import java.util.*;
import java.time.LocalDateTime;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

/**
 *
 */

public class DatabaseManager {
    public static String date;

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
            e.printStackTrace();
        }
        return cityCollection;
    }

    public static void saveCollection(Stack<City> cityCollection, Connection database, String username) {
        Iterator<City> iterator = cityCollection.iterator();

        try {
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
                    st.executeUpdate(
                            "INSERT INTO cityCollection(id, name, coordinates,localDate," +
                                    "                    area, population, metersAboveSeaLevel,\n" +
                                    "                      carCode, climate, standardOfLiving, governor, username)" +
                                    " VALUES("+id+", '"  + name +
                                    "', '" + coordinates + "', '" + localDate +
                                    "', '" + area + "', '" + population +
                                    "', '" + metersAboveSeaLevel + "', '" + carCode +
                                    "', '" + climate + "', '" + standardOfLiving +
                                    "', '" + governor + "', '" + username + "')");

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            st.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
/*
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


