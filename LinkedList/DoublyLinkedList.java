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

		//Check if list is gonna be empty after deletion to prevent null pointer exception
		if (isEmpty())
			tail = null;
		else{
			head.prev.next = null;
			head.prev = null;
		}	
		return data;
	}



}

