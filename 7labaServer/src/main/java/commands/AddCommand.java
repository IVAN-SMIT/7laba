package commands;

import City.*;
import auxiliary.*;
import auxiliary.Messager;

import java.time.LocalDateTime;
import java.util.Stack;
import java.util.UUID;


public class AddCommand implements Command {
    public  String run(String argument, Stack<City> cityCollection) throws Exception {

        if (argument == null) {
            throw new IllegalArgumentException("add не имеет аргументов!");
        }

        String fields[] =argument.split(", ");
        System.out.println(argument);

        try {
            int index = cityCollection.size();

            Long id;
            UUID myId = UUID.randomUUID();
            id = (long) Math.floor(Math.abs(myId.hashCode()/100000));

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
                    carCode, climate, standardOfLiving, governor), index);

            System.out.println("добавлен элемент  " + cityCollection.peek().toString());


        }catch (Exception e) {
            //e.printStackTrace();
            return ("Введены неверные данные! Попробуйте снова. (начните с add)  \n" + e);

        }
        return "Элемент успешно добавлен!";
    }


}



