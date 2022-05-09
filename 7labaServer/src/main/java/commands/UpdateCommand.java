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
        Iterator<City> iterator = cityCollection.iterator();

        Long id = Long.valueOf(argument.split(";")[0]);
        String[] fields = argument.split(";", 2)[1].split(", ");
        ArrayList<String> fin = new ArrayList<>();

        while (iterator.hasNext()) {
            City element = iterator.next();
            if (element.getId().equals(id)) {
                iterator.remove();
                new SQLCommands().deleteByID(myDatabase, id);
                break;
            }
        }

        int index = cityCollection.size();

        try {
            //id = Long.parseLong(fields[0]);
            String name = fields[0];
            fin.add(name);
            String[] xy = fields[1].split(" ");
            Coordinates coordinates = new Coordinates(Integer.parseInt(xy[0]), Integer.parseInt(xy[1]));
            fin.add(coordinates.toString());
            LocalDateTime localDate = LocalDateTime.now();
            fin.add(localDate.toString());
            int area = Integer.parseInt(fields[3].trim());
            fin.add(String.valueOf(area));
            Long population = Long.valueOf(fields[4].trim());
            fin.add(population.toString());
            Long metersAboveSeaLevel = Long.valueOf(fields[5].trim());
            fin.add(metersAboveSeaLevel.toString());
            long carCode = Long.parseLong(fields[6].trim());
            fin.add(String.valueOf(carCode));
            Climate climate = Climate.getEnumByName(fields[7].trim());
            fin.add(String.valueOf(climate));
            StandardOfLiving standardOfLiving = StandardOfLiving.getEnumByName(fields[8].trim());
            fin.add(standardOfLiving.toString());
            float height = Float.parseFloat(fields[9]);
            Human governor = new Human(height);
            governor.setHeight(height);
            fin.add(String.valueOf(governor));

            cityCollection.insertElementAt(new City(id, name.trim(), coordinates,
                    localDate.toString(),
                    area, population, metersAboveSeaLevel,
                    carCode, climate, standardOfLiving, governor, username), index);

            System.out.println(username+" обновил элемент: " + cityCollection.peek().toString()+"\n");
            fin.add(Long.toString(id));
            fin.add(username);

        } catch (Exception e) {
            e.printStackTrace();
            return ("Введены неверные данные! Попробуйте снова. (начните с update)  \n" + e.toString());
        }

        String[] arr = fin.toArray(new String[0]);

        DatabaseManager.saveCollection(cityCollection, myDatabase, arr, id, username);
        return "Элемент был обновлен!";
    }
}

