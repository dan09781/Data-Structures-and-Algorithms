//Hash Table implementation using open addressing for collision resolution
//Use probing functions that ALWAYS result in a full cycle modulo

import java.util.*;

public class HashTableOpenAddressing<T,V>{

	//First declare the entry class
	private class Entry<T,V>{
		int hash;
		T key;
		V val;
		//Constructors 
		Entry(){
		}
		Entry(T key, V val){
			this.key = key;
			this.val = val;
			//Built in java method to get the hash code
			//get hash code of Key
			this.hash = key.hashCode();
		}

		@Override 
		public String toString(){
			return key+"=>"+val;
		}
	}

	//Hash table variables
	private static final int defaultCapacity=5;
	private static final double defaultLoadFactor=0.75;
	private double maxLoadFactor;
	private int capacity;
	//Threshold is to prevent table from getting too full, which may result
	//in slots not being used, b.c of too many collisions, hence linked lists being too long
	//thershold = defaultLoadFactor*capacity
	private int threshold;
	//size and table.size() are not the same thing. 
	//table.size() is meaningless as it will be the same as capacity
	//since we initialize the arraylist with nulls up to capacity
	//to avoid index out of bound exception later on when accessing elements
	//Whereas size represents the number of non-null elements in the array list
	private int size=0;
	private List<Entry<T,V>> table;
	private final Entry<T,V> TOMBSTONE = (Entry<T,V>)(new Object());
	private static final int LINEAR_CONSTANT = 1;

	HashTableOpenAddressing(){
		this(defaultCapacity, defaultLoadFactor);
	}

	HashTableOpenAddressing(int capacity){
		this(capacity, defaultLoadFactor);
	}

	HashTableOpenAddressing(int capacity, double loadFactor){
		//Capacity has to be greater or equal to 0 and load factor should be in the range (0,1]
		if (capacity<0 || (loadFactor<=0 || loadFactor>1))
			throw new IllegalArgumentException();
		maxLoadFactor = loadFactor;
		this.capacity = capacity;
		threshold = (int)(maxLoadFactor*capacity);
		//List instead of LinkedList because we are not creating linked lists yet
		table = new ArrayList<Entry<T,V>>(capacity);
		//Fill the table with nulls to avoid index out of bound error
		clear();
	}


	public int size(){
		return size;
	}

	public boolean isEmpty(){
		return size==0;
	}

	//This is a function that converts a hash value of a key to an index
	//in the fixed range of [0,capacity), so we can index it in our hash table
	private int getIndexFromHashCode(int hashCode){
		return (hashCode & 0x7FFFFFFF) % capacity;
	}

	//Clear hash table; set everything to null and reset size to 0
	public void clear(){
		for (int i=0;i<capacity;i++){
			table.add(null);
		}
		size = 0;
	}

	//Function to find greatest common denomenator of two integers
	protected int gcd(int a, int b){
		if (b==0) return a;
		return gcd(b,a%b);
	}

	//function to swap two elements on the table
	private void swap(int i, int j){
		Entry<T,V> temp = table.get(i);
		Entry<T,V> temp2 = table.get(j);
		table.set(i,temp2);
		table.set(j,temp);
	}

	//Some protected abstract methods to support quadratic and linear probing
	protected abstract int probingFunction(int x);

	protected abstract void adjustCapacity();

	public void insert(T key, V val){
		if (key==null)
			throw new IllegalArgumentException("Key cannot be null");

		int tableIndex = getIndexFromHashCode(key.hashCode());

		//if size exceeds threshold after insertion, resize table
		//It's important to leave at least 1 slot null even if max load factor is 1
		//to avoid infinite loop when probing
		if (size+1>=threshold)
			resizeTable();

		//Initialize index of tombstone to be -1
		int tombstoneExists = -1; 

		//Initialize x to be 0. Will be used for probing function
		int x = 0;


		//loop until no collision is detected or a tombstone is found
		while (table.get(tableIndex)!=null){
			//If the first tombstone is found, save the index of the tombstone
			//We are only interested in the first tombstone because that is 
			//where we want to locate an entry
			if (table.get(tableIndex)==TOMBSTONE && tombstoneExists==-1)
				tombstoneExists=tableIndex;
			//There is a collision with another entry, perhaps an entry with the same key already exists.
			//In that case, we update the value and swap with the tombstone
			else{
				//If there already exists an entry with the same key
				if (table.get(tableIndex).key.equals(key)){
					//Update the value
					table.get(tableIndex).val=val;
					//swap the entry with the tombstone if a tombstone had been found
					if (tombstoneExists!=-1){
						swap(tableIndex,tombstoneExists);
						//Set the old location of the entry to null
						table.set(tableIndex,null);
						size--;
					}
					//Job done. Return out of function
					return;

				}
			}
			//Update table index to keep probing
			tableIndex+=probingFunction(x++);
			tableIndex%=capacity;
		}

		//If we get to here, it means that an empty slot was found.
		//We can just insert the key,val pair to this slot and increment the size
		Entry<T,V> newEntry = new Entry<>(key, val);
		table.set(tableIndex, newEntry);
		size++;
		return;
	}
}
