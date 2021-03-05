import java.util.*;


//Driver Method!!!
public class Main{
	public static void main(String[] args){
		List<Integer> arr = new ArrayList<>(Arrays.asList(6,3,43,6,7,1,2,0,12,6,4,14));
		MaxPriorityQueue<Integer> pq = new MaxPriorityQueue<>(arr);
		for (Integer val:pq){
			System.out.println(val);
		}

		System.out.println(" ");
		System.out.println(pq.isMaxHeap(0));
		System.out.println(" ");

		pq.insert(12);
		pq.insert(39);
		pq.insert(-2);
		pq.insert(-5);
		pq.insert(50);
		//
		pq.insert(0);


		for (Integer val:pq){
			System.out.println(val);
		}
		System.out.println(pq.isMaxHeap(0));
	}
}