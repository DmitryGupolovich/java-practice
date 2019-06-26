import java.util.Arrays;

/**
 * Created by User on 16.06.2019.
 */
public class Main {

    public static void merge(
            int[] array, int[] low, int[] high, int left, int right) {

        int i = 0, j = 0, k = 0;
        while (i < left && j < right) {
            if (low[i] <= high[j]) {
                array[k++] = low[i++];
            } else {
                array[k++] = high[j++];
            }
        }
        while (i < left) {
            array[k++] = low[i++];
        }
        while (j < right) {
            array[k++] = high[j++];
        }
    }

    public static void mergeSort(int[] array, int len) {
        if (len < 2) {
            return;
        }
        int mid = len / 2;
        int[] low = new int[mid];
        int[] high = new int[len - mid];

        for (int i = 0; i < mid; i++) {
            low[i] = array[i];
        }
        for (int i = mid; i < len; i++) {
            high[i - mid] = array[i];
        }
        mergeSort(low, mid);
        mergeSort(high, len - mid);

        merge(array, low, high, mid, len - mid);
    }

    public static void main(String[] args) throws InterruptedException {


        int[] actual = new int [10000000];
        for (int i = 0; i < 10000000 ; i++) {
            actual[i] = (int) (Math.random()*50000000)+1;

        }
        long startTime = System.currentTimeMillis();

        mergeSort(actual, actual.length);
        System.out.println(Arrays.toString(actual));
    }
}
