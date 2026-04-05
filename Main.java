import java.util.ArrayList;
import java.util.Scanner;

/**
 * Entry point and menu controller for the Bakery Management System.
 *
 * Concepts demonstrated:
 *  - Polymorphism : ArrayList<Product> holds Bread, Cake, and Pastry objects
 *  - Selection    : switch statements for menu choices
 *  - Loops        : while loops keep menus running until user exits
 *  - ArrayList    : used for the product menu list
 */
public class Main {

    static Scanner              scanner      = new Scanner(System.in);
    static ArrayList<Product>   menu         = new ArrayList<>();  // polymorphic list
    static Cart                 cart         = new Cart();
    static OrderManager         orderManager = new OrderManager();

    public static void main(String[] args) {
        loadMenu();
        printBanner();

        boolean running = true;
        while (running) {
            printMainMenu();
            int choice = getIntInput("Enter your choice: ");

            switch (choice) {
                case 1:
                    viewMenu();
                    break;
                case 2:
                    addToCart();
                    break;
                case 3:
                    cart.displayCart();
                    break;
                case 4:
                    removeFromCart();
                    break;
                case 5:
                    placeOrder();
                    break;
                case 6:
                    orderManager.displayAllOrders();
                    break;
                case 7:
                    System.out.println("\n  Thank you for visiting Sweet Crumbs Bakery! Goodbye!");
                    running = false;
                    break;
                default:
                    System.out.println("  Invalid choice. Please enter 1 to 7.");
            }
        }

        scanner.close();
    }

    // ======================================================================
    // MENU DATA
    // ======================================================================

    /**
     * Pre-loads the bakery's products into the menu ArrayList.
     * Demonstrates polymorphism: Bread, Cake, Pastry objects stored as Product.
     */
    static void loadMenu() {
        // Bread(pID, pName, bPrice, qInStock, cat, isSliced)
        menu.add(new Bread("B001", "White Sandwich Loaf", 4.50, 20, "Bread", true));
        menu.add(new Bread("B002", "Wholemeal Loaf",      5.50, 15, "Bread", true));
        menu.add(new Bread("B003", "Sourdough Loaf",     12.00, 10, "Bread", false));

        // Pastry(pID, pName, bPrice, qInStock, cat, PastryType)
        menu.add(new Pastry("P001", "Butter Croissant", 3.50, 30, "Pastry", PastryType.CROSSAINT));
        menu.add(new Pastry("P002", "Apple Puff",       4.50, 25, "Pastry", PastryType.PUFF));
        menu.add(new Pastry("P003", "Egg Tart",         3.00, 40, "Pastry", PastryType.TART));
        menu.add(new Pastry("P004", "Chicken Pie",      6.50, 20, "Pastry", PastryType.PIE));

        // Cake(pID, pName, bPrice, qInStock, cat, customMessage, weight)
        menu.add(new Cake("C001", "Chocolate Fudge Cake",  28.00, 5, "Cake", "Happy Birthday!", 1.0));
        menu.add(new Cake("C002", "Vanilla Sponge Cake",   22.00, 5, "Cake", "Congratulations!", 0.8));
        menu.add(new Cake("C003", "Birthday Special Cake", 55.00, 3, "Cake", "Happy Birthday!", 2.0));
    }

    // ======================================================================
    // DISPLAY HELPERS
    // ======================================================================

    static void printBanner() {
        System.out.println("  ============================================");
        System.out.println("         SWEET CRUMBS BAKERY                  ");
        System.out.println("         Bakery Management System              ");
        System.out.println("  ============================================");
        System.out.println("  Take-Away Orders Only | No Deliveries");
        System.out.println("  ============================================");
    }

    static void printMainMenu() {
        System.out.println("\n  ========== MAIN MENU ==========");
        System.out.println("  1. View Bakery Menu");
        System.out.println("  2. Add Item to Cart");
        System.out.println("  3. View Cart");
        System.out.println("  4. Remove Item from Cart");
        System.out.println("  5. Place Order");
        System.out.println("  6. View Order History");
        System.out.println("  7. Exit");
        System.out.println("  ================================");
    }

