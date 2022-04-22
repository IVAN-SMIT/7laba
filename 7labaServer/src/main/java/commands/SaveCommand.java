package commands;

import City.City;
import auxiliary.Command;
import fileManager.FileManager;
import java.util.Stack;

/**
 * Сохраняет коллекцию в файл
 */

public class SaveCommand implements Command {
    public String run(String argument, Stack<City> cityCollection){
        if (argument == null) {
            new FileManager().saveCollection(cityCollection, null);
        } else {
           new FileManager().saveCollection(cityCollection, argument);
        }
        String result = "Коллекция сохранена!";
        System.out.println(result);
        return result;
    }

}
