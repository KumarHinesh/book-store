package org.acme.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import org.acme.models.Book;
import org.acme.models.Cart;
import org.acme.models.CartItem;


import java.util.List;
import java.util.Optional;

@ApplicationScoped
@Transactional
public class CartService {
    public Cart getOrCreateCartByUserId(Integer userId) {
        Optional<Cart> optionalCart = Cart.find("userId", userId).firstResultOptional();
        return optionalCart.orElseGet(() -> {
            Cart cart = new Cart();
            cart.setUserId(userId);
            cart.setStatus("Open");
            cart.persist();
            return cart;
        });
    }

    public CartItem addCartItem(CartItem cartItem) {
        cartItem.persist();
        return cartItem;
    }

    public double checkoutCart(Long cartId) {
        Cart cart = Cart.findById(cartId);
        if (cart != null && cart.getStatus().equals("Open")) {
            double total = calculateTotal(cart);
            cart.setStatus("Completed");
            return total;
        }
        return 0.0;
    }

    public double calculateTotal(Cart cart) {
        List<CartItem> cartItems = CartItem.find("cart", cart).list();
        double total = 0.0;
        for (CartItem item : cartItems) {
            total += item.getBook().getPrice() * item.getQuantity();
            total = total - item.getBook().getType().getDiscount() * item.getQuantity();
        }
        return total;
    }
}
