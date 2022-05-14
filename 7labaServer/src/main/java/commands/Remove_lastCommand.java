package commands;

import City.City;
import auxiliary.Command;
import database.DatabaseManager;
import database.SQLCommands;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Stack;

/**
 * удаляет последний элемент из коллекции
 */

public class Remove_lastCommand implements Command {
    public String run(Stack<City> cityCollection, Connection myDatabase, String username) throws SQLException {

            cityCollection.peek();
            new SQLCommands().clearDataBase(myDatabase);
            DatabaseManager.saveCollection(cityCollection, myDatabase, username);
          //  new SQLCommands().deleteByID()
        System.out.println(username+ " удалил последний элемент\n");
        return "Удален элемент:\n" + cityCollection.pop().toString();

        }

    }


