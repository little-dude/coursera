/******************************************************************************
 *  Compilation:  javac Deque.java
 *  Execution:    N/A
 *  Dependencies:
 ******************************************************************************/
import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Assert;
import org.junit.Test;

public class Deque<Item> implements Iterable<Item> {

    private Item[] array;
    private int head;
    private int tail;

    private class DequeIterator<Items> implements Iterator<Item> {
        private int position;

        public DequeIterator() {
            position = head;
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Item item = array[position];
            if (position == array.length - 1) {
                position = 0;
            } else {
                position++;
            }
            return item;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public boolean hasNext() {
            if (isEmpty()) {
                return false;
            }

            return ((head == tail && head == position)
                    || (head > tail && !(position < head && position > tail))
                    || (head < tail && (position >= head && position <= tail)));
        }
    }

    // Grader reject solutions with @SuppressWarnings but I don't know how to
    // do without it
    // @SuppressWarnings("unchecked")
    public Deque() {
        head = -1;
        tail = -1;
        array = (Item[]) new Object[4];
    }

    public int size() {
        if (head == -1 || tail == -1) {
            return 0;
        } else {
            if (tail >= head) {
                return tail - head + 1;
            } else {
                return array.length - head + tail + 1;
            }
        }
    }

    public boolean isEmpty() {
        return (size() == 0);
    }

    private boolean isFull() {
        return (size() == array.length);
    }

    private boolean shouldShrink() {
        return ((4*size() <= array.length) && (array.length > 4));
    }

    private void extend() {
        resize(2*array.length);
    }

    private void shrink() {
        resize(array.length/2);
    }

    // Grader reject solutions with @SuppressWarnings but I don't know how to
    // do without it
    // @SuppressWarnings("unchecked")
    private void resize(int capacity) {
        Item[] newArray = (Item[]) new Object[capacity];

        // the array is empty
        if (head == -1 || tail == -1) {
            array = newArray;
            return;
        }

        if (head > tail) {
            for (int i = head; i < array.length; i++) {
                newArray[i-head] = array[i];
            }
            for (int i = 0; i <= tail; i++) {
                newArray[i + array.length - head] = array[i];
            }
            tail = array.length + tail - head;
            head = 0;
        } else {
            for (int i = head; i <= tail; i++) {
                newArray[i-head] = array[i];
            }
            tail = tail - head;
            head = 0;
        }
        array = newArray;
    }

    private void validate(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
    }

    public void addFirst(Item item) {
        validate(item);

        if (isFull()) {
            extend();
        }

        if (isEmpty()) {
            head = 0;
            tail = 0;
        } else if (head == 0) {
            head = array.length - 1;
        } else {
            head--;
        }
        array[head] = item;
    }

    public void addLast(Item item) {
        validate(item);

        if (isFull()) {
            extend();
        }

        if (isEmpty()) {
            head = 0;
            tail = 0;
        } else if (tail == array.length - 1) {
            tail = 0;
        } else {
            tail++;
        }
        array[tail] = item;
    }

    private Item remove(int index) {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        Item item = array[index];
        array[index] = null;
        return item;
    }

    public Item removeFirst() {
        Item item = remove(head);

        if (head == tail) {
            // that was the last item, the array is now empty
            head = -1;
            tail = -1;
        } else if (head == array.length - 1) {
            head = 0;
        } else {
            head++;
        }

        if (shouldShrink()) {
            shrink();
        }

        return item;
    }

    public Item removeLast() {
        Item item = remove(tail);

        if (head == tail) {
            // that was the last item, the array is now empty
            head = -1;
            tail = -1;
        } else if (tail == 0) {
            tail = array.length - 1;
        } else {
            tail--;
        }

        if (shouldShrink()) {
            shrink();
        }

        return item;
    }

    public Iterator<Item> iterator() {
        return new DequeIterator<Item>();
    }

    @Test
    public void testNewDeque() {
        Deque<Integer> q = new Deque<>();
        Integer[] expected = new Integer[4];
        Assert.assertArrayEquals(q.array, expected);
    }

    @Test
    public void testAddFirst() {
        Deque<Integer> q = new Deque<>();
        Integer[] expected = new Integer[4];

        q.addFirst(1);
        expected[0] = 1;
        check(q, expected, 0, 0);

        q.addFirst(2);
        expected[3] = 2;
        check(q, expected, 3, 0);

        q.addFirst(3);
        expected[2] = 3;
        check(q, expected, 2, 0);

        q.addFirst(4);
        expected[1] = 4;
        check(q, expected, 1, 0);
    }

    @Test
    public void testAddLast() {
        Deque<Integer> q = new Deque<>();
        Integer[] expected = new Integer[4];

        q.addLast(1);
        expected[0] = 1;
        check(q, expected, 0, 0);

        q.addLast(2);
        expected[1] = 2;
        check(q, expected, 0, 1);

        q.addLast(3);
        expected[2] = 3;
        check(q, expected, 0, 2);

        q.addLast(4);
        expected[3] = 4;
        check(q, expected, 0, 3);
    }

