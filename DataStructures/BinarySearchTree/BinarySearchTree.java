/* Implementation of a Binary Search Tree (BST). Using generics that extends the comparable
 * class so any comparable data type can be used. Supports functions such as insertion, deletion, clear,
 * bst check, get min and max, contains check, get height, as well as all traversals.
 *
 * @author Daniel Min, daniel.min9609@gmail.com
 */

package DataStructureImplementation.DataStructures.BinarySearchTree;

import java.util.*;

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

	BinarySearchTree(){

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
			root=new Node(elem, null, null);
			return root;
		}
		else{
			//If element to be inserted is smaller than the current node
			if (elem.compareTo(root.data)<0){
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

	public boolean remove(T elem){
		//null element not accepted. return false to indicate nothing has been removed
		//Same with when elem cannot be found in the bst
		if (elem==null || !containsElem(elem))
			return false;
		bstRoot = removeHelper(elem, bstRoot);
		size--;
		return true;
	}

	private Node removeHelper(T elem, Node root){
		//We already know that the node we wish to remove exists in the bst at this point.
		//Find it.
		if (root==null)
			return null;
		//elem is smaller than cur node
		if (elem.compareTo(root.data)<0)
			root.left = removeHelper(elem, root.left);
		//elem is larger than cur node
		else if (elem.compareTo(root.data)>0)
			root.right = removeHelper(elem, root.right);
		//Here, we have found the node we want to remove
		// 4 cases that can happen in a removal:
		//        - 1. Node to be removed is a leaf node: No need to find a successor. Just remove it.
		//        - 2. Left subtree exists: Choose the root of the left subtree to be the successor
		//        - 3. Right subtree exists: Choose the root of the right subtree to be the successor
		//        - 4. Both subtrees exist: Choose either the largest value in the left subtree or the
		//        smallest value in the right subtree to be the successor.
		//        After replacing it, remove the successor node in its original position.
		//        Removing the successor in its original position should always yield one of cases 1, 2, or 3.
		else {
			//First case
			if (root.left == null && root.right == null) {
				//clear memory
				root.data = null;
				root = null;
				return null;
			}
			//Second case
			else if (root.right==null){
				root.data=null;
				return root.left;
			}
			//Third case
			else if (root.left==null){
				root.data=null;
				return root.right;
			}
			//Fourth case
			//Choose largest of the left subtree as successor
			else{
				//Find successor and replace the value with current node
				T successor = getMax(root.left);
				root.data=successor;
				//remove the successor node in its original position
				//by calling the remove function on the left subtree
				root.left = removeHelper(successor, root.left);
				return root;
			}
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

	//Clears the tree
	public void clear(){
		bstRoot=clear(bstRoot);
		bstRoot=null;
		size=0;
		return;
	}

	private Node clear(Node root){
		if (root==null)
			return null;
		root.left=clear(root.left);
		root.right=clear(root.right);
		return null;
	}

	//Get max and min functions

	//public function for user
	public T getMax(){
		return getMax(bstRoot);
	}

	//private function for uses within the class
	private T getMax(Node root){
		if (root.right==null)
			return root.data;
		//Go farthest to the right to get max
		T res=getMax(root.right);
		//this is when we are at the max node
		return res;
	}

	public T getMin(){
		return getMin(bstRoot);
	}

	private T getMin(Node root){
		if (root.left==null)
			return root.data;
		T res=getMin(root.left);
		return res;
	}


	public boolean isBST(){
		return isBST(bstRoot);
	}
	//Function to find out if a tree satisfies bst invariant
	//For testing purposes
	private boolean isBST(Node root){
		if (root==null)
			return true;
		if (root.left!=null && root.data.compareTo(root.left.data)<0)
			return false;
		if (root.right!=null && root.data.compareTo(root.right.data)>0)
			return false;
		return isBST(root.left) && isBST(root.right);
	}

	public int getHeight(){
		if (bstRoot==null)
			return 0;
		int height=0;
		return getHeight(bstRoot, height);
	}

	private int getHeight(Node root, int height){
		if (root==null)
			return height;
		int height1=getHeight(root.left, height+1);
		int height2=getHeight(root.right, height+1);
		return Math.max(height1, height2);
	}

	/*public Node inorderSuccessor(Node root){
		if (root==null)
			return null;
		if (root.right!=null)
			return getMinNode(root.right);

		//here input node has no right subtree
		//find if it is a left child or a right child
		//if it is a left child: return the parent
		//if it is a right child: go up til you find a node
		//who is a left child of its parent

		//means the root is the root of bst and has no right subtree
		//return null
		if (root.parent!=null)
			return null;

		//if it is a left child
		if (root.parent.key>root.key)
			return parent;

		//if it is a right child
		if (root.parent.key < root.key){
			while (root.parent.key < root.key)
				root=root.parent;
			return root.parent;
		}

	}

	private Node getMinNode(Node root){
		while (root.left!=null)
			root=root.left;
		return root;
	}*/


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

	/*Iterator<T> traversals(){
		switch(traversalType){
			case INORDER:
				return inOrderTraversal();
			case PREORDER:
				return preOrderTraversal();
			case POSTORDER:
				return postOrderTraversal();

		}
	}*/




	//Fancy iterative solution for inorder traversal using stack
	//very complex to write this iterator function recursively...
	Iterator<T> inOrderTraversal(){
		final java.util.Stack<Node> stack = new java.util.Stack<>();
		stack.push(bstRoot);

		return new java.util.Iterator<T>() {
			Node cur = bstRoot;

			@Override
			public boolean hasNext() {
				return bstRoot != null && !stack.isEmpty();
			}

			@Override
			public T next() {
				while (cur != null && cur.left != null) {
					stack.push(cur.left);
					cur = cur.left;
				}

				Node node = stack.pop();

				// Try moving down right once
				if (node.right != null) {
					stack.push(node.right);
					cur = node.right;
				}

				return node.data;
			}

			@Override
			public void remove() {
				throw new UnsupportedOperationException();
			}
		};
	}

	/*Iterator<T> preOrderTraversal(){

	}*/

	//List the bst with in-order traversal
	@Override 
	public String toString(){
		StringBuilder sb = new StringBuilder();
		preOrder(sb, bstRoot);
		return sb.toString();
	}
} 