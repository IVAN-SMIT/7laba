package commands;

import City.City;
import auxiliary.Command;
import auxiliary.Commander;
import database.DatabaseManager;
import database.SQLCommands;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Stack;

/**
 * перемешивает элементы коллекции в случайном порядке
 */

public class ShuffleCommand implements Command {
    public Stack<City> run(Stack<City> cityCollection, Connection myDatabase, String username) throws SQLException {
       // cityCollection = new SQLCommands().shuffleDB(myDatabase, cityCollection);//кто ж знал что это хуета
        Collections.shuffle(cityCollection);
        new SQLCommands().clearDataBase(myDatabase);
        DatabaseManager.saveCollection(cityCollection, myDatabase, username);
        System.out.println(username + " перемешал все элементы\n");
        Commander.response =  "Элементы перемешаны!";
        return cityCollection;
    }
}
