import java.util.ArrayList;

public class OrderManager {

    private ArrayList<Order> orders;

    public OrderManager() {
        orders = new ArrayList<>();
    }

    public void addOrder(Order order) {
        orders.add(order);
    }

    public boolean isEmpty() { return orders.isEmpty(); }

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
