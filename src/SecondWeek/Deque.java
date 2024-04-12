package SecondWeek;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private Node first = null;
    private Node last = null;
    private int arraySize = 0;

    private class Node {
        Item item;
        Node next;
    }

    // construct an empty deque
    public Deque() {

    }

    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        return arraySize;
    }

    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }

        Node oldFirst = first;
        first = new Node();
        first.item = item;
        if (isEmpty()) {
            first = last;
        } else {
            first.next = oldFirst;
        }
        arraySize++;
    }

    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        Node oldLast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        if (isEmpty()) {
            first = last;
        } else {
            oldLast.next = last;
        }
        arraySize++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty()) throw new NoSuchElementException();
        Item item = first.item;
        first = first.next;
        if (isEmpty()) last = null;
        arraySize--;
        return item;
    }

    public Item removeLast() {
        if (isEmpty()) throw new NoSuchElementException();

        // Default assumption when only one element or error in logic
        Item item = null;

        if (first == last) {
            item = first.item;
            first = last = null;
        } else {
            Node secondLast = first;
            // Traverse until the second-to-last node
            while (secondLast.next != null && secondLast.next.next != null) {
                secondLast = secondLast.next;
            }

            if (secondLast.next != null) {
                item = secondLast.next.item;
                secondLast.next = null;
                last = secondLast; // Update the last pointer to the new last node (secondLast)
            }
        }

        arraySize--;
        return item; 
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new Iterator<>() {
            private Node current = first;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public Item next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                Item item = current.item;
                current = current.next;
                return item;
            }
        };
    }

    public static void main(String[] args) {
        Deque<String> deque = new Deque<>();

        // Test isEmpty on a new deque
        assert deque.isEmpty() : "Deque should be empty initially";

        deque.addFirst("1");
        deque.addFirst("2");
        deque.iterator();

        deque.addLast("3");
        deque.addLast("4");
        assert deque.size() == 4 : "Deque should contain 4 items";

        assert deque.removeFirst().equals("2") : "removeFirst should return '2'";
        assert deque.removeLast().equals("4") : "removeLast should return '4'";

        Iterator<String> iterator = deque.iterator();
        assert iterator.hasNext() : "Iterator should have next";
        assert iterator.next().equals("1") : "Iterator next should return '1'";
        assert iterator.next().equals("3") : "Iterator next should return '3'";
        assert !iterator.hasNext() : "Iterator should have no more elements";

        assert !deque.isEmpty() : "Deque should not be empty before clearing";

        deque.removeFirst(); // Should remove '1'
        deque.removeLast();  // Should remove '3', which was at the front after previous removal

        assert deque.isEmpty() : "Deque should be empty after removing all elements";

        System.out.println("All tests passed!");
    }
}
