//Hash table quadratic probing implementation. Inherits hash table open addressing base class
//Using the popular P(x)=(x^2+x)/2 probing function 
//For this probing function to yield a full cycle modulo, we need the table size to be
//power of 2

public class HashTableQuadraticProbing<T,V> extends HashTableOpenAddressing<T,V>{

	private static final int LINEAR_CONSTANT = 1;

	//Constructors that call super constructors
	HashTableQuadraticProbing(){
		super();
	}

	HashTableQuadraticProbing(int capacity){
		super(capacity);
	}

	HashTableQuadraticProbing(int capacity, double loadFactor){
		super(capacity, loadFactor);
	}

	//Clever way to find if a number is power of 2
	private boolean isPowOfTwo(int x){
    	return (x & (x - 1)) == 0;
	}

	//Just overriding abstract functions
	//Implement quadratic probing function
	@Override
	protected int probingFunction(int x){
		return (x*x+x) >> 1;
	}

	@Override
	protected void adjustCapacity(){
		//we want capacity to be a power of 2
		while (!isPowOfTwo(capacity)){
			capacity++;
		}
	}
}