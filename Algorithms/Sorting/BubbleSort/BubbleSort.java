package DataStructureImplementation.Algorithms.Sorting.BubbleSort;

public class BubbleSort {

  public void sort(int[] values) {
    bubbleSort(values);
  }

  // Sort the array using bubble sort
  private void bubbleSort(int[] ar) {
    if (ar == null) {
      return;
    }
    for (int i = ar.length - 1; i >= 0; i--) {
      for (int j = 0; j + 1 <= i; j++) {
        if (ar[j + 1] < ar[j]) {
          swap(ar, j, j + 1);
        }
      }
    }
    return;
  }

  private void swap(int[] ar, int i, int j) {
    int tmp = ar[i];
    ar[i] = ar[j];
    ar[j] = tmp;
  }

  public static void main(String[] args) {
    int[] arr = {10,4,6,8,13,2,3};
    BubbleSort sorter = new BubbleSort();
    sorter.sort(arr);
    // Prints:
    // [2, 3, 4, 6, 8, 10, 13]
    System.out.println(java.util.Arrays.toString(arr));
  }
}