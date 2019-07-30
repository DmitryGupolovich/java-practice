package task12;

import java.util.Arrays;
import java.util.Random;

public class MergeSortThreaded {

    private static void runSort(int[] array, int countThread) throws InterruptedException {
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
                    i * array.length / countThread - 1,
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
            runSort(original, 3);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}
