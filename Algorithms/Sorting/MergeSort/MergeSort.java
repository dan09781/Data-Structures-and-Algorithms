package DataStructureImplementation.Algorithms.Sorting.MergeSort;

import java.util.*;

public class MergeSort {
    public void sort(int[] values) {
         int[] newArr = mergeSort(values);
         for (int i=0;i<newArr.length;i++){
             values[i] = newArr[i];
         }
    }

    private static int[] mergeSort(int[] arr) {
        //Base case
        if (arr==null || arr.length<=1){
            return arr;
        }
        int n = arr.length;
        int[] left = Arrays.copyOfRange(arr, 0, n/2);
        int[] right = Arrays.copyOfRange(arr, n/2, n);

        int[] partitionLeft = mergeSort(left);
        int[] partitionRight = mergeSort(right);

        int[] res = merge(partitionLeft, partitionRight);
        return res;
    }

    private static int[] merge(int[] left, int[] right){
        int n = left.length+right.length;
        int l=0;
        int r=0;
        int i=0;
        int[] res = new int[n];
        while (l<left.length && r<right.length){
            if (left[l] < right[r]){
                res[i] = left[l];
                l++;
                i++;
            }
            else{
                res[i] = right[r];
                r++;
                i++;
            }
        }

        //If end of left array had been reached, copy the rest of right array
        if (r<right.length){
            while (r<right.length){
                res[i] = right[r];
                i++;
                r++;
            }
        }
        else if (l<left.length){
            while (l<left.length){
                res[i] = left[l];
                i++;
                l++;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        MergeSort sorter = new MergeSort();
        int[] array = {10, 4, 6, 4, 8, -13, 2, 3, -20,5,3,40,23,12,-13,-15};
        sorter.sort(array);
        // Prints:
        // [-13, 2, 3, 4, 4, 6, 8, 10]
        System.out.println(Arrays.toString(array));
    }
}
