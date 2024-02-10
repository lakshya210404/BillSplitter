

import java.util.Iterator;
import java.util.NoSuchElementException;

// A class representing an iterator for a unique ordered list (UOL).
public class UOLIterator<T extends Comparable<T>> implements CopyableIterator<T> {
    private LinearNode<T> current; // The current node in the iterator.

    // Constructor that initializes the iterator with a starting node.
    public UOLIterator(LinearNode<T> startNode) {
        this.current = startNode;
    }

    // Override of hasNext() method from the Iterator interface.
    @Override
    public boolean hasNext() {
        return current != null; // Returns true if there are more elements to iterate over.
    }

    // Override of next() method from the Iterator interface.
    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException("iterator empty"); // Throws an exception if there are no more elements.
        }
        T data = current.getData(); // Retrieves the data from the current node.
        current = current.getNext(); // Moves the iterator to the next node.
        return data; // Returns the data of the current node.
    }

    // Override of copy() method from the CopyableIterator interface.
    @Override
    public CopyableIterator<T> copy() {
        return new UOLIterator<>(this.current); // Creates and returns a new iterator that starts at the current node.
    }
}
