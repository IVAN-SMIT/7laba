package commands;

import City.City;
import auxiliary.Command;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Stack;
import java.util.TimeZone;

/**
 * Показывает тип коллекции, дату ее создания и количество элементов в ней
 */

public class InfoCommand implements Command {

    public String run(Stack<City> stackInfo) throws Exception {
        try {
            Calendar c = null;
            File file = new File("cityCollection");
            SimpleDateFormat format = null;
            if (file.exists()) {
                Long lastModified = file.lastModified();
                format = new SimpleDateFormat("dd.MM.yyyy, HH:mm:ss");
                c = Calendar.getInstance();
                c.setTimeInMillis(lastModified);
                c.setTimeZone(TimeZone.getDefault());
                format.format(c.getTime());
            }

            return"Тип коллекции: " + stackInfo.getClass().getSimpleName() + " \nДата создания: " +
                    format.format(c.getTime()) + "\nКоличество элементов: " + stackInfo.size();

        } catch (Exception e) {
            System.out.println("Произошла ошибка!");
            }
        return "В коллекции отсутствуют элементы";
    }
    }
    



