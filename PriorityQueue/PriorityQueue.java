//Priority Queue implementation with a binary min heap
import java.util.*;


//T is the class or object that needs to be comparable. Thats why T extends comparable 
public class PriorityQueue<T extends Comparable<T>> implements Iterable<T>{
	private List<T> heap;

	//Constructors
	//Offering different ways to initialize PQ

	//With the user providing an already existing collection of elements
	PriorityQueue(List<T> arr){
		int heapSize = arr.size();
		heap = new ArrayList<T>(heapSize);
		//For collections, we can just do this
		heap.addAll(arr);

		//For array we have to do this.
		/*for (int i=0;i<heapSize;i++){
			heap.add(arr[i]);
		}*/

		//Build heap in O(n) time. This function builds min heap in worst cast O(n) time from going bottom-up.
		//Considering all leaf nodes satisfy heap invariant, we can ignore them altogether as they won't be bubbling
		//down at all. Since leaf nodes take up ~n/2 nodes, we can conclude that those ~n/2 nodes require 0 work. 
		//Using this pattern, we see that there are ~n/(2^(j+1)) nodes where j is the height of the heap from bottom-up.
		//So ~n/2 nodes require 0 work, ~n/4 nodes require at most 1 work, ~n/8 nodes require at most 2 works and so on
		//Using this notion and some good old calc, the time complexity converges to O(n)
		//(heapsize/2)-1 gives us the index of the first non-leaf node starting from the end of the heap
		for (int i=(heapSize/2)-1;i>=0;i--){
			bubbleDown(i);
		}
	}

	//Bubbling down to satisfy heap invariant
	private void bubbleDown(int i){
		int heapSize = heap.size();
		while (i>=0&&i<heapSize){
			int left = 2*i+1;
			int right = 2*i+2;
			int minIndex;
			//if both are valid indices
			if (left < heapSize && right < heapSize){
				//if node left <= node right
				//Using the compareTo method from Comparable interface
				if (heap.get(left).compareTo(heap.get(right)) <= 0){
					minIndex = left;
				}
				//if node right <= node left
				else
					minIndex = right;
			}
			//if only left index is valid
			else if (left < heapSize)
				minIndex = left;
			//if only right index is valid
			else if (right < heapSize)
				minIndex = right;
			else
			//if no index is valid, just return since it means i is a leaf node
				return;
			//if the parent node is greater than the smallest of the children, swap
			if (heap.get(minIndex).compareTo(heap.get(i)) <= 0){
				swap(minIndex, i);
			}
			//if parent node is smaller than smallest of the children, done bubbling down.
			//Return out of fun-n
			else{
				return;
			}
			i = minIndex;
		}	
	}

	private void swap(int i, int j){
		T temp1 = heap.get(i);
		T temp2 = heap.get(j);
		heap.set(i,temp2);
		heap.set(j,temp1);
	}


	//public boolean isMinHeap(){	
//
//	}

	@Override public java.util.Iterator<T> iterator(){
		return new java.util.Iterator<T> (){

			//This whole thing is definition of custom iterator class
			int i=0;
			int heapSize=heap.size();
			@Override public boolean hasNext(){
				return i<heapSize;
			}
			@Override public T next(){
				T data = heap.get(i);
				i++;
				return data;
			}
		};
	}
}