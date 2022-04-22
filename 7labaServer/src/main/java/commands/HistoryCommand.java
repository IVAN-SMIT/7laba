package commands;

import auxiliary.Command;
import auxiliary.Messager;

import java.util.LinkedList;
import java.util.Arrays;

/**
 * Выводит массив 10 ранее введенных пользователем комманд, используя LinkedList
 */

public class HistoryCommand implements Command {
    public String run(String argument, LinkedList<String> history) throws IllegalArgumentException {
        if (argument != null) {
            throw new IllegalArgumentException("history не имеет аргументов");
        }
        history.removeLast();
        return "Введенные команды (последние 10):"+Arrays.toString(history.toArray());

    }
}
