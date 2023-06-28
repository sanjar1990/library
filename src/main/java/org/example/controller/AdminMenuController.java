package org.example.controller;

import org.example.util.GetAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class AdminMenuController {
    @Autowired
    private GetAction getAction;
    @Autowired
    private BookController bookController;
    @Autowired
    private CategoryController categoryController;
    @Autowired
    private StudentController studentController;
    @Autowired
    private ProfileController profileController;
    public void start(){
        boolean t=true;
        while (t){
            menuShow();
            switch (getAction.getAction()){
                case 0->t=false;
                case 1->bookController();
                case 2->categoryController();
                case 3->studentController();
                case 4->profileController();

            }
        }
    }



    public void menuShow(){
        System.out.println("*** Menu ***\n" +
                "1. Book\n" +
                "2. Category  (only for admin)\n" +
                "3. Student\n" +
                "4. Profile (only for admin)");
    }
    private void bookController() {
        bookController.start();
    }
    private void categoryController() {
        categoryController.start();
    }
    private void studentController() {
        studentController.start();
    }

    private void profileController() {
    profileController.start();
    }






}
