package commands;

import City.City;
import auxiliary.Command;
import auxiliary.Commander;
import database.SQLCommands;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

/**
 * очищает коллекцию как и в базе данных, так и в буферной памяти
 */

public class ClearCommand implements Command {
 public Stack<City> run(String argument, Connection myDatabase, Stack<City> cityCollection) throws IllegalArgumentException, SQLException {
     if (argument != null) {
         throw new IllegalArgumentException("Clear не имеет аргументов!");
     }
     try {
         while (!cityCollection.empty()) {
             cityCollection.pop();
         }
         new SQLCommands().clearDataBase(myDatabase);
         Commander.response = "Все элементы коллекции удалены!";
     }catch (SQLException e){
         Commander.response = "Тут и так пусто, нечего удалять ";
     }
     return cityCollection;
    }
}
