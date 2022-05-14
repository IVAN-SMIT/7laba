package commands;

import City.City;
import auxiliary.Command;

import java.util.Stack;
import java.util.stream.Collectors;

/**
 * удаляет элемент из коллекции по его id, с использованием STREAM API
 */

public class Remove_by_idCommand implements Command {
    public String run(String argument, Stack<City> cityCollection) throws Exception {

        try {
            Long numId = Long.valueOf(argument);
            Stack<City> res = cityCollection.stream().filter((p) -> p.getId().equals(numId)).collect(Collectors.toCollection(Stack::new));
            cityCollection.removeAll(res);
            if (res.size() == 0){
                return "Элемент cо значением id:" + numId + " не найден!";
            } else {
                return "Элемент:\n" + res + "\nCо значением id:" + numId + " был удалён.";
            }
        }catch (Exception i){
            return "Введены неверные данные! Попробуйте снова. \n(начните с remove_by_id + id желаемого элемента)";
        }
    }
}



