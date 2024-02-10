
// Class for splitting a list of integers into a subset that sums up to a target value.
public class BillSplitter {

    // Static method to find a subset of 'list' that sums up to 'targetSum'.
    public static UniqueOrderedList<Integer> split(UniqueOrderedList<Integer> list, int targetSum) {
        // Return null for invalid inputs or when the target sum is not achievable.
        if (list == null || list.size() == 0) {
            return targetSum == 0 ? new UniqueOrderedList<>() : null;
        }

        // Use an iterator to traverse through the list.
        CopyableIterator<Integer> iterator = list.iterator();
        // This will store the solution subset, if one exists.
        UniqueOrderedList<Integer> solution = new UniqueOrderedList<>();
        // Check if a solution exists using a recursive method.
        if (splitRecursive(iterator, targetSum, 0, solution)) {
            return solution; // Return the solution if found.
        } else {
            return null; // Return null if no solution exists.
        }
    }

    // Helper method to recursively find the subset sum solution.
    private static boolean splitRecursive(CopyableIterator<Integer> iterator, int targetSum, int currentSum, UniqueOrderedList<Integer> solution) {
        // Base case: if there are no more elements, check if the current sum matches the target sum.
        if (!iterator.hasNext()) {
            return currentSum == targetSum;
        }

        // Take the next item from the iterator.
        Integer item = iterator.next();
        // Make a copy of the iterator to explore different subsets.
        CopyableIterator<Integer> copyIterator = iterator.copy();

        // Include the current item in the solution and check if a solution is found with this inclusion.
        solution.add(item);
        if (splitRecursive(copyIterator, targetSum, currentSum + item, solution)) {
            return true; // Solution found with the current item included.
        }

        // Exclude the current item and backtrack to explore other subsets.
        solution.remove(item);
        return splitRecursive(iterator, targetSum, currentSum, solution);
    }
}
