package DataStructureImplementation.Algorithms.Sorting.QuickSort;

import java.util.*;

public class QuickSort {
    public void sort(int[] values) {
        quickSort(values);
    }

    private void quickSort(int[] arr){
        quickSort(arr, 0, arr.length-1);
    }

    private void quickSort(int[]arr, int left, int right){
        if (left >= right)
            return;
        int pivotPos = partition(arr, left, right);
        quickSort(arr, left, pivotPos-1);
        quickSort(arr, pivotPos+1, right);
    }

    private int partition(int[] arr, int left, int right){
        int j=left;
        int i=j-1;

        //Choose pivot to be the last element of the partition
        int pivot = arr[right];
        for (;j<right;j++){
            //element is smaller than pivot
            if (arr[j]<pivot){
                swap(arr, j, ++i);
            }
        }

        //Place the pivot in the correct position
        swap(arr, ++i, right);
        return i;
    }

    private void swap(int[] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        QuickSort sorter = new QuickSort();
        int[] array = {10, 4, 6, 4, 8, -13, 2, 3, -20, 5, 3, 40, 23, 12, -13, -15};
        sorter.sort(array);
        // Prints:
        // [-13, 2, 3, 4, 4, 6, 8, 10]
        System.out.println(Arrays.toString(array));
    }
}
