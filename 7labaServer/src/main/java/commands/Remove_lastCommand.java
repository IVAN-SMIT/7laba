package commands;

import City.City;
import auxiliary.Command;
import database.SQLCommands;

import java.util.Stack;

/**
 * удаляет последний элемент из коллекции
 */

public class Remove_lastCommand implements Command {
    public String run(Stack<City> cityCollection) {

            cityCollection.peek();
          //  new SQLCommands().deleteByID()
        return "Удален элемент:\n" + cityCollection.pop().toString()
                    +"\nНе забывайте сохранять изменения с помощью команды 'save'";

        }

    }


