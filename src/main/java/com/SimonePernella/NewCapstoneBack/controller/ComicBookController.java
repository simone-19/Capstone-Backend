package com.SimonePernella.NewCapstoneBack.controller;



import com.SimonePernella.NewCapstoneBack.dto.ComicBookDTO;
import com.SimonePernella.NewCapstoneBack.entities.ComicBook;
import com.SimonePernella.NewCapstoneBack.entities.User;
import com.SimonePernella.NewCapstoneBack.repository.ComicBookRepository;
import com.SimonePernella.NewCapstoneBack.service.ComicBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comics")
public class ComicBookController {
    @Autowired
    private ComicBookRepository comicBookRepository;
    @Autowired
    private ComicBookService comicBookService;
    @PostMapping
    public ComicBook createComic(@RequestBody ComicBookDTO body)

    {ComicBook newComicBook = comicBookService.save(body); return newComicBook;}

    @GetMapping("/{id}")
    public ComicBook getComicById(@PathVariable long id) {
        return comicBookService.getById(id);
    }
    @DeleteMapping("/{id}")
    public void deleteComicBook(@PathVariable long id) {
        comicBookService.delete(id);
    }
    @GetMapping("/categoria")
    public ComicBook getByCategoria(@PathVariable String categoria) {
        return comicBookService.getByCategoria(categoria);
    }
    @GetMapping
    public List <ComicBook> getByAll() {return comicBookService.getAllComic();}

    @GetMapping("/search")
    public List<ComicBook> searchComicBooks(@RequestParam String title) {
        return comicBookService.searchBooksByTitolo(title);
    }
}
