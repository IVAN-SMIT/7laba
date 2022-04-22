package commands;

import City.*;
import auxiliary.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.Stack;

/**
 * Обновляет элемент коллекции по его id, согласно синтаксису
 */

public class UpdateCommand implements Command {
    public String run(String argument, Stack<City> cityCollection) throws Exception {
        Iterator<City> iterator = cityCollection.iterator();

        String[] fields =argument.split(", ");

        while (iterator.hasNext()) {
            City element = iterator.next();
            if (element.getId() == Integer.parseInt(fields[0])) {
                iterator.remove();
                break;
            }
        }
        int index = cityCollection.size();

        try {
            Long id = Long.parseLong(fields[0]);

            String name = fields[1];
            String[] xy = fields[2].split(" ");
            Coordinates coordinates = new Coordinates(Integer.parseInt(xy[0]), Integer.parseInt(xy[1]));
            LocalDateTime localDate = LocalDateTime.now();
            int area = Integer.parseInt(fields[4].trim());
            Long population = Long.valueOf(fields[5].trim());
            Long metersAboveSeaLevel = Long.valueOf(fields[6].trim());
            long carCode = Long.parseLong(fields[7].trim());
            Climate climate = Climate.getEnumByName(fields[8].trim());
            StandardOfLiving standardOfLiving = StandardOfLiving.getEnumByName(fields[9].trim());
            float height = Float.parseFloat(fields[10]);
            Human governor = new Human(height);
            governor.setHeight(height);

            cityCollection.insertElementAt(new City(id, name.trim(), coordinates,
                    localDate.toString(),
                    area, population, metersAboveSeaLevel,
                    carCode, climate, standardOfLiving, governor), index);

            System.out.println("обновлен элемент:  " + cityCollection.peek().toString());


        } catch (Exception e) {
           // e.printStackTrace();
            return ("Введены неверные данные! Попробуйте снова. (начните с update)  \n" + e.toString());

        }
        return "Элемент был обновлен!";
    }
}

