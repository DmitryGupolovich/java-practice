import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // write your code here
        int[] mas = new int[5];
        int sum = 0;
        int temp = 0;

        Scanner src = new Scanner(System.in);

        for (int i = 0; i <= mas.length - 1; i++) {
            System.out.print("Введите " + (++temp) + " элемент массива = ");
            mas[i] = src.nextInt();
        }
        for (int i = 0; i <= mas.length - 1; i++) {
            sum += mas[i];
        }
        System.out.println("Сумма чисел массива равна " + sum);
    }
}

