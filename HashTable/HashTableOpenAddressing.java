//Hash Table implementation using open addressing for collision resolution
//Use probing functions that ALWAYS result in a full cycle modulo

import java.util.*;

public abstract class HashTableOpenAddressing<T,V>{

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
	protected int capacity;
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
	private final Entry<T,V> TOMBSTONE = new Entry<T,V>();

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

		//Adjust the capacity, incase the capacity user gives does not yield a full cycle modulo w.r.t 
		//the probing function being used
		adjustCapacity();
		//List instead of LinkedList because we are not creating linked lists yet
		table = new ArrayList<Entry<T,V>>(capacity);
		//Fill the table with nulls to avoid index out of bound error
		clear(table,capacity);
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
	private void clear(List<Entry<T,V>> table, int tableCapacity){
		for (int i=0;i<tableCapacity;i++){
			table.add(null);
		}
		size=0;
	}

	private void clearUnused(List<Entry<T,V>> table, int tableCapacity){
		for (int i=0;i<tableCapacity;i++){
			table.add(null);
		}
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

	public void put(T key, V val){
		insert(table, key, val);
	}

	public void insert(List<Entry<T,V>> table, T key, V val){
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
		int x = 1;


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
		//If a tombstone had been found, replace the new pair with the tombstone
		if (tombstoneExists!=-1){
			Entry<T,V> newEntry = new Entry<>(key, val);
			table.set(tombstoneExists, newEntry);
			return;
		}
		//We can just insert the key,val pair to this slot and increment the size
		Entry<T,V> newEntry = new Entry<>(key, val);
		table.set(tableIndex, newEntry);
		size++;
		return;
	}

	public boolean containsKey(T key){
		if (isEmpty())
			return false;
		if (key==null)
			throw new IllegalArgumentException("null key not accepted!");
		int tableIndex = getIndexFromHashCode(key.hashCode());
		//iterate through all the entries and check if key equals for real
		//entries (not tombstone)
		while (table.get(tableIndex)!=null && table.get(tableIndex)!=TOMBSTONE){
			if (table.get(tableIndex).key.equals(key))
				return true;
		}

		//This means an empty slot was found which means the key does not exist
		return false;
	}

	public Entry<T,V> remove(T key){
		//Key does not exist in table. Return null indicating that nothing was removed
		if (!containsKey(key))
			return null;

		//Continue if key exists
		//Variable to store location of the first occurence of tombstone
		int tombstoneExists = -1;

		int x = 1;

		int tableIndex = getIndexFromHashCode(key.hashCode());

		//loop until no collision is detected or a tombstone is found
		while (!table.get(tableIndex).key.equals(key)){
			//If the first tombstone is found, save the index of the tombstone
			//We are only interested in the first tombstone because that is 
			//where we want to locate an entry
			if (table.get(tableIndex)==TOMBSTONE && tombstoneExists==-1)
				tombstoneExists=tableIndex;
			//Update table index to keep probing
			tableIndex+=probingFunction(x++);
			tableIndex%=capacity;
		}

		//We have found the entry matching the key
		Entry<T,V> res = table.get(tableIndex);
		table.set(tableIndex, TOMBSTONE);
		return res;

	}

	//Function to resize table when size exceeds threshold
	private void resizeTable(){
		//double the capacity and adjust the capacity to a value that yields full cycle modulo

		//old capacity used to clear the old table at the end of the function
		int oldCapacity = capacity;
		capacity*=2;
		adjustCapacity();
		List<Entry<T,V>> newTable = new ArrayList<>();
		clear(newTable,capacity);

		//Iterate through the old table and insert every entry onto the new table
		for (int i=0;i<oldCapacity;i++){
			//Found an entry
			if (table.get(i)!=null && table.get(i)!=TOMBSTONE){
				//get new table index with new capacity
				T key = table.get(i).key;
				V val = table.get(i).val;
				insert(newTable, key, val);
			}
		}
		clearUnused(table,oldCapacity);
		table=newTable;
	}

	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		for (int i=0;i<capacity;i++){
			if (table.get(i)==null)
				continue;
			else if (table.get(i)==TOMBSTONE)
				sb.append("Tombstone at " + i + ", ");
			else
				sb.append(i + ":" + table.get(i) + ", ");
		}
		sb.append("}");
		return sb.toString();
	}
}
