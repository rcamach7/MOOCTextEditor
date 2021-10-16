package textgen;

public class App {

	public static void main(String[] args) {
		MyLinkedList<String> myList = new MyLinkedList<String>();
		myList.add("ONE");
		myList.add("TWO");
		myList.add("THREE");
		
		myList.remove(2);		
		System.out.println(myList.get(0));
		System.out.println(myList.get(1));
		System.out.println(myList.size);
	}

}
