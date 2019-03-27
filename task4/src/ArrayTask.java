/**
 * Created by User on 26.03.2019.
 */
public class ArrayTask {
    public static void main(String[] args) {
        int[] array  = {1, 4, 2, -5, 0, 6};
        byte count=0;
        for (int val:array
             ) {
            if (val<0) count++;
        }
        System.out.printf("Количество отрицательных элементов %d",count);
    }
}
