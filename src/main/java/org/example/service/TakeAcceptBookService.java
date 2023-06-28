package org.example.service;

import org.example.container.ComponentContainer;
import org.example.entity.BookEntity;
import org.example.entity.TakenBookEntity;
import org.example.enums.TakenBookStatus;
import org.example.mapper.*;
import org.example.repository.BookRepository;
import org.example.repository.TakenBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class TakeAcceptBookService {
    @Autowired
    private TakenBookRepository takenBookRepository;
    @Autowired
    private BookRepository bookRepository;
    public void takeBook(Integer bookId) {
        TakenBookMapper takenBookMapper= takenBookRepository.getBookInfo(bookId);
        BookEntity bookEntity=bookRepository.getBookById(bookId);
        if (bookEntity==null){
            System.out.println("Book is not find");
            return;
        }
        if (takenBookMapper != null && takenBookMapper.getTakenBookStatus().equals(TakenBookStatus.TOOK)) {
          System.out.println("This book is already taken!");
          return;
      }
            TakenBookEntity takenBookEntity= new TakenBookEntity();
            takenBookEntity.setBookId(bookEntity);
            takenBookEntity.setStudentId(ComponentContainer.profileEntity);
            takenBookEntity.setStatus(TakenBookStatus.TOOK);
            takenBookEntity.setCreatedDate(LocalDateTime.now());
            LocalDate localDate=LocalDate.now().plusDays(bookEntity.getAvailableDay());
            takenBookEntity.setNote("No info");
            takenBookEntity.setReturnDate(localDate);
            takenBookRepository.addTakenBookInfo(takenBookEntity);
            System.out.println("Book is Took");
    }

    public void returnBook(int bookId) {
        BookEntity bookEntity=bookRepository.getBookById(bookId);
        TakenBookMapper takenBookMapper= takenBookRepository.getBookInfo(bookId);
        if(bookEntity==null){
            System.out.println("This book is not exists");
            return;
        }
        if(takenBookMapper==null ||
                !takenBookMapper.getStudentId().equals(ComponentContainer.profileEntity.getId())){
            System.out.println("You did not take this book !");
            return;
        }else if(takenBookMapper.getTakenBookStatus().equals(TakenBookStatus.RETURNED)){
            System.out.println("You have already returned this book! ");
            return;
        }

        takenBookRepository.returnBook(takenBookMapper.getTakenBookId(),TakenBookStatus.RETURNED);
        System.out.println("You have successfully returned book !");




    }
    public void getAllTakenBookById() {
        List<BooksOnHand> booksOnHandList=takenBookRepository.getAllBooksOnHandById(ComponentContainer.profileEntity.getId());
        if (booksOnHandList.isEmpty()){
            System.out.println("You don't have any books on your hand!");
        }else {
            booksOnHandList.forEach(System.out::println);
        }

    }

    public void getTodayReturnBookList(Integer id) {
        List<BooksOnHand> booksOnHandList=takenBookRepository.getAllBooksOnHandById(id);
        if(!booksOnHandList.isEmpty()){
            booksOnHandList.forEach(s->{
                if(s.getReturnDate().equals(LocalDate.now())){
                    System.out.println("You should return this book today: "+ s);
                }
            });
        }

    }

    public void getAllTakenBookHistory(Integer id) {
        List<BooksOnHand> list=takenBookRepository.getAllTakenBookList(id);
        if(list==null){
            System.out.println("You did not take any book from library!");
        }else {
            list.forEach(s-> System.out.println(s.takenBookHistoryShow()));
        }
    }

    public void getAllTakenBook() {
        List<AllBooksOnHandMapper> list=takenBookRepository.getAllBooksOnHand();
        if(list.isEmpty()){
             System.out.println("There are no book on hand!");
        }else {
            list.forEach(System.out::println);
        }
    }

    public void getAllBookHistoryByBookId(Integer id) {
        BookEntity bookEntity=bookRepository.getBookById(id);
        if(bookEntity==null){
            System.out.println("Book is not found");
            return;
        }
        List<BookHistoryMapper> list=takenBookRepository.getBookHistoryById(id);
        if(list.isEmpty()){
            System.out.println("Book history is not found!");
            return;
        }
        list.forEach(System.out::println);


    }

    public void getBestBooks() {
        List<BestBookMapper> list=takenBookRepository.getBestBooks();
        if(list==null){
            System.out.println("no best book find");
        }else {
            list.forEach(System.out::println);
        }
    }
}
