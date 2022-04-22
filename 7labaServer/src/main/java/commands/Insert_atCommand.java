package commands;

import City.*;
import auxiliary.Command;
import auxiliary.Corrector;
import auxiliary.Entries;
import auxiliary.Messager;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.util.Stack;
import java.util.UUID;

/**
 * добавляет новый элемент в заданную позицию
 * позиция указывается вручную
 */


public class Insert_atCommand implements Command {
    public String run(String argument, Stack<City> cityCollection) throws Exception {
        int index;
        Messager insert = new Messager();

            String[] fields =argument.split(", ");

        index = Integer.parseInt(fields[0]) - 1;

        if (index > cityCollection.size() | index <= 0){
            return ("А ничего, что в коллекции всего " + cityCollection.size() +" элементов???" +
                    "\n Начинай заново с 'insert_at'");}
                try {
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
                            carCode, climate, standardOfLiving, governor), index);

                    insert.println("Добавлен элемент " + cityCollection.peek().toString() +"\nв позицию "+index, true);
                }
                catch (Exception e) {
                    //e.printStackTrace();
                    insert.println("Введены неверные данные! Попробуйте снова. " +
                            "(начните с insert_at + номер желаемой позиции элемента)", true);
                    return "Введены неверные данные! Попробуйте снова. " +
                            "(начните с insert_at)  \n" +e;
                }

        return "Элемент обновлен!\n" + cityCollection.pop();
    }
}



