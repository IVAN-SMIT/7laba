package commands;

import City.*;
import auxiliary.Command;
import auxiliary.Commander;
import connection.Response;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;


/**
 * Запускает команды из файла
 */

public class Execute_scriptCommand implements Command {

    /**
     *Метод для чтения файлов, в которых могут быть записаны команды
     * @param file - имя файла
     *
     */

    public String run(String file, Stack<City> cityCollection) throws Exception {

        ArrayList<String> commands = new ArrayList<String>();

        try (
                FileReader reader = new FileReader(file);
                BufferedReader buf = new BufferedReader(reader)
        ){
            String line;
            while ((line = buf.readLine()) != null) {
                commands.add(line);
            }

        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден! Возможно, вы забыли добавить .txt");
        } catch (IOException e) {
            e.printStackTrace();
        }

        String result = "Найденные команды: "+commands.toString();

        for (String command : commands) {
            if (command.split(" ").length == 2 && command.split(" ")[0].equals("execute_script") && command.split(" ")[1].equals(file)) {
                throw new IllegalArgumentException("Ошибка зацикливания!");
            }
            Commander.scriptCommand = command;
            Commander.readCommand(cityCollection);
            result =  result + "\n\n"+ Commander.response;
        }

        return result;
    }
}