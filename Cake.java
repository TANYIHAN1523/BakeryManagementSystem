public class Cake extends Product {
    private String customMessage;
    private double weight;
    
    public Cake(String pID, String pName, double bPrice, int qInStock, String cat, String cMsg, double wt) {
        super(pID, pName, bPrice, qInStock, cat);
        customMessage = cMsg;
        weight = wt;
        
    } 
    
    @Override
    public void displayProduct() {
        System.out.println("Cake: " + getName());
        System.out.println("Price: RM" + getPrice());
        System.out.println("Weight: " + weight + "kg");
        System.out.println("Message on cake: " + customMessage);
        System.out.println("Available Stock: " + getQuantityInStock());
    }
    
    public String getCustomMessage() {
        return customMessage;
    }
    
    public double getWeight() {
        return weight;
    }
}