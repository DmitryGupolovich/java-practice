import java.util.Scanner;

class ArrayTask {

    public static void main(String[] args) {
        // write your code here
        boolean bool = true;
        Scanner src = new Scanner(System.in);
        while (bool) {
        System.out.print("Введите размерность массива ");
        int masSize = src.nextInt();
            if (Integer.signum(masSize) == -1 || Integer.signum(masSize) == 0) {
                System.out.println("Массив не может быть отрицательным или нулевым. Повторите ввод");
                bool=true;
            } else {
                bool=false;
                int[] mas = new int[masSize];
                int sum = 0;
                int temp = 0;

                for (int i = 0; i <= mas.length - 1; i++) {
                    System.out.print("Введите " + (++temp) + " элемент массива = ");
                    mas[i] = src.nextInt();
                }
                for (int i = 0; i <= mas.length - 1; i++) {
                    sum += mas[i];
                }
                System.out.println("Сумма элементов массива равна " + sum);
                System.out.printf("Среднее арифметическое чисел массива равно %.2f",(double)sum / mas.length);
            }
        }
    }
}

