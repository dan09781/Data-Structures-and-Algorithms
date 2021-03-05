//Hash Table implementation using open addressing for collision resolution

import java.util.*;

public class HashTable<T,V>{

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
	private List<List<Entry<T,V>>> table;

	HashTable(){
		this(defaultCapacity, defaultLoadFactor);
	}

	HashTable(int capacity){
		this(capacity, defaultLoadFactor);
	}

	HashTable(int capacity, double loadFactor){
		//Capacity has to be greater or equal to 0 and load factor should be in the range (0,1]
		if (capacity<0 || (loadFactor<=0 || loadFactor>1))
			throw new IllegalArgumentException();
		maxLoadFactor = loadFactor;
		this.capacity = capacity;
		threshold = (int)(maxLoadFactor*capacity);
		//List instead of LinkedList because we are not creating linked lists yet
		table = new ArrayList<List<Entry<T,V>>>(capacity);
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

	//Clear hash table
	public void clear(){
		for (int i=0;i<capacity;i++){
			table.add(null);
		}
		size = 0;
	}

	public boolean containsKey(T key){
		if (isEmpty())
			return false;
		int tableIndex = getIndexFromHashCode(key.hashCode());
		return listContainsKey(tableIndex,key);
	}

	//Function that finds if a key exists in a list
	private boolean listContainsKey(int i, T key){
		//If given index in the table does not exist
		//Do not accept null keys
		if (table.get(i)==null || key==null)
			return false;
		for (Entry<T,V> ent:table.get(i)){
			if (key.equals(ent.key)){
				return true;
			}
		}
		return false;
	}

	//Add new entry to the hash table
	public void put(T key, V val){
		if (key==null)
			throw new IllegalArgumentException("Key cannot be null");
		Entry<T,V> newEntry = new Entry<>(key,val);
		listInsertEntry(newEntry);
		return;
	}

	//Given an entry, insert it onto the appropriate list
	private void listInsertEntry(Entry<T,V> ent){
		//Get the table index by normalizing hash code
		int tableIndex = getIndexFromHashCode(ent.key.hashCode());
		//If this is the first entry being inserted into the table index, create new linked list
		//and add the entry to the new list
		if (table.get(tableIndex)==null){
			//If the size of table is going to exceed threshold, need to resize
			if (size+1 > threshold)
				resizeTable();
			List<Entry<T,V>> temp = new LinkedList<>();
			//Since we are using arrayList, instead of doing table.get(tableIndex) = temp, which
			//results in an error, use set() method
			table.set(tableIndex,temp);
			size++;
			table.get(tableIndex).add(ent);
		}
		else{
			//if entry already exists in the list, we update the value
			if (listContainsKey(tableIndex,ent.key)){
				for (Entry<T,V> tempEntry:table.get(tableIndex)){
					if (tempEntry.key.equals(ent.key))
						tempEntry.val = ent.val;
				}
			}
			//if not, we just add it to the list
			else
				table.get(tableIndex).add(ent);
		}

		return;
	}

	//Given a key, return its corresponding value
	public V get(T key){
		int tableIndex = getIndexFromHashCode(key.hashCode());
		//If the given key does not exist in table
		if (!listContainsKey(tableIndex,key))
			return null;
		for (Entry<T,V> ent:table.get(tableIndex)){
			if (key.equals(ent.key)){
				return ent.val;
			}
		}
		return null;
	}

	//Given a key, remove the entry corresponding to that key
	public Entry<T,V> remove(T key){
		int tableIndex = getIndexFromHashCode(key.hashCode());
		//Key does not exist in table. Nothing to return
		if (!listContainsKey(tableIndex,key))
			return null;
		//Key exists. Need to return the entry to be removed
		for (Entry<T,V> ent:table.get(tableIndex)){
			if (key.equals(ent.key)){
				Entry<T,V> res = ent;
				table.get(tableIndex).remove(ent);
				//If no entry exists in the list after deletion, 
				//delete the list and decrease the size
				if (table.get(tableIndex).size()==0){
					table.set(tableIndex,null);
					size--;
				}
				return res;
			}
		}
		return null;
	}

	//Resize table if number of elements in the table exceeds threshold
	private void resizeTable(){
		capacity*=2;
		threshold=(int)(capacity*maxLoadFactor);
		List<List<Entry<T,V>>> newTable = new ArrayList<List<Entry<T,V>>>(capacity);
		//Need to copy all entries from old table to new table
		for (int i=0;i<table.size();i++){
			//if a list exists do the copying
			if (table.get(i) != null){
				//Copy every entry
				for (Entry<T,V> ent:table.get(i)){
					int tableIndex = getIndexFromHashCode(ent.hash);
					//If no entries exist in index
					//create new linked list and add the entry to the new linked list
					if (newTable.get(tableIndex) == null){
						newTable.set(tableIndex, new LinkedList<Entry<T,V>>());
						newTable.get(tableIndex).add(ent);
					}
					//If there already is a linked list
					//Just add the entry to the existing linked list
					else{
						newTable.get(tableIndex).add(ent);
					}
				}
				//clear the list
				table.get(i).clear();
				table.set(i,null);
			}
		}
		//Assign newTable as table
		table=newTable;
	}


	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		for (int i=0;i<capacity;i++){
			if (table.get(i)==null)
				continue;
			for (Entry<T,V> ent:table.get(i)){
				//We can just use ent since linked list already has its own toString 
				//method that will convert ent into useful output
				sb.append(i + ":" + ent + ", ");
			}
		}
		sb.append("}");
		return sb.toString();
	}

}
