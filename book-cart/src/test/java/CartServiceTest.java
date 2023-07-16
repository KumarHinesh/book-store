import io.quarkus.test.junit.QuarkusTest;
import org.acme.models.Book;
import org.acme.models.Cart;
import org.acme.models.CartItem;
import org.acme.services.CartService;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
public class CartServiceTest {

    @Inject
    CartService cartService;

    @Test
    public void testGetOrCreateCartByUserId() {
        // Test the getOrCreateCartByUserId method

        // Create a user ID for testing
        Integer userId = 1;

        // Call the method
        Cart cart = cartService.getOrCreateCartByUserId(userId);

        // Validate the cart is not null and has the correct user ID
        assertNotNull(cart);
        assertEquals(userId, cart.getUserId());
    }

    @Test
    public void testAddCartItem() {
        // Test the addCartItem method

        // Create a CartItem for testing
        Cart cart = new Cart();
        int quantity = 2;
        Book book = new Book();
        CartItem cartItem = CartItem.builder().cart(cart).book(Book.builder().id(1).build()).quantity(2).build();

        // Call the method
        CartItem result = cartService.addCartItem(cartItem);

        // Validate the result is not null
        assertNotNull(result);
    }

    @Test
    public void testCheckoutCart() {
        // Test the checkoutCart method

        // Create a cart for testing
        Cart cart = new Cart();
        cart.setStatus("Open");

        // Calculate the expected total
        double total = 0.0;
        // Add some CartItems to the cart for testing
        // ...

        // Call the method
        double result = cartService.checkoutCart(cart.getId().longValue());

        // Validate the result matches the expected total
        assertEquals(total, result);
        // Validate the cart status is set to "Completed"
        assertEquals("Completed", cart.getStatus());
    }

    @Test
    public void testCalculateTotal() {
        // Test the calculateTotal method

        // Create a cart for testing
        Cart cart = new Cart();
        // Adding some CartItems to the cart for testing
        CartItem cartItem = CartItem.builder().cart(cart).book(Book.builder().id(1).build()).quantity(2).build();

        cart.addCartItem(cartItem);
        // Call the method
        double result = cartService.calculateTotal(cart);

        // Validate the result matches the expected total
        // ...
    }
}
