public class Pastry extends Product {
    private PastryType type;
    
    public Pastry(String pID, String pName, double bPrice, int qInStock, String cat, PastryType type) {
    super(pID, pName, bPrice, qInStock, cat);
    this.type = type;
    }
    
    @Override
    public void displayProduct() {
        System.out.println("Pastry: " + getName());
        System.out.println("Price: RM" + getPrice());
        System.out.println("Type: " + type);
        System.out.println("Available Stock: " + getQuantityInStock());
    }
    
    public PastryType getType() {
        return type;
    }
}