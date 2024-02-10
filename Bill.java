
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class Bill implements SimpleIterable<BillItem> {
    private List<BillItem> items;

    public Bill() {
        this.items = new ArrayList<>();
    }

    public void addItem(BillItem item) {
        items.add(item);
    }

    @Override
    public CopyableIterator<BillItem> iterator() {
        return new BillIterator();
    }

    private class BillIterator implements CopyableIterator<BillItem> {
        private int currentIndex = 0;

        public BillIterator() {
        }

        public BillIterator(int startIndex) {
            this.currentIndex = startIndex;
        }

        @Override
        public boolean hasNext() {
            return currentIndex < items.size();
        }

        @Override
        public BillItem next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No more elements in the bill.");
            }
            return items.get(currentIndex++);
        }

        @Override
        public CopyableIterator<BillItem> copy() {
            return new BillIterator(this.currentIndex);
        }
    }
}
