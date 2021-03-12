import java.util.*;


//Driver Method!!!
public class Main{
	public static void main(String[] args){
		BinarySearchTree<Integer> bst = new BinarySearchTree<>(5, null, null);
		bst.insert(3);
		bst.insert(2);
		bst.insert(6);
		bst.insert(9);
		bst.insert(11);
		bst.insert(15);
		System.out.println(bst.containsElem(6));
		System.out.println(bst);

		System.out.println(bst.getMin());
		bst.clear();
		System.out.println(bst);
		System.out.println(bst.isEmpty());
		System.out.println(bst.getSize());

	}
}