package textgen;
import java.util.AbstractList;

public class MyLinkedList<E> extends AbstractList<E> {
	LLNode<E> headNode;
	LLNode<E> tailNode;
	int size;

	/** Create a new empty LinkedList */
	public MyLinkedList() {
		headNode = new LLNode<E>();
		tailNode = new LLNode<E>();
		headNode.nextNode = tailNode;
		tailNode.previousNode = headNode;
		size = 0;
	}

	/**
	 * Appends an element to the end of the list
	 * @param element The element to add
	 */
	public boolean add(E element ) {
		LLNode<E> newNode = new LLNode<>(element);
		// New Node Pointers
		newNode.previousNode = tailNode.previousNode;
		newNode.nextNode = headNode;
		
		// Set pointer for the previous last valid node
		tailNode.previousNode.nextNode = newNode;
		// TailNode now points to the newNode as it's previous node.
		tailNode.previousNode = newNode;
		//Increment Size Of List
		size++;
		return true;
	}

	/** Get the element at position index 
	 * @throws IndexOutOfBoundsException if the index is out of bounds. */
	public E get(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException(String.format("Index %s out of range", index));
		}
		// Start the loop at head, and continue until index and 'i' match. 
		LLNode<E> nodeNeeded = new LLNode<E>();
		LLNode<E> currentNode = headNode;
		for (int i = 0; i < size; i++) {
			currentNode = currentNode.nextNode;
			if (i == index) {
				nodeNeeded = currentNode;
				break;
			}
		}
		return nodeNeeded.getData();
	}

	/**
	 * Add an element to the list at the specified index
	 * @param The index where the element should be added
	 * @param element The element to add
	 */
	public void add(int index, E element ) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException(String.format("Index %s out of range", index));
		}
		// New node that will be inserted at index given value element
		LLNode<E> newAddition = new LLNode<E>(element);
		// Next, we will initialize a node that will represent the current node at that index
		// and will set it by looking for that node using a loop
		LLNode<E> nodeAtCurrentIndex = headNode;
		for (int i = 0; i < size; i++) {
			nodeAtCurrentIndex = nodeAtCurrentIndex.nextNode;
			if (i == index) {
				break;
			}		
		}
		// Now we have the node that's in the index we want to add our new node
		// We will push all nodes up in a sense. So the currentNode will be pushed ahead (index + 1)
		newAddition.previousNode = nodeAtCurrentIndex.previousNode;
		newAddition.nextNode = nodeAtCurrentIndex;
		// The node previous to the currentNode, it's next node must now point to our newNode;
		nodeAtCurrentIndex.previousNode.nextNode = newAddition;
		// nextNode stays the same for currentNode, but previousNode will change for currentNode to the newNode.
		nodeAtCurrentIndex.previousNode = newAddition;
		// Increment size
		size++;	
	}

	/** Return the size of the list */
	public int size() {
		return size;
	}

	/** Remove a node at the specified index and return its data element.
	 * @param index The index of the element to remove
	 * @return The data element removed
	 * @throws IndexOutOfBoundsException If index is outside the bounds of the list
	 * 
	 */
	public E remove(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException(String.format("Index %s out of range", index));
		}
		// Locate node that needs to be removed!
		LLNode<E> toBeRemoved = headNode;
		for (int i = 0; i < size; i++) {
			toBeRemoved = toBeRemoved.nextNode;
			if (i == index) {
				break;
			}
		}
		// First, Set the pointer behind it, point to what's ahead of it.
		toBeRemoved.previousNode.nextNode = toBeRemoved.nextNode;
		// Now set the pointer in front of it, to point to what's behind the original pointer
		toBeRemoved.nextNode.previousNode = toBeRemoved.previousNode;
		size--;
				
		return toBeRemoved.getData();
	}

	/**
	 * Set an index position in the list to a new element
	 * @param index The index of the element to change
	 * @param element The new element
	 * @return The element that was replaced
	 * @throws IndexOutOfBoundsException if the index is out of bounds.
	 */
	public E set(int index, E element) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException(String.format("Index %s out of range", index));
		}
		// We don't need to create a new node, but we do need a representation of node at given index
		LLNode<E> nodeToEdit = headNode;	
		for (int i =0; i < size; i++) {
			nodeToEdit = nodeToEdit.nextNode;
			if (i == index) {
				break;
			}
		}
		nodeToEdit.set(element);
		return nodeToEdit.getData();
	}   
}

class LLNode<E> {
	LLNode<E> previousNode;
	LLNode<E> nextNode;
	E data;

	public LLNode(E e) {
		this.data = e;
		this.previousNode = null;
		this.nextNode = null;
	}
	
	public LLNode() {
		this.data = null;
		this.previousNode = null;
		this.nextNode = null;
	}
	
	public E getData() {
		return this.data;
	}
	
	public void set(E element) {
		this.data = element;
	}
	
	public String toString() {
		return String.format("Data inside this node is: %s", this.data);
	}
	
}