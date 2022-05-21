package commands;

import City.*;
import auxiliary.*;
import database.DatabaseManager;
import database.SQLCommands;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Stack;

/**
 * Обновляет элемент коллекции по его id, согласно синтаксису
 */

public class UpdateCommand implements Command {
    public String run(String argument, Stack<City> cityCollection, String username, Connection myDatabase) throws Exception {

        try {
            Iterator<City> iterator = cityCollection.iterator();

            Long id = Long.valueOf(argument.split(";")[0]);
            String[] fields = argument.split(";", 2)[1].split(", ");


            while (iterator.hasNext()) {
                City element = iterator.next();
                if (element.getId().equals(id) ) {
                    if(element.getUsername().equals(username)) {
                        iterator.remove();
                        new SQLCommands().deleteByID(myDatabase, id);
                        break;
                    }else return "Данный элемент был создан другим пользователем, вы не можете его редактировать";
                }
            }

            int index = cityCollection.size();

            try {

                //id = Long.parseLong(fields[0]);
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

                System.out.println(username + " обновил элемент: " + cityCollection.peek().toString() + "\n");


            } catch (Exception e) {
                e.printStackTrace();
                return ("Введены неверные данные! Попробуйте снова. (начните с update)  \n" + e.toString());
            }



        } catch (NumberFormatException e) {
            return e.getMessage();
        } catch (Exception e) {
            return e.toString();
        }
        DatabaseManager.saveCollection(cityCollection, myDatabase, username);
        return "Элемент был обновлен!";
    }
}

