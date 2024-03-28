package com.SimonePernella.NewCapstoneBack.entities;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "Cart")
@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;
    private BigDecimal totale;
    @OneToOne
    @JoinColumn(name="userId")
    @JsonIgnoreProperties("cart")
    private User user;
//    altra
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "cart_books",
            joinColumns = @JoinColumn(name = "cart_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id"))
    private List <ComicBook> books = new ArrayList<>();
}
