package org.example.controller;

import org.example.container.ComponentContainer;
import org.example.service.BookService;
import org.example.service.TakeAcceptBookService;
import org.example.util.GetAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Scanner;

@Controller
public class StudentMenuController {
    @Autowired
    private Scanner scanner;
    @Autowired
    private Scanner scannerLine;
    @Autowired
    private GetAction getAction;
    @Autowired
    private BookService bookService;
    @Autowired
    private TakeAcceptBookService takeAcceptBookService;
    public void start(){
        boolean t= true;
        todayBookDeadline();
        while (t){
            show();
            switch (getAction.getAction()){
                case 0->t=false;
                case 1->getAllBookList();
                case 2->searchBook();
                case 3->takeBook();
                case 4->returnBook();
                case 5->booksOnHand();
                case 6->TakenBookHistory();

            }
        }
    }




    public void show(){
        System.out.println("*** Student Menu ***\n" +
                "1. Book list: \n" +
                "    id, title, author, category, createdDate\n" +
                "2. Search: \n" +
                "    Enter name: \n" +
                "    id, title, author, category  \n" +
                "3. Take book:\n" +
                "    id, title, author, category, takenDate, returnDate\n" +
                "4. Return book:\n" +
                "    Enter Id:\n" +
                "5. Books on hand:\n" +
                "    id, title, author, category, takenDate, deadlineDate\n" +
                "6. Take book history: (print taken book list:)\n" +
                "    id, title, author, category, takenDate, returnDate");
    }

    public void todayBookDeadline(){
        takeAcceptBookService.getTodayReturnBookList(ComponentContainer.profileEntity.getId());
    }
    private void getAllBookList() {
        bookService.getAllBook();
    }
    private void searchBook() {
        System.out.println("Enter title or author or category or id: ");
        String data=scanner.next();
        bookService.searchByData(data);
    }
    private void takeBook() {
        System.out.println("Enter id of book: ");
        Integer bookId=scannerLine.nextInt();
        takeAcceptBookService.takeBook(bookId);
    }
    private void returnBook() {
        System.out.println("Enter book id: ");
        int bookId=scanner.nextInt();
        takeAcceptBookService.returnBook(bookId);
    }
    private void booksOnHand() {
        takeAcceptBookService.getAllTakenBookById();
    }
    private void TakenBookHistory() {
        takeAcceptBookService.getAllTakenBookHistory(ComponentContainer.profileEntity.getId());
    }
}
