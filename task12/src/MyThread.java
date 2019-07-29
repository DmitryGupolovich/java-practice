package task12;


class MyThread extends Thread {
    private int[] internal;
    private int low;
    private int high;
    private int[] temp;
    private void mergeSort(int[] array, int low, int high) {
        if (low < high) {

            int middle = low + (high - low) / 2;

            mergeSort(array, low, middle);
            mergeSort(array, middle + 1, high);

            merge(array, low, middle, high);
            // mergeSecond(array, low, middle, high);

        }
    }

    public void merge(int[] result, int low, int middle, int high) {
        int n = high - low + 1;

        int i = low;
        int j = middle + 1;
        int k = 0;

        while (i <= middle || j <= high) {
            if (i > middle)
                temp[k++] = result[j++];
            else if (j > high)
                temp[k++] = result[i++];
            else if (result[i] < result[j])
                temp[k++] = result[i++];
            else
                temp[k++] = result[j++];
        }
        for (j = 0; j < n; j++)
            result[low + j] = temp[j];
    }

    public void mergeSecond(int[] result, int low, int middle, int high) {

        int l = middle - low + 1;
        int r = high - middle;

        int[] leftArray = new int[l];
        int[] rightArray = new int[r];

        for (int i = 0; i < l; ++i)
            leftArray[i] = result[low + i];

        for (int j = 0; j < r; ++j)
            rightArray[j] = result[middle + 1 + j];

        int i = 0, j = 0;
        int k = low;
        while (i < l && j < r) {
            if (leftArray[i] <= rightArray[j]) {
                result[k] = leftArray[i];
                i++;
            } else {
                result[k] = rightArray[j];
                j++;
            }
            k++;
        }
        while (i < l) {
            result[k] = leftArray[i];
            i++;
            k++;
        }

        while (j < r) {
            result[k] = rightArray[j];
            j++;
            k++;
        }
    }

    MyThread(int[] arr, int low, int high) {
        this.internal = arr;
        this.low = low;
        this.high = high;
        this.temp = new int[arr.length];
    }

    @Override
    public void run() {
        mergeSort(internal, low, high);
    }
}