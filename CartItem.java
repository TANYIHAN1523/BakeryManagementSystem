/**
 * Represents one entry in the cart: a Product and how many the customer wants.
 *
 * OO Concepts: Encapsulation, Composition (contains a Product)
 */
public class CartItem {

    private Product item;
    private int     quantity;

    public CartItem(Product item, int quantity) {
        this.item     = item;
        this.quantity = quantity;
    }

    public Product getItem()            { return item;     }
    public int     getQuantity()        { return quantity; }
    public void    setQuantity(int qty) { quantity = qty;  }

    // Subtotal = base price x quantity
    public double getSubtotal() {
        return item.getPrice() * quantity;
    }

    @Override
    public String toString() {
        return String.format("  [%s] %-25s x%-3d  RM%.2f",
                item.getID(), item.getName(), quantity, getSubtotal());
    }
}
