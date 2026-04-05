import java.util.ArrayList;

public class Cart {

    private ArrayList<CartItem> cartItems;

    public Cart() {
        cartItems = new ArrayList<>();

    public boolean addItem(Product product, int quantity) {

        if (quantity <= 0 || quantity > 99) {
            System.out.println("  Invalid quantity. Must be between 1 and 99.");
            return false;
        }

        if (!product.isAvailable(quantity)) {
            System.out.println("  Sorry, not enough stock for \"" + product.getName()
                    + "\". Available: " + product.getQuantityInStock());
            return false;
        }


        for (CartItem ci : cartItems) {
            if (ci.getItem().getID().equals(product.getID())) {
                // Check combined quantity still fits in stock
                int newTotal = ci.getQuantity() + quantity;
                if (!product.isAvailable(newTotal)) {
                    System.out.println("  Cannot add " + quantity + " more. Only "
                            + product.getQuantityInStock() + " in stock total.");
                    return false;
                }
                ci.setQuantity(newTotal);
                return true;
            }
        }


        cartItems.add(new CartItem(product, quantity));
        return true;
    }

    public boolean removeItem(int displayNumber) {
        int index = displayNumber - 1;
        if (index >= 0 && index < cartItems.size()) {
            cartItems.remove(index);
            return true;
        }
        return false;
    }

    public void clear() {
        cartItems.clear();
    }

    public double getTotal() {
        double total = 0;
        for (CartItem ci : cartItems) {
            total += ci.getSubtotal();
        }
        return total;
    }

    public boolean             isEmpty()      { return cartItems.isEmpty(); }
    public ArrayList<CartItem> getCartItems() { return cartItems;           }

    public void displayCart() {
        System.out.println();
        System.out.println("  ==========================================");
        System.out.println("               YOUR CART                    ");
        System.out.println("  ==========================================");

        if (cartItems.isEmpty()) {
            System.out.println("  Cart is empty.");
        } else {
            for (int i = 0; i < cartItems.size(); i++) {
                System.out.printf("  %d.%s%n", (i + 1), cartItems.get(i));
            }
            System.out.println("  ------------------------------------------");
            System.out.printf ("  Total : RM%.2f%n", getTotal());
        }

        System.out.println("  ==========================================");
    }
}
