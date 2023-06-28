package org.example.controller;
import org.example.util.GetAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class StaffController {
    @Autowired
    private GetAction getAction;
    @Autowired
    private BookController bookController;
    @Autowired
    private StudentController studentController;

    public void start(){
        boolean t=true;
        while (t){
            show();
            switch (getAction.getAction()){
                case 0->t=false;
                case 1->bookController.start();
                case 2->studentController.start();
            }
        }

    }
    public void show(){
        System.out.println("1. Book\n" +
                           "2. Student\n");
    }

}
