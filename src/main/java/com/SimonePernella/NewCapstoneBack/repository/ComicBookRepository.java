package com.SimonePernella.NewCapstoneBack.repository;

import com.SimonePernella.NewCapstoneBack.entities.ComicBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ComicBookRepository extends JpaRepository<ComicBook, Long> {

    Optional<ComicBook> findByCategoria(String string);
    List<ComicBook> findByTitoloContaining(String title);
}
