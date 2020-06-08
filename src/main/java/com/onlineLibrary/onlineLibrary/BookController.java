package com.onlineLibrary.onlineLibrary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    BookRepository bookRepository;

    @GetMapping()
    public List<Book> getBooks() { return bookRepository.findAll(); }

    @GetMapping("/{id}")
    public Book getBooksById(@PathVariable int id) { return bookRepository.findById(id).get(); }

    @GetMapping("/search")
    public List<Book> search(@RequestBody Map<String, String> body) {
        String searchTerm = body.get("key-word");
        return bookRepository.findByTitleContainingOrDescriptionContaining(searchTerm, searchTerm);
    }

    @PostMapping()
    public Book save(@RequestBody Book book) { return bookRepository.save(book); }

    @PutMapping("/{id}")
    public Book update(@RequestBody Book book, @PathVariable int id) {
        Book bookToUpdate = bookRepository.findById(id).get();
        bookToUpdate.setTitle(book.getTitle());
        bookToUpdate.setAuthor(book.getAuthor());
        bookToUpdate.setDescription(book.getDescription());

        return bookRepository.save(bookToUpdate);
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable int id) { bookRepository.deleteById(id); return true; }



}
