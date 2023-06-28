package org.example.repository;

import org.example.entity.ProfileEntity;
import org.example.enums.ProfileStatus;
import org.example.enums.Role;
import org.example.mapper.StudentBookInfoMapper;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.lang.annotation.Native;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@Repository
public class ProfileRepository {
    @Autowired
    private SessionFactory sessionFactory;

    public  List<ProfileEntity> getAllStudentList() {
        Session session=sessionFactory.openSession();
        Query query=session.createQuery("from ProfileEntity where role=:role");
        query.setParameter("role", Role.STUDENT.name());
        List<ProfileEntity> list=query.getResultList();
        session.close();
        return list;
    }


    public boolean checkPhone(String phone) {
        Session session=sessionFactory.openSession();
        Query query=session.createQuery(" from ProfileEntity where phone=:phone ");
        query.setParameter("phone",phone);
        List<ProfileEntity> list=query.getResultList();
        return !list.isEmpty();
    }

    public boolean checkLogin(String login) {
        Session session=sessionFactory.openSession();
        Query query=session.createQuery(" from ProfileEntity where login=:login ");
        query.setParameter("login",login);
        List<ProfileEntity> list=query.getResultList();
        return !list.isEmpty();
    }

    public void registerProfile(ProfileEntity profileEntity) {
        Session session=sessionFactory.openSession();
        Transaction transaction=session.beginTransaction();
        session.save(profileEntity);
        transaction.commit();
        session.close();
    }

    public ProfileEntity getByLogin(String login) {
        Session session=sessionFactory.openSession();
        Query query=session.createQuery("from ProfileEntity where login=:login");
        query.setParameter("login",login);
        List<ProfileEntity>profileList=query.getResultList();
       return profileList.isEmpty()?null:profileList.get(0);
    }

    public ProfileEntity searchStudentById(Integer n) {
        Session session=sessionFactory.openSession();
        Query query=session.createQuery("from ProfileEntity where id=:id and role=:role ");
        query.setParameter("id",n);
        query.setParameter("role",Role.STUDENT);
        List<ProfileEntity> list=query.getResultList();
        session.close();
        return list.isEmpty()?null:list.get(0);
    }

    public List<ProfileEntity> getStudentByData(String data) {
        Session session=sessionFactory.openSession();
        Query query=session.createQuery("from ProfileEntity where name=:data or surname=:data and status=:role" +
                " or login=:data or phone=:data");
        query.setParameter("data",data);
        query.setParameter("role",Role.STUDENT);
        List<ProfileEntity> list=query.getResultList();
        session.close();
        return list;
    }

