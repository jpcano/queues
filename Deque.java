import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private Node first;
    private Node last;
    private int n;
    
    private class Node {
        private Item item;
        private Node next;
        private Node prev;
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
        if (item == null) throw new NullPointerException("Cannot add null item");
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
        first.prev = null;
        if (isEmpty())
            last = first;
        else
            oldfirst.prev = first;
        n++;
    }

    public void addLast(Item item) {
        if (item == null) throw new NullPointerException("Cannot add null item");
        Node oldlast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        last.prev = oldlast;
        if (isEmpty())
            first = last;
        else
            oldlast.next = last;
        n++;
    }

    public Item removeFirst() {
        if (isEmpty()) throw new NoSuchElementException("Deque underflow");
        Item item = first.item;
        first = first.next;
        if (size() > 1) first.prev = null;
        n--;
        return item;
    }

    public Item removeLast() {
        if (isEmpty()) throw new NoSuchElementException("Deque underflow");
        Item item = last.item;
        last = last.prev;
        if (size() > 1) last.next = null;
        n--;
        return item;
    }

    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }
        public void remove() {
            throw new UnsupportedOperationException();
        }
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException("No more elements");
            Item item = current.item;
            current = current.next;
            return item;
        }
    }
    
    public static void main(String[] args) {
        Deque<Integer> d = new Deque<Integer>();
        
        System.out.println("Add at front...");
        for (int i = 0; i < 10; i++) {
            d.addFirst(i);
        }
        for (int i: d)
            System.out.println(i);
        System.out.println("...remove at end");
        for (int i = 0; i < 10; i++) {
            System.out.println(d.removeLast());
        }

        System.out.println("Add at end...");
        for (int i = 0; i < 10; i++) {
            d.addLast(i);
        }
        for (int i: d)
            System.out.println(i);
        System.out.println("...remove at front");
        for (int i = 0; i < 10; i++) {
            System.out.println(d.removeFirst());
        }
        System.out.println(d.removeFirst());
    }
}
