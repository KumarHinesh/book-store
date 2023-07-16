package org.acme.resource;


import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import org.acme.models.Cart;
import org.acme.models.CartItem;
import org.acme.services.CartService;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

@Path("/carts")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CartResource {
    @Inject
    CartService cartService;

    @POST
    @Path("/{userId}")
    @Operation(summary = "Create or get a cart by user ID")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Cart retrieved or created successfully"),
            @APIResponse(responseCode = "400", description = "Invalid user ID")
    })
    public Response getOrCreateCart(@PathParam("userId") Integer userId) {
        Cart cart = cartService.getOrCreateCartByUserId(userId);
        return Response.ok(cart).build();
    }

    @POST
    @Path("/{cartId}/items")
    @Operation(summary = "Add a cart item to a cart")
    @APIResponses(value = {
            @APIResponse(responseCode = "201", description = "Cart item added successfully"),
            @APIResponse(responseCode = "400", description = "Invalid cart item details"),
            @APIResponse(responseCode = "404", description = "Cart not found")
    })
    public Response addCartItem(@PathParam("cartId") Long cartId, @Valid CartItem cartItem) {
        CartItem addedCartItem = cartService.addCartItem(cartItem);
        return Response.status(Response.Status.CREATED).entity(addedCartItem).build();
    }

    @POST
    @Path("/{cartId}/checkout")
    @Operation(summary = "Checkout a cart and calculate the total")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Cart checked out successfully"),
            @APIResponse(responseCode = "404", description = "Cart not found"),
            @APIResponse(responseCode = "400", description = "Cart already completed or empty")
    })
    public Response checkoutCart(@PathParam("cartId") Long cartId) {
        double total = cartService.checkoutCart(cartId);
        if (total > 0.0) {
            return Response.ok("Checkout successful. Total: " + total).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity("Cart is already completed or empty").build();
        }
    }
}
