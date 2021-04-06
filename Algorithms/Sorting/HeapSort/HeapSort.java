package DataStructureImplementation.Algorithms.Sorting.HeapSort;

import java.util.*;

public class HeapSort{

  public void sort(int[] values) {
    heapSort(values);
  }

  private static void heapSort(int[] arr) {
    if (arr == null || arr.length == 1) return;
    int n = arr.length;

    //Build max heap in O(n) time
    //n/2-1 is the index of the first non-leaf node
    for (int i=(n/2)-1;i>=0;i--){
      bubbleDown(arr, n, i);
    }

    // Remove the largest element (root of heap)
    // and treat the end of array as sorted portion
    // notice the size of array being passed into bubbleDown gets decremented every iteration
    for (int i = n - 1; i >= 0; i--) {
      swap(arr, 0, i);
      bubbleDown(arr, i, 0);
    }
  }

  private static void bubbleDown(int[] arr, int n, int i) {
    while (i>=0&&i<n){
      int left = 2*i+1;
      int right = 2*i+2;
      int maxIndex;
      //if both are valid indices
      if (left < n && right < n){
        //if node left >= node right
        //Using the compareTo method from Comparable interface
        if (arr[left] > arr[right]){
          maxIndex = left;
        }
        else
          maxIndex = right;
      }
      //if only left index is valid
      else if (left < n)
        maxIndex = left;
        //if only right index is valid
      else if (right < n)
        maxIndex = right;
      else
        //if no index is valid, just return since it means i is a leaf node
        return;
      //if the parent node is smaller than the greater of the children, swap
      if (arr[i] < arr[maxIndex]){
        swap(arr, maxIndex, i);
      }
      //if parent node is greater than greater of the children, done bubbling down.
      //Return out of fun-n
      else{
        return;
      }
      i = maxIndex;
    }
  }

  private static void swap(int[] arr, int i, int j) {
    int tmp = arr[i];
    arr[i] = arr[j];
    arr[j] = tmp;
  }

  public static void main(String[] args) {
    HeapSort sorter = new HeapSort();
    int[] array = {10, 4, 6, 4, 8, -13, 2, 3, -20,5,3,40,23,12,-13,-15};
    sorter.sort(array);
    // Prints:
    // [-13, 2, 3, 4, 4, 6, 8, 10]
    System.out.println(Arrays.toString(array));
  }
}