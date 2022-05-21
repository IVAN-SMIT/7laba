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

        Connection myDatabase = null;
        try {
            myDatabase = new ConnectToDB().connect(myDatabase);
            Stack<City> cityCollection = DatabaseManager.loadCollection(myDatabase);

            System.out.println("Коллекция загружена из базы данных");

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
            System.out.println(e.getClass().getName() + e);
        }
        }
    }


