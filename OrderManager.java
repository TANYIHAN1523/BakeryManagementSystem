import java.util.ArrayList;

/**
 * Stores all confirmed orders and displays order history.
 *
 * OO Concepts:
 *  - Encapsulation : private list of orders
 *  - ArrayList     : dynamically stores Order objects
 */
public class OrderManager {

    private ArrayList<Order> orders;

    public OrderManager() {
        orders = new ArrayList<>();
    }

    /**
     * Saves a confirmed order to the history.
     *
     * @param order the completed Order to record
     */
    public void addOrder(Order order) {
        orders.add(order);
    }

    public boolean isEmpty() { return orders.isEmpty(); }

    /**
     * Prints a summary list of all orders placed so far.
     */
    public void displayAllOrders() {
        System.out.println();
        System.out.println("  ==========================================");
        System.out.println("              ORDER HISTORY                  ");
        System.out.println("  ==========================================");

        if (orders.isEmpty()) {
            System.out.println("  No orders have been placed yet.");
        } else {
            for (Order order : orders) {
                System.out.printf("  Order #%d  |  %-20s  |  RM%.2f%n",
                        order.getOrderNumber(),
                        order.getCustomerName(),
                        order.getTotal());
            }
        }

        System.out.println("  ==========================================");
    }
}
