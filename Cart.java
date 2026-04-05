import java.util.ArrayList;

/**
 * The customer's shopping cart — holds CartItems before an order is placed.
 *
 * OO Concepts:
 *  - Encapsulation : private ArrayList managed via public methods
 *  - ArrayList     : dynamically stores CartItem objects
 */
public class Cart {

    private ArrayList<CartItem> cartItems;

    public Cart() {
        cartItems = new ArrayList<>();
    }

    /**
     * Adds a Product to the cart.
     *
     * Uses Product.isAvailable() to check stock before adding.
     * If the item is already in the cart, increases its quantity instead.
     *
     * @param product  the Product to add
     * @param quantity number of units
     * @return true if added successfully, false if quantity is invalid or out of stock
     */
    public boolean addItem(Product product, int quantity) {
        // Validate quantity range
        if (quantity <= 0 || quantity > 99) {
            System.out.println("  Invalid quantity. Must be between 1 and 99.");
            return false;
        }

        // Check stock using Product's own isAvailable() method
        if (!product.isAvailable(quantity)) {
            System.out.println("  Sorry, not enough stock for \"" + product.getName()
                    + "\". Available: " + product.getQuantityInStock());
            return false;
        }

        // Check if this product is already in the cart
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

        // Product not in cart yet -- add a new CartItem
        cartItems.add(new CartItem(product, quantity));
        return true;
    }

    /**
     * Removes a cart entry by its displayed number (1-based).
     *
     * @param displayNumber the 1-based number shown in the cart view
     * @return true if removed, false if number is out of range
     */
    public boolean removeItem(int displayNumber) {
        int index = displayNumber - 1; // convert 1-based display to 0-based index
        if (index >= 0 && index < cartItems.size()) {
            cartItems.remove(index);
            return true;
        }
        return false;
    }

    /**
     * Clears all items from the cart (called after a successful order).
     */
    public void clear() {
        cartItems.clear();
    }

    /**
     * Calculates the total price of all items in the cart.
     *
     * @return total in RM
     */
    public double getTotal() {
        double total = 0;
        for (CartItem ci : cartItems) {
            total += ci.getSubtotal();
        }
        return total;
    }

    public boolean             isEmpty()      { return cartItems.isEmpty(); }
    public ArrayList<CartItem> getCartItems() { return cartItems;           }

    /**
     * Prints all cart contents to the console.
     */
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
