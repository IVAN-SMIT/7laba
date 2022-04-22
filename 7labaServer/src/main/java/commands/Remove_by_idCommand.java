package commands;

import City.City;
import auxiliary.Command;
import auxiliary.Messager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Collection;
import java.util.Iterator;
import java.util.Stack;
import java.util.stream.Collectors;

/**
 * удаляет элемент из коллекции по его id
 */

public class Remove_by_idCommand implements Command {

    public String run(String argument, Stack<City> cityCollection) throws Exception {



        try {

         /*
            while (iterator.hasNext()) {
                City element = iterator.next();
                if (element.getId().equals(numId)) {
                    iterator.remove();
                    return "Элемент:\n" + element.toString() +"\nCо значением id:" + numId + " был удалён.\nНе забывайте про 'save' чтобы сохранить изменения.";
                }
            }
         */

            Long numId = Long.valueOf(argument);
            Stack<City> res = cityCollection.stream().filter((p) -> p.getId().equals(numId)).collect(Collectors.toCollection(Stack::new));
            cityCollection.removeAll(res);
            if (res.size() == 0){
                return "Элемент cо значением id:" + numId + " не найден!";
            } else {
                return "Элемент:\n" + res + "\nCо значением id:" + numId + " был удалён.\nНе забывайте сохранить изменения.";
            }
        }catch (Exception i){
            return "Введены неверные данные! Попробуйте снова. \n(начните с remove_by_id + id желаемого элемента)";
        }
    }
}



