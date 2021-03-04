//Min Priority Queue implementation with a binary min heap

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

	//insert value onto heap and bubble up to satisfy heap invariant
	//not accepting null
	public void insert(T elem){
		if (elem == null)
			throw new IllegalArgumentException("Elements in the heap can't be null!");

		//Add the element to the end of heap
		heap.add(elem);
		//Bubble up until heap invariant satisfied
		bubbleUp(heap.size()-1);
	}

	//Removes item at index i
	//In this implementation, we are doing a naive O(n) removal
	//since we are not using hash table.
	//Assume this implementation cannot afford the overhead of a hash table implementation
	public T removeAt(int i){
		if (isEmpty())
			throw new RuntimeException("Empty priority queue!!");
		if (i<0||i>=heap.size())
			throw new IndexOutOfBoundsException();
		//Swap the node to be removed with the last node
		T data = heap.get(i);
		swap(i,heap.size()-1);

		//remove the last node which is now the node that we want removed
		heap.remove(heap.size()-1);

		//Bubble down
		bubbleDown(i);

		return data;

	}

	//Removes the first occurence of elem
	//Don't do anything if elem does not exist in heap
	public T remove(T elem){
		if (isEmpty())
			throw new RuntimeException("Empty priority queue!!");
		int i = heap.indexOf(elem);
		T data = null;
		if (i>=0){
			data = removeAt(i);
		}
		return data;
	}

	//Some trivial getters and simple functions
	public T getMin(){
		return heap.get(0);
	}

	public int getSize(){
		return heap.size();
	}

	public boolean isEmpty(){
		return heap.size()==0;
	}


	//Similar to getMin() but in this function, we actually remove the min
	public T extractMin(){
		if (isEmpty())
			throw new RuntimeException("Priority queue is empty!!");
		T data = removeAt(0);
		return data;
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

	private void bubbleUp(int i){
		int parent = (i-1)/2;
		int heapSize = heap.size();
		while (parent>=0&&parent<heapSize){
			//if parent is less than the current node, we're done. Return out of fun-n
			if (heap.get(parent).compareTo(heap.get(i))<=0)
				return;
			//if parent is larger, swap
			swap(parent,i);
			//Set new parent index value
			parent=(parent-1)/2;
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