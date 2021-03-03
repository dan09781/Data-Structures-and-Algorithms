import java.util.Iterator;

//Driver Method!!!
public class Main{
	public static void main(String[] args){
		QueueArray queue = new QueueArray();
		queue.enqueue(1);
		queue.enqueue(2);
		queue.enqueue(3);
		queue.enqueue(4);
		queue.enqueue(5);
		for(Integer val:queue)
			System.out.println(val);

		System.out.println(queue.isEmpty());
		System.out.println(queue.getWrite());

		System.out.println(" ");
		queue.dequeue();
		queue.dequeue();
		queue.dequeue();
		queue.dequeue();
		queue.dequeue();

		for(Integer val:queue)
			System.out.println(val);

		System.out.println(" ");


		System.out.println(queue.isEmpty());
		System.out.println(queue.getRead());
		System.out.println(queue.getWrite());

		System.out.println(" ");

		queue.enqueue(5);
		queue.enqueue(6);
		queue.enqueue(7);
		queue.enqueue(8);
		queue.enqueue(9);



		System.out.println(queue.isEmpty());
		System.out.println(queue.getRead());
		System.out.println(queue.getWrite());

		System.out.println(" ");

		for(Integer val:queue)
			System.out.println(val);
	}
}