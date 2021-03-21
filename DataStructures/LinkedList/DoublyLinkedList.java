/*** doubly linked list implementation with tail pointer ***/

public class DoublyLinkedList <T> implements Iterable <T> { 
	private class Node<T>{
		T data;
		Node<T> prev, next;
		public Node (T data, Node<T> prev, Node<T> next){
			this.data = data;
			this.prev = prev;
			this.next = next;
		}
	}


	Node<T> head, tail;
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
		//Check if list is empty
		if (isEmpty())
			//If list is empty, assign both head and tail pointers to the new node
			head = tail = new Node<T>(value, null, null);
		else{
			//If list is not empty, assign only the head pointer to the new node and next of new 
			//node to the old head
			head.prev = new Node<T>(value, null, head);
			head = head.prev;
		} 
		size++;
	}

	public T pop_front(){
		//Check if list is empty
		if (isEmpty()){
			throw new RuntimeException("Empty list");
		}
		//If list is not empty, remove head and reassign head
		T data = head.data;
		head = head.next;
		size--;

		//Check if list is empty after deletion to prevent null pointer exception
		if (isEmpty())
			tail = null;
		else{
			head.prev.next = null;
			head.prev = null;
		}	
		return data;
	}
	////////////////////TESTED UP TO HERE



	public void push_back(T value){
		//Check if list is empty
		if (isEmpty())
			//If list is empty, assign both head and tail pointers to the new node
			head = tail = new Node<T>(value, null, null);
		else{
			//If list is not empty, assign only the tail pointer to the new node and prev of new 
			//node to the old tail
			tail.next = new Node<T>(value, tail, null);
			tail = tail.next;
		} 
		size++;
	}

	public T pop_back(){
		//Check if list is empty
		if (isEmpty()){
			throw new RuntimeException("Empty list");
		}
		//If list is not empty, remove tail and reassign tail
		T data = tail.data;
		tail = tail.prev;
		size--;

		//Check if list is empty after deletion to prevent null pointer exception
		if (isEmpty())
			head = null;
		else{
			tail.next.prev = null;
			tail.next = null;
		}	
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
		return tail.data;
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
		for (i=0,trav=head;i<size;i++){
			if (i==index){
				trav.prev.next = new Node<T>(value, trav.prev, trav);
				trav.prev = trav.prev.next;
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
		int i;
		Node<T> trav;
		for (i=0,trav=head;i<size;i++){
			if (i==index){
				trav.prev.next = trav.next;
				trav.next.prev = trav.prev;
				T data = trav.data;
				//memory cleanup 
				trav.prev = trav.next = null;
				trav.data = null;
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
		head = tail = null;
	}


	//Returns 0-based index of a given value
	public int indexOf(T value){
		if (isEmpty())
			throw new IndexOutOfBoundsException("Invalid index");

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

