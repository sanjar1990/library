package org.example.controller;

import org.example.entity.ProfileEntity;
import org.example.service.BookService;
import org.example.service.CategoryService;
import org.example.service.ProfileService;
import org.example.util.GetAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Scanner;

@Controller
public class StartMenuController {
    @Autowired
    private Scanner scanner;
    @Autowired
    private Scanner scannerLine;
    @Autowired
    private BookService bookService;
    @Autowired
    private GetAction getAction;
    @Autowired
    private ProfileService profileService;
    @Autowired
    private CategoryService categoryService;

    public void start(){
    boolean t=true;
    while (t){
        show();
        switch (getAction.getAction()){
        case 1->getBookList();
        case 2->searchBook();
        case 3->searchByCategory();
        case 4-> login();
        case 5-> registration();
        case 0-> t=false;
        }
    }
    }



    public void show(){
        System.out.println("*** Main *** \n" +
                "1. BookList  -> id, title, author, category\n" +
                "2. Search -> \n" +
                "    Enter query:  (search by title or author or category) \n" +
                "    id, title, author, category    \n" +
                "3. By category:  \n" +
                "      (print all category for choose) №,id,name\n" +
                "      Enter category:  \n" +
                "4. Login\n" +
                "5. Registration\n" +
                "0. Exit");
    }
    private void getBookList() {
    bookService.getAllBook();
    }
    private void searchBook() {
        System.out.println("Enter title or author or category or id: ");
        String data=scanner.next();
        bookService.searchByData(data);
    }
    private void searchByCategory() {
        categoryService.getAllCategory();
        System.out.println("Enter category: ");
        String category=scanner.next();
        bookService.searchBookByCategory(category);
    }
    public void login(){
        System.out.println("Enter login: ");
        String login=scanner.next();
        System.out.println("Enter password: ");
        String password=scanner.next();
        profileService.login(login,password);
    }

    private void registration() {
        System.out.println("Enter name: ");
        String name=scanner.next();
        System.out.println("Enter surname: ");
        String surname=scanner.next();
        System.out.println("Enter phone: ");
        String phone=scanner.next();
        System.out.println("Enter login: ");
        String login=scanner.next();
        System.out.println("Enter password: ");
        String password=scanner.next();
        ProfileEntity profileEntity= new ProfileEntity();
        profileEntity.setName(name);
        profileEntity.setSurname(surname);
        profileEntity.setPhone(phone);
        profileEntity.setLogin(login);
        profileEntity.setPassword(password);

        profileService.registration(profileEntity);

    }

}
