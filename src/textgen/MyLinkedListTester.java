package textgen;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MyLinkedListTester {
	private static final int LONG_LIST_LENGTH =10; 
	MyLinkedList<String> shortList;
	MyLinkedList<Integer> emptyList;
	MyLinkedList<Integer> longerList;
	MyLinkedList<Integer> list1;
	MyLinkedList<String> myName;
	MyLinkedList<String> mySocial;

	@Before
	public void setUp() throws Exception {
		// Feel free to use these lists, or add your own
	    shortList = new MyLinkedList<String>();
		shortList.add("A");
		shortList.add("B");
		
		emptyList = new MyLinkedList<Integer>();
		longerList = new MyLinkedList<Integer>();
		for (int i = 0; i < LONG_LIST_LENGTH; i++) {
			longerList.add(i);
		}
		list1 = new MyLinkedList<Integer>();
		list1.add(65);
		list1.add(21);
		list1.add(42);	
		
		myName = new MyLinkedList<String>();
		myName.add("P");
		myName.add("A");
		myName.add("T");
		myName.add("Y");
		myName.add(1, "SURPRISE");
		
		mySocial = new MyLinkedList<String>();
		mySocial.add("NOT");
		mySocial.add("GIVING");
		mySocial.add("IT");
		mySocial.set(0, "TOTALLY");
		
	}
	
	@Test
	public void testGet(){
		//test empty list, get should throw an exception
		try {
			emptyList.get(0);
			fail("Check out of bounds");
		} catch (IndexOutOfBoundsException e) {	
		}
		
		// test short list, first contents, then out of bounds
		assertEquals("Check first", "A", shortList.get(0));
		assertEquals("Check second", "B", shortList.get(1));
		
		try {
			shortList.get(-1);
			fail("Check out of bounds");
		} catch (IndexOutOfBoundsException e) {
		}
		
		try {
			shortList.get(2);
			fail("Check out of bounds");
		} catch (IndexOutOfBoundsException e) {
		}
		
		// test longer list contents
		for(int i = 0; i<LONG_LIST_LENGTH; i++ ) {
			assertEquals("Check " + i + " element", (Integer)i, longerList.get(i));
		}
		
		// test off the end of the longer array
		try {
			longerList.get(-1);
			fail("Check out of bounds");
		} catch (IndexOutOfBoundsException e) {
		}
		
		try {
			longerList.get(LONG_LIST_LENGTH);
			fail("Check out of bounds");
		} catch (IndexOutOfBoundsException e) {
		}
		
	}
	
	@Test
	public void testRemove() {
		int a = list1.remove(0);
		assertEquals("Remove: check a is correct ", 65, a);
		assertEquals("Remove: check element 0 is correct ", (Integer)21, list1.get(0));
		assertEquals("Remove: check size is correct ", 2, list1.size());
		
		// TODO: Add more tests here
	}
	
	@Test
	public void testAddEnd() {
		// Tests to see if adding a node works
        assertEquals("Check add method", "A", shortList.get(0));
        assertEquals("Check add method", "B", shortList.get(1));
        
        //Check longerList
        assertEquals("Check longerList add", (Integer)9, longerList.get(LONG_LIST_LENGTH - 1));
        
        // Check Integer List
        assertEquals("Check list1 add", (Integer)42, list1.get(2));
	}

	@Test
	public void testSize() {
		assertEquals("Check emptyList", 0, emptyList.size());
		assertEquals("Check shortList", 2, shortList.size());
		assertEquals("Check longList", LONG_LIST_LENGTH, longerList.size());
		assertEquals("Check list1", 3, list1.size());
	}
	
	@Test
	public void testAddAtIndex() {
		// Test adding function
		assertEquals("Check myName add at index", "SURPRISE", myName.get(1));
		
		// Test next node, to make sure a shift has been performed when adding at an index
		assertEquals("Check the shifting of nodes", "A", myName.get(2));
		
		// TODO: Adding in an invalid (non-existent) index position
		try {
			myName.add(30, "VIRUS");
			fail("Adding in an invalid (non-existent) index position");
		} catch (IndexOutOfBoundsException e) {
		}	
	}
	
	@Test
	public void testSet() {
		// Set data(replace) at specific index location
		assertEquals("Check set at specific index", "TOTALLY", mySocial.get(0));
		
		// Try and set data(replace) at an invalid index location
		try {
			mySocial.set(9, "INFILTRATION!");
			fail("Setting a value to an invalid index location");
		} catch (IndexOutOfBoundsException e) {
		}
	}
}