public class Deque<Item> {
    private Node first;
    private Node last;
    private int n;
    
    private class Node {
	Item item;
	Node next;
    }

    public Deque() {
	first = null;
	last = null;
	n = 0;
    }

    public boolean isEmpty() {
	return n == 0;
    }

    public int size() {
	return n;
    }

    public void addFirst(Item item) {
	Node oldfirst = first;
	first = new Node();
	first.item = item;
	first.next = oldfirst;
	if (isEmpty())
	    last = first;
	n++;
    }

    public void addLast(Item item) {
	Node oldlast = last;
	last = new Node();
	last.item = item;
	last.next = null;
	if (isEmpty())
	    first = last;
	else
	    oldlast.next = last;
	n++;
    }

    public Item removeFirst() {
	Item item = first.item;
	first = first.next;
	n--;
	return item;
    }

    // public Item removeLast() {
    // 	Item item = last.item;
    // 	first = first.next;
    // 	n--;
    // 	return item;
    // }
    
    public static void main(String[] args) {
	 System.out.println("Test output");
    }
}
