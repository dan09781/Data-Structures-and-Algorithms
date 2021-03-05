import java.util.*;


//Driver Method!!!
public class Main{
	public static void main(String[] args){
		HashTable<Integer,String> ht = new HashTable<>(10,0.8);
		ht.put(1,"hi");
		ht.put(2,"hello");
		ht.put(3,"sup");
		ht.put(4,"hey");
		ht.put(5,"annyung");
		ht.put(5,"annyunghasaeyo");

		ht.remove(4);

		System.out.println(ht);

	}
}