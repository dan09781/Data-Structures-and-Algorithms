import java.util.*;


//Driver Method!!!
public class Main{
	public static void main(String[] args){
		List<Integer> arr = new ArrayList<>(Arrays.asList(1,5,1,8,6,2,2,13,12,11,7,2,15,3,10));
		PriorityQueue<Integer> pq = new PriorityQueue<>(arr);
		for (Integer val:pq){
			System.out.println(val);
		}

		System.out.println(" ");
		System.out.println(pq.isMinHeap(0));
		System.out.println(" ");


		pq.removeAt(0);
		pq.remove(12);
		pq.remove(3);
		pq.removeAt(0);
		pq.remove(6);

		for (Integer val:pq){
			System.out.println(val);
		}
		System.out.println(pq.isMinHeap(0));
	}
}