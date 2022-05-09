package commands;

import City.City;
import auxiliary.Command;
import auxiliary.Commander;
import database.DatabaseManager;
import database.SQLCommands;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Stack;

/**
 * перемешивает элементы коллекции в случайном порядке
 */

public class ShuffleCommand implements Command {
    public Stack<City> run(Stack<City> cityCollection, Connection myDatabase) throws SQLException {
        cityCollection = new SQLCommands().shuffleDB(myDatabase, cityCollection);
       // Collections.shuffle(cityCollection);
        Commander.response =  "Элементы перемешаны!";
        return cityCollection;
    }
}
