package commands;

import auxiliary.Command;

import java.util.LinkedList;
import java.util.Arrays;

/**
 * Выводит массив 10 ранее введенных пользователем комманд, используя LinkedList
 */

public class HistoryCommand implements Command {
    public String run(LinkedList<String> history) throws IllegalArgumentException {
        try {
            history.removeLast();
            return "Введенные команды (последние 10):"+Arrays.toString(history.toArray());
        }catch (Exception e){
            return e.toString();
        }
    }
}
