package com.SimonePernella.NewCapstoneBack.service;

import com.SimonePernella.NewCapstoneBack.entities.Cart;
import com.SimonePernella.NewCapstoneBack.entities.ComicBook;
import com.SimonePernella.NewCapstoneBack.entities.User;
import com.SimonePernella.NewCapstoneBack.exception.BadRequestException;
import com.SimonePernella.NewCapstoneBack.exception.NotFoundException;
import com.SimonePernella.NewCapstoneBack.repository.CartRepository;
import com.SimonePernella.NewCapstoneBack.repository.ComicBookRepository;
import com.SimonePernella.NewCapstoneBack.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CartService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private ComicBookRepository comicBookRepository;
    @Autowired
    private ComicBookService comicBookService;
    public Cart createCart(User user) {

        Cart cart = new Cart();
        cart.setUser(user);
        cart=cartRepository.save(cart);
        user.setCart(cart);
        userRepository.save(user);
        return cart;
    }

    public Cart getById(long id) {
        return cartRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }
    public Cart addBook(Long cartId, Long bookId) {
        ComicBook newComicBook = comicBookService.getById(bookId);
        Cart newCart = getById(cartId);

        BigDecimal prezzo =new BigDecimal(String.valueOf(newComicBook.getPrezzo())); // Converti il prezzo in BigDecimal

        newCart.getBooks().add(newComicBook);
        newCart.setTotale(newCart.getTotale().add(prezzo));
        cartRepository.save(newCart);
        return newCart;
    }

    public Cart removeBook(Long cartId, Long bookId) {
        ComicBook newComicBook = comicBookService.getById(bookId);
        Cart newCart = getById(cartId);

        BigDecimal prezzo = new BigDecimal(String.valueOf(newComicBook.getPrezzo())); // Converti il prezzo in BigDecimal

        newCart.setTotale(newCart.getTotale().subtract(prezzo));
        newCart.getBooks().remove(newComicBook);
        cartRepository.save(newCart);
        return newCart;
    }
    public void svuotaCarrello(Cart cart) {
        cart.getBooks().clear();
        cart.setTotale(BigDecimal.ZERO);
        cartRepository.save(cart);
    }
}
