import java.util.*;


//Driver Method!!!
public class Main{
	public static void main(String[] args){
		List<Integer> arr = new ArrayList<>(Arrays.asList(1,5,1,8,6,2,2,13,12,11,7,2,15,3,10));
		MaxPriorityQueue<Integer> pq = new MaxPriorityQueue<>(arr);
		for (Integer val:pq){
			System.out.println(val);
		}

		System.out.println(" ");
		System.out.println(pq.isMaxHeap(0));
		System.out.println(" ");


		pq.removeAt(0);
		System.out.println(pq.isMaxHeap(0));
		pq.remove(12);
		System.out.println(pq.isMaxHeap(0));
		pq.remove(3);
		System.out.println(pq.isMaxHeap(0));
		pq.removeAt(0);
		System.out.println(pq.isMaxHeap(0));
		pq.remove(6);

		for (Integer val:pq){
			System.out.println(val);
		}
		System.out.println(pq.isMaxHeap(0));
	}
}