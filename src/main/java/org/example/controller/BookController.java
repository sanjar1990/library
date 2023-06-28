package org.example.controller;

import org.example.mapper.AddBookMapper;
import org.example.service.BookService;
import org.example.service.CategoryService;
import org.example.service.TakeAcceptBookService;
import org.example.util.GetAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Scanner;

@Controller
public class BookController {
    @Autowired
    private GetAction getAction;
    @Autowired
    private BookService bookService;
    @Autowired
    private Scanner scanner;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private TakeAcceptBookService takeAcceptBookService;

    public void start(){
        boolean t=true;
        while (t){
            show();
            switch (getAction.getAction()){
                case 0-> t=false;
                case 1->getAllBookList();
                case 2->searchBook();
                case 3->addBook();
                case 4->removeBook();
                case 5->allBooksOnHand();
                case 6->bookHistoryById();
                case 7->bestBooks();
            }


        }
    }




    public void show(){
        System.out.println("*** Book ***\n" +
                "1. Book list  -> id, title, author, category\n" +
                "2. Search -> \n" +
                "    Enter name: \n" +
                "    id, title, author, category  \n" +
                "3. Add book -> \n" +
                "    Enter title: \n" +
                "    Enter author:\n" +
                "    Enter category:\n" +
                "    Enter book publishDate: \n" +
                "4. Remove book:\n" +
                "    Enter id:\n" +
                "5. Books on hand:\n" +
                "    id, title, author, category, takenDate, deadlineDate, student name, student surname, student phone\n" +
                "6. Book history:\n" +
                "    Enter book id: \n" +
                "    takenDate, returnDate, student name, student surname, student phone, note\n" +
                "7. Best books: (eng ko'p olingan 10 kitob, taken count desc)\n" +
                "       id, title, author, category, taken count  ");
    }

    private void getAllBookList() {
        bookService.getAllBook();
    }

    private void searchBook() {
        System.out.println("Enter id, title, author, category to search:  ");
        String data=scanner.next();
        bookService.searchByData(data);
    }

    private void addBook() {
        System.out.println("Enter title: ");
        String title=scanner.next();
        System.out.println("Enter author: ");
        String author=scanner.next();
        System.out.println("Enter category name: ");
        categoryService.getAllCategory();
        String categoryName=scanner.next();
        System.out.println("Enter book publishDate:  ex:\"YYYY-MM-DD\"");
        String publishDate=scanner.next();

        AddBookMapper addBookMapper= new AddBookMapper(title,author,categoryName,publishDate);
        bookService.addBook(addBookMapper);
    }

    private void removeBook() {
        System.out.println("Enter book id: ");
        Integer id=scanner.nextInt();
        bookService.removeBook(id);
    }

    private void allBooksOnHand() {
        takeAcceptBookService.getAllTakenBook();
    }
    private void bookHistoryById() {
        System.out.println("Enter book id: ");
        Integer id=scanner.nextInt();
        takeAcceptBookService.getAllBookHistoryByBookId(id);

    }
    private void bestBooks() {
        takeAcceptBookService.getBestBooks();
    }




}
