package auxiliary;

import City.City;
import commands.Remove_by_idCommand;
import commands.SaveCommand;

import java.util.*;
import java.util.stream.Collectors;

public class IdChecker {

     /**Проверяет все элементы на уникальность id, предлагает удалить повторы
     * @param cityCollection - имя файла где находятся элементы коллекции
     */

    public static String check(Stack<City> cityCollection ) throws Exception {

        Iterator<City> iterator = cityCollection.iterator();
        Messager check = new Messager();
        boolean flag = true;
        Long array[] = new Long[cityCollection.size()];

            while (iterator.hasNext()) {
                for (int i = 0; i < cityCollection.size(); i++) {
                    long id = iterator.next().getId();
                    array[i] = id;
                }
            }
         List<Long> idArray = Arrays.asList(array);

        for(int i=0; i<cityCollection.size(); i++) {
            for (int j=i+1; j<cityCollection.size(); j++) {

             if(Objects.equals(array[i], array[j])) {

                check.println("Найдено повторение id перед позицией " + j+"\n" +
                        Arrays.toString(array)+"\n"+
                        "удалить элемент?\n"+ " \"1\"-да, элемент будет удален\n \"2\"-нет, завершить работу программы и исправить неполадки вручную", flag);

                boolean error = true;

                cityCollection.stream().distinct().collect(Collectors.toList());
                 int finalJ = j;
                 cityCollection.stream().filter((c)-> c.getId() != finalJ).collect(Collectors.toList());

                while (error) {
                    int up = Corrector.getInt();
                    switch (up) {
                        case 1:
                            new Remove_by_idCommand().run(String.valueOf(array[i]), cityCollection);
                            new SaveCommand().run("", cityCollection);
                            error = false;
                            break;
                        case 2: {
                            check.println("Надеюсь, вы устраните неполадки", true);
                            System.exit(0);
                        }
                        default:
                            check.println("Ведите либо 1, либо 2!", true);
                            break;
                    }
                }

            }
        }
    }

        return "";
    }
}

