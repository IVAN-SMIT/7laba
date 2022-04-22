package commands;

import City.City;
import auxiliary.Command;

import java.util.Collections;
import java.util.Stack;

/**
 * перемешивает элементы коллекции в случайном порядке
 */

public class ShuffleCommand implements Command {
    public String run(Stack<City> cityCollection) {
        Collections.shuffle(cityCollection);
        return "Элементы перемешаны!";

    }
}
