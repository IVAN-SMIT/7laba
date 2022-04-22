package auxiliary;

import City.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.util.Stack;

/**
 * Метод для ввода данных при добавлении элемента в коллекцию
 */

public class Entries {

    public Stack<City> getData(long id,int index, Stack<City> cityCollection,String[] fields, boolean flag) throws Exception {

        Messager p = new Messager();
        BufferedReader n = new BufferedReader(new InputStreamReader(System.in));



        p.println("Name: ", flag);
        String name = fields[0];

        p.println("(int)Coordinates [x y]: ", flag);
        String[] xy = fields[1].trim().split(" ");
        Coordinates coordinates = new Coordinates(Integer.parseInt(xy[0]), Integer.parseInt(xy[1]));

        LocalDateTime localDate = LocalDateTime.now();

        p.println("(int)Area: ", flag);
        int area = Integer.parseInt(fields[3].trim());

        p.println("(long)Population: ", flag);
        Long population = Long.valueOf(fields[4].trim());

        p.println("(long)Meters above sea level: ", flag);
        Long metersAboveSeaLevel = Long.valueOf(fields[5].trim());

        p.println("(long)Car code: ", flag);
        long carCode = Long.parseLong(fields[6].trim());

        p.println("Climate [MONSOON, OCEANIC, MEDITERRANIAN, STEPPE]: ", flag);
        Climate climate = Climate.getEnumByName(fields[7].trim());

        p.println("Standard of living[VERY_HIGH, HIGH, NIGHTMARE]: ", flag);
        StandardOfLiving standardOfLiving = StandardOfLiving.getEnumByName(fields[8].trim());

        p.println("(float)Height of Governor:", flag);
        float height = Float.parseFloat(fields[9]);
        Human governor = new Human(height);
        governor.setHeight(height);

        cityCollection.insertElementAt(new City(id, name.trim(), coordinates,
                localDate.toString(),
                area, population, metersAboveSeaLevel,
                carCode, climate, standardOfLiving, governor), index);

        return cityCollection;
    }
}
