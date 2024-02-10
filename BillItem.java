public class BillItem {
    private String itemName;
    private double cost;

    public BillItem(String itemName, double cost) {
        this.itemName = itemName;
        this.cost = cost;
    }

    public String getItemName() {
        return itemName;
    }

    public double getCost() {
        return cost;
    }
}
