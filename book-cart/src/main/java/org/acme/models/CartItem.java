package org.acme.models;


import com.fasterxml.jackson.annotation.JsonInclude;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "CART_ITEM")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class CartItem extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CART_ID")
    private Cart cart;

    @OneToOne
    @JoinColumn(name = "BOOK_ID")
    private Book book;


    @Column(name = "QUANTITY")
    private int quantity;

    public CartItem(Cart cart, int quantity) {
        this.cart = cart;
        this.quantity = quantity;
    }


}
