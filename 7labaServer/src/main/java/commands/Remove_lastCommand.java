package commands;

import City.City;
import auxiliary.Command;

import java.util.Stack;

/**
 * удаляет последний элемент из коллекции
 */

public class Remove_lastCommand implements Command {
    public String run(Stack<City> cityCollection) {

            cityCollection.peek();
        return "Удален элемент:\n" + cityCollection.pop().toString()
                    +"\nНе забывайте сохранять изменения с помощью команды 'save'";

        }

    }


