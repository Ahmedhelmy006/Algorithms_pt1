package SecondWeek;

import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] items;
    private int size;

    // Constructor
    public RandomizedQueue() {
        items = (Item[]) new Object[2];
        size = 0;
    }

    // Resize the array
    private void resize(int capacity) {
        Item[] copy = (Item[]) new Object[capacity];
        if (size >= 0) System.arraycopy(items, 0, copy, 0, size);
        items = copy;
    }

    public boolean isEmpty() {
        return size == 0;
    }


    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Item cannot be null");
        }
        if (size == items.length) {
            resize(2 * items.length);
        }
        items[size++] = item;
    }

    public Item dequeue() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException("Queue is empty");
        }
        int randomIndex = StdRandom.uniformInt(size);
        Item item = items[randomIndex];
        items[randomIndex] = items[--size];
        items[size] = null;
        if (size > 0 && size == items.length / 4) {
            resize(items.length / 2);
        }
        return item;
    }

    public Item sample() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException("Queue is empty");
        }
        int randomIndex = StdRandom.uniformInt(size);
        return items[randomIndex];
    }

    @Override
    public java.util.Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    private class RandomizedQueueIterator implements java.util.Iterator<Item> {
        private int current;
        private final int[] indices;

        RandomizedQueueIterator() {
            current = 0;
            indices = new int[size];
            for (int i = 0; i < size; i++) {
                indices[i] = i;
            }
            StdRandom.shuffle(indices);
        }

        public boolean hasNext() {
            return current < size;
        }

        public void remove() {
            throw new UnsupportedOperationException("Remove operation is not supported");
        }

        public Item next() {
            if (!hasNext()) {
                throw new java.util.NoSuchElementException("No more elements to iterate");
            }
            return items[indices[current++]];
        }
    }

    public static void main(String[] args) {
        // Test the RandomizedQueue implementation
    }
}