    public int updateStatusOfProfile(int id, String status) {
        Session session=sessionFactory.openSession();
        Transaction transaction=session.beginTransaction();
        Query query=session.createQuery("update ProfileEntity set status=:status where id=:id");
        query.setParameter("status", status);
        query.setParameter("id",id);
        int n=query.executeUpdate();
        transaction.commit();
        session.close();
        return n;
    }
    public List<StudentBookInfoMapper> getStudentBookInfo(int id) {
        Session session=sessionFactory.openSession();
        NativeQuery nativeQuery =session.createNativeQuery("select p.id, p.name, p.surname, p.login,p.phone, p.status, count(tb.status),(select cast(array_agg(b.title)as Text) from\n" +
                "taken_book as tb inner join profile as p on  tb.student_id=p.id \n" +
                "inner join book as b on tb.book_id=b.id where tb.status='TOOK' and p.id= :id )as book_on_hand from taken_book as tb \n" +
                "inner join profile as p on tb.student_id=p.id\n" +
                "inner join book as b on tb.book_id=b.id\n" +
                "where p.id=:id \n" +
                "group by  p.id, p.name, p.surname, p.login, p.status");
        nativeQuery.setParameter("id",id);
        List<Object[]> objList=nativeQuery.getResultList();
        List<StudentBookInfoMapper> list=new LinkedList<>();
        for (Object[] obj: objList){
            for (int i=0; i<objList.size(); i++){
                StudentBookInfoMapper studentBookInfoMapper= new StudentBookInfoMapper();
                studentBookInfoMapper.setId(Integer.valueOf(obj[0].toString()));
                studentBookInfoMapper.setName(obj[1].toString());
                studentBookInfoMapper.setSurname(obj[2].toString());
                studentBookInfoMapper.setLogin(obj[3].toString());
                studentBookInfoMapper.setPhone(obj[4].toString());
                studentBookInfoMapper.setStatus(ProfileStatus.valueOf(obj[5].toString()));
                studentBookInfoMapper.setTakenBookCount(Long.valueOf(obj[6].toString()));
                studentBookInfoMapper.setBookOnHandNamesCount(obj[7].toString());
                list.add(studentBookInfoMapper);
            }
        }
        session.close();
        return list;
    }

//    public List<StudentBookInfoMapper> getStudentBookInfo(int id) {
//        Session session=sessionFactory.openSession();
//            NativeQuery nativeQuery =session.createNativeQuery("select p.id, p.name, p.surname, p.login,p.phone, p.status, count(tb.status),(select count(b.title) from\n" +
//                "taken_book as tb inner join profile as p on  tb.student_id=p.id \n" +
//                "inner join book as b on tb.book_id=b.id where tb.status='TOOK' and p.id= :id )as book_on_hand from taken_book as tb \n" +
//                "inner join profile as p on tb.student_id=p.id\n" +
//                "inner join book as b on tb.book_id=b.id\n" +
//                "where p.id=:id \n" +
//                "group by  p.id, p.name, p.surname, p.login, p.status");
//        nativeQuery.setParameter("id",id);
//        List<Object[]> objList=nativeQuery.getResultList();
//       List<StudentBookInfoMapper> list=new LinkedList<>();
//        for (Object[] obj: objList){
//            for (int i=0; i<objList.size(); i++){
//              StudentBookInfoMapper studentBookInfoMapper= new StudentBookInfoMapper();
//              studentBookInfoMapper.setId(Integer.valueOf(obj[0].toString()));
//              studentBookInfoMapper.setName(obj[1].toString());
//              studentBookInfoMapper.setSurname(obj[2].toString());
//              studentBookInfoMapper.setLogin(obj[3].toString());
//              studentBookInfoMapper.setPhone(obj[4].toString());
//              studentBookInfoMapper.setStatus(ProfileStatus.valueOf(obj[5].toString()));
//              studentBookInfoMapper.setTakenBookCount(Long.valueOf(obj[6].toString()));
//              studentBookInfoMapper.setBookOnHandNamesCount(Integer.valueOf(obj[7].toString()));
//              list.add(studentBookInfoMapper);
//            }
//        }
//        session.close();
//        return list;
//    }
//
    public List<ProfileEntity> getAllProfile() {
        Session session=sessionFactory.openSession();
        Query query=session.createQuery("from ProfileEntity ");
        List<ProfileEntity> profileEntityList=query.getResultList();
        session.close();
        return profileEntityList;
    }

    public ProfileEntity searchProfileById(Integer id) {
        Session session=sessionFactory.openSession();
        Query query=session.createQuery("from ProfileEntity where id=:id");
        query.setParameter("id",id);
        List<ProfileEntity> list=query.getResultList();
        session.close();
        return list.isEmpty()?null:list.get(0);
    }

    public ProfileEntity getProfileByData(String data) {
        Session session=sessionFactory.openSession();
        Query query=session.createQuery("from ProfileEntity where name=:data or surname=:data or login=:data or phone=:data");
        query.setParameter("data",data);
        List<ProfileEntity>profileEntityList=query.getResultList();
        session.close();
        return profileEntityList.isEmpty()?null:profileEntityList.get(0);
    }

    public void changeProfileStatus(int id, String status) {
        Session session=sessionFactory.openSession();
        Transaction transaction=session.beginTransaction();
        Query query=session.createQuery("update ProfileEntity set status=:status where id=:id");
        query.setParameter("status", status);
        query.setParameter("id",id);
        query.executeUpdate();
        transaction.commit();
        session.close();
    }
}