    @Test
    public void testRemoveFirst() {
        Deque<Integer> q = new Deque<>();
        Integer[] expected = new Integer[4];

        for (int i=0; i<4; i++) {
            // see https://stackoverflow.com/a/27593737/1836144
            ((Object[])q.array)[i] = i;
            expected[i] = i;
        }
        q.head = 2;
        q.tail = 1;

        Integer item = q.removeFirst();
        Assert.assertEquals((int)item, 2);
        expected[2] = null;
        check(q, expected, 3, 1);

        item = q.removeFirst();
        Assert.assertEquals((int)item, 3);
        expected[3] = null;
        check(q, expected, 0, 1);

        item = q.removeFirst();
        Assert.assertEquals((int)item, 0);
        expected[0] = null;
        check(q, expected, 1, 1);

        item = q.removeFirst();
        Assert.assertEquals((int)item, 1);
        expected[1] = null;
        check(q, expected, -1, -1);
    }

    @Test
    public void testRemoveLast() {
        Deque<Integer> q = new Deque<>();
        Integer[] expected = new Integer[4];

        for (int i=0; i<4; i++) {
            // see https://stackoverflow.com/a/27593737/1836144
            ((Object[])q.array)[i] = i;
            expected[i] = i;
        }
        q.head = 2;
        q.tail = 1;

        Integer item = q.removeLast();
        Assert.assertEquals((int)item, 1);
        expected[1] = null;
        check(q, expected, 2, 0);

        item = q.removeLast();
        Assert.assertEquals((int)item, 0);
        expected[0] = null;
        check(q, expected, 2, 3);

        item = q.removeLast();
        Assert.assertEquals((int)item, 3);
        expected[3] = null;
        check(q, expected, 2, 2);

        item = q.removeLast();
        Assert.assertEquals((int)item, 2);
        expected[2] = null;
        check(q, expected, -1, -1);
    }

    @Test
    public void addAndExtend() {
        /* We start from
         *
         * [0 1 2 3]
         *    ^ ^
         *    T H
         *
         * After adding 4 at the tail we expect:
         *
         * [2 3 0 1 4 null null null]
         *  ^       ^
         *  H       T
         */

        Deque<Integer> q = new Deque<>();
        Integer[] expected = new Integer[8];
        for (int i=0; i<4; i++) {
            // see https://stackoverflow.com/a/27593737/1836144
            ((Object[])q.array)[i] = i;
            expected[(i+2) % 4] = i;
        }
        q.head = 2;
        q.tail = 1;

        q.addLast(4);
        expected[4] = 4;
        check(q, expected, 0, 4);
    }

    @Test
    public void removeAndShrink() {
        /* We start from
         *
         * [0 1 2 null null null null null]
         *  ^   ^ 
         *  H   T
         *
         * After removing the last item, we should have
         *
         * [0 1 null null]
         *  ^ ^
         *  H T
         */

        Deque<Integer> q = new Deque<>();
        q.array = new Integer[8];
        q.head = 0;
        q.tail = 2;
        Integer[] expected = new Integer[4];
        for (int i=0; i<3; i++) {
            ((Object[])q.array)[i] = i;
            expected[i] = i;
        }
        expected[2] = null;

        q.removeLast();
        check(q, expected, 0, 1);
    }

    @Test(expected = NoSuchElementException.class)
    public void iteratorOnNewDeque() {
        Deque<Integer> q = new Deque<>();
        Iterator<Integer> iterator = q.iterator();
        iterator.next();
    }

    @Test(expected = NoSuchElementException.class)
    public void iteratorAfterAddFirst() {
        Deque<Integer> q = new Deque<>();
        q.addFirst(1);
        q.addFirst(2);
        Iterator<Integer> iterator = q.iterator();
        Assert.assertEquals((Integer) 2, iterator.next());
        Assert.assertEquals((Integer) 1, iterator.next());
        iterator.next();
    }

    @Test(expected = NoSuchElementException.class)
    public void iteratorAfterAddLast() {
        Deque<Integer> q = new Deque<>();
        q.addLast(1);
        q.addLast(2);
        Iterator<Integer> iterator = q.iterator();
        Assert.assertEquals((Integer) 1, iterator.next());
        Assert.assertEquals((Integer) 2, iterator.next());
        iterator.next();
    }

    @Test
    public void iteratorAfterMixedOperations() {
        Deque<Integer> q = new Deque<>();
        q.addFirst(1);
        q.removeLast();
        q.addLast(3);
        q.addLast(4);
        q.addFirst(5);

        Iterator<Integer> iterator = q.iterator();
        Assert.assertEquals((Integer) 5, iterator.next());
        Assert.assertEquals((Integer) 3, iterator.next());
        Assert.assertEquals((Integer) 4, iterator.next());
    }

    private void check(Deque<Integer> q, Integer[] arr, int head, int tail) {
        Assert.assertArrayEquals(arr, q.array);
        Assert.assertEquals(head, q.head);
        Assert.assertEquals(tail, q.tail);
    }
}

