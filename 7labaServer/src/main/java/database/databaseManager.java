package database;


import City.*;
import java.util.*;
import java.time.LocalDateTime;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

public class databaseManager {
    public static String date;

    public static Stack<City> loadCollection(Connection db) {
        date = java.time.LocalDateTime.now().toString();
        Stack<City> cityCollection = new Stack<>();

        try {
            Statement st = db.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM collection");

            while (rs.next()) {
                try {
                    long id = Long.parseLong(rs.getString(1));
                    String name = rs.getString(2);
                    String[] xy = rs.getString(3).split(" ");
                    Coordinates coordinates = new Coordinates(Integer.valueOf(xy[0]), Integer.valueOf(xy[1]));
                    java.time.LocalDateTime localDate = LocalDateTime.parse(rs.getString(4));
                    int area = Integer.parseInt(rs.getString(5));
                    Long population = Long.valueOf(rs.getString(6));
                    Long metersAboveSeaLevel = Long.valueOf(rs.getString(7));
                    long carCode = Long.parseLong(rs.getString(8));
                    Climate climate = Climate.getEnumByName(rs.getString(9));
                    StandardOfLiving standardOfLiving = StandardOfLiving.getEnumByName(rs.getString(10));
                    float height = Float.parseFloat(rs.getString(11));
                    Human governor = new Human(height);
                    governor.setHeight(height);
                    String username = rs.getString(12);

                    cityCollection.add(new City(id, name.trim(), coordinates,
                            localDate.toString(),
                            area, population, metersAboveSeaLevel,
                            carCode, climate, standardOfLiving, governor, username));
                } catch (IllegalArgumentException e) {
                    System.out.println("Поврежденный файл! Некоторые элементы типа City имеют неправильные значения, поэтому они были пропущены");
                }
            }
            rs.close();
            st.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cityCollection;
    }

    public static void saveCollection(Stack<City> cityCollection, Connection db) {
        Iterator iterator = cityCollection.iterator();

        try {
            Statement st = db.createStatement();
            st.executeUpdate("TRUNCATE collection");
            while (iterator.hasNext()) {
                String[] fields = iterator.next().toString().split(",");
                try {
                    st.executeUpdate(
                            "INSERT INTO collection(id, name, coordinates,localDate," +
                                    "                    area, population, metersAboveSeaLevel,\n" +
                                    "                      carCode, climate, standardOfLiving, governor, username)" +
                                    " VALUES(" + fields[0] + ", '" + fields[1] +
                                    "', '" + fields[2] + "', '" + fields[3] +
                                    "', '" + fields[4] + "', '" + fields[5] +
                                    "', '" + fields[6] + "', '" + fields[7] +
                                    "', '" + fields[8] + "', '" + fields[9] +
                                    "', '" + fields[10]+ "', '" + fields[11] + "')");

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

