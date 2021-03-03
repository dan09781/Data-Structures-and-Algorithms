import java.util.Iterator;

//Driver Method!!!
public class Main{
	public static void main(String[] args){
		Stack<Integer> stack = new Stack<>();
		stack.push(1);
		stack.push(2);
		stack.push(3);
		//for (Integer val:stack)
		//	System.out.println(val);
		System.out.println(stack.peek());
		stack.pop();
		stack.pop();
		stack.pop();
		stack.pop();

	}
}