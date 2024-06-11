package data;

import java.util.concurrent.RecursiveTask;
import java.util.Random;

@SuppressWarnings("serial")
public class ConcurrentQuickSort extends RecursiveTask<Void> {
    private int[] array;
    private int start;
    private int end;

    private int partition(int start, int end, int[] arr) {
        int i = start, j = end;
        int pivoted = new Random().nextInt(j - i) + i;
        int t = arr[j];
        arr[j] = arr[pivoted];
        arr[pivoted] = t;
        j--;

        while (i <= j) {
            if (arr[i] <= arr[end]) {
                i++;
                continue;
            }

            if (arr[j] >= arr[end]) {
                j--;
                continue;
            }

            t = arr[j];
            arr[j] = arr[i];
            arr[i] = t;
            j--;
            i++;
        }

        t = arr[j + 1];
        arr[j + 1] = arr[end];
        arr[end] = t;
        return j + 1;
    }

    public ConcurrentQuickSort(int[] array, int start, int end) {
        this.array = array;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Void compute() {
        if (start >= end || array == null || array.length == 0)
            return null;

        if (start < 0 || end >= array.length)
            return null;

        int p = partition(start, end, array);

        ConcurrentQuickSort left = new ConcurrentQuickSort(array, start, p - 1);
        ConcurrentQuickSort right = new ConcurrentQuickSort(array, p + 1, end);

        left.fork();
        right.compute();

        left.join();

        return null;
    }
}