package org.example.controller;

import org.example.service.CategoryService;
import org.example.util.GetAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Scanner;

@Controller
public class CategoryController {
    @Autowired
    private GetAction getAction;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private Scanner scanner;
    public void start(){
        boolean t=true;
        while (t){
            show();
            switch (getAction.getAction()){
                case 0-> t=false;
                case 1-> categoryList();
                case 2-> addCategory();
                case 3-> removeCategory();
            }


        }
    }

    public void show(){
        System.out.println("*** Category *** \n" +
                "1. Category list:\n" +
                "2. Add category  \n"+
                "3. Delete category:\n" +
                "    Enter id;    ");
    }

    private void categoryList() {
        categoryService.getAllCategory();
    }

    private void addCategory() {
        System.out.println("Enter category name: ");
        String categoryName=scanner.next();
        categoryService.addCategory(categoryName);
    }

    private void removeCategory() {
        System.out.println("Enter category id to remove: ");
        Integer id=scanner.nextInt();
        categoryService.removeCategory(id);

    }
}
