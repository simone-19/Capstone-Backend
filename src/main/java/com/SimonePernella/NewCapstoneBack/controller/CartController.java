package com.SimonePernella.NewCapstoneBack.controller;


import com.SimonePernella.NewCapstoneBack.entities.Cart;
import com.SimonePernella.NewCapstoneBack.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")

public class CartController {
    @Autowired
    private CartService cartService;

    @PostMapping("/{cartId}/addBook")
    public ResponseEntity<Cart> addBookToCart(@PathVariable Long cartId, @RequestParam Long bookId) {
        cartService.addBook(cartId, bookId);
        Cart updatedCart = cartService.getById(cartId);
        return ResponseEntity.ok(updatedCart);
    }

    @PostMapping("/{cartId}/removeBook")
    public ResponseEntity<Cart> removeBookFromCart(@PathVariable Long cartId, @RequestParam Long bookId) {
        cartService.removeBook(cartId, bookId);
        Cart updatedCart = cartService.getById(cartId);
        return ResponseEntity.ok(updatedCart);
    }
}
