# Data Structure Implementation

Data structures implementations in Java

# List of data structures implemented
* Dynamic Array
* Linked List
  * Singly linked list with tail
  * Singly linked list without tail
  * Doubly linked list with tail
* Stack
* Queue
  * Queue with linked list
  * Queue with array using read and write pointers
  * Queue with 2 stacks
* Priority Queue
  * Min priority queue
  * Max priority queue
* Hash Table
  * Hash table with separate chaining
  * Hash table with open addressing
    * Supports linear and quadratic probing
* Binary Search Tree

# Running test cases on data structures
Compile JUnit test cases:
```
javac -d /absolute/path/for/compiled/classes -cp junit-platform-console-standalone-<version>.jar /absolute/path/to/TestClassName.java
```
Run the test cases:
```
java -cp /absolute/path/for/compiled/classes:junit-platform-console-standalone-<version>.jar:/absolute/path/to/hamcrest-core-1.3.jar org.junit.runner.JUnitCore DataStructureImplementation.BinarySearchTree.DataStructureClassName
```



