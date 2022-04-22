package auxillary;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Класс для уточнения данных, введенных пользователем
 */

public class CommandChecker {

    public static String commandFin;

    public String check(String command) {

        BufferedReader n = new BufferedReader(new InputStreamReader(System.in));

        try {
                if (command.equals("exit")) {
                    System.out.println("Завершение работы");
                    commandFin = "save";
                    System.exit(0);
                }
                if (command.trim().equals("filter_greater_than_car_code")) {
                    System.out.println("Введите значение car code: \n(long)");
                    commandFin = "filter_greater_than_car_code " + n.readLine();
                }
                if (command.trim().equals("remove_by_id")) {
                    System.out.println("Введите id элемента: ");
                    commandFin = "remove_by_id " + n.readLine();
                }

                if (command.trim().equals("remove_any_by_climate")) {
                    System.out.println("Выберите значение Climate \n[MONSOON, OCEANIC, MEDITERRANIAN, STEPPE]: ");
                    commandFin = "remove_any_by_climate " + n.readLine();
                }

                if (command.trim().equals("add")) {
                        commandFin = new Entries().getData("add");
                    if (!commandFin.equals("err")) {
                        System.out.println("Ваша коллекция:\n" + commandFin +
                                "\nВерно?\n \"1\"-да, все верно\n \"2\"-нет, ввести заново");
                        boolean flag = true;
                        Corrector.enter(flag);
                    }
                }

                if (command.trim().equals("update")) {
                    System.out.println("Введите id элемента: ");
                    String idUpdate = n.readLine();
                    commandFin = new Entries().getData("update " + idUpdate + ",");
                    if (!commandFin.equals("err")) {
                        System.out.println("Ваша коллекция:\n" + commandFin +
                                "\nВерно?\n \"1\"-да, все верно\n \"2\"-нет, ввести заново");
                        boolean flag = true;
                        Corrector.enter(flag);
                    }
                }
/*
                if (command.trim().equals("insert_at")) {

                    System.out.println("Введите индекс позиции элемента:");
                    String index = "insert_at " + n.readLine();
                    commandFin = new Entries().getData(index + ",");
                    //commandFin = "insert_at 4, f, 5 6, 2022-03-26T06:43:00.413, 56, 56, 56, 56, OCEANIC, HIGH, 6.0"; //ТЕСТЫ
                    if (!commandFin.equals("err")) {
                        System.out.println("Ваша коллекция:\n" + commandFin +
                                "\nВерно?\n \"1\"-да, все верно\n \"2\"-нет, ввести заново");
                        boolean flag = true;
                        Corrector.enter(flag);
                    }
                }

 */
        } catch (
                Exception e) {
            e.printStackTrace();
        }

        return commandFin;

    }

}

