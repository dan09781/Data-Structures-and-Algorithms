import java.util.*;


//Driver Method!!!
public class Main{
	public static void main(String[] args){
		List<Integer> arr = new ArrayList<>(Arrays.asList(6,3,43,6,7,1,2,0,12,6,4,14));
		PriorityQueue<Integer> pq = new PriorityQueue<>(arr);
		for (Integer val:pq){
			System.out.println(val);
		}
	}
}