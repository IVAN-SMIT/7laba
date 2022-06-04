package auxiliary;

import City.City;
import commands.*;
import org.postgresql.util.PSQLException;


import java.sql.Connection;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Stack;


/**
 * Отвечает за выполнение всех команд приложения, запоминает введенные команды в LinkedList
 */


public class Commander {
    private static Stack<City> cityCollection;

    private static LinkedList<String> history = new LinkedList<>();

    public static void setCollection(Stack<City> IcityCollection) {
        cityCollection = IcityCollection;
    }

    public static String command;
    public static String response;
    public static String scriptCommand;

    
    public static Stack<City> readCommand(Stack<City> cityCollection, String username, String password, Connection myDatabase) throws Exception {

        String argument = null;
        if (scriptCommand != null){
            command = scriptCommand;
            scriptCommand = null;
        }
        if (!(command.split(" ", 2).length == 1)) {
            argument = command.split(" ", 2)[1];
        }
        command = command.split(" ", 2)[0];

        if (history.size() == 11) {
            history.remove();
        }
        if (!Objects.equals(command, "insert_at_help")|!Objects.equals(command, "enter" )|!Objects.equals(command, "registration" )){
            history.add(command);
        }

        try {
        if(!myDatabase.isClosed()){
            System.out.println("Пользователь: "+username +"\n Распознана команда: "+command+"\n argument команды: " + argument+  "\n");
            switch (command) {
                case "enter": response =Registration.asEnter(username, password, myDatabase);break;
                case "registration": response = Registration.reg(username, password, myDatabase);;break;
                case "help": response = new HelpCommand().run();break;
                case "exit":  new SaveCommand().run();break;
                case "info":response = new InfoCommand().run(cityCollection);break;
                case "3137best": pashalOchka.run(argument);break;// интересно, а что же это......
                case "history": response = new HistoryCommand().run(history);break;
                case "show": response = new ShowCommand().run(argument, cityCollection);break;
                case "clear": cityCollection = new  ClearCommand().run(argument, myDatabase, cityCollection);break;
                case "add":  cityCollection = new AddCommand().run(argument, cityCollection, username, myDatabase);break;
                case "remove_by_id":response =  new Remove_by_idCommand().run(argument, cityCollection, username);break;
                case "remove_last": response = new Remove_lastCommand().run(cityCollection, myDatabase, username);break;
                case "update": {response =  new UpdateCommand().run(argument, cityCollection, username, myDatabase);break;}
                case "execute_script" : response = new Execute_scriptCommand().run(argument,cityCollection, username, password, myDatabase);break;
                case "remove_any_by_climate" :response= new Remove_any_by_climateCommand().run(argument, cityCollection, username);break;
                case "filter_greater_than_car_code" :response =  new Filter_greater_than_car_codeCommand().run(argument, cityCollection);break;
                case "insert_at_help" :{response= "Введите значение индкса.Максимально возможное значение: " + (cityCollection.size()+1);break;}
                case "shuffle" : cityCollection = new ShuffleCommand().run(cityCollection, myDatabase, username);break;
                case "insert_at" : cityCollection = new Insert_atCommand().run(argument, cityCollection, myDatabase, username);break;
                default: response ="Неопознанная команда. Введите 'help' для просмотра доступных команд";
            }
        }else {response = "Соединение с бд уже было закрыто каким-то хером";}

        }catch (PSQLException e){
        response = "Выполнение команд невозможно по причине потери связи с базой данных";
    }
        return cityCollection;
    }
}

