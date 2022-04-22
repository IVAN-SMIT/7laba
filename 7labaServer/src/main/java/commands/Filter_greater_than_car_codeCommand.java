package commands;

import City.City;
import auxiliary.Command;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.Stack;
import java.util.stream.Collectors;

/**
 * выводит элементы, значение поля carCode которых больше заданного
 */

public class Filter_greater_than_car_codeCommand implements Command {
    public String run(String argument, Stack<City> cityCollection) throws Exception {

            Long carCode;

            try {
                carCode = Long.parseLong(argument);
            } catch (Exception e) {
               // e.printStackTrace();
                return "Введены неверные данные! Попробуйте снова. (начните с filter_greater_than_car_code)";
            }

            int count = 0;
            int fin = cityCollection.size();
            String result ="";

        /*
           Iterator<City> iterator = cityCollection.iterator();
            while (iterator.hasNext()) {
                count = count + 1;
                City element = iterator.next();
                if (element.getCarCode() > carCode ) {
                    count = count + 1;
                    result = result + "\n"+ element.toString();
                }
             }
        */

       result = cityCollection.stream().filter((p)-> p.getCarCode() >= carCode).collect(Collectors.toList()).toString();

        if (count == fin) {return "Элементы со значением больше " + carCode + " отсутсвуют!";}

            return result;
        }
    }

