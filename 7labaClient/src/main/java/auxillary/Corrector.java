package auxillary;

import java.util.Scanner;

public class Corrector {
    /** Метод для подтверждения, что коллекция введена верно
     * @return возвращает целочисленное значение, необходимое для введения ответа
     */
    public  int getInt() {
        int validNumberEntered = 0;
        Scanner scan = new Scanner(System.in);
        boolean flag = true;
        while (flag) {
            if (scan.hasNextInt()) {
                validNumberEntered = scan.nextInt();
                flag = false;
            } else {
                System.out.println("(int) Ведите либо 1, либо 2!");
                scan.next();
            }
        }
        return validNumberEntered;

    }
    public static void enter(boolean flag) throws Exception {

        while (flag) {
            int up = new Corrector().getInt();
            switch (up) {
                case 1:
                    System.out.println("Элемент отправлен на сервер!");
                    flag = false;
                    break;
                case 2: {
                    System.out.println("Начинаем заново.");
                    CommandChecker.commandFin = new Entries().getData("add");
                }
                default:
                    System.out.println("Ведите либо 1, либо 2!");
                    break;
            }
        }

    }

}
