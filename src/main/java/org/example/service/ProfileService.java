package org.example.service;

import org.example.container.ComponentContainer;
import org.example.controller.StaffController;
import org.example.controller.StudentMenuController;
import org.example.controller.AdminMenuController;
import org.example.entity.ProfileEntity;
import org.example.enums.ProfileStatus;
import org.example.enums.Role;
import org.example.mapper.StudentBookInfoMapper;
import org.example.repository.ProfileRepository;
import org.example.util.CheckValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProfileService {
@Autowired
private CheckValidationUtil checkValidationUtil;
@Autowired
private ProfileRepository profileRepository;
@Autowired
private StudentMenuController studentMenuController;
@Autowired
private AdminMenuController adminMenuController;
@Autowired
private StaffController staffController;
    public void registration(ProfileEntity profileEntity) {
        if(!checkValidationUtil.checkPhoneNumber(profileEntity.getPhone())){
            System.out.println("Enter valid phone Number");
            return;
        }
        boolean phoneIsExists=profileRepository.checkPhone(profileEntity.getPhone());
        boolean loginIsExists=profileRepository.checkLogin(profileEntity.getLogin());
        if(phoneIsExists ){
            System.out.println("This phone number is exists!");
            return;
        }else if(loginIsExists){
            System.out.println("This login already taken!");
            return;
        }
        profileEntity.setCreatedDate(LocalDateTime.now());
        profileEntity.setRole(Role.STUDENT);
        profileEntity.setStatus(ProfileStatus.ACTIVE);
        profileRepository.registerProfile(profileEntity);
        System.out.println("You have registered successfully!");


    }

    public void login(String login, String password) {
        ProfileEntity profileEntity=profileRepository.getByLogin(login);
        if (profileEntity==null){
            System.out.println("Profile not exist first Register your account!");
            return;
        }
        else if (!profileEntity.getPassword().equals(password)){
            System.out.println("Enter valid password");
            return;
        }
        ComponentContainer.profileEntity=profileEntity;
        if(profileEntity.getRole().equals(Role.STUDENT)){
            studentMenuController.start();
        }else if(profileEntity.getRole().equals(Role.ADMIN)){
            adminMenuController.start();
        }else if(profileEntity.getRole().equals(Role.STAFF)){
         staffController.start();
        }
    }

    public void getAllStudent() {
        List<ProfileEntity> list=profileRepository.getAllStudentList();
        if(list.isEmpty()){
            System.out.println("No student was found!");
        }else {
            list.forEach(System.out::println);
        }
    }

    public void searchStudent(String data) {
        Integer n= checkValidationUtil.isId(data);
        if(n!=null){
            ProfileEntity student=profileRepository.searchStudentById(n);
            System.out.println(student);
            return;
        }
        List<ProfileEntity> profileList=profileRepository.getStudentByData(data);
        if(!profileList.isEmpty()){
            profileList.forEach(System.out::println);
            return;
        }
        System.out.println("Profile not found");
    }

    public void blockStudent(int id) {
        ProfileEntity student=profileRepository.searchStudentById(id);
        if(student==null){
            System.out.println("Student not found");
            return;
        }else if(student.getStatus().equals(ProfileStatus.BLOCK)){
            System.out.println("This profile is already blocked!");
            return;
        }

        int n=profileRepository.updateStatusOfProfile(id,ProfileStatus.BLOCK.name());
        if(n>0){
            System.out.println("Profile is blocked!");
        }
    }

    public void activateStudent(int id) {
        ProfileEntity profileEntity=profileRepository.searchStudentById(id);
        if(profileEntity==null){
            System.out.println("Profile not found!");
            return;
        }else if(profileEntity.getStatus().equals(ProfileStatus.ACTIVE)){
            System.out.println("profile Status is already active!");
            return;
        }
        int n=profileRepository.updateStatusOfProfile(id,ProfileStatus.ACTIVE.name());
        if(n>0){
            System.out.println("Profile is Activated!");
        }
    }

    public void getStudentBookInfo(int id) {
        ProfileEntity profileEntity=profileRepository.searchStudentById(id);
        if(profileEntity==null){
            System.out.println("Profile is not found!");
            return;
        }
    List<StudentBookInfoMapper>list=profileRepository.getStudentBookInfo(id);
        if(list.isEmpty()){
            System.out.println("No info was found!");
            return;
        }
        list.forEach(System.out::println);

    }

    public void getAllProfile() {
        List<ProfileEntity> list=profileRepository.getAllProfile();
        if(list.isEmpty()){
            System.out.println("No Profile is found!");
            return;
        }
        else {
            list.forEach(System.out::println);
        }
    }

    public void searchProfile(String data) {
        Integer id=checkValidationUtil.isId(data);
        if(id!=null){
            ProfileEntity profileEntity=profileRepository.searchProfileById(id);
            System.out.println(profileEntity);
            return;
        }
        ProfileEntity profileEntity=profileRepository.getProfileByData(data);
        if(profileEntity==null){
            System.out.println("Profile is not found!");
            return;
        }
        System.out.println(profileEntity);
    }
    public void changeStatus(int id, ProfileStatus profileStatus) {
        ProfileEntity profileEntity=profileRepository.searchProfileById(id);
        if(profileEntity==null){
            System.out.println("Profile is not found!");
            return;
        }
        profileRepository.changeProfileStatus(id,profileStatus.name());
        System.out.println("profile is updated!");
    }

    public void staffRegistration(ProfileEntity profileEntity) {
        if(!checkValidationUtil.checkPhoneNumber(profileEntity.getPhone())){
            System.out.println("Enter valid phone Number");
            return;
        }
        boolean phoneIsExists=profileRepository.checkPhone(profileEntity.getPhone());
        boolean loginIsExists=profileRepository.checkLogin(profileEntity.getLogin());
        if(phoneIsExists ){
            System.out.println("This phone number is exists!");
            return;
        }else if(loginIsExists){
            System.out.println("This login already taken!");
            return;
        }
        profileEntity.setCreatedDate(LocalDateTime.now());
        profileEntity.setRole(Role.STAFF);
        profileEntity.setStatus(ProfileStatus.ACTIVE);
        profileRepository.registerProfile(profileEntity);
        System.out.println("You have successfully added new staff!");
    }
}
