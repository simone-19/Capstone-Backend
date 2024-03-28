package com.SimonePernella.NewCapstoneBack.entities;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "Books")
@Entity
public class ComicBook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;
    private String titolo;
    private String immagine;
    private double prezzo;
    private String categoria;
}
