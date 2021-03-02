/*** Stack implementation using Java's built in linked list ***/
import java.util.LinkedList;

public class Stack<T> implements Iterable<T>{
	private LinkedList<T> list = new LinkedList<>();

	//Constructors
	Stack(){

	}

	Stack(T data){
		push(data);
	}

	//Push element
	public void push(T data){
		list.addFirst(data);
	}

	//Pop element
	public T pop(){
		if (isEmpty())
			throw new RuntimeException("Empty Stack. Nothing to pop");
		return list.removeFirst();
	}

	public T peek(){
		if (isEmpty())
			throw new RuntimeException("Empty Stack. Nothing to pop");
		return list.peekFirst();
	}

	public int size(){
		return list.size();
	}

	public boolean isEmpty(){
		return list.size() == 0;
	}

	@Override java.util.Iterator<T> iterator(){
		return list.iterator();
	}
}