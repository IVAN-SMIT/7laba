package commands;

import City.*;
import auxiliary.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.Stack;
import java.util.UUID;
import java.util.PriorityQueue;
import java.util.Iterator;
import java.sql.Connection;
import database.*;
import java.sql.Statement;
import java.sql.SQLException;

/**
 * Добавляет новый элемент в базу данных
 * id генерируется уникальным алгоритмом
 */

public class AddCommand implements Command {
    public  Stack<City> run(String argument, Stack<City> cityCollection, String username, Connection database) throws Exception {

        if (argument == null) {
            throw new IllegalArgumentException("add не имеет аргументов!");
        }

        String[] fields =argument.split(", ");

        long id;
        UUID myId = UUID.randomUUID();
        id = (long) Math.floor(Math.abs(myId.hashCode()/100000));

        try {

            int index = cityCollection.size();

            //Statement st = database.createStatement(); //простой SQL-запрос без параметров
           // ResultSet rs = st.executeQuery("SELECT last_value FROM cityCollection_id_seq");


            //id = Long.parseLong(rs.getString(1));

            //rs.close();
           // st.close();

            String name = fields[0];
            String[] xy = fields[1].split(" ");
            Coordinates coordinates = new Coordinates(Integer.parseInt(xy[0]), Integer.parseInt(xy[1]));
            LocalDateTime localDate = LocalDateTime.now();
            int area = Integer.parseInt(fields[3].trim());
            Long population = Long.valueOf(fields[4].trim());
            Long metersAboveSeaLevel = Long.valueOf(fields[5].trim());
            long carCode = Long.parseLong(fields[6].trim());
            Climate climate = Climate.getEnumByName(fields[7].trim());
            StandardOfLiving standardOfLiving = StandardOfLiving.getEnumByName(fields[8].trim());
            float height = Float.parseFloat(fields[9]);
            Human governor = new Human(height);
            governor.setHeight(height);

            cityCollection.insertElementAt(new City(id, name.trim(), coordinates,
                    localDate.toString(),
                    area, population, metersAboveSeaLevel,
                    carCode, climate, standardOfLiving, governor, username), index);

            fields[10] = Long.toString(id);
            fields[11] =username;

            System.out.println(username +" добавил элемент  " + cityCollection.peek().toString());

        }catch (Exception e) {
            //e.printStackTrace();
            Commander.response = ("Введены неверные данные! Попробуйте снова. (начните с add)  \n" + e);
        }

        DatabaseManager.saveCollection(cityCollection, database,fields, id, username);
        Commander.response = "Элемент успешно добавлен!";
        return cityCollection;
    }


}



