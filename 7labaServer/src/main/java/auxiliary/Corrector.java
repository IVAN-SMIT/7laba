package auxiliary;

import java.util.Scanner;

public class Corrector {
    /** Метод для подтверждения, что коллекция введена верно
     * @return возвращает целочисленное значение, необходимое для введения ответа
     */
    public static int getInt() {
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

}
