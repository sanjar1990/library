package org.example.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.InputMismatchException;
import java.util.Scanner;

@Component
public class GetAction {
@Autowired
private Scanner scanner;

    public int  getAction(){
        try {
            System.out.println("Enter action: ");
            return scanner.nextInt();
        }catch (InputMismatchException e){
            System.out.println("Enter only numbers!");
        }
        return scanner.nextInt();
    }
}