    /**
     * Displays all products using their displayProduct() method.
     * Since displayProduct() is abstract in Product, each subclass
     * (Bread, Pastry, Cake) runs its own version -- this is polymorphism.
     */
    static void viewMenu() {
        System.out.println();
        System.out.println("  ============================================");
        System.out.println("          SWEET CRUMBS BAKERY MENU            ");
        System.out.println("  ============================================");

        for (Product product : menu) {
            System.out.println("  ------------------------------------------");
            product.displayProduct(); // polymorphic call
        }

        System.out.println("  ============================================");
    }

    // ======================================================================
    // CART ACTIONS
    // ======================================================================

    /**
     * Prompts the user to enter a product ID and quantity, then adds it to the cart.
     * Cart.addItem() handles stock checking via Product.isAvailable().
     */
    static void addToCart() {
        viewMenu();
        System.out.print("\n  Enter Product ID to add (or 0 to cancel): ");
        String id = scanner.nextLine().trim();

        if (id.equals("0")) return;

        // Search the menu ArrayList for a matching product ID
        Product selected = null;
        for (Product p : menu) {
            if (p.getID().equalsIgnoreCase(id)) {
                selected = p;
                break;
            }
        }

        if (selected == null) {
            System.out.println("  Product ID \"" + id + "\" not found. Please try again.");
            return;
        }

        // Show the selected product's details using its own displayProduct()
        System.out.println("\n  Selected:");
        System.out.println("  ------------------------------------------");
        selected.displayProduct();
        System.out.println("  ------------------------------------------");

        int qty = getIntInput("  Enter quantity: ");

        if (cart.addItem(selected, qty)) {
            System.out.printf("  Added %dx %s to cart.%n", qty, selected.getName());
        }
        // if addItem() returned false, Cart already printed the reason
    }

    /**
     * Lets the user remove an item from the cart by its displayed number.
     */
    static void removeFromCart() {
        if (cart.isEmpty()) {
            System.out.println("  Your cart is empty. Nothing to remove.");
            return;
        }

        cart.displayCart();
        int num = getIntInput("  Enter item number to remove (0 to cancel): ");

        if (num == 0) return;

        if (cart.removeItem(num)) {
            System.out.println("  Item removed from cart.");
        } else {
            System.out.println("  Invalid number. Please try again.");
        }
    }

    // ======================================================================
    // ORDER ACTIONS
    // ======================================================================

    /**
     * Checks out the current cart:
     *  - Shows cart summary and total
     *  - Asks for customer name and confirmation
     *  - Creates an Order (which deducts stock via updateStock())
     *  - Prints receipt and clears the cart
     */
    static void placeOrder() {
        if (cart.isEmpty()) {
            System.out.println("  Your cart is empty. Add items before placing an order.");
            return;
        }

        cart.displayCart();
        System.out.print("\n  Enter your name: ");
        String name = scanner.nextLine().trim();

        if (name.isEmpty()) {
            System.out.println("  Name cannot be empty. Order cancelled.");
            return;
        }

        System.out.print("  Confirm order? (Y/N): ");
        String confirm = scanner.nextLine().trim().toUpperCase();

        if (confirm.equals("Y")) {
            // Creating an Order automatically deducts stock from each Product
            Order order = new Order(name, cart);
            orderManager.addOrder(order);
            order.displayReceipt();
            cart.clear(); // empty the cart after a successful order
        } else {
            System.out.println("  Order cancelled. Your cart is still saved.");
        }
    }

    // ======================================================================
    // INPUT HELPER
    // ======================================================================

    /**
     * Safely reads an integer, keeps prompting until valid input is given.
     */
    static int getIntInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = scanner.nextLine().trim();
            if (line.matches("-?\\d+")) {
                return Integer.parseInt(line);
            }
            System.out.println("  Please enter a valid whole number.");
        }
    }
}
