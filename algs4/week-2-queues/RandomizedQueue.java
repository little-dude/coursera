/******************************************************************************
 *  Compilation:  javac RandomizedQueue.java
 *  Execution:    N/A
 *  Dependencies:
 ******************************************************************************/
import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Assert;
import org.junit.Test;

public class RandomizedQueue<Item> implements Iterable<Item> {

    public class RandomizedQueueIterator<Items> implements Iterator<Item> {
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return sample();
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public boolean hasNext() {
            if (size == 0) {
                return false;
            } else {
                return true;
            }
        }
    }

    public class Node {
        Item item;
        Node next;

        public Node(Item item_, Node next_) {
            if (item_ == null) {
                throw new IllegalArgumentException();
            }
            item = item_;
            next = next_;
        }
    }

    Node tail;
    int size;

    public RandomizedQueue() {
        tail = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public void enqueue(Item item) {
        Node newNode = new Node(item, tail);
        tail = newNode;
        size++;
    }

    public Item dequeue() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        // FIXME: is it inclusive?
        int index = StdRandom.uniform(size);
        return remove(index);
    }

    private Item remove(int index) {
        Item item;
        if (index == 0) {
            item = tail.item;
            tail = tail.next;
        } else {
            Node parent = tail;
            for (int i=0; i<index-1; i++) {
                parent = parent.next;
            }
            item = parent.next.item;
            parent.next = parent.next.next;
        }
        size--;
        return item;
    }

    private Item get(int index) {
        Node node = tail;
        for (int i=0; i<index-1; i++) {
            node = node.next;
        }
        return node.item;
    }

    public Item sample() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        int index = StdRandom.uniform(size);
        return get(index);
    }

    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator<Item>();
    }

    @Test
    public void testNewRandomizedQueue() {
        RandomizedQueue<Integer> q = new RandomizedQueue<>();
        Assert.assertEquals(q.tail, null);
        Assert.assertEquals(0, q.size);
    }

    @Test
    public void testEnqueue() {
        RandomizedQueue<Integer> q = new RandomizedQueue<>();

        q.enqueue(0);
        Assert.assertEquals(1, q.size);
        Assert.assertEquals(null, q.tail.next);
        Assert.assertEquals(0, (int)q.tail.item);

        q.enqueue(1);
        Assert.assertEquals(2, q.size);
        Assert.assertEquals(1, (int)q.tail.item);
        Assert.assertEquals(0, (int)q.tail.next.item);
        Assert.assertEquals(null, q.tail.next.next);
    }

    @Test
    public void testRemove() {
        RandomizedQueue<Integer> q = new RandomizedQueue<>();
        q.enqueue(0);
        q.enqueue(1);
        q.enqueue(2);

        q.remove(1);
        Assert.assertEquals(null, q.tail.next.next);
        Assert.assertEquals(2, (int)q.tail.item);
        Assert.assertEquals(0, (int)q.tail.next.item);
        Assert.assertEquals(2, q.size);

        q.remove(0);
        Assert.assertEquals(null, q.tail.next);
        Assert.assertEquals(0, (int)q.tail.item);
        Assert.assertEquals(1, q.size);

        q.remove(0);
        Assert.assertEquals(null, q.tail);
        Assert.assertEquals(0, q.size);
    }

    @Test
    public void testGet() {
        RandomizedQueue<Integer> q = new RandomizedQueue<>();
        q.enqueue(0);
        q.enqueue(1);
        q.enqueue(2);

        Assert.assertEquals(null, q.tail.next.next.next);
        Assert.assertEquals(3, q.size);

        Assert.assertEquals(2, (int)q.tail.item);
        Assert.assertEquals(1, (int)q.tail.next.item);
        Assert.assertEquals(0, (int)q.tail.next.next.item);
    }
}
