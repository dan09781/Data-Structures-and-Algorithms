public class BinarySearchTree<T extends Comparable<T>>{
	private int size=0;
	Node bstRoot=null;

	private class Node{

		//If we call compareTo on T, we're using built in compareTo function of T
		//Or manually implemented compareTo if T is a custom class we wrote
		T data;
		Node left;
		Node right;

		Node(){
			//Set root node
		}

		Node(T data){
			this.data=data;
		}

		Node(T data, Node left, Node right){
			this.data=data;
			this.left=left;
			this.right=right;
		}

	}

	BinarySearchTree(T data, Node left, Node right){
		bstRoot = new Node(data, left, right);
		size=1;
	}

	public int getSize(){
		return size;
	}

	public boolean isEmpty(){
		return getSize()==0;
	}

	//Insert an element into the bst
	public boolean insert(T data){
		//If data is null or the data already exists in the bst
		//We do nothing
		if (data==null || contains(data, bstRoot)) 
			return false;
		//return the root with the new element inserted into bst
		//We must return the root because otherwise, inserted node will be lost
		//when the recursive call that creates the new node returns
		bstRoot=insertHelper(data, bstRoot);
		size++;
		return true;
	}

	//Inserts the node and returns the node that has been inserted
	private Node insertHelper(T elem, Node root){
		//We have found the null leaf position we want to insert the node into
		if (root==null){
			System.out.println("Insertion: found null leaf noce " + elem);
			root=new Node(elem, null, null);
			return root;
		}
		else{
			//If element to be inserted is smaller than the current node
			if (elem.compareTo(root.data)<0){
				System.out.println("Insertion: smaller" + elem);
				root.left=insertHelper(elem, root.left);
			}
			else
			//The only other possible option is if the element is larger than the current node
			//We have already taken care of the case in which they are equal in the main insert method
			//by first checking if the element already exists in the bst
				root.right=insertHelper(elem, root.right);
		}
		return root;
	}

	public boolean containsElem(T data){
		return contains(data, bstRoot);
	}

	//Private method to find out if a given element exists in the bst
	private boolean contains(T data, Node root){
		//If root is null, it either means that the tree does not exist or the element could not be found.
		//Since we don't allow null elements, we return false
		if (root==null || data==null){
			return false;
		}

		//If the element we are looking for is smaller than the current node, traverse the left subtree
		if (data.compareTo(root.data)<0){
			return contains(data, root.left);
		}
		//If the element we are looking for is greater than the current node, traverse the right subtree
		if (data.compareTo(root.data)>0){
			return contains(data, root.right);
		}
		//Else, the element we are looking for matches the current node.
		return true;
	}

	private Node find(T elem, Node root){
		//if we hit null, means we could not find the node. return null
		if (elem==null || root==null)
			return null;
		//If we found the node, return it
		if (elem.compareTo(root.data)==0)
			return root;
		//if the node we are finding is smaller than the root node
		else if (elem.compareTo(root.data) < 0)
			return find(elem, root.left);
		else 
			return find(elem, root.right);
	}

	//Function to find out if a tree satisfies bst invariant
	//For testing purposes
	public boolean isBST(Node root){
		if (root==null)
			return true;
		if (root.data.compareTo(root.left.data)<0)
			return false;
		if (root.data.compareTo(root.right.data)>0)
			return false;
		return isBST(root.left) && isBST(root.right);
	}

	//Inorder traversal function
	private void inOrder(StringBuilder sb, Node root){
		if (root==null)
			return;
		inOrder(sb, root.left);
		sb.append(root.data + ", ");
		inOrder(sb, root.right);
	}

	//Preorder traversal function
	private void preOrder(StringBuilder sb, Node root){
		if (root==null)
			return;
		sb.append(root.data + ", ");
		preOrder(sb, root.left);
		preOrder(sb, root.right);
	}

	//List the bst with in-order traversal
	@Override 
	public String toString(){
		StringBuilder sb = new StringBuilder();
		preOrder(sb, bstRoot);
		return sb.toString();
	}
} 