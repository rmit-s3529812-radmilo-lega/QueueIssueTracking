package Main;


/**
 * 
 * @author Lawrence Cavedon
 * Simple Queue class, implementing basic queue of int.
 * Items are added to BACK of queue; items removed from FRONT of queue.
 * Constructor specifies the max number of items taht can be stored
 *  at any time; a QueueFullException is thrown if an attempt is 
 *  made to insert an item into a full Queue object.
 * For simplicity, the Queue only stores int's.
 */
public class Queue {

    /* 
       Implementation uses a fixed-size array; as we add and remove
       items we may end up "wrarpping* around the end of array.
       However, testing does NOT need to take this into account: just
       test that filling the array behaves properly ...
    */
    private int[] data; // array storing the queue of data
    int first;          // index of first item currently stored
    int size;           // number of items currenly held in queue

    /**
     * Constructor: this constructs a Queue object of a specified fixed size
     */
    public Queue(int init_size) {
	data = new int[init_size];
	first = 0;
	size = 0;
    }

    /**
     * returns current size of queue, i.e., number of items currently in queue
     */
    public int size() {
	return size;
    }

    /**
     * add an item (an int)
     * new item this is added to the BACK of the queue --- i.e., it will
     *   be returned after all current items in queue are returned
     */
    public void add(int new_item) throws QueueFullException {
	// first check if queue is full OR if we've reached end of queue
	if (size() > data.length)
	    throw new QueueFullException();

	int last = first + size;
	// we know there is space, so if adding this item takes us past
	//   the end of the array, then there must be room at the start
	if (last >= data.length)
	    last = last - data.length;
	data[last] = new_item;
	// update number of items held in queue
	size++;
    }

    /**
     * remove an item --- this is removed from the FRONT of the queue
     *   ie., all remaining items in quere were added after this one
     */
    public int remove() throws QueueEmptyException {
	// first check that the queue is not empty
	if (size() < 1)
	    throw new QueueEmptyException();

	// not empty, so remove the item in position "first"
	int item = data[first];
	first++;
	// if updating the index of "first" takes us past the
	//   end of the array, then wrap it around to the front
	if (first >= data.length)
	    first = 0;
	// update the number of items left and return the item
	size--;
	return item;
    }

    /**
     * Utility function that could/should be useful for writing tests
     */
    public int getFirst() throws QueueEmptyException {
	if (size() < 1)
	    throw new QueueEmptyException();

	return data[first];
    }

    /**
     * Utility function that could/should be useful for testing
     */
    public int getLast() throws QueueEmptyException {
	if (size() < 1)
	    throw new QueueEmptyException();

	int last = first+size-1;
	if (last >= data.length)
	    last = last - data.length;
	return data[last];
    }


    /*
     * The following main() project illustrates usage of the above
     *  methods, which may provide hints to writing unit tests
     */
    public static void main(String[] params) {
	Queue q = new Queue(2);
	try {
	    q.add(3); System.out.println("Added 3");
	    System.out.println("first " + q.getFirst());
	    System.out.println("last " + q.getLast());
	    q.add(4); System.out.println("Added 4");
	    System.out.println("first " + q.getFirst());
	    System.out.println("last " + q.getLast());
	    System.out.println("removed: " + q.remove());
	    System.out.println("first " + q.getFirst());
	    System.out.println("last " + q.getLast());
	    q.add(5); System.out.println("Added 5");
	    System.out.println("first " + q.getFirst());
	    System.out.println("last " + q.getLast());
	    q.add(6); System.out.println("Added 6");
	} catch (Exception e) {
	    System.out.println("exception: " + e);
	}
    }
	
}