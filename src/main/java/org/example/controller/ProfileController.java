package org.example.controller;

import org.example.entity.ProfileEntity;
import org.example.enums.ProfileStatus;
import org.example.service.ProfileService;
import org.example.util.GetAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Scanner;

@Controller
public class ProfileController {
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
                case 1->profileList();
                case 2->searchProfile();
                case 3->changeProfileStatus();
                case 4->addStaff();
            }


        }
    }




    public void show(){
        System.out.println("*** Profile *** (only for admin)\n" +
                "1. Profile list:\n" +
                "    id, name, surname, login, phone, status, role, createdDate \n" +
                "2. Search profile\n" +
                "    Enter query: (id,name,surname,login,phone)\n" +
                "    id, name, surname, login, phone, status, createdDate \n" +
                "3. Change profile status:\n" +
                "4. Add Library Staff:\n");
    }
    private void profileList() {
    profileService.getAllProfile();
    }
    private void searchProfile() {
        System.out.println("Enter data to search (id,name,surname,login,phone): ");
        String data=scanner.next();
        profileService.searchProfile(data);
    }
    private void changeProfileStatus() {
        System.out.println("Enter profile id: ");
        int id=scanner.nextInt();
        System.out.println("Active==1, BLOCK==2");
        int n=scanner.nextInt();
        if(n==1){
            profileService.changeStatus(id, ProfileStatus.ACTIVE);
        }else {
            profileService.changeStatus(id,ProfileStatus.BLOCK);
        }

    }

    private void addStaff() {
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

        profileService.staffRegistration(profileEntity);
    }
}
