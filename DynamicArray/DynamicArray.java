/* Using static arrays for an automatically resizing  
 * dynamic array */

public class DynamicArray <T> {

	//Declare member variables as private so they cannot be modified outside
	private T[] arr;
	private int size;
	private int capacity;

	//Constructor
	public DynamicArray(int maxCapacity){
		//check if maxCapacity is a valid input
		if (maxCapacity < 0) 
			throw new IllegalArgumentException("Capacity must be a number greater or equal to zero");
		arr = (T[])new Object[maxCapacity];
		size = 0;
		capacity = maxCapacity;
	}

	//Simple getters
	public int getSize(){
		return size;
	}

	public int getCapacity(){
		return capacity;
	}

	public boolean isEmpty(){
		return (size == 0);
	}

	public T getAt(int i){
		if (i < 0 || i >= size)
			throw new IndexOutOfBoundsException("Index out of bounds");
		return arr[i];
	}

	public void push(T obj){
		//If the size exceeds capacity after insertion
		if (size + 1 > capacity){
			//Deal with cornercase of capacity being 0 and thus the new
			//capacity being 0 as well
			if (capacity == 0)
				capacity = 1;
			//Create new array of double the previous capacity
			capacity *= 2;
			resize(capacity);
		}
		arr[size++] = obj;
	}

	private void resize(int newCapacity){
		T[] tempArr = (T[])new Object[newCapacity];
		for (int i=0;i < size;i++){
			tempArr[i] = arr[i];
		}
		arr = tempArr;
	}

	public void insertAt(int i, T obj){
		//Deal with invalid index argument such as when it is negative or it is out of bounds by being
		//greater than size
		if (i < 0 || i > size)
			throw new IndexOutOfBoundsException("Invalid index");
		//If the size exceeds capacity after insertion, create new array of bigger capacity
		if (size + 1 > capacity){
			if (capacity == 0)
				capacity = 1;
			capacity *= 2;
			resize(capacity);
		}
		T[] tempArr = (T[])new Object[capacity];
		size++;
		for (int j = 0, k = 0;j < size;j++,k++){
			if (k == i){
				tempArr[k] = obj;
				j++;
			}
			else{
				tempArr[k] = arr[j];
			}
		}
	}

	public void prepend(T obj){
		//Inserting at the front
		insertAt(0, obj);
	}

	public T removeAt(int i){
		if (i < 0 || i >= size)
			throw new IndexOutOfBoundsException();
		//New array of size = size - 1
		T[] tempArr = (T[])new Object[size-1];
		T res = arr[i];
		for (int j=0,k=0;j<size;j++,k++){
			//When copying elements in the array, negate the index that is to be deleted
			if (j == i){
				k++;
				if (j < size-1)
					tempArr[j] = arr[k];
			}
			//Just usual copying
			else{
				tempArr[j] = arr[k];
			}
		}
		arr = tempArr;
		capacity = --size;
		return res;
	}

	public T pop(){
		return removeAt(size-1);
	}

	public int find(T obj){
		for (int i=0;i < size;i++){
			if (obj.equals(arr[i]))
				return i;
		}
		return -1;
	}

	public T remove(T obj){
		int index = find(obj);
		T res = obj;
		//removes all duplicates as well
		while (index != -1){
			res = removeAt(index);
			index = find(obj);
		}
		return res;

	}

}


