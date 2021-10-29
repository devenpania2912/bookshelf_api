package com.example.Bookshelf.book;

import org.springframework.data.map.repository.config.EnableMapRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@EnableMapRepositories
public class BookService {
  private final CrudRepository<Book, Long> repository;

  public BookService(CrudRepository<Book, Long> repository) {
    this.repository = repository;
    this.repository.saveAll(defaultBooks());
  }

  private static List<Book> defaultBooks() {
    return List.of(
      new Book(Long.valueOf(1), "Linda", 2020, "https://images.unsplash.com/photo-1554080353-a576cf803bda?ixid=MnwxMjA3fDB8MHxzZWFyY2h8M3x8cGhvdG98ZW58MHx8MHx8&ixlib=rb-1.2.1&w=1000&q=80", "N",4),
      new Book(Long.valueOf(2), "May", 2021, "https://images.unsplash.com/photo-1566275529824-cca6d008f3da?ixid=MnwxMjA3fDB8MHxzZWFyY2h8MTR8fHBob3RvfGVufDB8fDB8fA%3D%3D&ixlib=rb-1.2.1&w=1000&q=80", "Y",5)
    );
  }

    
    public List<Book> findAll() {
        List<Book> list = new ArrayList<>();
        Iterable<Book> Books = repository.findAll();
        Books.forEach(list::add);
        return list;
    }

    public Optional<Book> find(Long id) {
        return repository.findById(id);
    }

    public Book create(Book Book) {
        // To ensure the Book ID remains unique,
        // use the current timestamp.
        Book copy = new Book(
                new Date().getTime(),
                Book.getAuthor(),
                Book.getPublishYear(),
                Book.getImageUrl(),
                Book.getIsFinished(),
                Book.getRating()
        );
        return repository.save(copy);
    }

    public Optional<Book> update( Long id, Book newBook) {
        // Only update an Book if it can be found first.
        return repository.findById(id)
                .map(oldBook -> {
                Book updated = oldBook.updateWith(newBook);
                return repository.save(updated);
                });
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}