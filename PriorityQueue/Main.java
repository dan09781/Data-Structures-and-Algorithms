import java.util.*;


//Driver Method!!!
public class Main{
	public static void main(String[] args){
		List<Integer> arr = new ArrayList<>(Arrays.asList(8,3,4,6,2,5,7,9,10));
		PriorityQueue<Integer> pq = new PriorityQueue<>(arr);
		for (Integer val:pq){
			System.out.println(val);
		}
	}
}