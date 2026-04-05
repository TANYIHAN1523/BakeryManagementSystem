public class Bread  extends Product {
    private boolean isSliced;
    
    public Bread(String pID, String pName, double bPrice, int qInStock, String cat, boolean iSl) {
        super(pID, pName, bPrice, qInStock, cat);
        isSliced = iSl;
    }
    
    @Override
    public void displayProduct() {
        System.out.println("Bread: " + getName());
        System.out.println("Price: RM" + getPrice());
        
        if(isSliced) {
            System.out.println("Type: Sliced Loaf");
        }
        else {
            System.out.println("Type: Whole Loaf");
        }
        System.out.println("Available Stock: " + getQuantityInStock());
    }
    
    public boolean getIsSliced() { return isSliced; }
}