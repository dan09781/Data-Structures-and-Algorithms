/*** singly linked list implementation without tail pointer ***/

public class SinglyLinkedListWithoutTail <T> implements Iterable <T> { 
	private class Node<T>{
		T data;
		Node<T> next;
		public Node (T data, Node<T> next){
			this.data = data;
			this.next = next;
		}
	}


	Node<T> head;
	int size = 0;

	@Override public java.util.Iterator<T> iterator(){
		return new java.util.Iterator<T> (){

			//This whole thing is definition of custom iterator class
			private Node<T> trav = head;
			@Override public boolean hasNext(){
				return trav != null;
			}
			@Override public T next(){
				T data = trav.data;
				trav = trav.next;
				return data;
			}
		};
	}

	public int getSize(){
		return size;
	}

	public boolean isEmpty(){
		return size == 0;
	}

	public T value_at(int index){
		//Check for index out of bound exception
		if (index < 0 || index >= size)
			throw new IndexOutOfBoundsException("Not a valid index");
		Node<T> trav;
		int i;
		for (i=0, trav=head;i<size;i++){
			if (i==index)
				return trav.data;
			trav=trav.next;
		}
		return null;
	}

	public void push_front(T value){
		Node<T> newNode = new Node<T>(value, head);
		head = newNode;
		size++;
	}

	public T pop_front(){
		//Check if list is empty
		if (isEmpty()){
			throw new RuntimeException("Empty list");
		}
		//If list is not empty, remove head and reassign head
		T data = head.data;
		Node<T> temp = head;
		head = head.next;
		//memory cleanup
		temp.next = null;
		size--;
		return data;
	}



	public void push_back(T value){
		//Check if list is empty
		if (isEmpty())
			//If list is empty, assign head pointer to new node
			head = new Node<T>(value, null);
		else{
			//If list is not empty, iterate to last element and add new node
			Node<T> trav = head;
			//While loop terminates when trav is at the last element
			while (trav.next != null)
				trav=trav.next;
			trav.next = new Node<T>(value,null);
		} 
		size++;
	}

	public T pop_back(){
		//Check if list is empty
		if (isEmpty()){
			throw new RuntimeException("Empty list");
		}
		//If list is not empty, remove tail and reassign tail
		//For sll, we dont have access to the prev node of tail
		//Hence, we have to traverse from head to keep track of prev of tail
		Node<T> trav1, trav2;
		trav1 = trav2 = head;
		//while loop terminates when trav1.next == null. Which is the same as
		//when trav1 is pointing to tail so trav2 would be prev of tail
		while (trav1.next != null){
			trav2 = trav1;
			trav1 = trav1.next;
		}
		trav2.next = null;
		T data = trav1.data;
		size--;
		if (isEmpty())
			head = null;
		return data;
	}

	//Returns value of head
	public T getFront(){
		if (isEmpty()){
			throw new RuntimeException("Empty list");
		}
		return head.data;
	}

	//Returns value of tail
	public T getBack(){
		if (isEmpty()){
			throw new RuntimeException("Empty list");
		}
		Node<T> trav = head;
		while (trav.next != null)
			trav=trav.next;
		return trav.data;
	}

	//Insert value at index
	//Index can be ranged from 0 to size (size is allowed; Same functionality as push_back)
	public void insertAt(int index, T value){
		//Check for index out of bound exception
		if (index < 0 || index > size)
			throw new IndexOutOfBoundsException("Not a valid index");
		else if (index == 0){
			push_front(value);
			return;
		}
		else if (index == size){
			push_back(value);
			return;
		}

		int i;
		Node<T> trav;
		//2 options to do this
		//1. using 2 pointers to keep track of cur node and prev node
		//2. using 1 pointer to iterate until index-1 and inserting new node at index
		//implemented using no.2
		for (i=0,trav=head;i<size;i++){
			if (i==index-1){
				Node<T> newNode = new Node<>(value, trav.next);
				trav.next = newNode;
				size++;
				return;
			}
			trav=trav.next;
		}
	}

	public T removeAt(int index){
		//Check for index out of bound exception
		if (index < 0 || index >= size)
			throw new IndexOutOfBoundsException("Not a valid index");
		else if (index == 0)
			return pop_front();
		else if (index == size-1)
			return pop_back();

		//Again, 2 options to do removal
		//1. using 2 pointers to keep track of cur and prev nodes and deleting cur node when index matches
		//2. using 1 pointer to keep track of only the cur node. When index matches, copy the content of next
		//node to the cur node and delete the next node
		//Implemented using approach 2 since approach 1 is used in pop_back()
		int i;
		Node<T> trav;
		for (i=0,trav=head;i<size;i++){
			if (i==index){
				//First, get data to be returned
				T data = trav.data;
				//copy content and move pointers
				trav.data = trav.next.data;
				trav.next = trav.next.next;
				//memory cleanup 
				size--;
				return data;
			}
			trav=trav.next;
		}
		return null;
	}

	public void clear(){
		if (isEmpty())
			return;
		Node<T> trav;
		int i;
		while (size > 0)
			pop_front();

		//memory cleanup
		head = null;
	}


	//Returns 0-based index of a given value
	public int indexOf(T value){
		if (isEmpty())
			throw new RuntimeException("Empty list");

		int i;
		Node<T> trav;
		// We need to do a null check on value to avoid NPE when using equals()
		if (value == null){
			for (i=0,trav=head;i<size;i++){
			if (trav.data == null)
				return i;
			trav=trav.next;
			}
		}
		else{
			for (i=0,trav=head;i<size;i++){
			if (trav.data.equals(value))
				return i;
			trav=trav.next;
			}
		}
		return -1;
	}




}

