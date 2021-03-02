import java.util.Iterator;

//Driver Method!!!
public class Main{
	public static void main(String[] args){
		/*DoublyLinkedList<Integer> dll = new DoublyLinkedList<>();
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
			System.out.println(val);*/

		SinglyLinkedList<Integer> sll = new SinglyLinkedList<>();
		sll.push_front(1);
		sll.push_front(2);
		sll.push_front(3);
		sll.push_front(4);
		sll.push_front(5);
		sll.push_front(6);
		for (Integer val:sll)
			System.out.println(val);

		System.out.println(" ");


		//for (Integer val:sll)
		//	System.out.println(val);

		/*sll.insertAt(5,12);
		for (Integer val:sll)
			System.out.println(val);*/

		System.out.println(" ");

		sll.removeAt(5);
		for (Integer val:sll)
			System.out.println(val);

		System.out.println(" ");

		//sll.clear();

		//for (Integer val:sll)
		//	System.out.println(val);

		//System.out.println(sll.indexOf(6));
		System.out.println(sll.indexOf(5));
		System.out.println(sll.indexOf(4));
		System.out.println(sll.indexOf(3));
		System.out.println(sll.indexOf(2));
		System.out.println(sll.indexOf(1));

	}
}