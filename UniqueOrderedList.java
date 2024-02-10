
import java.util.NoSuchElementException;

// A class representing a unique ordered list that stores elements in a sorted manner without duplicates.
public class UniqueOrderedList<T extends Comparable<T>> implements UniqueOrderedListADT<T> {
    private LinearNode<T> head; // The head node of the list.
    private int size; // The size of the list, representing the number of elements.

    // Default constructor initializing an empty list.
    public UniqueOrderedList() {
        this.head = null;
        size = 0;
    }

    // Constructor that initializes the list with elements from an array.
    public UniqueOrderedList(T[] data) { 
        this();
        for (T datum : data) {
            this.add(datum); // Add each element in the array to the list.
        }
    }
    
    // Method to check if the list contains a specific element.
    public boolean contains(T element) { 
        LinearNode<T> current = this.head;
        // Traverse the list to find the element.
        while (current != null && current.getData().compareTo(element) <= 0)  {
            if (current.getData().equals(element)) { 
                return true;
            } else {
                current = current.getNext();
            }
        }
        return false;
    }

    // Method to add a new element to the list if it's not already present.
    public boolean add(T element) { 
        if (this.contains(element)) { 
            return false; // Element already exists, do not add.
        } else {
            // Insert the element in the correct order.
            if (this.head == null || this.head.getData().compareTo(element) > 0) {
                this.head = new LinearNode<>(element, this.head);
            } else {
                LinearNode<T> current = this.head;
                LinearNode<T> previous = null;
                while (current != null && current.getData().compareTo(element) < 0) { 
                    previous = current;
                    current = current.getNext();
                }
                if (previous != null) {
                    previous.setNext(new LinearNode<>(element, current));
                }
            }
            size++; // Increment the size of the list.
            return true;
        }
    }
    
    // Method to remove an element from the list.
    public boolean remove(T element) {
        if (head == null) {
            return false; // List is empty.
        }

        // Remove the head if it's the target element.
        if (head.getData().equals(element)) {
            head = head.getNext();
            size--;
            return true;
        }

        // Traverse the list to find and remove the element.
        LinearNode<T> current = head;
        while (current.getNext() != null) {
            if (current.getNext().getData().equals(element)) {
                current.setNext(current.getNext().getNext());
                size--;
                return true;
            }
            current = current.getNext();
        }

        return false; // Element not found.
    }
    
    // Getter for the size of the list.
    public int size() {
        return this.size;
    }

    // Provides an iterator for the list.
    public CopyableIterator<T> iterator() {
        return new UOLIterator(this.head);
    }

    // Inner class defining an iterator for UniqueOrderedList.
    private class UOLIterator implements CopyableIterator<T> {
        private LinearNode<T> current; // Current node in the iteration.

        // Constructor to initialize the iterator.
        public UOLIterator(LinearNode<T> startNode) {
            this.current = startNode;
        }

        @Override
        public boolean hasNext() {
            return current != null; // Checks if there is a next element in the list.
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException("iterator empty"); // Throw exception if no next element.
            }
            T data = current.getData();
            current = current.getNext();
            return data; // Return the next element.
        }

        @Override
        public CopyableIterator<T> copy() {
            return new UOLIterator(this.current); // Create a copy of the current iterator.
        }
    }
}
