//Queue implementation using a fixed-sized integer array based circular buffer
//With an array, enqueue is simple as we just have to add an element to the end of an array
//But with dequeueing, removing the first element in an array is a rather expensive operation.
//Since we want both enqueueing and dequeueing to take O(1) time, we need to use read and write pointers
//for a fast dequeue as well
//Write pointer: tells us where to enqueue the next element
//Read pointer: tells us which element to dequeue

public class QueueArray implements Iterable<Integer>{
	private static final int defaultArrSize = 5;
	private int read = -1;
	private int write = -1;
	private int[] arr;
	QueueArray(){
		this.arr = new int[defaultArrSize];
		//read=write=-1;
	}
	QueueArray(int size){
		if (size <= 0){
			throw new RuntimeException("size must be greater than 0");
		}
		this.arr = new int[size];
		//read=write=-1;
	}

	//These 2 fun-ns accessors for debugging purposes
	public int getRead(){
		return read;
	}

	public int getWrite(){
		return write;
	}

	public void enqueue(int data){
		if (isEmpty()){
			write=0;
			read=0;
			arr[write]=data;
			write = (write+1)%arr.length;
			return;
		}
		if (isFull()){
			return;
		}
		arr[write]=data;
		write = (write+1)%arr.length;
	}

	public int dequeue(){
		if (isEmpty())
			throw new RuntimeException("Empty queue!!");
		//value to be removed
		int res = arr[read];
		read = (read+1)%arr.length;
		if (read==write){
			read=write=-1;
		}
		return res;
	}

	//checks if queue is empty
	public boolean isEmpty(){
		return (read==-1&&write==-1);
	}

	public boolean isFull(){
		return (read==write&&read!=-1);
	}

	@Override public java.util.Iterator<Integer> iterator(){
		return new java.util.Iterator<Integer> (){

			//This whole thing is definition of custom iterator class
			private int numElements = getNumElements();
			private int itIndex = read;
			public int getNumElements(){
				if (isFull())
					return arr.length;
				else if (isEmpty())
					return 0;
				else
					return write>read?(write-read):(write+arr.length-read);
			}
			@Override public boolean hasNext(){
				return numElements>0;
			}
			@Override public Integer next(){
				int data = arr[itIndex];
				itIndex = (itIndex+1)%arr.length;
				numElements--;
				return data;
			}
		};
	}
}