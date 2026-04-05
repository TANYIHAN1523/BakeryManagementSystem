public abstract class Product {
        private String productID;
        private String productName;
        private double basePrice;
        private int quantityInStock;
        private String category;
        
        public Product(String pID, String pName, double bPrice, int qInStock, String cat) {
            productID = pID;
            productName = pName;
            basePrice = bPrice;
            quantityInStock = qInStock;
            category = cat;
        }
        
        public void updateStock(int quantity){
            quantityInStock += quantity;
        }
        
        public boolean isAvailable(int requestedQty){
            return quantityInStock >= requestedQty;
        }
        
        public abstract void displayProduct();
        
        public String getID() {
            return productID;
        }
        
        public String getName() {
            return productName;
        }
        
        public double getPrice() {
            return basePrice;
        }
        
        public int getQuantityInStock() {
            return quantityInStock;
        }
        
        public String getCategory() {
            return category;
        } 
        
}