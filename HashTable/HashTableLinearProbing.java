//Hash table linear probing implementation. Inherits hash table open addressing base class

public class HashTableLinearProbing<T,V> extends HashTableOpenAddressing<T,V>{

	private static final int LINEAR_CONSTANT = 1;

	//Constructors that call super constructors
	HashTableLinearProbing(){
		super();
	}

	HashTableLinearProbing(int capacity){
		super(capacity);
	}

	HashTableLinearProbing(int capacity, double loadFactor){
		super(capacity, loadFactor);
	}

	//Just overriding abstract functions
	@Override
	protected int probingFunction(int x){
		return LINEAR_CONSTANT*x;
	}

	@Override
	protected void adjustCapacity(){
		//For a capacity that yields a full cycle modulo, we want
		//a value that has a gcd of 1 w.r.t linear constant
		while (gcd(LINEAR_CONSTANT,capacity)!=1){
			capacity++;
		}
	}
}