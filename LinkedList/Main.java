import java.util.Iterator;

//Driver Method!!!
public class Main{
	public static void main(String[] args){
		DoublyLinkedList<Integer> dll = new DoublyLinkedList<>();
		dll.push_front(1);
		dll.push_front(2);
		dll.push_front(3);
		dll.push_front(4);
		dll.push_front(5);
		dll.push_front(6);
		for (Integer val:dll)
			System.out.println(val);

		System.out.println(" ");

		dll.insertAt(5,12);
		for (Integer val:dll)
			System.out.println(val);

		System.out.println(" ");

		dll.removeAt(3);
		for (Integer val:dll)
			System.out.println(val);

		System.out.println(" ");

		dll.clear();

		for (Integer val:dll)
			System.out.println(val);

	}
}