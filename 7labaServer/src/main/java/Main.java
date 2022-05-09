import City.City;
import auxiliary.IdChecker;
import connection.ServerManager;
import database.ConnectToDB;
import database.DatabaseManager;

import java.sql.Connection;
import java.util.Stack;

// вариант 665580

public class Main {

    public static void main(String[] args) throws Exception {

        if (args.length != 1) {
            System.out.println("Пожалуйста, запускайте только с именем файла коллекции!");
        }
        Connection myDatabase = null;


        try {
            myDatabase = new ConnectToDB().connect(myDatabase);
            Stack<City> cityCollection = DatabaseManager.loadCollection(myDatabase);

            System.out.println("Коллекция загружена");

        while (true) {

            new IdChecker();
            IdChecker.check(cityCollection);
            try {
                ServerManager server = new ServerManager(9890);
                server.starting(cityCollection, myDatabase);
            }catch (Exception e){
                System.out.println(e +"\n =======================");break;
            }

            }
        }catch (Exception e){
            e.printStackTrace();
        }
        }
    }


