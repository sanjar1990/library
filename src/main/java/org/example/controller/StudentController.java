package org.example.controller;
import org.example.service.ProfileService;
import org.example.util.GetAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Scanner;

@Controller
public class StudentController {
    @Autowired
    private GetAction getAction;
    @Autowired
    private Scanner scanner;
    @Autowired
    private ProfileService profileService;
    public void start(){
        boolean t=true;
        while (t){
            show();
            switch (getAction.getAction()){
                case 0-> t=false;
                case 1->getAllStudentList();
                case 2->searchStudent();
                case 3->blockStudent();
                case 4->activateStudent();
                case 5->findStudentTakenBook();
            }
        }
    }
    public void show(){
        System.out.println("*** Student ***\n" +
                "1. Student list:\n" +
                "    id, name, surname, login, phone, status, createdDate \n" +
                "2. Search student\n" +
                "    Enter query: (id,name,surname,login,phone)\n" +
                "    id, name, surname, login, phone, status, createdDate \n" +
                "3. Block student:\n" +
                "    Enter id;\n" +
                "4. Activate student:\n" +
                "    Enter id:\n" +
                "5. Student by book:  \n" +
                "    id, name, surname, login, phone, status,  takenBookCount, BookOnHand ");
    }
    private void getAllStudentList() {
        profileService.getAllStudent();
    }
    private void searchStudent() {
        System.out.println("To search student enter id,name,surname,login,phone: ");
        String data=scanner.next();
        profileService.searchStudent(data);
    }
    private void blockStudent() {
        System.out.println("Enter id of student: ");
        int id=scanner.nextInt();
    profileService.blockStudent(id);
    }
    private void activateStudent() {
        System.out.println("Enter student id: ");
        int id=scanner.nextInt();
        profileService.activateStudent(id);
    }
    private void findStudentTakenBook() {
        System.out.println("Enter student id: ");
        int id=scanner.nextInt();
        profileService.getStudentBookInfo(id);
    }
}
