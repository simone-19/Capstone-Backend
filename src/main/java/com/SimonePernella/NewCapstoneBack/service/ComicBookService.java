package com.SimonePernella.NewCapstoneBack.service;


import com.SimonePernella.NewCapstoneBack.dto.ComicBookDTO;
import com.SimonePernella.NewCapstoneBack.entities.ComicBook;
import com.SimonePernella.NewCapstoneBack.entities.User;
import com.SimonePernella.NewCapstoneBack.exception.NotFoundException;
import com.SimonePernella.NewCapstoneBack.repository.ComicBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComicBookService {
    @Autowired
    private ComicBookRepository comicBookRepository;

    public ComicBook save(ComicBookDTO body) {
        ComicBook newComicBook = new ComicBook();
        newComicBook.setTitolo(body.titolo());
        newComicBook.setImmagine(body.immagine());
        newComicBook.setCategoria(body.categoria());
        newComicBook.setPrezzo(body.prezzo());
        comicBookRepository.save(newComicBook);
        return newComicBook;
    }
    public ComicBook getById(long id) {
        return comicBookRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }
    public void delete(long id) {
        ComicBook found = this.getById(id);
        comicBookRepository.delete(found);
    }
    public ComicBook getByCategoria(String categoria) {
        return comicBookRepository.findByCategoria(categoria).orElseThrow(() -> new NotFoundException(categoria));
    }
    public List <ComicBook> getAllComic() { return comicBookRepository.findAll();}

    public List<ComicBook> searchBooksByTitolo(String title) {
        return comicBookRepository.findByTitoloContaining(title);
    }
}
