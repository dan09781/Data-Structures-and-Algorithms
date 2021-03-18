//Queue implementation using Java's built in linked list

import java.util.LinkedList;

public class Queue<T> implements Iterable<T>{
	private LinkedList<T> list = new LinkedList<T>();

	//Constructors
	Queue(){

	}

	Queue(T data){
		enqueue(data);
	}

	//Enqueue at tail
	public void enqueue(T data){
		list.addLast(data);
	}


	//Dequeue at head
	public T dequeue(){
		if (isEmpty())
			throw new RuntimeException("Empty queue. Nothing to dequeue");
		return list.removeFirst();
	}

	public T peek(){
		if (isEmpty())
			throw new RuntimeException("Empty queue. Nothing to dequeue");
		return list.peekFirst();
	}

	public int size(){
		return list.size();
	}

	public boolean isEmpty(){
		return list.size() == 0;
	}

	@Override public java.util.Iterator<T> iterator(){
		return list.iterator();
	}
}