package org.example.service;

import org.example.dto.BookDto;
import org.example.dto.CategoryDto;
import org.example.entity.BookEntity;
import org.example.entity.CategoryEntity;
import org.example.mapper.AddBookMapper;
import org.example.repository.BookRepository;
import org.example.util.CheckValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private CheckValidationUtil checkValidationUtil;
    @Autowired
    private CategoryService categoryService;
    public void getAllBook() {
        List<BookDto> bookDtoList=bookRepository.getAllBook();
        if (bookDtoList.isEmpty()){
            System.out.println("Library is empty");
        }else {
            bookDtoList.forEach(System.out::println);
        }
    }

    public void searchByData(String data) {
        Integer id=checkValidationUtil.isId(data);
        List<BookDto> list=bookRepository.searchByData(data,id);
        if (list.isEmpty()){
            System.out.println("Library is empty");
        }else {
            list.forEach(System.out::println);
        }
    }

    public void searchBookByCategory(String category) {
        List<BookDto> list=bookRepository.searchByCategory(category);
        if (list.isEmpty()){
            System.out.println("No book was found by this category!");
        }else {
            list.forEach(System.out::println);
        }
    }


    public void addBook(AddBookMapper addBookMapper) {
        CategoryEntity category=categoryService.getCategoryByName(addBookMapper.getCategoryName());
        if(category==null){
            System.out.println("Category "+addBookMapper.getCategoryName()+" is not exists!");
            return;
        }
        BookEntity bookEntity= new BookEntity();
        bookEntity.setVisible(true);
        bookEntity.setAuthor(addBookMapper.getAuthor());
        bookEntity.setCategory(category);
        bookEntity.setAvailableDay(10);
        bookEntity.setPublishDate(LocalDate.parse(addBookMapper.getPublishedDate()));
        bookEntity.setTitle(addBookMapper.getTitle());
        bookEntity.setCreatedDate(LocalDateTime.now());
        bookRepository.addBook(bookEntity);
        System.out.println("Book is added successfully!");

    }

    public void removeBook(Integer id) {
        int n=bookRepository.removeBook(id);
        if(n>0){
            System.out.println("Book was removed successfully!");
        }else {
            System.out.println("Book not found!");
        }
    }
}
