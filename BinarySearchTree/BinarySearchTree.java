public class BinarySearchTree<T extends Comparable<T>>{
	private class Node<T>{

		//If we call compareTo on T, we're using built in compareTo function of T
		//Or manually implemented compareTo if T is a custom class we wrote
		T data;
		Node<T> left;
		Node<T> right;

		Node(){

		}

		Node(T data){
			this.data=data;
		}

		Node(T data, Node<T> left, Node<T> right){
			this.data=data;
			this.left=left;
			this.right=right;
		}

		/*@Override
		public int compareTo(Node<T> elem){
			if (this.data < elem.data)
				return 1;
			else if (this.data > elem.data)
				return -1;
			else 
				return 0;
		}*/

		@Override 
		public String toString(){
			StringBuilder sb = new StringBuilder();
			sb.append(this.data);
			return sb.toString();
		}
	}

	//Insert an element into the bst
	public void insert(Node<T> elem){
		if (elem==null) 
			return;

	}

	private void insertHelper(Node<T> elem, Node<T> root){
		if (root==null){
			return;
		}
	}

	private Node<T> find(Node<T> elem, Node<T> root){
		//if we hit null, means we could not find the node. return null
		if (elem==null || root==null)
			return null;
		//If we found the node, return it
		if (elem.data.compareTo(root.data)==0)
			return root;
		//if the node we are finding is smaller than the root node
		else if (elem.data.compareTo(root.data) < 0)
			return find(elem, root.left);
		else 
			return find(elem, root.right);
	}

	//Function to find out if a tree satisfies bst invariant
	//For testing purposes
	public boolean isBST(Node<T> root){
		if (root==null)
			return true;
		if (root.data.compareTo(left)<0)
			return false;
		if (root.data.compareTo(right)>0)
			return false;
		return isBST(root.left) && isBST(root.right);
	}
} 