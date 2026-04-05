import java.util.ArrayList;

/**
 * Represents a confirmed order — created from the cart at checkout.
 *
 * When an order is placed, each product's stock is reduced using
 * Product.updateStock() to reflect the items sold.
 *
 * OO Concepts:
 *  - Encapsulation  : private fields, public getters
 *  - Static variable: orderCounter shared across all Order instances
 *  - ArrayList      : stores a snapshot of ordered items
 */
public class Order {

    // Static counter -- auto-numbers each order starting from 1001
    private static int orderCounter = 1000;

    private int                 orderNumber;
    private String              customerName;
    private ArrayList<CartItem> items; // snapshot of cart at time of order
    private double              total;

    /**
     * Creates an Order from the contents of a Cart.
     * Also deducts stock from each Product using updateStock().
     *
     * @param customerName name of the customer
     * @param cart         the cart being checked out
     */
    public Order(String customerName, Cart cart) {
        this.orderNumber  = ++orderCounter;
        this.customerName = customerName;
        this.total        = cart.getTotal();

        // Copy cart items into this order's own list (snapshot)
        this.items = new ArrayList<>();
        for (CartItem ci : cart.getCartItems()) {
            items.add(new CartItem(ci.getItem(), ci.getQuantity()));

            // Deduct the ordered quantity from the product's stock
            // updateStock() takes a positive number to ADD stock,
            // so we pass a negative value to deduct
            ci.getItem().updateStock(-ci.getQuantity());
        }
    }

    public int    getOrderNumber()  { return orderNumber;  }
    public String getCustomerName() { return customerName; }
    public double getTotal()        { return total;        }

    /**
     * Prints a formatted receipt for this order to the console.
     */
    public void displayReceipt() {
        System.out.println();
        System.out.println("  ==========================================");
        System.out.printf ("  Order #%d%n", orderNumber);
        System.out.printf ("  Customer : %s%n", customerName);
        System.out.println("  ------------------------------------------");

        for (CartItem ci : items) {
            System.out.println(ci);
        }

        System.out.println("  ------------------------------------------");
        System.out.printf ("  TOTAL PAID : RM%.2f%n", total);
        System.out.println("  Thank you! Please collect at the counter.");
        System.out.println("  ==========================================");
    }
}
