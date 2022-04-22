package auxiliary;

import City.City;
import commands.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Map;
import java.util.Objects;
import java.util.Stack;
import java.util.stream.Collectors;


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

    
    public static Stack<City> readCommand(Stack<City> cityCollection) throws Exception {

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
        if (!Objects.equals(command, "insert_at_help")){
            history.add(command);
        }
       


        System.out.println("Распознана команда: "+command+"\nargument команды: " + argument+ "\n");
        switch (command) {
            case"help": response = new HelpCommand().run();break;
            case "exit":  new SaveCommand().run();break;
            case "info":response = new InfoCommand().run(argument, cityCollection);break;
            case "3137best": pashalOchka.run(argument);break;// интересно, а что же это......
            case "history": response = new HistoryCommand().run(argument, history);break;
            case "show": response = new ShowCommand().run(argument, cityCollection);break;
            case "clear": response = new  ClearCommand().run(argument, cityCollection);break;
            case "add":  response = new AddCommand().run(argument, cityCollection);break;
            //case "save":  response = new SaveCommand().run(argument, cityCollection);break;
            case "remove_by_id":response =  new Remove_by_idCommand().run(argument, cityCollection);break;
            case "remove_last": response = new Remove_lastCommand().run(cityCollection);break;
            case "update": {response =  new UpdateCommand().run(argument, cityCollection);break;}
            case "execute_script" : response = new Execute_scriptCommand().run(argument, cityCollection);break;
            case "remove_any_by_climate" :response= new Remove_any_by_climateCommand().run(argument, cityCollection);break;
            case "filter_greater_than_car_code" :response =  new Filter_greater_than_car_codeCommand().run(argument, cityCollection);break;
            case "insert_at_help" :{response= "Введите значение индкса.Максимально возможное значение: " + (cityCollection.size()+1);break;}
            case "shuffle" : response = new ShuffleCommand().run(cityCollection);break;
            case "insert_at" : response = new Insert_atCommand().run(argument, cityCollection);break;
            default: response ="Неопознанная команда. Введите 'help' для просмотра доступных команд";
        }
        return cityCollection;

    }
}

