package database;

import java.io.FileInputStream;
import java.sql.*;
import java.util.Properties;

public class TableCreator {
    private static Connection connection;

    public static void TableCreator() {
        Connection c = null;
        PreparedStatement stmt = null;

        String CreateSql = null;
        try {

            Class.forName("org.postgresql.Driver");
            Properties info = new Properties();
            info.load(new FileInputStream("src/main/java/db.cfg"));
            c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", info);
            //          info.load(new FileInputStream("src/main/java/info_helios.cfg"));
//            c = DriverManager.getConnection("jdbc:postgresql://pg:5432/studs", "s", "");

            connection = c;
            System.out.println("Database Connected ..");

            CreateSql = "create table LabWorks\n" +
                    "(\n" +
                    "    id                       SERIAL PRIMARY KEY,\n" +
                    "    name                     varchar(256),\n" +
                    "    x                        float8,\n" +
                    "    y                        INT,\n" +
                    "    creation_date            TIMESTAMP WITH TIME ZONE,\n" +
                    "    minimalPoint             float8,\n" +
                    "    personalQualitiesMinimum INT,\n" +
                    "    difficulty               varchar(20),\n" +
                    "    author_name              varchar(256),\n" +
                    "    birthday                 date,\n" +
                    "    weight                   float8,\n" +
                    "    eye_color                varchar(20),\n" +
                    "    login_id                    int\n" +
                    ");";
            stmt = c.prepareStatement(CreateSql);
            stmt.executeUpdate();
            System.out.println("LabWorks' таблица создана");
//            stmt.close();
//
//            c.close();

        } catch (Exception e) {
            if (e.getMessage().contains("already exists"))
                System.out.println("LabWorks' table already exists");
            else {
                System.err.println(e.getClass().getName() + ": " + e.getMessage());

                System.exit(0);
            }
        }
        try {

            Class.forName("org.postgresql.Driver");

            // c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "kpop");


            CreateSql = "Create Table Users(id SERIAL PRIMARY KEY, login varchar unique, password varchar ) ";
            System.out.println("User table created");
            stmt = c.prepareStatement(CreateSql);
            stmt.executeUpdate();

        } catch (Exception e) {
            if (e.getMessage().contains("already exists"))
                System.out.println("Такая таблица уже существует");
            else {
                System.out.println(e.getClass().getName() + ": " + e.getMessage());

                System.exit(0);
            }
        }

        System.out.println("SQL tables are ready to use\n");
    }
}
/*
CREATE TABLE cityCollection (
   id serial PRIMARY KEY,
   name VARCHAR (50) NOT NULL,
   coordinates VARCHAR (50) NOT NULL,
   localDate VARCHAR (50) NOT NULL,
   area VARCHAR (50) NOT NULL,
   population VARCHAR (50) NOT NULL,
   metersAboveSeaLevel VARCHAR (50) NOT NULL,
   carCode VARCHAR (50) NOT NULL,
   climate VARCHAR (50) NOT NULL,
   standardOfLiving VARCHAR (50) NOT NULL,
   governor VARCHAR (50) NOT NULL,
   username VARCHAR (100) NOT NULL
 );


CREATE TABLE users(
	username VARCHAR (50)NOT NULL,
	password VARCHAR (250)NOT NULL
	);


 INSERT INTO cityCollection(id, name, coordinates,localDate, area, population, metersAboveSeaLevel,
                                                         carCode, climate, standardOfLiving, governor, username)
                                     VALUES(774,12,60,2022-04-22,324,23,23,23,'OCEANIC','HIGH',1.0, 'op')



         SELECT * FROM cityCollection;
 */

