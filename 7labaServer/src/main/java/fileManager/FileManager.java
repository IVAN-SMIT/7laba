package fileManager;

import City.City;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;

import java.io.*;

import java.util.Stack;


public class FileManager {
    private static String fileName;
    public static String date;

    private static ObjectMapper mapper = new ObjectMapper();

    /**
     * Управляет записью и чтением коллекции из файла
     * @param fileName имя файла
     */

    public  String read(String fileName) {
        if (fileName == null){fileName = "cityCollection";}
        try {
            BufferedReader stream = new BufferedReader(new FileReader(fileName));
            int bufferSize = 2048;
            char[] buffer = new char[bufferSize];
            StringBuilder out = new StringBuilder();
            Reader in = new BufferedReader(stream);
            for (int numRead; (numRead = in.read(buffer, 0, buffer.length)) > 0; ) {
                out.append(buffer, 0, numRead);
            }
            in.close();
            return out.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return "error";
        }
    }

    /**
     *  Загружает коллекции с файла
     * @param fname имя файла
     * @return cityCollection
     */

    public Stack<City> loadCollection(String fname) throws JsonProcessingException, FileNotFoundException {

        fileName = fname;
        date = java.time.LocalDateTime.now().toString();

        File file = new File(fileName);

        try {
            if (!file.exists()) {
                file.createNewFile();
                System.out.println("Такого файла нет! Создан новый.");
            }
        } catch (IOException e) {
            System.out.println("Ошибка!");
        }

        String jsonCity = new FileManager().read(fileName);

        Stack<City> cityCollection = new Stack<>();
        mapper.registerModule(new JSR310Module());
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

        try {

            cityCollection = mapper.readValue(jsonCity, new TypeReference<Stack<City>>() {
            });


        } catch (IOException y) {
            System.out.println("Файл поврежден или в нем отсутствуют элементы!");
        }
        return cityCollection;
    }

    /**
     * метод для сохранений коллекций
     *
     * @param cityCollection коллекция
     * @param pathToSave путь сохранения
     */

    public void saveCollection(Stack<City> cityCollection, String pathToSave){
        String path ;
        String result = "";
        String time = null;

        try {
            result = mapper.writeValueAsString(cityCollection);


        } catch (JsonProcessingException ex) {
            System.out.println("Не удалось перевести коллекцию в Json.");
            ex.printStackTrace();
        }

        try {
            if (pathToSave != null) {
                path = pathToSave;
            } else {
                path = fileName;
            }

            FileOutputStream fOut = new FileOutputStream(fileName);
            PrintWriter myOutWriter = new PrintWriter(fOut);
            myOutWriter.append(result);
            myOutWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
