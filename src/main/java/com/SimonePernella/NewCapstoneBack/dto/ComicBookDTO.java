package com.SimonePernella.NewCapstoneBack.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

public record ComicBookDTO (@NotEmpty(message = "The title is required.")
                            String titolo,
                            @NotEmpty(message = "The image is required.")
                            String immagine,
                            @NotEmpty(message = "The category is required.")
                            String categoria,
                            @NotEmpty(message = "The price is required.")
                            double prezzo
) {
}
