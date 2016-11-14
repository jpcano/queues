import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] q;
    private int n;

    public RandomizedQueue() {
        q = (Item[]) new Object[1];
        n = 0;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }

    private void resize(int size) {
        Item[] temp = (Item[]) new Object[size];
        for (int i = 0; i < q.length; i++) {
            temp[i] = q[i];
        }
        q = temp;
    }
    
    public void enqueue(Item item) {
        if (item == null) throw new NullPointerException("Cannot add null item");
        if (n == q.length) resize(2 * q.length);
        q[n++] = item;
    }

    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException("Deque underflow");
        int pos = StdRandom.uniform(n);
        Item temp = q[pos];
        q[pos] = q[--n];
        return temp;
    }

    public Item sample() {
        if (isEmpty()) throw new NoSuchElementException("Deque underflow");
        int pos = StdRandom.uniform(n);
        return q[pos];
    }

    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        private Item[] shuffled;
        private int i = n;
        
        public ListIterator() {
            shuffled = (Item[]) new Object[n];
            for (int j = 0; j < i; j++)
                shuffled[i] = q[i];
            StdRandom.shuffle(shuffled);
        }
            
        public boolean hasNext() {
            return i > 0;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException("No more elements");
            return shuffled[--i];
        }
    }

    public static void main(String[] args) {
        RandomizedQueue<Integer> q = new RandomizedQueue<Integer>();
        
        System.out.println("Adding elements...");
        for (int i = 0; i < 10; i++) {
            q.enqueue(i);
        }       
        System.out.println("Printing with iterator...");
        for (int i: q)
            System.out.println(i);
        System.out.println("Removing elements...");
        for (int i = 0; i < 10; i++) {
            System.out.println(q.dequeue());
        }
    }
}
