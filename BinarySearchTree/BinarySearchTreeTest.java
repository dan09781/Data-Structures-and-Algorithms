package DataStructureImplementation.BinarySearchTree;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BinarySearchTreeTest {

    @Test
    void getSize() {
    }

    @Test
    void isEmpty() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        assertEquals(true, bst.isEmpty());
        bst.insert(1);
        assertEquals(1, bst.getSize());
    }

    @Test
    void insert() {
        BinarySearchTree<Character> bst = new BinarySearchTree<>();
        assertEquals(true, bst.insert('A'));
        assertEquals(false, bst.insert('A'));
        assertEquals(true, bst.insert('B'));
    }

    @Test
    void containsElem() {
        BinarySearchTree<Character> bst = new BinarySearchTree<>();
        bst.insert('B');
        bst.insert('A');
        bst.insert('C');

        assertEquals(true, bst.containsElem('B'));
        assertEquals(false, bst.containsElem('D'));
        assertEquals(true, bst.containsElem('A'));
        assertEquals(true, bst.containsElem('C'));
    }

    @Test
    void clear() {
        BinarySearchTree<Character> bst = new BinarySearchTree<>();
        bst.insert('B');
        bst.insert('A');
        bst.insert('C');
        bst.insert('B');
        bst.insert('A');
        bst.insert('C');
        bst.insert('D');
        bst.insert('E');
        bst.insert('F');
        bst.insert('G');
        assertEquals(7, bst.getSize());
        bst.clear();
        assertEquals(0, bst.getSize());
        bst.insert('E');
        bst.insert('F');
        bst.insert('G');
        assertEquals(3, bst.getSize());
    }

    @Test
    void getMax() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        bst.insert(1);
        bst.insert(2);
        bst.insert(12);
        bst.insert(-12);
        bst.insert(580);
        assertEquals(5, bst.getSize());
        assertEquals(580, bst.getMax());
    }

    @Test
    void getMin() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        bst.insert(1);
        bst.insert(2);
        bst.insert(12);
        bst.insert(-12);
        bst.insert(580);
        assertEquals(5, bst.getSize());
        assertEquals(-12, bst.getMin());
    }

    @Test
    void isBST() {
        BinarySearchTree<Character> bst = new BinarySearchTree<>();
        bst.insert('B');
        bst.insert('A');
        bst.insert('C');
        bst.insert('B');
        bst.insert('A');
        bst.insert('C');
        bst.insert('D');
        bst.insert('E');
        bst.insert('F');
        bst.insert('G');
        assertEquals(7, bst.getSize());
        assertEquals(true, bst.isBST());
    }

    @Test
    void testRemove(){
        

    }

    @Test
    void inOrderTraversal() {
    }

    @Test
    void testToString() {
    }
}