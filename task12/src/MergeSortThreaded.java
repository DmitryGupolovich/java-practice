import java.util.Random;

public class MergeSortThreaded {

    public static void runSort(int[] array, int countThread) throws InterruptedException {
        long startTime = System.currentTimeMillis();

        MyThread[] t = new MyThread[countThread];

        // start
        for (int i = 0; i < countThread; i++) {

            t[i] = new MyThread(array, i * array.length / countThread, (i + 1) * array.length / countThread - 1); // мы должны передавать границы, с которых будем искать
            t[i].start();
        }
        t[0].join();

        // join
        for (int i = 1; i < countThread; i++) {
            t[i].join();
            t[i].merge(array, 0,
                    i * array.length / countThread -1,
                    (i + 1) * array.length / countThread - 1); // мы должны передавать границы, с которых будем искать
        }


        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;
        System.out.println(" Time takes: " + (float) elapsedTime / 1000 + " seconds");

    }

    public static void main(String[] args) {
        Random rand = new Random();
        int[] original = new int[10000000];
        for (int i = 0; i < original.length; i++) {
            original[i] = rand.nextInt(10000000);
        }
        try {
            runSort(original, 8);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class MyThread extends Thread {
    private int[] internal;
    private int low;
    private int high;

    public void mergeSort(int[] array, int low, int high) {
        if (low < high) {

            int middle = low + (high - low) / 2;

            mergeSort(array, low, middle);
            mergeSort(array, middle + 1, high);

            merge(array, low, middle, high);
        }
    }

    public void merge(int[] result, int low, int middle, int high) {

        int l = middle - low + 1;
        int r = high - middle;

        int LeftArray[] = new int[l];
        int RightArray[] = new int[r];

        for (int i = 0; i < l; ++i)
            LeftArray[i] = result[low + i];

        for (int j = 0; j < r; ++j)
            RightArray[j] = result[middle + 1 + j];

        int i = 0, j = 0;
        int k = low;
        while (i < l && j < r) {
            if (LeftArray[i] <= RightArray[j]) {
                result[k] = LeftArray[i];
                i++;
            } else {
                result[k] = RightArray[j];
                j++;
            }
            k++;
        }
        while (i < l) {
            result[k] = LeftArray[i];
            i++;
            k++;
        }

        while (j < r) {
            result[k] = RightArray[j];
            j++;
            k++;
        }
    }

    MyThread(int[] arr, int low, int high) {
        this.internal = arr;
        this.low = low;
        this.high = high;
    }

    @Override
    public void run() {
        mergeSort(internal, low, high);
    }
}