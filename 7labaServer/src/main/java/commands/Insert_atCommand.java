package commands;

import City.*;
import auxiliary.*;
import database.DatabaseManager;

import java.sql.Connection;
import java.time.LocalDateTime;
import java.util.Stack;
import java.util.UUID;

/**
 * добавляет новый элемент в заданную позицию
 * позиция указывается вручную
 */

public class Insert_atCommand implements Command {
    public Stack<City> run(String argument, Stack<City> cityCollection, Connection database, String username) throws Exception {
        int index;

        try {

            String[] fields = argument.split(", ");

            index = Integer.parseInt(fields[0]) - 1;
            if (index > cityCollection.size() | index <= 0){
                Commander.response = ("А ничего, что в коллекции всего " + cityCollection.size() +" элементов???" +
                        "\n Начинай заново с 'insert_at'");}

            UUID myId = UUID.randomUUID();
            long id = (long) Math.floor(Math.abs(myId.hashCode()/100000));

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
                    carCode, climate, standardOfLiving, governor, username), index);

            System.out.println("Добавлен элемент " + cityCollection.peek().toString() +"\nв позицию "+index);
        }
        catch (Exception e) {
            //e.printStackTrace();
            System.out.println("Введены неверные данные! Попробуйте снова. " +
                    "(начните с insert_at + номер желаемой позиции элемента)");
            Commander.response = "Введены неверные данные! Попробуйте снова. " +
                    "(начните с insert_at)  \n" +e;
        }


        DatabaseManager.saveCollection(cityCollection, database, username);
        Commander.response = "Элемент обновлен!\n" + cityCollection.pop();
        return cityCollection;
    }
}



