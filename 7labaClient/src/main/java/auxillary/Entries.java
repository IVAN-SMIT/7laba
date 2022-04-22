package auxillary;



import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDateTime;


/**
 * Метод для ввода данных при добавлении элемента в коллекцию
 */

public class Entries {

    public String getData(String command) throws Exception {

        Messager p = new Messager();
        BufferedReader n = new BufferedReader(new InputStreamReader(System.in));
        boolean flag = true;

        try {
            p.println("Name: ", flag);
            String name = n.readLine();

            p.println("(int)Coordinates [x y]: ", flag);
            String xy = n.readLine().trim();
            // coordinates = new Coordinates(Integer.parseInt(xy[0]), Integer.parseInt(xy[1]));

            LocalDateTime localDate = LocalDateTime.now();

            p.println("(int)Area: ", flag);
            int area = Integer.parseInt(n.readLine().trim());

            p.println("(long)Population: ", flag);
            Long population = Long.valueOf(n.readLine().trim());

            p.println("(long)Meters above sea level: ", flag);
            Long metersAboveSeaLevel = Long.valueOf(n.readLine().trim());

            p.println("(long)Car code: ", flag);
            long carCode = Long.parseLong(n.readLine().trim());

            p.println("Climate [MONSOON, OCEANIC, MEDITERRANIAN, STEPPE]: ", flag);
            String climate = n.readLine().trim();

            p.println("Standard of living[VERY_HIGH, HIGH, NIGHTMARE]: ", flag);
            String standardOfLiving = n.readLine().trim();

            p.println("(float)Height of Governor:", flag);
            float height = Float.parseFloat(n.readLine());


            command = command + " " + name + ", " + xy + ", " + localDate + ", " + area + ", " + population
                    + ", " + metersAboveSeaLevel + ", " + carCode + ", " + climate + ", " + standardOfLiving + ", " + height;

        }catch (Exception e){
            System.out.println("Произошла ошибка ввода данных!\nНачните заново");
            command = "err";
        }
        return command;
    }
}
