import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 30.03.2019.
 */
public class Matrix {
    public static void main(String[] args) {
        int[][] array = {   {1,0,3,7},
                         {4,-5,6,7,7},
                          {4,-12,8,9},
                         {99,23,23,23}
        };
        System.out.println("Элементы главной диагонали ");
        // 5.Распечатайте (выведите) элементы главной диагонали
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if (i == j) {
                    System.out.print("\t" + array[i][j]);
                }

            }
        }
        System.out.println("\nМатрица ");
        // 6. Распечатайте матрицу (двумерный массив)
        for (int[] arr : array) {
            for (int v : arr) {
                System.out.print("\t" + v);
            }
            System.out.println("");
        }

        System.out.println("\nэлемент матрицы должен быть больше суммы индексов i + j ");
        List<Integer> ints = new ArrayList<>();
        System.out.println(ints.toString());
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if (i + j < array[i][j]) {
                    ints.add(array[i][j]);
                }

            }
        }
        System.out.println(ints.toString());

        System.out.println("Побочная диагональ через один цикл");
        for (int i = 0; i < array.length; i++) {
            if (i < array[i].length && array[i][array[i].length - i - 1]>i*2) {
                System.out.println(array[i][array[i].length - i - 1]);
            }
        }

        System.out.println("Главная диагональ через один цикл");
        for (int i = 0; i < array.length; i++) {
            if (i < array[i].length) {
                System.out.println(array[i][i]);
            }
        }


    }
}
