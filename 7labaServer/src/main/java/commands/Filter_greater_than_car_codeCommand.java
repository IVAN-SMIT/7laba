package commands;

import City.City;
import auxiliary.Command;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

/**
 * выводит элементы, значение поля carCode которых больше заданного, с использованием STREAM API
 */

public class Filter_greater_than_car_codeCommand implements Command {
    public String run(String argument, Stack<City> cityCollection) throws Exception {
            long carCode;
            try {
                carCode = Long.parseLong(argument);
            } catch (Exception e) {
                return "Введены неверные данные! Попробуйте снова. (начните с filter_greater_than_car_code)";
            }
        List<City> listOfCarCode = cityCollection.stream().filter((p)-> p.getCarCode() >= carCode).collect(Collectors.toList());
        if (listOfCarCode.size() == 0) {return "Элементы со значением больше " + carCode + " отсутсвуют!";}
        return listOfCarCode.toString();
        }
    }

